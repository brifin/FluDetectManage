package System;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class InfoThread implements Callable<HashMap<String, String>> {
    private final String[] infoList;

    public InfoThread(String[] infoList){
        this.infoList = infoList;
    }
    @Override
    public HashMap<String, String> call() {
        HashMap<String, String> result = new HashMap<>();
//        "total":{"confirm":2022,"suspect":0,"heal":0,"dead":0,"severe":0},"extData":{},
//        "name":"成都","id":"510100","lastUpdateTime":"2022-10-17 10:06:50","children":[]},
//    {"today":{"confirm":6,"suspect":null,"heal":null,"dead":null,"severe":null,"storeConfirm":null},
        for (String s : infoList) {
            if (s != null && s.contains("成都")) {
                int first = s.indexOf("{\"confirm\":") + 11;
                int last = s.indexOf(",\"suspect\"");
                result.put("CDAll", s.substring(first, last));

                int first1 = s.lastIndexOf("{\"confirm\":") + 11;
                int last1 = s.lastIndexOf(",\"suspect\"");
                result.put("CDToday", s.substring(first1, last1));

                int first2 = s.indexOf("Time\":\"") + 7;
                int last2 = s.indexOf("\",\"children");
                result.put("reportTime", s.substring(first2, last2));

            } else if (s != null && s.contains("中国")) {
                int first = s.indexOf("{\"confirm\":") + 11;
                int last = s.indexOf(",\"suspect\"");
                result.put("CNAll", s.substring(first, last));

            } else if (s != null && s.contains("美国")) {
                int first = s.indexOf("{\"confirm\":") + 11;
                int last = s.indexOf(",\"suspect\"");
                result.put("USAll", s.substring(first, last));
            }
        }
        return result;
    }
}

//所在地区疫情现状报告
public class LocalReport extends JPanel{
    JLabel lpos,llocal,lchina,lusa,ltime;
    JLabel tpos,tlocal,tchina,tusa,ttime;
    HashMap<String, String> result;

    public LocalReport() throws IOException {
        setLayout(null);
        getInfo();
        Font font = new Font("黑体", Font.BOLD, 16);

        lpos=new JLabel("地区:");
        lpos.setBounds(80,40,100,40);
        lpos.setFont(font);

        tpos =new JLabel("成都");
        tpos.setBounds(200,40,200,40);
        tpos.setFont(font);

        llocal=new JLabel("地区确诊：");
        llocal.setBounds(80,110,100,40);
        llocal.setFont(font);

        tlocal =new JLabel("今日:"+result.get("CDToday")+"  总共:"+result.get("CDAll"));
        tlocal.setBounds(200,110,200,40);
        tlocal.setFont(font);

        lchina=new JLabel("中国总确诊：");
        lchina.setBounds(80,180,100,40);
        lchina.setFont(font);

        tchina =new JLabel(result.get("CNAll"));
        tchina.setBounds(200,180,200,40);
        tchina.setFont(font);

        lusa=new JLabel("美国总确诊：");
        lusa.setBounds(80,250,100,40);
        lusa.setFont(font);

        tusa =new JLabel(result.get("USAll"));
        tusa.setBounds(200,250,200,40);
        tusa.setFont(font);

        ltime=new JLabel("更新时间:");
        ltime.setBounds(80,320,100,40);
        ltime.setFont(font);

        ttime =new JLabel(result.get("reportTime"));
        ttime.setBounds(200,320,200,40);
        ttime.setFont(font);

        add(lpos);
        add(lchina);
        add(llocal);
        add(ltime);
        add(lusa);
        add(tpos);
        add(tchina);
        add(tlocal);
        add(ttime);
        add(tusa);
    }

    private void getInfo() throws IOException {
        {
            final String theUrl = "https://c.m.163.com/ug/api/wuhan/app/data/list-total";
            java.net.URL url = new java.net.URL(theUrl);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();
            BufferedReader reader = null;
            StringBuilder resultBuffer = new StringBuilder();
            String tempLine = null;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            String text = resultBuffer.toString();

            String[] infoList = text.split("\"total\"");
            int batchLen = infoList.length/3;
            String[] infoList1 = new String[400];
            String[] infoList2 = new String[400];
            String[] infoList3 = new String[400];

            System.arraycopy(infoList, 0, infoList1, 0, batchLen);
            System.arraycopy(infoList, batchLen, infoList2, 0, batchLen);
            System.arraycopy(infoList, 2*batchLen, infoList3, 0, infoList.length-2*batchLen);

            InfoThread if1 = new InfoThread(infoList1);
            InfoThread if2 = new InfoThread(infoList2);
            InfoThread if3 = new InfoThread(infoList3);

            FutureTask<HashMap<String, String>> ft1 = new FutureTask<>(if1);
            FutureTask<HashMap<String, String>> ft2 = new FutureTask<>(if2);
            FutureTask<HashMap<String, String>> ft3 = new FutureTask<>(if3);

            new Thread(ft1).start();
            new Thread(ft2).start();
            new Thread(ft3).start();

            try {
                result = ft1.get();
                result.putAll(ft2.get());
                result.putAll(ft3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
