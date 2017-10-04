package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyeGetPushEasySelfCalibration extends n {
    private static DataEyeGetPushEasySelfCalibration instance = null;

    public enum CaliStatusCode {
        NotCalibrating(0),
        WaitingMove(1),
        CollectImage(2),
        Caculating(3),
        WaitingNext(99),
        Success(100),
        MoveWrong(-1),
        MoveTooFast(-2),
        GroundDetailTooLess(-3),
        LightEnviromentInvalid(-4),
        FeatureLess(-5),
        DiffLarge(-6),
        AlreadyCali(-10),
        CalulateTimeOut(-100),
        ParamDiffSerious(-101),
        OTHER(100);
        
        private final int data;

        private CaliStatusCode(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static CaliStatusCode find(int i) {
            CaliStatusCode caliStatusCode = NotCalibrating;
            for (CaliStatusCode caliStatusCode2 : values()) {
                if (caliStatusCode2._equals(i)) {
                    return caliStatusCode2;
                }
            }
            return caliStatusCode;
        }
    }

    public enum VisionSensorType {
        None(0),
        Bottom(1),
        Forward(2),
        Right(3),
        Backward(4),
        Left(5),
        Top(6),
        OTHER(100);
        
        private final int data;

        private VisionSensorType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static VisionSensorType find(int i) {
            VisionSensorType visionSensorType = None;
            for (VisionSensorType visionSensorType2 : values()) {
                if (visionSensorType2._equals(i)) {
                    return visionSensorType2;
                }
            }
            return visionSensorType;
        }
    }

    public static synchronized DataEyeGetPushEasySelfCalibration getInstance() {
        DataEyeGetPushEasySelfCalibration dataEyeGetPushEasySelfCalibration;
        synchronized (DataEyeGetPushEasySelfCalibration.class) {
            if (instance == null) {
                instance = new DataEyeGetPushEasySelfCalibration();
            }
            dataEyeGetPushEasySelfCalibration = instance;
        }
        return dataEyeGetPushEasySelfCalibration;
    }

    public int getTinkCount() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public CaliStatusCode getCaliStatusCode() {
        if (this._recData == null || this._recData.length <= 1) {
            return CaliStatusCode.NotCalibrating;
        }
        return CaliStatusCode.find(this._recData[1]);
    }

    public VisionSensorType getSensorType() {
        return VisionSensorType.find(((Integer) get(2, 1, Integer.class)).intValue());
    }

    public int getProgress() {
        if (this._recData == null || this._recData.length <= 3) {
            return 0;
        }
        return this._recData[3];
    }

    protected void doPack() {
    }
}
