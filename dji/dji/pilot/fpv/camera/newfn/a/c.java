package dji.pilot.fpv.camera.newfn.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.pilot.fpv.camera.newfn.b.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class c extends BaseAdapter {
    private final LayoutInflater a;
    private List<d> b = null;
    private a c = null;
    private OnClickListener d = null;

    public interface a {
        void a(int i, int i2, Object obj);
    }

    private static final class b {
        public DJILinearLayout a;
        public DJITextView b;
        public DJIImageView c;
        public DJIImageView d;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public c(Context context) {
        this.a = LayoutInflater.from(context);
        this.d = new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    int id = view.getId();
                    if (id == R.id.kp) {
                        Object tag = view.getTag();
                        if (tag instanceof Integer) {
                            int intValue = ((Integer) tag).intValue();
                            this.a.c.a(intValue, id, this.a.getItem(intValue));
                        }
                    }
                }
            }
        };
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(List<d> list) {
        this.b = list;
    }

    public int getCount() {
        return this.b != null ? this.b.size() : 0;
    }

    public Object getItem(int i) {
        if (this.b == null || i < 0 || i >= this.b.size()) {
            return null;
        }
        return (d) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item != null) {
            return ((d) item).e;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        Object item = getItem(i);
        b bVar;
        if (itemViewType == 1) {
            if (view == null) {
                bVar = new b();
                view = this.a.inflate(R.layout.camera_newfn_base_listview_item, null);
                bVar.a = (DJILinearLayout) view.findViewById(R.id.km);
                bVar.b = (DJITextView) view.findViewById(R.id.ko);
                bVar.c = (DJIImageView) view.findViewById(R.id.kn);
                bVar.d = (DJIImageView) view.findViewById(R.id.kp);
                bVar.d.setOnClickListener(this.d);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            bVar.d.setTag(Integer.valueOf(i));
            bVar.b.show();
            bVar.c.show();
            if (item != null) {
                d dVar = (d) item;
                bVar.c.setImageResource(dVar.g);
                bVar.b.setText(dVar.f);
                bVar.a.setSelected(dVar.k);
                if (dVar.h == 0) {
                    bVar.d.go();
                } else {
                    bVar.d.show();
                    bVar.d.setImageResource(dVar.h);
                }
            }
        } else if (itemViewType == 0 || itemViewType == 2) {
            b bVar2;
            if (view == null) {
                bVar = new b();
                view = this.a.inflate(R.layout.camera_newfn_base_listview_item, null);
                bVar.a = (DJILinearLayout) view.findViewById(R.id.km);
                bVar.b = (DJITextView) view.findViewById(R.id.ko);
                bVar.c = (DJIImageView) view.findViewById(R.id.kn);
                bVar.d = (DJIImageView) view.findViewById(R.id.kp);
                bVar.d.setOnClickListener(this.d);
                view.setTag(bVar);
                bVar2 = bVar;
            } else {
                bVar2 = (b) view.getTag();
            }
            bVar2.d.setTag(Integer.valueOf(i));
            bVar2.b.show();
            bVar2.c.go();
            if (item != null) {
                d dVar2 = (d) item;
                bVar2.b.setText(dVar2.f);
                bVar2.a.setSelected(dVar2.k);
                if (itemViewType == 2 || (dVar2.h != 0 && dVar2.k)) {
                    bVar2.d.show();
                    bVar2.d.setImageResource(dVar2.h);
                } else {
                    bVar2.d.go();
                }
            }
        }
        return view;
    }
}
