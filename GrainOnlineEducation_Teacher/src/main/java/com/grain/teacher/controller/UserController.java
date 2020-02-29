package com.grain.teacher.controller;

import com.grain.common.result.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/29 10:40
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("info")
    public Result info(String token){
        return Result.ok()
                .data("roles","[\"admin\"]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
