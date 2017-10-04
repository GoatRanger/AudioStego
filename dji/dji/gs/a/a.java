package dji.gs.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.gs.e.c;
import dji.gs.e.e;
import dji.gs.views.MarkerItemView;
import java.util.ArrayList;

public class a extends BaseAdapter {
    private Context a;
    private ArrayList<? extends c> b;
    private boolean c = false;

    public a(Context context, ArrayList<? extends c> arrayList) {
        this.a = context;
        this.b = arrayList;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a() {
        this.b = null;
    }

    public void a(ArrayList<? extends c> arrayList) {
        this.b = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.b.size() - 1;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View markerItemView;
        if (view == null) {
            markerItemView = new MarkerItemView(this.a);
        } else {
            markerItemView = (MarkerItemView) view;
        }
        e info = ((c) this.b.get(i + 1)).getInfo();
        if (info != null) {
            markerItemView.init(info.d(), (float) info.c(), i + 1, a(i + 1), i == getCount() + -1);
        }
        return markerItemView;
    }

    private dji.gs.views.MarkerItemView.a a(int i) {
        if (i == 1) {
            return dji.gs.views.MarkerItemView.a.LEFT;
        }
        if (i == getCount()) {
            return dji.gs.views.MarkerItemView.a.RIGHT;
        }
        return dji.gs.views.MarkerItemView.a.MID;
    }
}
