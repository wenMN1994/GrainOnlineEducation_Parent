package com.grain.teacher.service.impl;

import com.grain.teacher.client.VodClient;
import com.grain.teacher.entity.EduVideo;
import com.grain.teacher.mapper.EduVideoMapper;
import com.grain.teacher.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
