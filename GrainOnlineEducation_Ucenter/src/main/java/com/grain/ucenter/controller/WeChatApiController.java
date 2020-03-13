package com.grain.ucenter.controller;

import com.google.gson.Gson;
import com.grain.common.exception.EduException;
import com.grain.ucenter.entity.Member;
import com.grain.ucenter.service.MemberService;
import com.grain.ucenter.utils.HttpClientUtils;
import com.grain.ucenter.utils.WeChatConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/13 11:33
 * @description：
 * @modified By：
 * @version: $
 */
@Api("微信API调用")
@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WeChatApiController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("生成授权二维码")
    @GetMapping("login")
    public String genQrConnect(HttpSession session) {

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "#wechat_redirect";

        // 回调地址
        String redirectUrl = WeChatConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (Exception e) {

        }

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                WeChatConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl);

        return "redirect:" + qrcodeUrl;
    }

    /**
     * 1、获取回调参数
     * 2、从redis中读取state进行比对，异常则拒绝调用
     * 3、向微信的授权服务器发起请求，使用临时票据换取access token
     * 4、使用上一步获取的openid查询数据库，判断当前用户是否已注册，如果已注册则直接进行登录操作
     * 5、如果未注册，则使用openid和access token向微信的资源服务器发起请求，请求获取微信的用户信息
     *   5.1、将获取到的用户信息存入数据库
     *   5.2、然后进行登录操作
     *
     * @param code
     * @param state
     * @return
     */
    @ApiOperation("回调URL")
    @GetMapping("callback")
    public String callback(String code, String state){

        //得到授权临时票据code
        System.out.println(code);
        System.out.println(state);

        //从redis中将state获取出来，和当前传入的state作比较
        //如果一致则放行，如果不一致则抛出异常：非法访问

        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String accessTokenUrl = String.format(baseAccessTokenUrl,
                WeChatConstantPropertiesUtil.WX_OPEN_APP_ID,
                WeChatConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code);

        String result = null;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessToken=============" + result);
        } catch (Exception e) {
            throw new EduException(20001, "获取access_token失败");
        }

        //解析json字符串
        Gson gson = new Gson();
        HashMap map = gson.fromJson(result, HashMap.class);
        String accessToken = (String)map.get("access_token");
        String openid = (String)map.get("openid");

        //查询数据库当前用用户是否曾经使用过微信登录
        Member member = memberService.getByOpenid(openid);
        if(member == null){
            System.out.println("新用户注册");

            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            String resultUserInfo = null;
            try {
                resultUserInfo = HttpClientUtils.get(userInfoUrl);
                System.out.println("resultUserInfo==========" + resultUserInfo);
            } catch (Exception e) {
                throw new EduException(20001, "获取用户信息失败");
            }

            //解析json
            HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
            String nickname = (String)mapUserInfo.get("nickname");
            String headimgurl = (String)mapUserInfo.get("headimgurl");

            //向数据库中插入一条记录
            member = new Member();
            member.setNickname(nickname);
            member.setOpenid(openid);
            member.setAvatar(headimgurl);
            memberService.save(member);
        }

        //TODO 登录

        return "redirect:http://localhost:3000";
    }
}
