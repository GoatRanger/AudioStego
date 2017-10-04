package dji.pilot.gallery.entryActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import dji.pilot.R;
import dji.pilot.gallery.entryActivity.d.c;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.mine.view.FixRatioImageView;
import dji.publics.DJIUI.DJIGridView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DJIGalleryAlbumsGridView extends LinearLayout {
    private Context a;
    private DJIGridView b;
    private a c;

    private class a extends BaseAdapter {
        final /* synthetic */ DJIGalleryAlbumsGridView a;
        private b b;
        private ArrayList<dji.pilot.gallery.entryActivity.d.a> c = new ArrayList();
        private TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> d = new TreeMap(new c());

        private class a {
            public FixRatioImageView a;
            public DJIImageView b;
            public DJITextView c;
            public DJITextView d;
            public DJIRelativeLayout e;
            final /* synthetic */ a f;

            private a(a aVar) {
                this.f = aVar;
            }
        }

        public a(DJIGalleryAlbumsGridView dJIGalleryAlbumsGridView) {
            this.a = dJIGalleryAlbumsGridView;
        }

        public void a(b bVar) {
            this.b = bVar;
        }

        public void a(TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap) {
            this.d = treeMap;
            b(treeMap);
            notifyDataSetChanged();
        }

        private void b(TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap) {
            this.c = new ArrayList(treeMap.keySet());
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.d.get((dji.pilot.gallery.entryActivity.d.a) this.c.get(i));
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.a).inflate(R.layout.gallery_album_grid_item, null);
                a aVar2 = new a();
                aVar2.e = (DJIRelativeLayout) view.findViewById(R.id.afm);
                aVar2.a = (FixRatioImageView) view.findViewById(R.id.afo);
                aVar2.c = (DJITextView) view.findViewById(R.id.afn);
                aVar2.d = (DJITextView) view.findViewById(R.id.afp);
                aVar2.b = (DJIImageView) view.findViewById(R.id.afq);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            final List list = (List) getItem(i);
            aVar.b.setVisibility(4);
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (((g) list.get(i2)).k) {
                    aVar.b.setVisibility(0);
                }
            }
            String str = "";
            if (((g) list.get(0)).h == dji.pilot.gallery.entryActivity.d.b.Type_VIDEO) {
                str = ((g) list.get(0)).b;
            } else {
                str = ((g) list.get(0)).c;
            }
            b.getInstance().b(str, aVar.a);
            str = ((g) list.get(0)).c;
            aVar.c.setText(str.split(d.t)[str.split(d.t).length - 2]);
            aVar.d.setText(list.size() + "");
            aVar.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.b.a(list);
                }
            });
            return view;
        }
    }

    public interface b {
        void a(List<g> list);
    }

    public DJIGalleryAlbumsGridView(Context context) {
        this(context, null);
    }

    public DJIGalleryAlbumsGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIGalleryAlbumsGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        a();
    }

    public TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> getmMedias() {
        return null;
    }

    public void refresh() {
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }

    public void setDate(TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap) {
        if (this.c != null) {
            this.c.a((TreeMap) treeMap);
        }
    }

    public void setChildItemClickListener(b bVar) {
        this.c.a(bVar);
    }

    private void a() {
        dji.setting.a.a.a((View) this, (int) R.layout.gallery_album_gridview);
        if (!isInEditMode()) {
            this.b = (DJIGridView) findViewById(R.id.afr);
            this.c = new a(this);
            this.b.setAdapter(this.c);
        }
    }
}
