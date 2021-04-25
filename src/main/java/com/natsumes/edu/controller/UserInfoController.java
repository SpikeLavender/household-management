package com.natsumes.edu.controller;

import com.github.pagehelper.PageInfo;
import com.natsumes.edu.controller.form.UserInfoRegisterForm;
import com.natsumes.edu.controller.form.UserInfoUpdateForm;
import com.natsumes.edu.controller.form.UserLoginForm;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.pojo.UserInfo;
import com.natsumes.edu.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.natsumes.edu.config.HouseholdConst.CURRENT_USER;

/**
 * @author hetengjiao
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoServiceImpl;

    /**
     * 注册
     * @param registerForm 用户信息
     * @return 注册信息
     */
    @PostMapping("/register")
    public Response<UserInfo> register(@Valid @RequestBody UserInfoRegisterForm registerForm, HttpSession session) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(registerForm, userInfo);
        // 调用注册业务接口
        Response<UserInfo> response = userInfoServiceImpl.register(userInfo);
        // 将用户信息存入session
        session.setAttribute(CURRENT_USER, response.getData());
        return response;
    }

    /**
     * 用户登录
     * @param userLoginForm 用户名
     * @param session session
     * @return 登录信息
     */
    @PostMapping("/login")
    public Response<UserInfo> login(@Valid @RequestBody UserLoginForm userLoginForm, HttpSession session) {
        Response<UserInfo> response = userInfoServiceImpl.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        session.setAttribute(CURRENT_USER, response.getData());
        log.info("login sessionId = {}", session.getId());
        return response;
    }

    @PostMapping("/{userId}/logout")
    public Response<UserInfo> logout(@PathVariable Integer userId, HttpSession session) {
        log.info("logout sessionId = {}", userId);

        session.removeAttribute(CURRENT_USER);
        return Response.success();
    }

    /**
     * 个人注册信息修改
     * @param updateForm 用户信息
     * @return 修改结果
     */
    @PostMapping("/{userId}/update")
    public Response<UserInfo> update(@PathVariable Integer userId, @Valid @RequestBody UserInfoUpdateForm updateForm) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(updateForm, userInfo);
        return userInfoServiceImpl.update(userInfo);
    }

    /**
     * 用户信息审核
     * @param userId 用户id
     * @param status 用户状态: 0-未审核, 1-审核中, 2-审核通过, 3-审核不通过
     * @return 审核结果
     */
    @RequestMapping("/{userId}/verify/{status}")
    public Response verify(@PathVariable Integer userId, @PathVariable Integer status) {
        // 调用用户信息审核业务接口
        return userInfoServiceImpl.verify(userId, status);
    }

    /**
     * 用户信息删除
     * @param userId 用户id
     * @return 删除结果
     */
    @DeleteMapping("/{userId}/delete")
    public Response delete(@PathVariable Integer userId) {
        return userInfoServiceImpl.delete(userId);
    }

    /**
     * 查询全部用户信息
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 用户信息
     */
    @GetMapping("/userInfos")
    public Response<PageInfo> getUserInfo(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return userInfoServiceImpl.getUserInfo(pageNum, pageSize);
    }

    /**
     * 查询用户信息
     * @return 用户信息
     */
    @GetMapping("/{userId}/userInfo")
    public Response<UserInfo> getUserInfoById(@PathVariable Integer userId) {
        return userInfoServiceImpl.getUserInfoById(userId);
    }

    /**
     * 根据用户状态查询用户信息
     * @param userStatus 用户状态
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 用户信息
     */
    @GetMapping("/status/{userStatus}")
    @CrossOrigin(origins = "*")
    public Response<PageInfo> getUserInfoByStatus(@PathVariable Integer userStatus,
                                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return userInfoServiceImpl.getUserInfoByStatus(userStatus, pageNum, pageSize);
    }

}
