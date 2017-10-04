package dji.pilot2.upgrade.ble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import dji.midware.b.a.c;
import dji.pilot.R;
import dji.pilot2.widget.DJIWorkingTextView;
import java.util.List;

public class a extends BaseAdapter {
    private static final String a = "DJIBleListAdapter";
    private final LayoutInflater b;
    private List<dji.midware.b.b> c;
    private a d;

    public interface a {
        void onClick(View view, View view2, int i, int i2, String str);
    }

    private static final class b {
        public TextView a;
        public DJIWorkingTextView b;
        public Button c;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }

    public a(Context context) {
        this.b = LayoutInflater.from(context);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a(List<dji.midware.b.b> list) {
        this.c = list;
    }

    public List<dji.midware.b.b> a() {
        return this.c;
    }

    public void a(dji.midware.b.b bVar) {
        if (!this.c.contains(bVar)) {
            this.c.add(bVar);
        }
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        if (this.c == null || i < 0 || i >= this.c.size()) {
            return null;
        }
        return (dji.midware.b.b) this.c.get(i);
    }

    public dji.midware.b.b a(String str) {
        if (!(this.c == null || str == null)) {
            for (dji.midware.b.b bVar : this.c) {
                if (bVar.a.equals(str)) {
                    return bVar;
                }
            }
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        b bVar;
        if (view == null) {
            b bVar2 = new b();
            inflate = this.b.inflate(R.layout.v2_home_ble_list_item_ly, null);
            bVar2.a = (TextView) inflate.findViewById(R.id.b_x);
            bVar2.b = (DJIWorkingTextView) inflate.findViewById(R.id.b_z);
            bVar2.c = (Button) inflate.findViewById(R.id.b_y);
            inflate.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
            inflate = view;
        }
        final dji.midware.b.b bVar3 = (dji.midware.b.b) getItem(i);
        if (bVar3 != null) {
            bVar.a.setText(bVar3.b);
            if (bVar3.f == c.b) {
                bVar.b.stopWorking();
                bVar.b.setText(R.string.lp_ble_device_connected);
            } else if (bVar3.f == c.d) {
                bVar.c.setText(R.string.lp_ble_device_connecting);
                bVar.b.setVisibility(0);
                bVar.b.startWorking();
                bVar.c.setVisibility(8);
            } else if (bVar3.f == c.c) {
                bVar.b.setVisibility(8);
                bVar.b.stopWorking();
                bVar.c.setVisibility(0);
                bVar.c.setText(R.string.lp_ble_device_connect_txt);
                bVar.c.setSelected(false);
            }
        }
        final int id = bVar.c.getId();
        if (!bVar.c.hasOnClickListeners()) {
            final ViewGroup viewGroup2 = viewGroup;
            final int i2 = i;
            bVar.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a f;

                public void onClick(View view) {
                    if (this.f.d != null) {
                        this.f.d.onClick(inflate, viewGroup2, i2, id, bVar3.a);
                    }
                }
            });
        }
        return inflate;
    }
}
