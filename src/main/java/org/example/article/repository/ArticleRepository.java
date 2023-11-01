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
    private List<Article> articleList;
    private DBConnection dbConnection;

    public ArticleRepository () {
        dbConnection = Container.getDBconnection();
        articleList = new ArrayList<>();
    }

    public long create(String title, String content, int memberId, String regDate) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("INSERT INTO article "));
        sb.append(String.format("SET title = '%s', ", title));
        sb.append(String.format("content = '%s', ", content));
        sb.append(String.format("memberId = %s, ", memberId));
        sb.append(String.format("regDate = now(); "));

        int id = dbConnection.insert(sb.toString());

        return id;
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
        int id = article.getId();

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("DELETE FROM article "));
        sb.append(String.format("WHERE id = %s", id));

        System.out.println(dbConnection.delete(sb.toString()));
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
