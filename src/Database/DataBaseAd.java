package Database;

import Log.AdminRegis;

import java.sql.*;

public class DataBaseAd implements DataBaseFunc{
    //关于数据库的一系列操作
    private Connection conn;

    public DataBaseAd() {
        String USER_NAME = "root";
        String PSW = "cjhsql";
        String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/se_project?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DATABASE_URL, USER_NAME, PSW);
            Statement stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS `admin_info`(\n" +
                    "   `id` INT AUTO_INCREMENT,\n" +
                    "   `name` VARCHAR(20),\n" +
                    "   `phone_number` VARCHAR(12),\n" +
                    "   `psw` VARCHAR(20),\n" +
                    "   `job_number` VARCHAR(20),\n" +
                    "   PRIMARY KEY ( `id` )\n" +
                    ")CHARSET=utf8;");
            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String register(Object per){    //用户注册
        AdminRegis person = (AdminRegis) per;
        String msg = null;
        try {
            String sql1 = "SELECT id FROM admin_info WHERE name LIKE ? and phone_number LIKE ?";
            PreparedStatement prep1 = this.conn.prepareStatement(sql1);
            prep1.setString(1, person.getName());
            prep1.setString(2, person.getPhoneNum());
            ResultSet rs = prep1.executeQuery();
            if(!rs.next()){
                String sql2 = "INSERT INTO admin_info(name,psw,phone_number,job_number) VALUES(?,?,?,?)";
                PreparedStatement prep2 = this.conn.prepareStatement(sql2);
                prep2.setString(1, person.getName());
                prep2.setString(2, person.getPwd());
                prep2.setString(3, person.getPhoneNum());
                prep2.setString(4, person.getJobNumber());
                prep2.executeUpdate();
                prep2.close();
                msg = "注册成功";
            }else {
                rs.close();
                msg = "您已注册";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return msg;
    }

    public String Revise(Object per){   //修改管理员信息
        AdminRegis person = (AdminRegis) per;
        String msg = null;
        try {
            String sql = "SELECT phone_number FROM admin_info";
            PreparedStatement prep1 = this.conn.prepareStatement(sql);
            ResultSet rs = prep1.executeQuery(sql);
            boolean Exist=false;
            while (rs.next()){
                String phone_number = rs.getString(1);
                if(phone_number.equals(person.getPhoneNum())) {
                    Exist = true;
                }
            }
            if (Exist) {
                String sql2="update admin_info set phone_number= ?,psw = ?,job_number=? where name=?";
                PreparedStatement prep2 = this.conn.prepareStatement(sql2);
                prep2.setString(1, person.getPhoneNum());
                prep2.setString(2, person.getPwd());
                prep2.setString(3,person.getJobNumber());
                prep2.setString(4,person.getName());
                prep2.executeUpdate();
                prep2.close();
                msg = "修改成功";
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return msg;
    }

    public boolean[] LoginDetection(String[] user){  //登录判断
        boolean[] Exist={false,false};
        try {
            String sql = "SELECT name,phone_number,psw,job_number FROM admin_info";
            PreparedStatement prep1 = this.conn.prepareStatement(sql);
            ResultSet rs = prep1.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString(1);
                String phone_number = rs.getString(2);
                String pwd = rs.getString(3);
                String job_number = rs.getString(4);
                if(phone_number.equals(user[1])) {
                    Exist[0] = true;
                    if (pwd.equals(user[2])) {
                        Exist[1]=true;
                        user[0]=name;
                        user[3]=job_number;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Exist;
    }


    public void close(){
        try {
            this.conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
