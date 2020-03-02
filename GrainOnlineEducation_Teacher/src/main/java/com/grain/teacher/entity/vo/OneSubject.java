package com.grain.teacher.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/2 14:16
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubject> children = new ArrayList<TwoSubject>();

}
