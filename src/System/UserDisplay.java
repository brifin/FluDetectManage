package System;

import Log.UserRegis;

import javax.swing.*;
import java.awt.*;

public class UserDisplay extends JPanel {
    JLabel lname,lphoneNumber,lpwd,ldtime,lposition;
    JLabel tname,tphoneNumber,tpwd,tdtime,tposition;

    public UserDisplay(UserRegis ur) {
        setLayout(null);
        Font font = new Font("黑体", Font.BOLD, 20);

        lname=new JLabel("姓名:");
        lname.setBounds(120,50,100,40);
        lname.setFont(font);

        tname =new JLabel(ur.getName());
        tname.setBounds(240,50,280,40);
        tname.setFont(font);

        lphoneNumber=new JLabel("电话号码:");
        lphoneNumber.setBounds(120,120,100,40);
        lphoneNumber.setFont(font);

        tphoneNumber =new JLabel(ur.getPhoneNum());
        tphoneNumber.setBounds(240,120,280,40);
        tphoneNumber.setFont(font);

        lpwd=new JLabel("密码:");
        lpwd.setBounds(120,190,100,40);
        lpwd.setFont(font);

        tpwd =new JLabel(ur.getPwd());
        tpwd.setBounds(240,190,280,40);
        tpwd.setFont(font);

        ldtime=new JLabel("检测时间:");
        ldtime.setBounds(120,260,100,40);
        ldtime.setFont(font);

        tdtime =new JLabel(UserRegis.getFormatDetectTime(ur.getLastDetectTime()));
        tdtime.setBounds(240,260,280,40);
        tdtime.setFont(font);

        lposition=new JLabel("住址:");
        lposition.setBounds(120,330,120,40);
        lposition.setFont(font);

        tposition =new JLabel(ur.getPosition());
        tposition.setBounds(240,330,280,40);
        tposition.setFont(font);


        add(lname);
        add(tname);
        add(lphoneNumber);
        add(tphoneNumber);
        add(lpwd);
        add(tpwd);
        add(ldtime);
        add(tdtime);
        add(lposition);
        add(tposition);

        setLayout(null);//坐标布局或绝对布局
    }
}
