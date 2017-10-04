package dji.pilot2.multimoment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot2.template.d;

public abstract class e extends BaseAdapter {
    public static final int g = 10086;
    Context b;
    LayoutInflater c;
    int d;
    a e = null;
    View f = null;

    public interface a {
        void a(int i);
    }

    abstract class b {
        final /* synthetic */ e m;

        public abstract void a(int i);

        b(e eVar) {
            this.m = eVar;
        }
    }

    public abstract d b();

    public abstract int getCount();

    public abstract Object getItem(int i);

    public e(Context context) {
        this.b = context;
        this.c = LayoutInflater.from(this.b);
        this.d = 0;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        if (i < getCount()) {
            this.d = i;
            notifyDataSetChanged();
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a(View view) {
    }

    public void a(dji.pilot2.multimoment.videolib.b bVar) {
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
