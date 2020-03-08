package com.grain.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grain.teacher.client.VodClient;
import com.grain.teacher.entity.EduVideo;
import com.grain.teacher.mapper.EduVideoMapper;
import com.grain.teacher.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-06
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public Boolean deleteByVideoId(String id) {
        //根据小节的ID查询视频的ID
        //查询云端视频id
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }

        int delete = baseMapper.deleteById(id);

        return delete > 0;

    }

    @Override
    public Boolean deleteByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(wrapper);

        // 定义一个集合存放视频ID
        List<String> videoSourceIds = new ArrayList<>();

        // 可以获取视频ID
        for (EduVideo video : videoList) {
            if(!StringUtils.isEmpty(video.getVideoSourceId())){
                videoSourceIds.add(video.getVideoSourceId());
            }
        }

        if(videoSourceIds.size() > 0){
            vodClient.removeVideoList(videoSourceIds);
        }

        // 删除
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        int delete = baseMapper.delete(queryWrapper);

        return delete > 0;
    }
}
