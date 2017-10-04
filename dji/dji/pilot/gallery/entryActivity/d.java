package dji.pilot.gallery.entryActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Media;
import android.provider.MediaStore.Video.Thumbnails;
import android.text.format.DateFormat;
import android.util.Log;
import com.dji.frame.c.f;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class d {
    private static d a;
    private Object b = new Object();
    private ArrayList<g> c = new ArrayList();
    private String d;
    private Context e;
    private ContentResolver f;
    private d g;
    private Map<a, List<g>> h = new TreeMap(new c());
    private Map<a, List<g>> i = new TreeMap(new c());
    private Map<a, List<g>> j = new TreeMap(new c());

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;

        public a(String str, String str2, String str3) {
            this.a = str;
            this.c = str3;
            this.b = str2;
        }

        public int hashCode() {
            return (this.a.hashCode() + this.c.hashCode()) + this.b.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (!this.c.equals(aVar.c)) {
                return false;
            }
            if (this.d != null && !this.d.equals(aVar.d)) {
                return false;
            }
            if (this.e == null || this.e.equals(aVar.e)) {
                return true;
            }
            return false;
        }
    }

    public enum b {
        Type_IMG,
        Type_VIDEO
    }

    public static class c implements Comparator<a> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((a) obj, (a) obj2);
        }

        public int a(a aVar, a aVar2) {
            int compareTo;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                compareTo = simpleDateFormat.parse(aVar2.c).compareTo(simpleDateFormat.parse(aVar.c));
            } catch (ParseException e) {
                e.printStackTrace();
                compareTo = aVar.c.compareTo(aVar2.c);
            }
            if (compareTo != 0) {
                return compareTo;
            }
            if (aVar.d == null || aVar2.d == null) {
                return (aVar.d == null || aVar2.d != null) ? compareTo : compareTo;
            } else {
                compareTo = aVar.d.compareTo(aVar2.d);
                if (compareTo != 0) {
                    return compareTo;
                }
                if (aVar.e != null && aVar2.e != null) {
                    return aVar.e.compareTo(aVar2.e);
                }
                if (aVar.e == null && aVar2.e != null) {
                    compareTo = -1;
                }
                if (aVar2.e != null || aVar.e == null) {
                    return compareTo;
                }
                return 1;
            }
        }
    }

    public interface d {
        void a();
    }

    public static synchronized d getInstance(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    d(Context context) {
        this.e = context;
        this.f = this.e.getContentResolver();
    }

    public void a(String str) {
        this.c.clear();
        this.i.clear();
        this.h.clear();
        this.j.clear();
        b(str);
        c(str);
    }

    public TreeMap<a, List<g>> a() {
        TreeMap<a, List<g>> treeMap = new TreeMap(new c());
        synchronized (this.b) {
            treeMap.putAll(this.j);
        }
        return treeMap;
    }

    public TreeMap<a, List<g>> b() {
        TreeMap<a, List<g>> treeMap = new TreeMap(new c());
        synchronized (this.b) {
            treeMap.putAll(this.h);
        }
        return treeMap;
    }

    public TreeMap<a, List<g>> c() {
        TreeMap<a, List<g>> treeMap = new TreeMap(new c());
        synchronized (this.b) {
            treeMap.putAll(this.i);
        }
        return treeMap;
    }

    public TreeMap<a, List<g>> d() {
        TreeMap<a, List<g>> treeMap = new TreeMap(new c());
        synchronized (this.b) {
            treeMap.putAll(this.i);
            for (Entry entry : this.h.entrySet()) {
                if (treeMap.containsKey(entry.getKey())) {
                    ((List) treeMap.get(entry.getKey())).addAll((List) entry.getValue());
                } else if (((List) entry.getValue()).size() > 0) {
                    treeMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return treeMap;
    }

    public ArrayList<g> e() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            arrayList.addAll(this.c);
        }
        ArrayList<g> arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            g gVar = (g) arrayList.get(i);
            if (gVar != null && gVar.h == b.Type_VIDEO) {
                arrayList.add(gVar);
            }
        }
        return arrayList2;
    }

    public ArrayList<g> f() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            arrayList.addAll(this.c);
        }
        ArrayList<g> arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            g gVar = (g) arrayList.get(i);
            if (gVar != null && gVar.h == b.Type_IMG) {
                arrayList.add(gVar);
            }
        }
        return arrayList2;
    }

    public ArrayList<g> g() {
        ArrayList<g> arrayList = new ArrayList();
        synchronized (this.b) {
            arrayList.addAll(this.c);
        }
        return arrayList;
    }

    public void b(String str) {
        String[] strArr = new String[]{"_id", "_data", "date_added", "duration", "height", "width"};
        Object[] objArr = new Object[]{str};
        String[] strArr2 = new String[]{String.format("%s%%", objArr)};
        Cursor query = this.f.query(Media.EXTERNAL_CONTENT_URI, strArr, "_data like ?", strArr2, "_id asc");
        if (query != null) {
            query.moveToFirst();
            for (int i = 0; i < query.getCount(); i++) {
                try {
                    long j = query.getLong(0);
                    String string = query.getString(1);
                    String string2 = query.getString(2);
                    if (f.a(string).booleanValue()) {
                        g gVar = new g();
                        gVar.e = DateFormat.format("yyyy-MM-dd HH:mm:ss", Long.valueOf(string2).longValue() * 1000).toString();
                        gVar.d = query.getInt(3);
                        gVar.c = string;
                        string2 = a(j);
                        if (string2 == null) {
                            query.moveToNext();
                        } else {
                            gVar.a = (int) j;
                            gVar.b = string2;
                            gVar.i = query.getInt(4);
                            gVar.j = query.getInt(5);
                            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                            mediaMetadataRetriever.setDataSource(string);
                            Integer.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue();
                            Integer.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue();
                            string = null;
                            if (VERSION.SDK_INT >= 17) {
                                string = mediaMetadataRetriever.extractMetadata(24);
                                Log.e("bob", "ratote " + string);
                            }
                            if (string.equals("90")) {
                                int i2 = gVar.j;
                                gVar.j = gVar.i;
                                gVar.i = i2;
                            }
                            mediaMetadataRetriever.release();
                            gVar.h = b.Type_VIDEO;
                            if (gVar.b != null && f.a(gVar.b).booleanValue()) {
                                MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                                mediaMetadataRetriever2.setDataSource(gVar.c);
                                string = mediaMetadataRetriever2.extractMetadata(23);
                                if (string != null) {
                                    int indexOf = string.indexOf("-", 1);
                                    int indexOf2 = string.indexOf("+", 1);
                                    if (indexOf == -1) {
                                        gVar.f = "" + Float.valueOf(string.substring(0, indexOf2));
                                        gVar.g = "" + Float.valueOf(string.substring(indexOf2, string.length()));
                                    } else {
                                        gVar.f = "" + Float.valueOf(string.substring(0, indexOf));
                                        gVar.g = "" + Float.valueOf(string.substring(indexOf, string.length()));
                                    }
                                } else {
                                    gVar.f = "";
                                    gVar.g = "";
                                }
                                synchronized (this.b) {
                                    List arrayList;
                                    this.c.add(gVar);
                                    a aVar = new a(gVar.f, gVar.g, gVar.e);
                                    if (this.h.containsKey(aVar)) {
                                        ((List) this.h.get(aVar)).add(gVar);
                                    } else {
                                        arrayList = new ArrayList();
                                        arrayList.add(gVar);
                                        this.h.put(aVar, arrayList);
                                    }
                                    aVar = new a("", "", d(gVar.c));
                                    if (this.j.containsKey(aVar)) {
                                        ((List) this.j.get(aVar)).add(gVar);
                                    } else {
                                        arrayList = new ArrayList();
                                        arrayList.add(gVar);
                                        this.j.put(aVar, arrayList);
                                    }
                                }
                                if (this.g != null) {
                                    this.g.a();
                                }
                            }
                            query.moveToNext();
                        }
                    } else {
                        query.moveToNext();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            query.close();
        }
    }

    public void c(String str) {
        String[] strArr = new String[]{"_id", "_data", "date_added"};
        Object[] objArr = new Object[]{str};
        String[] strArr2 = new String[]{String.format("%s%%", objArr)};
        Cursor query = this.f.query(Images.Media.EXTERNAL_CONTENT_URI, strArr, "_data like ?", strArr2, "_id asc");
        if (query != null) {
            query.moveToFirst();
            for (int i = 0; i < query.getCount(); i++) {
                try {
                    long j = query.getLong(0);
                    String string = query.getString(1);
                    String string2 = query.getString(2);
                    g gVar = new g();
                    gVar.e = DateFormat.format("yyyy-MM-dd HH:mm:ss", Long.valueOf(string2).longValue() * 1000).toString();
                    gVar.a = (int) j;
                    gVar.c = string;
                    gVar.h = b.Type_IMG;
                    gVar.f = "";
                    gVar.g = "";
                    if (gVar.c != null && f.a(gVar.c).booleanValue()) {
                        a(gVar.c, gVar);
                        synchronized (this.b) {
                            List arrayList;
                            this.c.add(gVar);
                            a aVar = new a(gVar.f, gVar.g, gVar.e);
                            if (this.i.containsKey(aVar)) {
                                ((List) this.i.get(aVar)).add(gVar);
                            } else {
                                arrayList = new ArrayList();
                                arrayList.add(gVar);
                                this.i.put(aVar, arrayList);
                            }
                            aVar = new a("", "", d(gVar.c));
                            if (this.j.containsKey(aVar)) {
                                ((List) this.j.get(aVar)).add(gVar);
                            } else {
                                arrayList = new ArrayList();
                                arrayList.add(gVar);
                                this.j.put(aVar, arrayList);
                            }
                        }
                        if (this.g != null) {
                            this.g.a();
                        }
                    }
                    query.moveToNext();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            query.close();
        }
    }

    private void a(String str, g gVar) {
        if (gVar != null) {
            try {
                float[] fArr = new float[2];
                if (new ExifInterface(str).getLatLong(fArr)) {
                    gVar.f = "" + fArr[0];
                    gVar.g = "" + fArr[1];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String a(long j) {
        String[] strArr = new String[]{"_data"};
        String[] strArr2 = new String[]{String.valueOf(j)};
        Thumbnails.getThumbnail(this.f, j, 1, null);
        Cursor query = this.f.query(Thumbnails.EXTERNAL_CONTENT_URI, strArr, "video_id = ?", strArr2, null);
        if (query == null || query.getCount() <= 0) {
            return null;
        }
        query.moveToFirst();
        return query.getString(query.getColumnIndex("_data"));
    }

    public void a(d dVar) {
        this.g = dVar;
    }

    public static String d(String str) {
        String str2 = "";
        String[] split = str.split(dji.pilot.usercenter.protocol.d.t);
        for (int i = 0; i < str.split(dji.pilot.usercenter.protocol.d.t).length - 1; i++) {
            str2 = str2 + split[i] + dji.pilot.usercenter.protocol.d.t;
        }
        return str2;
    }
}
