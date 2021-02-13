package com.natsumes.edu.controller.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hetengjiao
 */
@Data
@Builder
public class UserInfoRegisterForm {
    private Integer id;

    @NotBlank
    private String username;

    @NotNull
    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;
}
