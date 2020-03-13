package com.grain.ucenter.service;

import com.grain.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-09
 */
public interface MemberService extends IService<Member> {

    /**
     * 统计注册人数
     * @param day
     * @return
     */
    public Integer registerCountNum(String day);

    /**
     * 获取微信Openid
     * @param openid
     * @return
     */
    Member getByOpenid(String openid);
}
