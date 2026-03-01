package com.logey.aimin.auth.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_address")
public class Address {
    private Long id;
    private Long userId;
    private String city;
    private String address;
}
