package com.natsumes.edu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.entity.ResponseEnum;
import com.natsumes.edu.mapper.NoticeInfoMapper;
import com.natsumes.edu.pojo.NoticeInfo;
import com.natsumes.edu.service.INoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hetengjiao
 */
@Service
public class NoticeInfoServiceImpl implements INoticeInfoService {

    @Autowired
    NoticeInfoMapper noticeInfoMapper;

    /**
     * 查询公告信息
     *
     * @param pageNum  页码
     * @param pageSize 每页数目
     *
     * @return 公告信息
     */
    @Override
    public Response<PageInfo> getNoticeInfo(Integer pageNum, Integer pageSize) {
        // 设置分页信息
        PageHelper.startPage(pageNum, pageSize);
        // 查询全部通知列表
        List<NoticeInfo> noticeInfos = noticeInfoMapper.selectAll();
        // 组装分页结果
        PageInfo pageInfo = new PageInfo<>(noticeInfos);
        // 返回公告信息
        return Response.success(pageInfo);
    }

    /**
     * 创建公告
     *
     * @param noticeInfo 公告信息
     *
     * @return 创建结果
     */
    @Override
    public Response<NoticeInfo> createNoticeInfo(NoticeInfo noticeInfo) {
        // 插入公告信息
        if (noticeInfoMapper.insertSelective(noticeInfo) <= 0) {
            // 插入失败，返回系统异常
            return Response.error(ResponseEnum.SYSTEM_ERROR);
        }
        // 返回公告信息
        return Response.success(noticeInfo);
    }

    /**
     * 更新公告
     *
     * @param noticeInfo 公告信息
     *
     * @return 修改结果
     */
    @Override
    public Response<NoticeInfo> updateNoticeInfo(NoticeInfo noticeInfo) {
        // 更新公告信息
        noticeInfoMapper.updateByPrimaryKeySelective(noticeInfo);
        // 返回结果
        return Response.success(noticeInfo);
    }

    /**
     * 删除公告
     *
     * @param noticeId 公告id
     *
     * @return 删除结果
     */
    @Override
    public Response deleteNoticeInfo(Integer noticeId) {
        // 根据公告id删除公告结果
        noticeInfoMapper.deleteByPrimaryKey(noticeId);
        // 返回结果
        return Response.success();
    }

    /**
     * 批量删除公告
     *
     * @param noticeIds {@link List <Integer> 公告id}
     *
     * @return 删除结果
     */
    @Override
    public Response deleteNoticeInfoBatch(List<Integer> noticeIds) {
        // 批量删除公告信息
        noticeInfoMapper.deleteBatch(noticeIds);
        // 返回结果
        return Response.success();
    }
}
