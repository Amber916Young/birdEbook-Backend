package com.orderit.app.dto;

import lombok.Data;

@Data
public class CompanyVerifyUserDTO extends CompanyUserDTO {
    private String companyEmail;
    private Long companyId;
    private String companyName;
}
