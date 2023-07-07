package com.orderit.app.integratedsupplier.caf.feign;

import com.orderit.app.integratedsupplier.caf.model.CAFOrder;
import com.orderit.app.integratedsupplier.caf.model.CAFProduct;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "CAFFeignClient", url = "${feign.caf.url}")
public interface CAFFeignClient {
    @GetMapping("/api/public/products")
    ResponseEntity<List<CAFProduct>> getProductsForCustomer(@RequestParam String customerAccountNumber, @RequestParam String token);

    @PostMapping("/api/public/orders")
    @Headers("Content-Type: application/json")
    ResponseEntity<Void> submitOrder(@RequestParam String token, @RequestBody CAFOrder order);
}
