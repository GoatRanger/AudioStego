package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import com.google.a.b.a.k;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368GetPushStatus;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.usb.P3.a.b;
import dji.pilot.c.d;
import dji.pilot.setting.ui.R;
import dji.publics.a;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;

public class LB2OutputModeView extends ItemViewSpinner {
    private String[] a = this.b;
    private String[] b = new String[]{k.b, "EXT", "PIP_LB", "PIP_EXT"};
    private String[] c = new String[]{"HDMI", "AV", "PIP_HDMI", "PIP_AV"};

    public LB2OutputModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2OutputModeView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }, 700);
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    private void a() {
        if (a.a()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        if (dji.midware.usb.P3.a.getInstance().d() == b.b) {
            this.a = this.b;
        } else {
            this.a = this.c;
        }
        if (DataDm368GetGParams.getInstance().getOutputEnable() || !a.a()) {
            this.f.setData(this.a, DataDm368GetGParams.getInstance().getOutputMode(), (DJISpinnerButton.b) this);
            if (dji.midware.usb.P3.a.getInstance().d() == b.c) {
                int f = dji.midware.usb.P3.a.getInstance().f();
                if (f == 10) {
                    this.f.setData(this.a, 0, (DJISpinnerButton.b) this);
                    return;
                } else if (f == 0) {
                    this.f.setData(this.a, 1, (DJISpinnerButton.b) this);
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        setVisibility(8);
    }

    private void b() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ LB2OutputModeView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        });
    }

    public void onItemClick(int i) {
        int e = dji.midware.usb.P3.a.getInstance().e();
        if (dji.midware.usb.P3.a.getInstance().d() == b.c) {
            e = dji.midware.usb.P3.a.getInstance().f();
        }
        if (d.b == MODE.b) {
            if (e != -1 && i != -1) {
                if (e == 10 && i != 0) {
                    DJILogHelper.getInstance().LOGD("HdView", "ONLY LB ENABLE", false, true);
                    dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_output_mode_failed);
                    b();
                    return;
                } else if (e == 0 && i != 1) {
                    DJILogHelper.getInstance().LOGD("HdView", "ONLY EXT ENABLE", false, true);
                    dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_output_mode_failed);
                    b();
                    return;
                }
            }
            return;
        }
        if (e == 0 && i != 1) {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_bandwidth_only);
            b();
        } else if (e != 10 || i == 0) {
            new DataDm368SetGParams().a(CmdId.l, i).start(new dji.midware.e.d(this) {
                final /* synthetic */ LB2OutputModeView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.f();
                    DJILogHelper.getInstance().LOGD("", "mode Success", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.b();
                }
            });
        } else {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_bandwidth_only);
            b();
        }
    }

    public void onEventMainThread(b bVar) {
        a();
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        a();
    }

    public void onEventMainThread(DataDm368GetPushStatus dataDm368GetPushStatus) {
        a();
    }
}
