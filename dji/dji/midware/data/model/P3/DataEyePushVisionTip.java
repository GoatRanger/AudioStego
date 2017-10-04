package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyePushVisionTip extends n {
    private static DataEyePushVisionTip a;

    public enum TrackingTipType {
        None(0),
        ConfirmGesture(1),
        TakePhotoGesture(2),
        RockerCircle(3),
        AutoCircle(4),
        MinDistance(5),
        MaxDistance(6),
        OTHER(Integer.MAX_VALUE);
        
        private final int i;

        private TrackingTipType(int i) {
            this.i = i;
        }

        public int a() {
            return this.i;
        }

        public boolean a(int i) {
            return this.i == i;
        }

        public static TrackingTipType find(int i) {
            TrackingTipType trackingTipType = None;
            for (TrackingTipType trackingTipType2 : values()) {
                if (trackingTipType2.a(i)) {
                    return trackingTipType2;
                }
            }
            return trackingTipType;
        }
    }

    public static DataEyePushVisionTip getInstance() {
        if (a == null) {
            a = new DataEyePushVisionTip();
        }
        return a;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public TrackingTipType b() {
        return TrackingTipType.find(((Integer) get(1, 1, Integer.class)).intValue());
    }

    public int c() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int d() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
