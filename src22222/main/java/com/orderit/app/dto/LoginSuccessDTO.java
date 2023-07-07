package com.orderit.app.dto;

import com.orderit.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginSuccessDTO {
    private String jwt;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private final String contactNumber;
}
