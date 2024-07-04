package com.ydxl.sirs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydxl.sirs.entity.StudentLearn;
import com.ydxl.sirs.mapper.StudentLearnMapper;
import com.ydxl.sirs.model.ScoreRank;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class StudentLearnService {

    @Resource
    private StudentLearnMapper studentLearnMapper;


    public boolean saveOrUpdate(StudentLearn studentLearn) {
        int studentUserId = studentLearn.getStudentUserId();
        String courseName = studentLearn.getCourseName();
        if (StringUtils.isEmpty(courseName)) {
            throw new IllegalArgumentException("课程名称不得为空");
        }
        courseName = StringUtils.trimWhitespace(courseName);
        studentLearn.setCourseName(courseName);
        StudentLearn entity = studentLearnMapper.selectOne(new QueryWrapper<>(new StudentLearn().setStudentUserId(studentUserId).setCourseName(courseName)));
        if (entity == null) {
            return studentLearnMapper.insert(studentLearn) > 0;
        }
        entity.setClassroomPerformance(studentLearn.getClassroomPerformance());
        entity.setHomework(studentLearn.getHomework());
        entity.setExperiment(studentLearn.getExperiment());
        entity.setBigJob(studentLearn.getBigJob());
        entity.setScore(studentLearn.getScore());
        return studentLearnMapper.updateById(studentLearn) > 0;
    }

    public List<ScoreRank> list(int studentUserId, String courseName) {
        List<StudentLearn> list = studentLearnMapper.selectList(
                new LambdaQueryWrapper<>(new StudentLearn().setStudentUserId(studentUserId))
                        .like(StringUtils.hasText(courseName), StudentLearn::getCourseName, courseName)
        );
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<ScoreRank> scoreRanks = new ArrayList<>(list.size());
        for (StudentLearn studentLearn : list) {
            ScoreRank scoreRank = new ScoreRank();
            BeanUtils.copyProperties(studentLearn, scoreRank);
            scoreRank.setRank(studentLearnMapper.selectRank(studentLearn.getId(), studentLearn.getCourseName()));
            scoreRanks.add(scoreRank);
        }
        scoreRanks.sort(Comparator.comparing(ScoreRank::getRank));
        return scoreRanks;
    }

}
