package com.grain.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/29 21:28
 * @description：
 * @modified By：
 * @version: $
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);

}
