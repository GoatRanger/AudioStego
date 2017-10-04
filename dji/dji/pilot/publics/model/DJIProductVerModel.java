package dji.pilot.publics.model;

import java.util.ArrayList;
import java.util.Iterator;

public class DJIProductVerModel {
    public ArrayList<DJIVerModel> all;
    public ArrayList<DJIVerModel> battery;
    public ArrayList<DJIVerModel> camera;
    public ArrayList<DJIVerModel> ignoreAll;
    public ArrayList<DJIVerModel> ignorebattery;
    public ArrayList<DJIVerModel> ignorecamera;
    public ArrayList<DJIVerModel> ignoremc;
    public ArrayList<DJIVerModel> ignorerc;
    public ArrayList<DJIVerModel> mc;
    public ArrayList<DJIVerModel> rc;

    public static class DJIVerModel {
        public String code;
        public int flag = 0;
        public boolean isNeedUp = false;
        public boolean isSeted = false;
        public String name;
        public int position = 0;
        public String version = "";

        public void reset() {
            this.isSeted = false;
            this.isNeedUp = false;
            this.version = "";
            this.position = 0;
            this.flag = 0;
        }

        public void setFlag(int i) {
            this.flag = Math.max(this.flag, i);
        }
    }

    public ArrayList<DJIVerModel> getAll() {
        if (this.all == null) {
            this.all = new ArrayList();
            if (this.camera != null) {
                this.all.addAll(this.camera);
            }
            if (this.battery != null) {
                this.all.addAll(this.battery);
            }
            if (this.rc != null) {
                this.all.addAll(this.rc);
            }
            if (this.mc != null) {
                this.all.addAll(this.mc);
            }
        }
        return this.all;
    }

    public ArrayList<DJIVerModel> getIgnoreList() {
        if (this.ignoreAll == null) {
            this.ignoreAll = new ArrayList();
        }
        this.ignoreAll.clear();
        addVerModel(this.ignoreAll, this.ignorecamera);
        addVerModel(this.ignoreAll, this.ignorebattery);
        addVerModel(this.ignoreAll, this.ignorerc);
        addVerModel(this.ignoreAll, this.ignoremc);
        return this.ignoreAll;
    }

    private void addVerModel(ArrayList<DJIVerModel> arrayList, ArrayList<DJIVerModel> arrayList2) {
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            arrayList.addAll(arrayList2);
        }
    }

    private void resetVerModel(ArrayList<DJIVerModel> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((DJIVerModel) it.next()).reset();
            }
        }
    }

    public void reset() {
        resetVerModel(this.camera);
        resetVerModel(this.battery);
        resetVerModel(this.rc);
        resetVerModel(this.mc);
        resetVerModel(this.ignorecamera);
        resetVerModel(this.ignorebattery);
        resetVerModel(this.ignorerc);
        resetVerModel(this.ignoremc);
        this.all = null;
    }
}
