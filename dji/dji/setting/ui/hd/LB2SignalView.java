package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class LB2SignalView extends DividerLinearLayout implements OnClickListener {
    private static final int i = -20;
    private static final int l = -100;
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;

    public LB2SignalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_hd_lb2_signal_check);
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.fpv_hd_signal_aerial1);
            this.b = (TextView) findViewById(R.id.fpv_hd_signal_aerial2);
            this.c = (TextView) findViewById(R.id.fpv_hd_signal_aerial3);
            this.d = (TextView) findViewById(R.id.fpv_hd_signal_aerial4);
            this.a.setText(getResources().getString(R.string.setting_ui_hd_signal_check_aerial) + " 1");
            this.b.setText(getResources().getString(R.string.setting_ui_hd_signal_check_aerial) + " 2");
            this.c.setText(getResources().getString(R.string.setting_ui_hd_signal_check_aerial) + " 3");
            this.d.setText(getResources().getString(R.string.setting_ui_hd_signal_check_aerial) + " 4");
            this.e = (TextView) findViewById(R.id.fpv_hd_signal_aerial1_value);
            this.f = (TextView) findViewById(R.id.fpv_hd_signal_aerial2_value);
            this.g = (TextView) findViewById(R.id.fpv_hd_signal_aerial3_value);
            this.h = (TextView) findViewById(R.id.fpv_hd_signal_aerial4_value);
            b();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        b();
    }

    public void onEventMainThread(a aVar) {
        b();
    }

    private void b() {
        if (a.a()) {
            setVisibility(0);
            int a = a(DataOsdGetPushSignalQuality.getInstance().getAerial2DownSignalQuality());
            int a2 = a(DataOsdGetPushSignalQuality.getInstance().getAerial1DownSignalQuality());
            int a3 = a(DataOsdGetPushSignalQuality.getInstance().getAerial2UpSignalQuality());
            int a4 = a(DataOsdGetPushSignalQuality.getInstance().getAerial1UpSignalQuality());
            String str = " dBm";
            this.e.setText(a + str);
            this.f.setText(a2 + str);
            this.g.setText(a3 + str);
            this.h.setText(a4 + str);
            return;
        }
        setVisibility(8);
    }

    private int a(int i) {
        if (i < -100) {
            return -100;
        }
        if (i > -20) {
            return -20;
        }
        return i;
    }

    public void onClick(View view) {
        int i = 0;
        int id = view.getId();
        if (id != R.id.setting_ui_hd_pip_left_top_ly) {
            if (id == R.id.setting_ui_hd_pip_right_top_ly) {
                i = 1;
            } else if (id == R.id.setting_ui_hd_pip_left_bottom_ly) {
                i = 2;
            } else if (id == R.id.setting_ui_hd_pip_right_bottom_ly) {
                i = 3;
            }
        }
        if (i != DataDm368GetGParams.getInstance().getOutputLocation()) {
            new DataDm368SetGParams().a(CmdId.m, i).start(new d(this) {
                final /* synthetic */ LB2SignalView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.b();
                        }
                    });
                }
            });
        }
    }
}
