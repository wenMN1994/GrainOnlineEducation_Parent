package com.grain.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.grain.common.exception.EduException;
import com.grain.vod.service.VodService;
import com.grain.vod.utils.AliyunVodSDKUtil;
import com.grain.vod.utils.VodConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

    @Override
    public Boolean deleteVodById(String videoSourceId) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtil.initVodClient(
                    VodConstantPropertiesUtil.ACCESS_KEY_ID,
                    VodConstantPropertiesUtil.ACCESS_KEY_SECRET);

            DeleteVideoResponse response = new DeleteVideoResponse();
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoSourceId);

            response = client.getAcsResponse(request);

            return true;
        } catch (Exception e) {
            log.warn("ErrorMessage = " + e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public Boolean removeVideoList(List videoIdList) {
        try {
            //初始化
            DefaultAcsClient client = AliyunVodSDKUtil.initVodClient(
                    VodConstantPropertiesUtil.ACCESS_KEY_ID,
                    VodConstantPropertiesUtil.ACCESS_KEY_SECRET);

            //创建请求对象
            //一次只能批量删20个
            String str = org.apache.commons.lang.StringUtils.join(videoIdList.toArray(), ",");
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(str);

            //获取响应
            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");

            return true;
        } catch (ClientException e) {
            throw new EduException(20001, "视频删除失败");
        }
    }
}
