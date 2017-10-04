package dji.pilot2.nativeaudio.b;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import com.dji.frame.c.f;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;

public class b {
    private static b f;
    private ContentResolver a;
    private Context b;
    private final String c = "DJIAudioScanner";
    private ArrayList<dji.pilot2.nativeaudio.model.b> d;
    private ArrayList<dji.pilot2.nativeaudio.model.b> e;
    private a g;

    public interface a {
        void a();

        void a(ArrayList<dji.pilot2.nativeaudio.model.b> arrayList);
    }

    public static b getInstance(Context context) {
        if (f == null) {
            f = new b(context);
        }
        return f;
    }

    private b(Context context) {
        this.b = context;
        this.d = new ArrayList();
        this.e = new ArrayList();
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a() {
        this.d.clear();
        DJILogHelper.getInstance().LOGD("DJIAudioScanner", "scaneAllMedias");
        if (this.b != null) {
            this.a = this.b.getContentResolver();
            String[] strArr = new String[]{"_id", "_data", "_display_name", "date_added", "duration", "artist_id", "artist", "album_id", "album"};
            Cursor query = this.a.query(Media.EXTERNAL_CONTENT_URI, strArr, null, null, "_id asc");
            if (query != null) {
                query.moveToFirst();
                for (int i = 0; i < query.getCount(); i++) {
                    dji.pilot2.nativeaudio.model.b bVar = new dji.pilot2.nativeaudio.model.b();
                    bVar.a = query.getString(1);
                    if (bVar.a == null || f.a(bVar.a).booleanValue()) {
                        bVar.c = query.getString(2);
                        bVar.b = query.getString(3);
                        bVar.e = query.getLong(4);
                        if (bVar.c == null || (a(bVar.c) && bVar.e != 0)) {
                            bVar.g = query.getInt(5);
                            bVar.f = query.getString(6);
                            bVar.i = query.getInt(7);
                            bVar.h = query.getString(8);
                            this.d.add(bVar);
                            Log.i("zhang", "scan name:" + bVar.c);
                            query.moveToNext();
                        } else {
                            query.moveToNext();
                        }
                    } else {
                        query.moveToNext();
                    }
                }
                query.close();
            }
            if (this.d == null || this.d.size() <= 0) {
                this.g.a();
            } else {
                this.g.a(this.d);
            }
        }
    }

    private boolean a(String str) {
        String toLowerCase = str.toLowerCase();
        if (toLowerCase == null || (!toLowerCase.contains(".mp3") && !toLowerCase.contains(".wav") && !toLowerCase.contains(".m4a"))) {
            return false;
        }
        return true;
    }

    public String a(int i) {
        String str = null;
        String[] strArr = new String[]{"album_art"};
        Log.i("path", "path:" + i);
        Cursor query = this.a.query(Uri.parse("content://media/external/audio/albums" + d.t + Integer.toString(i)), strArr, null, null, null);
        if (query.getCount() > 0 && query.getColumnCount() > 0) {
            query.moveToNext();
            str = query.getString(0);
        }
        query.close();
        Log.i("path", "path:" + str);
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<dji.pilot2.nativeaudio.model.b> b() {
        /*
        r4 = this;
        r0 = r4.b;
        r0 = dji.pilot2.utils.n.c(r0);
        r1 = "DJIAudioScanner";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "local path :";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        android.util.Log.i(r1, r2);
        if (r0 == 0) goto L_0x0038;
    L_0x0020:
        r1 = r0.isEmpty();
        if (r1 != 0) goto L_0x0038;
    L_0x0026:
        r1 = new java.io.File;
        r1.<init>(r0);
        r0 = r1.isDirectory();
        if (r0 == 0) goto L_0x0038;
    L_0x0031:
        r0 = r1.list();
        r0 = r0.length;
        if (r0 <= 0) goto L_0x0038;
    L_0x0038:
        r0 = r4.e;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.nativeaudio.b.b.b():java.util.ArrayList<dji.pilot2.nativeaudio.model.b>");
    }

    public ArrayList<dji.pilot2.nativeaudio.model.b> c() {
        return this.d;
    }
}
