package dji.pilot.gallery.entryActivity;

import android.content.Context;
import android.os.AsyncTask;
import dji.log.DJILogHelper;
import dji.pilot.gallery.entryActivity.d.d;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class e implements dji.pilot.gallery.entryActivity.a.a {
    private dji.pilot.gallery.entryActivity.a.b a;
    private d b;
    private c c;
    private String d;
    private TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> e;

    public enum a {
        EventHGPhotoCreate(0),
        EventHGPhotoDel(1);
        
        private int c;

        private a(int i) {
            this.c = i;
        }
    }

    public static class b {
    }

    public class c extends AsyncTask<Void, TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>>, TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>>> {
        final /* synthetic */ e a;

        public c(e eVar) {
            this.a = eVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((TreeMap) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((TreeMap[]) objArr);
        }

        protected TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> a(Void... voidArr) {
            if (this.a.d == null) {
                return null;
            }
            this.a.b.a(this.a.d);
            return this.a.b.d();
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.a.a.a(true);
        }

        protected void a(TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap) {
            super.onPostExecute(treeMap);
            if (treeMap != null) {
                DJILogHelper.getInstance().LOGI("bob", " scane all items size = " + treeMap.size());
                for (List size : treeMap.values()) {
                    DJILogHelper.getInstance().LOGI("bob", " scane all items 1 size = " + size.size());
                }
                this.a.e = treeMap;
                if (this.a.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_ALL) {
                    this.a.a.a(this.a.e);
                } else if (this.a.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_PIC) {
                    this.a.a.a(this.a.e());
                } else if (this.a.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_VIDEO) {
                    this.a.a.a(this.a.f());
                } else if (this.a.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_ALBUM) {
                    this.a.a.a(this.a.i());
                }
                this.a.a.a(false);
                DJILogHelper.getInstance().LOGI("bob", "ScanerTask onPostExecute");
            }
        }

        protected void a(TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>>... treeMapArr) {
            super.onProgressUpdate(treeMapArr);
        }
    }

    public e(dji.pilot.gallery.entryActivity.a.b bVar, String str) {
        this.a = bVar;
        this.d = str;
    }

    public void a(Context context) {
        this.b = d.getInstance(context);
        this.b.a(new d(this) {
            boolean a = false;
            final /* synthetic */ e b;

            {
                this.b = r2;
            }

            public void a() {
                if (this.b.a != null && this.a) {
                    if (this.b.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_ALL) {
                        this.b.a.a(this.b.b.d());
                    } else if (this.b.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_PIC) {
                        this.b.a.a(this.b.b.c());
                    } else if (this.b.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_VIDEO) {
                        this.b.a.a(this.b.b.b());
                    } else if (this.b.a.a() == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_ALBUM) {
                        this.b.a.a(this.b.b.a());
                    }
                    this.a = true;
                }
                this.b.b.a(null);
            }
        });
        this.c = new c(this);
        this.c.execute(new Void[0]);
    }

    private boolean b(g gVar) {
        for (List<g> it : this.e.values()) {
            for (g equals : it) {
                if (equals.equals(gVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int a() {
        int i = 0;
        for (Entry value : this.e.entrySet()) {
            for (g gVar : (List) value.getValue()) {
                if (gVar.k) {
                    i++;
                }
            }
        }
        return i;
    }

    public List<g> h() {
        List<g> arrayList = new ArrayList();
        for (Entry value : this.e.entrySet()) {
            for (g gVar : (List) value.getValue()) {
                if (gVar.k) {
                    arrayList.add(gVar);
                }
            }
        }
        return arrayList;
    }

    public boolean b() {
        dji.pilot.gallery.entryActivity.d.b bVar = null;
        int i = 0;
        for (Entry value : this.e.entrySet()) {
            for (g gVar : (List) value.getValue()) {
                dji.pilot.gallery.entryActivity.d.b bVar2;
                int i2;
                if (gVar.k) {
                    i++;
                    if (bVar == null) {
                        bVar2 = gVar.h;
                        i2 = i;
                        i = i2;
                        bVar = bVar2;
                    } else if (gVar.h != bVar) {
                        return false;
                    } else {
                        if (gVar.h == dji.pilot.gallery.entryActivity.d.b.Type_IMG && i > 1) {
                            return false;
                        }
                    }
                }
                bVar2 = bVar;
                i2 = i;
                i = i2;
                bVar = bVar2;
            }
        }
        if (i != 0) {
            return true;
        }
        return false;
    }

    public boolean c() {
        for (Entry value : this.e.entrySet()) {
            for (g gVar : (List) value.getValue()) {
                if (gVar.k) {
                    gVar.k = false;
                }
            }
        }
        return true;
    }

    private void c(g gVar) {
        if (gVar.c != null) {
            File file = new File(gVar.c);
            if (file.exists() && !file.isDirectory()) {
                file.delete();
            }
            DJILogHelper.getInstance().LOGI("bob", "deleteFile path=" + gVar.c);
        }
    }

    public boolean d() {
        Iterator it = this.e.entrySet().iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            List list = (List) ((Entry) it.next()).getValue();
            Iterator it2 = list.iterator();
            boolean z3 = z2;
            z2 = z;
            while (it2.hasNext()) {
                g gVar = (g) it2.next();
                if (gVar.h.equals(dji.pilot.gallery.entryActivity.d.b.Type_IMG)) {
                    z2 = true;
                }
                if (gVar.h.equals(dji.pilot.gallery.entryActivity.d.b.Type_VIDEO)) {
                    z3 = true;
                }
                if (gVar.k) {
                    c(gVar);
                    it2.remove();
                }
            }
            if (list.size() == 0) {
                DJILogHelper.getInstance().LOGI("bob", "deleteSelected delete group");
                it.remove();
            }
            z = z2;
            z2 = z3;
        }
        if (z2) {
            dji.thirdparty.a.c.a().e(new b());
        }
        if (z) {
            dji.thirdparty.a.c.a().e(a.EventHGPhotoDel);
        }
        return true;
    }

    private boolean d(g gVar) {
        for (List<g> it : this.e.values()) {
            for (g gVar2 : it) {
                if (gVar2.equals(gVar)) {
                    if (((List) this.e.get(new dji.pilot.gallery.entryActivity.d.a(gVar2.f, gVar2.g, gVar2.e))).remove(gVar)) {
                        return true;
                    }
                    DJILogHelper.getInstance().LOGI("bob", "findAndDelete delete error");
                    return false;
                }
            }
        }
        return false;
    }

    public void a(g gVar) {
        if (d(gVar)) {
            File file = new File(gVar.c);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void a(ArrayList<g> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a((g) it.next());
        }
    }

    public void a(dji.pilot.gallery.entryActivity.DJIGalleryFragment.b bVar) {
        if (this.a == null) {
            return;
        }
        if (bVar == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_ALL) {
            this.a.a(this.e);
        } else if (bVar == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_PIC) {
            this.a.a(e());
        } else if (bVar == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_VIDEO) {
            this.a.a(f());
        } else if (bVar == dji.pilot.gallery.entryActivity.DJIGalleryFragment.b.SHOW_MODE_ALBUM) {
            this.a.a(i());
        }
    }

    public List<g> g() {
        List<g> arrayList = new ArrayList();
        for (Entry value : this.e.entrySet()) {
            List list = (List) value.getValue();
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> e() {
        TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap = new TreeMap(new dji.pilot.gallery.entryActivity.d.c());
        if (this.e == null) {
            this.c.cancel(true);
            this.c.execute(new Void[0]);
            return treeMap;
        }
        for (Entry entry : this.e.entrySet()) {
            Object obj = null;
            for (g gVar : (List) entry.getValue()) {
                if (gVar.h == dji.pilot.gallery.entryActivity.d.b.Type_IMG) {
                    if (obj == null) {
                        obj = new ArrayList();
                    }
                    obj.add(gVar);
                }
            }
            if (obj != null) {
                treeMap.put(entry.getKey(), obj);
            }
        }
        return treeMap;
    }

    public TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> f() {
        TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap = new TreeMap(new dji.pilot.gallery.entryActivity.d.c());
        if (this.e == null) {
            this.c.cancel(true);
            this.c.execute(new Void[0]);
            return treeMap;
        }
        for (Entry entry : this.e.entrySet()) {
            Object obj = null;
            for (g gVar : (List) entry.getValue()) {
                if (gVar.h == dji.pilot.gallery.entryActivity.d.b.Type_VIDEO) {
                    DJILogHelper.getInstance().LOGI("bob", "getVideos catch video");
                    if (obj == null) {
                        obj = new ArrayList();
                    }
                    obj.add(gVar);
                }
            }
            if (obj != null) {
                treeMap.put(entry.getKey(), obj);
            }
        }
        return treeMap;
    }

    public TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> i() {
        TreeMap<dji.pilot.gallery.entryActivity.d.a, List<g>> treeMap = new TreeMap(new dji.pilot.gallery.entryActivity.d.c());
        if (this.e == null) {
            this.c.cancel(true);
            this.c.execute(new Void[0]);
            return treeMap;
        }
        for (Entry value : this.e.entrySet()) {
            for (g gVar : (List) value.getValue()) {
                dji.pilot.gallery.entryActivity.d.a aVar = new dji.pilot.gallery.entryActivity.d.a("", "", d.d(gVar.c));
                if (treeMap.containsKey(aVar)) {
                    ((List) treeMap.get(aVar)).add(gVar);
                } else {
                    List arrayList = new ArrayList();
                    arrayList.add(gVar);
                    treeMap.put(aVar, arrayList);
                }
            }
        }
        return treeMap;
    }
}
