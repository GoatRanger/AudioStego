package dji.setting.ui.vision;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.a;
import dji.pilot.setting.ui.R;
import dji.pilot.visual.util.c;
import dji.setting.ui.widget.DividerLinearLayout;

public class VisionReboundView extends DividerLinearLayout implements OnCheckedChangeListener, a {
    private ParamInfo E = null;
    private int F = 0;
    private Switch a = null;

    public VisionReboundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_vision_rebound);
        if (!isInEditMode()) {
            this.E = d.read(a.z);
            this.a = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.a.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = 1;
        if ((this.E.value.intValue() != 0) != z) {
            this.a.setEnabled(false);
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            dataFlycSetParams.a(new String[]{a.z});
            Number[] numberArr = new Integer[1];
            if (!z) {
                i = 0;
            }
            numberArr[0] = Integer.valueOf(i);
            dataFlycSetParams.a(numberArr);
            dataFlycSetParams.start(new dji.midware.e.d(this) {
                final /* synthetic */ VisionReboundView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a.setEnabled(true);
                        }
                    });
                    DJILogHelper.getInstance().LOGD(c.a, "==== Overshot On success", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a(false);
                            this.a.a.a.setEnabled(true);
                        }
                    });
                }
            });
        }
    }

    private void a(boolean z) {
        this.a.setChecked(this.E.value.intValue() != 0);
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (flycVersion != this.F) {
            this.F = flycVersion;
            b();
        }
    }

    public void onEventMainThread(a.a aVar) {
        if (a.z.equals(aVar.a)) {
            a(false);
        }
    }

    private void b() {
        if (ProductType.Pomato != i.getInstance().c() || this.F < 16) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        a(false);
        b.getInstance().a(a.z);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            b();
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
