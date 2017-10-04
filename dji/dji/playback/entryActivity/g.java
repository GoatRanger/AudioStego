package dji.playback.entryActivity;

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
import dji.pilot2.mine.view.FixRatioImageView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.g.a.i;
import java.io.File;
import java.io.IOException;
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

public class g extends BaseExpandableListAdapter {
    private TreeMap<dji.playback.entryActivity.d.a, List<h>> a = new TreeMap(new dji.playback.entryActivity.d.c());
    private ArrayList<dji.playback.entryActivity.d.a> b = new ArrayList();
    private Context c;
    private DJIPlaybackFragment d;
    private a e;
    private Handler f;
    private f g;

    public interface a {
        void a();

        void a(int i);

        void a(h hVar);

        void b(int i);
    }

    public static class b {
        public FixRatioImageView a;
        public DJIImageView b;
        public DJIImageView c;
        public DJITextView d;

        public void a(h hVar, boolean z) {
            if (z && hVar.k) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
            if (hVar.h.equals(dji.playback.entryActivity.d.b.Type_IMG)) {
                String a = g.b(new File(hVar.c), dji.thirdparty.g.b.b.a.b.aL);
                if (a == null) {
                    this.b.setVisibility(8);
                } else if (a.equalsIgnoreCase("DJI-HDR")) {
                    this.b.setImageResource(R.drawable.hg_hdr);
                    this.b.setVisibility(0);
                } else if (a.equalsIgnoreCase(e.P_180.toString())) {
                    this.b.setImageResource(R.drawable.lp_pano_180);
                    this.b.setVisibility(0);
                } else if (a.equalsIgnoreCase(e.P_330.toString())) {
                    this.b.setImageResource(R.drawable.lp_pano_330);
                    this.b.setVisibility(0);
                } else if (a.equalsIgnoreCase(e.P_WIDE.toString())) {
                    this.b.setImageResource(R.drawable.lp_pano_wide);
                    this.b.setVisibility(0);
                } else {
                    this.b.setVisibility(8);
                }
                this.d.setVisibility(8);
                b.getInstance().b(hVar.c, this.a);
            } else if (hVar.h.equals(dji.playback.entryActivity.d.b.Type_VIDEO)) {
                this.b.setVisibility(0);
                this.d.setVisibility(0);
                this.b.setImageResource(R.drawable.hg_videodesc);
                this.d.setText("" + String.format("%d:%d", new Object[]{Integer.valueOf(hVar.d / 60000), Integer.valueOf((hVar.d % 60000) / 1000)}));
                b.getInstance().a(hVar.b, this.a);
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
        final /* synthetic */ g g;

        public c(g gVar) {
            this.g = gVar;
        }

        public void a(int i, int i2) {
            int i3;
            boolean z = this.g.d.d().equals(dji.playback.entryActivity.DJIPlaybackFragment.a.SelectOrPreview_SELECT);
            if (this.g.c.getResources().getConfiguration().orientation == 2) {
                i3 = i2 * 6;
            } else {
                i3 = i2 * 3;
            }
            Object child = this.g.getChild(i, i3);
            Object child2 = this.g.getChild(i, i3 + 1);
            Object child3 = this.g.getChild(i, i3 + 2);
            if (child == null || !(child instanceof h)) {
                this.a.setVisibility(4);
            } else {
                this.a.setVisibility(0);
                final h hVar = (h) child;
                a(this.a, hVar, z);
                this.a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem1");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(hVar);
                        }
                    }
                });
            }
            if (child2 == null || !(child2 instanceof h)) {
                this.b.setVisibility(4);
            } else {
                this.b.setVisibility(0);
                hVar = (h) child2;
                a(this.b, hVar, z);
                this.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem2");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(hVar);
                        }
                    }
                });
            }
            if (child3 == null || !(child3 instanceof h)) {
                this.c.setVisibility(4);
            } else {
                this.c.setVisibility(0);
                hVar = (h) child3;
                a(this.c, hVar, z);
                this.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem3");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(hVar);
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
            if (child == null || !(child instanceof h)) {
                this.d.setVisibility(4);
            } else {
                this.d.setVisibility(0);
                hVar = (h) child;
                a(this.d, hVar, z);
                this.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem4");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(hVar);
                        }
                    }
                });
            }
            if (child2 == null || !(child2 instanceof h)) {
                this.e.setVisibility(4);
            } else {
                this.e.setVisibility(0);
                hVar = (h) child2;
                a(this.e, hVar, z);
                this.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c b;

                    public void onClick(View view) {
                        DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem5");
                        if (this.b.g.e != null) {
                            this.b.g.e.a(hVar);
                        }
                    }
                });
            }
            if (child3 == null || !(child3 instanceof h)) {
                this.f.setVisibility(4);
                return;
            }
            this.f.setVisibility(0);
            hVar = (h) child3;
            a(this.f, hVar, z);
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    DJILogHelper.getInstance().LOGI("bob", "onclick mChildItem6");
                    if (this.b.g.e != null) {
                        this.b.g.e.a(hVar);
                    }
                }
            });
        }

        private void a(View view, h hVar, boolean z) {
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
                bVar.a(hVar, z);
            }
        }
    }

    public class d {
        public DJITextView a;
        public DJITextView b;
        public DJITextView c;
        public DJITextView d;
        final /* synthetic */ g e;

        public d(g gVar) {
            this.e = gVar;
        }

        public void a(dji.playback.entryActivity.DJIPlaybackFragment.a aVar, dji.playback.entryActivity.d.a aVar2, final int i) {
            DJILogHelper.getInstance().LOGI("bob", "GroupViewTag  configure" + aVar);
            if (aVar.equals(dji.playback.entryActivity.DJIPlaybackFragment.a.SelectOrPreview_SELECT)) {
                this.b.setVisibility(0);
                if (this.e.c(i)) {
                    this.b.setText(this.e.c.getResources().getString(R.string.v2_hg_cancel));
                } else {
                    this.b.setText(this.e.c.getResources().getString(R.string.v2_hg_select));
                }
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
                    CharSequence string = this.b.e.c.getResources().getString(R.string.v2_hg_select);
                    if (this.b.b.getText().equals(string)) {
                        this.b.b.setText(this.b.e.c.getResources().getString(R.string.v2_hg_cancel));
                        if (this.b.e.e != null) {
                            this.b.e.e.a(i);
                            return;
                        }
                        return;
                    }
                    this.b.b.setText(string);
                    if (this.b.e.e != null) {
                        this.b.e.e.b(i);
                    }
                }
            });
        }
    }

    public enum e {
        P_330,
        P_BALL,
        P_SELF,
        P_MANUAL,
        P_180,
        P_VERTICAL,
        P_WIDE
    }

    public g(Context context, DJIPlaybackFragment dJIPlaybackFragment, a aVar) {
        this.c = context;
        this.d = dJIPlaybackFragment;
        this.e = aVar;
        this.f = new Handler(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                int i = message.what;
                this.a.notifyDataSetChanged();
                super.handleMessage(message);
            }
        };
        this.g = new f(this.c);
        this.g.a(new a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a() {
                DJILogHelper.getInstance().LOGI("bob", "getLocationThread onFinished");
                TreeMap treeMap = new TreeMap(new dji.playback.entryActivity.d.c());
                for (Entry entry : this.a.a.entrySet()) {
                    if (treeMap.containsKey(entry.getKey())) {
                        ((List) treeMap.get(entry.getKey())).addAll((Collection) entry.getValue());
                    } else {
                        treeMap.put(entry.getKey(), entry.getValue());
                    }
                }
                for (List sort : treeMap.values()) {
                    Collections.sort(sort, new Comparator<h>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return a((h) obj, (h) obj2);
                        }

                        public int a(h hVar, h hVar2) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                return simpleDateFormat.parse(hVar2.e).compareTo(simpleDateFormat.parse(hVar.e));
                            } catch (ParseException e) {
                                e.printStackTrace();
                                return hVar.e.compareTo(hVar2.e);
                            }
                        }
                    });
                }
                this.a.a = treeMap;
                this.a.b = new ArrayList(treeMap.keySet());
                this.a.b();
            }
        });
    }

    public void a() {
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }

    public void b() {
        this.f.sendMessage(new Message());
    }

    public void a(TreeMap<dji.playback.entryActivity.d.a, List<h>> treeMap) {
        DJILogHelper.getInstance().LOGI("bob", "adapter setData size " + treeMap.size());
        for (List sort : treeMap.values()) {
            Collections.sort(sort, new Comparator<h>(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((h) obj, (h) obj2);
                }

                public int a(h hVar, h hVar2) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        return simpleDateFormat.parse(hVar2.e).compareTo(simpleDateFormat.parse(hVar.e));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return hVar.e.compareTo(hVar2.e);
                    }
                }
            });
        }
        this.a = treeMap;
        this.b = new ArrayList(treeMap.keySet());
        b();
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
        dji.playback.entryActivity.d.a aVar = (dji.playback.entryActivity.d.a) this.b.get(i);
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
        dji.playback.entryActivity.d.a aVar = (dji.playback.entryActivity.d.a) this.b.get(i);
        if (aVar != null) {
            List list = (List) this.a.get(aVar);
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((h) list.get(i2)).k = true;
            }
        }
        return true;
    }

    public boolean c() {
        for (Entry value : this.a.entrySet()) {
            List list = (List) value.getValue();
            for (int i = 0; i < list.size(); i++) {
                ((h) list.get(i)).k = false;
            }
        }
        return true;
    }

    public boolean b(int i) {
        if (i >= this.b.size()) {
            return false;
        }
        dji.playback.entryActivity.d.a aVar = (dji.playback.entryActivity.d.a) this.b.get(i);
        if (aVar != null) {
            List list = (List) this.a.get(aVar);
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((h) list.get(i2)).k = false;
            }
        }
        return true;
    }

    public boolean c(int i) {
        if (i >= this.b.size()) {
            return false;
        }
        dji.playback.entryActivity.d.a aVar = (dji.playback.entryActivity.d.a) this.b.get(i);
        if (aVar != null) {
            List list = (List) this.a.get(aVar);
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (!((h) list.get(i2)).k) {
                    return false;
                }
            }
        }
        return true;
    }

    public Object getGroup(int i) {
        if (this.b == null || i >= this.b.size()) {
            return null;
        }
        dji.playback.entryActivity.d.a aVar = (dji.playback.entryActivity.d.a) this.b.get(i);
        if (aVar == null) {
            return null;
        }
        return aVar;
    }

    public Object getChild(int i, int i2) {
        if (i >= this.b.size()) {
            return null;
        }
        dji.playback.entryActivity.d.a aVar = (dji.playback.entryActivity.d.a) this.b.get(i);
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
            view = LayoutInflater.from(this.c).inflate(R.layout.v2_hgplayback_groupitem, null);
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
                dVar.a(this.d.d(), (dji.playback.entryActivity.d.a) tag, i);
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
            view = LayoutInflater.from(this.c).inflate(R.layout.v2_hg_childitems, null);
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
        DJILogHelper.getInstance().LOGI("bob", "groupPosition = " + i + "  childPosition=" + i2);
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

    private static String b(File file, dji.thirdparty.g.b.b.a.e eVar) {
        i a;
        try {
            a = dji.thirdparty.g.g.a(file);
        } catch (dji.thirdparty.g.e e) {
            e.printStackTrace();
            a = null;
        } catch (IOException e2) {
            e2.printStackTrace();
            a = null;
        }
        if (a == null) {
            return null;
        }
        dji.thirdparty.g.b.b.e a2;
        if (a instanceof dji.thirdparty.g.b.a.b) {
            String str;
            dji.thirdparty.g.b.a.b bVar = (dji.thirdparty.g.b.a.b) a;
            if (bVar != null) {
                a2 = bVar.a(eVar);
                if (a2 != null) {
                    try {
                        str = (String) a2.i();
                    } catch (dji.thirdparty.g.e e3) {
                        e3.printStackTrace();
                    }
                    return str;
                }
            }
            str = null;
            return str;
        }
        dji.thirdparty.g.b.b.g gVar = (dji.thirdparty.g.b.b.g) a;
        if (gVar == null) {
            return null;
        }
        try {
            a2 = gVar.a(eVar);
            if (a2 != null) {
                return a2.j();
            }
            return null;
        } catch (dji.thirdparty.g.e e32) {
            e32.printStackTrace();
            return null;
        }
    }

    public static Bitmap a(String str) {
        if (str == null) {
            return null;
        }
        Bitmap a = dji.pilot2.media.e.getInstance().a(str);
        if (a != null || !new File(str).exists()) {
            return a;
        }
        Options options = new Options();
        options.inSampleSize = 1;
        a = BitmapFactory.decodeFile(str, options);
        if (a == null) {
            return a;
        }
        dji.pilot2.media.e.getInstance().a(str, a);
        return a;
    }
}
