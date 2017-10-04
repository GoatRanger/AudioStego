package dji.device.common.view.set.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.common.view.set.b.b;
import dji.device.common.view.set.view.DJIStageViewCompat.a;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIListView;
import java.util.List;

public abstract class DJIBaseSecondaryListView extends DJILinearLayout implements a {
    protected DJIListView a = null;
    protected OnItemClickListener b = null;
    protected dji.device.common.view.set.a.a c = null;
    protected int d = Integer.MAX_VALUE;
    protected List<b> e = null;
    protected int f = Integer.MAX_VALUE;
    protected int g = Integer.MAX_VALUE;
    protected Context h;

    protected abstract void a();

    protected abstract void b();

    protected abstract List<b> c();

    public DJIBaseSecondaryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = context;
    }

    public void updateData(int i) {
        if (this.d != i) {
            this.f = Integer.MAX_VALUE;
            this.g = Integer.MAX_VALUE;
            this.d = i;
            this.e = c();
            this.c.a(this.e);
        }
        a();
        this.c.notifyDataSetChanged();
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

    protected void a(int i, int i2) {
        int size = this.e.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = (b) this.e.get(i3);
            boolean z;
            if (i2 == Integer.MAX_VALUE) {
                if (bVar.i == i) {
                    z = true;
                } else {
                    z = false;
                }
                bVar.k = z;
            } else {
                z = bVar.i == i && bVar.j == i2;
                bVar.k = z;
            }
        }
        this.c.notifyDataSetChanged();
    }

    protected boolean b(int i, int i2) {
        return ((i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE || (this.f == i && this.g == i2)) && (i == Integer.MAX_VALUE || i2 != Integer.MAX_VALUE || this.f == i)) ? false : true;
    }

    protected boolean a(AdapterView<?> adapterView, View view, int i, long j) {
        return false;
    }

    protected b a(int i, int i2, String[] strArr, int[] iArr, int[] iArr2, int[] iArr3) {
        b bVar = new b();
        bVar.e = i2;
        bVar.f = strArr[i];
        if (iArr != null) {
            bVar.g = iArr[i];
        }
        if (iArr2 != null) {
            bVar.i = iArr2[i];
        }
        if (iArr3 != null) {
            bVar.j = iArr3[i];
        } else {
            bVar.j = Integer.MAX_VALUE;
        }
        bVar.k = false;
        bVar.h = null;
        return bVar;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            b();
            this.c = new dji.device.common.view.set.a.a(getContext());
            this.a = (DJIListView) findViewById(R.id.longan_camera_newfn_base_lv);
            this.a.setAdapter(this.c);
            this.a.setOnItemClickListener(this.b);
            a();
        }
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
