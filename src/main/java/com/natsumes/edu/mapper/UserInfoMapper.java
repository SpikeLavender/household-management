package com.natsumes.edu.mapper;

import com.natsumes.edu.pojo.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByUsername(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int countByUsername(String username);

    UserInfo selectByUsername(String username);

    List<UserInfo> selectAll();

    List<UserInfo> selectByStatus(Integer status);
}