package com.orderit.app.dto.email;

import lombok.Data;
@Data
public class EmailRequestDTO {
    private final String from;
    private final String subject;
    private final String content;
    private final String toRecipients;
    private final String ccRecipients;

    public EmailRequestDTO(String from, String toRecipients, String ccRecipients, String subject, String content) {
        this.from = from;
        this.toRecipients = toRecipients;
        this.ccRecipients = ccRecipients;
        this.subject = subject;
        this.content = content;
    }
    public EmailRequestDTO(String from, String toRecipients, String subject, String content) {
        this.from = from;
        this.toRecipients = toRecipients;
        this.ccRecipients = null;
        this.subject = subject;
        this.content = content;
    }
}
