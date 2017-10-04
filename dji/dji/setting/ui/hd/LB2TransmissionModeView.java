package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetPushStatus;
import dji.midware.data.model.P3.DataDm385GetParams;
import dji.midware.data.model.P3.DataDm385SetParams;
import dji.midware.data.model.P3.DataDm385SetParams.DM385CmdId;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.usb.P3.a.b;
import dji.pilot.c.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.publics.a;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class LB2TransmissionModeView extends ItemViewRadio {
    public LB2TransmissionModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.g.setText(R.string.setting_ui_hd_transmission_low);
            this.h.setText(R.string.setting_ui_hd_transmission_high);
            a();
            b();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2TransmissionModeView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        }, 700);
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        a();
    }

    public void onEventMainThread(DataDm368GetPushStatus dataDm368GetPushStatus) {
        a();
    }

    private void a() {
        int e = dji.midware.usb.P3.a.getInstance().e();
        if (dji.midware.usb.P3.a.getInstance().d() == b.c) {
            e = dji.midware.usb.P3.a.getInstance().f();
        }
        if (a.a()) {
            setVisibility(0);
            if (e != 10 || d.b == MODE.b) {
                setVisibility(8);
                return;
            }
            setVisibility(0);
            if (DataDm385GetParams.getInstance().getTransmissionMode() == 0) {
                this.h.setChecked(true);
                return;
            } else {
                this.g.setChecked(true);
                return;
            }
        }
        setVisibility(8);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2;
        if (i == this.h.getId()) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (DataDm385GetParams.getInstance().getTransmissionMode() != i2) {
            if (i2 == 0) {
                e.a("FPV_ImageTransmissionSettings_OutputDevice_Button_LowDelay");
            } else {
                e.a("FPV_ImageTransmissionSettings_OutputDevice_Button_HighQuality");
            }
            new DataDm385SetParams().a(DM385CmdId.a, i2).start(new dji.midware.e.d(this) {
                final /* synthetic */ LB2TransmissionModeView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("", "mode Success", false, true);
                    this.a.b();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a();
                            Toast.makeText(this.a.a.getContext(), R.string.setting_ui_hd_transmission_fails, 0).show();
                        }
                    });
                }
            });
        }
    }

    private void b() {
        DataDm385GetParams.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ LB2TransmissionModeView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "DataDm385GetParams=" + aVar, false, true);
            }
        });
    }
}
