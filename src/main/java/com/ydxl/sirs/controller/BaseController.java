package com.ydxl.sirs.controller;


import com.ydxl.sirs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String SESSION_USER = "sessionUser";
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    protected Map<String, Object> success(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        map.put("msg", "ok");
        map.put("data", data);
        return map;
    }

    protected Map<String, Object> fail(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        map.put("msg", StringUtils.isEmpty(msg) ? "failed" : msg);
        return map;
    }

    protected void storeSessionUser(User user) {
        request.getSession().setAttribute(SESSION_USER, user);
    }

    protected void removeSessionUser() {
        request.getSession().removeAttribute(SESSION_USER);
    }

    protected User getSessionUser() {
        return (User) request.getSession().getAttribute(SESSION_USER);
    }

}
