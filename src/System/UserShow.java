package System;

import Database.DataBaseUr;
import Log.AdminRegis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserShow extends JPanel {

    JButton b,b0,message;
    JTextField jTextField,jTextField0;


    JComboBox<String> comboUnit,comboUnit0;
    JTable table = null;
    JPanel jPanel, editpanel;
    //表格标题
    String[] title = {"序号","住户名称", "电话号码", "住址","核酸检测时间"};

    DataBaseUr db=new DataBaseUr();
    Object[][] data=db.tenantList();

    public UserShow(AdminRegis ad) throws SQLException {
        jPanel = new JPanel();
        comboUnit = new JComboBox<String>();

        //设置下拉列表组件
        comboUnit.addItem("按姓名查找");
        comboUnit.addItem("按手机号查找");
        comboUnit.addItem("按住址查找");
        comboUnit.setSelectedIndex(1);

        jTextField = new JTextField(5);

        b = new JButton("确定");
        jPanel.add(comboUnit);
        jPanel.add(jTextField);
        jPanel.add(b);
        add(jPanel,BorderLayout.NORTH);

        editpanel = new JPanel();
        comboUnit0=new JComboBox<String>();

        comboUnit0.addItem("按楼号分组");
        comboUnit0.addItem("按核酸检测分组");
        comboUnit0.addItem("按年龄分组");
        comboUnit0.setSelectedIndex(1);

        jTextField0 = new JTextField(5);

        b0 = new JButton("确定");
        editpanel.add(comboUnit0);
        editpanel.add(jTextField0);
        editpanel.add(b0);
        add(editpanel,BorderLayout.NORTH);


        // TODO Auto-generated constructor stub
        //1.构造表格对象并显示
        //（1）创建表格
        table = new JTable(data, title);
        //（2）设置表格参数
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getTableHeader().setReorderingAllowed(false);//不可改变列顺序
        table.getTableHeader().setResizingAllowed(false);//不可改变列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.setEnabled(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只能选一行

        //（3）创建滚动面板并把表格加到该面板中
        JScrollPane js = new JScrollPane(table);
        js.setPreferredSize(new Dimension(500, 320));//设置滚动面板的宽度、高度。需要在使用布局管理器的时候使用
        //（4）把滚动面板加入到窗口
        add(js, BorderLayout.CENTER);

        message = new JButton("超72h一键短信发送提醒");
        //（2）通过匿名内部类为按钮添加事件处理
        message.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessage sm = new SendMessage(db.getUnDoneList());
                byte result = sm.msgGo();
                if(result == 0){
                    JOptionPane.showMessageDialog(null, "全部满足要求，无需发送");
                }else if(result == 1){
                    JOptionPane.showMessageDialog(null, "全部发送成功");
                }else if(result == 2){
                    JOptionPane.showMessageDialog(null, "发生错误",
                            "错误提示",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //（3）把按钮加入到窗口
        add(message);


        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        b0.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
