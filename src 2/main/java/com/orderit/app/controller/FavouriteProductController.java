package com.orderit.app.controller;

import com.orderit.app.dto.FavouriteProductDTO;
import com.orderit.app.mapper.FavouriteProductMapper;
import com.orderit.app.service.FavouriteProductService;
import com.orderit.common.entity.FavouriteProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/favouriteProducts")
@RequiredArgsConstructor
@Slf4j
public class FavouriteProductController {
    private final FavouriteProductService favouriteProductService;
    private final FavouriteProductMapper favouriteProductMapper;

    @PostMapping(produces = "application/json")
    public ResponseEntity<FavouriteProductDTO> createFavourite(@Valid @RequestBody FavouriteProductDTO favouriteProductDTO) {
        FavouriteProduct savedFavouriteProduct = favouriteProductService.createFavourite(favouriteProductMapper.toEntity(favouriteProductDTO));

        return new ResponseEntity<>(favouriteProductMapper.toDTO(savedFavouriteProduct), HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<FavouriteProductDTO> updateFavourite(@Valid @RequestBody FavouriteProductDTO favouriteProductDTO) {
        FavouriteProduct updatedFavouriteProduct = favouriteProductService.updateFavourite(favouriteProductMapper.toEntity(favouriteProductDTO));

        return new ResponseEntity<>(favouriteProductMapper.toDTO(updatedFavouriteProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FavouriteProductDTO> deleteFavourite(@PathVariable Long id) {
        favouriteProductService.deleteFavourite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
