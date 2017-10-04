package dji.pilot2.mine.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.main.fragment.DJIMineFragment;
import dji.pilot2.mine.db.DraftBean;
import dji.pilot2.mine.db.PenddingAddDraft;
import dji.pilot2.utils.m;
import dji.pilot2.utils.n;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class c {
    private static volatile c a;
    private List<DraftBean> b;
    private final List<dji.pilot2.mine.e.b> c = new ArrayList();
    private final List<b> d = new ArrayList();
    private boolean e;
    private ConcurrentHashMap<String, WeakReference<dji.pilot2.mine.a.c.a>> f = new ConcurrentHashMap();

    public interface b {
        void a();
    }

    public class a extends AsyncTask<List<PenddingAddDraft>, String, Boolean> {
        final /* synthetic */ c a;

        public a(c cVar) {
            this.a = cVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((List[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((String[]) objArr);
        }

        protected Boolean a(List<PenddingAddDraft>... listArr) {
            Context a = dji.pilot2.b.a.a();
            for (PenddingAddDraft penddingAddDraft : listArr[0]) {
                if (new File(penddingAddDraft.getFilePath()).exists() && new File(penddingAddDraft.getFilePath().substring(0, penddingAddDraft.getFilePath().length() - 5)).exists()) {
                    dji.pilot2.share.mode.a aVar = new dji.pilot2.share.mode.a(penddingAddDraft.getFilePath());
                    if (aVar != null && aVar.D == 0) {
                        String substring = aVar.G.substring(0, aVar.G.length() - 5);
                        String str = aVar.E;
                        String str2 = aVar.F;
                        String str3 = "video";
                        DJILogHelper.getInstance().LOGI("Lyric", "penddingDraft: " + substring + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str3);
                        this.a.a(substring, str, str2, "", str3);
                    }
                }
                com.dji.frame.c.c.c(a).f(penddingAddDraft);
            }
            return Boolean.valueOf(true);
        }

        protected void a(String... strArr) {
            super.onProgressUpdate(strArr);
        }

        protected void a(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue() && DJIMineFragment.P != null) {
                DJIMineFragment.P.sendEmptyMessage(2);
            }
        }
    }

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            if (!a.e) {
                a.d();
            }
            cVar = a;
        }
        return cVar;
    }

    private static void d(dji.pilot2.mine.e.b bVar) {
        Bitmap createScaledBitmap;
        Exception exception;
        dji.pilot2.media.c cVar;
        Bitmap a;
        Object obj;
        FileOutputStream fileOutputStream;
        OutputStream fileOutputStream2;
        OutputStream outputStream;
        Throwable th;
        long j = 0;
        FileOutputStream fileOutputStream3 = null;
        String e = bVar.e();
        String d = bVar.d();
        String str = dji.pilot2.b.a.a().getExternalCacheDir() + d.t;
        if (!(bVar.e() == null || bVar.e().equals(""))) {
            j = new File(bVar.e()).lastModified();
        }
        bVar.a(j);
        if (bVar.n() == null) {
            bVar.h("");
            bVar.g("0");
            Bitmap frameAtTime;
            if (d.equals("video")) {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever.setDataSource(e);
                    frameAtTime = mediaMetadataRetriever.getFrameAtTime();
                    createScaledBitmap = Bitmap.createScaledBitmap(frameAtTime, 1024, (frameAtTime.getHeight() * 1024) / frameAtTime.getWidth(), false);
                    try {
                        frameAtTime.recycle();
                        bVar.g(m.a(Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) + 500));
                        try {
                            mediaMetadataRetriever.release();
                        } catch (RuntimeException e2) {
                        }
                    } catch (Exception e3) {
                        Exception exception2 = e3;
                        Object obj2 = createScaledBitmap;
                        exception = exception2;
                        try {
                            exception.printStackTrace();
                            cVar = new dji.pilot2.media.c();
                            cVar.a(e);
                            a = cVar.a(0);
                        } catch (NullPointerException e4) {
                            e4.printStackTrace();
                            a = null;
                        } catch (Throwable th2) {
                            try {
                                mediaMetadataRetriever.release();
                            } catch (RuntimeException e5) {
                            }
                        }
                        if (a == null) {
                            obj = fileOutputStream;
                        } else {
                            createScaledBitmap = Bitmap.createScaledBitmap(a, 1024, (a.getHeight() * 1024) / a.getWidth(), false);
                            a.recycle();
                        }
                        try {
                            bVar.g(m.a((long) (cVar.a() + 500)));
                        } catch (NullPointerException e6) {
                            e6.printStackTrace();
                        }
                        try {
                            mediaMetadataRetriever.release();
                        } catch (RuntimeException e7) {
                        }
                        if (createScaledBitmap == null) {
                            try {
                                d = str + com.dji.frame.c.a.a(e) + dji.pilot2.main.a.a.n;
                                fileOutputStream2 = new FileOutputStream(d);
                                try {
                                    createScaledBitmap.compress(CompressFormat.JPEG, 80, fileOutputStream2);
                                    createScaledBitmap.recycle();
                                    bVar.h(d);
                                    if (fileOutputStream2 == null) {
                                        try {
                                            fileOutputStream2.close();
                                        } catch (IOException e8) {
                                            e8.printStackTrace();
                                            return;
                                        }
                                    }
                                } catch (Exception e9) {
                                    exception = e9;
                                    outputStream = fileOutputStream2;
                                    try {
                                        exception.printStackTrace();
                                        if (fileOutputStream3 != null) {
                                            try {
                                                fileOutputStream3.close();
                                                return;
                                            } catch (IOException e82) {
                                                e82.printStackTrace();
                                                return;
                                            }
                                        }
                                        return;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        if (fileOutputStream3 != null) {
                                            try {
                                                fileOutputStream3.close();
                                            } catch (IOException e10) {
                                                e10.printStackTrace();
                                            }
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    outputStream = fileOutputStream2;
                                    if (fileOutputStream3 != null) {
                                        fileOutputStream3.close();
                                    }
                                    throw th;
                                }
                            } catch (Exception e11) {
                                exception = e11;
                                exception.printStackTrace();
                                if (fileOutputStream3 != null) {
                                    fileOutputStream3.close();
                                    return;
                                }
                                return;
                            }
                        }
                    }
                } catch (Exception e12) {
                    exception = e12;
                    fileOutputStream = null;
                    exception.printStackTrace();
                    cVar = new dji.pilot2.media.c();
                    cVar.a(e);
                    a = cVar.a(0);
                    if (a == null) {
                        createScaledBitmap = Bitmap.createScaledBitmap(a, 1024, (a.getHeight() * 1024) / a.getWidth(), false);
                        a.recycle();
                    } else {
                        obj = fileOutputStream;
                    }
                    bVar.g(m.a((long) (cVar.a() + 500)));
                    mediaMetadataRetriever.release();
                    if (createScaledBitmap == null) {
                        d = str + com.dji.frame.c.a.a(e) + dji.pilot2.main.a.a.n;
                        fileOutputStream2 = new FileOutputStream(d);
                        createScaledBitmap.compress(CompressFormat.JPEG, 80, fileOutputStream2);
                        createScaledBitmap.recycle();
                        bVar.h(d);
                        if (fileOutputStream2 == null) {
                            fileOutputStream2.close();
                        }
                    }
                }
            }
            if (d.equals("photo")) {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(e, options);
                int i = options.outWidth;
                int i2 = (options.outHeight * 1024) / i;
                options.inSampleSize = i / 1024;
                options.inJustDecodeBounds = false;
                frameAtTime = BitmapFactory.decodeFile(e, options);
                if (frameAtTime != null) {
                    createScaledBitmap = Bitmap.createScaledBitmap(frameAtTime, 1024, i2, false);
                    frameAtTime.recycle();
                }
            }
            createScaledBitmap = null;
            if (createScaledBitmap == null) {
                d = str + com.dji.frame.c.a.a(e) + dji.pilot2.main.a.a.n;
                fileOutputStream2 = new FileOutputStream(d);
                createScaledBitmap.compress(CompressFormat.JPEG, 80, fileOutputStream2);
                createScaledBitmap.recycle();
                bVar.h(d);
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            }
        }
    }

    private void d() {
        Exception exception;
        List list;
        Exception exception2;
        File file;
        this.e = true;
        List list2 = null;
        try {
            com.dji.frame.c.c.c(dji.pilot2.b.a.a()).a(DraftBean.class, "status=4");
            this.b = com.dji.frame.c.c.c(dji.pilot2.b.a.a()).c(DraftBean.class, "status=1 OR status=16");
            list2 = com.dji.frame.c.c.c(dji.pilot2.b.a.a()).c(PenddingAddDraft.class);
            try {
                if (this.b.size() == 0 && list2.size() == 0) {
                    f();
                    list2 = com.dji.frame.c.c.c(dji.pilot2.b.a.a()).c(PenddingAddDraft.class);
                }
                Collection arrayList = new ArrayList();
                for (DraftBean draftBean : this.b) {
                    if (draftBean.getStatus() == 4) {
                        arrayList.add(draftBean);
                    }
                }
                this.b.removeAll(arrayList);
            } catch (Exception e) {
                exception = e;
                list = list2;
                exception2 = exception;
                exception2.printStackTrace();
                this.b = new ArrayList();
                list2 = list;
                for (DraftBean draftBean2 : this.b) {
                    if (!"0".equals(draftBean2.getCreateTime())) {
                    }
                    file = new File(draftBean2.getFilePath());
                    if (!file.exists()) {
                        draftBean2.setCreateTime(Long.toString(file.lastModified()));
                        com.dji.frame.c.c.c(dji.pilot2.b.a.a()).e(draftBean2);
                    }
                    e(new dji.pilot2.mine.e.b(draftBean2));
                }
                if (list2 != null) {
                    return;
                }
            }
        } catch (Exception e2) {
            exception = e2;
            list = list2;
            exception2 = exception;
            exception2.printStackTrace();
            this.b = new ArrayList();
            list2 = list;
            for (DraftBean draftBean22 : this.b) {
                if ("0".equals(draftBean22.getCreateTime())) {
                }
                file = new File(draftBean22.getFilePath());
                if (!file.exists()) {
                    draftBean22.setCreateTime(Long.toString(file.lastModified()));
                    com.dji.frame.c.c.c(dji.pilot2.b.a.a()).e(draftBean22);
                }
                e(new dji.pilot2.mine.e.b(draftBean22));
            }
            if (list2 != null) {
            }
            return;
        }
        for (DraftBean draftBean222 : this.b) {
            if ("0".equals(draftBean222.getCreateTime()) || draftBean222.getCreateTime() == null || draftBean222.getCreateTime().equals("")) {
                file = new File(draftBean222.getFilePath());
                if (!file.exists()) {
                    draftBean222.setCreateTime(Long.toString(file.lastModified()));
                    com.dji.frame.c.c.c(dji.pilot2.b.a.a()).e(draftBean222);
                }
            }
            e(new dji.pilot2.mine.e.b(draftBean222));
        }
        if (list2 != null && list2.size() > 0) {
            new a(this).execute(new List[]{list2});
        }
    }

    private c() {
    }

    public synchronized boolean a(dji.pilot2.mine.e.b bVar) {
        return a(bVar.a(), bVar.e());
    }

    public synchronized boolean a(String str, String str2) {
        boolean z;
        for (int i = 0; i < this.c.size(); i++) {
            if (((dji.pilot2.mine.e.b) this.c.get(i)).e().equals(str2)) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public synchronized void a(String str, String str2, String str3, String str4, String str5) {
        dji.pilot2.mine.e.b bVar = new dji.pilot2.mine.e.b(f.getInstance().j(), str, str2, str3, str5);
        bVar.i(str4);
        b(bVar);
    }

    public synchronized void b(dji.pilot2.mine.e.b bVar) {
        if (bVar.a() == null || bVar.a().equals("")) {
            bVar.a(f.getInstance().j());
        }
        dji.pilot2.mine.e.b b = b(bVar.e());
        DraftBean draftBean;
        if (b == null) {
            d(bVar);
            e(bVar);
            draftBean = new DraftBean(bVar.a(), bVar.e(), bVar.b(), bVar.c(), bVar.d(), bVar.g(), bVar.n(), bVar.l(), Long.toString(bVar.k()));
            draftBean.setTag(bVar.p());
            this.b.add(0, draftBean);
            com.dji.frame.c.c.c(dji.pilot2.b.a.a()).a(draftBean);
            for (b a : this.d) {
                a.a();
            }
        } else if (b.a().equalsIgnoreCase(bVar.a())) {
            b.b(bVar.b());
            b.c(bVar.c());
            b.i(bVar.p());
            draftBean = c(b.e());
            if (draftBean != null) {
                draftBean.setTitle(b.b());
                draftBean.setDescription(b.c());
                draftBean.setTag(b.p());
                com.dji.frame.c.c.c(dji.pilot2.b.a.a()).e(draftBean);
            }
        } else {
            c(b);
            draftBean = new DraftBean(bVar.a(), b.e(), bVar.b(), bVar.c(), bVar.d(), b.g(), b.n(), b.l(), Long.toString(b.k()));
            draftBean.setTag(bVar.p());
            e(new dji.pilot2.mine.e.b(draftBean));
            this.b.add(0, draftBean);
            com.dji.frame.c.c.c(dji.pilot2.b.a.a()).a(draftBean);
        }
    }

    public void a(String str) {
        if (str == null || str.equals("")) {
            this.e = false;
            this.b.clear();
            this.c.clear();
            this.f.clear();
            return;
        }
        d();
    }

    public synchronized int a(int i) {
        int i2;
        i2 = 0;
        for (dji.pilot2.mine.e.b g : this.c) {
            int i3;
            if (g.g() == i) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i2 = i3;
        }
        return i2;
    }

    public synchronized dji.pilot2.mine.e.b b(String str) {
        dji.pilot2.mine.e.b bVar;
        for (int i = 0; i < this.c.size(); i++) {
            if (((dji.pilot2.mine.e.b) this.c.get(i)).e().equals(str)) {
                bVar = (dji.pilot2.mine.e.b) this.c.get(i);
                break;
            }
        }
        bVar = null;
        return bVar;
    }

    public synchronized DraftBean c(String str) {
        DraftBean draftBean;
        for (int i = 0; i < this.b.size(); i++) {
            if (((DraftBean) this.b.get(i)).getFilePath().equals(str)) {
                draftBean = (DraftBean) this.b.get(i);
                break;
            }
        }
        draftBean = null;
        return draftBean;
    }

    private synchronized dji.pilot2.mine.e.b e() {
        dji.pilot2.mine.e.b bVar;
        for (dji.pilot2.mine.e.b bVar2 : this.c) {
            if (bVar2.g() == 4) {
                break;
            }
        }
        bVar2 = null;
        return bVar2;
    }

    public synchronized void c(dji.pilot2.mine.e.b bVar) {
        this.c.remove(bVar);
        DraftBean c = c(bVar.e());
        if (c != null) {
            com.dji.frame.c.c.c(dji.pilot2.b.a.a()).f(c);
            for (b a : this.d) {
                a.a();
            }
        }
    }

    public synchronized void a() {
        while (true) {
            dji.pilot2.mine.e.b e = e();
            if (e == null) {
                break;
            }
            this.c.remove(e);
            DraftBean c = c(e.e());
            if (c != null) {
                com.dji.frame.c.c.c(dji.pilot2.b.a.a()).f(c);
            }
        }
        for (b a : this.d) {
            a.a();
        }
    }

    public dji.pilot2.mine.e.b b(int i) {
        return (dji.pilot2.mine.e.b) this.c.get(i);
    }

    public int b() {
        return this.c.size();
    }

    public void a(String str, dji.pilot2.mine.a.c.a aVar) {
        this.f.put(str, new WeakReference(aVar));
    }

    public void d(String str) {
        this.f.remove(str);
    }

    public dji.pilot2.mine.a.c.a e(String str) {
        if (this.f.containsKey(str)) {
            return (dji.pilot2.mine.a.c.a) ((WeakReference) this.f.get(str)).get();
        }
        return null;
    }

    public void c() {
        this.f.clear();
    }

    public void a(b bVar) {
        if (!this.d.contains(bVar)) {
            this.d.add(bVar);
        }
    }

    public void b(b bVar) {
        this.d.remove(bVar);
    }

    private void f() {
        Context a = dji.pilot2.b.a.a();
        File file = new File(n.f(a));
        File[] fileArr = null;
        if (file != null && file.exists()) {
            fileArr = file.listFiles(new FilenameFilter(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public boolean accept(File file, String str) {
                    if (str.endsWith(".info")) {
                        return true;
                    }
                    return false;
                }
            });
        }
        for (File file2 : r0) {
            PenddingAddDraft penddingAddDraft = new PenddingAddDraft();
            penddingAddDraft.setFilePath(file2.getAbsolutePath());
            com.dji.frame.c.c.c(a).a(penddingAddDraft);
        }
    }

    private synchronized void e(dji.pilot2.mine.e.b bVar) {
        int i = 0;
        while (i < this.c.size() && bVar.k() < ((dji.pilot2.mine.e.b) this.c.get(i)).k()) {
            i++;
        }
        this.c.add(i, bVar);
    }
}
