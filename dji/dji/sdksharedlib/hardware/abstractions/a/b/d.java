package dji.sdksharedlib.hardware.abstractions.a.b;

import dji.common.airlink.AirLinkUtils;
import dji.common.airlink.WiFiFrequencyBand;
import dji.common.error.DJIAirLinkError;
import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataWifiGetPassword;
import dji.midware.data.model.P3.DataWifiGetSSID;
import dji.midware.data.model.P3.DataWifiRestart;
import dji.midware.data.model.P3.DataWifiSetPassword;
import dji.midware.data.model.P3.DataWifiSetSSID;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class d extends a {
    public void a(WiFiFrequencyBand wiFiFrequencyBand, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(String str, final e eVar) {
        if (!AirLinkUtils.verifySSID(str) || eVar == null) {
            DataWifiSetSSID.getInstance().a(str.getBytes()).start(new dji.midware.e.d(this) {
                final /* synthetic */ d b;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(null);
                    }
                }

                public void onFailure(a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIError.getDJIError(aVar));
                    }
                }
            });
        } else {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void b(final e eVar) {
        if (eVar != null) {
            DataWifiGetSSID.getInstance().start(new dji.midware.e.d(this) {
                final /* synthetic */ d b;

                public void onSuccess(Object obj) {
                    eVar.a(DataWifiGetSSID.getInstance().getSSID());
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIError.getDJIError(aVar));
                }
            });
        }
    }

    public void b(String str, final e eVar) {
        if (str != null && str.length() >= 8) {
            DataWifiSetPassword.getInstance().a(str.getBytes()).start(new dji.midware.e.d(this) {
                final /* synthetic */ d b;

                public void onSuccess(Object obj) {
                    if (eVar != null) {
                        eVar.a(null);
                    }
                }

                public void onFailure(a aVar) {
                    if (eVar != null) {
                        eVar.a(DJIError.getDJIError(aVar));
                    }
                }
            });
        } else if (eVar != null) {
            eVar.a(DJIAirLinkError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void c(final e eVar) {
        if (eVar != null) {
            DataWifiGetPassword.getInstance().start(new dji.midware.e.d(this) {
                final /* synthetic */ d b;

                public void onSuccess(Object obj) {
                    eVar.a(DataWifiGetPassword.getInstance().getPassword());
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIError.getDJIError(aVar));
                }
            });
        }
    }

    public boolean b() {
        return false;
    }

    public void d(final e eVar) {
        DataWifiRestart.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ d b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(null);
                }
            }

            public void onFailure(a aVar) {
                if (eVar != null) {
                    eVar.a(DJIError.getDJIError(aVar));
                }
            }
        });
    }
}
