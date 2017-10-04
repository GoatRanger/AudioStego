package dji.setting.ui.hd;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.Switch;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataDm368SetParams;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.e.d;
import dji.midware.usb.P3.a.b;
import dji.setting.ui.rc.RcMasterSlaveView;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class LB2OpenEXTView extends ItemViewSwitch implements OnClickListener {
    Handler a;
    private boolean e = true;

    public LB2OpenEXTView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.eS_.setOnClickListener(this);
        this.a = new Handler();
    }

    public void onClick(View view) {
        if (view instanceof Switch) {
            final boolean isChecked = ((Switch) view).isChecked();
            if (isChecked) {
                new DataDm368SetParams().a(DM368CmdId.d, 0).start(new d(this) {
                    final /* synthetic */ LB2OpenEXTView b;

                    public void onSuccess(Object obj) {
                        this.b.e = isChecked;
                        DJILogHelper.getInstance().LOGD("pm820", "****into set single");
                    }

                    public void onFailure(a aVar) {
                        this.b.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.eS_.setChecked(this.a.b.e);
                            }
                        });
                    }
                });
            } else {
                DataOsdSetConfig.getInstance().f(10).start(new d(this) {
                    final /* synthetic */ LB2OpenEXTView b;

                    public void onSuccess(Object obj) {
                        this.b.setLBVideoResource(isChecked);
                    }

                    public void onFailure(a aVar) {
                        this.b.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.eS_.setChecked(this.a.b.e);
                            }
                        });
                        DJILogHelper.getInstance().LOGD("", "bandwidth set fail: " + aVar.name(), false, true);
                    }
                });
            }
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
    }

    private void setLBVideoResource(final boolean z) {
        DataOsdSetConfig.getInstance().g(1).start(new d(this) {
            final /* synthetic */ LB2OpenEXTView b;

            public void onSuccess(Object obj) {
                a.f();
                this.b.a(z);
                DJILogHelper.getInstance().LOGD("", "set video source success ", false, true);
            }

            public void onFailure(a aVar) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.eS_.setChecked(this.a.b.e);
                    }
                });
                DJILogHelper.getInstance().LOGD("", "set video source failure", false, true);
            }
        });
    }

    private void a(final boolean z) {
        new DataDm368SetParams().a(DM368CmdId.d, 1).start(new d(this) {
            final /* synthetic */ LB2OpenEXTView b;

            public void onSuccess(Object obj) {
                this.b.e = z;
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("", "switch2Dual failure", false, true);
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.eS_.setChecked(this.a.b.e);
                    }
                });
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        a();
    }

    private void a() {
        if (!a.c() || dji.pilot.c.d.b == MODE.b) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (dji.midware.usb.P3.a.getInstance().d() == b.b) {
            this.eS_.setChecked(true);
        } else {
            this.eS_.setChecked(false);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        dji.publics.a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2OpenEXTView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }, 700);
    }

    public void onEventMainThread(b bVar) {
        if (bVar == b.c) {
            this.e = false;
        } else {
            this.e = true;
        }
        this.eS_.setChecked(this.e);
    }
}
