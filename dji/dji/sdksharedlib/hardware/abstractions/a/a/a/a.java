package dji.sdksharedlib.hardware.abstractions.a.a.a;

import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBSDRBandwidth;
import dji.midware.data.model.P3.DataOsdGetSdrConfig;
import dji.midware.e.d;
import dji.sdksharedlib.b.a.c;
import dji.sdksharedlib.hardware.a.e;
import dji.sdksharedlib.hardware.a.h;
import java.util.List;

public class a extends h {

    private class a implements d {
        final /* synthetic */ a a;
        private b[] b;

        public a(a aVar, b[] bVarArr) {
            this.a = aVar;
            this.b = bVarArr;
        }

        public void onSuccess(Object obj) {
            new dji.sdksharedlib.b.c.a().b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(0)).c(c.h).b(Integer.valueOf(0));
            DataOsdGetSdrConfig.getInstance().getSelectionMode();
            for (b bVar : this.b) {
                if (bVar.a.compareTo(c.H) == 0) {
                    bVar.b.a(LBSDRBandwidth.find(DataOsdGetSdrConfig.getInstance().getBandwidthType()));
                } else if (bVar.a.compareTo(c.K) == 0) {
                    bVar.b.a(new Integer(DataOsdGetSdrConfig.getInstance().getSdrNf()));
                } else if (bVar.a.compareTo("ChannelSelectionMode") == 0) {
                    bVar.b.a(LBAirLinkChannelSelectionMode.find(DataOsdGetSdrConfig.getInstance().getSelectionMode() == 0 ? 1 : 0));
                }
            }
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
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
