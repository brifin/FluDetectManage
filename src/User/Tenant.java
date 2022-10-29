package User;

//关于人的一系列信息
public class Tenant {
//    名字
    private final String name;
//    电话号码
    private final String phoneNum;
//    密码
    private final String pwd;

    protected Tenant(String name, String phoneNum, String pwd){
        this.name = name;
        this.phoneNum = phoneNum;
        this.pwd=pwd;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPwd() { return pwd; }
}


