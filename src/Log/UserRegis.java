package Log;

//注册时需要使用

import User.Tenant;

public class UserRegis extends Tenant {   //用户类
    //    地址
    private final String position;
    //    上次核酸时间戳
    private String lastDetectTime;
    public UserRegis(String name, String phoneNum, String position, String pwd,String lastDetectTime)
    {
        super(name, phoneNum,pwd);
        this.position = position;
        this.lastDetectTime = lastDetectTime;
    }

    public String getPosition() {
        return position;
    }

    public void setLastDetectTime(String lastDetectTime) {
        this.lastDetectTime = lastDetectTime;
    }

    public String getLastDetectTime(){return lastDetectTime;}

    //    将时间转换为简洁文字表示格式
    public static String getFormatDetectTime(String timeStamp){
        if(timeStamp == null){
            return "";
        }
        long curTime = System.currentTimeMillis();
        int hours = (int)(curTime - Long.parseLong(timeStamp))/(1000*60*60*24);
        String hoursPeriod;
        switch (hours){
            case 0: hoursPeriod = "24小时内";break;
            case 1: hoursPeriod = "48小时内";break;
            case 2: hoursPeriod = "72小时内";break;
            default: hoursPeriod = "72小时后";
        }
        return hoursPeriod;
    }
}
