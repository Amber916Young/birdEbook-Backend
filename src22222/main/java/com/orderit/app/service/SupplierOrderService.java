package com.orderit.app.service;

import com.orderit.app.integratedsupplier.buytillrolls.BuyTillRollsClient;
import com.orderit.app.integratedsupplier.caf.CAFClient;
import com.orderit.app.integratedsupplier.pureoil.PureOilClient;
import com.orderit.common.entity.FavouriteProduct;
import com.orderit.common.entity.FavouriteSupplier;
import com.orderit.common.entity.Supplier;
import com.orderit.common.entity.SupplierOrder;
import com.orderit.common.enums.OrderStatus;
import com.orderit.common.exception.BadRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.SupplierOrderItemRepository;
import com.orderit.common.repository.SupplierOrderRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SupplierOrderService {

    private final CompanyService companyService;
    private final SupplierOrderRepository supplierOrderRepository;
    private final SupplierOrderItemRepository supplierOrderItemRepository;
    private final SupplierOrderEmailService emailOrderService;
    private final SupplierService supplierService;
    private final FavouriteSupplierService favouriteSupplierService;
    private final FavouriteProductService favouriteProductService;
    private final CAFClient cafClient;
    private final BuyTillRollsClient buyTillRollsClient;
    private final PureOilClient pureOilClient;

    public Set<SupplierOrder> getOrdersForCurrentUser() {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();

        return companyService.getCompanyById(companyId).getSupplierOrders();
    }

    public SupplierOrder createOrder(SupplierOrder newSupplierOrder) {
        newSupplierOrder.setCreatedBy(SecurityUtil.getCurrentUserName());
        SupplierOrder savedSupplierOrder = saveOrder(newSupplierOrder);
        if (savedSupplierOrder.getStatus() == OrderStatus.SENT_TO_SUPPLIER) {
            sendOrderToSupplier(savedSupplierOrder, newSupplierOrder);
        }
        return savedSupplierOrder;
    }

    public SupplierOrder updateOrder(SupplierOrder newSupplierOrder) {
        if (newSupplierOrder.getId() != null) {
            SupplierOrder existingSupplierOrder =
                    supplierOrderRepository.findById(newSupplierOrder.getId()).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found));
            if(existingSupplierOrder.getStatus() != OrderStatus.DRAFT ){
                throw new BadRequestException(ErrorReasonCode.Order_Cannot_Update);
            }

            deleteExistingOrderItems(existingSupplierOrder);
            newSupplierOrder.setCreatedBy(existingSupplierOrder.getCreatedBy());
            newSupplierOrder.setCreateTime(existingSupplierOrder.getCreateTime());
            if (newSupplierOrder.getDeliveryDate() == null) {
                newSupplierOrder.setDeliveryDate(existingSupplierOrder.getDeliveryDate());
            }
            if (newSupplierOrder.getSupplierLogoUrl() == null) {
                newSupplierOrder.setSupplierLogoUrl(existingSupplierOrder.getSupplierLogoUrl());
            }
            SupplierOrder savedSupplierOrder = saveOrder(newSupplierOrder);
            if (savedSupplierOrder.getStatus() == OrderStatus.SENT_TO_SUPPLIER) {
                sendOrderToSupplier(savedSupplierOrder, newSupplierOrder);
            }
            return savedSupplierOrder;
        } else {
            throw new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found);
        }
    }
    public void updateOrderFromExternalSupplier(SupplierOrder updatedSupplierOrder) {
        if (updatedSupplierOrder.getId() != null) {
            SupplierOrder existingSupplierOrder =
                    supplierOrderRepository.findById(updatedSupplierOrder.getId()).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found));

            deleteExistingOrderItems(existingSupplierOrder);

            existingSupplierOrder.setTotalVat(updatedSupplierOrder.getTotalVat());
            existingSupplierOrder.setTotalPrice(updatedSupplierOrder.getTotalPrice());
            existingSupplierOrder.setDeliveryDate(updatedSupplierOrder.getDeliveryDate());
            existingSupplierOrder.setSupplierOrderItems(updatedSupplierOrder.getSupplierOrderItems());
            existingSupplierOrder.setStatus(OrderStatus.UPDATED_BY_SUPPLIER);

            Long companyId = existingSupplierOrder.getCompanyId();
            Long supplierId = existingSupplierOrder.getSupplierId();
            existingSupplierOrder.getSupplierOrderItems().forEach(orderItem -> {
                orderItem.setSupplierOrder(existingSupplierOrder);
            });

            existingSupplierOrder.getSupplierOrderItems().forEach(orderItem -> {
                Long productId = orderItem.getSupplierProductId();
                // fetch nickname logic here
                Optional<FavouriteProduct> favouriteProduct = favouriteProductService
                        .getFavouriteProductBySupplierIdAndSupplierProductIdAndCompanyId(supplierId, productId, companyId);
                favouriteProduct.ifPresent(product -> orderItem.setNickname(product.getNickname()));
            });

            emailOrderService.emailOrderStatusToUser(existingSupplierOrder);
            supplierOrderRepository.save(existingSupplierOrder);
        } else {
            throw new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found);
        }
    }

    public void deleteOrder(long id) {
        SupplierOrder existingSupplierOrder = supplierOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Order_Already_Delete));
        if(existingSupplierOrder.getStatus() == OrderStatus.DRAFT){
            supplierOrderRepository.deleteById(id);
        }else {
            throw new BadRequestException(ErrorReasonCode.Order_Cannot_Found);
        }
    }

    public SupplierOrder confirmOrder(Long id) {
        if (id != null && supplierOrderRepository.existsById(id)) {
            SupplierOrder existingSupplierOrder = supplierOrderRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found));

            if (existingSupplierOrder.getStatus() != OrderStatus.CONFIRMED) {
                existingSupplierOrder.setStatus(OrderStatus.CONFIRMED);

                emailOrderService.emailOrderStatusToUser(existingSupplierOrder);
                return supplierOrderRepository.save(existingSupplierOrder);
            }

            return existingSupplierOrder;
        } else {
            throw new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found);
        }
    }

    public void rejectOrder(Long id) {
        if (id != null && supplierOrderRepository.existsById(id)) {
            SupplierOrder existingSupplierOrder = supplierOrderRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found));

            if (existingSupplierOrder.getStatus() != OrderStatus.REJECTED) {
                existingSupplierOrder.setStatus(OrderStatus.REJECTED);

                emailOrderService.emailOrderStatusToUser(existingSupplierOrder);
                supplierOrderRepository.save(existingSupplierOrder);
            }
        } else {
            throw new NotFoundRequestException(ErrorReasonCode.Order_Cannot_Found);
        }
    }

    private SupplierOrder saveOrder(SupplierOrder supplierOrderToSave) {
        supplierOrderToSave.getSupplierOrderItems().forEach(orderItem ->
                orderItem.setSupplierOrder(supplierOrderToSave));

        supplierOrderToSave.setCompanyId(SecurityUtil.getCurrentUserCompanyId());

        return supplierOrderRepository.save(supplierOrderToSave);
    }

    private void deleteExistingOrderItems(SupplierOrder supplierOrder) {
        supplierOrderItemRepository.deleteAll(supplierOrder.getSupplierOrderItems());
    }


    private void sendOrderToSupplier(SupplierOrder savedSupplierOrder, SupplierOrder newSupplierOrder) {
        Supplier supplier = supplierService.getSupplierById(newSupplierOrder.getSupplierId());
        if (BooleanUtils.isTrue(supplier.getIsIntegratedSupplier())) {
            Optional<FavouriteSupplier> favouriteSupplier = favouriteSupplierService.getFavouriteSupplierForCurrentCompany(supplier.getId());
            switch (supplier.getSupplierName()) {
                case "CAF Trading Company": {
                    favouriteSupplier.ifPresent(value -> cafClient.submitOrder(value.getAccountNumber(), savedSupplierOrder));
                    break;
                }
                case "Buy Till Rolls": {
                    favouriteSupplier.ifPresent(value -> buyTillRollsClient.submitOrder(value.getAccountNumber(), savedSupplierOrder));
                    break;
                }
                case "Pure Oil": {
                    favouriteSupplier.ifPresent(value -> pureOilClient.submitOrder(value.getAccountNumber(), savedSupplierOrder));
                    break;
                }
            }
        } else {
            emailOrderService.emailOrderToSupplier(savedSupplierOrder);
        }
    }
}
