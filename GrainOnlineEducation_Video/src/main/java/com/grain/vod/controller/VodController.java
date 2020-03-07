package com.grain.vod.controller;

import com.grain.common.result.Result;
import com.grain.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/7 17:33
 * @description：
 * @modified By：
 * @version: $
 */
@Api("阿里云视频点播微服务")
@RestController
@RequestMapping("vod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation(value = "上传视频")
    @PostMapping("upload")
    public Result uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = vodService.uploadVideo(file);
        return Result.ok().message("视频上传成功").data("videoId", videoId);
    }

}
