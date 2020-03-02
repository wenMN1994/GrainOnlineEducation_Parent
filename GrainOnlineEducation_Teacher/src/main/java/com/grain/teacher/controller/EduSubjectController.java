package com.grain.teacher.controller;


import com.grain.common.result.Result;
import com.grain.teacher.entity.vo.OneSubject;
import com.grain.teacher.service.EduSubjectService;
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
@RestController
@RequestMapping("/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

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
    @GetMapping("/tree")
    public Result TreeSubject(){
        List<OneSubject> subjectList = subjectService.getTree();
        return Result.ok().data("subjectList", subjectList);
    }
}

