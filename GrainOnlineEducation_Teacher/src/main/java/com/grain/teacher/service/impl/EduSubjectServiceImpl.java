package com.grain.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grain.teacher.entity.EduSubject;
import com.grain.teacher.entity.vo.OneSubject;
import com.grain.teacher.entity.vo.TwoSubject;
import com.grain.teacher.mapper.EduSubjectMapper;
import com.grain.teacher.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<String> importExcel(MultipartFile file) {

        //存储错误信息集合
        List<String> meg = new ArrayList<>();
        try {
            //1、获取文件流
            InputStream inputStream = file.getInputStream();
            //2、根据流创建workBook
            Workbook workbook = new HSSFWorkbook(inputStream);
            //3、获取sheet.getSheetAt(0)
            Sheet sheet = workbook.getSheetAt(0);
            //4、根据sheet获取行数
            int lastRowNum = sheet.getLastRowNum();
            if(lastRowNum <= 1){
                meg.add("请填写数据");
                return meg;
            }
            //5、遍历行
            for (int rowNum = 1; rowNum < lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(0);
                //6、获取每一行的第一列：一级分类
                if(cell == null ){
                    meg.add("第" + rowNum + "行第1列为空");
                    continue;
                }
                String cellValue = cell.getStringCellValue();
                if(StringUtils.isEmpty(cellValue)){
                    meg.add("第" + rowNum + "行第1列为数据空");
                    continue;
                }

                //7、判断列是否存在，存在获取的数据
                EduSubject subject = this.selectSubjectByName(cellValue);
                String pid = "";
                //8、把这一列中的数据（一级分类）保存到数据库中
                if(subject == null){
                    //9、在保存之前判断此一级分类是否存在，如果在就不再添加；如果不存在就保存数据
                    EduSubject su = new EduSubject();
                    su.setTitle(cellValue);
                    su.setParentId("0");
                    su.setSort(0);
                    baseMapper.insert(su);
                    pid = su.getId();
                } else {
                    pid = subject.getId();
                }

                //10、再获取每一行的第二列
                Cell cell_1 = row.getCell(1);
                //11、获取第二列中的数据（二级分类）
                if(cell_1 == null){
                    meg.add("第" + rowNum + "行第2列为空");
                    continue;
                }
                String stringCellValue = cell_1.getStringCellValue();
                if(StringUtils.isEmpty(stringCellValue)){
                    meg.add("第" + rowNum + "行第2列为数据空");
                    continue;
                }
                //12、判断此一级分类中是否存在此二级分类
                EduSubject subject_1 = this.selectSubjectByNameAndParentId(stringCellValue,pid);
                //13、如果此一级分类中有此二级分类：不保存
                if(subject_1 == null){
                    EduSubject su = new EduSubject();
                    su.setTitle(stringCellValue);
                    su.setParentId(pid);
                    su.setSort(0);
                    baseMapper.insert(su);
                }
                //14、如果没有则保存
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return meg;
    }

    @Override
    public List<OneSubject> getTree() {
        //1、创建一个集合存放OneSubject
        List<OneSubject> oneSubjects = new ArrayList<>();
        //2、获取一级分类的列表
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        List<EduSubject> eduSubjectsList = baseMapper.selectList(wrapper);
        //3、遍历一级分类的列表
        for (EduSubject subject : eduSubjectsList) {
            //4、把一级分类对象复制到OneObject
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(subject, oneSubject);
            //5、根据每一个一级分类获取二级分类的列表
            QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", oneSubject.getId());
            List<EduSubject> eduSubjects = baseMapper.selectList(queryWrapper);
            //6、遍历二级分类列表
            for (EduSubject su: eduSubjects) {
                //7、把二级分类对象复制到TwoObject
                TwoSubject twoSubject = new TwoSubject();
                BeanUtils.copyProperties(su, twoSubject);
                //8、把TwoObject添加到OneObject的children集合属性中
                oneSubject.getChildren().add(twoSubject);
            }
            //9、把OneSubject添加到集合中
            oneSubjects.add(oneSubject);
        }

        return oneSubjects;
    }

    @Override
    public boolean deleteById(String id) {
        //根据ID查询数据库中是否存在此ID为ParentId（二级分类）
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<EduSubject> subjects = baseMapper.selectList(wrapper);
        if(subjects.size() != 0){
            return false;
        }
        int i = baseMapper.deleteById(id);
        return i == 1;
    }

    /**
     * 根据课程分类的名字和父类ID查询分类是否存在
     * @param stringCellValue
     * @param pid
     * @return
     */
    private EduSubject selectSubjectByNameAndParentId(String stringCellValue, String pid) {
        QueryWrapper<EduSubject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title", stringCellValue);
        subjectQueryWrapper.eq("parent_id", pid);
        EduSubject subject = baseMapper.selectOne(subjectQueryWrapper);
        return subject;
    }

    /**
     * 根据课程分类的名字查询分类是否存在
     * @param cellValue
     * @return
     */
    private EduSubject selectSubjectByName(String cellValue) {
        QueryWrapper<EduSubject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title", cellValue);
        subjectQueryWrapper.eq("parent_id", 0);
        EduSubject subject = baseMapper.selectOne(subjectQueryWrapper);
        return subject;
    }

}
