package com.stu.library.service;

import com.stu.library.dao.UserDaoImpl;
import com.stu.library.domain.User;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private static LoginService loginService = new LoginService();
    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    //保存验证码信息
    Map<String, String> verificationMap = new HashMap<>();

    public static LoginService getInstance() {
        return loginService;
    }


    /**
     * 用户登录接口
     *
     * @param userId           用户名
     * @param password         用户密码
     * @param verificationCode 验证码
     * @return
     */
    public User userLogin(String userId, String password, String verificationCode) {
        try {
            if (password == null || password.equals("")) return null;
            if (verificationCode == null || verificationCode.equals("")) return null;
            int id = Integer.parseInt(userId);

            //校验验证码
            String serverVerificationCode = verificationMap.get(userId);
            if (!verificationCode.equals(serverVerificationCode)) return null;

            //校验密码
            User user = userDao.queryUser(id);
            if (user == null || !user.getPassword().equals(password)) return null;

            return user;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成验证码接口
     *
     * @param userName
     * @return
     */
    public String getCode(String userName) {
        String verificationCode = String.valueOf((int) (Math.random() * 90000) + 10000);

        // 保存验证码在系统中
        verificationMap.put(userName, verificationCode);

        return verificationCode;
    }


}
