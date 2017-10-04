package dji.pilot.flyunlimit.jsonbean;

import java.util.List;

public class DJIUnlimitAreaResult {
    public List<AreaData> data;
    public AreaExtra extra;
    public String signature;
    public long status;
    public boolean success;

    public static class AreaData {
        public int id;
    }

    public static class AreaExtra {
        public long key;
        public String msg;
    }
}
