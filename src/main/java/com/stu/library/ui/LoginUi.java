package com.stu.library.ui;

import com.stu.library.domain.User;
import com.stu.library.service.LoginService;
import com.stu.library.util.ShowMessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUi {
    private LoginService loginService;
    private JFrame jf = new JFrame("图书馆系统");

    //各种输入框
    private JPasswordField passwordInput;
    private JTextField nameInput;
    private JTextField verificationInput;
    private JRadioButton loginUserTypeButton;

    //字体
    public static Font BIG_FONT = new Font("楷体", 1, 28);
    public static Font MIDDLE_FONT = new Font("楷体", 0, 20);
    public static Font SMALL_FONT = new Font("楷体", 0, 18);

    public LoginUi() {
        //获取service对象单例实例
        loginService = LoginService.getInstance();

        //设置窗口
        Dimension sc = Toolkit.getDefaultToolkit().getScreenSize(); // 获得屏幕尺寸
        jf.setSize(900, 500);
        jf.setLocation((sc.width - 900) / 2, (sc.height - 500) / 2);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置container
        Container container = jf.getContentPane();
        container.setLayout(null);
        container.setVisible(true);

        //开始绘制各组件
        draw(container);
    }

    /**
     * 绘制各组件
     *
     * @param container
     */
    public void draw(Container container) {
        //设置标题
        JLabel titleLabel = new JLabel("欢迎使用");
        titleLabel.setBounds(395, 10, 170, 30);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.black);
        container.add(titleLabel);

        //设置账号label
        JLabel nameLabel = new JLabel("账 号");
        nameLabel.setBounds(350, 140, 50, 30);
        nameLabel.setFont(MIDDLE_FONT);
        nameLabel.setForeground(Color.black);
        container.add(nameLabel);

        //设置密码label
        JLabel passwordLabel = new JLabel("密 码");
        passwordLabel.setBounds(350, 190, 50, 30);
        passwordLabel.setForeground(Color.black);
        passwordLabel.setFont(MIDDLE_FONT);
        container.add(passwordLabel);

        // 设置账号输入框
        nameInput = new JTextField();
        nameInput.setBounds(410, 143, 160, 25);
        nameInput.setBorder(null);
        nameInput.setFont(MIDDLE_FONT);
        container.add(nameInput);

        //设置密码输入框
        passwordInput = new JPasswordField();
        passwordInput.setBounds(410, 193, 160, 25);
        passwordInput.setBorder(null);
        passwordInput.setEchoChar('*');
        passwordInput.setFont(MIDDLE_FONT);
        container.add(passwordInput);

        //设置登录方式
        loginUserTypeButton = new JRadioButton("用户");
        JRadioButton loginManagerTypeButton = new JRadioButton("管理员");
        loginUserTypeButton.setBounds(350, 290, 140, 25);
        loginUserTypeButton.setSelected(true);
        loginManagerTypeButton.setBounds(490, 290, 80, 25);
        container.add(loginUserTypeButton);
        container.add(loginManagerTypeButton);

        // 登录方式弄成单选组
        ButtonGroup loginTypeButtonGroup = new ButtonGroup();
        loginTypeButtonGroup.add(loginUserTypeButton);
        loginTypeButtonGroup.add(loginManagerTypeButton);

        //设置“验证码”label
        JLabel verificationLabel = new JLabel("验证码");
        verificationLabel.setBounds(350, 240, 60, 25);
        verificationLabel.setFont(MIDDLE_FONT);
        verificationLabel.setForeground(Color.black);
        container.add(verificationLabel);

        //设置验证码输入框
        verificationInput = new JTextField();
        verificationInput.setBounds(420, 240, 80, 25);
        verificationInput.setBorder(null);
        verificationInput.setFont(MIDDLE_FONT);
        container.add(verificationInput);


        //设置获取验证码按钮
        JButton verifyButton = new JButton("获取验证码");
        verifyButton.setBounds(520, 240, 130, 25);
        verifyButton.setFont(SMALL_FONT);
        verifyButton.addActionListener(new VerifyButtonListener());
        container.add(verifyButton);

        //设置注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(350, 340, 90, 25);
        registerButton.setFont(SMALL_FONT);
        registerButton.addActionListener(new RegisterButtonListener());
        container.add(registerButton);

        //设置登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(480, 340, 90, 25);
        loginButton.setFont(SMALL_FONT);
        loginButton.addActionListener(new LoginSubmitListener());
        container.add(loginButton);

        // 背景图，在项目下的resources文件夹
        ImageIcon bgImg = new ImageIcon("resources/background.jpg");
        JLabel bg = new JLabel(bgImg);
        Container laycon = jf.getLayeredPane();
        bg.setSize(jf.getSize().width, jf.getSize().height);
        bgImg.setImage(bgImg.getImage().getScaledInstance(bg.getSize().width, bg.getSize().height, Image.SCALE_DEFAULT));
        laycon.add(bg, new Integer(Integer.MIN_VALUE));
        container.add(bg);

    }


    /**
     * 点击登录按钮的监听事件
     */
    class LoginSubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // 获得输入的 账号、密码、验证码
            String id = nameInput.getText();
            String password = new String(passwordInput.getPassword());
            String code = verificationInput.getText();
            if (id.equals("") || password.equals("")) {
                ShowMessageUtil.winMessage("账号、密码不能为空！");
                return;
            }

            if ("".equals(code)) {
                ShowMessageUtil.winMessage("请输入或点击获取验证码！");
                return;
            }


            //管理员功能未实现
            if (!loginUserTypeButton.isSelected()) {
                ShowMessageUtil.winMessage("管理员功能尚未实现！");
                return;
            }

            //调用登录接口
            User user = loginService.userLogin(id, password, code);
            if (user == null) {
                ShowMessageUtil.winMessage("验证失败！");
            } else {
                new UserUi(user.getId(), UserUi.ALL_BOOK_CARD);
                jf.dispose();
            }
        }

    }

    /**
     * 点击注册按钮的事件监听
     */
    class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ShowMessageUtil.winMessage("注册功能尚未实现！");
        }
    }

    /**
     * 点击获取验证码按钮的事件监听
     */
    class VerifyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String countInput = nameInput.getText();
            if ("".equals(countInput)) {
                ShowMessageUtil.winMessage("请先输入账号！");
                return;
            }

            //弹出验证码
            String code = loginService.getCode(countInput);
            System.out.println("验证码：" + code);
            ShowMessageUtil.winMessage("验证码为：" + code);
        }
    }

}



