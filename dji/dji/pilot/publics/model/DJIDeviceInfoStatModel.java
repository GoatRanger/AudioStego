package dji.pilot.publics.model;

import dji.midware.data.manager.P3.i;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.usercenter.b.f;
import java.util.UUID;

public class DJIDeviceInfoStatModel {
    public int apptype;
    public String appversion;
    public long createtime;
    public String devicesn;
    public int devicetype;
    public String deviceversion;
    public String guid;
    public int id;
    public boolean isUploaded;
    public int productype;
    public String user;

    public DJIDeviceInfoStatModel() {
        String str;
        if (f.getInstance().j().equals("")) {
            str = "unlogin";
        } else {
            str = f.getInstance().j();
        }
        this.user = str;
        this.apptype = 1;
        this.productype = i.getInstance().c().value();
        this.appversion = DJIApplication.e;
        this.guid = UUID.randomUUID().toString();
        this.createtime = System.currentTimeMillis();
        this.isUploaded = false;
    }
}
