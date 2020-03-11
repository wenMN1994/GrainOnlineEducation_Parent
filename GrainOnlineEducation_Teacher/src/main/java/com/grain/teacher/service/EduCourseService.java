package com.grain.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grain.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grain.teacher.entity.query.CourseQuery;
import com.grain.teacher.entity.vo.CourseVo;

import java.util.List;
import java.util.Map;

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

    /**
     *根据课程ID查询课程基本信息
     * @param id
     * @return
     */
    CourseVo getCourseVoById(String id);

    /**
     * 修改课程基本信息和描述
     * @param courseVo
     * @return
     */
    boolean updateCourseVo(CourseVo courseVo);

    /**
     * 根据条件分页查询课程列表
     * @param pageParam
     * @param courseQuery
     */
    void getPageList(Page<EduCourse> pageParam, CourseQuery courseQuery);

    /**
     * 据课程ID删除课程信息
     * @param id
     * @return
     */
    boolean deleteCourseById(String id);

    /**
     * 根据课程Id修改课程状态
     * @param id
     * @return
     */
    Boolean updateStatusById(String id);

    /**
     * 根据课程Id查询课程Map对象
     * @param id
     * @return
     */
    Map<String, Object> getMapById(String id);

    /**
     * 根据讲师id查询当前讲师的课程列表
     * @param id
     * @return
     */
    List<EduCourse> selectCourseByTeacherIdFront(String id);

    /**
     * 前端获取分页课程列表
     * @param pageParam
     * @return
     */
    Map<String, Object> pageListFront(Page<EduCourse> pageParam);

    /**
     * 前端根据课程Id查询课程Map对象
     * @param id
     * @return
     */
    Map<String, Object> getMapByIdFront(String id);
}
