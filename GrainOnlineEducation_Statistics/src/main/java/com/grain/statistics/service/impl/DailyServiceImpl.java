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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {

        // 根据时间范围进行数据查询
        QueryWrapper<Daily> dailyQueryWrapper = new QueryWrapper<>();
        dailyQueryWrapper.between("date_calculated", begin, end);
        // 指定查询的字段
        dailyQueryWrapper.select("date_calculated", type);
        List<Daily> statisticsList  = baseMapper.selectList(dailyQueryWrapper);
        //把数据构建成想要的结构，最终变成两个json数组形式
        //创建两个list集合
        //日期集合
        List<String> calculatedList = new ArrayList<>();
        //数据集合
        List<Integer> dataList = new ArrayList<>();

        //向两个list集合中封装数据
        //遍历查询集合
        for (int i = 0; i < statisticsList.size(); i++) {
            //集合每个对象
            Daily sta = statisticsList.get(i);
            //封装日期集合数据
            String dateCalculated = sta.getDateCalculated();
            calculatedList.add(dateCalculated);

            //封装数据部分
            //判断获取哪个统计因子
            switch (type) {
                case "register_num":
                    Integer registerNum = sta.getRegisterNum();
                    dataList.add(registerNum);
                    break;
                case "login_num":
                    Integer loginNum = sta.getLoginNum();
                    dataList.add(loginNum);
                    break;
                case "video_view_num":
                    Integer videoViewNum = sta.getVideoViewNum();
                    dataList.add(videoViewNum);
                    break;
                case "course_num":
                    Integer courseNum = sta.getCourseNum();
                    dataList.add(courseNum);
                    break;
                default:
                    break;
            }
        }

        //创建map集合，把封装之后两个list集合放到map集合，返回
        Map<String,Object> map = new HashMap<>();
        map.put("calculatedList",calculatedList);
        map.put("dataList",dataList);

        return map;
    }
}
