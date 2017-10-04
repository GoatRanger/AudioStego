package dji.common.util;

import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBSDRBandwidth;

public class SDRLinkHelper {
    public static final float ORIGINAL_NF_START_INDEX = 2400.5f;
    public static final int RANGE_SIZE_10MHZ = 5;
    public static final int RANGE_SIZE_20MHZ = 10;

    public static float convertFrequencyFormFrequencyPointIndex(int i) {
        if (i < 1001 || i > 1084) {
            return -1.0f;
        }
        return ORIGINAL_NF_START_INDEX + ((float) (i - 1001));
    }

    public static int convertFrequencyPointIndexFromFrequency(float f) {
        if (f < ORIGINAL_NF_START_INDEX || ((double) f) > 2483.5d) {
            return 0;
        }
        float f2 = (f - ORIGINAL_NF_START_INDEX) + 1001.0f;
        if (((double) f2) < 0.0d) {
            f2 = 0.0f;
        }
        int floor = (int) Math.floor((double) f2);
        int ceil = (int) Math.ceil((double) f2);
        if (floor == ceil) {
            return (int) f2;
        }
        if (Math.abs(convertFrequencyFormFrequencyPointIndex(floor) - f) <= Math.abs(convertFrequencyFormFrequencyPointIndex(ceil) - f)) {
            return floor;
        }
        return ceil;
    }

    public static float[] convertValidIndexRange(LBSDRBandwidth lBSDRBandwidth, LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode) {
        float[] fArr = new float[]{ORIGINAL_NF_START_INDEX, 2481.5f};
        if (!(lBSDRBandwidth == null || lBAirLinkChannelSelectionMode == null)) {
            if (lBSDRBandwidth.equals(LBSDRBandwidth.Bandwidth10MHz) && lBAirLinkChannelSelectionMode.equals(LBAirLinkChannelSelectionMode.Manual)) {
                fArr[0] = convertFrequencyFormFrequencyPointIndex(1007);
                fArr[1] = convertFrequencyFormFrequencyPointIndex(1072);
            } else if (lBSDRBandwidth.equals(LBSDRBandwidth.Bandwidth20MHz) && lBAirLinkChannelSelectionMode.equals(LBAirLinkChannelSelectionMode.Manual)) {
                fArr[0] = convertFrequencyFormFrequencyPointIndex(1012);
                fArr[1] = convertFrequencyFormFrequencyPointIndex(1067);
            }
        }
        return fArr;
    }
}
