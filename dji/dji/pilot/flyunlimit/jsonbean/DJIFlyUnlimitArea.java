package dji.pilot.flyunlimit.jsonbean;

import java.util.ArrayList;
import java.util.List;

public class DJIFlyUnlimitArea {
    public String account = "";
    public String create_at = "";
    public boolean disable = false;
    public int id = 0;
    public String key = "";
    private ArrayList<String> mPilotVersion = new ArrayList();
    private ArrayList<String> mUnlimitData = new ArrayList();
    public String name = "";
    public String updated_at = "";

    public List<String> getPilotSN() {
        return this.mPilotVersion;
    }

    public void addPilotSN(String str) {
        this.mPilotVersion.add(str);
    }

    public List<String> getUnlimitData() {
        return this.mUnlimitData;
    }

    public void addUnlimitData(String str) {
        this.mUnlimitData.add(str);
    }
}
