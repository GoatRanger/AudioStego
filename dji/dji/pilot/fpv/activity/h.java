package dji.pilot.fpv.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataFlycSetRTKState;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRTKPushStatus;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJITextView;

public class h extends c implements OnClickListener {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DJITextView f = null;
    private DJITextView g = null;
    private DJITextView h = null;
    private DJITextView i = null;
    private DJITextView j = null;
    private DJITextView k = null;
    private DJITextView l = null;
    private DJITextView m = null;
    private ImageView n;
    private ImageView o;
    private Switch p;
    private LinearLayout q;
    private ScrollView r;
    private TextView s;
    private boolean t = false;

    public h(Context context) {
        super(context);
        setContentView(R.layout.fpv_rtk_dialog);
        findViewById(R.id.aba).setOnClickListener(this);
        this.a = (DJITextView) findViewById(R.id.aav);
        this.b = (DJITextView) findViewById(R.id.aaw);
        this.c = (DJITextView) findViewById(R.id.aax);
        this.d = (DJITextView) findViewById(R.id.aaz);
        this.e = (DJITextView) findViewById(R.id.ab0);
        this.f = (DJITextView) findViewById(R.id.ab1);
        this.g = (DJITextView) findViewById(R.id.ab2);
        this.h = (DJITextView) findViewById(R.id.ab3);
        this.i = (DJITextView) findViewById(R.id.ab4);
        this.j = (DJITextView) findViewById(R.id.ab5);
        this.k = (DJITextView) findViewById(R.id.ab6);
        this.l = (DJITextView) findViewById(R.id.ab7);
        this.m = (DJITextView) findViewById(R.id.ab8);
        this.n = (ImageView) findViewById(R.id.ab9);
        this.o = (ImageView) findViewById(R.id.ab_);
        this.p = (Switch) findViewById(R.id.aas);
        this.q = (LinearLayout) findViewById(R.id.aat);
        this.r = (ScrollView) findViewById(R.id.aau);
        this.s = (TextView) findViewById(R.id.aay);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DataFlycSetRTKState.getInstance().a(((Switch) view).isChecked()).start(null);
            }
        });
    }

    public void onStart() {
        super.onStart();
        onEventMainThread(DataRTKPushStatus.getInstance());
        dji.thirdparty.a.c.a().a(this);
    }

    public void onStop() {
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
    }

    public void onEventMainThread(DataRTKPushStatus dataRTKPushStatus) {
        if (dataRTKPushStatus.isPushLosed()) {
            dismiss();
        }
        if (dataRTKPushStatus.isGetted()) {
            int c = dataRTKPushStatus.c();
            if ((c & 1) != 0) {
                this.a.setText("" + (c >> 1));
            } else {
                this.a.setText("N/A");
            }
            c = dataRTKPushStatus.f();
            if ((c & 1) != 0) {
                this.b.setText("" + (c >> 1));
            } else {
                this.b.setText("N/A");
            }
            c = dataRTKPushStatus.i();
            if ((c & 1) != 0) {
                this.c.setText("" + (c >> 1));
            } else {
                this.c.setText("N/A");
            }
            a(dataRTKPushStatus);
            this.g.setText(String.format("%.6f", new Object[]{Double.valueOf(dataRTKPushStatus.l())}));
            this.h.setText(String.format("%.6f", new Object[]{Double.valueOf(dataRTKPushStatus.o())}));
            this.i.setText(String.format("%.6f", new Object[]{Double.valueOf(dataRTKPushStatus.m())}));
            this.j.setText(String.format("%.6f", new Object[]{Double.valueOf(dataRTKPushStatus.p())}));
            this.k.setText(String.format("%.3f", new Object[]{Float.valueOf(dataRTKPushStatus.n())}));
            this.l.setText(String.format("%.3f", new Object[]{Float.valueOf(dataRTKPushStatus.q())}));
            this.m.setText(String.format("%.2f", new Object[]{Float.valueOf(dataRTKPushStatus.r())}));
            this.o.setImageResource(R.drawable.rtk_enable_status_img);
            if (dataRTKPushStatus.a() == 2) {
                this.c.setText("N/A");
                this.f.setText("N/A");
                this.h.setText("N/A");
                this.j.setText("N/A");
                this.l.setText("N/A");
            }
            if (dataRTKPushStatus.b() != 50) {
                this.g.setText("N/A");
                this.i.setText("N/A");
                this.k.setText("N/A");
                this.o.setImageResource(R.drawable.rtk_disable_status_img);
            }
            if (dataRTKPushStatus.s() != 50) {
                this.m.setText("N/A");
                this.n.setImageResource(R.drawable.rtk_disable_status_img);
            } else {
                this.n.setImageResource(R.drawable.rtk_enable_status_img);
            }
            if (dataRTKPushStatus.t()) {
                this.p.setChecked(true);
                this.r.setVisibility(0);
                this.q.setVisibility(8);
                return;
            }
            this.p.setChecked(false);
            this.r.setVisibility(8);
            this.q.setVisibility(0);
            return;
        }
        this.a.setText("N/A");
        this.b.setText("N/A");
        this.c.setText("N/A");
        this.d.setText("N/A");
        this.e.setText("N/A");
        this.f.setText("N/A");
        this.g.setText("N/A");
        this.h.setText("N/A");
        this.i.setText("N/A");
        this.j.setText("N/A");
        this.k.setText("N/A");
        this.l.setText("N/A");
        this.m.setText("N/A");
        this.o.setImageResource(R.drawable.rtk_disable_status_img);
    }

    private void a(DataRTKPushStatus dataRTKPushStatus) {
        int d = dataRTKPushStatus.d();
        if ((d & 1) != 0) {
            this.d.setText("" + (d >> 1));
            this.s.setText(R.string.fpv_rtk_dialog_north_num);
        } else {
            this.d.setText("N/A");
        }
        d = dataRTKPushStatus.g();
        if ((d & 1) != 0) {
            this.e.setText("" + (d >> 1));
        } else {
            this.e.setText("N/A");
        }
        d = dataRTKPushStatus.j();
        if ((d & 1) != 0) {
            this.f.setText("" + (d >> 1));
            return;
        }
        this.f.setText("N/A");
        d = dataRTKPushStatus.e();
        if ((d & 1) != 0) {
            this.d.setText("" + (d >> 1));
            this.s.setText(R.string.fpv_rtk_dialog_glonass_num);
        } else {
            this.d.setText("N/A");
        }
        d = dataRTKPushStatus.h();
        if ((d & 1) != 0) {
            this.e.setText("" + (d >> 1));
        } else {
            this.e.setText("N/A");
        }
        d = dataRTKPushStatus.k();
        if ((d & 1) != 0) {
            this.f.setText("" + (d >> 1));
        } else {
            this.f.setText("N/A");
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (this.t != dataOsdGetPushCommon.isMotorUp()) {
            this.t = dataOsdGetPushCommon.isMotorUp();
            if (this.t) {
                this.p.setEnabled(false);
            } else {
                this.p.setEnabled(true);
            }
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            dismiss();
        }
    }

    protected void onCreate(Bundle bundle) {
        a(DJIBaseActivity.screenWidth - b.a(this.N, R.dimen.yp), DJIBaseActivity.screenHeight - b.a(this.N, R.dimen.yo), 0, 17, true, false);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aba:
                dismiss();
                return;
            default:
                return;
        }
    }
}
