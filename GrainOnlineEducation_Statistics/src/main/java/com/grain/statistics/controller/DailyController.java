package com.grain.statistics.controller;


import com.grain.common.result.Result;
import com.grain.statistics.service.DailyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /**
     * 创建统计数据
     * @param day
     * @return
     */
    @ApiOperation(value = "创建统计数据")
    @PostMapping("{day}")
    public Result createStatisticsByDate(
            @ApiParam(name = "day", value = "统计日期", required = true)
            @PathVariable String day){

        dailyService.createStatisticsByDay(day);
        return Result.ok();
    }

    /**
     * 获取统计数据
     * @param begin
     * @param end
     * @param type
     * @return
     */
    @ApiOperation(value = "获取统计数据")
    @GetMapping("show-chart/{begin}/{end}/{type}")
    public Result showChart(
            @ApiParam(name = "begin", value = "开始时间")
            @PathVariable String begin,
            @ApiParam(name = "end", value = "结束时间")
            @PathVariable String end,
            @ApiParam(name = "type", value = "统计类型")
            @PathVariable String type){

        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return Result.ok().data(map);
    }
}

