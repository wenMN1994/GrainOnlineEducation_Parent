package com.grain.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grain.teacher.entity.EduChapter;
import com.grain.teacher.entity.EduVideo;
import com.grain.teacher.entity.vo.OneChapter;
import com.grain.teacher.entity.vo.TwoVideo;
import com.grain.teacher.mapper.EduChapterMapper;
import com.grain.teacher.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grain.teacher.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public boolean saveChapter(EduChapter chapter) {
        if(chapter == null){
            return false;
        }
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("title", chapter.getTitle());
        Integer count = baseMapper.selectCount(chapterWrapper);
        if(count > 0){
            return false;
        }
        int insert = baseMapper.insert(chapter);
        return insert == 1;
    }

    @Override
    public List<OneChapter> queryChapterAndVideoList(String id) {
        //定义一个章节集合
        List<OneChapter> oneChapterList = new ArrayList<>();
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",id);
        chapterWrapper.orderByAsc("sort", "id");
        //先查询章节列表集合
        List<EduChapter> chapterList = baseMapper.selectList(chapterWrapper);
        //再遍历章节集合，获取每个章节ID
        for (EduChapter eduChapter : chapterList) {
            OneChapter oneChapter = new OneChapter();
            BeanUtils.copyProperties(eduChapter,oneChapter);
            //再根据每个章节的ID查询节点的列表
            QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
            videoWrapper.eq("chapter_id",oneChapter.getId());
            videoWrapper.orderByAsc("sort", "id");
            List<EduVideo> eduVideoList = videoService.list(videoWrapper);
            //把小节的列表添加章节中
            for(EduVideo eduVideo : eduVideoList){
                TwoVideo twoVideo = new TwoVideo();
                BeanUtils.copyProperties(eduVideo,twoVideo);
                oneChapter.getChildren().add(twoVideo);
            }
            oneChapterList.add(oneChapter);
        }

        return oneChapterList;
    }

    @Override
    public boolean updateChapterById(EduChapter chapter) {
        if(chapter == null){
            return false;
        }
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("title", chapter.getTitle());
        Integer count = baseMapper.selectCount(chapterWrapper);
        if(count > 0){
            return false;
        }
        int update = baseMapper.updateById(chapter);
        return update == 1;
    }
}
