package com.grain.teacher.service.impl;

import com.grain.teacher.entity.EduVideo;
import com.grain.teacher.mapper.EduVideoMapper;
import com.grain.teacher.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public Boolean deleteByVideoId(String id) {
        //根据小节的ID查询视频的ID
        //TODO 删除阿里云上的视频

        int delete = baseMapper.deleteById(id);

        return delete > 0;

    }
}
