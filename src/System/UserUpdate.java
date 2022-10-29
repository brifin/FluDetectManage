package System;

import Database.DataBaseUr;
import Log.UserRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUpdate extends JPanel implements ActionListener {
    JLabel lphoneNumber,lpwd,ldtime;
    JTextField tphoneNumber,tpwd,tdtime;
    JButton jButton;
    UserRegis nur;
    public UserUpdate(UserRegis user) {
        nur=user;
        setLayout(null);
        Font font = new Font("黑体", Font.BOLD, 16);


        lphoneNumber=new JLabel("电话号码:");
        lphoneNumber.setBounds(60,80,100,40);
        lphoneNumber.setFont(font);

        tphoneNumber =new JTextField();
        tphoneNumber.setBounds(160,80,250,40);

        lpwd=new JLabel("密码:");
        lpwd.setBounds(60,150,100,40);
        lpwd.setFont(font);

        tpwd =new JTextField();
        tpwd.setBounds(160,150,250,40);

        ldtime=new JLabel("住址:");
        ldtime.setBounds(60,220,100,40);
        ldtime.setFont(font);

        tdtime =new JTextField();
        tdtime.setBounds(160,220,250,40);

        jButton = new JButton("确定修改");
        jButton.setBounds(190,300,100,40);
        jButton.addActionListener(this);

        add(lphoneNumber);
        add(tphoneNumber);
        add(lpwd);
        add(tpwd);
        add(ldtime);
        add(tdtime);
        add(jButton);

        setLayout(null);//坐标布局或绝对布局
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //---1.判断必填信息是否完整
        //判断电话号码是否不空
        String phoneNumber = tphoneNumber.getText();
        if("".equals(phoneNumber)){
            phoneNumber = nur.getPhoneNum();
        }
        //判断密码是否不空
        String pwd = tpwd.getText();
        if("".equals(pwd)){
            pwd = nur.getPwd();
        }
        //判断住址是否不空
        String dtime = tdtime.getText();
        if("".equals(dtime)){
            dtime = nur.getPosition();
        }

        DataBaseUr db=new DataBaseUr();
        UserRegis ur = new UserRegis(nur.getName(),phoneNumber,dtime,pwd,nur.getLastDetectTime());
        //db.Revise(ur);
        String reply=db.Revise(ur);
        if (reply.equals("修改成功")) {
            JOptionPane.showMessageDialog(this, "保存用户信息成功！重新登陆后可以看到最新信息.");
            tphoneNumber.setText(null);
            tpwd.setText(null);
            tdtime.setText(null);
        }else {
            JOptionPane.showMessageDialog(this, "发生错误",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
        }
    }
}
