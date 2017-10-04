package dji.pilot2.library.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import java.util.HashMap;

public class d extends BaseAdapter {
    private HashMap<Integer, Drawable> a;
    private Context b;
    private int[] c = new int[]{R.string.v2_library_menu_sd, R.string.v2_library_menu_ph, R.string.v2_library_menu_vd};
    private int[] d = new int[]{R.drawable.v2_library_menu_download, R.drawable.v2_library_menu_pic, R.drawable.v2_library_menu_video};

    public class a {
        ImageView a;
        TextView b;
        final /* synthetic */ d c;

        public a(d dVar) {
            this.c = dVar;
        }
    }

    public d(Context context) {
        this.b = context;
        this.a = new HashMap();
        for (int i = 0; i < 3; i++) {
            this.a.put(Integer.valueOf(this.c[i]), this.b.getResources().getDrawable(this.d[i]));
        }
    }

    public int getCount() {
        return 3;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.v2_library_menu_item, null);
            aVar = new a(this);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a = (ImageView) view.findViewById(R.id.cqa);
        aVar.b = (TextView) view.findViewById(R.id.cqb);
        aVar.a.setBackgroundDrawable((Drawable) this.a.get(Integer.valueOf(this.c[i])));
        aVar.b.setText(this.b.getResources().getString(this.c[i]));
        view.setTag(aVar);
        return view;
    }
}
