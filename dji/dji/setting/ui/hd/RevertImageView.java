package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataDm368GetParams;
import dji.midware.data.model.P3.DataDm368SetParams;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class RevertImageView extends ItemViewSwitch {
    private boolean a = false;
    private DataCommonGetVersion e = new DataCommonGetVersion();

    public RevertImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e.setDeviceType(DeviceType.DM368);
        this.e.setDeviceModel(1);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.eS_.setChecked(this.a);
            c.a().a(this);
            onEventMainThread(DataGimbalGetPushType.getInstance());
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.b) {
            onEventMainThread(DataGimbalGetPushType.getInstance());
        }
    }

    public void onEventMainThread(DataGimbalGetPushType dataGimbalGetPushType) {
        DJIGimbalType type = dataGimbalGetPushType.getType();
        if (dataGimbalGetPushType.isGetted() && DJIGimbalType.Ronin == type) {
            setVisibility(0);
            a();
        } else {
            setVisibility(8);
        }
        if (getVisibility() == 0) {
            this.e.start(new d(this) {
                final /* synthetic */ RevertImageView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("pm820", "revert image get firmver: " + this.a.e.getFirmVer("."));
                    if (this.a.e.getFirmVer(".").compareTo("01.00.00.23") < 0) {
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.setVisibility(8);
                            }
                        });
                    }
                }

                public void onFailure(a aVar) {
                }
            });
        }
    }

    private void a() {
        final DataDm368GetParams dataDm368GetParams = new DataDm368GetParams();
        dataDm368GetParams.set(DM368CmdId.f).start(new d(this) {
            final /* synthetic */ RevertImageView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        boolean z = true;
                        RevertImageView revertImageView = this.a.b;
                        if (dataDm368GetParams.getResult() != 1) {
                            z = false;
                        }
                        revertImageView.a = z;
                        this.a.b.eS_.setChecked(this.a.b.a);
                    }
                });
            }

            public void onFailure(a aVar) {
            }
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        int i = 0;
        if (z != this.a) {
            this.eS_.setEnabled(false);
            DataDm368SetParams dataDm368SetParams = new DataDm368SetParams();
            DM368CmdId dM368CmdId = DM368CmdId.f;
            if (z) {
                i = 1;
            }
            dataDm368SetParams.a(dM368CmdId, i);
            dataDm368SetParams.start(new d(this) {
                final /* synthetic */ RevertImageView b;

                public void onSuccess(Object obj) {
                    this.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.eS_.setEnabled(true);
                            this.a.b.a = z;
                        }
                    });
                }

                public void onFailure(a aVar) {
                    this.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.eS_.setEnabled(true);
                            this.a.b.eS_.setChecked(this.a.b.a);
                        }
                    });
                }
            });
        }
    }
}
