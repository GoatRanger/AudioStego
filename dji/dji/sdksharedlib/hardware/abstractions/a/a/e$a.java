package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.airlink.FrequencyInterference;
import dji.midware.data.model.P3.DataOsdGetPushSdrSweepFrequency;
import dji.sdksharedlib.b.a.d;

class e$a {
    final /* synthetic */ e a;

    private e$a(e eVar) {
        this.a = eVar;
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrSweepFrequency dataOsdGetPushSdrSweepFrequency) {
        Object obj = new FrequencyInterference[dataOsdGetPushSdrSweepFrequency.getRecData().length];
        for (int i = 0; i < dataOsdGetPushSdrSweepFrequency.getRecData().length; i = (byte) (i + 1)) {
            obj[i] = new FrequencyInterference((((float) i) * 2.0f) + 2400.0f, (((float) (i + 1)) * 2.0f) + 2400.0f, (byte) dataOsdGetPushSdrSweepFrequency.getSignalList()[i]);
        }
        e.a(this.a, obj, this.a.a.d(d.m).a());
    }
}
