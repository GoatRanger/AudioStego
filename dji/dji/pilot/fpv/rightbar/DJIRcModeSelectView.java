package dji.pilot.fpv.rightbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams.FlycMode;
import dji.pilot.R;
import dji.pilot.fpv.control.u;
import dji.thirdparty.a.c;

public class DJIRcModeSelectView extends RelativeLayout {
    protected RadioGroup a;
    protected RadioButton b;
    protected RadioButton c;
    protected RadioButton d;
    private u e = u.getInstance();
    private OnClickListener f = new OnClickListener(this) {
        final /* synthetic */ DJIRcModeSelectView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            int id = view.getId();
            FlycMode flycMode = FlycMode.b;
            switch (id) {
                case R.id.bkf:
                    flycMode = FlycMode.a;
                    break;
                case R.id.bkg:
                    flycMode = FlycMode.b;
                    break;
                case R.id.bkh:
                    flycMode = FlycMode.c;
                    break;
            }
            this.a.e.b(flycMode);
            this.a.postDelayed(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.updateRadioView();
                }
            }, 500);
        }
    };

    public DJIRcModeSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    private void a() {
        if (this.e.b()) {
            setVisibility(8);
            updateRadioView();
            return;
        }
        setVisibility(8);
    }

    private void b() {
        this.a = (RadioGroup) findViewById(R.id.bke);
        this.b = (RadioButton) findViewById(R.id.bkf);
        this.c = (RadioButton) findViewById(R.id.bkg);
        this.d = (RadioButton) findViewById(R.id.bkh);
        this.b.setOnClickListener(this.f);
        this.c.setOnClickListener(this.f);
        this.d.setOnClickListener(this.f);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
        } else if ((i == 8 || i == 4) && c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void updateRadioView() {
        FlycMode a = this.e.a();
        if (a == FlycMode.a) {
            this.b.setChecked(true);
        } else if (a == FlycMode.c) {
            this.d.setChecked(true);
        } else if (a == FlycMode.b) {
            this.c.setChecked(true);
        }
    }

    public void onEventMainThread(ProductType productType) {
        WM220LogUtil.LOGD("into DJIRcModeSelectView ProductType event");
        a();
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            a();
        } else {
            setVisibility(8);
        }
    }
}
