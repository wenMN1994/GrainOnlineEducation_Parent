package com.grain.teacher.exception;

import com.baomidou.mybatisplus.extension.api.R;
import com.grain.common.result.Result;
import com.grain.common.result.ResultCode;
import com.grain.common.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/27 21:43
 * @description：统一异常处理类
 * @modified By：
 * @version: $
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //1、全局异常类型Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return Result.error().message("出错了");
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public Result error(BadSqlGrammarException e){
        e.printStackTrace();
        return Result.error().code(ResultCode.SQL_ERROR).message("SQL语法错误");
    }

    @ExceptionHandler(EduException.class)
    @ResponseBody
    public Result error(EduException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

}
