package dji.common.error;

import dji.midware.data.config.P3.a;

public class DJIBatteryError extends DJIError {
    public static final DJIBatteryError BATTERY_GET_SMART_BATTERY_INFO_FAILED = new DJIBatteryError("Get smart battery info failed");
    public static final DJIBatteryError UPDATE_WRONG = new DJIBatteryError("Update error");
    private String mDescription;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$config$P3$Ccode = new int[a.values().length];

        static {
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private DJIBatteryError(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public static DJIError getDJIError(a aVar) {
        if (COMMON_UNKNOWN != DJIError.getDJIError(aVar)) {
            return DJIError.getDJIError(aVar);
        }
        switch (AnonymousClass1.$SwitchMap$dji$midware$data$config$P3$Ccode[aVar.ordinal()]) {
            case 1:
                return COMMON_UNKNOWN;
            default:
                return COMMON_UNKNOWN;
        }
    }
}
