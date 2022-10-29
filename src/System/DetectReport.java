package System;

import Database.DataBaseUr;
import Log.UserRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetectReport extends JPanel implements ActionListener {
    JLabel lyear,lmonth,lday,lhour,lminute;
    JComboBox<String> comboUnit0,comboUnit1,comboUnit2,comboUnit3,comboUnit4;
    JButton jButton;
    UserRegis ur;
    DataBaseUr db=new DataBaseUr();

    public DetectReport(UserRegis ur) {
        this.ur = ur;
        setLayout(null);
        Font font = new Font("黑体", Font.BOLD, 16);

        lyear=new JLabel("年:");
        lyear.setBounds(60,70,100,40);
        lyear.setFont(font);

        comboUnit0 = new JComboBox<String>();
        comboUnit0.addItem("2021");
        comboUnit0.addItem("2022");
        comboUnit0.addItem("2023");
        comboUnit0.setBounds(160,70,100,40);
        comboUnit0.setSelectedIndex(1);

        lmonth=new JLabel("月:");
        lmonth.setBounds(60,120,100,40);
        lmonth.setFont(font);

        comboUnit1 = new JComboBox<String>();
        for(int i = 1;i <= 12;i++){
            comboUnit1.addItem(String.valueOf(i));
        }
        comboUnit1.setBounds(160,120,100,40);
        comboUnit1.setSelectedIndex(0);

        lday=new JLabel("日:");
        lday.setBounds(60,170,100,40);
        lday.setFont(font);

        comboUnit2 = new JComboBox<String>();
        for(int i = 1;i <= 31;i++){
            comboUnit2.addItem(String.valueOf(i));
        }
        comboUnit2.setBounds(160,170,100,40);
        comboUnit2.setSelectedIndex(0);

        lhour=new JLabel("小时:");
        lhour.setBounds(60,220,100,40);
        lhour.setFont(font);

        comboUnit3 = new JComboBox<String>();
        for(int i = 1;i <= 24;i++){
            comboUnit3.addItem(String.valueOf(i));
        }
        comboUnit3.setBounds(160,220,100,40);
        comboUnit3.setSelectedIndex(0);

        lminute=new JLabel("分钟：");
        lminute.setBounds(60,270,100,40);
        lminute.setFont(font);

        comboUnit4 = new JComboBox<String>();
        for(int i = 1;i <= 60;i++){
            comboUnit4.addItem(String.valueOf(i));
        }
        comboUnit4.setBounds(160,270,100,40);
        comboUnit4.setSelectedIndex(0);

        jButton = new JButton("提交时间");
        jButton.setBounds(110,330,100,40);
        jButton.addActionListener(this);

        add(lyear);
        add(lmonth);
        add(lday);
        add(lhour);
        add(lminute);
        add(comboUnit0);
        add(comboUnit1);
        add(comboUnit2);
        add(comboUnit3);
        add(comboUnit4);
        add(jButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dateString = comboUnit0.getSelectedItem()+"-"+comboUnit1.getSelectedItem()+"-"+
                comboUnit2.getSelectedItem()+" "+comboUnit3.getSelectedItem()+":"+comboUnit4.getSelectedItem()+":00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateString);
            String timeStamp = String.valueOf(date.getTime());
            ur.setLastDetectTime(timeStamp);
            String msg = db.updateDetect(ur);
            if("上传成功".equals(msg)){
                JOptionPane.showMessageDialog(this, "核酸上报成功");
                comboUnit0.setSelectedIndex(1);
                comboUnit1.setSelectedIndex(0);
                comboUnit2.setSelectedIndex(0);
                comboUnit3.setSelectedIndex(0);
                comboUnit4.setSelectedIndex(0);
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }
}
