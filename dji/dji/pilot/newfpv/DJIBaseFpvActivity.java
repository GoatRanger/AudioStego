package dji.pilot.newfpv;

import android.os.Bundle;
import dji.pilot.newfpv.view.DJIMFView;
import dji.pilot.newfpv.view.b.a;
import java.util.HashMap;
import java.util.Map;

public abstract class DJIBaseFpvActivity extends DJIAbsFpvActivity<b> {
    protected DJIMFView a = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void a() {
    }

    public Map<a, d> b() {
        Map hashMap = new HashMap();
        hashMap.put(this.a.getViewId(), this.a.getViewInfo());
        return hashMap;
    }

    public boolean a(d dVar, int i) {
        return a.ViewId_MF == dVar.b ? true : true;
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected Class<b> u() {
        return b.class;
    }
}
