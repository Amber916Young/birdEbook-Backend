package com.orderit.app.integratedsupplier.buytillrolls.feign;

import com.orderit.app.integratedsupplier.buytillrolls.model.BuyTillRollsOrder;
import com.orderit.app.integratedsupplier.buytillrolls.model.BuyTillRollsProduct;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "BuyTillRollsFeignClient", url = "${feign.buyTillRolls.url}")
public interface BuyTillRollsFeignClient {
    @GetMapping("/api/public/products")
    ResponseEntity<List<BuyTillRollsProduct>> getProductsForCustomer(@RequestParam String customerAccountNumber, @RequestParam String token);

    @PostMapping("/api/public/orders")
    @Headers("Content-Type: application/json")
    ResponseEntity<Void> submitOrder(@RequestParam String token, @RequestBody BuyTillRollsOrder order);
}
