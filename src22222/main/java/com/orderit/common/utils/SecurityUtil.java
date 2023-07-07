package com.orderit.common.utils;

import com.orderit.common.config.security.UserDetailsImpl;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.enums.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtil {
    public static String getCurrentUserLogin() {
        UserDetailsImpl springSecurityUser = getCurrentUserDetails();
        return springSecurityUser == null? "public" :springSecurityUser.getUsername();
    }

    public static String getCurrentUserName() {
        UserDetailsImpl springSecurityUser = getCurrentUserDetails();

        return springSecurityUser.getFirstName()+" "+springSecurityUser.getLastName();
    }

    public static Long getCurrentUserCompanyId() {
        UserDetailsImpl springSecurityUser = getCurrentUserDetails();

        return springSecurityUser.getCompanyId();
    }

    public static Long getCurrentUserId() {
        UserDetailsImpl springSecurityUser = getCurrentUserDetails();

        return springSecurityUser.getId();
    }

    private static UserDetailsImpl getCurrentUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
                return  (UserDetailsImpl) authentication.getPrincipal();
            }
        }
        return null;
    }
}
