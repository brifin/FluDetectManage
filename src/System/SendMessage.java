package System;

import Dependence.HttpUtils;
import Log.UserRegis;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SendMessage {
    private final String host = "https://gyytz.market.alicloudapi.com";
    private final String path = "/sms/smsSend";
    private final String method = "POST";
    private final List<UserRegis> ur;
    public SendMessage(List<UserRegis> ur) {
        this.ur = ur;
    }
    public byte msgGo(){
//        返回状态
//        0为不需要发送短信
//        1为全部发送成功
//        2为发生错误
        byte result = 1;
        if(ur.size()==0){
            result = 0;
            return result;
        }
        for (UserRegis rs : ur) {
            Map<String, String> headers = new HashMap<String, String>();
            Map<String, String> querys = new HashMap<String, String>();
            headers.put("Authorization", "APPCODE " + appcode);
            querys.put("param", "**name**:"+rs.getName());
            querys.put("smsSignId", signId);
            querys.put("templateId", templateId);
            querys.put("mobile", rs.getPhoneNum());
            Map<String, String> bodys = new HashMap<String, String>();

            try {
                HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

                if(!EntityUtils.toString(response.getEntity()).contains("成功")){
                    result = 2;
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
