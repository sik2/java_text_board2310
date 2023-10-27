package org.example;

import org.example.article.controller.ArticleController;
import org.example.db.DBConnection;
import org.example.member.controller.MemberController;
import org.example.member.entity.Member;
import org.example.system.controller.SystemController;

public class App {
    public App () {
        DBConnection.DB_NAME = "proj1";
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";
        DBConnection.DB_PORT = 3306;

        Container.getDBconnection().connect();
    }

    void run () {
        SystemController systemController = new SystemController();
        ArticleController articleController = new ArticleController();
        MemberController memberController = new MemberController();

        System.out.println("== 프로그램 시작 ==");
        memberController.init();
        while (true) {
            System.out.printf("명령어) ");
            String command = Container.getScanner().nextLine().trim();

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
    }
}