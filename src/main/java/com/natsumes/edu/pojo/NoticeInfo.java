package com.natsumes.edu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author hetengjiao
 */
@Data
public class NoticeInfo {
    private Integer id;

    private String title;

    private Integer status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    private String detail;
}