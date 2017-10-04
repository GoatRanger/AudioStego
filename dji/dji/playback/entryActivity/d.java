package dji.playback.entryActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Media;
import android.provider.MediaStore.Video.Thumbnails;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import com.dji.frame.c.f;
import dji.log.DJILogHelper;
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
    private ArrayList<h> c = new ArrayList();
    private String d;
    private Context e;
    private ContentResolver f;
    private d g;
    private Map<a, List<h>> h = new TreeMap(new c());
    private Map<a, List<h>> i = new TreeMap(new c());

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

    public void a(String[] strArr) {
        this.c.clear();
        this.i.clear();
        this.h.clear();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                a(str);
                DJILogHelper.getInstance().LOGI("bob", "after scaneVideo");
                b(str);
            }
        }
    }

    public TreeMap<a, List<h>> a() {
        TreeMap<a, List<h>> treeMap = new TreeMap(new c());
        synchronized (this.b) {
            treeMap.putAll(this.h);
        }
        return treeMap;
    }

    public TreeMap<a, List<h>> b() {
        TreeMap<a, List<h>> treeMap = new TreeMap(new c());
        synchronized (this.b) {
            treeMap.putAll(this.i);
        }
        return treeMap;
    }

    public TreeMap<a, List<h>> c() {
        TreeMap<a, List<h>> treeMap = new TreeMap(new c());
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

    public ArrayList<h> d() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            arrayList.addAll(this.c);
        }
        ArrayList<h> arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            h hVar = (h) arrayList.get(i);
            if (hVar != null && hVar.h == b.Type_VIDEO) {
                arrayList.add(hVar);
            }
        }
        return arrayList2;
    }

    public ArrayList<h> e() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            arrayList.addAll(this.c);
        }
        ArrayList<h> arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            h hVar = (h) arrayList.get(i);
            if (hVar != null && hVar.h == b.Type_IMG) {
                arrayList.add(hVar);
            }
        }
        return arrayList2;
    }

    public ArrayList<h> f() {
        ArrayList<h> arrayList = new ArrayList();
        synchronized (this.b) {
            arrayList.addAll(this.c);
        }
        return arrayList;
    }

    public void a(String str) {
        String string;
        String[] strArr = new String[]{"_id", "_data", "date_added", "duration", "height", "width"};
        DJILogHelper.getInstance().LOGI("bob", "pattonString = " + String.format("%s%%", new Object[]{str}));
        String[] strArr2 = new String[]{string};
        Cursor query = this.f.query(Media.EXTERNAL_CONTENT_URI, strArr, "_data like ?", strArr2, "_id asc");
        if (query != null) {
            query.moveToFirst();
            DJILogHelper.getInstance().LOGI("bob", "scaneVideo size =" + query.getCount());
            for (int i = 0; i < query.getCount(); i++) {
                try {
                    long j = query.getLong(0);
                    String string2 = query.getString(1);
                    string = query.getString(2);
                    if (f.a(string2).booleanValue()) {
                        h hVar = new h();
                        hVar.e = DateFormat.format("yyyy-MM-dd HH:mm:ss", Long.valueOf(string).longValue() * 1000).toString();
                        hVar.d = query.getInt(3);
                        hVar.c = string2;
                        string = a(j);
                        if (string == null) {
                            query.moveToNext();
                        } else {
                            hVar.a = (int) j;
                            hVar.b = string;
                            hVar.i = query.getInt(4);
                            hVar.j = query.getInt(5);
                            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                            mediaMetadataRetriever.setDataSource(string2);
                            int intValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue();
                            int intValue2 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue();
                            string = null;
                            if (VERSION.SDK_INT >= 17) {
                                string = mediaMetadataRetriever.extractMetadata(24);
                                Log.e("bob", "ratote " + string);
                            }
                            if (string.equals("90") || string.equals("270")) {
                                int i2 = hVar.j;
                                hVar.j = hVar.i;
                                hVar.i = i2;
                            }
                            mediaMetadataRetriever.release();
                            DJILogHelper.getInstance().LOGI("bob", "scaneVideo path =" + string2 + " width=" + intValue + " height=" + intValue2);
                            DJILogHelper.getInstance().LOGI("bob", "info.mPathString = " + hVar.c);
                            hVar.h = b.Type_VIDEO;
                            if (hVar.b != null && f.a(hVar.b).booleanValue()) {
                                MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                                mediaMetadataRetriever2.setDataSource(hVar.c);
                                string = mediaMetadataRetriever2.extractMetadata(23);
                                DJILogHelper.getInstance().LOGI("bob", "location = " + string);
                                if (string != null) {
                                    int indexOf = string.indexOf("-", 1);
                                    int indexOf2 = string.indexOf("+", 1);
                                    if (indexOf == -1) {
                                        hVar.f = "" + Float.valueOf(string.substring(0, indexOf2));
                                        hVar.g = "" + Float.valueOf(string.substring(indexOf2, string.length()));
                                    } else {
                                        hVar.f = "" + Float.valueOf(string.substring(0, indexOf));
                                        hVar.g = "" + Float.valueOf(string.substring(indexOf, string.length()));
                                    }
                                } else {
                                    hVar.f = "";
                                    hVar.g = "";
                                }
                                DJILogHelper.getInstance().LOGI("bob", "location = " + string + " info.mLocation = " + hVar.f + " info.mLocation1 =" + hVar.g);
                                synchronized (this.b) {
                                    this.c.add(hVar);
                                    a aVar = new a(hVar.f, hVar.g, hVar.e);
                                    if (this.h.containsKey(aVar)) {
                                        ((List) this.h.get(aVar)).add(hVar);
                                    } else {
                                        List arrayList = new ArrayList();
                                        arrayList.add(hVar);
                                        this.h.put(aVar, arrayList);
                                    }
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
        } else {
            DJILogHelper.getInstance().LOGI("bob", "scanevideo cursor is null ");
        }
        DJILogHelper.getInstance().LOGI("bob", "scanevideo size= " + this.h.size());
    }

    public void b(String str) {
        String[] strArr = new String[]{"_id", "_data", "date_added"};
        Object[] objArr = new Object[]{str};
        String[] strArr2 = new String[]{String.format("%s%%", objArr)};
        Cursor query = this.f.query(Images.Media.EXTERNAL_CONTENT_URI, strArr, "_data like ?", strArr2, "_id asc");
        if (query != null) {
            query.moveToFirst();
            DJILogHelper.getInstance().LOGI("bob", "scaneImage size =" + query.getCount());
            for (int i = 0; i < query.getCount(); i++) {
                try {
                    long j = query.getLong(0);
                    String string = query.getString(1);
                    String string2 = query.getString(2);
                    DJILogHelper.getInstance().LOGI("bob", "scaneImage path =" + string);
                    h hVar = new h();
                    hVar.e = DateFormat.format("yyyy-MM-dd HH:mm:ss", Long.valueOf(string2).longValue() * 1000).toString();
                    hVar.a = (int) j;
                    hVar.c = string;
                    hVar.h = b.Type_IMG;
                    hVar.f = "";
                    hVar.g = "";
                    if (hVar.c != null && f.a(hVar.c).booleanValue()) {
                        a(hVar.c, hVar);
                        synchronized (this.b) {
                            this.c.add(hVar);
                            a aVar = new a(hVar.f, hVar.g, hVar.e);
                            if (this.i.containsKey(aVar)) {
                                ((List) this.i.get(aVar)).add(hVar);
                            } else {
                                List arrayList = new ArrayList();
                                arrayList.add(hVar);
                                this.i.put(aVar, arrayList);
                            }
                        }
                    }
                    query.moveToNext();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            query.close();
        } else {
            DJILogHelper.getInstance().LOGI("bob", "scaneimage cursor is null ");
        }
        DJILogHelper.getInstance().LOGI("bob", "scaneimage size= " + this.i.size());
    }

    private void a(String str, h hVar) {
        if (hVar != null) {
            try {
                float[] fArr = new float[2];
                if (new ExifInterface(str).getLatLong(fArr)) {
                    hVar.f = "" + fArr[0];
                    hVar.g = "" + fArr[1];
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
}
