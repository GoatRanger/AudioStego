package dji.common.util;

public class DJIParamCapability {
    protected boolean isSupported;

    public DJIParamCapability(boolean z) {
        this.isSupported = z;
    }

    public boolean isSuppported() {
        return this.isSupported;
    }
}
