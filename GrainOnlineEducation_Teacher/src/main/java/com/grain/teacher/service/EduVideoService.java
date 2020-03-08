package com.grain.teacher.service;

import com.grain.teacher.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-06
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 删除小节
     * @param id
     * @return
     */
    Boolean deleteByVideoId(String id);

    /**
     *  根据课程ID删除小节
     * @param courseId
     * @return
     */
    Boolean deleteByCourseId(String courseId);
}
