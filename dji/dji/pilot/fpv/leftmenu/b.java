package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIEditText;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class b extends c implements OnClickListener {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    private DJIImageView h = null;
    private DJITextView i = null;
    private DJITextView j = null;
    private DJITextView k = null;
    private DJILinearLayout l = null;
    private DJIEditText m = null;
    private DJITextView n = null;
    private DJIRelativeLayout o = null;
    private DJITextView p = null;
    private SeekBar q = null;
    private DJITextView r = null;
    private DJITextView s = null;
    private DJIImageView t = null;
    private OnSeekBarChangeListener u = null;
    private a v = null;
    private boolean w = true;
    private int x = R.dimen.ld;

    public interface a {
        void a(DialogInterface dialogInterface, int i);

        void a(DialogInterface dialogInterface, boolean z, int i);

        void b(DialogInterface dialogInterface, int i);
    }

    public b(Context context) {
        super(context);
        f();
    }

    public b(Context context, int i) {
        super(context);
        this.x = i;
        f();
    }

    public b a(a aVar) {
        this.v = aVar;
        return this;
    }

    public b a(int i) {
        if (i == 0) {
            this.h.setBackgroundResource(R.drawable.fpv_alert_icon_takeoff);
            this.i.setTextColor(this.N.getResources().getColor(R.color.do));
            this.j.setTextColor(this.N.getResources().getColor(R.color.do));
        } else if (1 == i) {
            this.h.setBackgroundResource(R.drawable.fpv_alert_icon_takeoff);
            this.i.setTextColor(this.N.getResources().getColor(R.color.dq));
            this.j.setTextColor(this.N.getResources().getColor(R.color.dq));
        } else if (2 == i) {
            this.h.setBackgroundResource(R.drawable.fpv_warning_icon);
            this.i.setTextColor(this.N.getResources().getColor(R.color.dp));
            this.j.setTextColor(this.N.getResources().getColor(R.color.dp));
        } else if (3 == i) {
            this.h.setBackgroundResource(R.drawable.fpv_warning_icon);
            this.i.setTextColor(this.N.getResources().getColor(R.color.dp));
            this.j.setTextColor(this.N.getResources().getColor(R.color.dp));
        } else if (4 == i) {
            this.h.setBackgroundResource(R.drawable.fpv_alert_mode_on);
            this.i.setTextColor(this.N.getResources().getColor(R.color.do));
            this.j.setTextColor(this.N.getResources().getColor(R.color.do));
        } else if (6 == i) {
            this.h.go();
            this.i.setTextColor(this.N.getResources().getColor(R.color.om));
            this.j.go();
        }
        return this;
    }

    public b b(int i) {
        this.h.setBackgroundResource(i);
        return this;
    }

    public b a(String str) {
        this.i.setText(str);
        return this;
    }

    public b a(boolean z) {
        if (z) {
            this.i.show();
        } else {
            this.i.go();
        }
        return this;
    }

    public b a(int i, String str) {
        this.j.setVisibility(i);
        this.j.setText(str);
        return this;
    }

    public b b(String str) {
        this.k.setText(str);
        return this;
    }

    public b b() {
        return this;
    }

    public b c() {
        this.k.go();
        return this;
    }

    public b c(int i) {
        this.k.setMaxHeight(i);
        return this;
    }

    public b c(String str) {
        this.r.setText(str);
        return this;
    }

    public b d(String str) {
        this.s.setText(str);
        return this;
    }

    public b d(int i) {
        this.s.setVisibility(i);
        this.t.setVisibility(i);
        return this;
    }

    public int d() {
        String obj = this.m.getText().toString();
        int i = 0;
        if (obj.trim().length() > 0) {
            try {
                i = Integer.parseInt(obj);
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public b a(int i, int i2) {
        this.l.setVisibility(i);
        int v = DJIGenSettingDataManager.getInstance().v();
        if (v == 0) {
            this.n.setText(R.string.fpv_gensetting_foot);
            i2 = (int) DJIGenSettingDataManager.getInstance().b((float) i2);
        } else if (v == 1) {
            this.n.setText(R.string.fpv_gensetting_metric);
        }
        this.m.setText(String.valueOf(i2));
        return this;
    }

    public b e(String str) {
        this.p.setText(str);
        return this;
    }

    public b e(int i) {
        this.q.setProgress(0);
        this.o.setVisibility(i);
        return this;
    }

    public b f(int i) {
        this.m.setVisibility(i);
        return this;
    }

    private void e() {
        this.u = new OnSeekBarChangeListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.g();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }
        };
    }

    private void f() {
        e();
        setContentView(R.layout.fpv_leftmenu_dlg);
        this.h = (DJIImageView) findViewById(R.id.a41);
        this.i = (DJITextView) findViewById(R.id.a42);
        this.j = (DJITextView) findViewById(R.id.a43);
        this.k = (DJITextView) findViewById(R.id.a44);
        this.l = (DJILinearLayout) findViewById(R.id.a45);
        this.m = (DJIEditText) findViewById(R.id.a46);
        this.n = (DJITextView) findViewById(R.id.a47);
        this.o = (DJIRelativeLayout) findViewById(R.id.a48);
        this.p = (DJITextView) findViewById(R.id.a49);
        this.q = (SeekBar) findViewById(R.id.a4_);
        this.r = (DJITextView) findViewById(R.id.a4b);
        this.s = (DJITextView) findViewById(R.id.a4d);
        this.t = (DJIImageView) findViewById(R.id.a4c);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.q.setOnSeekBarChangeListener(this.u);
        this.q.setPadding(0, 0, 0, 0);
    }

    private void g() {
        if (this.q.getProgress() < 95 || !this.w) {
            this.q.setProgress(0);
            b(false);
            return;
        }
        this.q.setProgress(100);
        b(true);
    }

    private void b(boolean z) {
        if (this.v != null) {
            this.v.a(this, z, 0);
        }
    }

    private void h() {
        if (this.v != null) {
            this.v.a(this, 0);
        }
    }

    private void i() {
        if (this.v != null) {
            this.v.b(this, 0);
        }
    }

    protected void onCreate(Bundle bundle) {
        a((int) getContext().getResources().getDimension(this.x), -2, 0, 17, true, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.a4b) {
            h();
        } else if (id == R.id.a4d) {
            i();
        }
    }
}
