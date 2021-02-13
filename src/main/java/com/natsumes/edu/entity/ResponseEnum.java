package com.natsumes.edu.entity;


import lombok.Getter;

/**
 * 错误码定义:
 * 1xxxxx: 系统级别的错误
 * 40xxxx: API参数校验失败
 * 50xxxx: 后台业务校验失败
 * xx1xxx: 用户模块
 * xx2xxx: 商品模块
 * xx3xxx: 购物车模块
 * xx4xxx: 收货地址模块
 * xx5xxx: 订单模块
 *
 * @author hetengjiao
 */
@Getter
public enum ResponseEnum {
    /**
     * 成功状态码
     */
    SUCCESS(0, "成功"),
    /**
     * 参数为空
     */
    PARAM_ERROR(400000, "参数错误"),

    USERNAME_EXIST(501001, "用户名已注册"),

    EMAIL_EXIST(501002, "邮箱已注册"),

    USERNAME_OR_PASSWORD_ERROR(501003, "用户名或密码错误"),

    NEED_LOGIN(501004, "用户未登录, 请先登录"),

    USER_STATUS_ERROR(501005, "审核状态有误"),

    WECHART_LOGIN_ERROR(501006, "微信查询ID失败"),

    PARENT_HAS_EXIST(501007, "上级ID已存在"),

    PARENT_IS_NOT_VALID(501008, "此ID为你的下级"),

    PRODUCT_OFF_SALE_OR_DELETE(502001, "商品已下架或删除"),

    PRODUCT_NOT_EXIST(502002, "商品不存在"),

    PRODUCT_STOCK_ERROR(502003, "库存不正确"),

    CART_PRODUCT_NOT_EXIST(503001, "购物车无此商品"),

    DELETE_SHIPPING_FAIL(504001, "删除收货地址失败"),

    SHIPPING_NOT_EXIST(504002, "收货地址不存在"),

    CART_SELECTED_IS_EMPTY(505001, "请选择商品后下单"),

    ORDER_NOT_EXIST(505002, "订单不存在"),

    ORDER_STATUS_ERROR(505003, "订单状态有误"),

    PROFIT_NUMBER_ERROR(506001, "可领取佣金数目不正确"),

    PROFIT_CANT_RECEIVE(506002, "佣金暂时不可领取"),

    SYSTEM_BLOCK_FLOWING(510001, "系统负载过高，熔断中，请稍后重试"),

    SYSTEM_BLOCK_DEGRADING(510002, "当前接口异常，降级中，请稍后重试"),

    SYSTEM_ERROR(100000, "服务端错误"),

    ;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String desc;

    /**
     * 异常枚举信息
     *
     * @param code 状态码
     * @param desc 信息
     */
    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

