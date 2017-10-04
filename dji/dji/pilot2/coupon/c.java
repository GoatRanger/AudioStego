package dji.pilot2.coupon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import dji.pilot.R;
import dji.pilot.fpv.d.c$c;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.coupon.couponmain.DJICouponMainActivity;
import dji.publics.DJIUI.DJITextView;

public class c extends dji.pilot.publics.objects.c implements OnClickListener {
    private Context a;
    private DJITextView b;
    private DJIStateTextView c;
    private RelativeLayout d;
    private String e;

    public c(Context context) {
        super(context);
        this.a = context;
        this.e = context.getResources().getString(R.string.v2_coupondialog_txt1);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_coupon_dialog);
        b();
        setCancelable(false);
    }

    private void b() {
        this.b = (DJITextView) findViewById(R.id.ci8);
        this.c = (DJIStateTextView) findViewById(R.id.ci9);
        this.d = (RelativeLayout) findViewById(R.id.ci_);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.b.setText(this.e);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ci9:
                dismiss();
                this.a.startActivity(new Intent(this.a, DJICouponMainActivity.class));
                e.b(c$c.a);
                return;
            case R.id.ci_:
                dismiss();
                return;
            default:
                return;
        }
    }

    public void a(String str) {
        this.e = str;
    }

    public static void a(Context context, String str) {
        c cVar = new c(context);
        if (!(str == null || str.equals(""))) {
            cVar.a(str);
        }
        cVar.show();
    }
}
