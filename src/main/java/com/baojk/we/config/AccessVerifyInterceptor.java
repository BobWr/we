package com.baojk.we.config;

import com.baojk.we.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/18
 */
@Component
public class AccessVerifyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisManager redisManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
        String method = request.getMethod();
        StringBuffer requestURL = request.getRequestURL();
        if (requestURL.indexOf("/api/v1/user") > 0 && method.equalsIgnoreCase(HttpMethod.POST.name())) {
            return true;
        }
        //        String token = request.getHeader("Authorization");
        String token = request.getParameter("Authorization");
        if (token != null && !token.isEmpty() && redisManager.isTokenExist(token)) {
            return true;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String data = "Missing or invalid Authorization header.";
        response.getWriter().write("{\"code\":403,\"message\":\"" + data + "\"}");
        return false;
    }
}
