package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import java.util.ArrayList;
import java.util.List;

public class DataFlycGetPushUnlimitState extends n {
    private static DataFlycGetPushUnlimitState instance = null;
    private List<UnlimitArea> mUnlimitAreas = new ArrayList();

    public static class UnlimitArea {
        public double lat;
        public double lng;
        public double radius;
        public long startTime;
        public long stopTime;
        public int type;
    }

    public static synchronized DataFlycGetPushUnlimitState getInstance() {
        DataFlycGetPushUnlimitState dataFlycGetPushUnlimitState;
        synchronized (DataFlycGetPushUnlimitState.class) {
            if (instance == null) {
                instance = new DataFlycGetPushUnlimitState();
            }
            dataFlycGetPushUnlimitState = instance;
        }
        return dataFlycGetPushUnlimitState;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
    }

    public int isInUnlimitArea() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getUnlimitAreasAction() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getUnlimitAreasSize() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getUnlimitAreasEnabled() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public List<UnlimitArea> getUnlimitAreasList() {
        this.mUnlimitAreas.clear();
        int unlimitAreasSize = getUnlimitAreasSize();
        for (int i = 0; i < unlimitAreasSize; i++) {
            UnlimitArea unlimitArea = new UnlimitArea();
            unlimitArea.lat = ((double) ((Long) get((i * 21) + 7, 4, Long.class)).longValue()) / 1000000.0d;
            unlimitArea.lng = ((double) ((Long) get(((i * 21) + 7) + 4, 4, Long.class)).longValue()) / 1000000.0d;
            unlimitArea.radius = (double) ((Long) get(((i * 21) + 7) + 8, 4, Long.class)).longValue();
            unlimitArea.type = ((Integer) get(((i * 21) + 7) + 12, 1, Integer.class)).intValue();
            unlimitArea.startTime = ((Long) get(((i * 21) + 7) + 13, 4, Long.class)).longValue();
            unlimitArea.stopTime = ((Long) get(((i * 21) + 7) + 17, 4, Long.class)).longValue();
            this.mUnlimitAreas.add(unlimitArea);
        }
        return this.mUnlimitAreas;
    }
}
