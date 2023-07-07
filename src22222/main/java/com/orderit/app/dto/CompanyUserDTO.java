package com.orderit.app.dto;

import com.orderit.common.enums.Role;
import com.orderit.common.enums.UserStatus;
import lombok.Data;

@Data
public class CompanyUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private Role role;
    private UserStatus status;
}
