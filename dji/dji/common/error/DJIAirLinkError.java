package dji.common.error;

import dji.midware.data.config.P3.a;

public class DJIAirLinkError extends DJIError {
    public static final DJIAirLinkError IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE = new DJIAirLinkError("Cannot set the parameters in this state");
    public static final DJIAirLinkError IMAGE_TRANSMITTER_INVALID_PARAMETER = new DJIAirLinkError("The input parameter is out of bound or invalid.");
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

    private DJIAirLinkError() {
    }

    private DJIAirLinkError(String str) {
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
