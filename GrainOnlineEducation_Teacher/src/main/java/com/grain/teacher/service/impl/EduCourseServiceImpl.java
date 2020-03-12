package com.grain.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grain.teacher.entity.EduCourse;
import com.grain.teacher.entity.EduCourseDescription;
import com.grain.teacher.entity.query.CourseQuery;
import com.grain.teacher.entity.vo.CourseVo;
import com.grain.common.exception.EduException;
import com.grain.teacher.mapper.EduCourseMapper;
import com.grain.teacher.service.EduChapterService;
import com.grain.teacher.service.EduCourseDescriptionService;
import com.grain.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grain.teacher.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-03
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Override
    public String saveVo(CourseVo courseVo) {
        //1、添加课程
        baseMapper.insert(courseVo.getEduCourse());
        //2、获取课程的ID
        String courseId = courseVo.getEduCourse().getId();
        //3、添加课程描述
        courseVo.getEduCourseDesc().setId(courseId);
        courseDescriptionService.save(courseVo.getEduCourseDesc());
        return courseId;
    }

    @Override
    public CourseVo getCourseVoById(String id) {
        CourseVo courseVo = new CourseVo();
        //获取课程
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse == null){
            throw new EduException(20001,"此课程不存在");
        }
        courseVo.setEduCourse(eduCourse);

        EduCourseDescription eduCourseDescription = courseDescriptionService.getById(id);
        if(eduCourseDescription != null){
            courseVo.setEduCourseDesc(eduCourseDescription);
        }
        return courseVo;

    }

    @Override
    public boolean updateCourseVo(CourseVo courseVo) {
        //判断ID是否存在、如果存在直接放回false
        if(StringUtils.isEmpty(courseVo.getEduCourse().getId())){
            throw new EduException(20001,"没有课程编号，修改失败");
        }
        int i = baseMapper.updateById(courseVo.getEduCourse());
        if(i <= 0 ){
            throw new EduException(20001,"修改课程信息失败");
        }
        //获取course—ID
        String courseId = courseVo.getEduCourse().getId();
        //设置课程描述的ID
        courseVo.getEduCourseDesc().setId(courseId);
        boolean b = courseDescriptionService.updateById(courseVo.getEduCourseDesc());
        return b;

    }

    @Override
    public void getPageList(Page<EduCourse> pageParam, CourseQuery courseQuery) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if(courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }
        baseMapper.selectPage(pageParam, queryWrapper);

    }

    @Override
    public boolean deleteCourseById(String id) {
        // 根据id删除所有视频
        videoService.deleteByCourseId(id);
        // 根据id删除所有章节
        chapterService.deleteChapterByCourseId(id);

        //删除课程描述
        boolean b = courseDescriptionService.removeById(id);
        if(!b){// 如果描述没有删除成功直接返回
            return false;
        }
        Integer result = baseMapper.deleteById(id);

        return result == 1 ;

    }

    @Override
    public Boolean updateStatusById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        int update = baseMapper.updateById(course);
        return update > 0;
    }

    @Override
    public Map<String, Object> getMapById(String id) {
        Map<String, Object> map = baseMapper.getMapById(id);
        return map;
    }

    @Override
    public List<EduCourse> selectCourseByTeacherIdFront(String teacherId) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<EduCourse>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<EduCourse> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> pageListFront(Page<EduCourse> pageParam) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");

        baseMapper.selectPage(pageParam, queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public Map<String, Object> getMapByIdFront(String id) {
        Map<String, Object> map = baseMapper.getMapByIdFront(id);
        return map;
    }

    @Override
    public void updateCourseViewCount(String courseId) {

        EduCourse course = baseMapper.selectById(courseId);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }

}
