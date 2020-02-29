package com.grain.oss.controller;

import com.grain.common.result.Result;
import com.grain.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/29 21:20
 * @description：文件管理控制器类
 * @modified By：
 * @version: $
 */
@Api("阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/oss")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @return 图片地址
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("file/upload")
    public Result upload(@ApiParam(name = "file", value = "文件", required = true)
                             @RequestParam("file") MultipartFile file){

        //调用阿里云OSS上传的程序
        String uploadUrl = fileService.upload(file);
        //返回r对象
        return Result.ok().message("文件上传成功").data("url", uploadUrl);
    }
}
