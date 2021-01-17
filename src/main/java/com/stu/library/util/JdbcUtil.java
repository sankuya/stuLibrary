package com.stu.library.util;

import java.sql.*;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/17 12:14
 * @Description:
 */
public class JdbcUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/stu_library";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName(DRIVER); // 加载驱动类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // 创建连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     *
     * @param conn
     * @param stat
     * @param rs
     */
    public static void close(Connection conn, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }
    }
}
