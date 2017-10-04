package dji.device.common.view.set.a;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.device.common.view.set.b.b;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class a extends BaseAdapter {
    private final LayoutInflater a;
    private List<b> b = null;

    private static final class a {
        public DJITextView a;
        public DJIImageView b;
        public DJIImageView c;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }

    public a(Context context) {
        this.a = LayoutInflater.from(context);
    }

    public void a(List<b> list) {
        this.b = list;
    }

    public int getCount() {
        return this.b != null ? this.b.size() : 0;
    }

    public Object getItem(int i) {
        if (this.b == null || i < 0 || i >= this.b.size()) {
            return null;
        }
        return (b) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item != null) {
            return ((b) item).e;
        }
        return 2;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        int itemViewType = getItemViewType(i);
        Object item = getItem(i);
        a aVar;
        if (itemViewType == 1) {
            if (view != null) {
                aVar = (a) view.getTag();
            } else {
                aVar = new a();
                view = this.a.inflate(R.layout.longan_shotcuts_itemlist_view, null);
                aVar.a = (DJITextView) view.findViewById(R.id.longan_shotcuts_itemlist_title);
                aVar.b = (DJIImageView) view.findViewById(R.id.longan_shotcuts_itemlist_value_iv);
                aVar.c = (DJIImageView) view.findViewById(R.id.longan_shotcuts_itemlist_arrow);
            }
            aVar.a.go();
            aVar.b.go();
            if (item != null) {
                b bVar = (b) item;
                DJIImageView dJIImageView = aVar.c;
                if (bVar.k) {
                    itemViewType = 0;
                } else {
                    itemViewType = 4;
                }
                dJIImageView.setVisibility(itemViewType);
            }
            view.setTag(aVar);
        } else if (itemViewType == 2) {
            if (view != null) {
                r0 = (a) view.getTag();
            } else {
                aVar = new a();
                view = this.a.inflate(R.layout.longan_shotcuts_itemlist_view, null);
                aVar.a = (DJITextView) view.findViewById(R.id.longan_shotcuts_itemlist_title);
                aVar.b = (DJIImageView) view.findViewById(R.id.longan_shotcuts_itemlist_value_iv);
                aVar.c = (DJIImageView) view.findViewById(R.id.longan_shotcuts_itemlist_arrow);
                r0 = aVar;
            }
            r0.a.show();
            r0.b.show();
            if (item != null) {
                r1 = (b) item;
                r0.b.setImageResource(r1.g);
                r0.a.setText(r1.f);
                r2 = r0.c;
                if (!r1.k) {
                    i2 = 4;
                }
                r2.setVisibility(i2);
            }
            view.setTag(r0);
        } else if (itemViewType == 0) {
            if (view == null) {
                aVar = new a();
                view = this.a.inflate(R.layout.longan_shotcuts_itemlist_view, null);
                aVar.a = (DJITextView) view.findViewById(R.id.longan_shotcuts_itemlist_title);
                aVar.b = (DJIImageView) view.findViewById(R.id.longan_shotcuts_itemlist_value_iv);
                aVar.c = (DJIImageView) view.findViewById(R.id.longan_shotcuts_itemlist_arrow);
                view.setTag(aVar);
                r0 = aVar;
            } else {
                r0 = (a) view.getTag();
            }
            r0.a.show();
            r0.b.go();
            if (item != null) {
                r1 = (b) item;
                r0.a.setText(r1.f);
                r2 = r0.c;
                if (!r1.k) {
                    i2 = 4;
                }
                r2.setVisibility(i2);
            } else {
                Log.w("", "res debug:null == item");
            }
            view.setTag(r0);
        }
        return view;
    }
}
