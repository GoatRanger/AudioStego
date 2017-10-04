package dji.common.airlink;

public class DJISignalInformation {
    private int mPercent;
    private int mPower;

    void setPercent(int i) {
        this.mPercent = i;
    }

    void setPower(int i) {
        this.mPower = i;
    }

    public int getPercent() {
        return this.mPercent;
    }

    public int getPower() {
        return this.mPower;
    }

    public DJISignalInformation(int i, int i2) {
        this.mPercent = i;
        this.mPower = i2;
    }
}
