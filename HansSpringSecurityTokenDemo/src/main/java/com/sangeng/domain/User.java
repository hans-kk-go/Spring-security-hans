package com.sangeng.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表(User)实体类
 *
 * @author 三更
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_table")
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    /**
    * 主键
    */
    @TableId
    private Long id;
    /**
    * 用户名
    */
    @TableField(value = "username")
    private String userName;
    /**
    * 密码
    */
    private String password;
}