package com.natsumes.edu.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author  hetengjiao
 * @date    2020-10-30
 */
@Data
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -6364311853459085962L;
    private Integer status;

    private String msg;

    private T data;

    private Response(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private Response(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> Response<T> successByMsg(String msg) {
        return new Response<>(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Response<T> success() {
        return new Response<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(ResponseEnum.SUCCESS.getCode(), data);
    }

    public static <T> Response<T> error(ResponseEnum responseEnum) {
        return new Response<>(responseEnum.getCode(), responseEnum.getDesc());
    }

    public static <T> Response<T> error(ResponseEnum responseEnum, BindingResult bindingResult) {
        return new Response<>(responseEnum.getCode(),
                Objects.requireNonNull(bindingResult.getFieldError()).getField() + " " +
                        bindingResult.getFieldError().getDefaultMessage());
    }

    public static <T> Response<T> error(ResponseEnum responseEnum, String msg) {
        return new Response<>(responseEnum.getCode(), msg);
    }
}