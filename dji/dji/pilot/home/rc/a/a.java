package dji.pilot.home.rc.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;

public class a extends BaseAdapter {
    private ArrayList<dji.pilot.home.rc.b.a> a;
    private Context b;
    private LayoutInflater c;

    public class a {
        public DJIImageView a = null;
        public DJITextView b = null;
        final /* synthetic */ a c;

        public a(a aVar) {
            this.c = aVar;
        }
    }

    public a(Context context, ArrayList<dji.pilot.home.rc.b.a> arrayList) {
        this.b = context;
        this.a = arrayList;
        this.c = LayoutInflater.from(context);
    }

    public void a(ArrayList<dji.pilot.home.rc.b.a> arrayList) {
        this.a = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        final dji.pilot.home.rc.b.a aVar2 = (dji.pilot.home.rc.b.a) this.a.get(i);
        if (view == null) {
            view = this.c.inflate(R.layout.setting_application_grid_item, null);
            a aVar3 = new a(this);
            aVar3.a = (DJIImageView) view.findViewById(R.id.bnv);
            aVar3.b = (DJITextView) view.findViewById(R.id.bnw);
            view.setTag(aVar3);
            aVar = aVar3;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setImageDrawable(aVar2.e);
        aVar.b.setText(aVar2.a);
        aVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.b.startActivity(this.b.b.getPackageManager().getLaunchIntentForPackage(aVar2.b));
            }
        });
        return view;
    }
}
