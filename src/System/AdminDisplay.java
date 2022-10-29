package System;

import Database.DataBaseAd;
import Log.AdminRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDisplay extends JPanel implements ActionListener {   //管理员信息展示
    JLabel lname,lphoneNumber,lpwd,ljobNumber;
    JLabel tname,tphoneNumber,tpwd,tjobNumber;
    JLabel N;
    JTextField NphoneNumber,Npwd,NjobNum;

    JButton jButton;

    AdminRegis newad;
    public AdminDisplay(AdminRegis ad) {
        newad=ad;
        setLayout(null);
        Font font = new Font("黑体", Font.BOLD, 16);

        N =new JLabel("信息修改");
        N.setBounds(370,50,100,40);
        N.setFont(font);
        add(N);

        lname=new JLabel("姓名:");
        lname.setBounds(50,50,100,40);
        lname.setFont(font);

        tname =new JLabel(ad.getName());
        tname.setBounds(150,50,200,40);
        tname.setFont(font);

        lphoneNumber=new JLabel("电话号码:");
        lphoneNumber.setBounds(50,120,100,40);
        lphoneNumber.setFont(font);

        tphoneNumber =new JLabel(ad.getPhoneNum());
        tphoneNumber.setBounds(150,120,200,40);
        tphoneNumber.setFont(font);

        NphoneNumber =new JTextField();
        NphoneNumber.setBounds(300,120,220,40);

        lpwd=new JLabel("密码:");
        lpwd.setBounds(50,190,100,40);
        lpwd.setFont(font);

        tpwd =new JLabel(ad.getPwd());
        tpwd.setBounds(150,190,200,40);
        tpwd.setFont(font);

        Npwd =new JTextField();
        Npwd.setBounds(300,190,220,40);

        ljobNumber=new JLabel("工作号:");
        ljobNumber.setBounds(50,260,100,40);
        ljobNumber.setFont(font);

        tjobNumber =new JLabel(ad.getJobNumber());
        tjobNumber.setBounds(150,260,200,40);
        tjobNumber.setFont(font);

        NjobNum =new JTextField();
        NjobNum.setBounds(300,260,220,40);

        jButton = new JButton("确定修改");
        jButton.setBounds(190,340,100,40);
        jButton.addActionListener(this);

        add(lname);
        add(tname);
        //add(Nname);
        add(lphoneNumber);
        add(tphoneNumber);
        add(NphoneNumber);
        add(lpwd);
        add(tpwd);
        add(Npwd);
        add(ljobNumber);
        add(tjobNumber);
        add(NjobNum);
        add(jButton);

        setLayout(null);//坐标布局或绝对布局
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //---1.判断必填信息是否完整
        String name = newad.getName();
        //判断电话号码是否不空
        String phoneNumber = NphoneNumber.getText();
        byte flag = 0;
        if("".equals(phoneNumber)){
            phoneNumber=newad.getPhoneNum();
            flag++;
        }
        //判断密码是否不空
        String pwd = Npwd.getText();
        if("".equals(pwd)){
            pwd=newad.getPwd();
            flag++;
        }
        //判断工作号是否不空
        String jobNum = NjobNum.getText();
        System.out.println(jobNum);
        if("".equals(jobNum)){
            jobNum=newad.getJobNumber();
            flag++;
        }

        DataBaseAd dba=new DataBaseAd();
        AdminRegis ad = new AdminRegis(name,phoneNumber,pwd,jobNum);
        String reply=dba.Revise(ad);

        if(flag == 3){
            JOptionPane.showMessageDialog(this, "内容不能全空",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (reply.equals("修改成功")) {
            JOptionPane.showMessageDialog(this, "更改管理员信息成功！重新登陆后可以看到最新信息.");
            NphoneNumber.setText(null);
            Npwd.setText(null);
            NjobNum.setText(null);
        }else {
            JOptionPane.showMessageDialog(this, "发生错误",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
        }
    }
}
