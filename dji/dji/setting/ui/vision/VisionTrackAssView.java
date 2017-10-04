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
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataSingleVisualParam;
import dji.midware.data.model.P3.DataSingleVisualParam.ParamCmdId;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class VisionTrackAssView extends DividerLinearLayout implements OnCheckedChangeListener {
    private volatile boolean a = false;
    private Switch b = null;

    public VisionTrackAssView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_vision_track_ass);
        if (!isInEditMode()) {
            this.b = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.b.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        if (this.a != z) {
            this.b.setEnabled(false);
            new DataSingleVisualParam().a(false).a(ParamCmdId.e).b(z).start(new d(this) {
                final /* synthetic */ VisionTrackAssView b;

                public void onSuccess(Object obj) {
                    this.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.a = z;
                            this.a.b.b.setEnabled(true);
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.b.setChecked(this.a.b.a);
                            this.a.b.b.setEnabled(true);
                        }
                    });
                }
            });
        }
    }

    public void initParams() {
        final DataSingleVisualParam a = new DataSingleVisualParam().a(true).a(ParamCmdId.e);
        a.start(new d(this) {
            final /* synthetic */ VisionTrackAssView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.a = a.e();
                        this.a.b.b.setChecked(this.a.b.a);
                    }
                });
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
            onEventMainThread(DataEyeGetPushException.getInstance());
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(DataEyeGetPushException dataEyeGetPushException) {
        if (dataEyeGetPushException.getVisionVersion() >= 16778240) {
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
