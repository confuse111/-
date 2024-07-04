package com.ydxl.sirs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 学生学习情况登记表实体
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_student_learn", autoResultMap = true)
public class StudentLearn {

    /**
     * Id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生用户ID
     */
    private Integer studentUserId;

    /**
     * 登记老师
     */
    private Integer teacherUserId;

    /**
     * 课程
     */
    private String courseName;

    /**
     * 课堂表现评价
     */
    private Integer classroomPerformance;

    /**
     * 实验评价
     */
    private Integer experiment;

    /**
     * 课后作业评价
     */
    private Integer homework;
    /**
     * 大作业
     */
    private Integer bigJob;

    /**
     * 整体评分
     */
    private Integer score;

}
