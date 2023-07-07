package com.orderit.app.dto;

import com.orderit.common.enums.Role;
import lombok.Data;
@Data
public class CompanyUserProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private Role role;
}
