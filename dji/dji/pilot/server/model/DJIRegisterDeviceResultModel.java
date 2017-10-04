package dji.pilot.server.model;

public class DJIRegisterDeviceResultModel {
    public int index;
    public String msg;
    public String[] services;
    public int status;
    public String token;

    public String getBestUrl() {
        String str = this.services[this.index];
        this.index++;
        if (this.index >= this.services.length) {
            this.index = 0;
        }
        return str;
    }
}
