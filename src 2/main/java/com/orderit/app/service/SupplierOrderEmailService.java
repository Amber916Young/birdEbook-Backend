package com.orderit.app.service;

import com.orderit.app.dto.email.EmailRequestDTO;
import com.orderit.app.dto.email.OrderEmailDTO;
import com.orderit.app.dto.email.OrderProductEmailDTO;
import com.orderit.app.dto.email.OrderUpdateEmailDTO;
import com.orderit.common.entity.Company;
import com.orderit.common.entity.FavouriteSupplier;
import com.orderit.common.entity.Supplier;
import com.orderit.common.entity.SupplierOrder;
import com.orderit.common.utils.SecurityUtil;
import com.orderit.common.utils.email.EmailSender;
import com.orderit.common.utils.email.EmailTemplateFetcher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SupplierOrderEmailService {
    private final static String ORDER_EMAIL_SUBJECT = "You Have A New Delivery Order From %s";
    private final static String UPDATE_ORDER_EMAIL_SUBJECT = "Your Order Has Updated By %s";
    private final static String CONFIRM_LINK = "%s/public/supplierOrderConfirmation/%s";
    private final static String REJECT_LINK = "%s/public/supplierOrderRejection/%s";
    private final static String VIEW_LINK = "%s/public/orders/%s";

    @Value("${application.order-email}")
    private String orderEmail;
    @Value("${application.app-url}")
    private String appUrl;
    @Value("${application.internal-company-id}")
    private long internalCompanyId;
    @Value("${application.internal-supplier-test-email}")
    private String internalSupplierTestEmail;

    private final CompanyService companyService;
    private final SupplierService supplierService;
    private final FavouriteSupplierService favouriteSupplierService;
    private final EmailTemplateFetcher emailTemplateService;
    private final EmailSender emailService;

    @SneakyThrows
    public void emailOrderToSupplier(SupplierOrder supplierOrder) {
        Company company = companyService.getCompanyById(SecurityUtil.getCurrentUserCompanyId());
        Supplier supplier = supplierService.getSupplierById(supplierOrder.getSupplierId());
        Optional<FavouriteSupplier> favouriteSupplier = favouriteSupplierService.getFavouriteSupplierForCurrentCompany(supplierOrder.getSupplierId());
        OrderEmailDTO orderEmailDTO = new OrderEmailDTO();
        favouriteSupplier.ifPresent(value -> orderEmailDTO.setCompanyAccountNumber(value.getAccountNumber()));

        List<OrderProductEmailDTO> items = supplierOrder.getSupplierOrderItems().stream().map(orderItem -> {
            OrderProductEmailDTO orderProduct = new OrderProductEmailDTO();
            orderProduct.setName(orderItem.getSupplierProductName());
            orderProduct.setQuantity(orderItem.getQuantity());

            return orderProduct;
        }).collect(Collectors.toList());


        String encodedOrderId = Base64.getEncoder().encodeToString(String.valueOf(supplierOrder.getId()).getBytes(StandardCharsets.UTF_8));
        orderEmailDTO.setProducts(items);
        orderEmailDTO.setOrderId(supplierOrder.getId());
        orderEmailDTO.setSupplierName(supplierOrder.getSupplierName());
        orderEmailDTO.setDeliveryDate(supplierOrder.getDeliveryDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        orderEmailDTO.setCompanyName(company.getCompanyName());
        orderEmailDTO.setContactEmail(company.getCompanyEmail());
        orderEmailDTO.setCompanyContactName(company.getContactName());
        orderEmailDTO.setCompanyContactNumber(company.getContactNumber());
        orderEmailDTO.setCompanyAddressLine1(company.getAddressLine1());
        orderEmailDTO.setCompanyAddressLine2(company.getAddressLine2());
        orderEmailDTO.setCompanyAddressLine3(company.getAddressLine3());
        orderEmailDTO.setOrderDate(supplierOrder.getCreateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        orderEmailDTO.setConfirmLink(String.format(CONFIRM_LINK, appUrl, encodedOrderId));
        orderEmailDTO.setRejectLink(String.format(REJECT_LINK, appUrl, encodedOrderId));

        String content = emailTemplateService.getOrderFromSupplierEmailBody(orderEmailDTO);

        emailService.sendEmail(
                new EmailRequestDTO(
                        orderEmail,
                        // Redirect email to our internal mailbox if the request is from our internal test user
                        SecurityUtil.getCurrentUserCompanyId() == internalCompanyId? internalSupplierTestEmail : supplier.getSupplierEmail(),
                        null,
                        String.format(ORDER_EMAIL_SUBJECT, company.getCompanyName()),
                        content));
    }

    @SneakyThrows
    public void emailOrderStatusToUser(SupplierOrder supplierOrder) {
        Company company = companyService.getCompanyById(supplierOrder.getCompanyId());
        OrderUpdateEmailDTO updateEmailDTO = new OrderUpdateEmailDTO();

        String status = "confirm";
        switch (supplierOrder.getStatus()){
            case REJECTED:
                status = "rejected";
                break;
            case UPDATED_BY_SUPPLIER:
                status = "updated";
                break;
        }
        updateEmailDTO.setOrderStatus(status);
        updateEmailDTO.setSupplierName(supplierOrder.getSupplierName());
        updateEmailDTO.setOrderId(supplierOrder.getId());
        updateEmailDTO.setCompanyName(company.getCompanyName());
        updateEmailDTO.setViewLink(String.format(VIEW_LINK, appUrl, supplierOrder.getId()));

        String content = emailTemplateService.getOrderUpdateEmailBody(updateEmailDTO);

        emailService.sendEmail(
                new EmailRequestDTO(
                        orderEmail,
                        company.getCompanyEmail(),
                        null,
                        String.format(UPDATE_ORDER_EMAIL_SUBJECT, supplierOrder.getSupplierName()),
                        content));
    }
}
