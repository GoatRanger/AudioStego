package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataSmartBatteryGetPushReArrangement extends n {
    private static DataSmartBatteryGetPushReArrangement mInstance = null;

    public static class ReArrangement {
        public int dstIndex;
        public ReArrangementOption option;
        public int srcIndex;
    }

    public enum ReArrangementOption {
        None,
        PlugOut,
        Switch,
        Move
    }

    public static DataSmartBatteryGetPushReArrangement getInstance() {
        if (mInstance == null) {
            mInstance = new DataSmartBatteryGetPushReArrangement();
        }
        return mInstance;
    }

    public ReArrangement[] getReArrangement() {
        ReArrangement[] reArrangementArr = new ReArrangement[6];
        for (int i = 0; i < 6; i++) {
            reArrangementArr[i] = new ReArrangement();
            switch (((Integer) get(i * 3, 1, Integer.class)).intValue()) {
                case 0:
                    reArrangementArr[i].option = ReArrangementOption.None;
                    break;
                case 1:
                    reArrangementArr[i].option = ReArrangementOption.PlugOut;
                    break;
                case 2:
                    reArrangementArr[i].option = ReArrangementOption.Switch;
                    break;
                case 3:
                    reArrangementArr[i].option = ReArrangementOption.Move;
                    break;
                default:
                    break;
            }
            reArrangementArr[i].srcIndex = ((Integer) get((i * 3) + 1, 1, Integer.class)).intValue();
            reArrangementArr[i].dstIndex = ((Integer) get((i * 3) + 2, 1, Integer.class)).intValue();
        }
        return reArrangementArr;
    }

    protected void doPack() {
    }
}
