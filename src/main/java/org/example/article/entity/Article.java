package org.example.article.entity;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private int id;
    private String title;
    private String content;
    private int memberId;
    private String regDate;

    public Article (Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.title = (String) row.get("title");
        this.content = (String) row.get("content");
        this.memberId = (int)row.get("memberId");
        this.regDate = (String)row.get("regDate");
    }
}


