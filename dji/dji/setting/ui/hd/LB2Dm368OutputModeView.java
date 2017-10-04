package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.usb.P3.a;
import dji.midware.usb.P3.a.b;
import dji.midware.usb.P3.a.c;
import dji.pilot.c.d;
import dji.pilot.setting.ui.R;

public class LB2Dm368OutputModeView extends LB2AppOutputView {
    public LB2Dm368OutputModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected boolean b() {
        return a.b();
    }

    protected void a() {
        super.a();
        if (!a.b() || this.c.d() != b.b) {
            return;
        }
        if (DataDm368GetGParams.getInstance().getOutputMode() == a) {
            this.c.a(c.a);
        } else {
            this.c.a(c.b);
        }
    }

    public void onItemClick(int i) {
        int e = a.getInstance().e();
        if (a.getInstance().d() == b.c) {
            e = a.getInstance().f();
        }
        if (d.b == MODE.b) {
            if (e != -1 && i != -1) {
                if (e == 10 && i != 0) {
                    DJILogHelper.getInstance().LOGD("HdView", "ONLY LB ENABLE", false, true);
                    dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_output_mode_failed);
                    a();
                    return;
                } else if (e == 0 && i != 1) {
                    DJILogHelper.getInstance().LOGD("HdView", "ONLY EXT ENABLE", false, true);
                    dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_output_mode_failed);
                    a();
                    return;
                }
            }
            return;
        }
        super.onItemClick(i);
    }

    protected void a(final int i) {
        new DataDm368SetGParams().a(CmdId.l, i).start(new dji.midware.e.d(this) {
            final /* synthetic */ LB2Dm368OutputModeView b;

            public void onSuccess(Object obj) {
                if (i == LB2AppOutputView.a) {
                    this.b.c.a(c.a);
                } else {
                    this.b.c.a(c.b);
                }
                DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "mode Success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.c();
            }
        });
    }

    private void c() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ LB2Dm368OutputModeView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        });
    }

    public void onEventMainThread(c cVar) {
        super.onEventMainThread(cVar);
        postDelayed(new Runnable(this) {
            final /* synthetic */ LB2Dm368OutputModeView a;

            {
                this.a = r1;
            }

            public void run() {
                a.f();
            }
        }, 500);
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        a();
    }
}
