package com.ydxl.sirs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydxl.sirs.entity.StudentLearn;
import com.ydxl.sirs.entity.User;
import com.ydxl.sirs.mapper.StudentLearnMapper;
import com.ydxl.sirs.mapper.UserMapper;
import com.ydxl.sirs.model.ScoreRank;
import com.ydxl.sirs.service.StudentLearnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student/learn")
public class StudentLearnController extends BaseController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentLearnMapper studentLearnMapper;

    @Resource
    private StudentLearnService studentLearnService;

    @GetMapping("/page")
    String page(@ModelAttribute StudentLearn studentLearn, Model model) {
        List<User> list = userMapper.selectList(new QueryWrapper<>(new User().setUserType(0)));
        Map<Integer, User> userMap = list.stream().collect(Collectors.toMap(User::getUserId, v -> v));
        List<StudentLearn> studentLearns = studentLearnMapper.selectList(new QueryWrapper<>(studentLearn.setTeacherUserId(getSessionUser().getUserId())));
        model.addAttribute("userMap", userMap);
        model.addAttribute("studentLearns", studentLearns);
        model.addAttribute("studentLearn", studentLearn);
        return "learn/page";
    }

    @GetMapping("/mylist")
    String mylist(String courseName, Model model) {
        List<ScoreRank> studentLearns = studentLearnService.list(getSessionUser().getUserId(), courseName);
        List<User> list = userMapper.selectList(new QueryWrapper<>(new User().setUserType(1)));
        Map<Integer, User> userMap = list.stream().collect(Collectors.toMap(User::getUserId, v -> v));
        model.addAttribute("userMap", userMap);
        model.addAttribute("studentLearns", studentLearns);
        model.addAttribute("courseName", courseName);
        return "learn/list";
    }

    @GetMapping("/toAddOrEdit")
    String toAddOrEdit(Model model) {
        StudentLearn studentLearn = new StudentLearn();
        model.addAttribute("bean", studentLearn);
        List<User> list = userMapper.selectList(new QueryWrapper<>(new User().setUserType(0)));
        model.addAttribute("userList", list);
        model.addAttribute("isAdd", true);
        return "learn/edit";
    }

    @PostMapping
    @ResponseBody
    Map<String, Object> save(@RequestBody StudentLearn studentLearn) {
        studentLearn.setTeacherUserId(getSessionUser().getUserId());
        boolean success = studentLearnService.saveOrUpdate(studentLearn);
        return success(success);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    Map<String, Object> delete(@PathVariable Integer id) {
        return success(studentLearnMapper.deleteById(id) > 0);
    }

}
