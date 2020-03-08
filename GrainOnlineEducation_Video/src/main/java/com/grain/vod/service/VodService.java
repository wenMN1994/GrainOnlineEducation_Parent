package com.grain.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/7 17:37
 * @description：
 * @modified By：
 * @version: $
 */
public interface VodService {

    /**
     *
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 根据视频ID删除视频
     * @param videoSourceId
     * @return
     */
    Boolean deleteVodById(String videoSourceId);

    /**
     * 批量删除云端视频
     * @param videoIdList
     * @return
     */
    Boolean removeVideoList(List videoIdList);
}
