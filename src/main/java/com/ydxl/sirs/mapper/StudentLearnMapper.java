package com.ydxl.sirs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydxl.sirs.entity.StudentLearn;
import org.apache.ibatis.annotations.Select;

public interface StudentLearnMapper extends BaseMapper<StudentLearn> {


    @Select({
            "SELECT `rank` FROM (",
            " SELECT t1.id,t1.score,COUNT( DISTINCT t2.score ) AS 'rank' FROM t_student_learn t1,t_student_learn t2 WHERE t1.score <= t2.score AND t1.course_name = #{courseName}",
            " GROUP BY t1.id ORDER BY t1.score DESC) tt WHERE tt.id = #{id}"
    })
    int selectRank(int id, String courseName);

}
