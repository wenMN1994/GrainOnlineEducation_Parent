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
     * 课程章节添加
     * @param chapter
     * @return
     */
    boolean saveChapter(EduChapter chapter);

    /**
     * 根据课程ID查询章节、小节的列表
     * @param id
     * @return
     */
    List<OneChapter> queryChapterAndVideoList(String id);

    /**
     * 修改章节
     * 修改时判断章节名称是否重复
     * @param chapter
     * @return
     */
    boolean updateChapterById(EduChapter chapter);

    /**
     * 根据章节的ID删除章节信息
     * @param id
     * @return
     */
    boolean deleteChapterById(String id);
}
