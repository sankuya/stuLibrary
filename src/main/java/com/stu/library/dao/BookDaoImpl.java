package com.stu.library.dao;

import com.stu.library.domain.Book;
import com.stu.library.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl {

    private static BookDaoImpl bookDao = new BookDaoImpl();

    public static BookDaoImpl getInstance() {
        return bookDao;
    }

    /**
     * 查询所有书籍
     *
     * @return
     */
    public List<Book> queryAllBooks() {
        String sql = "select * from Book";
        Connection conn = JdbcUtil.getConn();
        ResultSet rs = null;
        List<Book> list = new ArrayList<Book>();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            rs = psts.executeQuery(); // 执行
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("name");
                int count = rs.getInt("count");
                String type = rs.getString("type");
                String author = rs.getString("author");
                int discount = rs.getInt("discount");
                int hasLended = rs.getInt("lend_num");
                String address = rs.getString("address");
                Book book = new Book(id, name, count, type, author, discount, hasLended, address);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, psts, rs);
        }
        return list;
    }


    /**
     * 按名字查询书籍
     *
     * @param name
     * @return
     */
    public Book queryBookByName(String name) {
        String sql = "select * from Book where name = ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement psts = null;
        ResultSet rs = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, name);
            rs = psts.executeQuery(); // 执行
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("name"));
                book.setCount(rs.getInt("count"));
                book.setType(rs.getString("type"));
                book.setAuthor(rs.getString("author"));
                book.setDiscount(rs.getInt("discount"));
                book.setLendNum(rs.getInt("lend_num"));
                book.setAddress(rs.getString("address"));
                return book;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            JdbcUtil.close(conn, psts, rs);
        }
    }

    /**
     * 按id查询书籍
     *
     * @param bookId
     * @return
     */
    public Book queryBookById(int bookId) {
        String sql = "select * from Book where id= ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement psts = null;
        ResultSet rs = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, bookId);
            rs = psts.executeQuery(); // 执行
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("name"));
                book.setCount(rs.getInt("count"));
                book.setType(rs.getString("type"));
                book.setAuthor(rs.getString("author"));
                book.setDiscount(rs.getInt("discount"));
                book.setLendNum(rs.getInt("lend_num"));
                book.setAddress(rs.getString("address"));
                return book;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            JdbcUtil.close(conn, psts, rs);
        }
    }

}
