package com.natsumes.edu.service;

import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.pojo.HouseholdInfo;
import org.springframework.stereotype.Service;

/**
 * @author hetengjiao
 */
@Service
public interface IHouseholdService {

    /**
     * 创建户籍信息
     * @param householdInfo 户籍信息
     * @return 创建好的户籍信息
     */
    Response<HouseholdInfo> createHouseholdInfo(HouseholdInfo householdInfo);

    /**
     * 更新户籍信息
     * @param householdInfo 户籍信息
     * @return 更新后的信息
     */
    Response<HouseholdInfo> updateHouseholdInfo(HouseholdInfo householdInfo);

    /**
     * 删除户籍信息
     * @param id 户籍id
     * @param userId 用户id
     * @return 删除结果
     */
    Response deleteHouseholdInfo(Integer id, Integer userId);

    /**
     * 查询户籍信息
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 全部用户信息
     */
    Response<PageInfo> getHouseholdInfo(Integer pageNum, Integer pageSize);

    /**
     * 使用用户id查询户籍信息
     * @param userId 用户id
     * @return 户籍信息
     */
    Response<HouseholdInfo> getHouseholdInfoByUserId(Integer userId);
}
