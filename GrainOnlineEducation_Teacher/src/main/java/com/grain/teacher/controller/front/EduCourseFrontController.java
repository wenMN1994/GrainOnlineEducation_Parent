package com.grain.teacher.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grain.common.result.Result;
import com.grain.teacher.entity.EduCourse;
import com.grain.teacher.entity.vo.OneChapter;
import com.grain.teacher.service.EduChapterService;
import com.grain.teacher.service.EduCourseService;
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
 * @date：Created in 2020/3/11 15:58
 * @description：
 * @modified By：
 * @version: $
 */
@Api("前端课程模块")
@RestController
@RequestMapping("/front/course")
@CrossOrigin
public class EduCourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "前端获取分页课程列表")
    @GetMapping("{page}/{limit}")
    public Result pageListFront(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);

        Map<String, Object> map = courseService.pageListFront(pageParam);

        return  Result.ok().data(map);
    }

    @ApiOperation(value = "前端根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public Result getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        Map<String, Object> map = courseService.getMapByIdFront(courseId);

        //查询当前课程的章节信息
        List<OneChapter> chapterList = chapterService.queryChapterAndVideoList(courseId);

        return Result.ok().data(map).data("chapterList", chapterList);
    }
}
