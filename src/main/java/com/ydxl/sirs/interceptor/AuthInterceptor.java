package com.ydxl.sirs.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydxl.sirs.controller.BaseController;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        Object user = request.getSession().getAttribute(BaseController.SESSION_USER);
        if (Objects.isNull(user)) {
            if (isAjax(handler)) {
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                Map<String, Object> data = new HashMap<>();
                data.put("status", 2);
                data.put("msg", "未登录或登录已过期，请重新登录");
                objectMapper.writeValue(response.getWriter(), data);
            } else {
                response.sendRedirect("/");
            }
            return false;
        }
        return true;
    }

    private boolean isAjax(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
            if (responseBody != null) {
                return true;
            }
            Class<?> clazz = handlerMethod.getBeanType();
            RestController restController = clazz.getAnnotation(RestController.class);
            return restController != null;
        }
        return false;
    }
}
