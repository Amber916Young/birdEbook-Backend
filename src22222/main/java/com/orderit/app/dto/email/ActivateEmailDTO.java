package com.orderit.app.dto.email;

import lombok.Data;

@Data
public class ActivateEmailDTO {
    private String name;
    private String email;
    private String companyName;




    public ActivateEmailDTO(String name, String email, String companyName) {
        this.name = name;
        this.email = email;
        this.companyName = companyName;
    }
}
