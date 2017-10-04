package dji.phone.set.list;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.common.view.set.b.b;
import dji.device.common.view.set.listview.DJIBaseSecondaryListView;
import dji.midware.data.manager.P3.i;
import dji.phone.c.a;
import dji.phone.d.d;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;

public class DJILPSecondStageView extends DJIBaseSecondaryListView {
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    private static final String m = "DJILPSecondStageView";

    public DJILPSecondStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void a() {
    }

    protected void b() {
        this.b = new OnItemClickListener(this) {
            final /* synthetic */ DJILPSecondStageView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = ((b) this.a.e.get(i)).i;
                if (!this.a.b(i2, ((b) this.a.e.get(i)).j)) {
                    return;
                }
                if (this.a.d == 0) {
                    dji.pilot.phonecamera.a.c.a().h(i2);
                    this.a.f = ((b) this.a.e.get(i)).i;
                    this.a.g = Integer.MAX_VALUE;
                    this.a.a(this.a.f, this.a.g);
                } else if (1 == this.a.d) {
                    String str = (String) dji.phone.c.c.a.get(Integer.valueOf(i2));
                    a.c().b(str);
                    this.a.a(str);
                    this.a.f = ((b) this.a.e.get(i)).i;
                    d.getInstance().a(this.a.f, false);
                    this.a.g = Integer.MAX_VALUE;
                    this.a.a(this.a.f, this.a.g);
                    r1 = new dji.pilot.d.a();
                    r1.a(1);
                    r1.a(((b) this.a.e.get(i)).g);
                    c.a().e(r1);
                } else if (2 == this.a.d) {
                    if (d.getInstance().b() != dji.phone.d.c.b.CAMERA_FRONT) {
                        this.a.f = ((b) this.a.e.get(i)).i;
                        d.getInstance().b(this.a.f, false);
                        this.a.g = Integer.MAX_VALUE;
                        this.a.a(this.a.f, this.a.g);
                        a.c().a(dji.phone.c.c.a(this.a.f));
                        r1 = new dji.pilot.d.a();
                        r1.a(2);
                        r1.a(((b) this.a.e.get(i)).g);
                        c.a().e(r1);
                    }
                } else if (3 == this.a.d) {
                    this.a.f = ((b) this.a.e.get(i)).i;
                    this.a.g = Integer.MAX_VALUE;
                    this.a.a(this.a.f, this.a.g);
                    dji.pilot.phonecamera.a.c.a().i(this.a.f);
                    this.a.a(this.a.f);
                    r1 = new dji.pilot.d.a();
                    r1.a(3);
                    r1.a(((b) this.a.e.get(i)).g);
                    c.a().e(r1);
                    c.a().e(dji.pilot.phonecamera.a.c.a.valueOf(this.a.f));
                }
            }
        };
    }

    private void a(String str) {
        dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.m, str, false);
        dji.publics.b.b.a.getInstance().e("createtime", System.currentTimeMillis() + "", false).e("device_type", i.getInstance().c()._name(), false).e("pro_id", dji.publics.b.b.a.a, false).e("device_ver", dji.device.common.b.getInstance().b(), true);
    }

    private void a(int i) {
        switch (i) {
            case 0:
                dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.n, "1", false);
                break;
            case 1:
                dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.n, "2", false);
                break;
            case 2:
                dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.n, "3", false);
                break;
            case 3:
                dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.n, "4", false);
                break;
        }
        dji.publics.b.b.a.getInstance().e("createtime", System.currentTimeMillis() + "", false).e(dji.publics.b.a.b.u, "0.0.0.0", false).e("device_type", i.getInstance().c()._name(), false).e("pro_id", dji.publics.b.b.a.a, false).e("device_ver", "0.0.0.0", true);
    }

    protected List<b> c() {
        String[] strArr;
        int i;
        int[] a;
        int[] a2;
        int[] intArray;
        int[] iArr;
        int l;
        int i2 = 0;
        List arrayList = new ArrayList();
        dji.pilot.d.a aVar = new dji.pilot.d.a();
        if (this.d == 0) {
            List a3 = dji.phone.c.c.a(getResources().getStringArray(R.array.phone_camera_videosolution), dji.pilot.phonecamera.a.c.a().p(dji.pilot.phonecamera.a.c.a().s()));
            strArr = new String[a3.size()];
            for (i = 0; i < a3.size(); i++) {
                strArr[i] = (String) a3.get(i);
                if (strArr[i].equals("4K")) {
                    aVar.a(R.drawable.advanced_more_videoformat_4k);
                } else if (strArr[i].equals("1080P")) {
                    aVar.a(R.drawable.advanced_more_videoformat_1080p);
                } else if (strArr[i].equals(dji.pilot.phonecamera.a.c.A)) {
                    aVar.a(R.drawable.advanced_more_videoformat_720p);
                } else if (strArr[i].equals("480P")) {
                    aVar.a(R.drawable.advanced_more_videoformat_480p);
                }
            }
            a = aVar.a(null);
            a2 = dji.phone.c.c.b(strArr).a(null);
        } else {
            a2 = null;
            a = null;
            strArr = null;
        }
        if (1 == this.d) {
            strArr = getResources().getStringArray(R.array.phone_camera_wb_names_array);
            TypedArray obtainTypedArray = getResources().obtainTypedArray(R.array.phone_camera_wb_res_array);
            strArr = dji.phone.c.c.a(strArr);
            a = dji.phone.c.c.a(getResources(), obtainTypedArray, dji.phone.c.c.a().a(a));
            a2 = dji.phone.c.c.a().a(a2);
        }
        if (3 == this.d) {
            strArr = getResources().getStringArray(R.array.camera_grid_str_array);
            a = dji.device.common.view.set.view.b.g;
            a2 = getResources().getIntArray(R.array.camera_grid_value_array);
        }
        if (2 == this.d) {
            strArr = getResources().getStringArray(R.array.phone_camera_flash_names_array);
            intArray = getResources().getIntArray(R.array.phone_camera_flash_value_array);
            iArr = dji.device.common.view.set.view.b.e;
        } else {
            intArray = a2;
            iArr = a;
        }
        int length = strArr.length;
        for (i = 0; i < length; i++) {
            arrayList.add(a(i, 2, strArr, iArr, intArray, null));
        }
        if (this.d == 0) {
            l = dji.pilot.phonecamera.a.c.a().l();
            for (i = 0; i < arrayList.size(); i++) {
                if (((b) arrayList.get(i)).i == l) {
                    ((b) arrayList.get(i)).k = true;
                }
            }
        }
        if (1 == this.d) {
            l = dji.pilot.phonecamera.a.c.a().f();
            for (i = 0; i < arrayList.size(); i++) {
                if (((b) arrayList.get(i)).i == l) {
                    ((b) arrayList.get(i)).k = true;
                }
            }
        }
        if (2 == this.d) {
            int g = dji.pilot.phonecamera.a.c.a().g();
            for (i = 0; i < arrayList.size(); i++) {
                if (((b) arrayList.get(i)).i == g) {
                    ((b) arrayList.get(i)).k = true;
                }
            }
        }
        if (3 == this.d) {
            i = dji.pilot.phonecamera.a.c.a().k();
            while (i2 < arrayList.size()) {
                if (((b) arrayList.get(i2)).i == i) {
                    ((b) arrayList.get(i2)).k = true;
                }
                i2++;
            }
        }
        return arrayList;
    }

    public void onEventMainThread(dji.phone.d.c cVar) {
        Log.d(m, "onEventMainThread: " + cVar.toString());
        int i;
        dji.pilot.d.a aVar;
        switch (this.d) {
            case 0:
                Log.d(m, "onEventMainThread: TYPE_VIDEO_RESOLUTION Id = " + cVar.a.f());
                for (i = 0; i < this.e.size(); i++) {
                    Log.d(m, "onEventMainThread: mDatas.get(" + i + ").mValue1Id = " + ((b) this.e.get(i)).toString());
                    if (((b) this.e.get(i)).i == cVar.a.f()) {
                        ((b) this.e.get(i)).k = true;
                        aVar = new dji.pilot.d.a();
                        aVar.a(0);
                        aVar.a(((b) this.e.get(i)).g);
                        c.a().e(aVar);
                    } else {
                        ((b) this.e.get(i)).k = false;
                    }
                }
                this.c.notifyDataSetChanged();
                return;
            case 1:
                Log.d(m, "onEventMainThread: TYPE_WB");
                for (i = 0; i < this.e.size(); i++) {
                    if (((b) this.e.get(i)).i == cVar.a.c()) {
                        ((b) this.e.get(i)).k = true;
                        aVar = new dji.pilot.d.a();
                        aVar.a(1);
                        aVar.a(((b) this.e.get(i)).g);
                        c.a().e(aVar);
                    } else {
                        ((b) this.e.get(i)).k = false;
                    }
                }
                this.c.notifyDataSetChanged();
                return;
            case 2:
                Log.d(m, "onEventMainThread: TYPE_FLASH");
                for (i = 0; i < this.e.size(); i++) {
                    if (((b) this.e.get(i)).i == cVar.a.d()) {
                        Log.d(m, "onEventMainThread: value = " + ((b) this.e.get(i)).i);
                        ((b) this.e.get(i)).k = true;
                        aVar = new dji.pilot.d.a();
                        aVar.a(2);
                        aVar.a(((b) this.e.get(i)).g);
                        c.a().e(aVar);
                    } else {
                        ((b) this.e.get(i)).k = false;
                    }
                }
                this.c.notifyDataSetChanged();
                return;
            default:
                return;
        }
    }

    public void forceUpdateData(int i) {
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.d = i;
        this.e = c();
        this.c.a(this.e);
        a();
        this.c.notifyDataSetChanged();
    }

    public void onEventMainThread(dji.phone.d.c.b bVar) {
        dji.phone.d.c k = d.getInstance().k();
        Log.d(m, "onEventMainThread: " + k.toString());
        int i;
        dji.pilot.d.a aVar;
        switch (this.d) {
            case 0:
                forceUpdateData(0);
                Log.d(m, "onEventMainThread: TYPE_VIDEO_RESOLUTION Id = " + dji.pilot.phonecamera.a.c.a().l());
                for (i = 0; i < this.e.size(); i++) {
                    Log.d(m, "onEventMainThread: mDatas.get(" + i + ").mValue1Id = " + ((b) this.e.get(i)).toString());
                    if (((b) this.e.get(i)).i == dji.pilot.phonecamera.a.c.a().l()) {
                        ((b) this.e.get(i)).k = true;
                        dji.pilot.d.a aVar2 = new dji.pilot.d.a();
                        aVar2.a(0);
                        aVar2.a(((b) this.e.get(i)).g);
                        c.a().e(aVar2);
                    } else {
                        ((b) this.e.get(i)).k = false;
                    }
                }
                this.c.notifyDataSetChanged();
                return;
            case 1:
                Log.d(m, "onEventMainThread: TYPE_WB");
                for (i = 0; i < this.e.size(); i++) {
                    if (((b) this.e.get(i)).i == k.a.c()) {
                        ((b) this.e.get(i)).k = true;
                        aVar = new dji.pilot.d.a();
                        aVar.a(1);
                        aVar.a(((b) this.e.get(i)).g);
                        c.a().e(aVar);
                    } else {
                        ((b) this.e.get(i)).k = false;
                    }
                }
                this.c.notifyDataSetChanged();
                return;
            case 2:
                Log.d(m, "onEventMainThread: TYPE_FLASH");
                for (i = 0; i < this.e.size(); i++) {
                    if (((b) this.e.get(i)).i == k.a.d()) {
                        Log.d(m, "onEventMainThread: value = " + ((b) this.e.get(i)).i);
                        ((b) this.e.get(i)).k = true;
                        aVar = new dji.pilot.d.a();
                        aVar.a(2);
                        aVar.a(((b) this.e.get(i)).g);
                        c.a().e(aVar);
                    } else {
                        ((b) this.e.get(i)).k = false;
                    }
                }
                this.c.notifyDataSetChanged();
                return;
            default:
                return;
        }
    }
}
