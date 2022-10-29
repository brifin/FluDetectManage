package Database;

import Log.OuterRegis;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseOt implements DataBaseFunc{
    //关于数据库的一系列操作
    private Connection conn;

    public DataBaseOt() {
        String USER_NAME = "root";
        String PSW = "cjhsql";
        String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/se_project?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DATABASE_URL, USER_NAME, PSW);
            Statement stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS `outer_info`(\n" +
                    "   `id` INT AUTO_INCREMENT,\n" +
                    "   `name` VARCHAR(20),\n" +
                    "   `phone_number` VARCHAR(12),\n" +
                    "   `live_pos` VARCHAR(20),\n" +
                    "   `come_time` VARCHAR(20),\n" +
                    "   `origin_pos` VARCHAR(40),\n"+
                    "   PRIMARY KEY ( `id` )\n" +
                    ")CHARSET=utf8;");
            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String register(Object per){    //上传外地入院情况
        OuterRegis person = (OuterRegis)per;
        String msg = null;
        try {
            String sql1 = "SELECT id FROM admin_info WHERE name LIKE ?";
            PreparedStatement prep1 = this.conn.prepareStatement(sql1);
            prep1.setString(1, person.getName());
            ResultSet rs = prep1.executeQuery();
            if (!rs.next()) {
                String sql2 = "INSERT INTO outer_info(name,phone_number,live_pos,come_time,origin_pos) VALUES(?,?,?,?,?)";
                PreparedStatement prep2 = this.conn.prepareStatement(sql2);
                prep2.setString(1, person.getName());
                prep2.setString(2, person.getPhoneNum());
                prep2.setString(3, person.getLivePosition());
                prep2.setString(4, person.getComeTime());
                prep2.setString(5, person.getFromOrigin());
                prep2.executeUpdate();
                prep2.close();
                msg = "上报成功";
            } else {
                msg = Revise(person);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }

    //    返回数据库中所有外来人员信息
    public Object[][] tenantList() throws SQLException {
        String sql = "SELECT id,name,phone_number,live_pos,come_time,origin_pos FROM outer_info";
        PreparedStatement prep1 = this.conn.prepareStatement(sql);
        ResultSet rs = prep1.executeQuery(sql);
        ArrayList<Integer> list1=new ArrayList<>();
        ArrayList<String> list2=new ArrayList<>();
        ArrayList<String> list3=new ArrayList<>();
        ArrayList<String> list4=new ArrayList<>();
        ArrayList<String> list5=new ArrayList<>();
        ArrayList<String> list6=new ArrayList<>();
        int i=0;
        while (rs.next()){
            Integer id=rs.getInt(1);
            String name = rs.getString(2);
            String phone_number = rs.getString(3);
            String live_pos = rs.getString(4);
            String come_time= rs.getString(5);
            String origin_pos= rs.getString(6);
            list1.add(id);
            list2.add(name);
            list3.add(phone_number);
            list4.add(live_pos);
            list5.add(come_time);
            list6.add(origin_pos);
            i++;
        }
        Object[][] users=new Object[i][6];
        for (int k = 0; k < i; k++) {
            users[k][0]=list1.get(k);
            users[k][1]=list2.get(k);
            users[k][2]=list3.get(k);
            users[k][3]=list4.get(k);
            users[k][4]=list5.get(k);
            users[k][5]=list6.get(k);
        }
        return users;
    }

    public String Revise(Object per){   //修改外地入院信息
        OuterRegis person = (OuterRegis)per;
        String msg = null;
        try {
            String sql2="update outer_info set phone_number=? , live_pos=? , come_time=? , origin_pos=? where name=?";
            PreparedStatement prep2 = this.conn.prepareStatement(sql2);
            prep2.setString(1, person.getPhoneNum());
            prep2.setString(2, person.getLivePosition());
            prep2.setString(3,person.getComeTime());
            prep2.setString(4,person.getFromOrigin());
            prep2.setString(5,person.getName());
            prep2.executeUpdate();
            prep2.close();
                msg = "上报成功";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return msg;
    }

    public void close(){
        try {
            this.conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
