package Enter;


import Database.DataBaseUr;
import Enter.UserRegister;
import MainEngine.Head;
import Log.UserRegis;
import System.UserSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserLogin {

    private static Boolean Judge = true;

    public static void LoginWind(JFrame jFrame) {


        JDialog jDialog = new JDialog(jFrame, "用户登录注册页面", true);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(null);

        panel.setBackground(new Color(0xe7fafe));

        //添加标签【手机号】
        JLabel tphoneNumber = new JLabel("手机号:");
        tphoneNumber.setForeground(new Color(0Xdbb4f7));
        tphoneNumber.setFont(new Font("黑体", Font.PLAIN, 30));
        tphoneNumber.setBounds(80, 100, 200, 100);
        panel.add(tphoneNumber);

        //添加输入框【用户名输入框】
        JTextField phineNumber = new JTextField(20);
        phineNumber.setFont(new Font("黑体", Font.PLAIN, 18));
        phineNumber.setSelectedTextColor(new Color(0xFFF5EE));
        phineNumber.setBounds(210, 130, 280, 40);
        panel.add(phineNumber);

        //添加标签【密码】
        JLabel textPassword = new JLabel("密码  :");
        textPassword.setForeground(new Color(0Xdbb4f7));
        textPassword.setFont(new Font("黑体", Font.PLAIN, 30));
        textPassword.setBounds(80, 170, 200, 100);
        panel.add(textPassword);

        //添加密码输入框【密码】
        JPasswordField password = new JPasswordField(20);
        password.setBounds(210, 200, 280, 40);
        panel.add(password);

        //添加按钮【登录】
        JButton jButton = new JButton("登录");
        jButton.setForeground(new Color(0xffcbe1));
        jButton.setBackground(new Color(0xfffde4));
        jButton.setFont(new Font("黑体", Font.PLAIN, 20));
        jButton.setBorderPainted(false);
        jButton.setBounds(150, 300, 100, 50);
        panel.add(jButton);

        //添加按钮【注册】
        JButton register = new JButton("注册");
        register.setForeground(new Color(0xffcbe1));
        register.setBackground(new Color(0xfffde4));
        register.setFont(new Font("黑体", Font.PLAIN, 20));
        register.setBorderPainted(false);
        register.setBounds(350, 300, 100, 50);
        panel.add(register);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNum = phineNumber.getText();//获取账户
                String passwd = password.getText();//获取密码
                String[] user = new String[5];
                user[1] = phoneNum;
                user[3] = passwd;
                DataBaseUr db = new DataBaseUr();
                boolean[] Exist = db.LoginDetection(user);
                if (!Exist[0]) {
                    JOptionPane.showMessageDialog(panel, "警告:电话号码输入错误", "警告", JOptionPane.WARNING_MESSAGE);
                } else if (!Exist[1]) {
                    JOptionPane.showMessageDialog(panel, "警告:密码输入错误", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "登录成功", "通知", JOptionPane.INFORMATION_MESSAGE);
                    UserRegis ur = new UserRegis(user[0], user[1], user[2], user[3], user[4]);
                    UserSystem u = new UserSystem(ur);
                    u.setVisible(true);
                    u.setTitle("住户系统");
                    //Head.UserName=phoneNum;
                    jDialog.setVisible(false);
                    jFrame.setVisible(true);
                }
                db.close();
            }
        });

        register.addMouseListener(new MouseAdapter() {   //创建注册页面
            @Override
            public void mouseClicked(MouseEvent e) {
                //创建用户注册页面;
                if (Judge) {
                    Judge = false;
                }
                UserRegister.Subpage(Head.frame);
            }
        });


        //返回键
        JButton register1 = new JButton("返回");
        register1.setForeground(new Color(0xfddfeb));
        register1.setBackground(new Color(0x84d696));
        register1.setFont(new Font("黑体", Font.PLAIN, 20));
        register1.setBorderPainted(false);
        register1.setBounds(0, 0, 80, 40);
        register1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jDialog.setVisible(false);
                jFrame.setVisible(true);
            }
        });
        panel.add(register1);


        jDialog.add(panel);//新页面
        jFrame.setVisible(false);
        Head.initJDialog(jDialog);

    }
}

