package dji.setting.ui.vision;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.setting.ui.R;
import dji.pilot.visual.util.c;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class VisionGHAssView extends DividerLinearLayout implements OnCheckedChangeListener, a {
    private Switch f = null;
    private ParamInfo g = null;

    public VisionGHAssView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        if (!isInEditMode()) {
        }
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_vision_ghass_view);
        if (!isInEditMode()) {
            this.g = d.read(a.e);
            this.f = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.f.setOnCheckedChangeListener(this);
        }
    }

    private void b() {
        this.f.setChecked(this.g.value.intValue() != 0);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == this.f) {
            if ((this.g.value.intValue() != 0) != z) {
                if (z) {
                    this.f.setEnabled(false);
                    new DataFlycSetParams().a(a.e, Integer.valueOf(1)).start(new dji.midware.e.d(this) {
                        final /* synthetic */ VisionGHAssView a;

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
                                    this.a.a.f.setEnabled(true);
                                }
                            });
                            DJILogHelper.getInstance().LOGD(c.a, "==== GH Off Success", false, true);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.b();
                                    this.a.a.f.setEnabled(true);
                                }
                            });
                        }
                    });
                    return;
                }
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_vision_ghass_tip_content, new OnClickListener(this) {
                    final /* synthetic */ VisionGHAssView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.f.setEnabled(false);
                        new DataFlycSetParams().a(a.e, Integer.valueOf(0)).start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                this.a.a.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.a.f.setEnabled(true);
                                    }
                                });
                                DJILogHelper.getInstance().LOGD(c.a, "==== GH Off Success", false, true);
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.a.a.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.a.b();
                                        this.a.a.a.f.setEnabled(true);
                                    }
                                });
                            }
                        });
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ VisionGHAssView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.b();
                    }
                }).setCancelable(false);
            }
        }
    }

    public void onEventMainThread(a.a aVar) {
        if (a.e.equals(aVar.a)) {
            b();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            onEventMainThread(i.getInstance().c());
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (dji.pilot.publics.e.a.k(productType)) {
            setVisibility(0);
            b();
            b.getInstance().a(a.e);
            return;
        }
        setVisibility(8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        onEventMainThread(i.getInstance().c());
    }

    protected void onDetachedFromWindow() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
