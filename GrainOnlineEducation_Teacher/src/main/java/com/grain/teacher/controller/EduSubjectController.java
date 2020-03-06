package com.grain.teacher.controller;


import com.grain.common.result.Result;
import com.grain.teacher.entity.EduSubject;
import com.grain.teacher.entity.vo.OneSubject;
import com.grain.teacher.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-01
 */
@Api(value = "课程科目(分类)管理")
@RestController
@RequestMapping("/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    /**
     * 导入课程分类
     * @param file
     * @return
     */
    @ApiOperation(value = "导入课程分类")
    @PostMapping("import")
    public Result importSubject(MultipartFile file){
        //考虑到Excel模板中的数据不准确，所以返回多个错误信息，那么多个错误信息放在集合中
        List<String> mesList = subjectService.importExcel(file);

        if(mesList.size() ==0){
            return Result.ok();
        } else {
            return Result.ok().data("messageList", mesList);
        }
    }

    /**
     * 获取课程分类的Tree
     * @return
     */
    @ApiOperation(value = "获取课程分类的Tree")
    @GetMapping("/tree")
    public Result TreeSubject(){
        List<OneSubject> subjectList = subjectService.getTree();
        return Result.ok().data("subjectList", subjectList);
    }

    /**
     * 根据ID删除课程分类
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID删除课程分类")
    @DeleteMapping("/{id}")
    public Result deleteById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable  String id){
        boolean isDelete = subjectService.deleteById(id);
        if(isDelete){
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    /**
     * 新增一级分类
     * @param subject
     * @return
     */
    @ApiOperation(value = "新增一级分类")
    @PostMapping("saveLevelOne")
    public Result saveLevelOne(
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody EduSubject subject){

        boolean result = subjectService.saveLevelOne(subject);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("新增失败");
        }
    }

    /**
     * 新增二级分类
     * @param subject
     * @return
     */
    @ApiOperation(value = "新增二级分类")
    @PostMapping("saveLevelTwo")
    public Result saveLevelTwo(
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody EduSubject subject){

        boolean result = subjectService.saveLevelTwo(subject);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("新增失败");
        }
    }
}

