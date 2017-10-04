package dji.sdk.api.simulator;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSimulatorConnectHeartPacket;
import dji.midware.data.model.P3.DataSimulatorGetPushConnectHeartPacket;
import dji.midware.data.model.P3.DataSimulatorGetPushFlightStatusParams;
import dji.midware.data.model.P3.DataSimulatorGetPushMainControllerReturnParams;
import dji.midware.data.model.P3.DataSimulatorRequestMainControllerParams;
import dji.midware.data.model.P3.DataSimulatorSetGetWind;
import dji.midware.data.model.P3.DataSimulatorSimulateFlightCommend;
import dji.midware.e.d;
import dji.sdk.api.simulator.DJISimulatorTypeDef.DJISimulatorDroneType;
import dji.sdk.api.simulator.DJISimulatorTypeDef.DJISimulatorProductType;
import dji.thirdparty.a.c;
import java.util.Timer;
import java.util.TimerTask;

public class DJISimulator {
    private static final int HEART_PACKET_INTERVAL = 1000;
    private boolean isHeartConnectionBuild = false;
    private boolean isRequest = false;
    private d mCommonDataCallBack = new d() {
        public void onSuccess(Object obj) {
        }

        public void onFailure(a aVar) {
        }
    };
    private DataSimulatorSetGetWind mSetGetWind = DataSimulatorSetGetWind.getInstance();
    private DataSimulatorSimulateFlightCommend mSimulateFlightCommend = DataSimulatorSimulateFlightCommend.getInstance();
    private DataSimulatorConnectHeartPacket mSimulatorConnectHeartPacket = DataSimulatorConnectHeartPacket.getInstance();
    private DJISimulatorFlycStatus mSimulatorFlycStatus = new DJISimulatorFlycStatus();
    private DJISimulatorGetPushFlycStatusCallBack mSimulatorGetPushFlycStatusCallBack;
    private DJISimulatorGetPushTypeCallBack mSimulatorGetPushTypeCallBack;
    private DataSimulatorRequestMainControllerParams mSimulatorRequestMainControllerParams = DataSimulatorRequestMainControllerParams.getInstance();
    private Timer mTimer;

    class PingHeart extends TimerTask {
        PingHeart() {
        }

        public void run() {
            if (DJISimulator.this.isHeartConnectionBuild) {
                DJISimulator.this.mSimulatorConnectHeartPacket.a(0).start(new d() {
                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(a aVar) {
                    }
                });
                return;
            }
            DJISimulator.this.isHeartConnectionBuild = true;
            DJISimulator.this.mSimulatorConnectHeartPacket.a(1).start(new d() {
                public void onSuccess(Object obj) {
                }

                public void onFailure(a aVar) {
                }
            });
        }
    }

