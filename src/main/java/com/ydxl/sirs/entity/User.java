package com.ydxl.sirs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户表
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_user", autoResultMap = true)
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String nickname;

    private String password;

    private Integer userType;


}
