package dji.common.airlink;

public class SDRHdOffsetParams {
    public int mPathLossOffset;
    public int mRcLinkOffset;
    public int mTxPowerOffset;

    public SDRHdOffsetParams(int i, int i2, int i3) {
        this.mPathLossOffset = i;
        this.mRcLinkOffset = i2;
        this.mTxPowerOffset = i3;
    }
}
