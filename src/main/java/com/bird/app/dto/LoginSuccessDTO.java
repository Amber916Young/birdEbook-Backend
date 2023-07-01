package com.bird.app.dto;

import com.bird.common.enums.Role;
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
