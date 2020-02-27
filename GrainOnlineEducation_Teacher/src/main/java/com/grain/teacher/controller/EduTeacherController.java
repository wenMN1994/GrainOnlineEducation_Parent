package com.grain.teacher.controller;


import com.grain.teacher.entity.EduTeacher;
import com.grain.teacher.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-02-27
 */
@RestController
@RequestMapping("/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("list")
    public List<EduTeacher> list(){
        return teacherService.list(null);
    }

}

