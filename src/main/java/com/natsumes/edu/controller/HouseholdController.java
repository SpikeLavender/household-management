package com.natsumes.edu.controller;

import com.github.pagehelper.PageInfo;
import com.natsumes.edu.entity.Response;
import com.natsumes.edu.pojo.HouseholdInfo;
import com.natsumes.edu.service.IHouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hetengjiao
 */
@RestController
@RequestMapping("/household")
public class HouseholdController {

    @Autowired
    IHouseholdService householdServiceImpl;

    /**
     * 创建户籍信息
     * @param householdInfo 户籍信息
     * @return 创建好的户籍信息
     */
    @PostMapping("/")
    public Response<HouseholdInfo> createHouseholdInfo(@RequestBody HouseholdInfo householdInfo) {
        return householdServiceImpl.createHouseholdInfo(householdInfo);
    }

    /**
     * 更新户籍信息
     * @param householdInfo 户籍信息
     * @return 更新后的信息
     */
    @PutMapping("/{household}")
    public Response<HouseholdInfo> updateHouseholdInfo(@PathVariable Integer household,
                                                       @RequestBody HouseholdInfo householdInfo) {
        householdInfo.setId(household);
        return householdServiceImpl.updateHouseholdInfo(householdInfo);
    }

    /**
     * 删除户籍信息
     * @param household 户籍id
     * @param userId 用户id
     * @return 删除结果
     */
    @DeleteMapping("/{userId}/{household}")
    public Response deleteHouseholdInfo(@PathVariable Integer household, @PathVariable Integer userId) {
        return householdServiceImpl.deleteHouseholdInfo(household, userId);
    }

    /**
     * 查询户籍信息
     * @param pageNum 页码
     * @param pageSize 每页数目
     * @return 全部用户信息
     */
    @GetMapping(value = "/all")
    public Response<PageInfo> getHouseholdInfo(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                               @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return householdServiceImpl.getHouseholdInfo(pageNum, pageSize);
    }

    /**
     * 使用用户id查询户籍信息
     * @param userId 用户id
     * @return 户籍信息
     */
    @GetMapping("/{userId}")
    public Response<HouseholdInfo> getHouseholdInfoByUserId(@PathVariable Integer userId) {
        return householdServiceImpl.getHouseholdInfoByUserId(userId);
    }

}
