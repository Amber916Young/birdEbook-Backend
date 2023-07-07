package com.orderit.app.dto.email;

import com.orderit.common.entity.Company;
import com.orderit.common.entity.CompanyUser;
import lombok.Data;

@Data
public class CompanyRegistrationEmailDTO {
    private CompanyUser companyUser;
    private Company company;
}
