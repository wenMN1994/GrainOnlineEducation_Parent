package com.grain.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grain.teacher.entity.EduTeacher;
import com.grain.teacher.entity.query.TeacherQuery;
import com.grain.teacher.mapper.EduTeacherMapper;
import com.grain.teacher.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-02-27
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        //获取对象属性
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public Map<String, Object> getTeacherListFront(Page<EduTeacher> teacherPage) {
        //调用方法分页查询，把分页数据封装到pageTeacher对象里面
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        baseMapper.selectPage(teacherPage, queryWrapper);

        //把pageTeacher对象里面分页数据获取出来，封装到map集合中
        long current = teacherPage.getCurrent(); //当前页
        long pages = teacherPage.getPages(); //总页数
        long size = teacherPage.getSize(); //每页显示记录数
        long total = teacherPage.getTotal(); //总记录数
        List<EduTeacher> records = teacherPage.getRecords(); //每页数据list集合
        boolean hasPrevious = teacherPage.hasPrevious(); //上一页
        boolean hasNext = teacherPage.hasNext(); //下一页

        //封装map
        Map<String,Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

}
