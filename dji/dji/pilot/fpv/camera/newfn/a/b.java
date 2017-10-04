package dji.pilot.fpv.camera.newfn.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.pilot.fpv.camera.newfn.b.c;
import dji.pilot.publics.e.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class b extends BaseAdapter {
    private final LayoutInflater a;
    private final List<c> b;

    private static final class a {
        public View a;
        public DJITextView b;
        public DJIImageView c;
        public DJITextView d;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public b(Context context, List<c> list) {
        this.b = list;
        this.a = LayoutInflater.from(context);
    }

    public int getCount() {
        int size = this.b.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            if (((c) this.b.get(i)).g) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    public Object getItem(int i) {
        return a(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public c a(int i) {
        int i2 = 0;
        int size = this.b.size();
        int i3 = 0;
        while (i3 < size) {
            int i4;
            if (((c) this.b.get(i3)).g) {
                i4 = i2 + 1;
            } else {
                i4 = i2;
            }
            if (i4 == i + 1) {
                return (c) this.b.get(i3);
            }
            i3++;
            i2 = i4;
        }
        return null;
    }

    public boolean isEnabled(int i) {
        c a = a(i);
        if (a != null) {
            return a.h;
        }
        return super.isEnabled(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a();
            view = this.a.inflate(R.layout.camera_newfn_first_item, null);
            aVar2.a = view;
            aVar2.b = (DJITextView) view.findViewById(R.id.lh);
            aVar2.c = (DJIImageView) view.findViewById(R.id.lk);
            aVar2.d = (DJITextView) view.findViewById(R.id.lj);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        c a = a(i);
        if (a != null) {
            aVar.a.setEnabled(a.h);
            aVar.a.setAlpha(a.h ? 1.0f : 0.3f);
            aVar.b.setText(a.b);
            if (a.c == 0) {
                aVar.c.go();
            } else {
                aVar.c.show();
                aVar.c.setImageResource(a.c);
            }
            if (d.a(a.d)) {
                aVar.d.go();
            } else {
                aVar.d.show();
                aVar.d.setText(a.d);
            }
        }
        return view;
    }
}
