package com.stu.library;

import com.stu.library.dao.UserDaoImpl;
import com.stu.library.domain.User;
import com.stu.library.util.JdbcUtil;

import java.sql.*;

public class Test {
    public static void main(String args[]) {
        String name = "h";
        String password = "h";
        String sql = "insert into user (name,password,point) values (?,?,?);";
        PreparedStatement statement = null;
        try (Connection conn = JdbcUtil.getConn()) {
            statement = conn.prepareStatement(sql);
            statement.setObject(1, name);
            statement.setObject(2, password);
            statement.setObject(3, 0);
            if (statement.executeUpdate() > 0) {
                sql = "SELECT LAST_INSERT_ID();";
                statement = conn.prepareStatement(sql);
                ResultSet res;
                res = statement.executeQuery();
                if (res.next()) {
                    int return_id = res.getInt("LAST_INSERT_ID()");
                    System.out.println(new User(return_id, name, password, 0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
