package com.natsumes.edu.exception;

import com.natsumes.edu.entity.Response;
import com.natsumes.edu.entity.ResponseEnum;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import java.util.Objects;

/**
 * @author hetengjiao
 */
@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response handle(RuntimeException e) {
        return Response.error(ResponseEnum.SYSTEM_ERROR, e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Response userLoginHandle() {
        return Response.error(ResponseEnum.NEED_LOGIN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response notValidExceptionHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Objects.requireNonNull(bindingResult.getFieldError());
        return Response.error(ResponseEnum.PARAM_ERROR,
                bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }
}
