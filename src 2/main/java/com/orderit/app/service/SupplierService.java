package com.orderit.app.service;

import com.orderit.app.dto.SupplierDTO;
import com.orderit.app.dto.ProductDTO;
import com.orderit.app.integratedsupplier.buytillrolls.BuyTillRollsClient;
import com.orderit.app.integratedsupplier.caf.CAFClient;
import com.orderit.app.integratedsupplier.pureoil.PureOilClient;
import com.orderit.app.mapper.CustomProductMapper;
import com.orderit.app.mapper.SupplierMapper;
import com.orderit.app.mapper.SupplierProductMapper;
import com.orderit.common.entity.CustomProduct;
import com.orderit.common.entity.FavouriteProduct;
import com.orderit.common.entity.FavouriteSupplier;
import com.orderit.common.entity.Supplier;
import com.orderit.common.enums.ProductType;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.SupplierRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SupplierService {
    private final FavouriteProductService favouriteProductService;
    private final FavouriteSupplierService favouriteSupplierService;
    private final CustomProductService customProductService;
    private final SupplierMapper supplierMapper;

    private final CustomProductMapper customProductMapper;
    private final SupplierProductMapper supplierProductMapper;
    private final SupplierRepository supplierRepository;
    @Value("${application.internal-company-id}")
    private long internalCompanyId;
    private final CAFClient cafClient;
    private final BuyTillRollsClient buyTillRollsClient;
    private final PureOilClient pureOilClient;


    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Supplier_Not_Found));
    }

    public List<Supplier> getAllSuppliers() {
        // return published suppliers for external user, and return all suppliers for internal user
        if (SecurityUtil.getCurrentUserCompanyId() == internalCompanyId) {
            return supplierRepository.findAll();
        } else {
            return supplierRepository.findByPublishedTrue();
        }
    }

    public List<SupplierDTO> getAllSuppliersDTO() {
        List<SupplierDTO> suppliers = supplierMapper.toDTOList(getAllSuppliers());
        List<FavouriteSupplier> favouriteSuppliers = favouriteSupplierService.getAllFavouriteSupplierForCurrentCompany();

        suppliers.forEach(supplier -> {
            supplier.setSupplierProducts(new HashSet<>());
            favouriteSuppliers.forEach(favouriteSupplier -> {
                if (Objects.equals(favouriteSupplier.getSupplierId(), supplier.getId())) {
                    supplier.setAccountNumber(favouriteSupplier.getAccountNumber());
                    supplier.setShopName(favouriteSupplier.getShopName());
                }
            });
        });
        return suppliers;
    }

    private void addCustomProducts(List<CustomProduct> customProducts, List<ProductDTO> productDTOs) {
        customProducts.forEach(customProduct -> {
            ProductDTO newProduct = new ProductDTO();
            newProduct.setType(ProductType.CUSTOM);
            newProduct.setProductCode(customProduct.getProductCode());
            newProduct.setProductName(customProduct.getProductName());
            newProduct.setNickname(customProduct.getProductName());
            newProduct.setPrice(customProduct.getPrice());
            newProduct.setId(customProduct.getId());
            newProduct.setFavId(customProduct.getId());
            newProduct.setUnit(customProduct.getUnit());
            newProduct.setCategory(customProduct.getCategory());
            newProduct.setVat(customProduct.getVat());
            newProduct.setDescription(customProduct.getDescription());
            newProduct.setSupplierId(customProduct.getSupplierId());
            newProduct.setProductImageUrl(customProduct.getProductImageUrl());
            productDTOs.add(newProduct);
        });
    }


    public List<ProductDTO> getAllSupplierProductsDTO(Long supplierId) {
        Supplier supplier = getSupplierById(supplierId);
        Long companyId = SecurityUtil.getCurrentUserCompanyId();

        List<FavouriteProduct> favouriteProducts =
                favouriteProductService.getFavouriteProductBySupplierIdAndCompanyId(supplierId, companyId);


        List<CustomProduct> customProducts = customProductService.getCustomProductsBySupplierIdAndCompanyId(supplierId, companyId);

        if (BooleanUtils.isTrue(supplier.getIsIntegratedSupplier())) {
            switch (supplier.getSupplierName()) {
                case "CAF Trading Company": {
                    // CAF only accepts existing customer in its system
                    Optional<FavouriteSupplier> customerAccount = favouriteSupplierService
                            .getFavouriteSupplierForCurrentCompany(supplierId);

                    if (customerAccount.isPresent()) {
                        List<ProductDTO> cafProducts =
                                cafClient.getProductList(customerAccount.get().getAccountNumber());
                        setSupplierIdAndFavDetailsForIntegratedSupplierProducts(supplierId, favouriteProducts, cafProducts);
                        addCustomProducts(customProducts, cafProducts);
                        return cafProducts;
                    }
                    break;
                }
                case "Buy Till Rolls": {
                    // Buy Till Rolls only accepts existing customer in its system
                    Optional<FavouriteSupplier> customerAccount = favouriteSupplierService
                            .getFavouriteSupplierForCurrentCompany(supplierId);

                    if (customerAccount.isPresent()) {
                        List<ProductDTO> buyTillRollsProducts =
                                buyTillRollsClient.getProductList(customerAccount.get().getAccountNumber());
                        setSupplierIdAndFavDetailsForIntegratedSupplierProducts(supplierId, favouriteProducts, buyTillRollsProducts);
                        addCustomProducts(customProducts, buyTillRollsProducts);
                        return buyTillRollsProducts;
                    }
                    break;
                }
                case "Pure Oil": {
                    // Pure Oil only accepts existing customer in its system
                    Optional<FavouriteSupplier> customerAccount = favouriteSupplierService
                            .getFavouriteSupplierForCurrentCompany(supplierId);

                    if (customerAccount.isPresent()) {
                        List<ProductDTO> pureOilProducts =
                                pureOilClient.getProductList(customerAccount.get().getAccountNumber());
                        setSupplierIdAndFavDetailsForIntegratedSupplierProducts(supplierId, favouriteProducts, pureOilProducts);
                        addCustomProducts(customProducts, pureOilProducts);
                        return pureOilProducts;
                    }
                    break;
                }
            }
        } else {
            List<ProductDTO> supplierProducts = supplierProductMapper.toDTOList(supplier.getSupplierProducts());
            setSupplierIdAndFavDetailsForIntegratedSupplierProducts(supplierId, favouriteProducts, supplierProducts);
            addCustomProducts(customProducts, supplierProducts);

            return supplierProducts;
        }

        throw new NotFoundRequestException(ErrorReasonCode.Supplier_Not_Found);
    }

    private void setSupplierIdAndFavDetailsForIntegratedSupplierProducts(Long supplierId,
                                                                         List<FavouriteProduct> favouriteProducts,
                                                                         List<ProductDTO> products) {
        AtomicInteger count = new AtomicInteger(0);
        products.forEach(product -> {
            // supplierId is missing from integrated supplier, so we need to manually set it
            product.setSupplierId(supplierId);
            product.setType(ProductType.GENERAL);
            if (count.intValue() < favouriteProducts.size()) {
                Optional<FavouriteProduct> favouriteProduct =
                        getFavProductFromProductList(product.getId(), favouriteProducts);
                if (favouriteProduct.isPresent()) {
                    count.getAndIncrement();
                    product.setNickname(favouriteProduct.get().getNickname());
                    product.setFavId(favouriteProduct.get().getId());
                }
            }

        });
    }

    private Optional<FavouriteProduct> getFavProductFromProductList(Long productId,
                                                                    List<FavouriteProduct> favouriteProducts) {
        return favouriteProducts.stream()
                .filter(favouriteSupplier -> Objects.equals(favouriteSupplier.getSupplierProductId(), productId))
                .findFirst();
    }
}
