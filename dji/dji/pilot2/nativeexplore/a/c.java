package dji.pilot2.nativeexplore.a;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import dji.pilot.R;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.ExploreItem.ExploreType;
import java.util.ArrayList;
import java.util.List;

public class c extends BaseAdapter {
    private Context a;
    private DisplayImageOptions b = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();
    private List<ExploreItem> c = new ArrayList();
    private View d;
    private View e;
    private boolean f = false;
    private boolean g;

    public void a(boolean z) {
        this.g = z;
    }

    public c(Context context) {
        this.a = context;
        this.d = LayoutInflater.from(context).inflate(R.layout.v2_explore_refresh_footer, null);
        ((AnimationDrawable) ((ImageView) this.d.findViewById(R.id.cj8)).getDrawable()).start();
        this.e = new View(context);
    }

    public void a() {
        this.c.clear();
        this.f = false;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public synchronized void a(List<ExploreItem> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object obj;
                ExploreItem exploreItem = (ExploreItem) list.get(i);
                int i2 = 0;
                while (i2 < this.c.size()) {
                    if (exploreItem.id != null && this.c.get(i2) != null && exploreItem.id.equals(((ExploreItem) this.c.get(i2)).id)) {
                        obj = 1;
                        break;
                    }
                    i2++;
                }
                obj = null;
                if (obj == null) {
                    i2 = this.c.size();
                    while (i2 - 1 >= 0 && this.c.get(i2 - 1) != null && ((ExploreItem) this.c.get(i2 - 1)).type == ExploreType.ads && ((ExploreItem) this.c.get(i2 - 1)).adsPosition > i2 - 1) {
                        i2--;
                    }
                    this.c.add(i2, exploreItem);
                }
            }
            notifyDataSetChanged();
        }
    }

    public synchronized void b(List<ExploreItem> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ExploreItem exploreItem = (ExploreItem) list.get(i);
                if (!(exploreItem == null || exploreItem.type != ExploreType.ads || this.c.contains(exploreItem))) {
                    if (exploreItem.adsPosition >= this.c.size()) {
                        this.c.add(exploreItem);
                    } else {
                        this.c.add(exploreItem.adsPosition, exploreItem);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.c.size() + 1;
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean isEnabled(int i) {
        return false;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i != this.c.size()) {
            d dVar;
            if (view == null || view.getTag() == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.v2_native_explore_item, null);
                dVar = new d(this.a, this.b);
                dVar.a(view);
                view.setTag(dVar);
            }
            dVar = (d) view.getTag();
            dVar.a((ExploreItem) this.c.get(i));
            dVar.a(this.g);
            dVar.a();
            return view;
        } else if (this.f || this.c.size() == 0) {
            return this.e;
        } else {
            return this.d;
        }
    }
}
