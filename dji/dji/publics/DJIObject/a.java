package dji.publics.DJIObject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class a extends BaseAdapter {
    private int a;

    public abstract View a(int i, ViewGroup viewGroup);

    public abstract void a(int i, View view);

    protected void a(int i) {
        this.a = i;
    }

    public int getCount() {
        return this.a;
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = a(i, viewGroup);
        }
        a(i, view);
        return view;
    }
}
