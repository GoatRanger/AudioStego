package dji.pilot2.multimoment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class a extends BaseAdapter {
    private Context a;
    private LayoutInflater b;
    private int c;
    private b d = null;
    private View e = null;
    private List<dji.pilot2.multimoment.template.a> f;

    public interface b {
        void a(int i);
    }

    class a {
        DJITextView a = null;
        DJIImageView b;
        DJIImageView c;
        int d;
        final /* synthetic */ a e;

        a(a aVar) {
            this.e = aVar;
        }

        public void a(int i) {
            this.d = i;
            this.a.setText(((dji.pilot2.multimoment.template.a) this.e.getItem(i)).a());
            this.b.setImageBitmap(((dji.pilot2.multimoment.template.a) this.e.getItem(i)).b());
            if (this.e.c == i) {
                this.e.e = this.c;
                this.c.setVisibility(0);
                return;
            }
            this.c.setVisibility(4);
        }
    }

    public a(Context context, List<dji.pilot2.multimoment.template.a> list) {
        this.a = context;
        this.b = LayoutInflater.from(this.a);
        this.f = list;
        this.c = -1;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f == null ? 0 : this.f.size();
    }

    public Object getItem(int i) {
        return this.f.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a(View view) {
        a aVar = (a) view.getTag();
        if (aVar != null && this.d != null && this.c != aVar.d) {
            if (this.e != null) {
                this.e.setVisibility(4);
            }
            this.c = aVar.d;
            this.e = view.findViewById(R.id.ctl);
            this.e.setVisibility(0);
            this.d.a(aVar.d);
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.b.inflate(R.layout.v2_multimoment_filter_list_layout, null);
            a aVar2 = new a(this);
            aVar2.b = (DJIImageView) view.findViewById(R.id.ctk);
            aVar2.c = (DJIImageView) view.findViewById(R.id.ctl);
            aVar2.a = (DJITextView) view.findViewById(R.id.cto);
            aVar2.d = i;
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(i);
        view.requestLayout();
        return view;
    }
}
