package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataGimbalGetHandleParams;
import dji.midware.data.model.P3.DataGimbalSetHandleParams;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.g;
import dji.pilot.set.view.base.SetGroupButtonView;

public class StickControlSetterView extends SetGroupButtonView {
    public static final String a = ".";
    private static final long d = 1290087;
    private DataCommonGetVersion e;

    public StickControlSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = null;
        this.e = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        this.e.start(new d(this) {
            final /* synthetic */ StickControlSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                long parseLong = Long.parseLong(this.a.e.getFirmVer(".").replace(".", ""));
                if (parseLong != 0 && parseLong >= StickControlSetterView.d) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.setVisibility(0);
                        }
                    });
                }
            }

            public void onFailure(a aVar) {
            }
        }, 1000, 3);
    }

    protected int getTitleId() {
        return R.string.fpv_gensetting_gimbal_stick_control;
    }

    public void setValue(final int i) {
        a(i, new d(this) {
            final /* synthetic */ StickControlSetterView b;

            public void onSuccess(Object obj) {
                boolean z = true;
                DJILogHelper.getInstance().LOGD("tag", "setIsGimbalStickFree 1 success", false, true);
                Context context = this.b.getContext();
                String str = g.l;
                if (i != 1) {
                    z = false;
                }
                dji.pilot.set.a.a(context, str, z);
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.setSelect(i);
                    }
                });
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("tag", "setIsGimbalStickFree 1 failed" + aVar, false, true);
            }
        });
    }

    protected void a(int i, d dVar) {
        new DataGimbalSetHandleParams().a(i).start(dVar);
    }

    public void onEventMainThread(ResetGimbalSettingView resetGimbalSettingView) {
        a();
    }

    protected void a() {
        final DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new d(this) {
            final /* synthetic */ StickControlSetterView b;

            public void onSuccess(Object obj) {
                final int dualChannelEnable = dataGimbalGetHandleParams.getDualChannelEnable();
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void run() {
                        this.b.b.setSelect(dualChannelEnable);
                    }
                });
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("stick", "get  failed" + aVar, false, true);
                final boolean b = dji.pilot.set.a.b(this.b.getContext(), g.l);
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void run() {
                        int i = 1;
                        StickControlSetterView stickControlSetterView = this.b.b;
                        if (!b) {
                            i = 0;
                        }
                        stickControlSetterView.setSelect(i);
                    }
                });
            }
        });
    }

    protected int getLeftBtnStrId() {
        return R.string.set_gimbal_stick_control_hv;
    }

    protected int getRightBtnStrId() {
        return R.string.set_gimbal_stick_control_free;
    }
}
