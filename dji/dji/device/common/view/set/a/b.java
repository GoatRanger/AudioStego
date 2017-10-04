package dji.device.common.view.set.a;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class b extends BaseAdapter {
    private final LayoutInflater a;
    private final List<dji.device.common.view.set.b.a> b;
    private Map<Integer, View> c = new HashMap();

    private static final class a {
        public DJITextView a;
        public DJIImageView b;
        public DJITextView c;
        public Switch d;
        public DJIImageView e;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public b(Context context, List<dji.device.common.view.set.b.a> list) {
        this.b = list;
        this.a = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.b != null ? this.b.size() : 0;
    }

    public Object getItem(int i) {
        if (this.b == null || i < 0 || i >= this.b.size()) {
            return null;
        }
        return (dji.device.common.view.set.b.a) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    private View a(int i, int i2) {
        View view = (View) this.c.get(Integer.valueOf(i));
        if (view == null) {
            view = this.a.inflate(i2, null);
            if (view != null) {
                this.c.put(Integer.valueOf(i), view);
            }
        }
        return view;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!((dji.device.common.view.set.b.a) this.b.get(i)).i) {
            return new View(viewGroup.getContext());
        }
        int itemViewType = getItemViewType(i);
        a aVar;
        dji.device.common.view.set.b.a aVar2;
        if (itemViewType == 0) {
            aVar = new a();
            view = this.a.inflate(R.layout.longan_camera_newfn_first_item, null);
            aVar.a = (DJITextView) view.findViewById(R.id.set_item_title);
            aVar.b = (DJIImageView) view.findViewById(R.id.longan_camera_newfn_first_item_value);
            aVar.c = (DJITextView) view.findViewById(R.id.longan_camera_newfn_first_item_value_tv);
            view.setTag(aVar);
            aVar2 = (dji.device.common.view.set.b.a) this.b.get(i);
            aVar.a.setText(aVar2.k);
            if (aVar2.l == 0) {
                aVar.b.go();
                aVar.c.show();
                aVar.c.setText(aVar2.m);
                return view;
            }
            aVar.c.go();
            aVar.b.show();
            aVar.b.setImageResource(aVar2.l);
            return view;
        } else if (itemViewType == 1) {
            aVar = new a();
            view = this.a.inflate(R.layout.longan_shotcuts_litsitem_switch, null);
            aVar.a = (DJITextView) view.findViewById(R.id.set_item_title);
            aVar.d = (Switch) view.findViewById(R.id.set_item_value);
            if (aVar == null) {
                return view;
            }
            aVar2 = (dji.device.common.view.set.b.a) this.b.get(i);
            aVar.d.setChecked(aVar2.h);
            aVar.d.setEnabled(aVar2.j);
            aVar.a.setText(aVar2.k);
            return view;
        } else if (itemViewType == 2) {
            aVar = new a();
            view = this.a.inflate(R.layout.longan_camera_newfn_base_btn, null);
            aVar.a = (DJITextView) view.findViewById(R.id.set_item_title);
            if (aVar == null) {
                return view;
            }
            aVar.a.setText(((dji.device.common.view.set.b.a) this.b.get(i)).k);
            return view;
        } else if (itemViewType == 3) {
            aVar = new a();
            view = this.a.inflate(R.layout.longan_camera_newfn_image_btn, null);
            aVar.a = (DJITextView) view.findViewById(R.id.set_item_title);
            aVar.b = (DJIImageView) view.findViewById(R.id.image);
            if (aVar == null) {
                return view;
            }
            aVar2 = (dji.device.common.view.set.b.a) this.b.get(i);
            aVar.a.setText(aVar2.k);
            if (aVar2.l == 0) {
                return view;
            }
            aVar.b.setImageResource(aVar2.l);
            return view;
        } else {
            Log.e("", "error type");
            return view;
        }
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item != null) {
            return ((dji.device.common.view.set.b.a) item).f;
        }
        return 0;
    }

    public int getViewTypeCount() {
        return 4;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
