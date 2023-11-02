package org.example.article.repository;

import org.example.Container;
import org.example.article.entity.Article;
import org.example.db.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    private DBConnection dbConnection;

    public ArticleRepository () {
        dbConnection = Container.getDBconnection();
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
        List<Article> articleList = new ArrayList<>();

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

        dbConnection.delete(sb.toString());
    }

    public void modify(Article article, String title, String content) {
        int id = article.getId();

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("UPDATE article "));
        sb.append(String.format("SET title = '%s', ", title ));
        sb.append(String.format("content = '%s' ", content));
        sb.append(String.format("WHERE id = %s", id));

        dbConnection.update(sb.toString());
    }

    public Article getArticleFindById(long id) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM article "));
        sb.append(String.format("WHERE id = %s ", id));


        Map<String, Object> row = dbConnection.selectRow(sb.toString());

        Article article = new Article(row);

        return article;
    }

//    public Article getArticleFindById(long id) {
//        List<Article> articleList = this.getArticleListAll();
//
//        for (int i = 0; i < articleList.size(); i++) {
//            Article article = articleList.get(i);
//            if (article.getId() == id) {
//                return article;
//            }
//        }
//        return null;
//    }
}
