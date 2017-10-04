package dji.common.camera;

import android.util.Log;
import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.common.camera.DJICameraSettingsDef.CameraApertureRange;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensation;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensationRange;
import dji.common.camera.DJICameraSettingsDef.CameraExposureMode;
import dji.common.camera.DJICameraSettingsDef.CameraExposureModeRange;
import dji.common.camera.DJICameraSettingsDef.CameraISO;
import dji.common.camera.DJICameraSettingsDef.CameraISORange;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.camera.DJICameraSettingsDef.CameraModeRange;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeed;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeedRange;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolutionAndFrameRateRange;
import dji.midware.c.a;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.hardware.abstractions.b.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class CameraParamRangeManager {
    private static final String TAG = "CameraParamRangeManager";
    private CameraApertureRange cameraApertureRange = null;
    private CameraExposureCompensationRange cameraExposureCompensationRange = null;
    private CameraExposureModeRange cameraExposureModeRange = null;
    private CameraISORange cameraISORange = null;
    private CameraModeRange cameraModeRange = null;
    private CameraShutterSpeedRange cameraShutterSpeedRange = null;
    private CameraVideoResolutionAndFrameRateRange cameraVideoResolutionAndFrameRateRange = null;
    private c defaultKey;
    private ArrayList<d> listeners;
    private f onValueChangeListener;

    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType = new int[CameraType.values().length];

        static {
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC550.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC300X.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC330X.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC300XW.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC550Raw.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC350.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeCV600.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC300S.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeFC260.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeTau640.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[CameraType.DJICameraTypeTau336.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType = new int[a.c.values().length];
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.Inspire.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.M100.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.M600.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.OSMO.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.P3x.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.P3w.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.P3c.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.P3s.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$dji$midware$component$DJIComponentManager$PlatformType[a.c.P4.ordinal()] = 9;
            } catch (NoSuchFieldError e20) {
            }
        }
    }

    public CameraParamRangeManager(f fVar, c cVar) {
        this.onValueChangeListener = fVar;
        this.defaultKey = cVar;
        triggerUpdateAll();
        this.listeners = new ArrayList();
        addListenersForISORange(cVar.c(b.m), cVar.c(b.b), cVar.c(b.bX));
        addListenersForExposureModeRange(cVar.c(b.bX));
        addListenersForCameraModeRange(cVar.c(b.bX));
        addListenersForVideoResolutionFpsRange(cVar.c(b.bX), cVar.c(b.g));
        addListenersForCameraShutterSpeedRange(cVar.c(b.m), cVar.c(b.b), cVar.c(b.d));
        addListenersForCameraApertureRange(cVar.c(b.bX), cVar.c(b.m));
    }

    private void addListenersForISORange(c... cVarArr) {
        d anonymousClass1 = new d() {
            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                CameraParamRangeManager.this.updateCameraISORange();
            }
        };
        for (c addOneListener : cVarArr) {
            addOneListener(addOneListener, anonymousClass1);
        }
    }

    private void addListenersForExposureCompensationRange(c... cVarArr) {
        d anonymousClass2 = new d() {
            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                CameraParamRangeManager.this.updateCameraExposureCompensationRange();
            }
        };
        for (c addOneListener : cVarArr) {
            addOneListener(addOneListener, anonymousClass2);
        }
    }

    private void addListenersForExposureModeRange(c... cVarArr) {
        d anonymousClass3 = new d() {
            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                CameraParamRangeManager.this.updateCameraExposureModeRange();
            }
        };
        for (c addOneListener : cVarArr) {
            addOneListener(addOneListener, anonymousClass3);
        }
    }

    private void addListenersForCameraModeRange(c... cVarArr) {
        d anonymousClass4 = new d() {
            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                CameraParamRangeManager.this.updateCameraModeRange();
            }
        };
        for (c addOneListener : cVarArr) {
            addOneListener(addOneListener, anonymousClass4);
        }
    }

    private void addListenersForVideoResolutionFpsRange(c... cVarArr) {
        d anonymousClass5 = new d() {
            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                CameraParamRangeManager.this.updateVideoResolutionFpsRange();
            }
        };
        for (c addOneListener : cVarArr) {
            addOneListener(addOneListener, anonymousClass5);
        }
    }

    private void addListenersForCameraShutterSpeedRange(c... cVarArr) {
        d anonymousClass6 = new d() {
            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                CameraParamRangeManager.this.updateCameraShutterSpeedRange();
            }
        };
        for (c addOneListener : cVarArr) {
            addOneListener(addOneListener, anonymousClass6);
        }
    }

    private void addListenersForCameraApertureRange(c... cVarArr) {
        d anonymousClass7 = new d() {
            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                CameraParamRangeManager.this.updateCameraApertureRange();
            }
        };
        for (c addOneListener : cVarArr) {
            addOneListener(addOneListener, anonymousClass7);
        }
    }

    private void addOneListener(c cVar, d dVar) {
        DJISDKCache.getInstance().startListeningForUpdates(cVar, dVar, false);
        this.listeners.add(dVar);
    }

    private void updateCameraISORange() {
        CameraISORange cameraISORange = new CameraISORange(2);
        Object[] objArr = null;
        if (a.getInstance().i()) {
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
            ExposureMode exposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
            if (cameraType == CameraType.DJICameraTypeFC260 || cameraType == CameraType.DJICameraTypeFC300S || cameraType == CameraType.DJICameraTypeFC300X || cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeFC300XW || cameraType == CameraType.DJICameraTypeFC330X) {
                objArr = ExposureMode.e == exposureMode ? mode == MODE.RECORD ? new CameraISO[]{CameraISO.ISO_100, CameraISO.ISO_200, CameraISO.ISO_400, CameraISO.ISO_800, CameraISO.ISO_1600, CameraISO.ISO_3200} : mode == MODE.TAKEPHOTO ? new CameraISO[]{CameraISO.ISO_100, CameraISO.ISO_200, CameraISO.ISO_400, CameraISO.ISO_800, CameraISO.ISO_1600} : new CameraISO[0] : new CameraISO[]{CameraISO.Auto};
            } else if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw) {
                objArr = mode == MODE.RECORD ? ExposureMode.e == exposureMode ? new CameraISO[]{CameraISO.ISO_100, CameraISO.ISO_200, CameraISO.ISO_400, CameraISO.ISO_800, CameraISO.ISO_1600, CameraISO.ISO_3200, CameraISO.ISO_6400} : new CameraISO[]{CameraISO.ISO_100, CameraISO.ISO_200, CameraISO.ISO_400, CameraISO.ISO_800, CameraISO.ISO_1600, CameraISO.ISO_3200, CameraISO.ISO_6400, CameraISO.Auto} : ExposureMode.e == exposureMode ? new CameraISO[]{CameraISO.ISO_100, CameraISO.ISO_200, CameraISO.ISO_400, CameraISO.ISO_800, CameraISO.ISO_1600, CameraISO.ISO_3200, CameraISO.ISO_6400, CameraISO.ISO_12800, CameraISO.ISO_25600} : new CameraISO[]{CameraISO.ISO_100, CameraISO.ISO_200, CameraISO.ISO_400, CameraISO.ISO_800, CameraISO.ISO_1600, CameraISO.ISO_3200, CameraISO.ISO_6400, CameraISO.ISO_12800, CameraISO.ISO_25600, CameraISO.Auto};
            } else {
                objArr = null;
            }
        }
        if (objArr != null) {
            cameraISORange.getRangeList().addAll(Arrays.asList(objArr));
            if (this.cameraISORange == null || !this.cameraISORange.equals(cameraISORange)) {
                this.cameraISORange = cameraISORange;
                this.onValueChangeListener.b(cameraISORange, this.defaultKey.c(b.I));
            }
        }
    }

    private void updateCameraExposureCompensationRange() {
        CameraExposureCompensationRange cameraExposureCompensationRange = new CameraExposureCompensationRange(2);
        Object[] objArr = null;
        if (a.getInstance().i()) {
            a.c a = a.getInstance().a();
            DataCameraGetPushStateInfo.getInstance().getCameraType();
            switch (a) {
                case Inspire:
                case M100:
                case M600:
                case OSMO:
                case P3x:
                case P3w:
                case P3c:
                case P3s:
                case P4:
                    objArr = new CameraExposureCompensation[]{CameraExposureCompensation.N_3_0, CameraExposureCompensation.N_2_7, CameraExposureCompensation.N_2_3, CameraExposureCompensation.N_2_0, CameraExposureCompensation.N_1_7, CameraExposureCompensation.N_1_3, CameraExposureCompensation.N_1_0, CameraExposureCompensation.N_0_7, CameraExposureCompensation.N_0_3, CameraExposureCompensation.N_0_0, CameraExposureCompensation.P_0_3, CameraExposureCompensation.P_0_7, CameraExposureCompensation.P_1_0, CameraExposureCompensation.P_1_3, CameraExposureCompensation.P_1_7, CameraExposureCompensation.P_2_0, CameraExposureCompensation.P_2_3, CameraExposureCompensation.P_2_7, CameraExposureCompensation.P_3_0};
                    break;
            }
        }
        if (objArr != null) {
            cameraExposureCompensationRange.getRangeList().addAll(Arrays.asList(objArr));
            if (this.cameraExposureCompensationRange == null || !this.cameraExposureCompensationRange.equals(cameraExposureCompensationRange)) {
                this.cameraExposureCompensationRange = cameraExposureCompensationRange;
                this.onValueChangeListener.b(cameraExposureCompensationRange, this.defaultKey.c(b.K));
            }
        }
    }

    private void updateCameraExposureModeRange() {
        CameraExposureModeRange cameraExposureModeRange = new CameraExposureModeRange(2);
        Object[] objArr = null;
        if (a.getInstance().i()) {
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            objArr = (cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeFC350) ? new CameraExposureMode[]{CameraExposureMode.Manual, CameraExposureMode.Program, CameraExposureMode.ShutterPriority} : (cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeFC550) ? new CameraExposureMode[]{CameraExposureMode.Manual, CameraExposureMode.Program, CameraExposureMode.ShutterPriority, CameraExposureMode.AperturePriority} : new CameraExposureMode[]{CameraExposureMode.Manual, CameraExposureMode.Program, CameraExposureMode.ShutterPriority};
        }
        if (objArr != null) {
            cameraExposureModeRange.getRangeList().addAll(Arrays.asList(objArr));
            if (this.cameraExposureModeRange == null || !this.cameraExposureModeRange.equals(cameraExposureModeRange)) {
                this.cameraExposureModeRange = cameraExposureModeRange;
                this.onValueChangeListener.b(cameraExposureModeRange, this.defaultKey.c(b.n));
            }
        }
    }

    private void updateCameraModeRange() {
        CameraModeRange cameraModeRange = new CameraModeRange(2);
        Object[] objArr = null;
        a.c a = a.getInstance().a();
        switch (AnonymousClass8.$SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[DataCameraGetPushStateInfo.getInstance().getCameraType().ordinal()]) {
            case 1:
                objArr = new CameraMode[]{CameraMode.ShootPhoto, CameraMode.RecordVideo, CameraMode.Playback};
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                objArr = new CameraMode[]{CameraMode.ShootPhoto, CameraMode.RecordVideo, CameraMode.Playback, CameraMode.MediaDownload};
                break;
            case 6:
            case 7:
                if (a != a.c.OSMO) {
                    objArr = new CameraMode[]{CameraMode.ShootPhoto, CameraMode.RecordVideo, CameraMode.Playback, CameraMode.MediaDownload};
                    break;
                } else {
                    objArr = new CameraMode[]{CameraMode.ShootPhoto, CameraMode.RecordVideo, CameraMode.MediaDownload};
                    break;
                }
            case 8:
            case 9:
            case 10:
            case 11:
                objArr = new CameraMode[]{CameraMode.ShootPhoto, CameraMode.RecordVideo, CameraMode.MediaDownload};
                break;
        }
        if (objArr != null) {
            cameraModeRange.getRangeList().addAll(Arrays.asList(objArr));
            if (this.cameraModeRange == null || !this.cameraModeRange.equals(cameraModeRange)) {
                this.cameraModeRange = cameraModeRange;
                this.onValueChangeListener.b(cameraModeRange, this.defaultKey.c(b.c));
            }
        }
    }

    private void updateVideoResolutionFpsRange() {
        CameraVideoResolutionAndFrameRateRange cameraVideoResolutionAndFrameRateRange = new CameraVideoResolutionAndFrameRateRange(new HashSet(10));
        Object[] objArr = null;
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        int videoStandard = DataCameraGetPushShotParams.getInstance().getVideoStandard();
        a.c a = a.getInstance().a();
        switch (AnonymousClass8.$SwitchMap$dji$midware$data$model$P3$DataCameraGetPushStateInfo$CameraType[instance.getCameraType().ordinal()]) {
            case 1:
            case 5:
                if (videoStandard != 1) {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                } else {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                }
            case 2:
            case 4:
                if (videoStandard != 1) {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                } else {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                }
            case 3:
                if (videoStandard != 1) {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_120fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                } else {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_120fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                }
            case 6:
            case 7:
                switch (a) {
                    case Inspire:
                    case M600:
                        if (videoStandard != 1) {
                            objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                            break;
                        } else {
                            objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                            break;
                        }
                    case OSMO:
                        if (videoStandard != 1) {
                            objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_120fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                            break;
                        } else {
                            objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_4096x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_3840x2160, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_120fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                            break;
                        }
                    default:
                        break;
                }
            case 8:
                if (videoStandard != 1) {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                } else {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                }
            case 9:
                if (videoStandard != 1) {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_50fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_25fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                } else {
                    objArr = new CameraVideoResolutionAndFrameRate[]{new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_2704X1520, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_24fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_60fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_48fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_30fps), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1280x720, CameraVideoFrameRate.FrameRate_24fps)};
                    break;
                }
            default:
                objArr = null;
                break;
        }
        if (objArr != null) {
            cameraVideoResolutionAndFrameRateRange.getRangeList().addAll(Arrays.asList(objArr));
            if (this.cameraVideoResolutionAndFrameRateRange == null || !this.cameraVideoResolutionAndFrameRateRange.equals(cameraVideoResolutionAndFrameRateRange)) {
                this.cameraVideoResolutionAndFrameRateRange = cameraVideoResolutionAndFrameRateRange;
                this.onValueChangeListener.b(cameraVideoResolutionAndFrameRateRange, this.defaultKey.c(b.e));
            }
        }
    }

    private void updateCameraShutterSpeedRange() {
        CameraShutterSpeedRange cameraShutterSpeedRange = new CameraShutterSpeedRange(2);
        Object[] objArr = null;
        ExposureMode exposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
        if (a.getInstance().i()) {
            if (exposureMode == ExposureMode.c || exposureMode == ExposureMode.e) {
                MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
                if (mode == MODE.TAKEPHOTO) {
                    objArr = new CameraShutterSpeed[]{CameraShutterSpeed.ShutterSpeed1_8000, CameraShutterSpeed.ShutterSpeed1_6400, CameraShutterSpeed.ShutterSpeed1_5000, CameraShutterSpeed.ShutterSpeed1_4000, CameraShutterSpeed.ShutterSpeed1_3200, CameraShutterSpeed.ShutterSpeed1_2500, CameraShutterSpeed.ShutterSpeed1_2000, CameraShutterSpeed.ShutterSpeed1_1600, CameraShutterSpeed.ShutterSpeed1_1250, CameraShutterSpeed.ShutterSpeed1_1000, CameraShutterSpeed.ShutterSpeed1_800, CameraShutterSpeed.ShutterSpeed1_640, CameraShutterSpeed.ShutterSpeed1_500, CameraShutterSpeed.ShutterSpeed1_400, CameraShutterSpeed.ShutterSpeed1_320, CameraShutterSpeed.ShutterSpeed1_240, CameraShutterSpeed.ShutterSpeed1_200, CameraShutterSpeed.ShutterSpeed1_160, CameraShutterSpeed.ShutterSpeed1_120, CameraShutterSpeed.ShutterSpeed1_100, CameraShutterSpeed.ShutterSpeed1_80, CameraShutterSpeed.ShutterSpeed1_60, CameraShutterSpeed.ShutterSpeed1_50, CameraShutterSpeed.ShutterSpeed1_40, CameraShutterSpeed.ShutterSpeed1_30, CameraShutterSpeed.ShutterSpeed1_25, CameraShutterSpeed.ShutterSpeed1_20, CameraShutterSpeed.ShutterSpeed1_15, CameraShutterSpeed.ShutterSpeed1_12p5, CameraShutterSpeed.ShutterSpeed1_10, CameraShutterSpeed.ShutterSpeed1_8, CameraShutterSpeed.ShutterSpeed1_6p25, CameraShutterSpeed.ShutterSpeed1_5, CameraShutterSpeed.ShutterSpeed1_4, CameraShutterSpeed.ShutterSpeed1_3, CameraShutterSpeed.ShutterSpeed1_2p5, CameraShutterSpeed.ShutterSpeed1_2, CameraShutterSpeed.ShutterSpeed1_1p67, CameraShutterSpeed.ShutterSpeed1_1p25, CameraShutterSpeed.ShutterSpeed1p0, CameraShutterSpeed.ShutterSpeed1p3, CameraShutterSpeed.ShutterSpeed1p6, CameraShutterSpeed.ShutterSpeed2p0, CameraShutterSpeed.ShutterSpeed2p5, CameraShutterSpeed.ShutterSpeed3p0, CameraShutterSpeed.ShutterSpeed3p2, CameraShutterSpeed.ShutterSpeed4p0, CameraShutterSpeed.ShutterSpeed5p0, CameraShutterSpeed.ShutterSpeed6p0, CameraShutterSpeed.ShutterSpeed7p0, CameraShutterSpeed.ShutterSpeed8p0};
                } else if (mode == MODE.RECORD) {
                    switch (DataCameraGetPushShotParams.getInstance().getVideoFps()) {
                        case 1:
                        case 2:
                            objArr = new CameraShutterSpeed[]{CameraShutterSpeed.ShutterSpeed1_8000, CameraShutterSpeed.ShutterSpeed1_6400, CameraShutterSpeed.ShutterSpeed1_5000, CameraShutterSpeed.ShutterSpeed1_4000, CameraShutterSpeed.ShutterSpeed1_3200, CameraShutterSpeed.ShutterSpeed1_2500, CameraShutterSpeed.ShutterSpeed1_2000, CameraShutterSpeed.ShutterSpeed1_1600, CameraShutterSpeed.ShutterSpeed1_1250, CameraShutterSpeed.ShutterSpeed1_1000, CameraShutterSpeed.ShutterSpeed1_800, CameraShutterSpeed.ShutterSpeed1_640, CameraShutterSpeed.ShutterSpeed1_500, CameraShutterSpeed.ShutterSpeed1_400, CameraShutterSpeed.ShutterSpeed1_320, CameraShutterSpeed.ShutterSpeed1_240, CameraShutterSpeed.ShutterSpeed1_200, CameraShutterSpeed.ShutterSpeed1_160, CameraShutterSpeed.ShutterSpeed1_120, CameraShutterSpeed.ShutterSpeed1_100, CameraShutterSpeed.ShutterSpeed1_80, CameraShutterSpeed.ShutterSpeed1_60, CameraShutterSpeed.ShutterSpeed1_50, CameraShutterSpeed.ShutterSpeed1_40, CameraShutterSpeed.ShutterSpeed1_30, CameraShutterSpeed.ShutterSpeed1_25};
                            break;
                        case 3:
                            objArr = new CameraShutterSpeed[]{CameraShutterSpeed.ShutterSpeed1_8000, CameraShutterSpeed.ShutterSpeed1_6400, CameraShutterSpeed.ShutterSpeed1_5000, CameraShutterSpeed.ShutterSpeed1_4000, CameraShutterSpeed.ShutterSpeed1_3200, CameraShutterSpeed.ShutterSpeed1_2500, CameraShutterSpeed.ShutterSpeed1_2000, CameraShutterSpeed.ShutterSpeed1_1600, CameraShutterSpeed.ShutterSpeed1_1250, CameraShutterSpeed.ShutterSpeed1_1000, CameraShutterSpeed.ShutterSpeed1_800, CameraShutterSpeed.ShutterSpeed1_640, CameraShutterSpeed.ShutterSpeed1_500, CameraShutterSpeed.ShutterSpeed1_400, CameraShutterSpeed.ShutterSpeed1_320, CameraShutterSpeed.ShutterSpeed1_240, CameraShutterSpeed.ShutterSpeed1_200, CameraShutterSpeed.ShutterSpeed1_160, CameraShutterSpeed.ShutterSpeed1_120, CameraShutterSpeed.ShutterSpeed1_100, CameraShutterSpeed.ShutterSpeed1_80, CameraShutterSpeed.ShutterSpeed1_60, CameraShutterSpeed.ShutterSpeed1_50, CameraShutterSpeed.ShutterSpeed1_40, CameraShutterSpeed.ShutterSpeed1_30};
                            break;
                        case 4:
                        case 5:
                            objArr = new CameraShutterSpeed[]{CameraShutterSpeed.ShutterSpeed1_8000, CameraShutterSpeed.ShutterSpeed1_6400, CameraShutterSpeed.ShutterSpeed1_5000, CameraShutterSpeed.ShutterSpeed1_4000, CameraShutterSpeed.ShutterSpeed1_3200, CameraShutterSpeed.ShutterSpeed1_2500, CameraShutterSpeed.ShutterSpeed1_2000, CameraShutterSpeed.ShutterSpeed1_1600, CameraShutterSpeed.ShutterSpeed1_1250, CameraShutterSpeed.ShutterSpeed1_1000, CameraShutterSpeed.ShutterSpeed1_800, CameraShutterSpeed.ShutterSpeed1_640, CameraShutterSpeed.ShutterSpeed1_500, CameraShutterSpeed.ShutterSpeed1_400, CameraShutterSpeed.ShutterSpeed1_320, CameraShutterSpeed.ShutterSpeed1_240, CameraShutterSpeed.ShutterSpeed1_200, CameraShutterSpeed.ShutterSpeed1_160, CameraShutterSpeed.ShutterSpeed1_120, CameraShutterSpeed.ShutterSpeed1_100, CameraShutterSpeed.ShutterSpeed1_80, CameraShutterSpeed.ShutterSpeed1_60, CameraShutterSpeed.ShutterSpeed1_50};
                            break;
                        case 6:
                            objArr = new CameraShutterSpeed[]{CameraShutterSpeed.ShutterSpeed1_8000, CameraShutterSpeed.ShutterSpeed1_6400, CameraShutterSpeed.ShutterSpeed1_5000, CameraShutterSpeed.ShutterSpeed1_4000, CameraShutterSpeed.ShutterSpeed1_3200, CameraShutterSpeed.ShutterSpeed1_2500, CameraShutterSpeed.ShutterSpeed1_2000, CameraShutterSpeed.ShutterSpeed1_1600, CameraShutterSpeed.ShutterSpeed1_1250, CameraShutterSpeed.ShutterSpeed1_1000, CameraShutterSpeed.ShutterSpeed1_800, CameraShutterSpeed.ShutterSpeed1_640, CameraShutterSpeed.ShutterSpeed1_500, CameraShutterSpeed.ShutterSpeed1_400, CameraShutterSpeed.ShutterSpeed1_320, CameraShutterSpeed.ShutterSpeed1_240, CameraShutterSpeed.ShutterSpeed1_200, CameraShutterSpeed.ShutterSpeed1_160, CameraShutterSpeed.ShutterSpeed1_120, CameraShutterSpeed.ShutterSpeed1_100, CameraShutterSpeed.ShutterSpeed1_80, CameraShutterSpeed.ShutterSpeed1_60};
                            break;
                        default:
                            objArr = null;
                            break;
                    }
                } else {
                    objArr = null;
                }
            } else {
                objArr = null;
            }
        }
        if (objArr != null) {
            cameraShutterSpeedRange.getRangeList().addAll(Arrays.asList(objArr));
            if (this.cameraShutterSpeedRange == null || !this.cameraShutterSpeedRange.equals(cameraShutterSpeedRange)) {
                this.cameraShutterSpeedRange = cameraShutterSpeedRange;
                this.onValueChangeListener.b(cameraShutterSpeedRange, this.defaultKey.c(b.G));
            }
        }
    }

    private void updateCameraApertureRange() {
        Object[] objArr = null;
        CameraApertureRange cameraApertureRange = new CameraApertureRange(2);
        if (a.getInstance().i()) {
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            ExposureMode exposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
            if ((cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw) && (ExposureMode.e == exposureMode || ExposureMode.d == exposureMode)) {
                objArr = new CameraAperture[]{CameraAperture.F_1p7, CameraAperture.F_1p8, CameraAperture.F_2p0, CameraAperture.F_2p2, CameraAperture.F_2p5, CameraAperture.F_2p8, CameraAperture.F_3p2, CameraAperture.F_3p5, CameraAperture.F_4p0, CameraAperture.F_4p5, CameraAperture.F_5p0, CameraAperture.F_5p6, CameraAperture.F_6p3, CameraAperture.F_7p1, CameraAperture.F_8p0, CameraAperture.F_9p0, CameraAperture.F_10p0, CameraAperture.F_11p0, CameraAperture.F_13p0, CameraAperture.F_14p0, CameraAperture.F_16p0};
            }
        }
        if (objArr != null) {
            cameraApertureRange.getRangeList().addAll(Arrays.asList(objArr));
            if (this.cameraApertureRange == null || !this.cameraApertureRange.equals(cameraApertureRange)) {
                this.cameraApertureRange = cameraApertureRange;
                this.onValueChangeListener.b(cameraApertureRange, this.defaultKey.c(b.E));
            }
        }
    }

    private void triggerUpdateAll() {
        try {
            updateCameraISORange();
            updateCameraExposureModeRange();
            updateCameraExposureCompensationRange();
            updateCameraModeRange();
            updateVideoResolutionFpsRange();
            updateCameraShutterSpeedRange();
            updateCameraApertureRange();
        } catch (Exception e) {
            Log.e(TAG, "init RangeManager fail in triggerUpdateAll method");
            e.printStackTrace();
        }
    }

    public void onDestory() {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            DJISDKCache.getInstance().stopListening((d) it.next());
        }
        this.listeners = null;
        this.onValueChangeListener = null;
    }
}
