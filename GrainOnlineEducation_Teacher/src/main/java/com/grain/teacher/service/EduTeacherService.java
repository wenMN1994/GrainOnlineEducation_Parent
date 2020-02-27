package com.grain.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grain.teacher.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grain.teacher.entity.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-02-27
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     *  根据多个条件查询讲师列表
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

}
