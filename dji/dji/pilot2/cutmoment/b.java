package dji.pilot2.cutmoment;

import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class b {
    protected Comparator<a> a = new Comparator<a>(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((a) obj, (a) obj2);
        }

        public int a(a aVar, a aVar2) {
            if (aVar == null || aVar2 == null) {
                return 0;
            }
            return (int) (aVar.e - aVar2.e);
        }
    };
    private ArrayList<String> b = new ArrayList();
    private ArrayList<a> c = new ArrayList();
    private a d = new a();
    private boolean e;

    public b(ArrayList<String> arrayList) {
        this.b = arrayList;
    }

    public long a() {
        long j = 0;
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                mediaPlayer.setDataSource((String) it.next());
                j += (long) mediaPlayer.getDuration();
            }
            return j;
        } catch (Exception e) {
            Exception exception = e;
            long j2 = 0;
            exception.printStackTrace();
            return j2;
        }
    }

    public void a(ArrayList<a> arrayList) {
        this.c = arrayList;
    }

    public void b() {
        Collections.sort(this.c, this.a);
    }

    public boolean a(a aVar) {
        boolean remove = this.c.remove(aVar);
        b();
        return remove;
    }

    public ArrayList<a> c() {
        return this.c;
    }

    public void b(a aVar) {
        this.d = aVar;
    }

    public a d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }
}
