package dji.pilot2.usercenter.activate;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.c.d;
import dji.pilot.publics.e.a;

public class ActivateStatementView extends RelativeLayout implements OnClickListener, b {
    Button a;
    private g b = null;
    private TextView c = null;
    private TextView d = null;
    private ImageView e = null;
    private TextView f = null;

    public ActivateStatementView(Context context) {
        super(context);
    }

    public ActivateStatementView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateStatementView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean handleGoNext() {
        return true;
    }

    public boolean handleGoPre() {
        return false;
    }

    public boolean onResume() {
        return false;
    }

    public boolean onShow() {
        if (this.a != null) {
            this.a.setEnabled(true);
        }
        return true;
    }

    public boolean canShowTopView() {
        return false;
    }

    public void setMainTopViewControl(g gVar) {
        this.b = gVar;
    }

    public boolean canGoPre() {
        return false;
    }

    public boolean canGoNext() {
        return false;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        b();
    }

    private void a() {
        this.c = (TextView) findViewById(R.id.em);
        this.d = (TextView) findViewById(R.id.en);
        this.e = (ImageView) findViewById(R.id.ep);
        this.f = (TextView) findViewById(R.id.eo);
        this.a = (Button) findViewById(R.id.eq);
        this.a.setOnClickListener(this);
    }

    private void b() {
        if (!isInEditMode()) {
            ProductType c = i.getInstance().c();
            int e = d.getInstance().e(c);
            this.e.setImageResource(d.getInstance().f(c));
            this.c.setText(Html.fromHtml(getContext().getResources().getString(e)));
            if (b.h(null)) {
                this.d.setText(R.string.v2_active_osmo_desc);
            }
            if (a.c(c)) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            if (b.r(c) || c == ProductType.A3 || c == ProductType.N3) {
                TextView textView = (TextView) findViewById(R.id.en);
                if (c == ProductType.PM820) {
                    textView.setText(R.string.fpv_m600_is_not_activite);
                } else if (c == ProductType.PM820PRO) {
                    textView.setText(R.string.fpv_m600_pro_is_not_activite);
                } else if (c == ProductType.A3) {
                    textView.setText(R.string.fpv_a3_is_not_activite);
                } else {
                    textView.setText(R.string.fpv_n3_is_not_activite);
                }
                this.a.setVisibility(4);
                this.a.setText(R.string.back);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.eq) {
            ProductType c = i.getInstance().c();
            if (b.r(c) || c == ProductType.A3 || c == ProductType.N3) {
                ((Activity) getContext()).finish();
            } else if (this.b != null) {
                this.b.a();
                this.a.setEnabled(false);
            }
        }
    }
}
