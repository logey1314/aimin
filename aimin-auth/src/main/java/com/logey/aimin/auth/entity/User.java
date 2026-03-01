package com.logey.aimin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yulichang.annotation.EntityMapping;
import com.logey.aimin.ds.annotation.DictTranslate;
import lombok.Data;

@Data
@TableName("t_user_account")
public class User {
    private int id;
    @TableField("usermane")
    private String username;
    @TableField("password")
    private String password;
    @TableField("pid")
    private int pid;

    @TableField(exist = false)
    @EntityMapping(thisField = "pid", joinField = "id")
    private User parent;

    @TableField("sex")
    @DictTranslate(valueTo="sexName")
    private int sex;

    @TableField(exist = false)
    private  String sexName;

}
