package com.natsumes.edu.service;

import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.pojo.NoticeInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hetengjiao
 */
@Service
public interface INoticeInfoService {

    /**
     * 查询公告信息
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 公告信息
     */
    Response<PageInfo> getNoticeInfo(Integer pageNum, Integer pageSize);

    /**
     * 创建公告
     * @param noticeInfo 公告信息
     * @return 创建结果
     */
    Response<NoticeInfo> createNoticeInfo(NoticeInfo noticeInfo);

    /**
     * 更新公告
     * @param noticeInfo 公告信息
     * @return 修改结果
     */
    Response<NoticeInfo> updateNoticeInfo(NoticeInfo noticeInfo);

    /**
     * 删除公告
     * @param noticeId 公告id
     * @return 删除结果
     */
    Response deleteNoticeInfo(Integer noticeId);

    /**
     * 批量删除公告
     * @param noticeIds {@link List<Integer> 公告id}
     * @return 删除结果
     */
    Response deleteNoticeInfoBatch(List<Integer> noticeIds);
}
