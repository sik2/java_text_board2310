package org.example.article.service;

import org.example.Container;
import org.example.article.entity.Article;
import org.example.article.repository.ArticleRepository;

import java.util.List;

public class ArticleService {

    ArticleRepository articleRepository = new ArticleRepository();

    public long create(String title, String content, String userId) {
        return this.articleRepository.create(title, content, userId);
    }

    public List<Article> getArticleListAll() {
        return this.articleRepository.getArticleListAll();
    }

    public void remove(Article article) {
        this.articleRepository.remove(article);
    }

    public void modify(Article article, String title, String content) {
        this.articleRepository.modify(article, title, content);
    }

    public Article getArticleFindById(long id) {
        return this.articleRepository.getArticleFindById(id);
    }
}