package com.orderit.app.service;

import com.orderit.app.dto.email.SupplierRegistrationEmailDTO;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.entity.FavouriteSupplier;
import com.orderit.common.entity.Supplier;
import com.orderit.common.enums.Role;
import com.orderit.common.exception.ConflictRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.FavouriteSupplierRepository;
import com.orderit.common.repository.SupplierRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FavouriteSupplierService {
    private final FavouriteSupplierRepository favouriteSupplierRepository;
    private final SupplierRepository supplierRepository;
    private final EmailSenderService emailSenderService;
    private final SupplierContactHistoryService supplierContactHistoryService;
    private final CompanyService companyService;
    private final UserService userService;

    public FavouriteSupplier createFavourite(FavouriteSupplier favouriteSupplier) {
        long companyId = SecurityUtil.getCurrentUserCompanyId();
        Company company = companyService.getCompanyById(companyId);

        favouriteSupplier.setCompanyId(companyId);
        favouriteSupplier.setShopName(company.getCompanyName());
        // if fav already exists, throw error
        if (favouriteSupplierRepository.existsByCompanyIdAndSupplierId(favouriteSupplier.getCompanyId(), favouriteSupplier.getSupplierId())) {
            throw new ConflictRequestException(ErrorReasonCode.Favourite_Supplier_Already_Update);
        }

        return favouriteSupplierRepository.save(favouriteSupplier);
    }

    public FavouriteSupplier updateFavourite(FavouriteSupplier favouriteSupplier) {
        if (favouriteSupplier.getId() != null) {
            FavouriteSupplier updateFavouriteSupplier = favouriteSupplierRepository.findById(favouriteSupplier.getId()).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Favourite_Supplier_Not_Exist));
            favouriteSupplier.setCompanyId(SecurityUtil.getCurrentUserCompanyId());
            favouriteSupplier.setCreateTime(updateFavouriteSupplier.getCreateTime());

            return favouriteSupplierRepository.save(favouriteSupplier);
        } else {
            throw new NotFoundRequestException(ErrorReasonCode.Favourite_Supplier_Not_Exist);
        }
    }

    public void sendRegistrationRequestToSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Favourite_Supplier_Not_Exist));

        Company company = companyService.getCompanyForCurrentUser();
        SupplierRegistrationEmailDTO registrationEmail = new SupplierRegistrationEmailDTO();
        registrationEmail.setSupplierName(supplier.getSupplierName());
        registrationEmail.setSupplierEmail(supplier.getSupplierEmail());
        registrationEmail.setSupplierId(supplierId);
        registrationEmail.setCompanyEmail(company.getCompanyEmail());
        registrationEmail.setCompanyName(company.getCompanyName());
        registrationEmail.setAddress(company.getAddressLine1() + " " + company.getAddressLine2() + " " + company.getAddressLine3());
        emailSenderService.sendRegistrationRequestToSupplier(registrationEmail);
        supplierContactHistoryService.createSupplerContactHistory(registrationEmail.getSupplierId());
    }

    public void sendRegistrationRequestToSupplier(SupplierRegistrationEmailDTO registrationEmail) {
        Supplier supplier = supplierRepository.findById(registrationEmail.getSupplierId()).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Favourite_Supplier_Not_Exist));

        registrationEmail.setSupplierName(supplier.getSupplierName());
        registrationEmail.setSupplierEmail(supplier.getSupplierEmail());
        List<CompanyUser> companyUsers = userService.findCompanyUserByCompanyIdAndRole(Role.ROLE_COMPANY_OWNER);
        if (companyUsers.size() > 0) {
            CompanyUser owner = companyUsers.get(0);
            registrationEmail.setOwnerEmail(owner.getEmail());
            registrationEmail.setOwnerName(owner.getFirstName() + " " + owner.getLastName());
            registrationEmail.setOwnerAccountNumber(owner.getContactNumber());
            String ownersEmails = companyUsers.stream().map(a -> String.valueOf(a.getEmail())).collect(Collectors.joining(", "));
            String ownersName = companyUsers.stream().map(a -> a.getFirstName() + " " + a.getLastName()).collect(Collectors.joining(", "));
            String ownersAccountNumber = companyUsers.stream().map(a -> String.valueOf(a.getContactNumber())).collect(Collectors.joining(", "));


            ownersEmails = ownersEmails.trim();
            if (ownersEmails.endsWith(",")) {
                ownersEmails = ownersEmails.substring(0, ownersEmails.length() - 1);
            }
            ownersName = ownersName.trim();
            if (ownersName.endsWith(",")) {
                ownersName = ownersName.substring(0, ownersName.length() - 1);
            }
            ownersAccountNumber = ownersAccountNumber.trim();
            if (ownersAccountNumber.endsWith(",")) {
                ownersAccountNumber = ownersAccountNumber.substring(0, ownersAccountNumber.length() - 1);
            }

            registrationEmail.setOwnersEmail(ownersEmails);
            registrationEmail.setOwnersName(ownersName);
            registrationEmail.setOwnersAccountNumber(ownersAccountNumber);
        }


        emailSenderService.sendRegistrationRequestToSupplier(registrationEmail);
        supplierContactHistoryService.createSupplerContactHistory(registrationEmail.getSupplierId());
    }


    public Optional<FavouriteSupplier> getFavouriteSupplierForCurrentCompany(Long supplierId) {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();
        return favouriteSupplierRepository.getFavouriteSupplierForCurrentCompanyByCompanyIdAndSupplierId(companyId, supplierId);
    }

    public List<FavouriteSupplier> getAllFavouriteSupplierForCurrentCompany() {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();
        return favouriteSupplierRepository.getFavouriteSuppliersByCompanyId(companyId);
    }
}
