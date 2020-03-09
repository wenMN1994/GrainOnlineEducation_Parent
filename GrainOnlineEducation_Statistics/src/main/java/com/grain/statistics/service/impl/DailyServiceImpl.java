package com.grain.statistics.service.impl;

import com.grain.statistics.entity.Daily;
import com.grain.statistics.mapper.DailyMapper;
import com.grain.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
