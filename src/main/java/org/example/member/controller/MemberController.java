package org.example.member.controller;

import org.example.Container;
import org.example.member.entity.Member;
import org.example.member.service.MemberService;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class MemberController {

    MemberService memberService = new MemberService();

    public void login() {

        if (Container.getLoginedMember() != null) {
            System.out.println("로그인이 되어 있습니다.");
            return;
        }

        System.out.printf("아이디) ");
        String userId = Container.getScanner().nextLine();
        System.out.printf("비번) ");
        String password = Container.getScanner().nextLine();


        Member member = this.memberService.getMemberFindByUserId(userId);

        if (member == null) {
            System.out.println("해당 회원이 존재하지 않습니다.");
            return;
        }

        if (member.getPassword().equals(password) == false) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        Container.setLoginedMember(member);

        System.out.println("로그인 성공! " + Container.getLoginedMember().getUserId() + " 님 환영합니다.");
    }

    public void join() {
        String userId;
        String password;
        String passwordConfirm;

        while (true) {
            System.out.printf("아이디) ");
            userId = Container.getScanner().nextLine();

            Member member = this.memberService.getMemberFindByUserId(userId);

            if (member != null) {
                System.out.println("존재하는 아이디 입니다.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.printf("비번) ");
            password = Container.getScanner().nextLine();

            System.out.printf("비번 확인) ");
            passwordConfirm = Container.getScanner().nextLine();

            if (password.equals(passwordConfirm) == false) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
            break;
        }

        try {
            this.memberService.join(userId, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(userId + "님 회원가입이 완료되었습니다.");
    }

    public void logout() {
        if (Container.getLoginedMember() == null) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        Container.setLoginedMember(null);
        System.out.println("로그아웃 처리가 되었습니다.");
    }
}
