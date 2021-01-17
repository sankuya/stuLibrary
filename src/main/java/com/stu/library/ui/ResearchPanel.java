package com.stu.library.ui;

import com.stu.library.domain.Book;
import com.stu.library.service.BookService;
import com.stu.library.util.ShowMessageUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/17 13:34
 * @Description:
 */
public class ResearchPanel extends MyShowPanel {
    private BookService bookService = BookService.getInstance();

    private JTextField bookNameInput;
    private JLabel resultLabel;

    @Override
    public void init() {
        this.removeAll();
        this.setLayout(null);

        //设置 “书名”
        JLabel bookLabel = new JLabel("书  名");
        bookLabel.setBounds(160, 50, 80, 30);
        bookLabel.setFont(LoginUi.MIDDLE_FONT);
        this.add(bookLabel);

        //设置书名输入框
        bookNameInput = new JTextField();
        bookNameInput.setBounds(240, 50, 200, 30);
        bookNameInput.setFont(LoginUi.MIDDLE_FONT);
        this.add(bookNameInput);

        //设置搜索按钮
        JButton searchButton = new JButton("查 找");
        searchButton.setBounds(450, 50, 80, 30);
        searchButton.setFont(LoginUi.SMALL_FONT);
        searchButton.addActionListener(new SearchButtonClickListener(this));
        this.add(searchButton);

        //设置显示搜索结果
        resultLabel = new JLabel();
        resultLabel.setBounds(170, 80, 450, 200);
        resultLabel.setFont(LoginUi.MIDDLE_FONT);
        this.add(resultLabel);

        this.setFont(LoginUi.MIDDLE_FONT);
    }

    /**
     * 点击搜索按钮之后的事件监听
     */
    class SearchButtonClickListener implements ActionListener {
        private JPanel panel;

        public SearchButtonClickListener(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = bookNameInput.getText();
            if (name.equals("")) {
                ShowMessageUtil.winMessage("您还没有输入书名！");
                return;
            }

            //调用查询接口
            Book book = bookService.queryBookByName(name);

            if (book == null || book.getId() <= 0) {
                ShowMessageUtil.winMessage("书籍不存在!");
            } else {
                resultLabel.setText(buildBookHtml(book));
            }
        }
    }

    /**
     * 返回书籍信息的html代码
     *
     * @param book
     * @return
     */
    private String buildBookHtml(Book book) {
        String template = "<html> 状态：%s <br>书名：%s <br>作者：%s <br>类型：%s" +
                "<br>库存剩余：%s <br>借阅总次：%s <br>藏书位置：%s </div></html>";
        String status = book.getCount() <= 0 ? "无库存" : "可借";

        return String.format(template, status, book.getName(), book.getAuthor(), book.getType()
                , book.getCount() - book.getLendNum(), book.getDiscount(), book.getAddress());
    }

}
