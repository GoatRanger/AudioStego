package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.media.h.b.a;

public class DataCameraGetVideoParams extends n implements e {
    private static DataCameraGetVideoParams instance = null;

    public enum FPS_Drone {
        FPS0(0, 15),
        FPS1(1, 24),
        FPS2(2, 25),
        FPS3(3, 30),
        FPS4(4, 48),
        FPS5(5, 50),
        FPS6(6, 60),
        FPS7(7, 120),
        FPS8(8, 240),
        FPS9(9, 480),
        OTHER(-1, 0);
        
        private int fps;
        private int type;

        private FPS_Drone(int i, int i2) {
            this.type = i;
            this.fps = i2;
        }

        public int type() {
            return this.type;
        }

        public int fps() {
            return this.fps;
        }

        public boolean _equals(int i) {
            return this.type == i;
        }

        public static FPS_Drone find(int i) {
            FPS_Drone fPS_Drone = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return fPS_Drone;
        }
    }

    public enum Resolution_Drone {
        R0(0, 640, 480, "p"),
        R1(1, 640, 480, "i"),
        R2(2, 1280, 640, "p"),
        R3(3, 1280, 640, "i"),
        R4(4, 1280, 720, "p"),
        R5(5, 1280, 720, "i"),
        R6(6, 1280, 960, "p"),
        R7(7, 1280, 960, "i"),
        R8(8, 1920, 960, "p"),
        R9(9, 1920, 960, "i"),
        R10(10, 1920, a.e, "p"),
        R11(11, 1920, a.e, "i"),
        R12(12, 1920, 1440, "p"),
        R13(13, 1920, 1440, "i"),
        R14(14, 3840, 1920, "p"),
        R15(15, 3840, 1920, "i"),
        R16(16, 3840, 2160, "p"),
        R17(17, 3840, 2160, "i"),
        R18(18, 3840, 2880, "p"),
        R19(19, 3840, 2880, "i"),
        R20(20, 4096, 2048, "p"),
        R21(21, 4096, 2048, "i"),
        R22(22, 4096, 2160, "p"),
        R23(23, 4096, 2160, "i"),
        OTHER(-1, 0, 0, "-");
        
        private int height;
        private String suffix;
        private int type;
        private int width;

        private Resolution_Drone(int i, int i2, int i3, String str) {
            this.type = i;
            this.width = i2;
            this.height = i3;
            this.suffix = str;
        }

        public int type() {
            return this.type;
        }

        public int height() {
            return this.height;
        }

        public int width() {
            return this.width;
        }

        public String suffix() {
            return this.suffix;
        }

        public boolean _equals(int i) {
            return this.type == i;
        }

        public static Resolution_Drone find(int i) {
            Resolution_Drone resolution_Drone = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return resolution_Drone;
        }
    }

    public static synchronized DataCameraGetVideoParams getInstance() {
        DataCameraGetVideoParams dataCameraGetVideoParams;
        synchronized (DataCameraGetVideoParams.class) {
            if (instance == null) {
                instance = new DataCameraGetVideoParams();
            }
            dataCameraGetVideoParams = instance;
        }
        return dataCameraGetVideoParams;
    }

    public int getFolderId() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getFileId() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public long getUuid() {
        return ((Long) get(4, 4, Long.class)).longValue();
    }

    public int getRatio() {
        return ((Integer) get(8, 1, Integer.class)).intValue();
    }

    public int getFps() {
        return ((Integer) get(9, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aZ.a();
        start(cVar, dVar);
    }
}
