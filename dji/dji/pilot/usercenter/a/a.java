package dji.pilot.usercenter.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot.usercenter.e.b;
import java.util.ArrayList;

public class a extends BaseAdapter {
    private ArrayList<b> a;
    private Context b;

    private static final class a {
        TextView a;

        private a() {
        }
    }

    public a(ArrayList<b> arrayList, Context context) {
        this.a = arrayList;
        this.b = context;
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
        if (view == null) {
            aVar = new a();
            view = LayoutInflater.from(this.b).inflate(R.layout.usercenter_myinfo_select_region_listitem, null);
            aVar.a = (TextView) view.findViewById(R.id.c3k);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((b) this.a.get(i)).b);
        return view;
    }
}
