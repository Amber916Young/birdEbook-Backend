package com.orderit.app.service;

import com.orderit.app.dto.email.ActivateEmailDTO;
import com.orderit.app.dto.email.CompanyRegistrationEmailDTO;
import com.orderit.app.dto.email.EmailRequestDTO;
import com.orderit.app.dto.email.SupplierRegistrationEmailDTO;
import com.orderit.common.utils.SecurityUtil;
import com.orderit.common.utils.email.EmailSender;
import com.orderit.common.utils.email.EmailTemplateFetcher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmailSenderService {
    private final static String RESET_PASSWORD_LINK = "%s/auth/reset-password/%s";
    private final static String INVITATION_EMAIL_SUBJECT = "New Account Registration Request From %s";
    private final EmailTemplateFetcher emailTemplateService;
    private final static String ACTIVATE_EMAIL_SUBJECT = "Activate Request from %s";
    private final static String COMPANY_REGISTRATION_EMAIL_SUBJECT = "New OrderIT App Customer Registered";
    private final EmailSender emailService;
    @Value("${application.app-url}")
    private String hostUrl;
    @Value("${application.server-email}")
    private String serverEmail;
    @Value("${application.order-email}")
    private String orderEmail;
    @Value("${application.company-register-email}")
    private String companyRegisterEmail;
    @Value("${application.internal-company-id}")
    private long internalCompanyId;
    @Value("${application.internal-supplier-test-email}")
    private String internalSupplierTestEmail;

    @SneakyThrows
    public void sendRegistrationRequestToSupplier(SupplierRegistrationEmailDTO registrationEmail) {
        String content = emailTemplateService.getSupplierRegistrationEmailBody(registrationEmail);

        emailService.sendEmail(new EmailRequestDTO(
                orderEmail,
                // Redirect email to our internal mailbox if the request is from our internal test user
                SecurityUtil.getCurrentUserCompanyId() == internalCompanyId? internalSupplierTestEmail :registrationEmail.getSupplierEmail(),
                registrationEmail.getCompanyEmail(),
                String.format(INVITATION_EMAIL_SUBJECT, registrationEmail.getCompanyName()),
                content));
    }

    @SneakyThrows
    public void sendActivateEmail(ActivateEmailDTO activateEmail , String randomKey) {

        String content = emailTemplateService.getActivateEmailBody(activateEmail,String.format(RESET_PASSWORD_LINK, hostUrl, randomKey));
        emailService.sendEmail(new EmailRequestDTO(
                serverEmail,
                activateEmail.getEmail(),
                String.format(ACTIVATE_EMAIL_SUBJECT, activateEmail.getCompanyName()),
                content));

    }

    public void sendCompanyVerificationEmail(String company_email) {
        emailService.sendCompanyVerificationEmail(company_email);
    }


    @SneakyThrows
    public void sendCompanyRegisterEmail(CompanyRegistrationEmailDTO registrationEmailDTO) {
        String content = emailTemplateService.getCompanyRegistrationEmailBody(registrationEmailDTO);

        emailService.sendEmail(new EmailRequestDTO(
                serverEmail,
                companyRegisterEmail,
                COMPANY_REGISTRATION_EMAIL_SUBJECT,
                content));
    }
}
