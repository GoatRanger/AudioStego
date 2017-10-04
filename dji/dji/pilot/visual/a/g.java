package dji.pilot.visual.a;

public interface g {
    public static final float l = 0.0f;
    public static final float[] m = new float[]{-5.0f, 5.0f};
    public static final boolean n = false;
    public static final int o = 5;
    public static final int p = Integer.MAX_VALUE;
    public static final String q = "key_show_trackmode_tip_";
    public static final String r = "key_show_pointmode_tip_";

    public enum c {
        CLOSED(false, 0),
        DISABLE(false, 0),
        INVALID(false, 0),
        HIDE(true, 0),
        NORMAL(true, 0);
        
        private boolean f;
        private int g;

        private c(boolean z, int i) {
            this.f = false;
            this.g = Integer.MIN_VALUE;
            this.f = z;
            this.g = i;
        }

        public boolean a() {
            return this.f;
        }
    }
}
