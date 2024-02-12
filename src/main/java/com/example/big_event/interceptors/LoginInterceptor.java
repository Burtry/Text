package com.example.big_event.interceptors;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.example.big_event.utils.JwtUtil;
import com.example.big_event.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);

            //获取相同的redis
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            Object username = claims.get("username");
            String userToken = operations.get(username);
            if (userToken == null) {
                throw new RuntimeException();
            }
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}
