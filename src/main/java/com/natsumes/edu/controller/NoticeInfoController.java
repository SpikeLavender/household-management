package com.natsumes.edu.controller;

import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.pojo.NoticeInfo;
import com.natsumes.edu.service.INoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hetengjiao
 */
@RestController
@RequestMapping("/notice")
public class NoticeInfoController {

    @Autowired
    INoticeInfoService noticeInfoServiceImpl;

    /**
     * 查询公告信息
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 公告信息
     */
    @GetMapping("/notices")
    public Response<PageInfo> getNoticeInfo(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return noticeInfoServiceImpl.getNoticeInfo(pageNum, pageSize);
    }

    /**
     * 创建公告
     * @param noticeInfo 公告信息
     * @return 创建结果
     */
    @PostMapping("/notices")
    public Response<NoticeInfo> createNoticeInfo(@RequestBody NoticeInfo noticeInfo) {
        return noticeInfoServiceImpl.createNoticeInfo(noticeInfo);
    }

    /**
     * 更新公告
     * @param noticeInfo 公告信息
     * @return 修改结果
     */
    @PostMapping("/{noticeId}")
    public Response<NoticeInfo> updateNoticeInfo(@PathVariable Integer noticeId, @RequestBody NoticeInfo noticeInfo) {
        noticeInfo.setId(noticeId);
        return noticeInfoServiceImpl.updateNoticeInfo(noticeInfo);
    }

    /**
     * 删除公告
     * @param noticeId 公告id
     * @return 删除结果
     */
    @DeleteMapping("/{noticeId}")
    public Response deleteNoticeInfo(@PathVariable Integer noticeId) {
        return noticeInfoServiceImpl.deleteNoticeInfo(noticeId);
    }

    /**
     * 批量删除公告
     * @param noticeIds {@link List <Integer> 公告id}
     * @return 删除结果
     */
    @DeleteMapping("/bantchs/{noticeIds}")
    public Response deleteNoticeInfoBatch(@PathVariable String noticeIds) {
        String[] split = noticeIds.split(",");
        List<String> ids = Arrays.asList(split);
        List<Integer> integers = ids.stream().map(Integer::parseInt).collect(Collectors.toList());
        return noticeInfoServiceImpl.deleteNoticeInfoBatch(integers);
    }

}
