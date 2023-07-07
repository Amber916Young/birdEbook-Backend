package com.orderit.app.integratedsupplier.caf;

import com.orderit.app.dto.ProductDTO;
import com.orderit.app.integratedsupplier.IntegratedSupplierClient;
import com.orderit.app.integratedsupplier.caf.feign.CAFFeignClient;
import com.orderit.app.integratedsupplier.caf.mapper.CAFProductMapper;
import com.orderit.app.integratedsupplier.caf.model.CAFOrder;
import com.orderit.app.integratedsupplier.caf.model.CAFOrderItem;
import com.orderit.app.integratedsupplier.caf.model.CAFProduct;
import com.orderit.common.entity.SupplierOrder;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CAFClient implements IntegratedSupplierClient {
    private final CAFFeignClient cafFeignClient;
    private final CAFProductMapper cafProductMapper;
    @Value("${feign.caf.token}")
    private String token;

    @Override
    public List<ProductDTO> getProductList(String customerAccountNumber) {
        List<CAFProduct> cafProducts = cafFeignClient.getProductsForCustomer(customerAccountNumber, token).getBody();
        return cafProductMapper.toProductDTOList(cafProducts);
    }

    @Override
    public void submitOrder(String customerAccountNumber, SupplierOrder order) {
        CAFOrder cafOrder = new CAFOrder();
        cafOrder.setCustomerAccountNumber(customerAccountNumber);
        cafOrder.setDeliveryDate(order.getDeliveryDate());
        cafOrder.setExternalId(order.getId());
        cafOrder.setCustomerName(SecurityUtil.getCurrentUserName());

        List<CAFOrderItem> cafOrderItems = new ArrayList<>();
        order.getSupplierOrderItems().forEach(orderItem -> {
            CAFOrderItem cafOrderItem = new CAFOrderItem();
            cafOrderItem.setProductId(orderItem.getSupplierProductId());
            cafOrderItem.setQuantity(orderItem.getQuantity());
            cafOrderItems.add(cafOrderItem);
        });
        cafOrder.setItems(cafOrderItems);

        cafFeignClient.submitOrder(token,cafOrder);
    }
}
