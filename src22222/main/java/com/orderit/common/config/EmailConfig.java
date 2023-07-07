package com.orderit.common.config;

import com.orderit.common.config.aws.AmazonClientProvider;
import com.orderit.common.utils.email.EmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Bean
    public EmailSender emailSender() {
        return new EmailSender(new AmazonClientProvider().getAmazonSimpleEmailService());
    }
}
