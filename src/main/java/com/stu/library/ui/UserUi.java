package com.stu.library.ui;

import com.stu.library.StartApplication;

import javax.swing.*;
import java.awt.*;

public class UserUi {
    public static final int WELCOME_CARD = 0;
    public static final int ALL_BOOK_CARD = 1;
    public static final int REMIND_CARD = 2;
    public static final int RECORD_CARD = 3;
    public static final int INFO_CARD = 4;
    public static final int MANAGER_CARD = 5;

    private int userId;
    JFrame frame = new JFrame();

    public UserUi(int id, int defaultCard) {
        userId = id;

        //初始化窗体frame
        initFrame();

        //初始化菜单
        initMenu();

        //初始化左边点击的导航栏
        initNavigation(defaultCard);

        frame.setVisible(true);
    }

    /**
     * 初始化frame
     */
    private void initFrame() {
        frame.setTitle("图书馆系统");
        frame.setResizable(false);
        Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((sc.width - 900) / 2, (sc.height - 500) / 2, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 初始化菜单
     */
    private void initMenu() {
        //初始化菜单的刷新退出
        JButton refreshButton = new JButton("刷新");
        JButton exitButton = new JButton("退出");

        //刷新按钮的点击监听
        refreshButton.addActionListener((e) -> {
            new UserUi(userId, UserUi.ALL_BOOK_CARD);
            frame.dispose();
        });

        //退出按钮的点击监听
        exitButton.addActionListener((e) -> {
            StartApplication.run();
            frame.dispose();
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(refreshButton);
        menuBar.add(exitButton);
        frame.setJMenuBar(menuBar);
    }

    /**
     * 初始化左边的导航栏
     *
     * @param defaultCard
     */
    private void initNavigation(int defaultCard) {
        // 点击栏位置
        JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPanel.setFont(LoginUi.MIDDLE_FONT);
        tabbedPanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // 设置索引0的面板不可用
        JLabel welcomeLabel = new JLabel();
        tabbedPanel.addTab("欢迎用户使用", welcomeLabel);
        tabbedPanel.setEnabledAt(WELCOME_CARD, false);

        tabbedPanel.addTab("1.所有书籍", new AllBookPanel());
        tabbedPanel.addTab("2.查找书籍", new ResearchPanel());
        tabbedPanel.addTab("3.借还记录", new RecordPanel());
        tabbedPanel.addTab("4.个人信息", new InfoPanel(userId));

        // 设置默认选中的
        tabbedPanel.setSelectedIndex(defaultCard);
        ((MyShowPanel) tabbedPanel.getSelectedComponent()).init();

        //设置监听
        tabbedPanel.addChangeListener((e) -> {
            MyShowPanel showPanel = (MyShowPanel) tabbedPanel.getSelectedComponent();
            showPanel.init();
        });

        frame.getContentPane().add(tabbedPanel);
    }


}