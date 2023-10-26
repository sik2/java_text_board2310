package article.controller;

import article.entity.Article;
import article.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
    List<Article> articleList = new ArrayList<>();
    long lastId = 0;

    public void write(Scanner sc, Member loginedMember) {
        if (loginedMember == null) {
            System.out.println("로그인 후 등록이 가능합니다.");
            return;
        }


        lastId++;
        System.out.printf("제목: ");
        String title = sc.nextLine();
        System.out.printf("내용: ");
        String content = sc.nextLine();

        Article article = new Article(lastId, title, content, loginedMember.getUserId());
        articleList.add(article);

        System.out.println(lastId + "번 게시글이 등록되었습니다.");
    }

    public void list(Scanner sc) {
        if (articleList.size() == 0) {
            System.out.println("게시물이 없습니다.");
        } else {
            System.out.printf("번호 / 제목 / 내용 / 작성자 \n");
            for (int i = 0; i < articleList.size(); i++) {
                Article article = articleList.get(i);
                System.out.printf("%d / %s / %s / %s \n", article.getId(), article.getTitle(), article.getContent(), article.getUserId());
            }
        }
    }

    public void remove(Scanner sc, Member loginedMember) {
        System.out.printf("삭제 번호) ");
        long id = Long.parseLong(sc.nextLine());

        // aticleList 입력받은 id 값이랑 같은 id 를 가지고있는 article 객체 찾기
        Article article = this.getArticleFindById(id);

        // 작성자만 작성할 수 있도록
        // 게시글 존재하지 않을 경우
        if (article == null) {
            System.out.println("게시글이 존재하지 않습니다.");
            return;
        }

        articleList.remove(article);
    }

    public void modify(Scanner sc, Member loginedMember) {
        System.out.printf("수정 번호) ");
        long id = Long.parseLong(sc.nextLine());

        // aticleList 입력받은 id 값이랑 같은 id 를 가지고있는 article 객체 찾기
        Article article = this.getArticleFindById(id);

        if (article == null) {
            System.out.println("게시글이 존재하지 않습니다.");
            return;
        }

        System.out.printf("기존 제목: %s\n", article.getTitle());
        String title = sc.nextLine();
        article.setTitle(title);

        System.out.printf("기존 내용: %s\n", article.getContent());
        String content = sc.nextLine();
        article.setContent(content);

        System.out.println(id + "번째 게시글이 수정 되었습니다.");
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
}
