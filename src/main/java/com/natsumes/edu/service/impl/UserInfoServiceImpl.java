package com.natsumes.edu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.entity.ResponseEnum;
import com.natsumes.edu.mapper.HouseholdInfoMapper;
import com.natsumes.edu.mapper.UserInfoMapper;
import com.natsumes.edu.pojo.HouseholdInfo;
import com.natsumes.edu.pojo.UserInfo;
import com.natsumes.edu.pojo.UserStatusEnum;
import com.natsumes.edu.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author hetengjiao
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    HouseholdInfoMapper householdInfoMapper;

    /**
     * 注册
     *
     * @param userInfo 用户信息
     *
     * @return 注册信息
     */
    @Override
    @Transactional(rollbackFor = {})
    public Response<UserInfo> register(UserInfo userInfo) {
        // 用户名是否存在
        if (userInfoMapper.countByUsername(userInfo.getUsername()) > 0) {
            // 返回用户名已注册
            return Response.error(ResponseEnum.USERNAME_EXIST);
        }
        // 用户密码进行加密
        String password = DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes(StandardCharsets.UTF_8));
        userInfo.setPassword(password);
        // 将用户信息数据插入数据库
        if (userInfoMapper.insertSelective(userInfo) == 0) {
            //插入异常，返回错误
            return Response.error(ResponseEnum.SYSTEM_ERROR, "服务内部异常");
        }
        // 插入户籍信息
        HouseholdInfo householdInfo = new HouseholdInfo();
        householdInfo.setUserId(userInfo.getId());
        householdInfo.setName(userInfo.getUsername());
        // 插入一条用户名对应的户籍信息
        if (householdInfoMapper.insertOrUpdateSelective(householdInfo) <= 0) {
            // 插入失败，返回异常
            return Response.error(ResponseEnum.SYSTEM_ERROR);
        }
        // 返回成功
        return Response.success();
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     *
     * @return 登录信息
     */
    @Override
    public Response<UserInfo> login(String username, String password) {
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo == null) {
            //用户不存在(返回: 用户名或密码错误)
            return Response.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        if (!userInfo.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            //密码错误(返回: 用户名或密码错误)
            return Response.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        // 把返回前端的密码设置为空，防止暴露
        userInfo.setPassword("");
        // 返回用户信息
        return Response.success(userInfo);
    }

    /**
     * 个人注册信息修改
     *
     * @param userInfo 用户信息
     *
     * @return 修改结果
     */
    @Override
    public Response<UserInfo> update(UserInfo userInfo) {
        // 更新用户信息
        userInfoMapper.updateByUsername(userInfo);
        return Response.success(userInfo);
    }

    /**
     * 用户信息审核
     *
     * @param id 用户id
     * @param status 用户状态
     *
     * @return 审核结果
     */
    @Override
    public Response verify(Integer id, Integer status) {
        try {
            UserStatusEnum.getStatus(status);
        } catch (IllegalArgumentException e) {
            return Response.error(ResponseEnum.USER_STATUS_ERROR);
        }
        // 新建一个UserInfo
        UserInfo userInfo = new UserInfo();
        // 设置 id
        userInfo.setId(id);
        // 设置 status
        userInfo.setStatus(status);
        // 更新审核状态
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return Response.success();
    }

    /**
     * 用户信息删除
     *
     * @param id 用户ID
     *
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = {})
    public Response delete(Integer id) {
        userInfoMapper.deleteByPrimaryKey(id);
        householdInfoMapper.deleteByUserId(id);
        return Response.success();
    }

    /**
     * 查询全部用户信息
     *
     * @param pageNum  页码
     * @param pageSize 每页数目
     *
     * @return 用户信息
     */
    @Override
    public Response<PageInfo> getUserInfo(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        userInfos.forEach(userInfo -> userInfo.setPassword(""));
        PageInfo pageInfo = new PageInfo<>(userInfos);
        return Response.success(pageInfo);
    }

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    @Override
    public Response<UserInfo> getUserInfoById(Integer id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        userInfo.setPassword("");
        return Response.success(userInfo);
    }

    /**
     * 根据用户状态查询用户信息
     *
     * @param userStatus 用户状态
     * @param pageNum    页码
     * @param pageSize   每页数目
     *
     * @return 用户信息
     */
    @Override
    public Response<PageInfo> getUserInfoByStatus(Integer userStatus, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userInfoMapper.selectByStatus(userStatus);
        userInfos.forEach(userInfo -> userInfo.setPassword(""));
        PageInfo pageInfo = new PageInfo<>(userInfos);
        return Response.success(pageInfo);
    }
}
