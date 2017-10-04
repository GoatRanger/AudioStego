package dji.setting.ui.vision;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataSingleVisualParam;
import dji.midware.data.model.P3.DataSingleVisualParam.ParamCmdId;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class VisionTapFlyParallelView extends DividerLinearLayout implements OnCheckedChangeListener {
    private volatile boolean a = false;
    private Switch b = null;

    public VisionTapFlyParallelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_vision_tapfly_parallel);
        if (!isInEditMode()) {
            this.b = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.b.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        int i = 0;
        if (this.a != z) {
            DataSingleVisualParam a = new DataSingleVisualParam().a(false).a(ParamCmdId.l);
            if (z) {
                i = 1;
            }
            a.b(i).start(new d(this) {
                final /* synthetic */ VisionTapFlyParallelView b;

                public void onSuccess(Object obj) {
                    this.b.a = z;
                    this.b.a(true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.a(true);
                }
            });
        }
    }

    private void a(boolean z) {
        if (z) {
            post(new Runnable(this) {
                final /* synthetic */ VisionTapFlyParallelView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.setChecked(this.a.a);
                }
            });
        } else {
            this.b.setChecked(this.a);
        }
    }

    public void initParams() {
        final DataSingleVisualParam dataSingleVisualParam = new DataSingleVisualParam();
        dataSingleVisualParam.a(true).a(ParamCmdId.l).start(new d(this) {
            final /* synthetic */ VisionTapFlyParallelView b;

            public void onSuccess(Object obj) {
                this.b.a = dataSingleVisualParam.j() == 1;
                this.b.a(true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            onEventMainThread(i.getInstance().c());
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (dji.pilot.publics.e.a.k(productType)) {
            setVisibility(0);
            initParams();
            return;
        }
        setVisibility(8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
