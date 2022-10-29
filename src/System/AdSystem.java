package System;

import Log.AdminRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdSystem extends JFrame implements ActionListener {
    JPanel centerPanel;
    AdminRegis ad;
    public AdSystem(AdminRegis admin) {
        ad=admin;
        init(ad);
    }
    private void init(AdminRegis ad) {
        //美化界面
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        setIconImage(new ImageIcon("lala.png").getImage());
        setSize(750,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel topjpanel = new JPanel();
        JLabel toplab = new JLabel("管理员系统");
        toplab.setFont(new Font("黑体", Font.BOLD, 30));
        topjpanel.add(toplab,BorderLayout.CENTER);
        add(topjpanel, BorderLayout.NORTH);

        JPanel menupanel = new JPanel();
        menupanel.setLayout(new GridLayout(4,1));
        String [] strings = {"展示管理员信息","用户核酸检测情况","查看外地来访情况","退出系统"};
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
        if ("展示管理员信息".equals(e.getActionCommand())) {
            centerPanel.setVisible(false);
            centerPanel=new AdminDisplay(ad);
            add(centerPanel);
        }else if("用户核酸检测情况".equals(e.getActionCommand())){
            centerPanel.setVisible(false);
            try {
                centerPanel=new UserShow(ad);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            add(centerPanel);
        }else if("查看外地来访情况".equals(e.getActionCommand())){
            centerPanel.setVisible(false);
            try {
                centerPanel = new OuterShow();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            add(centerPanel);
        }else {
            dispose();
        }
        validate();
    }

}
