package dji.pilot2.bigfilm;

import android.content.Context;
import com.dji.frame.c.h;
import dji.pilot2.bigfilm.b.b;
import dji.pilot2.mode.bigfilmConfMod;
import dji.pilot2.mode.bigfilmConfMod.c;
import dji.pilot2.mode.bigfilmConfMod.e;
import dji.pilot2.mode.bigfilmConfMod.f;
import dji.pilot2.mode.bigfilmConfMod.g;
import dji.pilot2.mode.bigfilmConfMod.o;
import dji.pilot2.mode.bigfilmConfMod.p;
import dji.pilot2.mode.bigfilmConfMod.q;
import dji.pilot2.template.d;
import dji.pilot2.utils.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BigFilmBean extends d {
    public static String VIDEOSOURCE_USER = "user";
    public static String VIDEOSOURCE_VIDEO = "video";
    public String mAuthorName;
    public String mBigFilmName;
    public String mCoverImageSrc;
    public String mDesc;
    public bigfilmConfMod mFilmConf;
    public String mMusicFile;
    public int mSegNum;
    public List<b> mSegs = new ArrayList();

    public BigFilmBean(Context context, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = "";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            str = str + readLine;
        }
        this.mFilmConf = (bigfilmConfMod) h.b(str, bigfilmConfMod.class);
        if (this.mFilmConf == null) {
            throw new IOException("Cannot parse configuration file");
        }
        this.mBigFilmName = this.mFilmConf.name;
        this.mMusicFile = this.mFilmConf.musicFile;
        this.mDesc = this.mFilmConf.desc;
        this.mAuthorName = this.mFilmConf.authorName;
        this.mCoverImageSrc = this.mFilmConf.coverImage;
        for (int i = 0; i < this.mFilmConf.composition.size(); i++) {
            c cVar = (c) this.mFilmConf.composition.get(i);
            b bVar = new b();
            bVar.c(cVar.c);
            bVar.a(cVar.d);
            bVar.a(cVar.a);
            bVar.b(cVar.b);
            bVar.b(cVar.f);
            bVar.a(c.convert(cVar.e));
            List list = cVar.g;
            for (int i2 = 0; i2 < list.size(); i2++) {
                e eVar = (e) list.get(i);
                g gVar = eVar.d;
                bVar.a(gVar.a, gVar.b, eVar.a);
            }
            this.mSegs.add(bVar);
        }
        this.mSegNum = this.mSegs.size();
    }

    public b getTransitionInfo(int i) {
        b bVar = (b) this.mSegs.get(i);
        if (bVar != null) {
            return bVar.c;
        }
        return null;
    }

    public List<b> getTransitionInfos() {
        List<b> arrayList = new ArrayList();
        for (int i = 0; i < this.mSegs.size(); i++) {
            arrayList.add(getTransitionInfo(i));
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public Boolean getIsConfedFile(int i) {
        if (i >= this.mSegs.size()) {
            return Boolean.valueOf(false);
        }
        b bVar = (b) this.mSegs.get(i);
        if (bVar == null || bVar.d.equals("user")) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public String getConfedFilePath(int i, Context context) {
        if (i >= this.mSegs.size()) {
            return null;
        }
        b bVar = (b) this.mSegs.get(i);
        return (bVar == null || !"video".equals(bVar.d)) ? null : n.i(context) + dji.pilot.usercenter.protocol.d.t + getVideoAssert(bVar.e).a;
    }

    public float getConfedSpeed(int i) {
        if (i >= this.mSegs.size()) {
            return 1.0f;
        }
        b bVar = (b) this.mSegs.get(i);
        return bVar != null ? bVar.b : 1.0f;
    }

    protected o getImageAssert(String str) {
        if (!(this.mFilmConf.assets == null || this.mFilmConf.assets.b == null)) {
            Field declaredField;
            try {
                declaredField = f.class.getDeclaredField(str);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                declaredField = null;
            }
            if (declaredField != null) {
                try {
                    return (o) declaredField.get(this.mFilmConf.assets.b);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (IllegalArgumentException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    protected q getWordAssert(String str) {
        if (!(this.mFilmConf.assets == null || this.mFilmConf.assets.c == null)) {
            Field declaredField;
            try {
                declaredField = p.class.getDeclaredField(str);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                declaredField = null;
            }
            if (declaredField != null) {
                try {
                    return (q) declaredField.get(this.mFilmConf.assets.c);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (IllegalArgumentException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    protected o getVideoAssert(String str) {
        if (!(this.mFilmConf.assets == null || this.mFilmConf.assets.a == null)) {
            Field declaredField;
            try {
                declaredField = bigfilmConfMod.n.class.getDeclaredField(str);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                declaredField = null;
            }
            if (declaredField != null) {
                try {
                    return (o) declaredField.get(this.mFilmConf.assets.a);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (IllegalArgumentException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    public long getTotalDurations() {
        long j = 0;
        for (int i = 0; i < this.mSegs.size(); i++) {
            j += (long) ((b) this.mSegs.get(i)).b();
        }
        return j;
    }

    public String getTemplateName() {
        return this.mFilmConf.name;
    }

    public int getDurationAtOrder(int i) {
        return ((b) this.mSegs.get(i)).b();
    }

    public int size() {
        return super.size();
    }

    public int getNormalSpeedDurationAtOrder(int i) {
        return ((b) this.mSegs.get(i)).c();
    }

    public List<Integer> getNormalSpeedDurations() {
        List<Integer> arrayList = new ArrayList();
        for (int i = 0; i < this.mSegs.size(); i++) {
            arrayList.add(Integer.valueOf(((b) this.mSegs.get(i)).c()));
        }
        return arrayList;
    }

    public List<Integer> getSegDurations() {
        List<Integer> arrayList = new ArrayList();
        for (int i = 0; i < this.mSegs.size(); i++) {
            arrayList.add(Integer.valueOf(((b) this.mSegs.get(i)).b()));
        }
        return arrayList;
    }

    public String getDescription() {
        return this.mFilmConf.desc;
    }

    public String getPreviewFileName() {
        return super.getPreviewFileName();
    }

    public String getPreviewMusicName() {
        return this.mFilmConf.musicFile;
    }

    public String getConcatMusicName() {
        return this.mFilmConf.musicFile;
    }
}
