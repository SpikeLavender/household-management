package com.natsumes.edu.controller.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hetengjiao
 */
@Data
public class UserInfoUpdateForm {
    private Integer id;

    @NotNull
    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;
}

