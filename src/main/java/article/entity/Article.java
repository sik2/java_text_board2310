package article.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private long id;
    private String title;
    private String content;
    private String userId;
}


