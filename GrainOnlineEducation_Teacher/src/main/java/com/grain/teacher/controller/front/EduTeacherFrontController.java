package com.grain.teacher.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grain.common.result.Result;
import com.grain.teacher.entity.EduCourse;
import com.grain.teacher.entity.EduTeacher;
import com.grain.teacher.service.EduCourseService;
import com.grain.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/11 14:31
 * @description：
 * @modified By：
 * @version: $
 */
@Api("前端讲师模块")
@RestController
@RequestMapping("/front/teacher")
@CrossOrigin
public class EduTeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "前端获取所有讲师分页列表")
    @GetMapping("{page}/{limit}")
    public Result getTeacherListFront(
            @ApiParam(name = "page", value = "当前页", required = true)
            @PathVariable(value = "page") Integer  page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable(value = "limit") Integer  limit) {

        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherListFront(teacherPage);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "前端根据ID查询讲师")
    @GetMapping("{id}")
    public Result getTeacherByIdFront(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        EduTeacher teacher = teacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = courseService.selectCourseByTeacherIdFront(id);

        return Result.ok().data("teacher", teacher).data("courseList", courseList);
    }

}
