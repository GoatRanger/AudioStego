package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class LB2PipView extends DividerLinearLayout implements OnClickListener {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;

    public LB2PipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_hd_lb2_pip);
        if (!isInEditMode()) {
            this.a = (ImageView) findViewById(R.id.setting_ui_hd_pip_left_top);
            this.b = (ImageView) findViewById(R.id.setting_ui_hd_pip_right_top);
            this.c = (ImageView) findViewById(R.id.setting_ui_hd_pip_left_bottom);
            this.d = (ImageView) findViewById(R.id.setting_ui_hd_pip_right_bottom);
            findViewById(R.id.setting_ui_hd_pip_left_top_ly).setOnClickListener(this);
            findViewById(R.id.setting_ui_hd_pip_right_top_ly).setOnClickListener(this);
            findViewById(R.id.setting_ui_hd_pip_left_bottom_ly).setOnClickListener(this);
            findViewById(R.id.setting_ui_hd_pip_right_bottom_ly).setOnClickListener(this);
            b();
            a.f();
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

    public void onEventMainThread(ProductType productType) {
        dji.publics.a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2PipView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        }, 700);
    }

    public void onEventMainThread(a aVar) {
        b();
    }

    private void b() {
        if (a.a()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        int outputLocation = DataDm368GetGParams.getInstance().getOutputLocation();
        this.a.setVisibility(8);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        switch (outputLocation) {
            case 0:
                this.a.setVisibility(0);
                return;
            case 1:
                this.b.setVisibility(0);
                return;
            case 2:
                this.c.setVisibility(0);
                return;
            case 3:
                this.d.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        int i = 0;
        this.a.setVisibility(8);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        int id = view.getId();
        if (id == R.id.setting_ui_hd_pip_left_top_ly) {
            this.a.setVisibility(0);
        } else if (id == R.id.setting_ui_hd_pip_right_top_ly) {
            this.b.setVisibility(0);
            i = 1;
        } else if (id == R.id.setting_ui_hd_pip_left_bottom_ly) {
            this.c.setVisibility(0);
            i = 2;
        } else if (id == R.id.setting_ui_hd_pip_right_bottom_ly) {
            this.d.setVisibility(0);
            i = 3;
        }
        new DataDm368SetGParams().a(CmdId.m, i).start(new d(this) {
            final /* synthetic */ LB2PipView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

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
