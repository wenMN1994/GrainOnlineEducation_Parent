package com.grain.teacher.controller;


import com.grain.common.result.Result;
import com.grain.teacher.entity.vo.CourseVo;
import com.grain.teacher.service.EduCourseDescriptionService;
import com.grain.teacher.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    /**
     * 保存基本信息
     */
    @PostMapping("save")
    public Result save(@RequestBody CourseVo courseVo){

        String courseId = courseService.saveVo(courseVo);
        return Result.ok().data("id", courseId);
    }

    /**
     * 根据课程ID获取课程基本信息和描述
     */
    @GetMapping("{id}")
    public Result getCourseVoById(@PathVariable String id){
        CourseVo vo = courseService.getCourseVoById(id);
        return Result.ok().data("courseInfo",vo);
    }

    /**
     * 修改课程基本信息和描述
     */
    @PutMapping("update")
    public Result updateCourseVo(@RequestBody CourseVo courseVo){
        boolean flag = courseService.updateCourseVo(courseVo);
        if(flag){
            return Result.ok();
        } else {
            return Result.error();
        }
    }

}

