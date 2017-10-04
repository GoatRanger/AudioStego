package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.a;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class FModeConfigView extends ItemViewRadio {
    private final String a = a.b;
    private volatile int b = 0;

    public FModeConfigView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g.setText(R.string.flyc_adv_setting_menu_f_mode_in);
        this.h.setText(R.string.flyc_adv_setting_menu_f_mode_m);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    private void a() {
        if ((i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3) && !dji.pilot.publics.e.a.h()) {
            setVisibility(0);
            new DataFlycGetParams().setInfos(new String[]{a.b}).start(new d(this) {
                final /* synthetic */ FModeConfigView a;

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
                            this.a.a.b = dji.midware.data.manager.P3.d.read(a.b).value.intValue();
                            DJILogHelper.getInstance().LOGD("pm820", "*f mode config get: " + this.a.a.b, false, true);
                            if (this.a.a.b == 2) {
                                this.a.a.g.setChecked(true);
                            } else {
                                this.a.a.h.setChecked(true);
                            }
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
            return;
        }
        setVisibility(8);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.g.getId()) {
            if (this.b != 2) {
                new DataFlycSetParams().a(a.b, Integer.valueOf(2)).start(new d(this) {
                    final /* synthetic */ FModeConfigView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.b = 2;
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.b == 2) {
                                    this.a.a.g.setChecked(true);
                                } else {
                                    this.a.a.h.setChecked(true);
                                }
                            }
                        });
                    }
                });
            }
        } else if (this.b != 0) {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_adv_fmode_tip, new OnClickListener(this) {
                final /* synthetic */ FModeConfigView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (dialogInterface != null) {
                        dialogInterface.dismiss();
                    }
                    new DataFlycSetParams().a(a.b, Integer.valueOf(0)).start(new d(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.b = 0;
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.a.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    if (this.a.a.a.b == 2) {
                                        this.a.a.a.g.setChecked(true);
                                    } else {
                                        this.a.a.a.h.setChecked(true);
                                    }
                                }
                            });
                        }
                    });
                }
            }, new OnClickListener(this) {
                final /* synthetic */ FModeConfigView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (dialogInterface != null) {
                        dialogInterface.dismiss();
                    }
                    if (this.a.b == 2) {
                        this.a.g.setChecked(true);
                    } else {
                        this.a.h.setChecked(true);
                    }
                }
            }).setCancelable(false);
        }
    }
}
