package com.orderit.common.utils.email;

import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.app.dto.email.*;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailTemplateFetcher {

    private static final Locale LOCALE = Locale.forLanguageTag("en");
    private final SpringTemplateEngine templateEngine;

    public String getResetPasswordEmailBody(String name, String resetPasswordLink) {
        Context context = new Context(LOCALE);
        context.setVariable("name", name);
        context.setVariable("resetPasswordLink", resetPasswordLink);
        return templateEngine.process("resetPasswordEmail", context);
    }

    public String getOrderFromSupplierEmailBody(OrderEmailDTO order) {
        Context context = new Context(LOCALE);
        context.setVariable("supplier_name", order.getSupplierName());
        context.setVariable("supplier_order_id", order.getOrderId());
        context.setVariable("company_name", order.getCompanyName());
        context.setVariable("company_contact_name", order.getCompanyContactName());
        context.setVariable("company_contact_number", order.getCompanyContactNumber());
        context.setVariable("company_order_date",order.getOrderDate() );
        context.setVariable("company_account_number", order.getCompanyAccountNumber() );
        context.setVariable("products", order.getProducts());
        context.setVariable("confirm_link", order.getConfirmLink());
        context.setVariable("reject_link", order.getRejectLink());
        context.setVariable("contact_email", order.getContactEmail());
        context.setVariable("delivery_date", order.getDeliveryDate());

        String company_address = order.getCompanyAddressLine1().trim();
        if(!order.getCompanyAddressLine2().trim().isEmpty()){
            company_address += "," +order.getCompanyAddressLine2().trim();
        }
        if(!order.getCompanyAddressLine3().trim().isEmpty()){
            company_address += "," +order.getCompanyAddressLine3().trim();
        }
        context.setVariable("company_address", company_address);

        return templateEngine.process("orderFromSupplierEmail", context);
    }

    public String getSupplierRegistrationEmailBody(SupplierRegistrationEmailDTO registrationEmail) {
        Context context = new Context(LOCALE);
        context.setVariable("company_name", registrationEmail.getCompanyName());
        context.setVariable("supplier_name", registrationEmail.getSupplierName());
        context.setVariable("company_email", registrationEmail.getCompanyEmail());
        context.setVariable("company_address", registrationEmail.getAddress());
        context.setVariable("company_account_number", registrationEmail.getCompanyAccountNumber());
        context.setVariable("owner_name", registrationEmail.getOwnerName());
        context.setVariable("owner_email", registrationEmail.getOwnerEmail());
        context.setVariable("owner_account_number", registrationEmail.getOwnerAccountNumber());
        context.setVariable("owners_name", registrationEmail.getOwnersName());
        context.setVariable("owners_email", registrationEmail.getOwnersEmail());
        context.setVariable("owners_account_number", registrationEmail.getOwnersAccountNumber());
        return templateEngine.process("supplierRegistrationEmail", context);

    }

    public String getActivateEmailBody(ActivateEmailDTO activateEmail,String resetPasswordLink) {
        Context context = new Context(LOCALE);
        context.setVariable("company_name", activateEmail.getCompanyName());
        context.setVariable("resetPasswordLink", resetPasswordLink);
        context.setVariable("name", activateEmail.getName());
        return templateEngine.process("activateEmail", context);
    }

    public String getOrderUpdateEmailBody( OrderUpdateEmailDTO updateEmailDTO) {
        Context context = new Context(LOCALE);
        context.setVariable("company_name", updateEmailDTO.getCompanyName());
        context.setVariable("order_status", updateEmailDTO.getOrderStatus());
        context.setVariable("order_id", updateEmailDTO.getOrderId());
        context.setVariable("supplier_name", updateEmailDTO.getSupplierName());
        context.setVariable("view_link", updateEmailDTO.getViewLink());
        return templateEngine.process("orderStatusEmail", context);
    }

    public String getCompanyRegistrationEmailBody(CompanyRegistrationEmailDTO registrationEmailDTO) {
        Context context = new Context(LOCALE);
        Company company = registrationEmailDTO.getCompany();
        CompanyUser companyUser = registrationEmailDTO.getCompanyUser();
        context.setVariable("company_name", company.getCompanyName());
        context.setVariable("company_email", company.getCompanyEmail());
        context.setVariable("company_contact_number", company.getContactNumber());
        context.setVariable("company_contact_name", company.getContactName());
        context.setVariable("company_address", company.getAddressLine1() + ", "+company.getAddressLine2() +
                ((company.getAddressLine1()  !=null && !company.getAddressLine1().isEmpty()) ? ", "+company.getAddressLine3() :""));

        context.setVariable("owner_name",companyUser.getFirstName()+", "+ companyUser.getLastName());
        context.setVariable("email", companyUser.getEmail());
        context.setVariable("contactNumber", companyUser.getContactNumber());


        return templateEngine.process("CompanyRegistrationEmail", context);

    }
}
