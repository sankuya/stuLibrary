package com.stu.library.ui;

import com.stu.library.domain.User;
import com.stu.library.service.UserService;
import com.stu.library.util.ShowMessageUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/17 13:39
 * @Description:
 */
public class InfoPanel extends MyShowPanel {
    private final int userId;
    private final UserService userService = UserService.getInstance();

    private final JLabel userNameLabel = new JLabel("用户名");
    private final JTextField userNameInput = new JTextField();
    private final JLabel oldPasswordLabel = new JLabel("旧密码");
    private final JPasswordField oldPasswordInput = new JPasswordField();
    private final JLabel newPasswordLabel = new JLabel("新密码");
    private final JPasswordField newPasswordInput = new JPasswordField();
    private final JButton submitButton = new JButton("确 定");
    private final JLabel infoLabel = new JLabel("");

    public InfoPanel(int userId) {
        this.userId = userId;
    }

    @Override
    public void init() {
        this.removeAll();
        this.setLayout(null);
        
        //设置用户名和输入
        userNameLabel.setBounds(160, 230, 60, 30);
        userNameLabel.setFont(LoginUi.SMALL_FONT);
        userNameInput.setBounds(220, 230, 200, 30);
        userNameInput.setFont(LoginUi.SMALL_FONT);
        this.add(userNameLabel);
        this.add(userNameInput);

        //设置旧密码和输入
        oldPasswordLabel.setBounds(160, 280, 60, 30);
        oldPasswordLabel.setFont(LoginUi.SMALL_FONT);
        oldPasswordInput.setBounds(220, 280, 200, 30);
        oldPasswordInput.setFont(LoginUi.SMALL_FONT);
        oldPasswordInput.setEchoChar('*');
        this.add(oldPasswordLabel);
        this.add(oldPasswordInput);

        //设置新密码和输入
        newPasswordLabel.setBounds(160, 330, 60, 30);
        newPasswordLabel.setFont(LoginUi.SMALL_FONT);
        newPasswordInput.setBounds(220, 330, 200, 30);
        newPasswordInput.setFont(LoginUi.SMALL_FONT);
        newPasswordInput.setEchoChar('*');
        this.add(newPasswordLabel);
        this.add(newPasswordInput);

        //设置提交按钮
        submitButton.setBounds(230, 380, 90, 30);
        submitButton.setFont(LoginUi.MIDDLE_FONT);
        submitButton.addActionListener(new EditInfoButtonListener());
        this.add(submitButton);

        //设置显示的用户信息
        User me = userService.getUserInfo(userId);
        infoLabel.setBounds(150, 0, 450, 200);
        infoLabel.setFont(LoginUi.MIDDLE_FONT);
        infoLabel.setText(this.buildInfoHtml(me));
        this.add(infoLabel);

        this.setFont(LoginUi.MIDDLE_FONT);
    }

    /**
     * 返回个人信息的内嵌html代码
     *
     * @param user
     * @return
     */
    private String buildInfoHtml(User user) {
        String template = "<html><style>td{width:150px;}p{font-size:18px;}th{width:400px;text-align:center;}</style>"
                + "<p>您的基本信息如下:</p><table><tr></tr><tr><td>用户名</td><td>账 号</td><td>积 分</td></tr><tr><td>"
                + " %s </td><td> %s </td><td> %s </td></tr>"
                + "<tr></tr></table><p>您可以修改自己的用户名及密码</p></html>";

        return String.format(template, user.getName(), user.getId(), user.getPoint());
    }

    class EditInfoButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = userNameInput.getText();
            String oldPassword = new String(oldPasswordInput.getPassword());
            String newPassword = new String(newPasswordInput.getPassword());
            if ("".equals(name) || "".equals(oldPassword) || "".equals(newPassword) || !oldPassword.equals(newPassword)) {
                ShowMessageUtil.winMessage("输入错误");
                return;
            }

            //调用修改接口
            boolean result = userService.editInfo(userId, name, oldPassword, newPassword);

            if (result) {
                ShowMessageUtil.winMessage("修改成功");
            } else {
                ShowMessageUtil.winMessage("修改失败");
            }
        }
    }
}
