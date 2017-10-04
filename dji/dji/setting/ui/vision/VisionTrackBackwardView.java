package dji.setting.ui.vision;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataSingleVisualParam;
import dji.midware.data.model.P3.DataSingleVisualParam.ParamCmdId;
import dji.pilot.setting.ui.R;
import dji.pilot.visual.a.d;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class VisionTrackBackwardView extends DividerLinearLayout implements OnCheckedChangeListener {
    private volatile boolean a = false;
    private Switch b = null;

    public VisionTrackBackwardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_vision_track_backward);
        if (!isInEditMode()) {
            ((TextView) findViewById(R.id.setting_ui_vision_track_bw_desc)).setText(Html.fromHtml(getResources().getString(R.string.setting_ui_vision_backward_desc)));
            this.b = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.b.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        if (this.a != z) {
            this.b.setEnabled(false);
            new DataSingleVisualParam().a(false).a(ParamCmdId.d).b(z ? d.c : 0.0f).start(new dji.midware.e.d(this) {
                final /* synthetic */ VisionTrackBackwardView b;

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
        final DataSingleVisualParam a = new DataSingleVisualParam().a(true).a(ParamCmdId.d);
        a.start(new dji.midware.e.d(this) {
            final /* synthetic */ VisionTrackBackwardView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.a = a.d() > 0.0f;
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
