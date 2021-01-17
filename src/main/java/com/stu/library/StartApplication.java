package com.stu.library;

import com.stu.library.ui.LoginUi;
import com.stu.library.ui.UserUi;

public class StartApplication {
    public static LoginUi login;

    /**
     * 整个程序开始的地方main方法
     *
     * @param args
     */
    public static void main(String args[]) {
        run();
    }

    public static void run() {
//        new LoginUi();
        new UserUi(1, UserUi.ALL_BOOK_CARD);
    }
}
