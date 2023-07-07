package com.orderit.app.integratedsupplier.pureoil.feign;

import com.orderit.app.integratedsupplier.pureoil.model.PureOilOrder;
import com.orderit.app.integratedsupplier.pureoil.model.PureOilProduct;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "PureOilFeignClient", url = "${feign.pureoil.url}")
public interface PureOilFeignClient {
    @GetMapping("/api/public/products")
    ResponseEntity<List<PureOilProduct>> getProductsForCustomer(@RequestParam String customerAccountNumber, @RequestParam String token);

    @PostMapping("/api/public/orders")
    @Headers("Content-Type: application/json")
    ResponseEntity<Void> submitOrder(@RequestParam String token, @RequestBody PureOilOrder order);
}
