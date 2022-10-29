package Enter;

import Database.DataBaseUr;
import MainEngine.Head;
import Log.UserRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserRegister {
    //创建注册页面
    public static void Subpage(JFrame jFrame){

        JDialog jDialog = new JDialog(jFrame,"注册页面",true);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(null);

        panel.setBackground(new Color(0xe7fafe));

        //用户名
        JLabel name = new JLabel("用户名:");
        name.setForeground(new Color(0xa5d478));
        name.setFont(new Font("黑体", Font.PLAIN, 30));
        name.setBounds(80, 60, 220, 40);
        panel.add(name);

        //用户输入框
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

        //住址
        JLabel position = new JLabel("住址:");
        position.setForeground(new Color(0xa5d478));
        position.setFont(new Font("黑体", Font.PLAIN, 30));
        position.setBounds(80, 300, 220, 40);
        panel.add(position);

        //住址输入框
        JTextField positionField = new JTextField(12);
        positionField.setFont(new Font("黑体", Font.PLAIN, 18));
//        NameField.setSelectedTextColor(new Color(0xFF0000));
        positionField.setBounds(230, 300, 280, 40);
        panel.add(positionField);

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
                int i;
                i = JOptionPane.showConfirmDialog(panel, "您确认注册用户吗？");
                if (i==0) {
                    String name = NameField.getText();
                    String phoneNumber=PhoneNumberField.getText();
                    String password = passwordField.getText();
                    String position=positionField.getText();
                    String a1="aaa";
                    UserRegis ur=new UserRegis(name,phoneNumber,position,password,a1);
                    DataBaseUr db=new DataBaseUr();
                    String msg=db.register(ur);
                    if (msg.equals("注册成功")) {
                        JOptionPane.showMessageDialog(panel, "恭喜你，用户注册成功", "通知", JOptionPane.INFORMATION_MESSAGE);
                        jDialog.setVisible(false);
                        jFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(panel, "抱歉，用户注册失败", "警告", JOptionPane.ERROR_MESSAGE);
                    }
                    db.close();
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
