package com.logey.aimin.auth.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

@Data
public class UserDTO {
    private Long id;

    private String username;
    private Integer age;
    private String email;

    private String password;
    private String city;
    private String address;
}