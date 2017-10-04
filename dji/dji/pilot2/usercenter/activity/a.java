package dji.pilot2.usercenter.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.pilot.usercenter.b.d;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;

public class a extends c {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private OnClickListener d = null;
    private d e = d.getInstance();

    public a(Context context) {
        super(context);
        c();
    }

    private void b() {
        this.d = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.c29) {
                    this.a.e.f();
                    this.a.dismiss();
                } else if (id == R.id.c2_) {
                    this.a.e.g();
                    this.a.dismiss();
                } else if (id == R.id.c2a) {
                    this.a.dismiss();
                }
            }
        };
    }

    private void c() {
        b();
        setContentView(R.layout.usercenter_flightrecord_clear_view);
        this.a = (DJITextView) findViewById(R.id.c29);
        this.b = (DJITextView) findViewById(R.id.c2_);
        this.c = (DJITextView) findViewById(R.id.c2a);
        this.a.setOnClickListener(this.d);
        this.b.setOnClickListener(this.d);
        this.c.setOnClickListener(this.d);
    }

    protected void onCreate(Bundle bundle) {
        int i;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            i = -1;
        } else {
            i = b.a(this.N, R.dimen.gs);
        }
        a(i, -2, 0, 17, false, false);
        a(0.4f);
    }
}
