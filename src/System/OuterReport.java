package System;

import Database.DataBaseOt;
import Database.DataBaseUr;
import Log.OuterRegis;
import Log.UserRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuterReport extends JPanel implements ActionListener {
    JLabel lname,lphone,lpos,lctime,lorigin;
    JTextField tname,tphone,tpos,tctime,torigin;
    JButton jButton;
    DataBaseOt db = new DataBaseOt();

    public OuterReport(UserRegis ur) {
        setLayout(null);
        Font font = new Font("黑体", Font.BOLD, 16);

        lname=new JLabel("姓名");
        lname.setBounds(60,50,100,40);
        lname.setFont(font);

        tname =new JTextField();
        tname.setBounds(160,50,250,40);

        lphone=new JLabel("电话号码");
        lphone.setBounds(60,100,100,40);
        lphone.setFont(font);

        tphone =new JTextField();
        tphone.setBounds(160,100,250,40);

        lpos=new JLabel("入院住处");
        lpos.setBounds(60,150,100,40);
        lpos.setFont(font);

        tpos =new JTextField();
        tpos.setBounds(160,150,250,40);

        lctime=new JLabel("入院时间");
        lctime.setBounds(60,200,100,40);
        lctime.setFont(font);

        tctime =new JTextField();
        tctime.setBounds(160,200,250,40);

        lorigin=new JLabel("来处（省市）");
        lorigin.setBounds(60,250,100,40);
        lorigin.setFont(font);

        torigin =new JTextField();
        torigin.setBounds(160,250,250,40);

        jButton = new JButton("上交报告");
        jButton.setBounds(190,310,100,40);
        jButton.addActionListener(this);

        add(lname);
        add(lphone);
        add(lpos);
        add(lctime);
        add(lorigin);
        add(tname);
        add(tphone);
        add(tpos);
        add(tctime);
        add(torigin);
        add(jButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //---1.判断必填信息是否完整
        //判断姓名是否不空
        String name = tname.getText();
        //判断密码是否不空
        String phoneNUm = tphone.getText();
        //判断住址是否不空
        String pos = tpos.getText();
//        判断时间是否不空
        String time = tctime.getText();
//        判断来处是否不空
        String origin = torigin.getText();
        if("".equals(origin) || "".equals(name) || "".equals(time)||"".equals(pos)||"".equals(phoneNUm)){
            JOptionPane.showMessageDialog(this, "所有项均为必填",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        OuterRegis or = new OuterRegis(name,phoneNUm,pos,time,origin);
        String reply = db.register(or);
        if (reply.equals("上报成功")) {
            JOptionPane.showMessageDialog(this, "上交外来入院信息成功");
            tname.setText(null);
            tphone.setText(null);
            tpos.setText(null);
            tctime.setText(null);
            torigin.setText(null);
        }else {
            JOptionPane.showMessageDialog(this, "发生错误",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
        }
    }
}
