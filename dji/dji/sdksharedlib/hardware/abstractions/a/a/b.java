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
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368GetPushStatus;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataDm368SetParams;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.data.model.P3.DataDm385GetParams;
import dji.midware.data.model.P3.DataDm385SetParams;
import dji.midware.data.model.P3.DataDm385SetParams.DM385CmdId;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.e.d;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class b extends c {
    private static final String b = "DJISDKCacheLightBridge2AirLinkAbstraction";
    private static final int d = 0;
    private static final int e = 1;
    VideoDataChannel a;
    private final int f = -1;
    private f g;

    static /* synthetic */ class AnonymousClass22 {
        static final /* synthetic */ int[] a = new int[VideoDataChannel.values().length];

        static {
            try {
                a[VideoDataChannel.HDGimbal.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[VideoDataChannel.FPVCamera.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[VideoDataChannel.AV.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[VideoDataChannel.HDMI.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public void a(String str, int i, String str2, int i2, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        this.g = new f(this);
        this.g.a();
    }

    public void e() {
        super.e();
    }

    public void a(LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode, final e eVar) {
        if (lBAirLinkChannelSelectionMode == null || lBAirLinkChannelSelectionMode.equals(LBAirLinkChannelSelectionMode.Unknown)) {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_INVALID_PARAMETER);
        }
        DataOsdSetConfig.getInstance().a(lBAirLinkChannelSelectionMode.equals(LBAirLinkChannelSelectionMode.Auto)).start(new d(this) {
            final /* synthetic */ b b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(null);
                }
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    public void a(final e eVar) {
        if (eVar != null) {
            DataOsdGetPushConfig.getInstance().start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(DataOsdGetPushConfig.getInstance().getIsAuto() ? LBAirLinkChannelSelectionMode.Auto : LBAirLinkChannelSelectionMode.Manual);
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(int i, final e eVar) {
        if (i < 0 || i > 7) {
            eVar.a(DJIAirLinkError.COMMON_PARAM_ILLEGAL);
            return;
        }
        DataOsdSetConfig.getInstance().d(i + 13).start(new d(this) {
            final /* synthetic */ b b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(null);
                }
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    public void b(final e eVar) {
        if (eVar != null) {
            DataOsdGetPushConfig.getInstance().start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(Integer.valueOf(DataOsdGetPushConfig.getInstance().getChannel() - 13));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(LBAirLinkDataRate lBAirLinkDataRate, final e eVar) {
        if (lBAirLinkDataRate != null && !lBAirLinkDataRate.equals(LBAirLinkDataRate.Unknown)) {
            DataOsdSetConfig.getInstance().e(lBAirLinkDataRate.value()).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(null);
                    }
                }

                public void onFailure(a aVar) {
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
            final /* synthetic */ b b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(LBAirLinkDataRate.find(DataOsdGetPushConfig.getInstance().getMcs()));
                }
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    public void a(LBAirLinkFPVVideoQualityLatency lBAirLinkFPVVideoQualityLatency, final e eVar) {
        if (lBAirLinkFPVVideoQualityLatency != null && !lBAirLinkFPVVideoQualityLatency.equals(LBAirLinkFPVVideoQualityLatency.Unknown)) {
            DataDm385SetParams dataDm385SetParams = new DataDm385SetParams();
            dataDm385SetParams.a(DM385CmdId.a, lBAirLinkFPVVideoQualityLatency.value());
            dataDm385SetParams.start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(null);
                    }
                }

                public void onFailure(a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIAirLinkError.getDJIError(aVar));
                    }
                }
            });
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void d(final e eVar) {
        DataDm385GetParams.getInstance().start(new d(this) {
            final /* synthetic */ b b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(LBAirLinkFPVVideoQualityLatency.find(DataDm385GetParams.getInstance().getTransmissionMode()));
                }
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    public void a(boolean z, final e eVar) {
        DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
        dataDm368SetGParams.a(CmdId.n, z ? 1 : 0);
        dataDm368SetGParams.start(new d(this) {
            final /* synthetic */ b b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(null);
                }
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    public void e(final e eVar) {
        final DataDm368GetGParams instance = DataDm368GetGParams.getInstance();
        instance.setType(true).start(new d(this) {
            final /* synthetic */ b c;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(Boolean.valueOf(instance.getOutputEnable()));
                }
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    public void a(LBAirLinkSecondaryVideoOutputPort lBAirLinkSecondaryVideoOutputPort, e eVar) {
        if (lBAirLinkSecondaryVideoOutputPort != null && !lBAirLinkSecondaryVideoOutputPort.equals(LBAirLinkSecondaryVideoOutputPort.Unknown)) {
            a(CmdId.i, lBAirLinkSecondaryVideoOutputPort.value(), eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void f(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(LBAirLinkSecondaryVideoOutputPort.find(DataDm368GetGParams.getInstance().getOutputDevice()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(LBAirLinkPIPDisplay lBAirLinkPIPDisplay, e eVar) {
        if (lBAirLinkPIPDisplay != null && !lBAirLinkPIPDisplay.equals(LBAirLinkPIPDisplay.Unknown)) {
            a(CmdId.l, lBAirLinkPIPDisplay.value(), eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void g(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(LBAirLinkPIPDisplay.find(DataDm368GetGParams.getInstance().getOutputMode()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void b(final int i, final e eVar) {
        if (i % 10 == 0 && i >= 0 && i <= 100) {
            Log.i(b, "set bandwidth: " + (i / 10));
            switch (i) {
                case 0:
                    Log.i(b, "change video resource to EXT");
                    DataOsdSetConfig.getInstance().g(0).start(new d(this) {
                        final /* synthetic */ b c;

                        public void onSuccess(Object obj) {
                            this.c.g(i, eVar);
                        }

                        public void onFailure(a aVar) {
                            eVar.a(DJIAirLinkError.getDJIError(aVar));
                        }
                    });
                    return;
                case 100:
                    Log.i(b, "change video resource to LB");
                    DataOsdSetConfig.getInstance().g(1).start(new d(this) {
                        final /* synthetic */ b c;

                        public void onSuccess(Object obj) {
                            this.c.g(i, eVar);
                        }

                        public void onFailure(a aVar) {
                            eVar.a(DJIAirLinkError.getDJIError(aVar));
                        }
                    });
                    return;
                default:
                    DataOsdSetConfig.getInstance().g(2).start(new d(this) {
                        final /* synthetic */ b c;

                        public void onSuccess(Object obj) {
                            this.c.g(i, eVar);
                        }

                        public void onFailure(a aVar) {
                            eVar.a(DJIAirLinkError.getDJIError(aVar));
                        }
                    });
                    return;
            }
        } else if (eVar != null) {
            eVar.a(DJIAirLinkError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void h(final e eVar) {
        DataOsdGetPushConfig.getInstance().start(new d(this) {
            final /* synthetic */ b b;

            public void onSuccess(Object obj) {
                Log.i(b.b, "fpv get bandwidth: " + DataOsdGetPushConfig.getInstance().getBandWidthPercent());
                eVar.a(Integer.valueOf(DataOsdGetPushConfig.getInstance().getBandWidthPercent() * 10));
            }

            public void onFailure(a aVar) {
                eVar.a(DJIAirLinkError.getDJIError(aVar));
            }
        });
    }

    public void b(boolean z, e eVar) {
        a(CmdId.a, z ? 1 : 0, eVar);
    }

    public void i(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(Boolean.valueOf(DataDm368GetGParams.getInstance().getIsShowOsd()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void c(int i, e eVar) {
        if (i >= 0 && i <= 50) {
            a(CmdId.e, i, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void j(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(Integer.valueOf(DataDm368GetGParams.getInstance().getOsdMarginTop()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void d(int i, e eVar) {
        if (i >= 0 && i <= 50) {
            a(CmdId.c, i, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void k(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(Integer.valueOf(DataDm368GetGParams.getInstance().getOsdMarginLeft()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void e(int i, e eVar) {
        if (i >= 0 && i <= 50) {
            a(CmdId.f, i, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void l(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(Integer.valueOf(DataDm368GetGParams.getInstance().getOsdMarginBottom()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void f(int i, e eVar) {
        if (i >= 0 && i <= 50) {
            a(CmdId.d, i, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void m(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(Integer.valueOf(DataDm368GetGParams.getInstance().getOsdMarginRight()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(LBAirLinkUnit lBAirLinkUnit, e eVar) {
        if (lBAirLinkUnit != null && !lBAirLinkUnit.equals(LBAirLinkUnit.Unknown)) {
            a(CmdId.g, lBAirLinkUnit.value(), eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void n(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(DataDm368GetGParams.getInstance().getUnit() ? LBAirLinkUnit.Metric : LBAirLinkUnit.Imperial);
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(LBAirLinkSecondaryVideoFormat lBAirLinkSecondaryVideoFormat, e eVar) {
        if (lBAirLinkSecondaryVideoFormat != null && !lBAirLinkSecondaryVideoFormat.equals(LBAirLinkSecondaryVideoFormat.Unknown)) {
            a(CmdId.j, lBAirLinkSecondaryVideoFormat.value(), eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void o(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(LBAirLinkSecondaryVideoFormat.find(DataDm368GetGParams.getInstance().getHDMIFormat()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void b(LBAirLinkSecondaryVideoFormat lBAirLinkSecondaryVideoFormat, e eVar) {
        if (lBAirLinkSecondaryVideoFormat != null && !lBAirLinkSecondaryVideoFormat.equals(LBAirLinkSecondaryVideoFormat.Unknown)) {
            a(CmdId.k, lBAirLinkSecondaryVideoFormat.value(), eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void p(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().setType(true).start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(LBAirLinkSecondaryVideoFormat.find(DataDm368GetGParams.getInstance().getSDIFormat()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(LBAirLinkPIPPosition lBAirLinkPIPPosition, e eVar) {
        if (lBAirLinkPIPPosition != null && !lBAirLinkPIPPosition.equals(LBAirLinkPIPPosition.Unknown)) {
            a(CmdId.m, lBAirLinkPIPPosition.value(), eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void q(final e eVar) {
        if (eVar != null) {
            DataDm368GetGParams.getInstance().start(new d(this) {
                final /* synthetic */ b b;

                public void onSuccess(Object obj) {
                    eVar.a(LBAirLinkPIPPosition.find(DataDm368GetGParams.getInstance().getOutputLocation()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(VideoDataChannel videoDataChannel, e eVar) {
        if (videoDataChannel == VideoDataChannel.Unknown) {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_INVALID_PARAMETER);
        } else if (c() || !(videoDataChannel == VideoDataChannel.AV || videoDataChannel == VideoDataChannel.HDMI)) {
            switch (AnonymousClass22.a[videoDataChannel.ordinal()]) {
                case 1:
                    v(eVar);
                    return;
                case 2:
                    u(eVar);
                    return;
                case 3:
                    w(eVar);
                    return;
                case 4:
                    x(eVar);
                    return;
                default:
                    return;
            }
        } else {
            eVar.a(DJIAirLinkError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
        }
    }

    public void r(e eVar) {
        eVar.a(this.a);
    }

    public void a(final LBAirLinkEncodeMode lBAirLinkEncodeMode, final e eVar) {
        if (!c()) {
            eVar.a(DJIAirLinkError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
        } else if (lBAirLinkEncodeMode == LBAirLinkEncodeMode.Unknown) {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_INVALID_PARAMETER);
        } else {
            DataDm368SetParams dataDm368SetParams = new DataDm368SetParams();
            dataDm368SetParams.a(DM368CmdId.d, lBAirLinkEncodeMode.value());
            dataDm368SetParams.start(new d(this) {
                final /* synthetic */ b c;

                public void onSuccess(Object obj) {
                    eVar.a(null);
                    this.c.g.a(lBAirLinkEncodeMode);
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void s(e eVar) {
        if (c()) {
            eVar.a(LBAirLinkEncodeMode.find(DataDm368GetPushStatus.getInstance().getEncodeMode()));
        } else {
            eVar.a(DJIAirLinkError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
        }
    }

    public void a(final float f, final e eVar) {
        if (!c()) {
            eVar.a(DJIAirLinkError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
        } else if (((double) f) < 0.0d || ((double) f) > 1.0d) {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_INVALID_PARAMETER);
        } else {
            float f2 = 10.0f * f;
            DataDm368SetParams dataDm368SetParams = new DataDm368SetParams();
            dataDm368SetParams.a(DM368CmdId.e, (int) f2);
            dataDm368SetParams.start(new d(this) {
                final /* synthetic */ b c;

                public void onSuccess(Object obj) {
                    eVar.a(null);
                    this.c.g.b((int) (f * 10.0f));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            });
        }
    }

    public void t(e eVar) {
        if (c()) {
            eVar.a(Float.valueOf(((float) DataDm368GetPushStatus.getInstance().getDualEncodeModePercentage()) / 10.0f));
        } else {
            eVar.a(DJIAirLinkError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
        }
    }

    public boolean c() {
        return DataDm368GetPushStatus.getInstance().isDualEncodeModeSupported();
    }

    public boolean b() {
        return true;
    }

    public boolean d() {
        return false;
    }

    public void a(VideoDataChannel videoDataChannel) {
        Log.i("LB2AbstractionHelper", "in set video channel");
        switch (AnonymousClass22.a[videoDataChannel.ordinal()]) {
            case 1:
                Log.i("LB2AbstractionHelper", "in setting to HDGimbal");
                dji.midware.usb.P3.a.getInstance().a(dji.midware.usb.P3.a.c.b);
                this.a = VideoDataChannel.HDGimbal;
                return;
            case 2:
                Log.i("LB2AbstractionHelper", "in setting to FPVCamera");
                dji.midware.usb.P3.a.getInstance().a(dji.midware.usb.P3.a.c.a);
                this.a = VideoDataChannel.FPVCamera;
                return;
            case 3:
                Log.i("LB2AbstractionHelper", "in setting to AV");
                dji.midware.usb.P3.a.getInstance().a(dji.midware.usb.P3.a.a.b);
                this.a = VideoDataChannel.AV;
                return;
            case 4:
                Log.i("LB2AbstractionHelper", "in setting to HDMI");
                dji.midware.usb.P3.a.getInstance().a(dji.midware.usb.P3.a.a.a);
                this.a = VideoDataChannel.HDMI;
                return;
            default:
                return;
        }
    }

    private void g(final int i, final e eVar) {
        DataOsdSetConfig.getInstance().f(i / 10).start(new d(this) {
            final /* synthetic */ b c;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(null);
                }
                Log.i(b.b, "percent / 10 :" + (i / 10));
                this.c.g.a(i / 10);
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIAirLinkError.getDJIError(aVar));
                }
            }
        });
    }

    private void v(e eVar) {
        Log.i(b, "in setVideoDataChannelToHDGimbal");
        Log.i(b, "current encode mode: " + this.g.a);
        Log.i(b, "current lb percent cache: " + this.g.b);
        if (this.g.a == LBAirLinkEncodeMode.Unknown || this.g.b == -1) {
            DJIError c = this.g.c();
            if (c != null) {
                eVar.a(c);
                return;
            }
            Log.i(b, "after refresh in setVideoDataChannelToHDGimbal");
            if (s()) {
                a(VideoDataChannel.HDGimbal);
                eVar.a(null);
                return;
            }
            Log.i(b, "not valid prerequisite");
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        } else if (s()) {
            a(VideoDataChannel.HDGimbal);
            eVar.a(null);
        } else {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        }
    }

    public void u(e eVar) {
        if (this.g.a == LBAirLinkEncodeMode.Unknown || this.g.b == -1) {
            DJIError c = this.g.c();
            if (c != null) {
                eVar.a(c);
            } else if (r()) {
                a(VideoDataChannel.FPVCamera);
                eVar.a(null);
            } else {
                eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
            }
        } else if (r()) {
            a(VideoDataChannel.FPVCamera);
            eVar.a(null);
        } else {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        }
    }

    private void w(e eVar) {
        if (this.g.a == LBAirLinkEncodeMode.Unknown || this.g.c == -1) {
            DJIError c = this.g.c();
            if (c != null) {
                eVar.a(c);
            } else if (q()) {
                a(VideoDataChannel.AV);
                eVar.a(null);
            } else {
                eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
            }
        } else if (q()) {
            a(VideoDataChannel.AV);
            eVar.a(null);
        } else {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        }
    }

    private void x(e eVar) {
        if (this.g.a == LBAirLinkEncodeMode.Unknown || this.g.c == -1) {
            DJIError c = this.g.c();
            if (c != null) {
                eVar.a(c);
            } else if (p()) {
                a(VideoDataChannel.HDMI);
                eVar.a(null);
            } else {
                eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
            }
        } else if (p()) {
            a(VideoDataChannel.HDMI);
            eVar.a(null);
        } else {
            eVar.a(DJIAirLinkError.IMAGE_TRANSMITTER_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        }
    }

    private boolean p() {
        Log.i(b, "in checkPrerequisiteForHDMIChannel()");
        Log.i(b, "encodeModeCache: " + this.g.a);
        Log.i(b, "dualPercentCache: " + this.g.c);
        if (this.g.a != LBAirLinkEncodeMode.Dual || this.g.c <= 0) {
            return false;
        }
        return true;
    }

    private boolean q() {
        Log.i(b, "in checkPrerequisiteForAVChannel()");
        Log.i(b, "encodeModeCache: " + this.g.a);
        Log.i(b, "dualPercentCache: " + this.g.c);
        if (this.g.a != LBAirLinkEncodeMode.Dual || this.g.c >= 10) {
            return false;
        }
        return true;
    }

    private boolean r() {
        Log.i(b, "in checkPrerequisiteForFPVCameraChannel()");
        Log.i(b, "encodeModeCache: " + this.g.a);
        Log.i(b, "lbPercentCache: " + this.g.b);
        if (this.g.a != LBAirLinkEncodeMode.Single || this.g.b <= 0) {
            return false;
        }
        return true;
    }

    private boolean s() {
        Log.i(b, "in checkPrerequisiteForHDGimbalChannel()");
        Log.i(b, "encodeModeCache: " + this.g.a);
        Log.i(b, "lbPercentCache: " + this.g.b);
        if (this.g.a != LBAirLinkEncodeMode.Single || this.g.b >= 10) {
            return false;
        }
        return true;
    }
}
