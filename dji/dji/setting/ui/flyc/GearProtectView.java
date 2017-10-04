package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class GearProtectView extends ItemViewSwitch {
    private static final String a = "g_config.gear_cfg.auto_control_enable_0";
    private int e = 0;

    public GearProtectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
            new DataFlycGetParams().setInfos(new String[]{a}).start(new d(this) {
                final /* synthetic */ GearProtectView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
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

                public void onFailure(a aVar) {
                }
            });
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        int i = 1;
        if ((dji.midware.data.manager.P3.d.read(a).value.intValue() == 1) != z) {
            if (z) {
                DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                String str = a;
                if (!z) {
                    i = 0;
                }
                dataFlycSetParams.a(str, Integer.valueOf(i)).start(new d(this) {
                    final /* synthetic */ GearProtectView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(a aVar) {
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

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
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_gear_protect_tip, new OnClickListener(this) {
                final /* synthetic */ GearProtectView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    int i2;
                    DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                    String str = GearProtectView.a;
                    if (z) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    dataFlycSetParams.a(str, Integer.valueOf(i2)).start(new d(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(a aVar) {
                            this.a.b.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.b.a();
                                }
                            });
                        }
                    });
                }
            }, new OnClickListener(this) {
                final /* synthetic */ GearProtectView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a();
                        }
                    });
                }
            }).setCancelable(false);
        }
    }

    private void a() {
        boolean z = true;
        if ((dji.pilot.publics.e.a.h(null) || dji.pilot.publics.e.a.g()) && this.e >= 5) {
            setVisibility(0);
            if (dji.midware.data.manager.P3.d.read(a).value.intValue() != 1) {
                z = false;
            }
            this.eS_.setChecked(z);
            return;
        }
        setVisibility(8);
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (flycVersion != this.e) {
            this.e = flycVersion;
            a();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        onEventMainThread(DataOsdGetPushCommon.getInstance());
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }
}
