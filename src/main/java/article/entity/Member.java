package article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    long id;
    String userId;
    String password;
    String regDate;

    public Member(String userId, String password, String regDate) {
        this.userId = userId;
        this.password = password;
        this.regDate = regDate;
    }
}
