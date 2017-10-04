package dji.phone.set.list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.common.view.set.listview.DJIBaseListView;
import dji.device.common.view.set.listview.LonganSencondarySetListView;
import dji.device.common.view.set.view.DJIStageViewCompat;
import dji.device.common.view.set.view.b;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataGimbalGetHandleParams;
import dji.midware.data.model.P3.DataGimbalSetHandleParams;
import dji.midware.e.d;
import dji.phone.bluetooth.c;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIListView;
import java.util.Iterator;

public class DJILPGimbalSetListView extends DJIBaseListView {
    public static final String o = ".";
    private static final String s = DJILPGimbalSetListView.class.getSimpleName();
    private static final int t = 0;
    private static final int u = 1;
    private static final int v = 2;
    private static final int w = 3;
    private static final int[] x = new int[]{R.layout.longan_camera_newfn_base_listview, R.layout.longan_shotcuts_litsitem_switch, R.layout.longan_shotcuts_litsitem_switch};
    private boolean A = false;
    private LocalBroadcastManager B;
    BroadcastReceiver p = new BroadcastReceiver(this) {
        final /* synthetic */ DJILPGimbalSetListView a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(DJILPGimbalSetListView.s, "onReceive: " + intent.getAction());
            if (intent.getAction().equals("GIMBAL_RESET")) {
                this.a.l();
                this.a.k();
            }
        }
    };
    d q = new d(this) {
        final /* synthetic */ DJILPGimbalSetListView a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            Object firmVer = ((DataCommonGetVersion) obj).getFirmVer(".");
            Log.d(DJILPGimbalSetListView.s, "getCameraVersion onSuccess: version = " + firmVer);
            if (TextUtils.isEmpty(firmVer)) {
                this.a.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.getGimbalVersion();
                    }
                }, 500);
                return;
            }
            this.a.y = Long.parseLong(firmVer.replace(".", ""));
            Log.d(DJILPGimbalSetListView.s, "onSuccess: mGetVersion = " + this.a.y);
            this.a.j();
        }

        public void onFailure(a aVar) {
            Log.d(DJILPGimbalSetListView.s, "getCameraVersion onFailure: ");
            this.a.postDelayed(new Runnable(this) {
                final /* synthetic */ AnonymousClass2 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.getGimbalVersion();
                }
            }, 500);
        }
    };
    private long y = 0;
    private boolean z = false;

    public DJILPGimbalSetListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = LocalBroadcastManager.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("GIMBAL_RESET");
        this.B.registerReceiver(this.p, intentFilter);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.f.a.a(this);
    }

    protected void a() {
        this.a = b.f;
        this.b = x;
        this.c = b.i;
    }

    protected void e() {
        this.f = (DJIStageViewCompat) findViewById(R.id.longan_shotcuts_gimbal_va);
        this.g = (DJIListView) findViewById(R.id.longan_shotcuts_gimbal_lv);
        this.g.setAdapter(this.e);
        this.g.setOnItemClickListener(this.k);
        if (c.getInstance().b()) {
            getGimbalVersion();
        }
    }

    private void getGimbalVersion() {
        Log.d(s, "getGimbalVersion: ");
        dji.device.common.b.getInstance().a(1, this.q);
    }

    private void j() {
        Log.d(s, "hideOrshowPhoneSensorDisableUI: mGimbalVersion = " + this.y);
        if (this.y >= 1300138) {
            b(2).i = true;
        } else {
            b(2).i = false;
        }
        m();
    }

    protected void f() {
    }

    protected dji.device.common.view.set.b.a a(int i) {
        dji.device.common.view.set.b.a aVar = new dji.device.common.view.set.b.a();
        aVar.k = this.a[i];
        aVar.n = this.b[i];
        aVar.o = this.a[i];
        if (this.c != null) {
            aVar.l = this.c[i];
        }
        if (aVar.n == R.layout.longan_shotcuts_litsitem_switch) {
            aVar.f = 1;
        } else if (aVar.n == R.layout.longan_camera_newfn_image_btn) {
            aVar.f = 3;
        } else {
            aVar.f = 0;
        }
        if (i == 2) {
            aVar.i = false;
            aVar.h = true;
        }
        return aVar;
    }

    protected void h() {
        this.k = new OnItemClickListener(this) {
            final /* synthetic */ DJILPGimbalSetListView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) this.a.d.get(i);
                ViewParent parent = this.a.getParent();
                Log.d(DJILPGimbalSetListView.s, "DJILPGimbalSetListView onItemClick: vp = " + parent + "model = " + aVar + "position = " + i);
                if (!(parent instanceof DJIStageViewCompat)) {
                    return;
                }
                if (i == 0) {
                    ((LonganSencondarySetListView) ((DJIStageViewCompat) parent).createStageView(aVar.n, aVar.o, true, this.a.getLayoutParams().width, this.a.getLayoutParams().height)).updateData(1001);
                } else if (1 == i) {
                    if (!this.a.b(1).j) {
                        return;
                    }
                    if (this.a.z) {
                        this.a.setPitchLock(false);
                    } else {
                        this.a.setPitchLock(true);
                    }
                } else if (2 == i) {
                    if (!this.a.b(2).j) {
                        return;
                    }
                    if (this.a.A) {
                        this.a.setPhoneSensorDisable(false);
                    } else {
                        this.a.setPhoneSensorDisable(true);
                    }
                } else if (3 != i) {
                } else {
                    if (dji.phone.c.a.c().a() == 1) {
                        dji.thirdparty.a.c.a().e(dji.phone.set.gimbalplan.a.b.SHOW);
                    } else {
                        dji.phone.k.b.showShort(R.string.gimbal_plan_notification_not_use);
                    }
                }
            }
        };
        l();
        k();
    }

    public void setPhoneSensorDisable(boolean z) {
        int i = 1;
        DJILogHelper.getInstance().LOGD(s, "setPhoneSensorDisable value= " + z, false, true);
        DataGimbalSetHandleParams dataGimbalSetHandleParams = new DataGimbalSetHandleParams();
        if (!z) {
            i = 0;
        }
        dataGimbalSetHandleParams.h(i).start(new d(this) {
            final /* synthetic */ DJILPGimbalSetListView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(DJILPGimbalSetListView.s, "onSuccess", false, true);
                this.a.k();
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD(DJILPGimbalSetListView.s, "onFailure", false, true);
                this.a.k();
            }
        });
        m();
    }

    private void k() {
        Log.d(s, "requestGetPhoneSensorDisable: ");
        final DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new d(this) {
            final /* synthetic */ DJILPGimbalSetListView b;

            public void onSuccess(Object obj) {
                dji.device.common.view.set.b.a a = this.b.b(2);
                this.b.A = dataGimbalGetHandleParams.getCellphoneSensorDisable();
                DJILogHelper.getInstance().LOGD(DJILPGimbalSetListView.s, "onSuccess: IsPhoneSensorDisable = " + this.b.A, false, true);
                if (this.b.A) {
                    if (a.h) {
                        a.h = false;
                        this.b.m();
                    }
                } else if (!a.h) {
                    a.h = true;
                    this.b.m();
                }
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD(DJILPGimbalSetListView.s, "onFailure: " + aVar.name(), false, true);
                if (c.getInstance().b()) {
                    this.b.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.k();
                        }
                    }, 2000);
                }
            }
        });
    }

    protected void setPitchLock(final boolean z) {
        int i;
        DataGimbalSetHandleParams dataGimbalSetHandleParams = new DataGimbalSetHandleParams();
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        dataGimbalSetHandleParams.e(i).start(new d(this) {
            final /* synthetic */ DJILPGimbalSetListView b;

            public void onSuccess(Object obj) {
                if (z) {
                    dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.q, "1", false);
                } else {
                    dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.q, "2", false);
                }
                dji.publics.b.b.a.getInstance().e("createtime", System.currentTimeMillis() + "", false).e("device_type", i.getInstance().c()._name(), false).e("pro_id", dji.publics.b.b.a.a, false).e("device_ver", dji.device.common.b.getInstance().b(), true);
                this.b.l();
            }

            public void onFailure(a aVar) {
                this.b.l();
            }
        });
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.B.unregisterReceiver(this.p);
        dji.f.a.b(this);
    }

    private void l() {
        final DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new d(this) {
            final /* synthetic */ DJILPGimbalSetListView b;

            public void onSuccess(Object obj) {
                dji.device.common.view.set.b.a a = this.b.b(1);
                Log.d(DJILPGimbalSetListView.s, "onSuccess: pitchLockModel = " + a);
                this.b.z = dataGimbalGetHandleParams.getPitchFree() == 1;
                Log.d(DJILPGimbalSetListView.s, "onSuccess: mIsPitchDirectionLocked = " + this.b.z);
                if (this.b.z) {
                    if (!a.h) {
                        a.h = true;
                        this.b.m();
                    }
                } else if (a.h) {
                    a.h = false;
                    this.b.m();
                }
            }

            public void onFailure(a aVar) {
                Log.d(DJILPGimbalSetListView.s, "onFailure: " + aVar.name());
            }
        });
    }

    private void m() {
        post(new Runnable(this) {
            final /* synthetic */ DJILPGimbalSetListView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e.notifyDataSetChanged();
            }
        });
    }

    public void onEventMainThread(p pVar) {
        Log.d(s, "onEventMainThread: event = " + pVar.name());
        if (pVar == p.b) {
            l();
            k();
            if (this.y == 0) {
                getGimbalVersion();
            }
        }
    }

    private dji.device.common.view.set.b.a b(int i) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) it.next();
            if (aVar.k == b.f[i]) {
                return aVar;
            }
        }
        return null;
    }
}
