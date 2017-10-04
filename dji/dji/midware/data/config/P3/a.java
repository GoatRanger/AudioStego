package dji.midware.data.config.P3;

import it.sauronsoftware.ftp4j.FTPCodes;

public enum a {
    TIMEOUT(256),
    OK(0),
    SUCCEED(1),
    NOGPS(128),
    INVALID_CMD(dji.thirdparty.g.b.a.a.fw_),
    TIMEOUT_REMOTE(FTPCodes.DATA_CONNECTION_OPEN),
    OUT_OF_MEMORY(FTPCodes.DATA_CONNECTION_CLOSING),
    INVALID_PARAM(FTPCodes.ENTER_PASSIVE_MODE),
    NOT_SUPPORT_CURRENT_STATE(228),
    TIME_NOT_SYNC(229),
    SET_PARAM_FAILED(FTPCodes.USER_LOGGED_IN),
    GET_PARAM_FAILED(231),
    SDCARD_NOT_INSERTED(232),
    SDCARD_FULL(233),
    SDCARD_ERR(234),
    SENSOR_ERR(235),
    CAMERA_CRITICAL_ERR(236),
    PARAM_NOT_AVAILABLE(237),
    FM_NONSEQUENCE(240),
    FM_LENGTH_WRONG(241),
    FM_CRC_WRONG(242),
    FLASH_C_WRONG(243),
    FLASH_W_WRONG(244),
    UPDATE_WRONG(245),
    FIRM_MATCH_WRONG(246),
    UPDATE_WAIT_FINISH(247),
    UPDATE_MOTOR_WORKING(249),
    UPDATE_NOCONNECT_CAMERA(254),
    FLASH_FLUSHING(253),
    UNDEFINED(255),
    NOCONNECT(511),
    NORECEIVE_PUSHDATA(510);
    
    private int G;
    private int H;

    private a(int i) {
        this.G = i;
    }

    public int a() {
        return this.G;
    }

    public void a(int i) {
        this.H = i;
    }

    public int b() {
        return this.H;
    }

    public boolean b(int i) {
        return this.G == i;
    }

    public static a find(int i) {
        a aVar;
        a aVar2 = UNDEFINED;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].b(i)) {
                aVar = values()[i2];
                break;
            }
        }
        aVar = aVar2;
        aVar.a(i);
        return aVar;
    }
}
