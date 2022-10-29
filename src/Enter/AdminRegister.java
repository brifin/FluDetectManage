package Enter;

import Database.DataBaseAd;
import MainEngine.Head;
import Log.AdminRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminRegister {
    public static void Subpage(JFrame jFrame){

        JDialog jDialog = new JDialog(jFrame,"注册页面",true);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(null);

        panel.setBackground(new Color(0xe7fafe));

        //管理员姓名
        JLabel name = new JLabel("管理员:");
        name.setForeground(new Color(0xa5d478));
        name.setFont(new Font("黑体", Font.PLAIN, 30));
        name.setBounds(80, 60, 220, 40);
        panel.add(name);

        //管理员输入框
        JTextField NameField = new JTextField(12);
        NameField.setFont(new Font("黑体", Font.PLAIN, 18));
        NameField.setBounds(230, 60, 280, 40);
        panel.add(NameField);

        //电话号码
        JLabel phoneNumber = new JLabel("电话号码:");
        phoneNumber.setForeground(new Color(0xa5d478));
        phoneNumber.setFont(new Font("黑体", Font.PLAIN, 30));
        phoneNumber.setBounds(80, 140, 220, 40);
        panel.add(phoneNumber);

        //电话输入框
        JTextField PhoneNumberField = new JTextField(12);
        PhoneNumberField.setFont(new Font("黑体", Font.PLAIN, 18));
        PhoneNumberField.setBounds(230, 140, 280, 40);
        panel.add(PhoneNumberField);

        //标签密码
        JLabel passwd = new JLabel("密码:");
        passwd.setForeground(new Color(0xa5d478));
        passwd.setFont(new Font("黑体", Font.PLAIN, 30));
        passwd.setBounds(80, 220, 220, 40);
        panel.add(passwd);

        //密码框
        JPasswordField passwordField= new JPasswordField(12);
        passwordField.setBounds(230, 220, 280, 40);
        panel.add(passwordField);

        //学工号
        JLabel jobNumber = new JLabel("学工号:");
        jobNumber.setForeground(new Color(0xa5d478));
        jobNumber.setFont(new Font("黑体", Font.PLAIN, 30));
        jobNumber.setBounds(80, 300, 220, 40);
        panel.add(jobNumber);

        //学工号输入框
        JTextField jobNumberField = new JTextField(12);
        jobNumberField.setFont(new Font("黑体", Font.PLAIN, 18));
        jobNumberField.setBounds(230, 300, 280, 40);
        panel.add(jobNumberField);

        //确认按钮
        JButton register = new JButton("确认");
        register.setForeground(new Color(0xa5d478));
        register.setBackground(new Color(0xfffed5));
        register.setFont(new Font("黑体", Font.PLAIN, 20));
        register.setBorderPainted(false);
        register.setBounds(260, 380, 80, 40);
        panel.add(register);

        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i=10;
                i = JOptionPane.showConfirmDialog(panel, "您确认注册管理员吗？");
                if (i==0) {
                    String name = NameField.getText();
                    String phoneNumber=PhoneNumberField.getText();
                    String password = passwordField.getText();
                    String jobNumber=jobNumberField.getText();
                    AdminRegis ad=new AdminRegis(name,phoneNumber,password,jobNumber);
                    DataBaseAd dba=new DataBaseAd();
                    String msg=dba.register(ad);
                    if (msg.equals("注册成功")) {
                        JOptionPane.showMessageDialog(panel, "恭喜你，管理员注册成功", "通知", JOptionPane.INFORMATION_MESSAGE);
                        jDialog.setVisible(false);
                        jFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(panel, "抱歉，管理员注册失败", "警告", JOptionPane.ERROR_MESSAGE);
                    }
                    dba.close();
                }
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
