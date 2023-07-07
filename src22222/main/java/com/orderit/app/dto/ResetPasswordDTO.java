package com.orderit.app.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String password;
    private String resetKey;
}
