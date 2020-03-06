package com.grain.teacher.service;

import com.grain.teacher.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grain.teacher.entity.vo.OneChapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-03
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程ID查询章节、小节的列表
     * @param id
     * @return
     */
    List<OneChapter> queryChapterAndVideoList(String id);
}
