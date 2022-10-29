package MainEngine;

import javax.swing.*;
import java.awt.*;

//工具类

public class Head {

    public static JFrame frame = new JFrame("登录");
    public static final int width = 600;
    public static final int height = 500;
    public static String UserName=null;
    //动态切换面板

    //获取屏幕分辨率   设置窗体在屏幕居中的位置
    public static void initFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获取一个与系统相关的工具类对象
        Dimension size = toolkit.getScreenSize();//获取屏幕分辨率
        int x = (int) size.getWidth();//获取中心位置的宽
        int y = (int) size.getHeight();//获取中心位置的高
        frame.setBounds((x - width) / 2, (y - height) / 2, width, height);//设置窗口位置，大小
        frame.setVisible(true);//设置窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭
    }

    public static void initFrame(JFrame frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获取一个与系统相关的工具类对象
        Dimension size = toolkit.getScreenSize();//获取屏幕分辨率
        int x = (int) size.getWidth();//获取中心位置的宽
        int y = (int) size.getHeight();//获取中心位置的高
        frame.setBounds((x - width) / 2, (y - height) / 2, width, height);//设置窗口位置，大小
        frame.setVisible(true);//设置窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭

    }

    public static void initJDialog(JDialog frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获取一个与系统相关的工具类对象
        Dimension size = toolkit.getScreenSize();//获取屏幕分辨率
        int x = (int) size.getWidth();//获取中心位置的宽
        int y = (int) size.getHeight();//获取中心位置的高
        frame.setBounds((x - width) / 2, (y - height) / 2, width, height);//设置窗口位置，大小
        frame.setVisible(true);//设置窗口可见
    }

}
