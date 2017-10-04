package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetSDRImageTransmMode;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode.SDRImageTransmMode;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class ImageTransmModeView extends ItemViewRadio {
    private DataOsdGetSDRImageTransmMode a = DataOsdGetSDRImageTransmMode.getInstance();
    private DataOsdSetSDRImageTransmMode b = DataOsdSetSDRImageTransmMode.getInstance();
    private SDRImageTransmMode c = SDRImageTransmMode.a;
    private String i = getClass().getName();

    public ImageTransmModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g.setText(R.string.setting_ui_hd_image_transm_smooth);
        this.h.setText(R.string.setting_ui_hd_image_transm_hd);
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

    private void a() {
        if (a.d()) {
            setVisibility(0);
            this.a.start(new d(this) {
                final /* synthetic */ ImageTransmModeView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.c = this.a.a.getMode();
                    DJILogHelper.getInstance().LOGD(this.a.i, "Get ImageTransmissionMode onSuccess Mode: " + this.a.c);
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.a.c == SDRImageTransmMode.a) {
                                this.a.a.g.setChecked(true);
                            } else {
                                this.a.a.h.setChecked(true);
                            }
                        }
                    });
                }

                public void onFailure(a aVar) {
                    DJILogHelper.getInstance().LOGD(this.a.i, "Get ImageTransmissionMode onFailure");
                }
            });
            return;
        }
        setVisibility(8);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.g.getId()) {
            setTransmMode(SDRImageTransmMode.a);
        } else {
            setTransmMode(SDRImageTransmMode.b);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    private void setTransmMode(final SDRImageTransmMode sDRImageTransmMode) {
        this.b.a(sDRImageTransmMode).start(new d(this) {
            final /* synthetic */ ImageTransmModeView b;

            public void onSuccess(Object obj) {
                this.b.c = sDRImageTransmMode;
                DJILogHelper.getInstance().LOGD(this.b.i, "Set ImageTransmissionMode onSuccess");
            }

            public void onFailure(a aVar) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.b.c == SDRImageTransmMode.a) {
                            this.a.b.g.setChecked(true);
                        } else {
                            this.a.b.h.setChecked(true);
                        }
                    }
                });
                DJILogHelper.getInstance().LOGD(this.b.i, "Set ImageTransmissionMode onFailure");
            }
        });
    }
}
