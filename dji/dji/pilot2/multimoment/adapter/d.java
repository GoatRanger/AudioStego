package dji.pilot2.multimoment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import dji.pilot.R;
import dji.pilot2.template.c;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class d extends e {
    protected List<c> a;

    class a extends b {
        DJITextView a = null;
        DJIImageView b;
        DJIImageView c;
        DJITextView d;
        DJITextView e;
        DJIImageView f;
        int g;
        final /* synthetic */ d h;

        a(d dVar) {
            this.h = dVar;
            super(dVar);
        }

        public void a(int i) {
            c cVar = (c) dji.pilot2.multimoment.template.c.getInstance().a(this.h.b).get(i);
            this.a.setText(cVar.getTemplateName());
            this.g = i;
            this.b.setImageBitmap(cVar.getThumbnailBitmap());
            this.e.setText(String.valueOf(cVar.size()));
            this.d.setText(p.e((int) (cVar.getTotalDurations() / 1000)));
            if (this.h.d == i) {
                this.h.f = this.c;
                this.c.setVisibility(0);
                this.d.setTextColor(this.h.b.getResources().getColorStateList(R.color.j0));
                this.a.setTextColor(this.h.b.getResources().getColorStateList(R.color.j0));
                return;
            }
            this.c.setVisibility(4);
            this.d.setTextColor(this.h.b.getResources().getColorStateList(R.color.om));
            this.a.setTextColor(this.h.b.getResources().getColorStateList(R.color.om));
        }
    }

    public interface b {
        void a(int i);
    }

    public d(Context context, List<c> list) {
        super(context);
        this.a = list;
    }

    public void a(View view) {
        a aVar = (a) view.getTag();
        if (aVar != null && this.e != null && this.d != aVar.g) {
            if (this.f != null) {
                this.f.setVisibility(4);
            }
            this.d = aVar.g;
            this.f = view.findViewById(R.id.cu2);
            this.f.setVisibility(0);
            this.e.a(aVar.g);
            notifyDataSetChanged();
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.c.inflate(R.layout.v2_multimoment_template_list_layout, null);
            a aVar2 = new a(this);
            aVar2.a = (DJITextView) view.findViewById(R.id.cu5);
            aVar2.b = (DJIImageView) view.findViewById(R.id.cu1);
            aVar2.c = (DJIImageView) view.findViewById(R.id.cu2);
            aVar2.d = (DJITextView) view.findViewById(R.id.ctq);
            aVar2.e = (DJITextView) view.findViewById(R.id.cts);
            aVar2.e.setVisibility(8);
            aVar2.f = (DJIImageView) view.findViewById(R.id.ctr);
            aVar2.f.setVisibility(8);
            aVar2.g = i;
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(i);
        view.requestLayout();
        return view;
    }

    public dji.pilot2.template.d b() {
        return (dji.pilot2.template.d) this.a.get(this.d);
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }
}
