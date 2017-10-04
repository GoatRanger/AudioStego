package dji.pilot2.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.pilot.R;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class b {
    private Context a;
    private RelativeLayout b;
    private TextView c;
    private int d;
    private int e = 0;

    public b(Context context, ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        if (i != 0) {
            this.e = i;
            this.b = (RelativeLayout) from.inflate(R.layout.v2_local_music_drag_rect, viewGroup, false);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.d = context.getResources().getDimensionPixelSize(R.dimen.g1) - 8;
            } else {
                this.d = context.getResources().getDimensionPixelSize(R.dimen.ft) - 11;
            }
        } else {
            this.b = (RelativeLayout) from.inflate(R.layout.v2_singlemoment_fine_horizonal_rect, viewGroup, false);
            this.b.findViewById(R.id.crc).getLayoutParams().width = this.b.getWidth();
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.d = context.getResources().getDimensionPixelSize(R.dimen.g1) - 2;
            } else {
                this.d = context.getResources().getDimensionPixelSize(R.dimen.ft) - 5;
            }
        }
        this.c = (TextView) this.b.findViewById(R.id.crd);
    }

    public RelativeLayout a() {
        return this.b;
    }

    public void a(int i, int i2) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((this.d * 2) + i2, -1);
        layoutParams.leftMargin = i - this.d;
        this.b.findViewById(R.id.crc).getLayoutParams().width = (this.d * 2) + i2;
        this.b.setLayoutParams(layoutParams);
    }

    public void a(String str) {
        this.c.setText(str);
    }

    public int b() {
        return this.b.getWidth() - (this.d * 2);
    }

    public int c() {
        return ((int) this.b.getX()) + this.d;
    }

    public void a(boolean z) {
        this.b.setPressed(z);
    }

    public Object d() {
        return this.b.getTag();
    }

    public void a(Object obj) {
        this.b.setTag(obj);
    }

    public void b(boolean z) {
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(4);
        }
    }
}
