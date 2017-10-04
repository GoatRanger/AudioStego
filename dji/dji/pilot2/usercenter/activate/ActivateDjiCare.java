package dji.pilot2.usercenter.activate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import dji.logic.f.d;
import dji.pilot.R;

public class ActivateDjiCare extends ActivateBaseView implements OnClickListener {
    private View c;
    private View d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private TextView h;
    private a i;

    private class a {
        int a;
        int b;
        int c;
        int d;
        String e;
        final /* synthetic */ ActivateDjiCare f;

        public a(ActivateDjiCare activateDjiCare, int i, int i2, int i3, int i4, String str) {
            this.f = activateDjiCare;
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = str;
        }
    }

    public ActivateDjiCare(Context context) {
        super(context);
    }

    public ActivateDjiCare(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateDjiCare(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean canShowTopView() {
        return false;
    }

    public void setData() {
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            this.c = findViewById(R.id.dh);
            this.c.setOnClickListener(this);
            this.d = findViewById(R.id.dm);
            this.d.setOnClickListener(this);
            this.f = (TextView) findViewById(R.id.di);
            this.e = (TextView) findViewById(R.id.dk);
            this.g = (ImageView) findViewById(R.id.dj);
            this.h = (TextView) findViewById(R.id.dl);
            if (d.a(null)) {
                this.i = new a(this, R.string.v2_activate_dji_care_title_shield, R.string.v2_active_dji_care_desc_osmo, R.string.v2_active_dji_care_tip_osmo, R.drawable.activate_dji_care_img_osmo, "http://www.dji.com/service/osmo-shield");
            } else if (dji.pilot.publics.e.a.c(null)) {
                this.i = new a(this, R.string.v2_activate_dji_care_title_kumquat, R.string.v2_active_dji_care_desc_kumquat, R.string.v2_active_dji_care_tip_kumquat, R.drawable.v2_activate_dji_care, "http://www.dji.com/mobile/service/djicare-refresh");
            } else {
                this.i = new a(this, R.string.v2_activate_dji_care_title_keep_flying, R.string.v2_active_dji_care_desc_drone, R.string.v2_active_dji_care_tip_drone, R.drawable.v2_activate_dji_care, "http://www.dji.com/cn/support/djicare");
            }
            this.f.setText(this.i.a);
            this.e.setText(this.i.b);
            this.g.setImageResource(this.i.d);
            this.h.setText(this.i.c);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dh:
                ((Activity) getContext()).finish();
                return;
            case R.id.dm:
                b();
                ((Activity) getContext()).finish();
                return;
            default:
                return;
        }
    }

    private void b() {
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.i.e)));
    }
}
