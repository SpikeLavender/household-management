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
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(householdInfo.getUserId());
        if (userInfo == null) {
            return Response.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        if (householdInfoMapper.insertOrUpdateSelective(householdInfo) <= 0) {
            return Response.error(ResponseEnum.SYSTEM_ERROR);
        }
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
        householdInfoMapper.updateByPrimaryKeySelective(householdInfo);
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
            householdInfoMapper.deleteByPrimaryKey(id);
            return Response.success();
        }
        householdInfoMapper.deleteByUserId(userId);
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
        PageHelper.startPage(pageNum, pageSize);
        List<HouseholdInfo> householdInfos = householdInfoMapper.selectAll();
        PageInfo<HouseholdInfo> pageInfo = new PageInfo<>(householdInfos);
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
        HouseholdInfo householdInfo = householdInfoMapper.selectByUserId(userId);
        return Response.success(householdInfo);
    }
}
