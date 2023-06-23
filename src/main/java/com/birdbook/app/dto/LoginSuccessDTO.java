package com.birdbook.app.dto;

import com.birdbook.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginSuccessDTO {
    private String jwt;
    private String userName;
    private Role role;
    private String email;
}
