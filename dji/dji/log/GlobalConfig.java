package dji.log;

public class GlobalConfig {
    public static final boolean DEBUG = true;
    public static final BACK_END_TYPE REMOTE_SERVER = BACK_END_TYPE.PROD;

    public enum BACK_END_TYPE {
        PROD,
        STAGE,
        DEV
    }
}
