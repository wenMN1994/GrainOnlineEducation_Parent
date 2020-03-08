package com.grain.teacher.client;

import com.grain.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/8 14:14
 * @description：
 * @modified By：
 * @version: $
 */
@FeignClient("grain-video")
@Component
public interface VodClient {

    @DeleteMapping(value = "/vod/{videoSourceId}")
    Result removeVideo(@PathVariable("videoSourceId") String videoSourceId);

    @DeleteMapping(value = "/vod/removeList")
    void removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
