package org.example.member.repository;

import org.example.Container;
import org.example.db.DBConnection;
import org.example.member.entity.Member;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    List<Member> memberList = new ArrayList<>();
    private DBConnection dbConnection;

    public MemberRepository () {
        dbConnection = Container.getDBconnection();
    }


    public Member getMemberFindByUserId(String userId) {

        String sql = "SELECT * FROM `member`";
        List<Map<String, Object>> rows = dbConnection.selectRows(sql);

        for (Map<String, Object> row : rows) {
            memberList.add(new Member(row));
        }

        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }

    public void join(String userId, String password) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("INSERT INTO `member` "));
        sb.append(String.format("SET regDate = now(), "));
        sb.append(String.format("userid = '%s', ", userId));
        sb.append(String.format("`password` = '%s' ", password));

        int id = dbConnection.insert(sb.toString());

        System.out.println(id + "번째 회원이 등록되었습니다.");
    }
}
