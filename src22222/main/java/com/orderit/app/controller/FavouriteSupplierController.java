package com.orderit.app.controller;

import com.orderit.app.dto.FavouriteSupplierDTO;
import com.orderit.app.dto.ProductDTO;
import com.orderit.app.dto.email.SupplierRegistrationEmailDTO;
import com.orderit.app.mapper.FavouriteSupplierMapper;
import com.orderit.app.service.FavouriteSupplierService;
import com.orderit.common.entity.FavouriteSupplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/favouriteSuppliers")
@RequiredArgsConstructor
@Slf4j
public class FavouriteSupplierController {

    private final FavouriteSupplierService favouriteSupplierService;
    private final FavouriteSupplierMapper favouriteSupplierMapper;


    @PostMapping(produces = "application/json")
    public ResponseEntity<FavouriteSupplierDTO> createFavouriteSupplier(@Valid @RequestBody FavouriteSupplierDTO favouriteSupplierDTO) {
        FavouriteSupplier savedFavouriteSupplier = favouriteSupplierService.createFavourite(
                favouriteSupplierMapper.toEntity(favouriteSupplierDTO));

        return new ResponseEntity<>(favouriteSupplierMapper.toDTO(savedFavouriteSupplier), HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<FavouriteSupplierDTO> updateFavouriteSupplier(@Valid @RequestBody FavouriteSupplierDTO favouriteSupplierDTO) {
        FavouriteSupplier updatedFavouriteProduct = favouriteSupplierService.updateFavourite(
                favouriteSupplierMapper.toEntity(favouriteSupplierDTO));

        return new ResponseEntity<>(favouriteSupplierMapper.toDTO(updatedFavouriteProduct), HttpStatus.OK);
    }


    @PostMapping(value = "/request",produces = "application/json")
    public ResponseEntity<SupplierRegistrationEmailDTO> requestRegistrationToSupplier(@Valid @RequestBody SupplierRegistrationEmailDTO registrationEmailDTO) {
        favouriteSupplierService.sendRegistrationRequestToSupplier(registrationEmailDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{supplierId}/request",produces = "application/json")
    public ResponseEntity<SupplierRegistrationEmailDTO> requestRegistrationToSupplierById(@PathVariable("supplierId") long supplierId) {
        favouriteSupplierService.sendRegistrationRequestToSupplierById(supplierId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
