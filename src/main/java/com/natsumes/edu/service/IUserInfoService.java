package com.natsumes.edu.service;

import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.pojo.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author hetengjiao
 */
@Service
public interface IUserInfoService {

    /**
     * 注册
     * @param userInfo 用户信息
     * @return 注册信息
     */
    Response<UserInfo> register(UserInfo userInfo);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     */
    Response<UserInfo> login(String username, String password);

    /**
     * 个人注册信息修改
     * @param userInfo 用户信息
     * @return 修改结果
     */
    Response<UserInfo> update(UserInfo userInfo);

    /**
     * 用户信息审核
     * @param id 用户id
     * @param status 用户状态
     * @return 审核结果
     */
    Response verify(Integer id, Integer status);

    /**
     * 用户信息删除
     * @param id 用户id
     * @return 删除结果
     */
    Response delete(Integer id);

    /**
     * 查询全部用户信息
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 用户信息
     */
    Response<PageInfo> getUserInfo(Integer pageNum, Integer pageSize);

    /**
     * 查询用户信息
     * @return 用户信息
     */
    Response<UserInfo> getUserInfoById(Integer id);

    /**
     * 根据用户状态查询用户信息
     * @param userStatus 用户状态
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 用户信息
     */
    Response<PageInfo> getUserInfoByStatus(Integer userStatus, Integer pageNum, Integer pageSize);
}
