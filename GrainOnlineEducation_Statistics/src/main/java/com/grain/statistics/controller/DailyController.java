package com.grain.statistics.controller;


import com.grain.common.result.Result;
import com.grain.statistics.service.DailyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-09
 */
@CrossOrigin
@RestController
@RequestMapping("/statistics/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation(value = "创建统计数据")
    @PostMapping("{day}")
    public Result createStatisticsByDate(
            @ApiParam(name = "day", value = "统计日期", required = true)
            @PathVariable String day){

        dailyService.createStatisticsByDay(day);
        return Result.ok();
    }
}

