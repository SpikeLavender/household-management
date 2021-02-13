package com.natsumes.edu.mapper;

import com.natsumes.edu.pojo.HouseholdInfo;

import java.util.List;

public interface HouseholdInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserId(Integer userId);

    int insert(HouseholdInfo record);

    int insertSelective(HouseholdInfo record);

    int insertOrUpdateSelective(HouseholdInfo record);

    HouseholdInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseholdInfo record);

    int updateByPrimaryKeyWithBLOBs(HouseholdInfo record);

    int updateByPrimaryKey(HouseholdInfo record);

    List<HouseholdInfo> selectAll();

    HouseholdInfo selectByUserId(Integer userId);
}