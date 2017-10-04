package dji.pilot2.multimoment.template;

import android.content.Context;
import android.graphics.Bitmap;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.multimoment.videolib.MusicDownloadTimeInfo;
import dji.pilot2.multimoment.videolib.musicCollectInfo;
import dji.pilot2.template.a;
import dji.pilot2.utils.n;
import dji.thirdparty.afinal.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TemplateController {
    public static final String DEFAULTLOCALIMAGE = "localmusic.png";
    public static final String DEFAULTMOREIMAGE = "moremusic.png";
    public static final String LOCALMUSIC = "local music";
    public static final String MOREMUSIC = "more music";
    private static final String TEMPLATE_CFG_DIR = "template_cfg";
    private static TemplateController mSinglTemplateController = new TemplateController();
    protected b mFinalDb;
    protected List<musicCollectInfo> mMusicCollectInfos;
    public List<a> mUpdateTemplates = null;
    public List<a> templates = null;

    private TemplateController() {
    }

    public static TemplateController getInstance() {
        return mSinglTemplateController;
    }

    public synchronized int addNewTemplates() {
        int i = 0;
        synchronized (this) {
            if (this.mUpdateTemplates != null) {
                int size = this.mUpdateTemplates.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.templates.add((a) this.mUpdateTemplates.get(i2));
                }
                sortMultiTemplateBeans(this.templates);
                this.mUpdateTemplates = null;
                i = size;
            }
        }
        return i;
    }

    public synchronized Boolean updateTemplate(Context context, String str) {
        Boolean valueOf;
        if (this.mUpdateTemplates == null) {
            this.mUpdateTemplates = new ArrayList();
        }
        File file = new File(str);
        if (file.exists()) {
            try {
                a aVar = new a(context, new FileInputStream(file));
                if (containsId(aVar.d())) {
                    DJILogHelper.getInstance().LOGI("bob", "download a duplicate template");
                    valueOf = Boolean.valueOf(false);
                } else {
                    aVar.a(Boolean.valueOf(true));
                    long currentTimeMillis = System.currentTimeMillis();
                    aVar.a(currentTimeMillis);
                    this.mUpdateTemplates.add(aVar);
                    insertDownloadMusicTime(aVar.getTemplateName(), aVar.d(), currentTimeMillis);
                    valueOf = Boolean.valueOf(true);
                }
            } catch (FileNotFoundException e) {
                if (file.exists()) {
                    file.delete();
                }
                valueOf = Boolean.valueOf(false);
            } catch (Exception e2) {
                DJILogHelper.getInstance().LOGD("bob", "bob exception");
                if (file.exists()) {
                    file.delete();
                }
                valueOf = Boolean.valueOf(false);
            }
        } else {
            valueOf = Boolean.valueOf(false);
        }
        return valueOf;
    }

    private void addMoreMusicTemplateBean(Context context) {
        a aVar = new a();
        aVar.a = context.getString(R.string.v2_moremusic_name);
        aVar.e = MOREMUSIC;
        aVar.a(DEFAULTMOREIMAGE, context);
        this.templates.add(aVar);
        sortMultiTemplateBeans(this.templates);
    }

    public synchronized boolean containsId(int i) {
        boolean z;
        int i2;
        for (i2 = 0; i2 < this.templates.size(); i2++) {
            if (i == ((a) this.templates.get(i2)).d()) {
                z = true;
                break;
            }
        }
        for (i2 = 0; i2 < this.mUpdateTemplates.size(); i2++) {
            if (i == ((a) this.mUpdateTemplates.get(i2)).d()) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public synchronized Boolean isContainLocalMusic() {
        Boolean valueOf;
        int i = 0;
        while (i < this.templates.size() && ((a) this.templates.get(i)).getDescription() != LOCALMUSIC) {
            i++;
        }
        if (i < this.templates.size()) {
            valueOf = Boolean.valueOf(true);
        } else {
            valueOf = Boolean.valueOf(false);
        }
        return valueOf;
    }

    public synchronized int getCollectNum() {
        int i;
        int i2 = 0;
        i = 0;
        while (i2 < this.templates.size()) {
            int i3;
            if (((a) this.templates.get(i2)).e() != 0) {
                i3 = i + 1;
            } else {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    protected String getLocalMusicNameFromPath(String str) {
        String substring;
        int lastIndexOf = str.lastIndexOf(".");
        int lastIndexOf2 = str.lastIndexOf(d.t);
        String str2 = "";
        try {
            substring = str.substring(str.lastIndexOf(d.t));
        } catch (Exception e) {
            e.printStackTrace();
            substring = str2;
        }
        if (!(lastIndexOf2 == -1 || lastIndexOf == -1 || lastIndexOf2 > lastIndexOf)) {
            str2 = "";
            try {
                substring = str.substring(lastIndexOf2 + 1, lastIndexOf - 1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return substring;
    }

    public synchronized Boolean addLocalMusicTemplateBean(String str, Context context) {
        Boolean valueOf;
        if (str != null) {
            if (!new File(str).exists()) {
                DJILogHelper.getInstance().LOGI("bob", "Local music is not exist");
                valueOf = Boolean.valueOf(false);
            }
        }
        int i = 0;
        while (i < this.templates.size()) {
            a aVar = (a) this.templates.get(i);
            if (aVar.getDescription() == LOCALMUSIC) {
                aVar.a = getLocalMusicNameFromPath(str);
                aVar.g = str;
                aVar.j = str;
                break;
            }
            i++;
        }
        if (i >= this.templates.size()) {
            aVar = new a();
            aVar.a = getLocalMusicNameFromPath(str);
            aVar.e = LOCALMUSIC;
            aVar.g = str;
            aVar.j = str;
            aVar.a(DEFAULTLOCALIMAGE, context);
            this.templates.add(aVar);
            sortMultiTemplateBeans(this.templates);
        }
        valueOf = Boolean.valueOf(true);
        return valueOf;
    }

    protected void insertDownloadMusicTime(String str, int i, long j) {
        MusicDownloadTimeInfo musicDownloadTimeInfo = new MusicDownloadTimeInfo();
        musicDownloadTimeInfo.setDownloadTime(j);
        musicDownloadTimeInfo.setMusicName(str);
        musicDownloadTimeInfo.setSequence(i);
        this.mFinalDb.a(musicDownloadTimeInfo);
    }

    protected void updateDownloadMusicTime(List<a> list) {
        List c = this.mFinalDb.c(MusicDownloadTimeInfo.class);
        for (int i = 0; i < c.size(); i++) {
            MusicDownloadTimeInfo musicDownloadTimeInfo = (MusicDownloadTimeInfo) c.get(i);
            int i2 = 0;
            while (i2 < list.size()) {
                a aVar = (a) list.get(i2);
                if (musicDownloadTimeInfo.getMusicName().equals(aVar.getTemplateName())) {
                    aVar.a(musicDownloadTimeInfo.getDownloadTime());
                    break;
                }
                i2++;
            }
            if (i2 == list.size()) {
                DJILogHelper.getInstance().LOGE("bob", "updateDownloadMusicTime err can not find collectmusic inof.getMusicName() = " + musicDownloadTimeInfo.getMusicName());
            }
        }
    }

    public synchronized Boolean setCollect(int i) {
        Boolean valueOf;
        long currentTimeMillis = System.currentTimeMillis();
        a aVar = (a) this.templates.get(i);
        if (aVar == null) {
            valueOf = Boolean.valueOf(false);
        } else if (aVar.getDescription() == MOREMUSIC || aVar.getDescription() == LOCALMUSIC) {
            valueOf = Boolean.valueOf(false);
        } else {
            aVar.b(currentTimeMillis);
            musicCollectInfo dji_pilot2_multimoment_videolib_musicCollectInfo = new musicCollectInfo();
            dji_pilot2_multimoment_videolib_musicCollectInfo.setCollectTime(currentTimeMillis);
            dji_pilot2_multimoment_videolib_musicCollectInfo.setMusicName(aVar.getTemplateName());
            dji_pilot2_multimoment_videolib_musicCollectInfo.setSequence(aVar.d());
            this.mFinalDb.a(dji_pilot2_multimoment_videolib_musicCollectInfo);
            sortMultiTemplateBeans(this.templates);
            valueOf = Boolean.valueOf(true);
        }
        return valueOf;
    }

    public synchronized Boolean delCollect(int i) {
        Boolean valueOf;
        a aVar = (a) this.templates.get(i);
        if (aVar == null) {
            valueOf = Boolean.valueOf(false);
        } else if (aVar.getDescription() == MOREMUSIC || aVar.getDescription() == LOCALMUSIC) {
            valueOf = Boolean.valueOf(false);
        } else {
            aVar.b(0);
            this.mFinalDb.a(musicCollectInfo.class, "musicName = \"" + aVar.getTemplateName() + "\"");
            sortMultiTemplateBeans(this.templates);
            valueOf = Boolean.valueOf(true);
        }
        return valueOf;
    }

    protected void getCollectMusicInfo(Context context) {
        this.mFinalDb = c.c(context);
        this.mMusicCollectInfos = this.mFinalDb.c(musicCollectInfo.class);
        updateCollectTime(this.templates);
        updateDownloadMusicTime(this.templates);
    }

    protected void updateCollectTime(List<a> list) {
        for (int i = 0; i < this.mMusicCollectInfos.size(); i++) {
            musicCollectInfo dji_pilot2_multimoment_videolib_musicCollectInfo = (musicCollectInfo) this.mMusicCollectInfos.get(i);
            int i2 = 0;
            while (i2 < list.size()) {
                a aVar = (a) list.get(i2);
                if (dji_pilot2_multimoment_videolib_musicCollectInfo.getMusicName().equals(aVar.getTemplateName())) {
                    aVar.b(dji_pilot2_multimoment_videolib_musicCollectInfo.getCollectTime());
                    break;
                }
                i2++;
            }
            if (i2 == list.size()) {
                DJILogHelper.getInstance().LOGE("bob", "updateCollectTime err can not find collectmusic inof.getMusicName() = " + dji_pilot2_multimoment_videolib_musicCollectInfo.getMusicName());
            }
        }
    }

    public synchronized Boolean delLocalMusicTemplateBean() {
        Boolean valueOf;
        int i = 0;
        while (i < this.templates.size() && ((a) this.templates.get(i)).getDescription() != LOCALMUSIC) {
            i++;
        }
        if (i != this.templates.size()) {
            this.templates.remove(i);
            valueOf = Boolean.valueOf(true);
        } else {
            valueOf = Boolean.valueOf(false);
        }
        return valueOf;
    }

    public synchronized Boolean addLocalMusicTemplateBean(String str, Bitmap bitmap, Context context) {
        Boolean valueOf;
        if (bitmap == null) {
            valueOf = Boolean.valueOf(false);
        } else {
            a aVar;
            if (str != null) {
                if (!new File(str).exists()) {
                    DJILogHelper.getInstance().LOGI("bob", "Local music is not exist");
                    valueOf = Boolean.valueOf(false);
                }
            }
            int i = 0;
            while (i < this.templates.size()) {
                aVar = (a) this.templates.get(i);
                if (aVar.getDescription() == LOCALMUSIC) {
                    aVar.a = getLocalMusicNameFromPath(str);
                    aVar.g = str;
                    aVar.j = str;
                    aVar.b(bitmap);
                    break;
                }
                i++;
            }
            if (i >= this.templates.size()) {
                aVar = new a();
                aVar.a = getLocalMusicNameFromPath(str);
                aVar.e = LOCALMUSIC;
                aVar.g = str;
                aVar.j = str;
                aVar.b(bitmap);
                this.templates.add(aVar);
                sortMultiTemplateBeans(this.templates);
            }
            valueOf = Boolean.valueOf(true);
        }
        return valueOf;
    }

    protected void sortMultiTemplateBeans(List<a> list) {
        Collections.sort(list, new Comparator<a>(this) {
            final /* synthetic */ TemplateController a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((a) obj, (a) obj2);
            }

            public int a(a aVar, a aVar2) {
                if (aVar.getDescription() == TemplateController.MOREMUSIC) {
                    return -1;
                }
                if (aVar.getDescription() == TemplateController.LOCALMUSIC) {
                    if (aVar2.getDescription() == TemplateController.MOREMUSIC) {
                        return 1;
                    }
                    return -1;
                } else if (aVar2.getDescription() == TemplateController.MOREMUSIC) {
                    return 1;
                } else {
                    if (aVar2.getDescription() == TemplateController.LOCALMUSIC) {
                        if (aVar.getDescription() != TemplateController.MOREMUSIC) {
                            return 1;
                        }
                        return -1;
                    } else if (aVar.e() > aVar2.e()) {
                        return -1;
                    } else {
                        if (aVar.e() < aVar2.e()) {
                            return 1;
                        }
                        if (aVar.c() > aVar2.c()) {
                            return -1;
                        }
                        if (aVar.c() < aVar2.c()) {
                            return 1;
                        }
                        if (aVar.d() <= aVar2.d()) {
                            return 1;
                        }
                        return -1;
                    }
                }
            }
        });
    }

    public synchronized List<a> getTemplates(Context context) {
        if (this.templates == null) {
            this.templates = new ArrayList();
            File[] listFiles = new File(n.s(context)).listFiles(new FilenameFilter(this) {
                final /* synthetic */ TemplateController a;

                {
                    this.a = r1;
                }

                public boolean accept(File file, String str) {
                    return str.endsWith(".cfg");
                }
            });
            addMoreMusicTemplateBean(context);
            for (File file : listFiles) {
                try {
                    this.templates.add(new a(context, new FileInputStream(file)));
                } catch (FileNotFoundException e) {
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Exception e2) {
                    DJILogHelper.getInstance().LOGD("bob", "bob exception");
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
            getCollectMusicInfo(context);
            sortMultiTemplateBeans(this.templates);
        }
        return this.templates;
    }

    public int getMaxSegNum(Context context) {
        List templates = getTemplates(context);
        int i = 1;
        for (int i2 = 0; i2 < templates.size(); i2++) {
            a aVar = (a) templates.get(i2);
            if (aVar != null && i < aVar.size()) {
                i = aVar.size();
            }
        }
        return i;
    }

    public int getBestMatchTemplateId(Context context, int i) {
        List templates = getTemplates(context);
        int i2 = 0;
        int i3 = 0;
        int i4 = 100;
        while (i3 < templates.size()) {
            int size;
            a aVar = (a) templates.get(i3);
            if (aVar != null) {
                size = i - aVar.size();
                if (size == 0) {
                    return i3;
                }
                if (size < i4) {
                    if (size < 0 && i4 < 0) {
                        size = i4;
                        i4 = i2;
                        i3++;
                        i2 = i4;
                        i4 = size;
                    } else if (size < 0 && i4 > 0) {
                        i4 = i3;
                        i3++;
                        i2 = i4;
                        i4 = size;
                    } else if (size > 0 && i4 > 0) {
                        i4 = i3;
                        i3++;
                        i2 = i4;
                        i4 = size;
                    }
                }
            }
            size = i4;
            i4 = i2;
            i3++;
            i2 = i4;
            i4 = size;
        }
        return i2;
    }
}
