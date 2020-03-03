package com.grain.teacher.service;

import com.grain.teacher.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grain.teacher.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-01
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 导入Excl表格
     * @param file
     * @return
     */
    List<String> importExcel(MultipartFile file);

    /**
     * 获取课程分类树状数据
     * @return
     */
    List<OneSubject> getTree();

    /**
     * 根据ID删除课程分类
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 保存课程一级分类
     * @param subject
     * @return
     */
    boolean saveLevelOne(EduSubject subject);

    /**
     * 保存课程二级分类
     * @param subject
     * @return
     */
    boolean saveLevelTwo(EduSubject subject);
}
