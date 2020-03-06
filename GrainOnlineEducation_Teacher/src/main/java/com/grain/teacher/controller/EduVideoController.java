package com.grain.teacher.controller;


import com.grain.common.result.Result;
import com.grain.teacher.entity.EduVideo;
import com.grain.teacher.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-06
 */
@Api(value = "课时管理")
@RestController
@RequestMapping("/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    /**
     * 保存课程章节小节
     * @param video
     * @return
     */
    @ApiOperation(value = "保存课程章节小节")
    @PostMapping("save")
    public Result save(
            @ApiParam(name = "video", value = "课程视频对象", required = true)
            @RequestBody EduVideo video){
        boolean save = videoService.save(video);
        if(save){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 查询课程章节小节
     * @param id
     * @return
     */
    @ApiOperation(value = "查询课程章节小节")
    @GetMapping("{id}")
    public Result getVideoById(
            @ApiParam(name = "id", value = "课程章节小节", required = true)
            @PathVariable String id){
        try {
            EduVideo eduVideo = videoService.getById(id);
            return  Result.ok().data("eduVideo",eduVideo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 修改课程章节小节
     * @param video
     * @return
     */
    @ApiOperation(value = "修改课程章节小节")
    @PutMapping("update")
    public Result updateVideo(
            @ApiParam(name = "video", value = "课程章节小节对象", required = true)
            @RequestBody EduVideo video){
        boolean update = videoService.updateById(video);
        if(update){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 删除课程章节小节
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程章节小节")
    @DeleteMapping("{id}")
    public Result deleteVideo(
            @ApiParam(name = "id", value = "课程章节小节ID", required = true)
            @PathVariable String id){
        Boolean flag = videoService.deleteByVideoId(id);
        if(flag){
            return Result.ok();
        }
        return Result.error();
    }

}

