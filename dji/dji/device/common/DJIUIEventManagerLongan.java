package dji.device.common;

import android.graphics.Bitmap;

public class DJIUIEventManagerLongan {
    private static DJIUIEventManagerLongan instance = null;
    private e mEvent;

    public enum a {
        ON_SELECTED,
        ON_DISSELECTED
    }

    public enum b {
        CLEAR_MOTION_SCREENTSHOTS,
        CLEAT_MOTION_DURS
    }

    public enum c {
        DISABLE_ALL,
        ENABLE_ALL,
        ENABLE_SHUTTER
    }

    public enum d {
        MIC_GAIN_UPDATED
    }

    public enum e {
        ON_CLICK_TIMELAPSE,
        TUTORIAL_FINISHED,
        ENTER_SLEEP_MODE,
        FORCE_START_PANO
    }

    public enum f {
        LANDSCAPE,
        PORTRAIT
    }

    public enum g {
        PANO_START,
        PANO_FINISH,
        PANO_STITCHED_ONE_FRAME,
        PANO_LOAD_FINISH,
        PANO_DOWNLOADED_ONE_FILE,
        PANO_DOWNLOADED_START,
        PANO_SWITCHUSB_FAILED,
        PANO_DELETE_ONE,
        PANO_TAKED_ONE,
        CLOSE_PANO_DISPLAYER,
        PANO_FAILED;
        
        int l;
    }

    public enum h {
        WB_SELECTED,
        WB_AUTO
    }

    public enum i {
        PLEASE_TAKE_SCREENSHORT,
        GOT_SCREENSHORT;
        
        public Bitmap c;
        public int d;

        public i a(int i) {
            this.d = i;
            return this;
        }

        public i a(Bitmap bitmap) {
            this.c = bitmap;
            return this;
        }
    }

    public enum j {
        START_SENSOR_CONTROL,
        STOP_SENSOR_CONTROL
    }

    public enum k {
        ADD_ONE_POINT,
        DELETE_ONE_POINT,
        DURATION_CHANGED,
        UPDATE_GENERATE_TIME,
        UPDATE_REMAIN_TIME,
        START;
        
        public int g;
        public int h;
        public String i;
        public String j;

        public k a(String str) {
            this.i = str;
            return this;
        }

        public k a(int i) {
            this.g = i;
            return this;
        }

        public k b(int i) {
            this.h = i;
            return this;
        }
    }

    public static class l {
        public static int a;
        public static int b;
        public static int c;
    }

    public enum m {
        HIDE_ALL,
        SHOW_ALL,
        HIDE_INFO_BAR,
        SHOW_INFO_BAR,
        INFO_BAR_SHOWEN,
        INFO_BAR_HIDDEN,
        HIDE_QUICKSETTING_BAR,
        SHOW_QUICKSETTING_BAR,
        RETURN_QUICKSETTING_BAR,
        HIDE_QUICKSETTING_GIMBAL_BAR,
        SHOW_QUICKSETTING_GIMBAL_BAR,
        HIDE_QUICKSETTING_WB_BAR,
        HIDE_TIMELAPSE_PLUS_BTN,
        SHOW_TIMELAPSE_PLUS_BTN,
        HIDE_TRACKING,
        SHOW_PASM,
        HIDE_PASM,
        SHOW_MENU,
        HIDE_MENU,
        HIDE_ERROR_NOTICE,
        SHOW_ERROR_NOTICE,
        SHOW_SHOTCUTS_CAMERA_LY,
        HIDE_SHOTCUTS_CAMERA_LY,
        SHOW_SHOTCUTS_GIMBAL_LY,
        HIDE_SHOTCUTS_GIMBAL_LY,
        SHOW_POWER_VIEW,
        SHOW_TIMELAPSE_LY,
        HIDE_TIMELAPSE_LY,
        SHOW_SET_PARMS_LY,
        SHOW_MOTION_LY,
        SHOW_GIMBAL_ROLL_TUNE_VIEW,
        SHOW_CONFLICT_RIGHT_VIEW,
        HIDE_CONFLICT_RIGHT_VIEW,
        EXIT_SETTING,
        CHANGE_DZOOM
    }

    public static synchronized DJIUIEventManagerLongan getInstance() {
        DJIUIEventManagerLongan dJIUIEventManagerLongan;
        synchronized (DJIUIEventManagerLongan.class) {
            if (instance == null) {
                instance = new DJIUIEventManagerLongan();
            }
            dJIUIEventManagerLongan = instance;
        }
        return dJIUIEventManagerLongan;
    }

    public void clear() {
        instance = null;
    }

    public e getEvent() {
        return this.mEvent;
    }

    public void setEvent(e eVar) {
        this.mEvent = eVar;
    }
}
