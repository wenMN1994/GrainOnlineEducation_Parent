package com.grain.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grain.common.result.Result;
import com.grain.teacher.entity.EduTeacher;
import com.grain.teacher.entity.query.TeacherQuery;
import com.grain.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-02-27
 */
@Api("讲师管理")
@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 获取所有讲师列表
     * @return
     */
    @ApiOperation(value = "获取所有讲师列表")
    @GetMapping("list")
    public Result list(){
        try {
            List<EduTeacher> list = teacherService.list(null);
            return Result.ok().data("items", list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 根据ID删除讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public Result deleteTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        try {
            teacherService.removeById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 讲师分页列表
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "讲师分页列表")
    @GetMapping("/{page}/{limit}")
    public Result selectTeacherByPage(
            @ApiParam(name = "page", value = "当前页", required = true)
            @PathVariable(value = "page") Integer  page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable(value = "limit") Integer  limit) {

        try {
            Page<EduTeacher> teacherPage = new Page<>(page, limit);
            teacherService.page(teacherPage, null);
            return Result.ok().data("total", teacherPage.getTotal()).data("rows",teacherPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 根据讲师条件分页查询
     * @param page
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation(value = "根据讲师条件分页查询")
    @PostMapping("/{page}/{limit}")
    public Result selectTeacherByPage(
            @ApiParam(name = "page", value = "当前页", required = true)
            @PathVariable(value = "page") Integer  page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable(value = "limit") Integer  limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody TeacherQuery teacherQuery) {

        try {
            Page<EduTeacher> teacherPage = new Page<>(page, limit);
            teacherService.pageQuery(teacherPage,teacherQuery);
            return Result.ok().data("total", teacherPage.getTotal()).data("rows",teacherPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 新增讲师
     * @param teacher
     * @return
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping("save")
    public Result saveTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        try {
            teacherService.save(teacher);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 根据ID查询讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public Result selectTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        try {
            EduTeacher teacher = teacherService.getById(id);
            return Result.ok().data("teacher", teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 根据讲师ID修改讲师信息
     * @param teacher
     * @return
     */
    @ApiOperation(value = "根据讲师ID修改讲师信息")
    @PutMapping("update")
    public Result updateTeacherById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        try {
            teacherService.updateById(teacher);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }


}

