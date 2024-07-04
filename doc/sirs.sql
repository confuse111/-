
CREATE DATABASE IF NOT EXISTS sirs;

USE sirs;

DROP TABLE IF EXISTS t_student_learn;
DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user
(
    user_id   INT         NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    username  VARCHAR(32) NOT NULL COMMENT '登录名',
    password  VARCHAR(65) NOT NULL COMMENT '密码',
    nickname  VARCHAR(16) NOT NULL DEFAULT '' COMMENT '昵称',
    user_type TINYINT     NOT NULL COMMENT '用户类型',
    PRIMARY KEY (user_id)
);

CREATE TABLE t_student_learn
(
    id                    INT         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    student_user_id       INT         NOT NULL COMMENT '学生用户ID',
    course_name           VARCHAR(32) NOT NULL COMMENT '课程',
    teacher_user_id       INT         NOT NULL COMMENT '登记教师用户ID',
    classroom_performance INT         NOT NULL DEFAULT 0 COMMENT '课堂表现',
    experiment            INT         NOT NULL DEFAULT 0 COMMENT '实验',
    homework              INT         NOT NULL DEFAULT 0 COMMENT '课后作业',
    big_job               INT         NOT NULL DEFAULT 0 COMMENT '大作业',
    score                 INT         NOT NULL COMMENT '分数',
    PRIMARY KEY (id),
    FOREIGN KEY (student_user_id) REFERENCES t_user (user_id),
    FOREIGN KEY (teacher_user_id) REFERENCES t_user (user_id)
);

INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (1, 'teacher', '123456', '泰达米尔', 1);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (2, 'yinli', '123456', '殷离', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (3, 'zhuyuanzhang', '123456', '朱元璋', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (4, 'chenyouliang', '123456', '陈友谅', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (5, 'zhouzhiruo', '123456', '周芷若', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (6, 'zhujiuzhen', '123456', '朱九真', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (7, 'zhoudian', '123456', '周颠', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (8, 'songqingshu', '123456', '宋青书', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (9, 'zhaomin', '123456', '赵敏', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (10, 'xiaozhao', '123456', '小昭', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (11, 'changyuchun', '123456', '常遇春', 0);
INSERT INTO `sirs`.`t_user` (`user_id`, `username`, `password`, `nickname`, `user_type`) VALUES (12, 'student', '123456', '张无忌', 0);

INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (1, 2, '高等数学', 1, 1, 2, 1, 1, 70);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (2, 3, '高等数学', 1, 1, 2, 2, 1, 88);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (3, 4, '高等数学', 1, 2, 1, 2, 2, 90);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (4, 5, '高等数学', 1, 1, 2, 1, 2, 89);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (5, 6, '高等数学', 1, 1, 1, 1, 1, 70);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (6, 7, '高等数学', 1, 2, 1, 1, 1, 88);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (7, 8, '高等数学', 1, 1, 1, 1, 1, 66);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (8, 9, '高等数学', 1, 2, 1, 2, 2, 98);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (9, 10, '高等数学', 1, 2, 1, 1, 1, 86);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (10, 11, '高等数学', 1, 2, 2, 2, 1, 90);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (11, 12, '高等数学', 1, 2, 1, 2, 2, 97);
INSERT INTO `sirs`.`t_student_learn` (`id`, `student_user_id`, `course_name`, `teacher_user_id`, `classroom_performance`, `experiment`, `homework`, `big_job`, `score`) VALUES (12, 12, 'java编程语言', 1, 2, 2, 2, 2, 100);
