package com.grain.teacher.controller;


import com.grain.common.result.Result;
import com.grain.teacher.entity.EduChapter;
import com.grain.teacher.entity.vo.OneChapter;
import com.grain.teacher.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-03
 */
@Api("章节管理")
@RestController
@RequestMapping("/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    /**
     * 根据课程ID查询章节、小节的列表
     * 1、创建一个对象，作为章节Vo，里面包括二级Vo
     * 2、创建二级的Vo（Video）
     * 3、根据课程ID查询章节的列表，遍历这个列表，根据每一个章节的ID查询二级列表（Video集合）
     * @param courseId
     * @return
     */
    @ApiOperation(value = "根据课程ID查询章节、小节的列表")
    @GetMapping("{courseId}")
    public Result getChapterAndVideoList(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){
        List<OneChapter> list = chapterService.queryChapterAndVideoList(courseId);
        if(list.size()>=0){
            return Result.ok().data("list",list);
        }
        return Result.error();
    }

    /**
     * 课程章节添加
     * @param chapter
     * @return
     */
    @ApiOperation(value = "课程章节添加")
    @PostMapping("save")
    public Result save(
            @ApiParam(name = "chapter", value = "课程章节对象", required = true)
            @RequestBody EduChapter chapter){
        boolean flag = chapterService.save(chapter);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

}

