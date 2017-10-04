package dji.gs.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.gs.R;

public class MarkerItemView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private ImageView e;
    private ImageView f;

    public enum a {
        LEFT,
        MID,
        RIGHT
    }

    public MarkerItemView(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.gs_marker_item, this);
        this.a = (TextView) inflate.findViewById(R.id.gs_item_direction);
        this.b = (TextView) inflate.findViewById(R.id.gs_item_distance);
        this.c = (TextView) inflate.findViewById(R.id.gs_item_num);
        this.f = (ImageView) inflate.findViewById(R.id.gs_item_img);
        this.d = (ImageView) inflate.findViewById(R.id.gs_item_seg_left);
        this.e = (ImageView) inflate.findViewById(R.id.gs_item_seg_right);
    }

    public void init(dji.gs.e.e.a aVar, float f, int i, a aVar2, boolean z) {
        if (z) {
            if (this.c.getVisibility() == 0) {
                this.c.setVisibility(4);
                this.f.setImageResource(R.drawable.gs_homepoint);
            }
        } else if (this.c.getVisibility() == 4) {
            this.c.setVisibility(0);
            this.f.setImageResource(R.drawable.gs_mark);
        }
        this.a.setText(aVar.toString());
        if (f == 0.0f) {
            a(this.b, 4);
        } else {
            a(this.b, 0);
        }
        this.c.setText(i + "");
        setSegView(aVar2);
    }

    private void setSegView(a aVar) {
        switch (aVar) {
            case LEFT:
                a(this.d, 4);
                a(this.e, 0);
                return;
            case MID:
                a(this.d, 0);
                a(this.e, 0);
                return;
            case RIGHT:
                a(this.d, 0);
                a(this.e, 4);
                return;
            default:
                return;
        }
    }

    private void a(View view, int i) {
        if (view.getVisibility() != i) {
            view.setVisibility(i);
        }
    }
}
