package com.natsumes.edu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author hetengjiao
 */
@Data
public class UserInfo {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}