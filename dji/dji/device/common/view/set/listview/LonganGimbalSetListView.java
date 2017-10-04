package dji.device.common.view.set.listview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.DJIUIEventManagerLongan.j;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.set.view.DJIStageViewCompat;
import dji.device.common.view.set.view.DJIStageViewCompat.e;
import dji.device.common.view.set.view.LonganGimbalNewShotcutsView;
import dji.device.common.view.set.view.b;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetHandleParams;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalSetHandleParams;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIListView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class LonganGimbalSetListView extends DJIStageViewCompat implements dji.device.common.view.set.view.DJIStageViewCompat.a {
    private static final int n = 0;
    private static final int o = 1;
    private static final int p = 2;
    private static final int q = 3;
    private static final int s = 4;
    private static final int t = 5;
    private static final int u = 6;
    private static final int[] v = new int[]{R.layout.longan_camera_newfn_base_listview, R.layout.longan_shotcuts_litsitem_switch, R.layout.longan_camera_newfn_image_btn, R.layout.longan_camera_newfn_image_btn, R.layout.longan_camera_newfn_image_btn, R.layout.longan_gimbal_angle_set_ly, R.layout.longan_camera_newfn_image_btn};
    private static final int w = 1;
    protected final ArrayList<dji.device.common.view.set.b.a> a = new ArrayList(b.c.length);
    DJIStageViewCompat b;
    DJIListView c;
    protected dji.device.common.view.set.a.b d = null;
    protected Context e = null;
    protected e f = null;
    protected OnClickListener g = null;
    protected OnItemClickListener h = null;
    protected dji.device.common.view.set.view.LonganCameraShotcutsView.a i = null;
    protected DJICameraDataManagerCompat j = DJICameraDataManagerCompat.getInstance();
    protected CameraType k = CameraType.OTHER;
    long l;
    long m;
    private final a x;
    private boolean y = false;
    private boolean z = false;

    private static class a extends Handler {
        private final WeakReference<LonganGimbalSetListView> a;

        public a(LonganGimbalSetListView longanGimbalSetListView) {
            this.a = new WeakReference(longanGimbalSetListView);
        }

        public void handleMessage(Message message) {
            LonganGimbalSetListView longanGimbalSetListView = (LonganGimbalSetListView) this.a.get();
            if (message.what == 1) {
                longanGimbalSetListView.d.notifyDataSetChanged();
            }
        }
    }

    public LonganGimbalSetListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
        this.x = new a(this);
        a();
        d();
    }

    protected void a() {
        int length = b.c.length;
        for (int i = 0; i < length; i++) {
            this.a.add(a(i));
        }
    }

    private dji.device.common.view.set.b.a a(int i) {
        dji.device.common.view.set.b.a aVar = new dji.device.common.view.set.b.a();
        aVar.k = b.c[i];
        aVar.l = b.h[i];
        aVar.n = v[i];
        aVar.o = b.c[i];
        if (aVar.n == R.layout.longan_shotcuts_litsitem_switch) {
            aVar.f = 1;
        } else if (aVar.n == R.layout.longan_camera_newfn_image_btn) {
            aVar.f = 3;
        } else {
            aVar.f = 0;
        }
        if (i == 1 || i == 0) {
            aVar.i = false;
        }
        return aVar;
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            post(new Runnable(this) {
                final /* synthetic */ LonganGimbalSetListView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.e();
                }
            });
        }
    }

    protected void b() {
    }

    protected void c() {
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.b = (DJIStageViewCompat) findViewById(R.id.longan_shotcuts_gimbal_va);
            this.c = (DJIListView) findViewById(R.id.longan_shotcuts_gimbal_lv);
            this.c.setAdapter(this.d);
            this.c.setOnItemClickListener(this.h);
            if (ServiceManager.getInstance().isConnected()) {
                onEventBackgroundThread(DataGimbalGetPushParams.getInstance());
                f();
                g();
            }
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void d() {
        if (!isInEditMode()) {
            this.f = new e(this) {
                final /* synthetic */ LonganGimbalSetListView a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                }

                public void a(int i, int i2, int i3) {
                }
            };
            this.h = new OnItemClickListener(this) {
                final /* synthetic */ LonganGimbalSetListView a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    DJILogHelper.getInstance().LOGD("", "DJIMethod : onItemClick (197)" + System.currentTimeMillis(), false, true);
                    dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) this.a.a.get(i);
                    ViewParent parent = this.a.getParent();
                    if (!(parent instanceof DJIStageViewCompat)) {
                        return;
                    }
                    if (2 == i) {
                        DataSpecialControl.getInstance().resetGimbal().start(20);
                        try {
                            Class cls = Class.forName("dji.pilot.reflect.FpvReflect");
                            cls.getMethod("flurryOsmoRecenter", new Class[0]).invoke(cls, new Object[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (3 == i) {
                        DataSpecialControl.getInstance().selfieGimbal().start(20);
                    } else if (4 == i) {
                        if (this.a.y) {
                            DataSpecialControl.getInstance().setGimbalMode(MODE.YawFollow).start(20);
                        } else {
                            DataSpecialControl.getInstance().setGimbalMode(MODE.YawNoFollow).start(20);
                        }
                    } else if (1 == i) {
                        if (!this.a.b(1).j) {
                            return;
                        }
                        if (this.a.z) {
                            this.a.setPitchLock(false);
                        } else {
                            this.a.setPitchLock(true);
                        }
                    } else if (6 == i) {
                        dji.device.common.view.set.b.a a = this.a.b(6);
                        if (a.j) {
                            c.a().e(j.STOP_SENSOR_CONTROL);
                            a.j = false;
                            a.l = R.drawable.longan_gimbal_sensor_off;
                        } else {
                            c.a().e(j.START_SENSOR_CONTROL);
                            a.j = true;
                            a.l = R.drawable.longan_gimbal_sensor_on;
                        }
                        this.a.h();
                    } else {
                        dji.device.common.view.set.view.DJIStageViewCompat.a createStageView = ((DJIStageViewCompat) parent).createStageView(aVar.n, aVar.o, true, this.a.getLayoutParams().width, this.a.getLayoutParams().height);
                        if (i == 0) {
                            ((LonganSencondarySetListView) createStageView).updateData(1001);
                        }
                    }
                }
            };
            this.d = new dji.device.common.view.set.a.b(getContext(), this.a);
        }
    }

    private void e() {
        boolean z;
        boolean z2 = true;
        boolean z3 = dji.device.common.b.getInstance().c(1) >= dji.device.common.b.e;
        if (dji.device.common.b.getInstance().c(1) >= dji.device.common.b.g) {
            z = true;
        } else {
            z = false;
        }
        dji.device.common.view.set.b.a b = b(0);
        dji.device.common.view.set.b.a b2 = b(1);
        if (b.i != z3) {
            b.i = z3;
            DJILogHelper.getInstance().LOGD("updateItemVisiable", "1", false, true);
            z3 = true;
        } else {
            z3 = false;
        }
        if (b2.i != z) {
            b2.i = z;
            DJILogHelper.getInstance().LOGD("updateItemVisiable", "2s", false, true);
        } else {
            z2 = z3;
        }
        if (z2) {
            this.d.notifyDataSetChanged();
        }
    }

    protected void setPitchLock(boolean z) {
        int i = 1;
        DJILogHelper.getInstance().LOGD("", "DJIMethod : setPitchLock (291)" + System.currentTimeMillis(), false, true);
        DataGimbalSetHandleParams dataGimbalSetHandleParams = new DataGimbalSetHandleParams();
        if (!z) {
            i = 0;
        }
        dataGimbalSetHandleParams.e(i).start(new d(this) {
            final /* synthetic */ LonganGimbalSetListView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "DJIMethod : onSuccess (296)" + System.currentTimeMillis(), false, true);
                this.a.f();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "DJIMethod : onFailure (303)" + aVar + "time:" + System.currentTimeMillis(), false, true);
                this.a.f();
            }
        });
    }

    private void f() {
        final DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new d(this) {
            final /* synthetic */ LonganGimbalSetListView b;

            public void onSuccess(Object obj) {
                dji.device.common.view.set.b.a a = this.b.b(1);
                this.b.z = dataGimbalGetHandleParams.getPitchFree() == 1;
                if (this.b.z) {
                    if (!a.h) {
                        a.h = true;
                        DJILogHelper.getInstance().LOGD("", "DJIMethod : onSuccess (318)" + System.currentTimeMillis(), false, true);
                        this.b.h();
                    }
                } else if (a.h) {
                    a.h = false;
                    this.b.h();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private dji.device.common.view.set.b.a b(int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) it.next();
            if (aVar.k == b.c[i]) {
                return aVar;
            }
        }
        return null;
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        boolean z = false;
        if (LonganGimbalNewShotcutsView.a) {
            int i;
            boolean z2;
            if (dataGimbalGetPushParams.getMode() == MODE.YawNoFollow) {
                if (!this.y) {
                    this.y = true;
                    i = R.drawable.longan_gimbal_pantiltlock_on;
                    z2 = true;
                }
                z2 = false;
                i = 0;
            } else {
                if (dataGimbalGetPushParams.getMode() == MODE.YawFollow && this.y) {
                    this.y = false;
                    i = R.drawable.longan_gimbal_pantiltlock_off;
                    z2 = true;
                }
                z2 = false;
                i = 0;
            }
            if (z2) {
                b(4).l = i;
                dji.device.common.view.set.b.a b = b(1);
                if (dataGimbalGetPushParams.getMode() != MODE.YawNoFollow) {
                    z = true;
                }
                b.j = z;
                h();
            }
        }
    }

    public void onEventBackgroundThread(dji.device.common.view.set.listview.LonganSencondarySetListView.a aVar) {
        if (aVar.a == 1001) {
            g();
        }
    }

    private void g() {
        final DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new d(this) {
            final /* synthetic */ LonganGimbalSetListView b;

            public void onSuccess(Object obj) {
                int profile = dataGimbalGetHandleParams.getProfile();
                if (((dji.device.common.view.set.b.a) this.b.a.get(0)).g != profile) {
                    ((dji.device.common.view.set.b.a) this.b.a.get(0)).g = profile;
                    ((dji.device.common.view.set.b.a) this.b.a.get(0)).l = dji.device.common.a.d.b(this.b.e, profile);
                    this.b.h();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void h() {
        post(new Runnable(this) {
            final /* synthetic */ LonganGimbalSetListView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d.notifyDataSetChanged();
            }
        });
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case SHOW_SHOTCUTS_CAMERA_LY:
                onEventBackgroundThread(DataGimbalGetPushParams.getInstance());
                return;
            default:
                return;
        }
    }
}
