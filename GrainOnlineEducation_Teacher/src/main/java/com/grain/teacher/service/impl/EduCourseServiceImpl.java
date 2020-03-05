package com.grain.teacher.service.impl;

import com.grain.teacher.entity.EduCourse;
import com.grain.teacher.entity.EduCourseDescription;
import com.grain.teacher.entity.vo.CourseVo;
import com.grain.teacher.exception.EduException;
import com.grain.teacher.mapper.EduCourseMapper;
import com.grain.teacher.service.EduCourseDescriptionService;
import com.grain.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
}
