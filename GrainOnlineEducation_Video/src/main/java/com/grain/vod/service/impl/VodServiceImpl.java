package com.grain.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.grain.common.exception.EduException;
import com.grain.vod.service.VodService;
import com.grain.vod.utils.VodConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/7 17:40
 * @description：
 * @modified By：
 * @version: $
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    VodConstantPropertiesUtil.ACCESS_KEY_ID,
                    VodConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.warn(errorMessage);
                if(StringUtils.isEmpty(videoId)){
                    throw new EduException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (IOException e) {
            throw new EduException(20001, "grain vod 服务上传失败");
        }

    }
}
