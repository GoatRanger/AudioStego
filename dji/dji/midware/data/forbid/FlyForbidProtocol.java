package dji.midware.data.forbid;

public class FlyForbidProtocol {
    public static final double DATABASE_UPDATE_DIST = 50000.0d;
    public static double SEARCH_RADIUS = 10000.0d;
    public static final double STRONG_WARNING_CHECK_RADIUS = 5000.0d;
    public static final int SUPPORT_UNLOCK_FLYC_PROTOCOL_VERSION = 9;
    public static final long UNLIMIT_AREA_EXPIRED_TIME = 86400;
    public static final double UNLOCK_RADIUS = 2000.0d;
    public static final double UPDATA_CACHE_DIST = 10000.0d;

    public enum DJIWarningAreaState {
        None(0),
        NearLimit(1),
        InnerLimit(4);
        
        private int data;

        private DJIWarningAreaState(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }
    }

    public enum LevelType {
        WARNING(0),
        CAN_UNLIMIT(1),
        CAN_NOT_UNLIMIT(2, 4),
        STRONG_WARNING(3);
        
        private int data;
        private int mSubType;

        private LevelType(int i) {
            this.mSubType = -1;
            this.data = i;
        }

        private LevelType(int i, int i2) {
            this.mSubType = -1;
            this.data = i;
            this.mSubType = i2;
        }

        public int value() {
            return this.data;
        }

        public int getSubType() {
            return this.mSubType;
        }
    }
}
