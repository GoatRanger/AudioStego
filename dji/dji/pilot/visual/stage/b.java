package dji.pilot.visual.stage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class b extends BaseAdapter {
    private final LayoutInflater a;
    private final List<a> b;
    private int c = 0;

    public static final class a {
        private int a = 0;
        private int b = 0;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public static final class b {
        private DJITextView a = null;
    }

    public b(Context context, List<a> list) {
        this.a = LayoutInflater.from(context);
        this.b = list;
    }

    public void a(int i) {
        if (i != this.c) {
            this.c = i;
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b == null || this.b.size() <= i) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        boolean z;
        if (view == null) {
            bVar = new b();
            view = this.a.inflate(R.layout.vision_track_mode_item, null);
            bVar.a = (DJITextView) view.findViewById(R.id.d87);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        a aVar = (a) this.b.get(i);
        DJITextView a = bVar.a;
        a.setCompoundDrawablesWithIntrinsicBounds(0, aVar.a, 0, 0);
        a.setText(aVar.b);
        if (i == this.c) {
            z = true;
        } else {
            z = false;
        }
        a.setSelected(z);
        return view;
    }
}
