package dji.setting.ui.vision;

public interface a {
    public static final int a = 4096;
    public static final long b = 200;
    public static final String c = "g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0";
    public static final String d = "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0";
    public static final String e = "g_config.go_home.avoid_enable_0";

    public static class a {
        public String a = null;

        public a(String str) {
            this.a = str;
        }
    }

    public enum b {
        VisionViewVisibilityEvent
    }

    public static class c {
        public b a;
        public int b;

        public c(b bVar, int i) {
            this.a = bVar;
            this.b = i;
        }
    }
}
