package com.orderit.app.integratedsupplier.buytillrolls;

import com.orderit.app.dto.ProductDTO;
import com.orderit.app.integratedsupplier.IntegratedSupplierClient;
import com.orderit.app.integratedsupplier.buytillrolls.feign.BuyTillRollsFeignClient;
import com.orderit.app.integratedsupplier.buytillrolls.mapper.BuyTillRollsProductMapper;
import com.orderit.app.integratedsupplier.buytillrolls.model.BuyTillRollsOrder;
import com.orderit.app.integratedsupplier.buytillrolls.model.BuyTillRollsOrderItem;
import com.orderit.app.integratedsupplier.buytillrolls.model.BuyTillRollsProduct;
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
public class BuyTillRollsClient implements IntegratedSupplierClient {
    private final BuyTillRollsFeignClient cafFeignClient;
    private final BuyTillRollsProductMapper cafProductMapper;
    @Value("${feign.buyTillRolls.token}")
    private String token;

    @Override
    public List<ProductDTO> getProductList(String customerAccountNumber) {
        List<BuyTillRollsProduct> cafProducts = cafFeignClient.getProductsForCustomer(customerAccountNumber, token).getBody();
        return cafProductMapper.toProductDTOList(cafProducts);
    }

    @Override
    public void submitOrder(String customerAccountNumber, SupplierOrder order) {
        BuyTillRollsOrder buyTillRollsOrderOrder = new BuyTillRollsOrder();
        buyTillRollsOrderOrder.setCustomerAccountNumber(customerAccountNumber);
        buyTillRollsOrderOrder.setDeliveryDate(order.getDeliveryDate());
        buyTillRollsOrderOrder.setExternalId(order.getId());
        buyTillRollsOrderOrder.setCustomerName(SecurityUtil.getCurrentUserName());

        List<BuyTillRollsOrderItem> buyTillRollsOrderItems = new ArrayList<>();
        order.getSupplierOrderItems().forEach(orderItem -> {
            BuyTillRollsOrderItem buyTillRollsOrderItem = new BuyTillRollsOrderItem();
            buyTillRollsOrderItem.setProductId(orderItem.getSupplierProductId());
            buyTillRollsOrderItem.setQuantity(orderItem.getQuantity());
            buyTillRollsOrderItems.add(buyTillRollsOrderItem);
        });
        buyTillRollsOrderOrder.setItems(buyTillRollsOrderItems);

        cafFeignClient.submitOrder(token,buyTillRollsOrderOrder);
    }
}
