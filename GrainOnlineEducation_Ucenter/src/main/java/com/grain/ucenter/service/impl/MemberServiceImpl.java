package com.grain.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grain.ucenter.entity.Member;
import com.grain.ucenter.mapper.MemberMapper;
import com.grain.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Dragon Wen
 * @since 2020-03-09
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public Integer registerCountNum(String day) {
        return baseMapper.countRegisterNum(day);
    }

    @Override
    public Member getByOpenid(String openid) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        Member member = baseMapper.selectOne(queryWrapper);
        return member;
    }
}
