package dji.common.error;

import dji.midware.data.config.P3.a;

public class DJIGeoError extends DJIError {
    public static final DJIGeoError COULD_NOT_CONNECT_TO_INTERNET_FOR_PULLING_DATA = new DJIGeoError("Could not connect to the Internet while SDK try to pull the latest cached data from server.");
    public static final DJIGeoError COULD_NOT_FIND_UNLOCKED_RECORD_IN_THE_SERVER = new DJIGeoError("Could not find unlocked record in the server.");
    public static final DJIGeoError NO_DATA_IN_DATABASE = new DJIGeoError("No data in database");
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

    private DJIGeoError() {
    }

    private DJIGeoError(String str) {
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
                return COMMON_TIMEOUT;
            default:
                return COMMON_UNKNOWN;
        }
    }
}
