package dji.common.error;

import com.facebook.internal.a;

public class DJIGimbalError extends DJIError {
    public static final DJIGimbalError GIMBAL_CANNOT_SET_PARAMETERS_IN_THIS_STATE = new DJIGimbalError("Cannot set the parameters in this state");
    public static final DJIGimbalError GIMBAL_RESULT_FAILED = new DJIGimbalError(a.u);
    private String mDescription;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$config$P3$Ccode = new int[dji.midware.data.config.P3.a.values().length];

        static {
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[dji.midware.data.config.P3.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private DJIGimbalError() {
    }

    private DJIGimbalError(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public static DJIError getDJIError(dji.midware.data.config.P3.a aVar) {
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
