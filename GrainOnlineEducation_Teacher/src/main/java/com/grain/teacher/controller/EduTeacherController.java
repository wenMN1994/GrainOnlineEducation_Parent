package com.grain.teacher.controller;


import com.grain.teacher.entity.EduTeacher;
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
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("list")
    public List<EduTeacher> list(){
        return teacherService.list(null);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public boolean deleteTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        try {
            teacherService.removeById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

