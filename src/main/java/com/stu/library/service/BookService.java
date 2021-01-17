package com.stu.library.service;

import com.stu.library.dao.BookDaoImpl;
import com.stu.library.domain.Book;

import java.util.List;

public class BookService {
    private static BookService bookService = new BookService();
    private BookDaoImpl bookDao = BookDaoImpl.getInstance();

    public static BookService getInstance() {
        return bookService;
    }

    public static final String CAN = "可借";
    public static final String CANT = "无库存";

    /**
     * 查找书籍信息
     *
     * @param name
     * @return
     */
    public Book queryBookByName(String name) {
        return bookDao.queryBookByName(name);
    }

    /**
     * 查找所有的书籍
     *
     * @return
     */
    public Object[][] queryAllBooks() {
        List<Book> list = bookDao.queryAllBooks();
        Object[][] obj = new Object[list.size()][7];
        int i = 0;
        for (Book book : list) {
            obj[i][0] = book.getLendNum() < book.getCount() ? CAN : CANT;
            obj[i][1] = book.getName();
            obj[i][2] = book.getCount() - book.getLendNum();
            obj[i][3] = book.getType();
            obj[i][4] = book.getAuthor();
            obj[i][5] = book.getDiscount();
            obj[i][6] = book.getAddress();
            i++;
        }
        return obj;
    }
}
