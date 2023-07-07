package com.orderit.app.controller;

import com.orderit.app.dto.ForgotPasswordDTO;
import com.orderit.app.dto.LoginDTO;
import com.orderit.app.dto.LoginSuccessDTO;
import com.orderit.app.dto.ResetPasswordDTO;
import com.orderit.app.service.AuthenticationService;
import com.orderit.common.config.security.JwtUtils;
import com.orderit.common.config.security.UserDetailsImpl;
import com.orderit.common.enums.Role;
import com.orderit.common.exception.BadRequestException;
import com.orderit.common.exception.ErrorReasonCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessDTO> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            log.info(loginRequest.getEmail() + " successfully login");
        } catch (AuthenticationException e) {
            log.info(loginRequest.getEmail() + " failed to login");
            throw new BadRequestException(ErrorReasonCode.Invalid_Username_Password);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return ResponseEntity.ok(new LoginSuccessDTO(jwt, userDetails.getFirstName(), userDetails.getLastName(), Role.valueOf(roles.get(0)), userDetails.getUsername(), userDetails.getContactNumber()));
    }

    @PostMapping("/forgotPassword")
    @ResponseStatus(value = HttpStatus.OK)
    public void forgetPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordRequest) {
        authService.forgotPassword(forgotPasswordRequest.getEmail());
    }

    @PostMapping("/resetPassword")
    @ResponseStatus(value = HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordRequest) {
        authService.resetPassword(resetPasswordRequest.getResetKey(), resetPasswordRequest.getPassword());
    }
}
