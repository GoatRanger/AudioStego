package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class VisualView extends ItemViewSwitch {
    private static final String a = "g_config.mvo_cfg.mvo_func_en_0";
    private boolean e = false;

    public VisualView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
            a.b().a("g_config.mvo_cfg.mvo_func_en_0");
        }
    }

    public void setFromVisionView(boolean z) {
        this.e = z;
        this.eU_ = !z;
        a();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = 1;
        boolean z2 = d.read("g_config.mvo_cfg.mvo_func_en_0").value.intValue() == 1;
        if (z2 != z) {
            DJILogHelper.getInstance().LOGD("", "curValue : " + z2, false, true);
            DJILogHelper.getInstance().LOGD("", "isChecked : " + z, false, true);
            if (z) {
                DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                String str = "g_config.mvo_cfg.mvo_func_en_0";
                if (!z) {
                    i = 0;
                }
                dataFlycSetParams.a(str, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
                    final /* synthetic */ VisualView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        a.b().a("g_config.mvo_cfg.mvo_func_en_0");
                    }

                    public void onFailure(a aVar) {
                        DJILogHelper.getInstance().LOGD("", "onFailure : " + aVar, false, true);
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a();
                            }
                        });
                    }
                });
                return;
            }
            dji.setting.ui.widget.a.c(getContext(), R.string.setting_ui_flyc_vision_desc, new OnClickListener(this) {
                final /* synthetic */ VisualView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    new DataFlycSetParams().a("g_config.mvo_cfg.mvo_func_en_0", Integer.valueOf(0)).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            a.b().a("g_config.mvo_cfg.mvo_func_en_0");
                        }

                        public void onFailure(a aVar) {
                            DJILogHelper.getInstance().LOGD("", "onFailure : " + aVar, false, true);
                            this.a.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a.a();
                                }
                            });
                        }
                    });
                    dialogInterface.dismiss();
                }
            }, new OnClickListener(this) {
                final /* synthetic */ VisualView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a();
                    dialogInterface.dismiss();
                }
            });
        }
    }

    private void a() {
        if (b()) {
            boolean z;
            setVisibility(0);
            if (d.read("g_config.mvo_cfg.mvo_func_en_0").value.intValue() == 1) {
                z = true;
            } else {
                z = false;
            }
            DJILogHelper.getInstance().LOGD("", "updateView isChecked : " + z, false, true);
            this.eS_.setChecked(z);
            return;
        }
        setVisibility(8);
    }

    private boolean b() {
        ProductType c = i.getInstance().c();
        if (this.e) {
            return dji.pilot.publics.e.a.k(c);
        }
        if ((c == ProductType.litchiX || c == ProductType.litchiS || c == ProductType.P34K || c == ProductType.Orange || c == ProductType.OrangeRAW || c == ProductType.BigBanana || c == ProductType.Olives || c == ProductType.OrangeCV600) && DataOsdGetPushCommon.getInstance().getFlycVersion() >= 6) {
            return true;
        }
        return false;
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals("g_config.mvo_cfg.mvo_func_en_0")) {
            a();
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
}
