package dji.pilot2.simulator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIRepeatButton;
import dji.pilot.publics.widget.DJIRepeatButton.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class b extends c {
    private static final int i = 5;
    private static final int j = 1;
    private static final int m = 50;
    private static final float o = 20.0f;
    private static final float p = 0.0f;
    private DJIImageView a;
    private DJIRepeatButton b;
    private DJIRepeatButton c;
    private DJIRepeatButton d;
    private DJIRepeatButton e;
    private DJITextView f;
    private DJIImageView g;
    private int h = 0;
    private OnClickListener k;
    private a l;
    private float n = 2.0f;

    public b(Context context) {
        super(context);
        setContentView(R.layout.v2_dialog_wind_setting);
        d();
        e();
    }

    private void d() {
        this.k = new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(view);
            }
        };
        this.l = new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(View view, long j) {
                this.a.a(view);
            }
        };
    }

    private void a(View view) {
        switch (view.getId()) {
            case R.id.cju:
                dismiss();
                return;
            case R.id.cjw:
                b(this.n - 0.1f);
                return;
            case R.id.cjy:
                b(this.n + 0.1f);
                return;
            case R.id.ck0:
                a(this.h + 5);
                return;
            case R.id.ck1:
                a(this.h - 5);
                return;
            default:
                return;
        }
    }

    private void e() {
        this.a = (DJIImageView) findViewById(R.id.cju);
        this.b = (DJIRepeatButton) findViewById(R.id.ck0);
        this.c = (DJIRepeatButton) findViewById(R.id.ck1);
        this.d = (DJIRepeatButton) findViewById(R.id.cjw);
        this.e = (DJIRepeatButton) findViewById(R.id.cjy);
        this.g = (DJIImageView) findViewById(R.id.ck2);
        this.f = (DJITextView) findViewById(R.id.cjx);
        this.a.setOnClickListener(this.k);
        this.c.setOnClickListener(this.k);
        this.b.setOnClickListener(this.k);
        this.d.setOnClickListener(this.k);
        this.e.setOnClickListener(this.k);
        this.c.setRepeatListener(this.l, 50);
        this.b.setRepeatListener(this.l, 50);
        this.d.setRepeatListener(this.l, 50);
        this.e.setRepeatListener(this.l, 50);
        this.f.setText(String.format("%.1f M/S", new Object[]{Float.valueOf(this.n)}));
    }

    public void b(float f) {
        if (f >= o) {
            f = o;
        } else if (f <= 0.0f) {
            f = 0.0f;
        }
        this.n = f;
        this.f.setText(String.format("%.1f M/S", new Object[]{Float.valueOf(this.n)}));
    }

    public float b() {
        return this.n;
    }

    public void a(int i) {
        if (i < 0) {
            i += 360;
        } else if (i > 360) {
            i %= 360;
        }
        this.h = i;
        this.g.setRotation((float) this.h);
    }

    public int c() {
        return this.h;
    }

    protected void onCreate(Bundle bundle) {
        a((int) this.N.getResources().getDimension(R.dimen.ge), (int) this.N.getResources().getDimension(R.dimen.g9), 0, 17, true, true);
        a(0.4f);
        setCanceledOnTouchOutside(false);
    }
}
