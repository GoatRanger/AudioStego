package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.gs.e.b;
import dji.gs.utils.a;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.thirdparty.a.c;

public class DistanceToHomeView extends TextView {
    private static final String a = "DistanceToHomeView";
    private Handler b = new Handler(Looper.getMainLooper());
    private Runnable c = new Runnable(this) {
        final /* synthetic */ DistanceToHomeView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a();
            this.a.b.postDelayed(this.a.c, 500);
        }
    };

    public DistanceToHomeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.post(this.c);
    }

    private void a() {
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        if (latitude == 0.0d || longitude == 0.0d) {
            setText("N/A");
            setTextColor(SupportMenu.CATEGORY_MASK);
            return;
        }
        e eVar = new e();
        eVar.s = 7;
        eVar.t = new d(this) {
            final /* synthetic */ DistanceToHomeView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (obj != null && (obj instanceof b)) {
                    b bVar = (b) obj;
                    b a = a.a(new b(f.b(), f.c()));
                    float atan2 = (float) ((Math.atan2((a.c - bVar.c) * 10000.0d, (-(a.b - bVar.b)) * 20000.0d) * 180.0d) / 3.141592653589793d);
                    if (atan2 < 0.0f) {
                        atan2 = (float) (((double) atan2) + 6.283185307179586d);
                    }
                    double a2 = a.a(a.b, a.c, bVar.b, bVar.c);
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        this.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(f.a((float) a2))}));
                    } else {
                        this.a.setText(String.format("%.1fM", new Object[]{Double.valueOf(a2)}));
                    }
                    if ((a2 > 5.0d || i.getInstance().c() == ProductType.A2) && (a2 > 10.0d || i.getInstance().c() != ProductType.A2)) {
                        this.a.setTextColor(this.a.getContext().getResources().getColor(R.color.setting_ui_btn_hover));
                    } else {
                        this.a.setTextColor(SupportMenu.CATEGORY_MASK);
                    }
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        };
        c.a().e(eVar);
    }
}
