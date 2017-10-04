package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetReadFlyDataMode;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.thirdparty.a.c;

public class SdModeView extends ItemViewButtonBig {
    public static boolean b = false;
    public a a;
    private int d = Integer.MIN_VALUE;

    public enum a {
        SUCCESS,
        TIMEOUT,
        FAILED
    }

    public SdModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    public void onClick(View view) {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_switch_sd_mode_error_motor_up);
        } else {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_switch_sd_mode_confirm_msg, new OnClickListener(this) {
                final /* synthetic */ SdModeView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.c.setText(R.string.setting_ui_flyc_switching_sd_mode);
                    SdModeView.b = true;
                    DataFlycSetReadFlyDataMode.getInstance().start(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a.a = a.SUCCESS;
                                    c.a().e(this.a.a.a);
                                }
                            });
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            if (aVar == dji.midware.data.config.P3.a.E) {
                                this.a.a.runOnUiThread(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        dji.setting.ui.widget.a.a(this.a.a.a.getContext(), R.string.setting_ui_flyc_switch_sd_mode_error);
                                        this.a.a.a.setBlackStatus(false);
                                    }
                                });
                            } else if (aVar == dji.midware.data.config.P3.a.a) {
                                this.a.a.a = a.TIMEOUT;
                                c.a().e(this.a.a);
                            } else {
                                this.a.a.a = a.FAILED;
                                c.a().e(this.a.a);
                            }
                        }
                    });
                    dialogInterface.dismiss();
                }
            });
        }
    }

    public void setBlackStatus(boolean z) {
        this.c.setText(R.string.setting_ui_flyc_switch_sd_mode);
        b = false;
    }

    private void a() {
        ProductType c = i.getInstance().c();
        if (c == ProductType.Tomato || c == ProductType.Pomato || this.d < 4) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (this.d != flycVersion) {
            this.d = flycVersion;
            a();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(DataOsdGetPushCommon.getInstance());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
