package com.grain.teacher.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/6 9:41
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class OneChapter {

    private String id;

    private String title;

    private List<TwoVideo> children = new ArrayList<>();

}
