package com.grain.statistics.service;

import com.grain.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-09
 */
public interface DailyService extends IService<Daily> {

    /**
     * 根据日期创建统计信息
     * @param day
     */
    void createStatisticsByDay(String day);

    /**
     * 获取统计数据
     * @param begin
     * @param end
     * @param type
     * @return
     */
    Map<String, Object> getChartData(String begin, String end, String type);
}
