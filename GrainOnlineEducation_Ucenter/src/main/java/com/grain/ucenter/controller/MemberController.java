package com.grain.ucenter.controller;

import com.grain.common.result.Result;
import com.grain.ucenter.entity.Member;
import com.grain.ucenter.service.MemberService;
import com.grain.ucenter.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-09
 */
@CrossOrigin
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "今日注册数")
    @GetMapping(value = "count-register/{day}")
    public Result registerCount(
        @ApiParam(name = "day", value = "统计日期", required = true)
        @PathVariable String day){
        Integer count = memberService.registerCountNum(day);
        return Result.ok().data("countRegister", count);
    }

    //根据token信息jwt的令牌，获取用户信息返回
    @ApiOperation(value = "根据token信息jwt的令牌，获取用户信息返回")
    @GetMapping("userInfo/{token}")
    public Result getUserInfoToken(@PathVariable String token) {
        //调用工具类获取用户信息
        Claims claims = JwtUtils.checkJWT(token);
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");
        String id = (String)claims.get("id");

        Member member = new Member();
        member.setId(id);
        member.setId(avatar);
        member.setNickname(nickname);

        return Result.ok().data("loginInfo",member);
    }
}

