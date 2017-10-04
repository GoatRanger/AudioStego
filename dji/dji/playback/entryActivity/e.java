package dji.playback.entryActivity;

import android.content.Context;
import android.os.AsyncTask;
import dji.log.DJILogHelper;
import dji.playback.entryActivity.d.d;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class e implements dji.playback.entryActivity.a.a {
    private dji.playback.entryActivity.a.b a;
    private d b;
    private c c;
    private String[] d;
    private TreeMap<dji.playback.entryActivity.d.a, List<h>> e;
    private Context f;

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

    public class c extends AsyncTask<Void, TreeMap<dji.playback.entryActivity.d.a, List<h>>, TreeMap<dji.playback.entryActivity.d.a, List<h>>> {
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

        protected TreeMap<dji.playback.entryActivity.d.a, List<h>> a(Void... voidArr) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.a.d == null) {
                return null;
            }
            this.a.b.a(this.a.d);
            DJILogHelper.getInstance().LOGI("bob", "loading time doinbackground time = " + (System.currentTimeMillis() - currentTimeMillis));
            return this.a.b.c();
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (this.a.a != null) {
                this.a.a.b();
            }
        }

        protected void a(TreeMap<dji.playback.entryActivity.d.a, List<h>> treeMap) {
            super.onPostExecute(treeMap);
            if (this.a.a != null) {
                this.a.a.c();
            }
            if (treeMap != null) {
                DJILogHelper.getInstance().LOGI("bob", " scane all items size = " + treeMap.size());
                for (List size : treeMap.values()) {
                    DJILogHelper.getInstance().LOGI("bob", " scane all items 1 size = " + size.size());
                }
                this.a.e = treeMap;
                if (this.a.a.a() == dji.playback.entryActivity.DJIPlaybackFragment.b.SHOW_MODE_ALL) {
                    this.a.a.a(this.a.e);
                } else if (this.a.a.a() == dji.playback.entryActivity.DJIPlaybackFragment.b.SHOW_MODE_PIC) {
                    this.a.a.a(this.a.e());
                } else {
                    this.a.a.a(this.a.f());
                }
                DJILogHelper.getInstance().LOGI("bob", "ScanerTask onPostExecute");
            }
        }

        protected void a(TreeMap<dji.playback.entryActivity.d.a, List<h>>... treeMapArr) {
            super.onProgressUpdate(treeMapArr);
        }
    }

    public e(dji.playback.entryActivity.a.b bVar, String[] strArr) {
        this.a = bVar;
        this.d = strArr;
    }

    public void a(Context context) {
        this.f = context;
        this.b = d.getInstance(context);
        this.b.a(new d(this) {
            boolean a = false;
            final /* synthetic */ e b;

            {
                this.b = r2;
            }

            public void a() {
                if (this.b.a != null && this.a) {
                    if (this.b.a.a() == dji.playback.entryActivity.DJIPlaybackFragment.b.SHOW_MODE_ALL) {
                        this.b.a.a(this.b.b.c());
                    } else if (this.b.a.a() == dji.playback.entryActivity.DJIPlaybackFragment.b.SHOW_MODE_PIC) {
                        this.b.a.a(this.b.b.b());
                    } else {
                        this.b.a.a(this.b.b.a());
                    }
                    this.a = true;
                }
                this.b.b.a(null);
            }
        });
        this.c = new c(this);
        this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private boolean b(h hVar) {
        if (this.e != null) {
            for (List<h> it : this.e.values()) {
                for (h equals : it) {
                    if (equals.equals(hVar)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int a() {
        if (this.e == null) {
            return 0;
        }
        int i = 0;
        for (Entry value : this.e.entrySet()) {
            for (h hVar : (List) value.getValue()) {
                if (hVar.k) {
                    i++;
                }
            }
        }
        return i;
    }

    public List<h> h() {
        List<h> arrayList = new ArrayList();
        for (Entry value : this.e.entrySet()) {
            for (h hVar : (List) value.getValue()) {
                if (hVar.k) {
                    arrayList.add(hVar);
                }
            }
        }
        return arrayList;
    }

    public boolean b() {
        dji.playback.entryActivity.d.b bVar = null;
        int i = 0;
        for (Entry value : this.e.entrySet()) {
            for (h hVar : (List) value.getValue()) {
                dji.playback.entryActivity.d.b bVar2;
                int i2;
                if (hVar.k) {
                    i++;
                    if (bVar == null) {
                        bVar2 = hVar.h;
                        i2 = i;
                        i = i2;
                        bVar = bVar2;
                    } else if (hVar.h != bVar) {
                        return false;
                    } else {
                        if (hVar.h == dji.playback.entryActivity.d.b.Type_IMG && i > 1) {
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
            for (h hVar : (List) value.getValue()) {
                if (hVar.k) {
                    hVar.k = false;
                }
            }
        }
        return true;
    }

    private void c(h hVar) {
        if (hVar.c != null) {
            dji.pilot.storage.a.b(this.f, hVar.c);
            DJILogHelper.getInstance().LOGI("bob", "deleteFile path=" + hVar.c);
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
                h hVar = (h) it2.next();
                if (hVar.h.equals(dji.playback.entryActivity.d.b.Type_IMG)) {
                    z2 = true;
                }
                if (hVar.h.equals(dji.playback.entryActivity.d.b.Type_VIDEO)) {
                    z3 = true;
                }
                if (hVar.k) {
                    c(hVar);
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

    private boolean d(h hVar) {
        for (List<h> it : this.e.values()) {
            for (h hVar2 : it) {
                if (hVar2.equals(hVar)) {
                    if (((List) this.e.get(new dji.playback.entryActivity.d.a(hVar2.f, hVar2.g, hVar2.e))).remove(hVar)) {
                        return true;
                    }
                    DJILogHelper.getInstance().LOGI("bob", "findAndDelete delete error");
                    return false;
                }
            }
        }
        return false;
    }

    public void a(h hVar) {
        if (d(hVar)) {
            File file = new File(hVar.c);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void a(ArrayList<h> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a((h) it.next());
        }
    }

    public void a(dji.playback.entryActivity.DJIPlaybackFragment.b bVar) {
        if (this.a == null) {
            return;
        }
        if (bVar == dji.playback.entryActivity.DJIPlaybackFragment.b.SHOW_MODE_ALL) {
            this.a.a(this.e);
        } else if (bVar == dji.playback.entryActivity.DJIPlaybackFragment.b.SHOW_MODE_PIC) {
            this.a.a(e());
        } else {
            this.a.a(f());
        }
    }

    public List<h> g() {
        List<h> arrayList = new ArrayList();
        for (Entry value : this.e.entrySet()) {
            List list = (List) value.getValue();
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public TreeMap<dji.playback.entryActivity.d.a, List<h>> e() {
        TreeMap<dji.playback.entryActivity.d.a, List<h>> treeMap = new TreeMap(new dji.playback.entryActivity.d.c());
        for (Entry entry : this.e.entrySet()) {
            Object obj = null;
            for (h hVar : (List) entry.getValue()) {
                if (hVar.h == dji.playback.entryActivity.d.b.Type_IMG) {
                    if (obj == null) {
                        obj = new ArrayList();
                    }
                    obj.add(hVar);
                }
            }
            if (obj != null) {
                treeMap.put(entry.getKey(), obj);
            }
        }
        return treeMap;
    }

    public TreeMap<dji.playback.entryActivity.d.a, List<h>> f() {
        TreeMap<dji.playback.entryActivity.d.a, List<h>> treeMap = new TreeMap(new dji.playback.entryActivity.d.c());
        for (Entry entry : this.e.entrySet()) {
            Object obj = null;
            for (h hVar : (List) entry.getValue()) {
                if (hVar.h == dji.playback.entryActivity.d.b.Type_VIDEO) {
                    DJILogHelper.getInstance().LOGI("bob", "getVideos catch video");
                    if (obj == null) {
                        obj = new ArrayList();
                    }
                    obj.add(hVar);
                }
            }
            if (obj != null) {
                treeMap.put(entry.getKey(), obj);
            }
        }
        return treeMap;
    }
}
