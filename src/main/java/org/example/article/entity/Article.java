package org.example.article.entity;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private long id;
    private String title;
    private String content;
    private String userId;

    public Article (Map<String, Object> row) {
        this.id = 1; // 테스트 값
        this.title = (String) row.get("title");
        this.content = (String) row.get("content");
        this.userId = "tester"; // 테스트 값
    }
}


