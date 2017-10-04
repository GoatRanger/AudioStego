package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class RcCommonView extends DividerLinearLayout {
    private View a;
    private ViewStub b;
    private View c;

    public RcCommonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_rc_common);
        this.a = findViewById(R.id.setting_ui_rc_group_ly);
        this.c = findViewById(R.id.setting_ui_rc_common_ly);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RcCommonView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!a.a()) {
                    c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_rc_common, R.string.setting_ui_rc, this.a));
                }
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            b();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        if (dji.pilot.publics.e.a.j(null)) {
            this.a.setVisibility(0);
            if (this.b != null) {
                this.c.setVisibility(8);
            }
            this.eU_ = true;
            return;
        }
        this.a.setVisibility(8);
        if (this.b == null) {
            this.b = (ViewStub) findViewById(R.id.setting_ui_rc_common_viewstub);
            this.b.inflate();
        }
        this.c.setVisibility(0);
        this.eU_ = false;
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }
}
