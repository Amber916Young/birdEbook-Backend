package com.orderit.app.integratedsupplier.pureoil;

import com.orderit.app.dto.ProductDTO;
import com.orderit.app.integratedsupplier.IntegratedSupplierClient;
import com.orderit.app.integratedsupplier.pureoil.feign.PureOilFeignClient;
import com.orderit.app.integratedsupplier.pureoil.mapper.PureOilProductMapper;
import com.orderit.app.integratedsupplier.pureoil.model.PureOilOrder;
import com.orderit.app.integratedsupplier.pureoil.model.PureOilOrderItem;
import com.orderit.app.integratedsupplier.pureoil.model.PureOilProduct;
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
public class PureOilClient implements IntegratedSupplierClient {
    private final PureOilFeignClient pureOilFeignClient;
    private final PureOilProductMapper pureOilProductMapper;
    @Value("${feign.pureoil.token}")
    private String token;

    @Override
    public List<ProductDTO> getProductList(String customerAccountNumber) {
        List<PureOilProduct> pureOilProducts =
                pureOilFeignClient.getProductsForCustomer(customerAccountNumber, token).getBody();
        return pureOilProductMapper.toProductDTOList(pureOilProducts);
    }

    @Override
    public void submitOrder(String customerAccountNumber, SupplierOrder order) {
        PureOilOrder pureOilOrder = new PureOilOrder();
        pureOilOrder.setCustomerAccountNumber(customerAccountNumber);
        pureOilOrder.setDeliveryDate(order.getDeliveryDate());
        pureOilOrder.setExternalId(order.getId());
        pureOilOrder.setCustomerName(SecurityUtil.getCurrentUserName());

        List<PureOilOrderItem> pureOilOrderItems = new ArrayList<>();
        order.getSupplierOrderItems().forEach(orderItem -> {
            PureOilOrderItem pureOilOrderItem = new PureOilOrderItem();
            pureOilOrderItem.setProductId(orderItem.getSupplierProductId());
            pureOilOrderItem.setQuantity(orderItem.getQuantity());
            pureOilOrderItems.add(pureOilOrderItem);
        });
        pureOilOrder.setItems(pureOilOrderItems);

        pureOilFeignClient.submitOrder(token, pureOilOrder);
    }
}
