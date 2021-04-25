package com.natsumes.edu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.entity.ResponseEnum;
import com.natsumes.edu.mapper.HouseholdInfoMapper;
import com.natsumes.edu.mapper.UserInfoMapper;
import com.natsumes.edu.pojo.HouseholdInfo;
import com.natsumes.edu.pojo.UserInfo;
import com.natsumes.edu.service.IHouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hetengjiao
 */
@Service
public class HouseholdServiceImpl implements IHouseholdService {

    @Autowired
    HouseholdInfoMapper householdInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 创建户籍信息
     *
     * @param householdInfo 户籍信息
     *
     * @return 创建好的户籍信息
     */
    @Override
    public Response<HouseholdInfo> createHouseholdInfo(HouseholdInfo householdInfo) {
        // 查询用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(householdInfo.getUserId());
        if (userInfo == null) {
            // 返回用户名或密码错误
            return Response.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        // 查询户籍信息
        if (householdInfoMapper.insertOrUpdateSelective(householdInfo) <= 0) {
            // 插入失败，返回系统异常
            return Response.error(ResponseEnum.SYSTEM_ERROR);
        }
        // 插入成功，返回户籍信息
        return Response.success(householdInfo);
    }

    /**
     * 更新户籍信息
     *
     * @param householdInfo 户籍信息
     *
     * @return 更新后的信息
     */
    @Override
    public Response<HouseholdInfo> updateHouseholdInfo(HouseholdInfo householdInfo) {
        // 更新户籍信息
        householdInfoMapper.updateByPrimaryKeySelective(householdInfo);
        // 更新成功，返回更新后的户籍信息
        return Response.success(householdInfo);
    }

    /**
     * 删除户籍信息
     *
     * @param id     户籍id
     * @param userId 用户id
     *
     * @return 删除结果
     */
    @Override
    public Response deleteHouseholdInfo(Integer id, Integer userId) {
        if (id != null) {
            // 根据id删除户籍信息
            householdInfoMapper.deleteByPrimaryKey(id);
            // 返回删除成功
            return Response.success();
        }
        // 根据用户id删除户籍信息
        householdInfoMapper.deleteByUserId(userId);
        // 返回删除成功
        return Response.success();
    }

    /**
     * 查询户籍信息
     *
     * @param pageNum  页码
     * @param pageSize 每页数目
     *
     * @return 全部用户信息
     */
    @Override
    public Response<PageInfo> getHouseholdInfo(Integer pageNum, Integer pageSize) {
        // 设置分页信息
        PageHelper.startPage(pageNum, pageSize);
        // 查询全部户籍信息
        List<HouseholdInfo> householdInfos = householdInfoMapper.selectAll();
        // 组装分页信息
        PageInfo<HouseholdInfo> pageInfo = new PageInfo<>(householdInfos);
        // 返回分页后的用户信息
        return Response.success(pageInfo);
    }

    /**
     * 使用用户id查询户籍信息
     *
     * @param userId 用户id
     *
     * @return 户籍信息
     */
    @Override
    public Response<HouseholdInfo> getHouseholdInfoByUserId(Integer userId) {
        // 根据用户id查询户籍信息
        HouseholdInfo householdInfo = householdInfoMapper.selectByUserId(userId);
        // 返回户籍信息
        return Response.success(householdInfo);
    }
}
