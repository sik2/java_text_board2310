import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    void run () {
        System.out.println("== 프로그램 시작 ==");

        long lastId = 0;
        List<Article> articleList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();
        Member loginedMember = null;
        Scanner sc = new Scanner(System.in);
        LocalDate now2 = LocalDate.now();

        Member member1 = new Member(1, "user1", "1234", now2.toString());
        memberList.add(member1);
        Member member2 = new Member(2, "user2", "1234", now2.toString());
        memberList.add(member2);
        Member member3 = new Member(3, "user3", "1234", now2.toString());
        memberList.add(member3);

        while (true) {
            System.out.printf("명령어) ");
            String command = sc.nextLine().trim();

            if (command.equals("종료")) {
                System.out.println("시스템을 종료합니다.");
                break;
            } else if (command.equals("회원가입")) {
                String userId;
                String password;
                String passwordConfirm;

                while (true) {
                    System.out.printf("아이디) ");
                    userId = sc.nextLine();
                    boolean duplicatedUserId = false;

                    for (int i = 0; i < memberList.size(); i++) {
                        if (memberList.get(i).getUserId().equals(userId)) {
                            duplicatedUserId = true;
                        }
                    }

                    if (duplicatedUserId) {
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

                LocalDate now = LocalDate.now();

                Member member = new Member(userId, password, now.toString());
                memberList.add(member);

                System.out.println(userId + "님 회원가입이 완료되었습니다.");
            } else if (command.equals("로그인")) {
                if (loginedMember != null) {
                    System.out.println("로그인이 되어 있습니다.");
                    continue;
                }

                boolean checkedUserId = false;
                Member member = null;

                System.out.printf("아이디) ");
                String userId = sc.nextLine();
                System.out.printf("비번) ");
                String password = sc.nextLine();

                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getUserId().equals(userId)) {
                        member = memberList.get(i);
                        checkedUserId = true;
                        break;
                    }
                }

                if (checkedUserId == false) {
                    System.out.println("해당 회원이 존재하지 않습니다.");
                    continue;
                }

                if (member.getPassword().equals(password) == false) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    continue;
                }

                loginedMember = member;

                System.out.println("로그인 성공! " + loginedMember.getUserId() +" 님 환영합니다.");
            } else if (command.equals("로그아웃")) {

                if (loginedMember == null) {
                    System.out.println("로그인 상태가 아닙니다.");
                    continue;
                }

                loginedMember = null;
                System.out.println("로그아웃 처리가 되었습니다.");
            } else if (command.equals("등록")) {

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
            } else if (command.equals("목록")) {
                if (articleList.size() == 0) {
                    System.out.println("게시물이 없습니다.");
                } else {
                    System.out.printf("번호 / 제목 / 내용 / 작성자 \n");
                    for (int i = 0; i < articleList.size(); i++) {
                        Article article = articleList.get(i);
                        System.out.printf("%d / %s / %s / %s \n", article.getId(), article.getTitle(), article.getContent(), article.getUserId());
                    }
                }
            } else if (command.equals("삭제")) {
                System.out.printf("삭제 번호) ");
                long id = Long.parseLong(sc.nextLine());

                long foundIndex = -1;

                boolean checkedAuthor = false;

                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    if (article.getId() == id) {
                        foundIndex = id;
                        if (article.getUserId() == loginedMember.userId) {
                            checkedAuthor = true;
                        }
                        articleList.remove(article);
                        break;
                    }
                }

                if (checkedAuthor) {
                    System.out.println("작성자만 삭제할 수 있습니다.");
                    continue;
                }

                if (foundIndex == -1) {
                    System.out.println(id + "번째 게시물은 존재하지 않습니다.");
                } else {
                    System.out.println(id + "번째 게시글이 삭제 되었습니다.");
                }
            } else if (command.equals("수정")) {
                System.out.printf("수정 번호) ");
                long id = Long.parseLong(sc.nextLine());

                long foundIndex = -1;

                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    if (article.getId() == id) {
                        foundIndex = id;

                        System.out.printf("기존 제목: %s\n", article.getTitle());
                        String title = sc.nextLine();
                        article.setTitle(title);

                        System.out.printf("기존 내용: %s\n", article.getContent());
                        String content = sc.nextLine();
                        article.setContent(content);

                        break;
                    }
                }

                if (foundIndex == -1) {
                    System.out.println(id + "번째 게시물은 존재하지 않습니다.");
                } else {
                    System.out.println(id + "번째 게시글이 수정 되었습니다.");
                }
            }
        }

        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }
}
