package com.orderit.app.service;

import com.orderit.common.entity.FavouriteProduct;
import com.orderit.common.exception.ConflictRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.FavouriteProductRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FavouriteProductService {

    private final CompanyService companyService;
    private final FavouriteProductRepository favouriteProductRepository;

    public Set<FavouriteProduct> getFavouriteProductsForCurrentUser() {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();
        return companyService.getCompanyById(companyId).getFavouriteProducts();
    }


    public FavouriteProduct createFavourite(FavouriteProduct favouriteProduct) {
        favouriteProduct.setCompanyId(SecurityUtil.getCurrentUserCompanyId());
        // if fav already exists, throw error
        if (favouriteProductRepository.existsByCompanyIdAndSupplierIdAndSupplierProductId(favouriteProduct.getCompanyId(), favouriteProduct.getSupplierId(), favouriteProduct.getSupplierProductId())) {
            throw new ConflictRequestException(ErrorReasonCode.Favourite_Product_Already_Exists);
        }
        return favouriteProductRepository.save(favouriteProduct);
    }

    public FavouriteProduct updateFavourite(FavouriteProduct favouriteProduct) {
        if (favouriteProduct.getId() != null) {
            FavouriteProduct updateFavouriteProduct = favouriteProductRepository.findById(favouriteProduct.getId()).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Favourite_Product_Already_Delete));
            favouriteProduct.setCompanyId(SecurityUtil.getCurrentUserCompanyId());
            favouriteProduct.setCreateTime(updateFavouriteProduct.getCreateTime());
            return favouriteProductRepository.save(favouriteProduct);
        } else {
            throw new NotFoundRequestException(ErrorReasonCode.Favourite_Product_Already_Delete);
        }
    }

    public void deleteFavourite(long id) {
        favouriteProductRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Favourite_Product_Already_Delete));

        favouriteProductRepository.deleteById(id);
    }

    public Optional<FavouriteProduct> getFavouriteProductBySupplierIdAndSupplierProductIdAndCompanyId(Long supplierId, Long supplierProductId, Long companyId) {
        return favouriteProductRepository.getFavouriteProductBySupplierIdAndSupplierProductIdAndCompanyId(supplierId, supplierProductId, companyId);
    }

    public List<FavouriteProduct> getFavouriteProductBySupplierIdAndCompanyId(Long supplierId, Long companyId) {
        return favouriteProductRepository.getFavouriteProductBySupplierIdAndCompanyId(supplierId, companyId);
    }
}
