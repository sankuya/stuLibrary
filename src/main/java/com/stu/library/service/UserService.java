package com.stu.library.service;

import com.stu.library.dao.UserDaoImpl;
import com.stu.library.domain.User;

public class UserService {
    private static UserService userService = new UserService();

    public static UserService getInstance() {
        return userService;
    }

    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    /**
     * 修改用户信息
     *
     * @param userId
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public boolean editInfo(int userId, String userName, String oldPassword, String newPassword) {
        User user = userDao.queryUser(userId);

        if (user == null || !user.getPassword().equals(oldPassword)) return false;

        return userDao.updUser(userId, userName, newPassword);
    }


    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    public User getUserInfo(int userId) {
        return userDao.queryUser(userId);
    }

}
