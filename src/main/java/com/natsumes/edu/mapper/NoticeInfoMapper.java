package com.natsumes.edu.mapper;

import com.natsumes.edu.pojo.NoticeInfo;

import java.util.List;

public interface NoticeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBatch(List<Integer> ids);

    int insert(NoticeInfo record);

    int insertSelective(NoticeInfo record);

    NoticeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeInfo record);

    int updateByPrimaryKeyWithBLOBs(NoticeInfo record);

    int updateByPrimaryKey(NoticeInfo record);

    List<NoticeInfo> selectAll();
}