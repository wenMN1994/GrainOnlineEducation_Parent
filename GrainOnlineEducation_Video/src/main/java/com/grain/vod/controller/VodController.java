package com.grain.vod.controller;

import com.aliyuncs.exceptions.ClientException;
import com.grain.common.result.Result;
import com.grain.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

        String videoSourceId = vodService.uploadVideo(file);
        return Result.ok().data("videoSourceId", videoSourceId);
    }

    /**
     * 根据视频ID删除视频
     * @return
     */
    @ApiOperation(value = "删除视频")
    @DeleteMapping("{videoSourceId}")
    public Result deleteVideoById(@PathVariable String videoSourceId){
        Boolean flag = vodService.deleteVodById(videoSourceId);
        if(flag){
            return Result.ok();
        }
        return Result.error();
    }


    /**
     * 批量删除视频
     * @return
     */
    @ApiOperation(value = "批量删除视频")
    @DeleteMapping("removeList")
    public Result removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List videoIdList){
        Boolean flag = vodService.removeVideoList(videoIdList);
        if(flag){
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "获取播放凭证")
    @GetMapping("get-play-auth/{videoId}")
    public Result getVideoPlayAuth(
            @ApiParam(name = "videoId", value = "云端视频id", required = true)
            @PathVariable String videoId) throws ClientException {
        String playAuth = vodService.getVideoPlayAuth(videoId);
        return Result.ok().data("playAuth", playAuth);
    }

}
