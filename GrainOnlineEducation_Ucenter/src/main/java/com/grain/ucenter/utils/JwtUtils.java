package com.grain.ucenter.utils;

import com.grain.ucenter.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/13 21:53
 * @description：JWT工具类
 * @modified By：
 * @version: $
 */
public class JwtUtils {

    //分类
    public static final String SUBJECT = "grain";

    //秘钥
    public static final String APPSECRET = "grain";

    //设置jwt生成字符串有效时间
    public static final long EXPIRE = 1000 * 60 * 30;  //过期时间，毫秒，30分钟

    /**
     * 根据对象，生成jwt字符串
     *
     * @param member
     * @return
     */
    public static String geneJsonWebToken(Member member) {

        if (member == null || StringUtils.isEmpty(member.getId())
                || StringUtils.isEmpty(member.getNickname())
                || StringUtils.isEmpty(member.getAvatar())) {
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", member.getId()) //向jwt设置主体部分数据
                .claim("nickname", member.getNickname())
                .claim("avatar", member.getAvatar())
                .setIssuedAt(new Date())//设置jwt字符串过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();//根据秘钥对字符串进行加密，加密方式使用hs256

        return token;
    }

    /**
     * 校验jwt token
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
        return claims;
    }

    public static void main(String[] afg) {
        Member member = new Member();
        member.setId("11");
        member.setNickname("lucymary");
        member.setAvatar("abcd");

        String token = geneJsonWebToken(member);
        // System.out.println(token);

        //根据生成jwt的token值获取数据
        Claims claims = checkJWT(token);
        String id = (String)claims.get("id");
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");

        System.out.println(id);
        System.out.println(nickname);
        System.out.println(avatar);
    }
}
