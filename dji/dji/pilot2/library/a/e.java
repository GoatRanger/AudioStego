package dji.pilot2.library.a;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.pilot.R;
import dji.pilot.usercenter.f.d;
import dji.pilot.usercenter.f.f;
import dji.pilot.usercenter.mode.g;
import dji.pilot.usercenter.widget.DJIShareProgressBar;
import dji.pilot2.library.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.List;
import java.util.Locale;

public class e extends BaseAdapter {
    private LayoutInflater a;
    private Context b;
    private List<g> c;
    private f d = f.getInstance();
    private final int e;
    private final int f;

    private static final class a {
        public DJIShareProgressBar a;
        public DJIImageView b;
        public DJIImageView c;
        public DJITextView d;

        private a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public e(Context context, List<g> list, int i, int i2) {
        this.d.a();
        this.a = LayoutInflater.from(context);
        this.b = context;
        this.c = list;
        this.e = i;
        this.f = i2;
    }

    public int getCount() {
        Log.i("LibraryCacheAdapter", "size is :" + this.c.size());
        return this.c.size();
    }

    public g a(int i) {
        if (this.c.size() > 0) {
            return (g) this.c.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = this.a.inflate(R.layout.v2_library_cache_item, null);
            aVar.a = (DJIShareProgressBar) view.findViewById(R.id.cpw);
            aVar.b = (DJIImageView) view.findViewById(R.id.cpz);
            aVar.c = (DJIImageView) view.findViewById(R.id.cq0);
            aVar.d = (DJITextView) view.findViewById(R.id.cpy);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        String language = Locale.getDefault().getLanguage();
        if (language.equals("cn") || language.equals("zh")) {
            aVar.b.setImageDrawable(this.b.getResources().getDrawable(R.drawable.v2_library_origin_cn));
        }
        g gVar = (g) this.c.get(i);
        aVar.a.setTag(gVar.e);
        if (d.b(gVar.g)) {
            aVar.d.setText(b(gVar.n));
            aVar.a.setFilePath(gVar.e);
            if (!b.e(gVar.e) || gVar.j) {
                aVar.b.go();
            } else {
                aVar.b.show();
            }
            this.d.a(aVar.a, gVar.e, this.f, this.e);
            if (gVar.j) {
                aVar.c.show();
            } else {
                aVar.c.go();
            }
        }
        return view;
    }

    private String b(int i) {
        int[] f = dji.pilot.fpv.d.b.f(i);
        return this.b.getString(R.string.v2_cachelist_videotime, new Object[]{Integer.valueOf(f[1]), Integer.valueOf(f[0])});
    }
}
