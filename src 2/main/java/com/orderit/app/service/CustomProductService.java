package com.orderit.app.service;

import com.orderit.common.entity.CustomProduct;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.CustomProductRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomProductService {
    private final FileService fileService;
    private final CustomProductRepository customProductRepository;


    public CustomProduct createCustomProduct(CustomProduct customProduct) {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();
        customProduct.setCompanyId(companyId);
        return  customProductRepository.save(customProduct);
    }

    public CustomProduct updateCustomProduct(CustomProduct customProduct) {
        if (customProduct.getId() != null) {
            CustomProduct updateCustomProduct = customProductRepository.findById(customProduct.getId()).orElseThrow(()->
                    new NotFoundRequestException(ErrorReasonCode.Custom_Product_Not_Found) );
            customProduct.setCreateTime(updateCustomProduct.getCreateTime());
            customProduct.setCompanyId(SecurityUtil.getCurrentUserCompanyId());
            return  customProductRepository.save(customProduct);

        }else {
            throw new NotFoundRequestException(ErrorReasonCode.Custom_Product_Not_Found);
        }
    }

    public void deleteCustomProduct(Long id) {
        CustomProduct customProduct =  customProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Custom_Product_Already_Delete));
        fileService.deleteOriginalProductImage(customProduct.getProductImageUrl());
        customProductRepository.deleteById(id);
    }

    public List<CustomProduct> getCustomProductsBySupplierIdAndCompanyId(Long supplierId, Long companyId) {
        return customProductRepository.getCustomProductBySupplierIdAndCompanyId(supplierId,companyId);

    }
}
