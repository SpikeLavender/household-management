package com.natsumes.edu.pojo;

import lombok.Getter;

/**
 * @author hetengjiao
 */
@Getter
public enum RoleEnum {
    /**
     * 普通用户
     */
    NORMAL(0),

    /**
     * 管理员
     */
    ADMIN(1);

    private int code;

    RoleEnum(int code) {
        this.code = code;
    }
}
