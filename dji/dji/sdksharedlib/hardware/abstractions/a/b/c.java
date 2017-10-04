package dji.sdksharedlib.hardware.abstractions.a.b;

import dji.common.airlink.AirLinkUtils;
import dji.common.airlink.WiFiFrequencyBand;
import dji.common.error.DJIAirLinkError;
import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataWifiGetPassword;
import dji.midware.data.model.P3.DataWifiGetSSID;
import dji.midware.data.model.P3.DataWifiGetWifiFrequency;
import dji.midware.data.model.P3.DataWifiRestart;
import dji.midware.data.model.P3.DataWifiSetPassword;
import dji.midware.data.model.P3.DataWifiSetSSID;
import dji.midware.data.model.P3.DataWifiSetWifiFrequency;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class c extends a {
    public void a(WiFiFrequencyBand wiFiFrequencyBand, final e eVar) {
        int i = 0;
        if (wiFiFrequencyBand != null && !wiFiFrequencyBand.equals(WiFiFrequencyBand.Unknown)) {
            DataWifiSetWifiFrequency dataWifiSetWifiFrequency = new DataWifiSetWifiFrequency();
            dataWifiSetWifiFrequency.a(false);
            if (!wiFiFrequencyBand.equals(WiFiFrequencyBand.FrequencyBand2Dot4G)) {
                i = 1;
            }
            dataWifiSetWifiFrequency.a(i).start(new d(this) {
                final /* synthetic */ c b;

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

    public void a(final e eVar) {
        if (eVar != null) {
            final DataWifiGetWifiFrequency dataWifiGetWifiFrequency = new DataWifiGetWifiFrequency();
            dataWifiGetWifiFrequency.setFromLongan(false);
            dataWifiGetWifiFrequency.start(new d(this) {
                final /* synthetic */ c c;

                public void onSuccess(Object obj) {
                    eVar.a(WiFiFrequencyBand.find(dataWifiGetWifiFrequency.getResult()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIError.getDJIError(aVar));
                }
            });
        }
    }

    public void a(String str, final e eVar) {
        if (!AirLinkUtils.verifySSID(str) || eVar == null) {
            DataWifiSetSSID dataWifiSetSSID = new DataWifiSetSSID();
            dataWifiSetSSID.a(true);
            dataWifiSetSSID.a(str.getBytes()).start(new d(this) {
                final /* synthetic */ c b;

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
            return;
        }
        eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
    }

    public void b(final e eVar) {
        if (eVar != null) {
            final DataWifiGetSSID fromLongan = new DataWifiGetSSID().setFromLongan(true);
            fromLongan.start(new d(this) {
                final /* synthetic */ c c;

                public void onSuccess(Object obj) {
                    eVar.a(fromLongan.getSSID());
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIError.getDJIError(aVar));
                }
            });
        }
    }

    public void b(String str, final e eVar) {
        if (str != null && str.length() >= 8) {
            DataWifiSetPassword dataWifiSetPassword = new DataWifiSetPassword();
            dataWifiSetPassword.a(true);
            dataWifiSetPassword.a(str.getBytes()).start(new d(this) {
                final /* synthetic */ c b;

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
            final DataWifiGetPassword fromLongan = new DataWifiGetPassword().setFromLongan(true);
            fromLongan.start(new d(this) {
                final /* synthetic */ c c;

                public void onSuccess(Object obj) {
                    eVar.a(fromLongan.getPassword());
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIError.getDJIError(aVar));
                }
            });
        }
    }

    public boolean b() {
        return true;
    }

    public void d(final e eVar) {
        DataWifiRestart dataWifiRestart = new DataWifiRestart();
        dataWifiRestart.a(true);
        dataWifiRestart.start(new d(this) {
            final /* synthetic */ c b;

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
