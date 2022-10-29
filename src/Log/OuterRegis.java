package Log;

import User.Tenant;

//外来用户信息
public class OuterRegis extends Tenant{
    private final String livePosition;
    private final String comeTime;
    private final String fromOrigin;

    public OuterRegis(String name, String phone_num, String livePosition, String comeTime, String fromOrigin) {
        super(name,phone_num,"");
        this.livePosition = livePosition;
        this.comeTime = comeTime;
        this.fromOrigin = fromOrigin;
    }

    public String getComeTime() {
        return comeTime;
    }

    public String getFromOrigin() {
        return fromOrigin;
    }

    public String getLivePosition() {
        return livePosition;
    }
}
