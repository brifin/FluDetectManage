package System;

import Log.UserRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserSystem extends JFrame implements ActionListener {
        JPanel centerPanel;
        UserRegis ur;
    public UserSystem(UserRegis user) {
        ur=user;
        init(ur);
    }
    private void init(UserRegis ur) {
        //美化界面
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        setIconImage(new ImageIcon("lala.png").getImage());
        setSize(650,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel topjpanel = new JPanel();
        JLabel toplab = new JLabel("用户系统");
        toplab.setFont(new Font("黑体", Font.BOLD, 30));
        topjpanel.add(toplab,BorderLayout.CENTER);
        add(topjpanel, BorderLayout.NORTH);

        JPanel menupanel = new JPanel();
        menupanel.setLayout(new GridLayout(6,1));
        String [] strings = {"展示用户信息","修改用户基本资料","核酸上报","外地入院上报","所在地区疫情状况","退出系统"};
        JButton [] buts = new JButton[strings.length];
        for (int i = 0; i < strings.length; i++) {
            buts[i] = new JButton(strings[i]);
            buts[i].addActionListener(this);
            buts[i].setFont(new Font("黑体", Font.BOLD, 16));
            menupanel.add(buts[i]);
        }
        add(menupanel, BorderLayout.WEST);

        centerPanel = new JPanel();
        JLabel centerlab = new JLabel(new ImageIcon("center.jpg"));
        centerlab.setFont(new Font("宋体", Font.BOLD, 36));
        centerPanel.add(centerlab);
        add(centerPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("展示用户信息".equals(e.getActionCommand())) {
            centerPanel.setVisible(false);
            centerPanel=new UserDisplay(ur);
            add(centerPanel);
        }else if("修改用户基本资料".equals(e.getActionCommand())){
            centerPanel.setVisible(false);
            centerPanel=new UserUpdate(ur);
            add(centerPanel);
        }else if("核酸上报".equals(e.getActionCommand())){
            centerPanel.setVisible(false);
            centerPanel=new DetectReport(ur);
            add(centerPanel);
        }else if("外地入院上报".equals(e.getActionCommand())){
            centerPanel.setVisible(false);
            centerPanel=new OuterReport(ur);
            add(centerPanel);
        }else if("所在地区疫情状况".equals(e.getActionCommand())){
            centerPanel.setVisible(false);
            try {
                centerPanel = new LocalReport();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            add(centerPanel);
        }else {
            dispose();
        }
        validate();
    }
}
