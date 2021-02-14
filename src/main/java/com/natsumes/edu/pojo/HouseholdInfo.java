package com.natsumes.edu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author hetengjiao
 */
@Data
public class HouseholdInfo {
    private Integer id;

    private Integer userId;

    private String name;

    private String identityCardId;

    private Date birthday;

    private Integer gender;

    private Date createTime;

    private Date updateTime;

    private String address;
}