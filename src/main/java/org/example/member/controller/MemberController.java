package org.example.member.controller;

import org.example.article.entity.Member;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    List<Member> memberList = new ArrayList<>();

    Scanner sc;
    Member loginedMember;

    public MemberController(Scanner sc, Member loginedMember) {
        this.sc= sc;
        this.loginedMember = loginedMember;
    }

    public void init() {
        Member member1 = new Member(1, "user1", "1234",  Util.nowDateTime());
        memberList.add(member1);
        Member member2 = new Member(2, "user2", "1234",  Util.nowDateTime());
        memberList.add(member2);
        Member member3 = new Member(3, "user3", "1234",  Util.nowDateTime());
        memberList.add(member3);
    }

    public void login() {

        if (loginedMember != null) {
            System.out.println("로그인이 되어 있습니다.");
            return;
        }

        System.out.printf("아이디) ");
        String userId = sc.nextLine();
        System.out.printf("비번) ");
        String password = sc.nextLine();

        Member member = this.getMemberFindByUserId(userId);

        if (member == null) {
            System.out.println("해당 회원이 존재하지 않습니다.");
            return;
        }

        if (member.getPassword().equals(password) == false) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        loginedMember = member;

        System.out.println("로그인 성공! " + loginedMember.getUserId() + " 님 환영합니다.");


    }

    public void join() {
        String userId;
        String password;
        String passwordConfirm;

        while (true) {
            System.out.printf("아이디) ");
            userId = sc.nextLine();

            Member member = this.getMemberFindByUserId(userId);

            if (member != null) {
                System.out.println("존재하는 아이디 입니다.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.printf("비번) ");
            password = sc.nextLine();

            System.out.printf("비번 확인) ");
            passwordConfirm = sc.nextLine();

            if (password.equals(passwordConfirm) == false) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
            break;
        }

        Member member = new Member(userId, password, Util.nowDateTime());
        memberList.add(member);

        System.out.println(userId + "님 회원가입이 완료되었습니다.");
    }

    public void logout() {
        if (loginedMember == null) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        loginedMember = null;
        System.out.println("로그아웃 처리가 되었습니다.");
    }

    private Member getMemberFindByUserId(String userId) {
        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }


}
