package com.stu.library;

import com.stu.library.ui.LoginUi;

public class StartApplication {
    /**
     * 整个程序开始的地方main方法
     *
     * @param args
     */
    public static void main(String args[]) {
        run();
    }

    public static void run() {
        new LoginUi();
    }
}
