package org.example.db;


import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public void connect () {
        String url = "jdbc:mysql://localhost:3306/proj1?serverTimezone=Asia/Seoul";

        String user = "root";
        String password = "";
        try {
            DriverManager.getConnection(url, user, password);
            System.out.println("연결성공");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 }
