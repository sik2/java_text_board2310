package org.example.article.repository;

import org.example.Container;
import org.example.article.entity.Article;
import org.example.db.DBConnection;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArticleRepository {
    int lastId = 0;
    private  List<Article> articleList;
    private DBConnection dbConnection;

    public ArticleRepository () {
        dbConnection = Container.getDBconnection();
        articleList = new ArrayList<>();
    }

    public long create(String title, String content, int memberId, String regDate) {
        lastId++;
        Article article = new Article(lastId, title, content, memberId, regDate);
        articleList.add(article);

        return lastId;
    }

    public List<Article> getArticleListAll() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM article"));

        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            articleList.add(new Article(row));
        }

        return articleList;
    }

    public void remove(Article article) {
        articleList.remove(article);
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
    }

    public Article getArticleFindById(long id) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}
