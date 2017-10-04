package dji.phone.set.list;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.common.view.set.listview.DJIBaseListView;
import dji.device.common.view.set.view.DJIStageViewCompat;
import dji.device.common.view.set.view.b;
import dji.f.a;
import dji.phone.d.c;
import dji.phone.d.d;
import dji.pilot.fpv.R;

public class DJILPCameraSetListView extends DJIBaseListView {
    private static final String o = "DJILPShortcutsListView";
    private static final int p = 0;
    private static final int q = 1;
    private static final int s = 2;
    private static final int t = 3;
    private static final int u = 4;
    private static final int[] v = new int[]{R.layout.lp_second_stage_view, R.layout.lp_second_stage_view, R.layout.lp_second_stage_view, R.layout.lp_second_stage_view};

    public DJILPCameraSetListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a.b(this);
    }

    protected void f() {
    }

    protected void h() {
        this.k = new OnItemClickListener(this) {
            final /* synthetic */ DJILPCameraSetListView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) this.a.d.get(i);
                DJIStageViewCompat.a createStageView = ((DJIStageViewCompat) this.a.getParent()).createStageView(aVar.n, aVar.o, true, this.a.getLayoutParams().width, this.a.getLayoutParams().height);
                if (i == 0) {
                    ((DJILPSecondStageView) createStageView).updateData(0);
                } else if (1 == i) {
                    ((DJILPSecondStageView) createStageView).updateData(1);
                } else if (3 == i) {
                    ((DJILPSecondStageView) createStageView).updateData(3);
                } else if (2 == i) {
                    ((DJILPSecondStageView) createStageView).updateData(2);
                }
            }
        };
    }

    protected void a() {
        this.a = b.b;
        this.b = v;
    }

    public void onEventMainThread(c cVar) {
        int i = 0;
        Log.d(o, "onEventMainThread: " + cVar.toString());
        int[] a = dji.phone.c.c.a(getResources(), getResources().obtainTypedArray(R.array.phone_camera_wb_res_array), dji.phone.c.c.a().a(null));
        int[] a2 = dji.phone.c.c.a().a(null);
        for (int i2 = 0; i2 < a2.length; i2++) {
            if (cVar.a.c() == a2[i2]) {
                ((dji.device.common.view.set.b.a) this.d.get(1)).l = a[i2];
                break;
            }
        }
        int[] intArray = getResources().getIntArray(R.array.phone_camera_flash_value_array);
        int[] iArr = b.e;
        while (i < intArray.length) {
            if (cVar.a.d() == intArray[i]) {
                ((dji.device.common.view.set.b.a) this.d.get(2)).l = iArr[i];
                break;
            }
            i++;
        }
        dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) this.d.get(3);
        aVar.l = b.g[dji.pilot.phonecamera.a.c.a().k()];
        this.e.notifyDataSetChanged();
    }

    public void onEventMainThread(c.b bVar) {
        int i = 0;
        c k = d.getInstance().k();
        Log.d(o, "onEventMainThread: " + k.toString());
        int[] a = dji.phone.c.c.a(getResources(), getResources().obtainTypedArray(R.array.phone_camera_wb_res_array), dji.phone.c.c.a().a(null));
        int[] a2 = dji.phone.c.c.a().a(null);
        for (int i2 = 0; i2 < a2.length; i2++) {
            if (k.a.c() == a2[i2]) {
                ((dji.device.common.view.set.b.a) this.d.get(1)).l = a[i2];
                break;
            }
        }
        int[] intArray = getResources().getIntArray(R.array.phone_camera_flash_value_array);
        int[] iArr = b.e;
        while (i < intArray.length) {
            if (k.a.d() == intArray[i]) {
                ((dji.device.common.view.set.b.a) this.d.get(2)).l = iArr[i];
                break;
            }
            i++;
        }
        dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) this.d.get(3);
        aVar.l = b.g[dji.pilot.phonecamera.a.c.a().k()];
        this.e.notifyDataSetChanged();
    }

    public void onEventMainThread(dji.pilot.d.a aVar) {
        if (aVar.b(0) == 1) {
            ((dji.device.common.view.set.b.a) this.d.get(aVar.b(0))).l = aVar.b(1);
        } else if (aVar.b(0) == 2) {
            ((dji.device.common.view.set.b.a) this.d.get(aVar.b(0))).l = aVar.b(1);
        } else if (aVar.b(0) == 3) {
            ((dji.device.common.view.set.b.a) this.d.get(aVar.b(0))).l = aVar.b(1);
        }
        this.e.notifyDataSetChanged();
    }

    protected dji.device.common.view.set.b.a a(int i) {
        dji.device.common.view.set.b.a aVar = new dji.device.common.view.set.b.a();
        aVar.k = this.a[i];
        aVar.n = this.b[i];
        aVar.o = this.a[i];
        int[] a;
        int[] a2;
        int i2;
        if (i == 1) {
            a = dji.phone.c.c.a(getResources(), getResources().obtainTypedArray(R.array.phone_camera_wb_res_array), dji.phone.c.c.a().a(null));
            a2 = dji.phone.c.c.a().a(null);
            for (i2 = 0; i2 < a2.length; i2++) {
                if (dji.pilot.phonecamera.a.c.a().f() == a2[i2]) {
                    aVar.l = a[i2];
                    break;
                }
            }
        } else if (i == 2) {
            a = getResources().getIntArray(R.array.phone_camera_flash_value_array);
            a2 = b.e;
            for (i2 = 0; i2 < a.length; i2++) {
                if (dji.pilot.phonecamera.a.c.a().f() == a[i2]) {
                    aVar.l = a2[i2];
                    break;
                }
            }
        } else if (i == 3) {
            aVar.l = b.g[dji.pilot.phonecamera.a.c.a().k()];
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
}
