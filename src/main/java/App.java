import util.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    List<Article> articleList = new ArrayList<>();
    List<Member> memberList = new ArrayList<>();
    Member loginedMember = null;
    Scanner sc = new Scanner(System.in);

    void run () {
        System.out.println("== 프로그램 시작 ==");

        long lastId = 0;

        Member member1 = new Member(1, "user1", "1234",  Util.nowDateTime());
        memberList.add(member1);
        Member member2 = new Member(2, "user2", "1234",  Util.nowDateTime());
        memberList.add(member2);
        Member member3 = new Member(3, "user3", "1234",  Util.nowDateTime());
        memberList.add(member3);

        while (true) {
            System.out.printf("명령어) ");
            String command = sc.nextLine().trim();

            switch (command) {
                case "종료":
                    System.out.println("시스템을 종료합니다.");
                    return;
                case "회원가입":
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
                    break;
                case "로그인":
                    if (loginedMember != null) {
                        System.out.println("로그인이 되어 있습니다.");
                        continue;
                    }

                    System.out.printf("아이디) ");
                    String userId = sc.nextLine();
                    System.out.printf("비번) ");
                    String password = sc.nextLine();

                    Member member = this.getMemberFindByUserId(userId);

                    if (member == null) {
                        System.out.println("해당 회원이 존재하지 않습니다.");
                        continue;
                    }

                    if (member.getPassword().equals(password) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;
                    }

                    loginedMember = member;

                    System.out.println("로그인 성공! " + loginedMember.getUserId() +" 님 환영합니다.");
                    break;
                case "로그아웃":
                    if (loginedMember == null) {
                        System.out.println("로그인 상태가 아닙니다.");
                        continue;
                    }

                    loginedMember = null;
                    System.out.println("로그아웃 처리가 되었습니다.");
                    break;
                case "등록":
                    if (loginedMember == null) {
                        System.out.println("로그인 후 등록이 가능합니다.");
                        continue;
                    }


                    lastId++;
                    System.out.printf("제목: ");
                    String title = sc.nextLine();
                    System.out.printf("내용: ");
                    String content = sc.nextLine();

                    Article article = new Article(lastId, title, content, loginedMember.getUserId());
                    articleList.add(article);

                    System.out.println(lastId + "번 게시글이 등록되었습니다.");
                    break;
                case "목록":
                    if (articleList.size() == 0) {
                        System.out.println("게시물이 없습니다.");
                    } else {
                        System.out.printf("번호 / 제목 / 내용 / 작성자 \n");
                        for (int i = 0; i < articleList.size(); i++) {
                            Article article = articleList.get(i);
                            System.out.printf("%d / %s / %s / %s \n", article.getId(), article.getTitle(), article.getContent(), article.getUserId());
                        }
                    }
                    break;
                case "삭제":
                    System.out.printf("삭제 번호) ");
                    long id = Long.parseLong(sc.nextLine());

                    // aticleList 입력받은 id 값이랑 같은 id 를 가지고있는 article 객체 찾기
                    Article article = this.getArticleFindById(id);

                    // 작성자만 작성할 수 있도록
                    // 게시글 존재하지 않을 경우
                    if (article == null) {
                        System.out.println("게시글이 존재하지 않습니다.");
                        continue;
                    }

                    articleList.remove(article);
                    break;
                case "수정":
                    System.out.printf("수정 번호) ");
                    long id = Long.parseLong(sc.nextLine());

                    // aticleList 입력받은 id 값이랑 같은 id 를 가지고있는 article 객체 찾기
                    Article article = this.getArticleFindById(id);

                    if (article == null) {
                        System.out.println("게시글이 존재하지 않습니다.");
                        continue;
                    }

                    System.out.printf("기존 제목: %s\n", article.getTitle());
                    String title = sc.nextLine();
                    article.setTitle(title);

                    System.out.printf("기존 내용: %s\n", article.getContent());
                    String content = sc.nextLine();
                    article.setContent(content);

                    System.out.println(id + "번째 게시글이 수정 되었습니다.");
                    break;
            }
        }

        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }

    private Article getArticleFindById(long id) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
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