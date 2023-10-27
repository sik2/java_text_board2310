package org.example.article.controller;

import org.example.Container;
import org.example.article.entity.Article;
import org.example.article.service.ArticleService;
import org.example.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    long lastId = 0;

    ArticleService articleService = new ArticleService();

    public void write() {
        if (Container.getLoginedMember() == null) {
            System.out.println("로그인 후 등록이 가능합니다.");
            return;
        }

        lastId++;
        System.out.printf("제목: ");
        String title = Container.getScanner().nextLine();
        System.out.printf("내용: ");
        String content = Container.getScanner().nextLine();


        long id = articleService.create(lastId, title, content, Container.getLoginedMember().getUserId());

        System.out.println(id + "번 게시글이 등록되었습니다.");
    }

    public void list() {
        List<Article> articleList = articleService.getArticleListAll();

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

    public void remove() {
        System.out.printf("삭제 번호) ");
        long id = Long.parseLong(Container.getScanner().nextLine());

        Article article = this.articleService.remove(id);

        // 작성자만 작성할 수 있도록
        // 게시글 존재하지 않을 경우
        if (article == null) {
            System.out.println("게시글이 존재하지 않습니다.");
            return;
        }
    }

    public void modify() {
        System.out.printf("수정 번호) ");
        long id = Long.parseLong(Container.getScanner().nextLine());

        Article article =  this.articleService.modify(id);

        if (article == null) {
            System.out.println("게시글이 존재하지 않습니다.");
            return;
        }

        System.out.printf("기존 제목: %s\n", article.getTitle());
        String title = Container.getScanner().nextLine();
        article.setTitle(title);

        System.out.printf("기존 내용: %s\n", article.getContent());
        String content = Container.getScanner().nextLine();
        article.setContent(content);

        System.out.println(id + "번째 게시글이 수정 되었습니다.");
    }
}
