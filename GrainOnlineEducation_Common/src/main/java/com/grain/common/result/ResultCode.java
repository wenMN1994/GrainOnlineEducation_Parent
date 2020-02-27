package com.grain.common.result;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/27 17:01
 * @description：返回结果状态码
 * @modified By：
 * @version: $
 */
public interface ResultCode {

    int OK = 20000;//成功
    int ERROR = 20001;//失败

    int SQL_ERROR = 20006;//sql错误

}
