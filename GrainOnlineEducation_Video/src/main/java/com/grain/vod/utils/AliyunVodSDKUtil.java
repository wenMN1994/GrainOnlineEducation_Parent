package com.grain.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/7 15:03
 * @description：
 * @modified By：
 * @version: $
 */
public class AliyunVodSDKUtil {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

}
