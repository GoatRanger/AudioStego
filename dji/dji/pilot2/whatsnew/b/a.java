package dji.pilot2.whatsnew.b;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.d;

public class a extends c {
    public Boolean a = Boolean.valueOf(false);
    public Boolean b = Boolean.valueOf(false);
    private ImageView c;
    private DJIStateTextView d;
    private DJIStateTextView e;
    private OnClickListener f = null;
    private d g = d.getInstance();

    public a(Context context) {
        super(context);
    }

    protected void b() {
        this.f = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.d5e) {
                    DJILogHelper.getInstance().LOGI("bob", "onclick yes");
                    this.a.g.a(3);
                    this.a.a = Boolean.valueOf(true);
                    this.a.dismiss();
                } else if (id == R.id.d5f) {
                    DJILogHelper.getInstance().LOGI("bob", "onclick no");
                    this.a.dismiss();
                } else if (id == R.id.d5d) {
                    DJILogHelper.getInstance().LOGI("bob", "onclick no");
                    this.a.b = Boolean.valueOf(true);
                    this.a.dismiss();
                }
            }
        };
    }

    protected void c() {
        setContentView(R.layout.v2_whatsnew_dialog);
        b();
        this.d = (DJIStateTextView) findViewById(R.id.d5e);
        this.e = (DJIStateTextView) findViewById(R.id.d5f);
        this.c = (ImageView) findViewById(R.id.d5d);
        this.d.setOnClickListener(this.f);
        this.e.setOnClickListener(this.f);
        this.c.setOnClickListener(this.f);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
        int a = b.a(this.N, R.dimen.ri) + b.a(this.N, R.dimen.n3);
        a(b.a(this.N, R.dimen.gb), -2, 0, 17, true, true);
        a(0.4f);
    }
}
