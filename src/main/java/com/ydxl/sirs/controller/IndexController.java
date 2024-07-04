package com.ydxl.sirs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydxl.sirs.entity.User;
import com.ydxl.sirs.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping
public class IndexController extends BaseController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/")
    String toLogin() {
        return "login";
    }


    @GetMapping("/index")
    String toIndex() {
        return "index";
    }

    @GetMapping("/welcome")
    String toWelcome() {
        return "welcome";
    }


    @PostMapping("/login")
    @ResponseBody
    Map<String, Object> login(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            return fail("用户名不得为空");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return fail("密码不得为空");
        }
        User entity = userMapper.selectOne(new QueryWrapper<>(new User().setUsername(user.getUsername()).setPassword(user.getPassword())));
        if (Objects.isNull(entity)) {
            return fail("用户名或密码错误");
        }
        storeSessionUser(entity);
        return success(entity.getUserType());
    }

    @GetMapping("/logout")
    String logout() {
        removeSessionUser();
        return "redirect:/";
    }

}
