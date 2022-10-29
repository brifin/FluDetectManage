package Log;

import User.Tenant;

public class AdminRegis extends Tenant {   //管理员类
    private final String jobNumber;

    public AdminRegis(String name, String phoneNum, String pwd,String jobNumber){
        super(name,phoneNum,pwd);
        this.jobNumber=jobNumber;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public String toString() {
        return "UserRegis{pwd = " + jobNumber + "}";
    }
}
