package MainEngine;

import Enter.AdminLogin;
import Enter.UserLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MSystem {  //主系统

    private static Boolean Judge = true;

    //账户密码登录窗口loginWind
    public static void LoginWind(JFrame jFrame) {


        JPanel panel = new JPanel();

        Head.frame.setBackground(new Color(0xd4f6ff));
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());

        panel.setLayout(null);

        //添加按钮【住户系统】
        JButton user = new JButton("用户登录系统");
        user.setForeground(new Color(0xffcbe1));
        user.setBackground(new Color(0xfffde4));
        user.setFont(new Font("黑体", Font.PLAIN, 20));
        user.setBorderPainted(false);
        user.setBounds(200, 120, 200, 60);
        panel.add(user);

        //添加按钮【管理员系统】
        JButton admin = new JButton("管理员登录系统");
        admin.setForeground(new Color(0xffcbe1));
        admin.setBackground(new Color(0xfffde4));
        admin.setFont(new Font("黑体", Font.PLAIN, 20));
        admin.setBorderPainted(false);
        admin.setBounds(200, 240, 200, 60);
        panel.add(admin);

        user.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       UserLogin.LoginWind(jFrame);  //进入用户登录系统
                                       //LogIn.Subpage(jFrame);
                                       Head.initFrame();
                                   }
                               }
        );

        admin.addMouseListener(new MouseAdapter() {   //创建注册页面
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminLogin.LoginWind(jFrame);  //进入管理员登录系统
                //Register.Subpage(jFrame);
                Head.initFrame();
            }
        });
        Head.frame.setContentPane(panel);
    }
}
