package com.grain.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grain.statistics.client.UcenterClient;
import com.grain.statistics.entity.Daily;
import com.grain.statistics.mapper.DailyMapper;
import com.grain.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-09
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void createStatisticsByDay(String day) {

        // 查询统计表中是否存在当前日期的统计数据，如果存在则删除数据
        QueryWrapper<Daily> dailyQueryWrapper = new QueryWrapper<>();
        dailyQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dailyQueryWrapper);

        // 获取统计信息
        Integer registerNum = (Integer) ucenterClient.registerCount(day).getData().get("countRegister");
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(200, 300);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        // 创建统计对象
        Daily daily = new Daily();
        daily.setDateCalculated(day);
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);

        baseMapper.insert(daily);
    }
}
