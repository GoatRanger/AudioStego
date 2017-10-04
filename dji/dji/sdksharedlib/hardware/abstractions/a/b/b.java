package dji.sdksharedlib.hardware.abstractions.a.b;

import dji.common.airlink.WiFiFrequencyBand;
import dji.common.error.DJIAirLinkError;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataWifiGetWifiFreqMode;
import dji.midware.data.model.P3.DataWifiSetWifiFreq5GMode;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class b extends d {
    public void a(WiFiFrequencyBand wiFiFrequencyBand, e eVar) {
        if (wiFiFrequencyBand != null && !wiFiFrequencyBand.equals(WiFiFrequencyBand.Unknown)) {
            new DataWifiSetWifiFreq5GMode().a(wiFiFrequencyBand.value()).start(CallbackUtils.getSetterDJIDataCallback(eVar));
        } else if (eVar != null) {
            eVar.a(DJIAirLinkError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void a(final e eVar) {
        if (eVar != null) {
            final DataWifiGetWifiFreqMode dataWifiGetWifiFreqMode = new DataWifiGetWifiFreqMode();
            dataWifiGetWifiFreqMode.start(new d(this) {
                final /* synthetic */ b c;

                public void onSuccess(Object obj) {
                    eVar.a(WiFiFrequencyBand.find(dataWifiGetWifiFreqMode.getFreqMode()));
                }

                public void onFailure(a aVar) {
                    eVar.a(DJIError.getDJIError(aVar));
                }
            });
        }
    }
}
