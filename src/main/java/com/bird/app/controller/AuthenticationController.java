package com.bird.app.controller;

import com.bird.app.dto.LoginDTO;
import com.bird.app.dto.LoginSuccessDTO;
import com.bird.app.service.AuthenticationService;
import com.bird.common.config.exception.BadRequestException;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.security.JwtUtils;
import com.bird.common.config.security.UserDetailsImpl;
import com.bird.common.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            log.info(loginRequest.getEmail() + " successfully login");
        } catch (AuthenticationException e) {
            log.info(loginRequest.getEmail() + " failed to login");
            throw new BadRequestException(ErrorReasonCode.Invalid_Username_Password);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new LoginSuccessDTO(jwt, userDetails.getUsername(), Role.valueOf(roles.get(0)), userDetails.getEmail() ));
    }

}