    public void destory() {
        this.mSimulatorGetPushFlycStatusCallBack = null;
        this.mSimulatorGetPushTypeCallBack = null;
        this.mCommonDataCallBack = null;
        this.mSimulatorFlycStatus = null;
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer.purge();
            this.mTimer = null;
        }
        c.a().d(this);
    }

    public DJISimulator() {
        c.a().a(this);
    }

    public void setSimulatorGetPushFlycStatusCallBack(DJISimulatorGetPushFlycStatusCallBack dJISimulatorGetPushFlycStatusCallBack) {
        this.mSimulatorGetPushFlycStatusCallBack = dJISimulatorGetPushFlycStatusCallBack;
    }

    public void setSimulatorGetPushDroneTypeCallBack(DJISimulatorGetPushTypeCallBack dJISimulatorGetPushTypeCallBack) {
        this.mSimulatorGetPushTypeCallBack = dJISimulatorGetPushTypeCallBack;
    }

    public void onEventBackgroundThread(DataSimulatorGetPushFlightStatusParams dataSimulatorGetPushFlightStatusParams) {
        if (this.mSimulatorGetPushFlycStatusCallBack != null && this.mSimulatorFlycStatus != null) {
            this.mSimulatorFlycStatus.recvData = dataSimulatorGetPushFlightStatusParams.getRecData();
            this.mSimulatorFlycStatus.length = dataSimulatorGetPushFlightStatusParams.getLength();
            this.mSimulatorGetPushFlycStatusCallBack.onResult(this.mSimulatorFlycStatus);
        }
    }

    public void onEventBackgroundThread(DataSimulatorGetPushMainControllerReturnParams dataSimulatorGetPushMainControllerReturnParams) {
        if (this.mSimulatorGetPushTypeCallBack != null) {
            this.mSimulatorGetPushTypeCallBack.onResult(DJISimulatorDroneType.find(dataSimulatorGetPushMainControllerReturnParams.getDroneType() & 15), DJISimulatorProductType.find((dataSimulatorGetPushMainControllerReturnParams.getDroneType() >> 4) & 15));
        }
    }

    public void onEventBackgroundThread(DataSimulatorGetPushConnectHeartPacket dataSimulatorGetPushConnectHeartPacket) {
        if (!this.isRequest) {
            this.mSimulatorRequestMainControllerParams.start(new d() {
                public void onSuccess(Object obj) {
                }

                public void onFailure(a aVar) {
                }
            });
            this.isRequest = true;
        }
    }

    public void resetSimulator() {
        this.isHeartConnectionBuild = false;
    }

    public boolean startHeartConnection() {
        if (this.mTimer != null) {
            return false;
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new PingHeart(), 0, 1000);
        return true;
    }

    public boolean stopHeartConnection() {
        if (this.mTimer == null) {
            return false;
        }
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer.purge();
            this.mTimer = null;
        }
        this.isHeartConnectionBuild = false;
        this.isRequest = false;
        return true;
    }

    public void startSimulateCommend(DJISimulatorCommend dJISimulatorCommend, final DJIExecuteIntResultCallback dJIExecuteIntResultCallback) {
        this.mSimulateFlightCommend.a().a(dJISimulatorCommend.isUseRealRC).b(dJISimulatorCommend.isModeFromThridPart).c(dJISimulatorCommend.isBatterySim).c(dJISimulatorCommend.altitude).a(dJISimulatorCommend.gpsCount).b(dJISimulatorCommend.pushHz).a(dJISimulatorCommend.latitude).b(dJISimulatorCommend.longitude).e(dJISimulatorCommend.mPitch).d(dJISimulatorCommend.mRoll).f(dJISimulatorCommend.mYaw).g(dJISimulatorCommend.mPositionX).h(dJISimulatorCommend.mPositionY).i(dJISimulatorCommend.mPositionZ).j(dJISimulatorCommend.mLatitude).k(dJISimulatorCommend.mLongitude).o(dJISimulatorCommend.mAccelerateX).p(dJISimulatorCommend.mAccelerateY).q(dJISimulatorCommend.mAccelerateZ).l(dJISimulatorCommend.mVeloctiyX).m(dJISimulatorCommend.mVelocityY).n(dJISimulatorCommend.mVelocityZ).r(dJISimulatorCommend.mGyroX).s(dJISimulatorCommend.mGyroY).t(dJISimulatorCommend.mGyroZ).u(dJISimulatorCommend.mRpm1).v(dJISimulatorCommend.mRpm2).w(dJISimulatorCommend.mRpm3).x(dJISimulatorCommend.mRpm4).y(dJISimulatorCommend.mRpm5).z(dJISimulatorCommend.mRpm6).A(dJISimulatorCommend.mRpm7).B(dJISimulatorCommend.mRpm8).C(dJISimulatorCommend.mSimTime).D(dJISimulatorCommend.mLEDColor).E(dJISimulatorCommend.mGearState).F(dJISimulatorCommend.mQuaternion).start(new d() {
            public void onSuccess(Object obj) {
                dJIExecuteIntResultCallback.onResult(DJISimulator.this.mSimulateFlightCommend.c());
            }

            public void onFailure(a aVar) {
                dJIExecuteIntResultCallback.onResult(aVar.a());
            }
        });
    }

    public void closeSimulateCommend(final DJIExecuteIntResultCallback dJIExecuteIntResultCallback) {
        this.mSimulateFlightCommend.b().start(new d() {
            public void onSuccess(Object obj) {
                dJIExecuteIntResultCallback.onResult(DJISimulator.this.mSimulateFlightCommend.c());
            }

            public void onFailure(a aVar) {
                dJIExecuteIntResultCallback.onResult(aVar.a());
            }
        });
    }

    public void setWind(float f, int i) {
        float cos = (float) (((double) (f * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) * Math.cos(Math.toRadians((double) i)));
        float sin = (float) (((double) (f * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) * Math.sin(Math.toRadians((double) i)));
        DJILogHelper.getInstance().LOGD("simulator", "set xpeed: " + cos + " yspeed: " + sin, false, true);
        this.mSetGetWind.setWindSpeedX((int) cos).setWindSpeedY((int) sin).setWindSpeedZ(0).setAckFlag(true).setInitFlag(false).start(new d() {
            public void onSuccess(Object obj) {
            }

            public void onFailure(a aVar) {
            }
        });
    }
}
