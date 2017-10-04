package dji.sdksharedlib.hardware.abstractions.a.a.a;

import dji.common.airlink.ChannelSelectionMode;
import dji.common.airlink.OcuSyncBandwidth;
import dji.common.error.DJIError;
import dji.midware.data.model.P3.DataOsdGetSdrConfig;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.a.e;
import dji.sdksharedlib.hardware.a.h;
import java.util.List;

public class b extends h {

    private class a implements d {
        final /* synthetic */ b a;
        private b[] b;

        public a(b bVar, b[] bVarArr) {
            this.a = bVar;
            this.b = bVarArr;
        }

        public void onSuccess(Object obj) {
            for (b bVar : this.b) {
                if (bVar.a.compareTo(dji.sdksharedlib.b.a.d.i) == 0) {
                    bVar.b.a(OcuSyncBandwidth.find(DataOsdGetSdrConfig.getInstance().getBandwidthType()));
                } else if (bVar.a.compareTo(dji.sdksharedlib.b.a.d.k) == 0) {
                    bVar.b.a(Integer.valueOf(DataOsdGetSdrConfig.getInstance().getSdrNf()));
                } else if (bVar.a.compareTo("ChannelSelectionMode") == 0) {
                    int i;
                    e eVar = bVar.b;
                    if (DataOsdGetSdrConfig.getInstance().getSelectionMode() == 0) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    eVar.a(ChannelSelectionMode.find(i));
                }
            }
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            for (b bVar : this.b) {
                bVar.b.a(DJIError.getDJIError(aVar));
            }
        }
    }

    public static class b {
        public String a;
        public e b;

        public b(String str, e eVar) {
            this.a = str;
            this.b = eVar;
        }
    }

    public void a(String str, e eVar) {
        a((Object) new b(str, eVar));
    }

    protected void a(List<Object> list) {
        DataOsdGetSdrConfig.getInstance().start(new a(this, (b[]) list.toArray(new b[list.size()])));
    }
}
