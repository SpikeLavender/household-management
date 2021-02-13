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
        PageHelper.startPage(pageNum, pageSize);
        List<NoticeInfo> noticeInfos = noticeInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(noticeInfos);
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
        if (noticeInfoMapper.insertSelective(noticeInfo) <= 0) {
            return Response.error(ResponseEnum.SYSTEM_ERROR);
        }
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
        noticeInfoMapper.updateByPrimaryKeySelective(noticeInfo);
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
        noticeInfoMapper.deleteByPrimaryKey(noticeId);
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
        noticeInfoMapper.deleteBatch(noticeIds);
        return Response.success();
    }
}
