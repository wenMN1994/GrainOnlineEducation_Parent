package com.grain.vod.service;

import org.springframework.web.multipart.MultipartFile;

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
}
