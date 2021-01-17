package com.stu.library.ui;

import com.stu.library.service.BookService;

import javax.swing.*;
import java.awt.*;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/17 13:17
 * @Description: 全部书籍的panel
 */
public class AllBookPanel extends MyShowPanel {
    private final BookService bookService = BookService.getInstance();

    public static final String[] bookTableHead = {"状态", "书 名", "剩余库存", "类型", "作者", "借阅总次", "所在位置"};

    @Override
    public void init() {
        this.removeAll();

        //调用查询所有书籍接口
        Object[][] allBooks = bookService.queryAllBooks();

        JTable bookTable = new JTable(allBooks, bookTableHead);
        bookTable.setFont(LoginUi.MIDDLE_FONT);
        bookTable.setPreferredSize(new Dimension(740, 460));
        bookTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        bookTable.getTableHeader().setFont(LoginUi.SMALL_FONT);
        bookTable.setRowHeight(30);
        bookTable.setEnabled(false);
        JScrollPane scroll = new JScrollPane(bookTable);
        scroll.setPreferredSize(new Dimension(740, 430));
        this.add(scroll);
    }
}
