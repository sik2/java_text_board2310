import article.controller.ArticleController;
import article.entity.Article;
import article.entity.Member;
import member.controller.MemberController;
import system.controller.SystemController;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Member loginedMember = null;
    Scanner sc = new Scanner(System.in);

    void run () {
        SystemController systemController = new SystemController();
        ArticleController articleController = new ArticleController(sc, loginedMember);
        MemberController memberController = new MemberController(sc, loginedMember);

        System.out.println("== 프로그램 시작 ==");
        memberController.init();
        while (true) {
            System.out.printf("명령어) ");
            String command = sc.nextLine().trim();

            switch (command) {
                case "종료":
                    systemController.exit();
                    return;
                case "회원가입":
                    memberController.join();
                    break;
                case "로그인":
                    memberController.login();
                    break;
                case "로그아웃":
                    memberController.logout();
                    break;
                case "등록":
                    articleController.write();
                    break;
                case "목록":
                    articleController.list();
                    break;
                case "삭제":
                    articleController.remove();
                    break;
                case "수정":
                    articleController.modify();
                    break;
            }
        }
        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }
}