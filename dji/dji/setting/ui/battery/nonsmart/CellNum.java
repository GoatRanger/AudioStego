package dji.setting.ui.battery.nonsmart;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.manager.P3.d;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.battery.a.a;
import dji.setting.ui.rc.RcMasterSlaveView;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;

public class CellNum extends ItemViewSpinner {
    public static final String a = "g_config.voltage.battery_cell_0";
    private boolean b = false;
    private int c = 3;

    public CellNum(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        int i = 0;
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            int i2;
            this.b = true;
            String[] strArr = new String[10];
            for (i2 = 3; i2 <= 12; i2++) {
                strArr[i2 - 3] = String.format("%dS", new Object[]{Integer.valueOf(i2)});
            }
            i2 = d.read("g_config.voltage.battery_cell_0").value.intValue() - 3;
            if (i2 >= 0) {
                if (i2 > 9) {
                    i = 9;
                } else {
                    i = i2;
                }
            }
            this.f.setData(strArr, i, (b) this);
            a();
            b();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        this.b = false;
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onItemClick(final int i) {
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(new String[]{"g_config.voltage.battery_cell_0"});
        dataFlycSetParams.a(new Number[]{Integer.valueOf(i + 3)});
        dataFlycSetParams.start(new dji.midware.e.d(this) {
            final /* synthetic */ CellNum b;

            public void onSuccess(Object obj) {
                this.b.c = i + 3;
                a.getInstance().e(this.b.c);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.f.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.f.setIndex(this.a.b.c - 3);
                    }
                });
            }
        });
    }

    private void a() {
        if (this.b) {
            new DataFlycGetParams().setInfos(new String[]{"g_config.voltage.battery_cell_0"}).start(new dji.midware.e.d(this) {
                final /* synthetic */ CellNum a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.f.setIndex(d.read("g_config.voltage.battery_cell_0").value.intValue() - 3);
                            a.getInstance().e(d.read("g_config.voltage.battery_cell_0").value.intValue());
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.a();
                }
            });
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        b();
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        b();
    }

    private void b() {
        boolean z = (dji.pilot.c.d.b == MODE.b || DataOsdGetPushCommon.getInstance().isMotorUp()) ? false : true;
        this.f.setEnabled(z);
    }
}
