package dji.pilot.fpv.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.b;
import dji.pilot.fpv.view.DJIStageView.d;
import dji.pilot.fpv.view.DJIStageView.e;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public abstract class a extends c implements b, d {
    protected DJILinearLayout a = null;
    protected DJIRelativeLayout b = null;
    protected DJITextView c = null;
    protected DJIImageView d = null;
    protected DJIImageView e = null;
    protected DJITextView f = null;
    protected DJIStageView g = null;
    private e h = null;
    private OnClickListener i = null;
    private OnClickListener j = null;
    private DJITextView k;

    public a(Context context) {
        super(context);
        h();
    }

    private void h() {
        i();
        j();
    }

    private void i() {
        this.i = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.sl == id) {
                    if (!this.a.g.handleClose()) {
                        this.a.c();
                    }
                } else if (R.id.sg == id) {
                    if (!this.a.g.handleGoBack() && this.a.g.canGoBack()) {
                        this.a.g.destoryStageView(true);
                    }
                } else if (R.id.si == id && this.a.j != null) {
                    this.a.j.onClick(view);
                }
            }
        };
        this.h = new e(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                this.a.a(i, i2, i3);
            }

            public void a(int i) {
                this.a.dismiss();
            }
        };
    }

    protected void a(int i, int i2, int i3) {
        if (i2 != 0) {
            this.c.setText(i2);
        }
        if (i == 1) {
            this.d.show();
            this.e.go();
            return;
        }
        this.e.show();
        this.d.show();
    }

    private void j() {
        setContentView(R.layout.fpv_gen_dialog_view);
        this.a = (DJILinearLayout) findViewById(R.id.a1f);
        this.b = (DJIRelativeLayout) findViewById(R.id.a1g);
        this.c = (DJITextView) findViewById(R.id.sj);
        this.d = (DJIImageView) findViewById(R.id.sl);
        this.e = (DJIImageView) findViewById(R.id.sg);
        this.f = (DJITextView) findViewById(R.id.sk);
        this.k = (DJITextView) findViewById(R.id.si);
        this.g = (DJIStageView) findViewById(R.id.a1h);
        this.d.setOnClickListener(this.i);
        this.e.setOnClickListener(this.i);
        this.k.setOnClickListener(this.i);
        this.g.setOnStageChangeListener(this.h);
        this.g.setOnOptListener(this);
    }

    protected boolean a() {
        if (this.g.handleGoBack()) {
            return true;
        }
        if (!this.g.canGoBack()) {
            return false;
        }
        this.g.destoryStageView(true);
        return true;
    }

    protected void onCreate(Bundle bundle) {
        a(DJIBaseActivity.screenWidth - dji.pilot.fpv.model.b.a(this.N, R.dimen.ix), DJIBaseActivity.screenHeight, 0, 17, true, true);
    }

    protected void onStart() {
        super.onStart();
        this.g.dispatchOnStart(false);
    }

    protected void onStop() {
        this.g.dispatchOnStop(false);
        super.onStop();
    }

    public boolean b() {
        c();
        return true;
    }

    protected void c() {
        dismiss();
    }

    public void d() {
    }

    public void e() {
    }

    public void f() {
    }

    public void g() {
    }
}
