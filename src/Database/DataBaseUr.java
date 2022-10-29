package Database;

import Log.UserRegis;
import User.Tenant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//关于数据库的一系列操作
public class DataBaseUr{
    private Connection conn;

//    连接数据库；如果表不存在则创建表
    public DataBaseUr(){
        String USER_NAME = "root";
        String PSW = "cjhsql";
        String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/se_project?serverTimezone=UTC";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DATABASE_URL, USER_NAME, PSW);
            Statement stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS `user_info`(\n" +
                    "   `id` INT AUTO_INCREMENT,\n" +
                    "   `name` VARCHAR(20),\n" +
                    "   `phone_number` VARCHAR(12),\n" +
                    "   `psw` VARCHAR(20),\n" +
                    //"   `last_detect_time` BIGINT,\n" +
                    "   `last_detect_time` VARCHAR(20),\n" +
                    "   `position` VARCHAR(200),\n" +
                    "   PRIMARY KEY ( `id` )\n" +
                    ")CHARSET=utf8;");
            stat.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    注册时调用，将信息加载到数据库
    public String register(Object per){
        UserRegis person = (UserRegis)per;
        String msg = null;
        try {
            String sql1 = "SELECT id FROM user_info WHERE name LIKE ? and phone_number LIKE ?";
            PreparedStatement prep1 = this.conn.prepareStatement(sql1);
            prep1.setString(1, person.getName());
            prep1.setString(2, person.getPhoneNum());
            ResultSet rs = prep1.executeQuery();
            if(!rs.next()){
                String sql2 = "INSERT INTO user_info(name,psw,phone_number,position) VALUES(?,?,?,?)";
                PreparedStatement prep2 = this.conn.prepareStatement(sql2);
                prep2.setString(1, person.getName());
                prep2.setString(2, person.getPwd());
                prep2.setString(3, person.getPhoneNum());
                prep2.setString(4, person.getPosition());
                prep2.executeUpdate();
                prep2.close();
                msg = "注册成功";
            }else {
                rs.close();
                msg = "该用户已注册";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return msg;
    }

//    返回数据库中所有用户信息
    public Object[][] tenantList() throws SQLException {
        String sql = "SELECT id,name,phone_number,position,last_detect_time FROM user_info";
        PreparedStatement prep1 = this.conn.prepareStatement(sql);
        ResultSet rs = prep1.executeQuery(sql);
        ArrayList<Integer> list1=new ArrayList<>();
        ArrayList<String> list2=new ArrayList<>();
        ArrayList<String> list3=new ArrayList<>();
        ArrayList<String> list4=new ArrayList<>();
        ArrayList<String> list5=new ArrayList<>();
        int i=0;
        while (rs.next()){
            Integer id=rs.getInt(1);
            String name = rs.getString(2);
            String phone_number = rs.getString(3);
            String pos = rs.getString(4);
            String lastDetectTime= rs.getString(5);
            list1.add(id);
            list2.add(name);
            list3.add(phone_number);
            list4.add(pos);
            list5.add(UserRegis.getFormatDetectTime(lastDetectTime));
            i++;
        }
        Object[][] users=new Object[i][5];
        for (int k = 0; k < i; k++) {
            users[k][0]=list1.get(k);
            users[k][1]=list2.get(k);
            users[k][2]=list3.get(k);
            users[k][3]=list4.get(k);
            users[k][4]=list5.get(k);
        }
        return users;
    }

//    返回许久未做核酸的人员
    public List<UserRegis> getUnDoneList(){
        List<UserRegis> user = new ArrayList<>();
        try {
            String sql = "SELECT name,phone_number,last_detect_time FROM user_info";
            PreparedStatement prep1 = this.conn.prepareStatement(sql);
            ResultSet rs = prep1.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                String phone_number = rs.getString(2);
                String lastDetectTime= rs.getString(3);
                if(UserRegis.getFormatDetectTime(lastDetectTime).equals("72小时后")){
                    UserRegis ur = new UserRegis(name,phone_number,"","","");
                    user.add(ur);
                }
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public String Revise(Object per){  //修改用户信息
        UserRegis person = (UserRegis)per;
        String msg = null;
        try {
            String sql = "SELECT phone_number FROM user_info";
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
                String sql2="update user_info set phone_number= ?,psw = ?,position=? where name=?";
                PreparedStatement prep2 = this.conn.prepareStatement(sql2);
                prep2.setString(1, person.getPhoneNum());
                prep2.setString(2, person.getPwd());
                prep2.setString(4,person.getName());
                prep2.setString(3,person.getPosition());
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
            String sql = "SELECT name,phone_number,psw,last_detect_time,position FROM user_info";
            PreparedStatement prep1 = this.conn.prepareStatement(sql);
            ResultSet rs = prep1.executeQuery(sql);
            while (rs.next()){
                String name=rs.getString(1);
                String phone_number = rs.getString(2);
                String pwd=rs.getString(3);
                String last_detect_time = rs.getString(4);
                String position=rs.getString(5);
                if(phone_number.equals(user[1])) {
                    Exist[0] = true;
                    if (pwd.equals(user[3])) {
                        Exist[1]=true;
                        user[0]=name;
                        user[2]=position;
                        user[4]=last_detect_time;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Exist;
    }

    public String updateDetect(UserRegis person){  //上报核酸
        String msg = null;
        try {
            String sql2="update user_info set last_detect_time=? where name=?";
            PreparedStatement prep2 = this.conn.prepareStatement(sql2);
            prep2.setString(1, person.getLastDetectTime());
            prep2.setString(2, person.getName());
            prep2.executeUpdate();
            prep2.close();
                msg = "上传成功";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return msg;
    }

//    结束数据库连接，务必在程序结束时调用
    public void close(){
        try {
            this.conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
