package com.orderit.app.service;

import com.orderit.app.dto.email.EmailRequestDTO;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.enums.UserStatus;
import com.orderit.common.exception.BadRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.repository.UserRepository;
import com.orderit.common.utils.email.EmailSender;
import com.orderit.common.utils.email.EmailTemplateFetcher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthenticationService {
    private final static String RESET_PASSWORD_LINK = "%s/auth/reset-password/%s";
    private final static String RESET_PASSWORD_SUBJECT = "Reset Your Order It Password";
    private final UserRepository userRepository;
    private final EmailSender emailService;
    private final EmailTemplateFetcher emailTemplateService;
    private final PasswordEncoder encoder;

    @Value("${application.app-url}")
    private String hostUrl;

    @Value("${application.server-email}")
    private String serverEmail;

    public void forgotPassword(String email) {
        log.debug("Forgot password triggered for {}", email);

        userRepository.findByEmail(email).ifPresent(user -> {
            String randomKey = RandomString.make(10);
            user.setResetKey(randomKey);
            userRepository.save(user);
            sendResetPasswordEmail(user.getFirstName() + " " + user.getLastName(), user.getEmail(), randomKey);
        });
    }

    public void resetPassword(String resetKey, String password) {
        log.debug("Reset password triggered for resetKey {}", resetKey);

        if (Objects.isNull(resetKey) || resetKey.isEmpty()) {
            throw new BadRequestException(ErrorReasonCode.Invalid_Reset_Key);
        }

        CompanyUser companyUser = userRepository.findByResetKey(resetKey).orElseThrow(() -> new BadRequestException(ErrorReasonCode.Invalid_Reset_Key));

        companyUser.setResetKey(null);
        companyUser.setPassword(encoder.encode(password));
        companyUser.setStatus(UserStatus.ACTIVE);
        userRepository.save(companyUser);
    }

    @SneakyThrows
    private void sendResetPasswordEmail(String name, String email, String resetPasswordKey) {
        String body = emailTemplateService.getResetPasswordEmailBody(name, String.format(RESET_PASSWORD_LINK, hostUrl, resetPasswordKey));

        EmailRequestDTO request = new EmailRequestDTO(serverEmail, email, RESET_PASSWORD_SUBJECT, body);

        emailService.sendEmail(request);
    }
}
