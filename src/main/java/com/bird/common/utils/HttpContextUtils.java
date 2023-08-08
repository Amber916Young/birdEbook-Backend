package com.bird.common.utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
/**
 * @ClassName:HttpContextUtils
 * @Auther: yyj
 * @Description:
 * @Date: 22/07/2023 21:23
 * @Version: v1.0
 */

public class HttpContextUtils {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉前缀 "Bearer "
        }
        return token;
    }
}


