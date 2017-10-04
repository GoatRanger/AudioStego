package dji.pilot2.mine.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.mine.b.d;

public class e extends BaseAdapter {
    private int[][] a = new int[][]{d.a, d.b, d.c, d.d};
    private int[][] b = new int[][]{d.e, d.f, d.g, d.h};
    private int[][] c = new int[][]{d.i, d.j, d.l, d.k};
    private int[] d = new int[]{R.string.medal_pilot_name, R.string.medal_director_name, R.string.medal_photographer_name, R.string.medal_sharer_name};
    private int[] e = new int[]{R.string.medal_pilot_exp_unit, R.string.medal_director_exp_unit, R.string.medal_photographer_exp_unit, R.string.medal_sharer_exp_unit};
    private int[] f = new int[]{R.string.medal_exp_reach_pilot, R.string.medal_exp_reach_director, R.string.medal_exp_reach_photographer, R.string.medal_exp_reach_sharer};
    private Context g;
    private int h;

    public class a {
        public ImageView a;
        public TextView b;
        public TextView c;
        public TextView d;
        final /* synthetic */ e e;

        public a(e eVar) {
            this.e = eVar;
        }

        public void a(int i) {
            this.a.setImageResource(this.e.a[this.e.h][i]);
            this.b.setText(this.e.g.getResources().getString(this.e.d[this.e.h], new Object[]{Integer.valueOf(i + 1)}));
            this.c.setText(this.e.e[this.e.h]);
            int i2 = 0;
            for (int i3 = 0; i3 <= i; i3++) {
                i2 += this.e.c[this.e.h][i3];
            }
            this.d.setText(this.e.g.getResources().getString(this.e.f[this.e.h], new Object[]{Integer.valueOf(i2)}));
        }
    }

    public e(Context context) {
        this.g = context;
        this.h = 0;
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public int getCount() {
        return this.a[0].length;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public boolean isEnabled(int i) {
        return false;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.g).inflate(R.layout.v2_medal_introduce_list_item, null);
            a aVar2 = new a(this);
            aVar2.a = (ImageView) view.findViewById(R.id.csb);
            aVar2.b = (TextView) view.findViewById(R.id.csc);
            aVar2.c = (TextView) view.findViewById(R.id.csd);
            aVar2.d = (TextView) view.findViewById(R.id.cse);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(i);
        return view;
    }
}
