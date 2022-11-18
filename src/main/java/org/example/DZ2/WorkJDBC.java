package org.example.DZ2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class WorkJDBC {

    public static void main(String[] args) throws SQLException {
        Statement statement = openConnection();
        statement.execute(readSQL("DZ2/dropTables.sql"));
        statement.execute(readSQL("DZ2/createTables.sql"));
        statement.executeUpdate(readSQL("DZ2/fillTables.sql"));

        System.out.println(getStats());
        System.out.println(userInfo(3));
    }

    public static String readSQL(String file) {
        InputStream stream = WorkJDBC.class.getClassLoader().getResourceAsStream(file);
        return new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining(""));
    }

    public static String userInfo(int userId) throws SQLException {
        Statement statement = openConnection();

        String name = "";
        ResultSet set = statement.executeQuery(String.format("SELECT \"name\" FROM \"user\" Where ID = %d", userId));
        while (set.next()) {
            name = set.getString(1);
        }

        String date = "";
        ResultSet setDate = statement.executeQuery(String.format("SELECT TO_CHAR (created_at, 'DD-MM-YYYY HH24:MI:SS') FROM \"user\" WHERE ID = %d", userId));
        while (setDate.next()) {
            date = setDate.getString(1);
        }

        String post = "";
        ResultSet setPost = statement.executeQuery(String.format("SELECT TO_CHAR (MIN(created_at), 'DD-MM-YYYY HH24:MI:SS') FROM post WHERE user_id = %d", userId));
        while (setPost.next()) {
            post = setPost.getString(1);
        }

        String comments = "";
        ResultSet setComm = statement.executeQuery(String.format("SELECT COUNT(id) FROM \"comment\" where USER_ID = %d", userId));
        while (setComm.next()) {
            comments = setComm.getString(1);
        }
        StringBuilder builder = new StringBuilder();
        return name.equals("") ? "Пользователь не найден" :
                builder
                        .append("---ИНФО ПО ПОЛЬЗОВАТЕЛЮ ")
                        .append(userId)
                        .append("------\n")
                        .append("Пользователь -  ")
                        .append(name)
                        .append("\n")
                        .append("Дата создания - ")
                        .append(date)
                        .append("\n")
                        .append("Самый первый пост - ")
                        .append(post)
                        .append("\n")
                        .append("Количество комментов - ")
                        .append(comments)
                        .toString();
    }

    public static Statement openConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "sql@java";
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection.createStatement();
    }

    public static String getStats() throws SQLException {
        Statement statement = openConnection();
        ResultSet set = statement.executeQuery(readSQL("DZ2/showStats.sql"));
        List<String> stats = new ArrayList<>(List.of("0", "0", "0", "0"));
        int count = 0;

        while (set.next()) {
            stats.set(count, set.getString(1));
            count++;
        }

        StringBuilder builder = new StringBuilder();
        return builder
                .append("----СТАТИСТИКА----")
                .append("\n")
                .append("количество пользователей - ")
                .append(stats.get(0))
                .append("\n")
                .append("количество постов - ")
                .append(stats.get(1))
                .append("\n")
                .append("количество комментариев - ")
                .append(stats.get(2))
                .append("\n")
                .append("количество лайков  - ")
                .append(stats.get(3))
                .toString();
    }
}