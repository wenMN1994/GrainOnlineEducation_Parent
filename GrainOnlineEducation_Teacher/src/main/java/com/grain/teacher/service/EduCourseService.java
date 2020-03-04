package com.grain.teacher.service;

import com.grain.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grain.teacher.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-03
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 保存课程基本信息
     * @param courseVo
     * @return
     */
    String saveVo(CourseVo courseVo);
}
