package dji.common.camera;

public class CameraLensFocusAssistant {
    private boolean enabledAF;
    private boolean enabledMF;

    public void setEnabledAF(boolean z) {
        this.enabledAF = z;
    }

    public void setEnabledMF(boolean z) {
        this.enabledMF = z;
    }

    public boolean isEnabledAF() {
        return this.enabledAF;
    }

    public boolean isEnabledMF() {
        return this.enabledMF;
    }
}
