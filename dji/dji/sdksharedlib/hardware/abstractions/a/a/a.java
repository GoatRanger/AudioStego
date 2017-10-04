package dji.sdksharedlib.hardware.abstractions.a.a;

import android.util.Log;
import dji.common.LBAirLinkEncodeMode;
import dji.common.LBAirLinkPIPPosition;
import dji.common.LBAirLinkSecondaryVideoFormat;
import dji.common.VideoDataChannel;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBAirLinkDataRate;
import dji.common.airlink.LBAirLinkFPVVideoQualityLatency;
import dji.common.airlink.LBAirLinkPIPDisplay;
import dji.common.airlink.LBAirLinkSecondaryVideoOutputPort;
import dji.common.airlink.LBAirLinkUnit;
import dji.common.error.DJIAirLinkError;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.e.d;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class a extends c {
    private static final String a = "DJISDKCacheLightBridge1AirLinkAbstraction";

    public void a(String str, int i, String str2, int i2, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, str2, i2, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
    }

    public void e() {
    }

    public void a(LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode, final e eVar) {
        Log.d("ProgressTAG", "start setChannel");
        if (lBAirLinkChannelSelectionMode != null && !lBAirLinkChannelSelectionMode.equals(LBAirLinkChannelSelectionMode.Unknown)) {
            Log.d("ProgressTAG", "vaild");
            DataOsdSetConfig.getInstance().a(lBAirLinkChannelSelectionMode.equals(LBAirLinkChannelSelectionMode.Auto)).start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    Log.d("ProgressTAG", "Success");
                    if (eVar != null) {
                        Log.d("ProgressTAG", "Callback is not null");
                        eVar.a(null);
                        Log.d("ProgressTAG", "Callback finished");
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    Log.d("ProgresTAG", "Error ccode: " + aVar.name());
                    if (eVar != null) {
                        Log.d("AirLinkUnitTest", aVar + "");
                        eVar.a(DJIAirLinkError.getDJIError(aVar));
                    }
                }
            });
        } else if (eVar != null) {
            eVar.a(DJIAirLinkError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void a(final e eVar) {
        Log.d("ProgressTAG", "start getChannel");
        if (eVar != null) {
            Log.d("ProgressTAG", "Not null");
            DataOsdGetPushConfig.getInstance().start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    Log.d("ProgressTAG", "I Get");
                    eVar.a(DataOsdGetPushConfig.getInstance().getIsAuto() ? LBAirLinkChannelSelectionMode.Auto : LBAirLinkChannelSelectionMode.Manual);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    Log.d("ProgressTAG", "Error ccode ：" + aVar.name());
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(int i, final e eVar) {
        if (i >= 0 && i <= 7) {
            DataOsdSetConfig.getInstance().d(i + 13).start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(null);
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIAirLinkError.getDJIError(aVar));
                    }
                }
            });
        } else if (eVar != null) {
            eVar.a(DJIAirLinkError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void b(final e eVar) {
        if (eVar != null) {
            DataOsdGetPushConfig.getInstance().start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    CallbackUtils.onSuccess(eVar, Integer.valueOf(DataOsdGetPushConfig.getInstance().getChannel() - 13));
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(LBAirLinkDataRate lBAirLinkDataRate, final e eVar) {
        if (lBAirLinkDataRate != null && !lBAirLinkDataRate.equals(LBAirLinkDataRate.Unknown)) {
            DataOsdSetConfig.getInstance().e(lBAirLinkDataRate.value()).start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(null);
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIAirLinkError.getDJIError(aVar));
                    }
                }
            });
        } else if (eVar != null) {
            eVar.a(DJIAirLinkError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void c(final e eVar) {
        DataOsdGetPushConfig.getInstance().start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(LBAirLinkDataRate.find(DataOsdGetPushConfig.getInstance().getMcs()));
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    public void a(LBAirLinkFPVVideoQualityLatency lBAirLinkFPVVideoQualityLatency, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void d(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(boolean z, final e eVar) {
        if (DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.Inspire && DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.OpenFrame) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        } else if (b() || !DataDm368_gGetPushCheckStatus.getInstance().isGetted() || DataDm368_gGetPushCheckStatus.getInstance().getHDMIExist()) {
            DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
            dataDm368SetGParams.a(CmdId.h, z ? 1 : 0);
            dataDm368SetGParams.start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(null);
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIAirLinkError.getDJIError(aVar));
                    }
                }
            });
        } else {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void e(final e eVar) {
        if (DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.Inspire && DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.OpenFrame) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        } else if (b() || !DataDm368_gGetPushCheckStatus.getInstance().isGetted() || DataDm368_gGetPushCheckStatus.getInstance().getHDMIExist()) {
            final DataDm368GetGParams instance = DataDm368GetGParams.getInstance();
            instance.setType(false).start(new d(this) {
                final /* synthetic */ a c;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(Boolean.valueOf(instance.getIsDouble()));
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIAirLinkError.getDJIError(aVar));
                    }
                }
            });
        } else {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(LBAirLinkSecondaryVideoOutputPort lBAirLinkSecondaryVideoOutputPort, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void f(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(LBAirLinkPIPDisplay lBAirLinkPIPDisplay, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void g(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void b(int i, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void h(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void b(boolean z, e eVar) {
        if (DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.Inspire && DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.OpenFrame) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        } else if (b() || !DataDm368_gGetPushCheckStatus.getInstance().isGetted() || DataDm368_gGetPushCheckStatus.getInstance().getHDMIExist()) {
            a(CmdId.a, z ? 1 : 0, eVar);
        } else {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void i(final e eVar) {
        if (DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.Inspire && DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.OpenFrame) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        } else if (b() || !DataDm368_gGetPushCheckStatus.getInstance().isGetted() || DataDm368_gGetPushCheckStatus.getInstance().getHDMIExist()) {
            final DataDm368GetGParams instance = DataDm368GetGParams.getInstance();
            instance.setType(false).start(new d(this) {
                final /* synthetic */ a c;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(Boolean.valueOf(instance.getIsShowOsd()));
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIAirLinkError.getDJIError(aVar));
                    }
                }
            });
        } else {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void c(int i, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void j(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void d(int i, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void k(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void e(int i, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void l(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void f(int i, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void m(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(LBAirLinkUnit lBAirLinkUnit, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void n(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(LBAirLinkSecondaryVideoFormat lBAirLinkSecondaryVideoFormat, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void o(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void b(LBAirLinkSecondaryVideoFormat lBAirLinkSecondaryVideoFormat, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void p(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(LBAirLinkPIPPosition lBAirLinkPIPPosition, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void q(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(VideoDataChannel videoDataChannel, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void r(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(LBAirLinkEncodeMode lBAirLinkEncodeMode, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void s(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(float f, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void t(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public boolean b() {
        return false;
    }

    public boolean h_() {
        return false;
    }
}
