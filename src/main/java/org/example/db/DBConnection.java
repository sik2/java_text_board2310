package org.example.db;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {
    public void connect () {
        String url = "jdbc:mysql://localhost:3306/proj1?serverTimezone=Asia/Seoul";

        String user = "root";
        String password = "";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("연결성공");

            String query = "SELECT * FROM article";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            ResultSetMetaData metaData = result.getMetaData();
            int columnSize = metaData.getColumnCount();

            while (result.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                    String columnName = metaData.getColumnName(columnIndex + 1);
                    Object value = result.getObject(columnName);

                    if (value instanceof Long) {
                        int numValue = (int) (long) value;
                        row.put(columnName, numValue);
                    } else if (value instanceof Timestamp) {
                        String dateValue = value.toString();
                        dateValue = dateValue.substring(0, dateValue.length() - 2);
                        row.put(columnName, dateValue);
                    } else {
                        row.put(columnName, value);
                    }
                }
                System.out.println(row);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 }
