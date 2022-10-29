package System;

import Database.DataBaseOt;
import Log.AdminRegis;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class OuterShow extends JPanel {

    JTable table = null;
    //表格标题
    String[] title = {"序号","外人", "电话号码", "暂时住处","入院时间","来处"};

    DataBaseOt ot=new DataBaseOt();
    Object[][] data=ot.tenantList();

    public OuterShow() throws SQLException {
        // TODO Auto-generated constructor stub
        //1.构造表格对象并显示
        //（1）创建表格
        table = new JTable(data, title);
        //（2）设置表格参数
        table.getTableHeader().setReorderingAllowed(false);//不可改变列顺序
        table.getTableHeader().setResizingAllowed(false);//不可改变列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(210);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.setEnabled(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只能选一行
        //（3）创建滚动面板并把表格加到该面板中
        JScrollPane js = new JScrollPane(table);
        js.setPreferredSize(new Dimension(500, 320));//设置滚动面板的宽度、高度。需要在使用布局管理器的时候使用
        //（4）把滚动面板加入到窗口
        add(js, BorderLayout.CENTER);
    }
}
