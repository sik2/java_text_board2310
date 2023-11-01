package org.example.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    int id;
    String userId;
    String password;
    String regDate;

    public Member(Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.userId = (String)row.get("userId");
        this.password = (String)row.get("password");
        this.regDate = (String)row.get("regDate");
    }

    public Member(String userId, String password, String regDate) {
        this.userId = userId;
        this.password = password;
        this.regDate = regDate;
    }
}
