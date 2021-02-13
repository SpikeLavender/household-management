package com.natsumes.edu.pojo;

import lombok.Getter;

/**
 * @author hetengjiao
 */
@Getter
public enum  UserStatusEnum {
    /**
     * 未审核
     */
    UNVERIFIED(0, "未审核"),

    /**
     * 审核中
     */
    VERIFYING(1, "审核中"),

    /**
     * 审核通过
     */
    VERIFIED(2, "审核通过"),

    /**
     * 审核未通过
     */
    VERIFY_FAIL(3, "审核未通过"),

    ;

    private int code;

    private String desc;

    UserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserStatusEnum getStatus(Integer code) {
        for (UserStatusEnum value : UserStatusEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("不支持的用户状态: " + code);
    }
}
