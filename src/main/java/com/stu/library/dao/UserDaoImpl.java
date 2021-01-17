package com.stu.library.dao;

import com.stu.library.domain.User;
import com.stu.library.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {

    private static UserDaoImpl userDaoImpl;

    static {
        userDaoImpl = new UserDaoImpl();
    }

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return userDaoImpl;
    }


    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    public User queryUser(int id) {
        String sql = "select * from User where id = ?";
        Connection conn = JdbcUtil.getConn();
        ResultSet rs = null;
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, id);
            rs = psts.executeQuery();
            if (rs.next()) {
                int return_id = rs.getInt("id");
                String name = rs.getString("name");
                String return_password = rs.getString("password");
                int point = rs.getInt("point");
                return new User(return_id, name, return_password, point);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            JdbcUtil.close(conn, psts, rs);
        }
    }

    /**
     * 修改用户
     *
     * @param id
     * @param name
     * @return
     */
    public boolean updUser(int id, String name, String newPassword) {
        String sql = "update User set name=?,password=? where id=?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setObject(1, name);
            statement.setObject(2, newPassword);
            statement.setObject(3, id);
            int res = statement.executeUpdate();
            if (res > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, statement, null);
        }
        return false;
    }
}