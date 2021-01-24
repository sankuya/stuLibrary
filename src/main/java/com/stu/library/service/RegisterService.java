package com.stu.library.service;

import com.stu.library.dao.UserDaoImpl;
import com.stu.library.domain.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterService {
    private static RegisterService registerService = new RegisterService();
    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    public static RegisterService getInstance() {
        return registerService;
    }


    /**
     * 用户注册
     *
     * @param userName
     * @param password
     * @param verificationCode
     * @return
     */
    public User userRegister(String userName, String password, String verificationCode) {

        try {
            if (password == null || password.equals("")) return null;
            if (verificationCode == null || verificationCode.equals("")) return null;
//            int id = Integer.parseInt(userId);
            //添加用户
            User user = userDao.queryUser(userName);
            if (user != null) return null;
            user = userDao.addUser(userName, password);
            if (user == null) return null;

            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
