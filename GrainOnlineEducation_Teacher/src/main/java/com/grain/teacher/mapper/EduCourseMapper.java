package com.grain.teacher.mapper;

import com.grain.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-03
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    Map<String, Object> getMapById(String id);

    Map<String, Object> getMapByIdFront(String id);
}
