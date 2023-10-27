package org.example.member.service;

import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;

public class MemberService {
    MemberRepository memberRepository = new MemberRepository();

    public Member getMemberFindByUserId(String userId) {
        return this.memberRepository.getMemberFindByUserId(userId);
    }

    public void join(String userId, String password) {
        this.memberRepository.join(userId, password);
    }
}
