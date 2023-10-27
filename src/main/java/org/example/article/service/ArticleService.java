package org.example.article.service;

import org.example.Container;
import org.example.article.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    List<Article> articleList = new ArrayList<>();


    public long create(long lastId, String title, String content, String userId) {
        Article article = new Article(lastId, title, content, Container.getLoginedMember().getUserId());
        articleList.add(article);

        return lastId;
    }

    public List<Article> getArticleListAll() {
        return articleList;
    }

    public Article remove(long id) {
        return this.getArticleFindById(id);
    }

    public Article modify(long id) {
        return this.getArticleFindById(id);
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
