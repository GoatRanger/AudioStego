package dji.device.common.view.set.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.view.set.a.b;
import dji.device.common.view.set.view.DJIStageViewCompat;
import dji.device.common.view.set.view.DJIStageViewCompat.a;
import dji.device.common.view.set.view.DJIStageViewCompat.e;
import dji.device.common.view.set.view.LonganCameraShotcutsView;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIListView;
import java.util.ArrayList;

public abstract class DJIBaseListView extends DJIStageViewCompat implements a {
    private static final String o = "DJIBaseListView";
    private static final int p = 1;
    protected int[] a;
    protected int[] b;
    protected int[] c;
    protected ArrayList<dji.device.common.view.set.b.a> d;
    protected b e = null;
    protected DJIStageViewCompat f;
    protected DJIListView g;
    protected Context h = null;
    protected e i = null;
    protected OnClickListener j = null;
    protected OnItemClickListener k = null;
    protected LonganCameraShotcutsView.a l = null;
    protected DJICameraDataManagerCompat m = DJICameraDataManagerCompat.getInstance();
    protected CameraType n = CameraType.OTHER;

    protected abstract void a();

    protected abstract void f();

    protected abstract void h();

    public DJIBaseListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = context;
        a();
        this.d = new ArrayList(this.a.length);
        b();
        g();
        h();
    }

    protected void b() {
        int length = this.a.length;
        for (int i = 0; i < length; i++) {
            this.d.add(a(i));
        }
        this.e = new b(getContext(), this.d);
    }

    public void showView() {
        if (getVisibility() != 0) {
            setVisibility(0);
            c();
            if (this.l != null) {
                this.l.a(true);
            }
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            setVisibility(8);
            d();
            if (this.l != null) {
                this.l.a(false);
            }
        }
    }

    protected void c() {
    }

    protected void d() {
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            e();
            f();
        }
    }

    protected void e() {
        this.f = (DJIStageViewCompat) findViewById(R.id.longan_shotcuts_camera_va);
        this.g = (DJIListView) findViewById(R.id.longan_shotcuts_camera_lv);
        this.g.setAdapter(this.e);
        this.g.setOnItemClickListener(this.k);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected void g() {
        this.i = new e(this) {
            final /* synthetic */ DJIBaseListView a;

            {
                this.a = r1;
            }

            public void a(int i) {
            }

            public void a(int i, int i2, int i3) {
            }
        };
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
        return aVar;
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
}
