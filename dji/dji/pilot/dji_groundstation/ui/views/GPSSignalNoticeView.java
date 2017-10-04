package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.gs.e.b;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.e;
import dji.thirdparty.a.c;

public class GPSSignalNoticeView extends TextView {
    private static final String a = "GPSSignalNoticeView";
    private Handler b = new Handler(Looper.getMainLooper());
    private Runnable c = new Runnable(this) {
        final /* synthetic */ GPSSignalNoticeView a;

        {
            this.a = r1;
        }

        public void run() {
            e eVar = new e();
            eVar.s = 6;
            eVar.t = new d(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    if (obj == null) {
                        this.a.a.setText(this.a.a.getContext().getString(R.string.gsnew_gs_follow_me_phone_gps_weak));
                        this.a.a.setTextColor(SupportMenu.CATEGORY_MASK);
                    } else if (!(obj instanceof b)) {
                    } else {
                        if (((b) obj) == null) {
                            this.a.a.setText(this.a.a.getContext().getString(R.string.gsnew_gs_follow_me_phone_gps_weak));
                            this.a.a.setTextColor(SupportMenu.CATEGORY_MASK);
                            return;
                        }
                        this.a.a.setText(this.a.a.getContext().getString(R.string.gsnew_gs_follow_me_phone_gps_strong));
                        this.a.a.setTextColor(-16711936);
                    }
                }

                public void onFailure(a aVar) {
                }
            };
            c.a().e(eVar);
            this.a.b.postDelayed(this.a.c, 500);
        }
    };

    public GPSSignalNoticeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.post(this.c);
    }
}
