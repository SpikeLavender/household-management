package com.natsumes.edu.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hetengjiao
 */
@Data
public class UserLoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
