package com.ydxl.sirs.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydxl.sirs.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {

    @Update("${sql}")
    int exeUpdate(@Param("sql") String sql);

}
