package dji.pilot.gallery.entryActivity;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.media.e;
import dji.pilot2.mine.view.FixRatioImageView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class f extends BaseExpandableListAdapter {
    private TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> a = new TreeMap(new dji.pilot.gallery.entryActivity.d.c());
    private ArrayList<dji.pilot.gallery.entryActivity.d.a> b = new ArrayList();
    private Context c;
    private DJIGalleryFragment d;
    private a e;
    private Handler f;
    private h g;

    public interface a {
        void a();

        void a(int i);

        void a(g gVar);

        void b(int i);
    }

    public static class b {
        public FixRatioImageView a;
        public DJIImageView b;
        public DJIImageView c;
        public DJITextView d;

        public void a(g gVar, boolean z) {
            if (z && gVar.k) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
            if (gVar.h.equals(dji.pilot.gallery.entryActivity.d.b.Type_IMG)) {
                this.b.setVisibility(8);
                this.d.setVisibility(8);
                b.getInstance().b(gVar.c, this.a);
            } else if (gVar.h.equals(dji.pilot.gallery.entryActivity.d.b.Type_VIDEO)) {
                this.b.setVisibility(0);
                this.d.setVisibility(0);
                this.b.setImageResource(R.drawable.hg_videodesc);
                this.d.setText("" + String.format("%d:%d", new Object[]{Integer.valueOf(gVar.d / 60000), Integer.valueOf((gVar.d % 60000) / 1000)}));
                b.getInstance().a(gVar.b, this.a);
            }
        }
    }

    public class c {
        public View a;
        public View b;
        public View c;
        public View d;
        public View e;
        public View f;
        final /* synthetic */ f g;

        public c(f fVar) {
            this.g = fVar;
        }

        public void a(int i, int i2) {
            int i3;
            boolean z = this.g.d.b().equals(dji.pilot.gallery.entryActivity.DJIGalleryFragment.a.SelectOrPreview_SELECT);
            if (this.g.c.getResources().getConfiguration().orientation == 2) {
                i3 = i2 * 6;
            } else {
                i3 = i2 * 3;
            }
            Object child = this.g.getChild(i, i3);
            Object child2 = this.g.getChild(i, i3 + 1);
            Object child3 = this.g.getChild(i, i3 + 2);
            if (child == null || !(child instanceof g)) {
                this.a.setVisibility(4);
            } else {
                this.a.setVisibility(0);
                final g gVar = (g) child;
                a(this.a, gVar, z);
                this.a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem1");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(gVar);
                        }
                    }
                });
            }
            if (child2 == null || !(child2 instanceof g)) {
                this.b.setVisibility(4);
            } else {
                this.b.setVisibility(0);
                gVar = (g) child2;
                a(this.b, gVar, z);
                this.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem2");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(gVar);
                        }
                    }
                });
            }
            if (child3 == null || !(child3 instanceof g)) {
                this.c.setVisibility(4);
            } else {
                this.c.setVisibility(0);
                gVar = (g) child3;
                a(this.c, gVar, z);
                this.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem3");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(gVar);
                        }
                    }
                });
            }
            if (this.g.c.getResources().getConfiguration().orientation == 1) {
                this.d.setVisibility(8);
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                return;
            }
            child = this.g.getChild(i, i3 + 3);
            child2 = this.g.getChild(i, i3 + 4);
            child3 = this.g.getChild(i, i3 + 5);
            if (child == null || !(child instanceof g)) {
                this.d.setVisibility(4);
            } else {
                this.d.setVisibility(0);
                gVar = (g) child;
                a(this.d, gVar, z);
                this.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem4");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(gVar);
                        }
                    }
                });
            }
            if (child2 == null || !(child2 instanceof g)) {
                this.e.setVisibility(4);
            } else {
                this.e.setVisibility(0);
                gVar = (g) child2;
                a(this.e, gVar, z);
                this.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem5");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(gVar);
                        }
                    }
                });
            }
            if (child3 == null || !(child3 instanceof g)) {
                this.f.setVisibility(4);
                return;
            }
            this.f.setVisibility(0);
            gVar = (g) child3;
            a(this.f, gVar, z);
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem6");
                    if (this.b.g.e != null) {
                        this.b.g.e.a(gVar);
                    }
                }
            });
        }

        private void a(View view, g gVar, boolean z) {
            b bVar;
            Object tag = view.getTag();
            if (tag == null || !(tag instanceof b)) {
                b bVar2 = new b();
                bVar2.b = (DJIImageView) view.findViewById(R.id.ags);
                bVar2.a = (FixRatioImageView) view.findViewById(R.id.agr);
                bVar2.c = (DJIImageView) view.findViewById(R.id.agu);
                bVar2.d = (DJITextView) view.findViewById(R.id.agt);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) tag;
            }
            if (bVar != null) {
                bVar.a(gVar, z);
            }
        }
    }

    public class d {
        public DJITextView a;
        public DJITextView b;
        public DJITextView c;
        public DJITextView d;
        final /* synthetic */ f e;

        public d(f fVar) {
            this.e = fVar;
        }

        public void a(dji.pilot.gallery.entryActivity.DJIGalleryFragment.a aVar, dji.pilot.gallery.entryActivity.d.a aVar2, final int i) {
            DJILogHelper.getInstance().LOGI("bob", "GroupViewTag  configure" + aVar);
            if (aVar.equals(dji.pilot.gallery.entryActivity.DJIGalleryFragment.a.SelectOrPreview_SELECT)) {
                this.b.setVisibility(0);
            } else {
                this.b.setVisibility(8);
            }
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(aVar2.c);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (aVar2.d == null && aVar2.e == null) {
                if (date != null) {
                    this.c.setText(new SimpleDateFormat("MMMM dd,yyyy", Locale.ENGLISH).format(date));
                }
                this.a.setText("");
                this.d.setText("");
            } else {
                this.a.setText(aVar2.d);
                this.c.setText(aVar2.e);
                if (date != null) {
                    this.d.setText(new SimpleDateFormat("MMMM dd,yyyy", Locale.ENGLISH).format(date));
                }
            }
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ d b;

                public void onClick(View view) {
                    if (this.b.b.getText().equals(this.b.e.c.getResources().getString(R.string.v2_hg_select))) {
                        if (this.b.e.e != null) {
                            this.b.e.e.a(i);
                        }
                        this.b.b.setText(this.b.e.c.getResources().getString(R.string.v2_hg_cancel));
                        return;
                    }
                    this.b.b.setText(this.b.e.c.getResources().getString(R.string.v2_hg_select));
                    if (this.b.e.e != null) {
                        this.b.e.e.b(i);
                    }
                }
            });
        }
    }

    public f(Context context, DJIGalleryFragment dJIGalleryFragment, a aVar) {
        this.c = context;
        this.d = dJIGalleryFragment;
        this.e = aVar;
        this.f = new Handler(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                int i = message.what;
                this.a.notifyDataSetChanged();
                super.handleMessage(message);
            }
        };
        this.g = new h(this.c);
        this.g.a(new a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a() {
                DJILogHelper.getInstance().LOGI("bob", "getLocationThread onFinished");
                TreeMap treeMap = new TreeMap(new dji.pilot.gallery.entryActivity.d.c());
                for (Entry entry : this.a.a.entrySet()) {
                    if (treeMap.containsKey(entry.getKey())) {
                        ((List) treeMap.get(entry.getKey())).addAll((Collection) entry.getValue());
                    } else {
                        treeMap.put(entry.getKey(), entry.getValue());
                    }
                }
                for (List sort : treeMap.values()) {
                    Collections.sort(sort, new Comparator<g>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return a((g) obj, (g) obj2);
                        }

                        public int a(g gVar, g gVar2) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                return simpleDateFormat.parse(gVar2.e).compareTo(simpleDateFormat.parse(gVar.e));
                            } catch (ParseException e) {
                                e.printStackTrace();
                                return gVar.e.compareTo(gVar2.e);
                            }
                        }
                    });
                }
                this.a.a = treeMap;
                this.a.b = new ArrayList(treeMap.keySet());
                this.a.a();
            }
        });
    }

    public void a() {
        this.f.sendMessage(new Message());
    }

    public void a(TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap) {
        DJILogHelper.getInstance().LOGI("bob", "adapter setData size " + treeMap.size());
        for (List sort : treeMap.values()) {
            Collections.sort(sort, new Comparator<g>(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((g) obj, (g) obj2);
                }

                public int a(g gVar, g gVar2) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        return simpleDateFormat.parse(gVar2.e).compareTo(simpleDateFormat.parse(gVar.e));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return gVar.e.compareTo(gVar2.e);
                    }
                }
            });
        }
        this.a = treeMap;
        this.b = new ArrayList(treeMap.keySet());
        a();
        this.g.a(this.b);
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public int getGroupCount() {
        if (this.a != null) {
            Set keySet = this.a.keySet();
            if (keySet != null) {
                return keySet.size();
            }
        }
        return 0;
    }

    public int getChildrenCount(int i) {
        dji.pilot.gallery.entryActivity.d.a aVar = (dji.pilot.gallery.entryActivity.d.a) this.b.get(i);
        if (aVar == null) {
            return 0;
        }
        List list = (List) this.a.get(aVar);
        if (list == null) {
            return 0;
        }
        int i2;
        int i3 = 3;
        if (this.c.getResources().getConfiguration().orientation == 2) {
            i3 = 6;
        }
        int size = list.size() / i3;
        if (list.size() % i3 > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return size + i2;
    }

    public boolean a(int i) {
        if (i >= this.b.size()) {
            return false;
        }
        dji.pilot.gallery.entryActivity.d.a aVar = (dji.pilot.gallery.entryActivity.d.a) this.b.get(i);
        if (aVar != null) {
            List list = (List) this.a.get(aVar);
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((g) list.get(i2)).k = true;
            }
        }
        return true;
    }

    public boolean b() {
        for (Entry value : this.a.entrySet()) {
            List list = (List) value.getValue();
            for (int i = 0; i < list.size(); i++) {
                ((g) list.get(i)).k = false;
            }
        }
        return true;
    }

    public boolean b(int i) {
        if (i >= this.b.size()) {
            return false;
        }
        dji.pilot.gallery.entryActivity.d.a aVar = (dji.pilot.gallery.entryActivity.d.a) this.b.get(i);
        if (aVar != null) {
            List list = (List) this.a.get(aVar);
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((g) list.get(i2)).k = false;
            }
        }
        return true;
    }

    public Object getGroup(int i) {
        if (this.b == null || i >= this.b.size()) {
            return null;
        }
        dji.pilot.gallery.entryActivity.d.a aVar = (dji.pilot.gallery.entryActivity.d.a) this.b.get(i);
        if (aVar == null) {
            return null;
        }
        return aVar;
    }

    public Object getChild(int i, int i2) {
        if (i >= this.b.size()) {
            return null;
        }
        dji.pilot.gallery.entryActivity.d.a aVar = (dji.pilot.gallery.entryActivity.d.a) this.b.get(i);
        if (aVar != null) {
            List list = (List) this.a.get(aVar);
            if (list != null && list.size() > i2) {
                return list.get(i2);
            }
        }
        return null;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        Object tag;
        d dVar = null;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.gallery_groupitem, null);
            dVar = new d(this);
            dVar.d = (DJITextView) view.findViewById(R.id.agn);
            dVar.c = (DJITextView) view.findViewById(R.id.agj);
            dVar.a = (DJITextView) view.findViewById(R.id.agm);
            dVar.b = (DJITextView) view.findViewById(R.id.agk);
            view.setTag(dVar);
        } else {
            tag = view.getTag();
            if (tag instanceof d) {
                dVar = (d) tag;
            } else {
                DJILogHelper.getInstance().LOGI("bob", "tag is not GroupViewTag");
            }
        }
        if (dVar != null) {
            tag = getGroup(i);
            if (tag != null) {
                view.setVisibility(0);
                dVar.a(this.d.b(), (dji.pilot.gallery.entryActivity.d.a) tag, i);
            } else {
                DJILogHelper.getInstance().LOGI("bob", "getGroupView getGroup null");
                view.setVisibility(8);
            }
        }
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.gallery_childitems, null);
            cVar = new c(this);
            cVar.a = view.findViewById(R.id.afs);
            cVar.b = view.findViewById(R.id.aft);
            cVar.c = view.findViewById(R.id.afu);
            cVar.d = view.findViewById(R.id.afv);
            cVar.e = view.findViewById(R.id.afw);
            cVar.f = view.findViewById(R.id.afx);
            view.setTag(cVar);
        } else {
            Object tag = view.getTag();
            if (tag instanceof c) {
                cVar = (c) tag;
            } else {
                DJILogHelper.getInstance().LOGI("bob", "tag is not ChildViewTag");
                cVar = null;
            }
        }
        if (cVar != null) {
            if (i >= getGroupCount() || i2 >= getChildrenCount(i)) {
                view.setVisibility(8);
            } else {
                view.setVisibility(0);
                cVar.a(i, i2);
            }
        }
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    public void onGroupExpanded(int i) {
    }

    public void onGroupCollapsed(int i) {
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.e != null) {
            this.e.a();
        }
    }

    public long getCombinedChildId(long j, long j2) {
        return 0;
    }

    public long getCombinedGroupId(long j) {
        return 0;
    }

    public static Bitmap a(String str) {
        if (str == null) {
            return null;
        }
        Bitmap a = e.getInstance().a(str);
        if (a != null || !new File(str).exists()) {
            return a;
        }
        Options options = new Options();
        options.inSampleSize = 1;
        a = BitmapFactory.decodeFile(str, options);
        if (a == null) {
            return a;
        }
        e.getInstance().a(str, a);
        return a;
    }
}
