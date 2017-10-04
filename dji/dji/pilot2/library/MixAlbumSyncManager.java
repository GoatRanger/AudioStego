package dji.pilot2.library;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumDirInfo;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.pilot2.library.model.DJISycAlbumModel;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MixAlbumSyncManager {
    private static final int SCREENNAIL_COMPLETE = 3;
    private static final int SCREENNAIL_PULLLIST = 4;
    private static final int SCREENNAIL_REFRESH = 2;
    private static final int THUMBNAIL_COMPLETE = 1;
    private static final int THUMBNAIL_REFRESH = 0;
    private static Context context;
    private static MixAlbumSyncManager instance;
    public static boolean isInThread = false;
    public static d syncHandler;
    private int current = 0;
    private ArrayList<DJISycAlbumModel> deleteLoadList = new ArrayList();
    public e fileDown;
    private ArrayList<DJIAlbumFileInfo> fileInfoList;
    private f fileMatch;
    private g filePull;
    private boolean isScanLocal = false;
    public ArrayList<dji.pilot.playback.litchi.a> mAlbumGroupList = new ArrayList();
    public List<DJIDeleteAlbumMd5> mDeleteDown;
    public List<String> mDeleteDownString;
    public List<DJINotShowAlbumMd5> mNotShowMd5;
    public List<String> mNotShowMd5String;
    private a mScanListeners;
    private b mSyncListeners;
    private ArrayList<DJISycAlbumModel> readyLoadList = new ArrayList();
    private dji.logic.album.a.d.a<DJIAlbumDirInfo> sdCardPullListener = null;
    public ArrayList<DJISycAlbumModel> showList = new ArrayList();
    public HashMap<String, DJISycAlbumModel> showMap = new HashMap();
    private int sum = 0;
    private dji.logic.album.a.d.a<DJIAlbumDirInfo> takePhotomDirInfoPullListener = null;
    public List<DJIVideoNewList> videoNewList;
    private c viewPagerListChange;

    public interface a {
        void a();

        void a(int i, int i2);

        void a(ArrayList<dji.pilot.playback.litchi.a> arrayList);
    }

    public interface b {
        void a();

        void a(int i);

        void a(int i, int i2, int i3);
    }

    public interface c {
        void a();
    }

    public static final class d extends Handler {
        private final WeakReference<MixAlbumSyncManager> a;

        public d(MixAlbumSyncManager mixAlbumSyncManager) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(mixAlbumSyncManager);
        }

        public void handleMessage(Message message) {
            MixAlbumSyncManager mixAlbumSyncManager = (MixAlbumSyncManager) this.a.get();
            if (mixAlbumSyncManager != null) {
                switch (message.what) {
                    case 0:
                        mixAlbumSyncManager.current = mixAlbumSyncManager.current + 1;
                        mixAlbumSyncManager.mSyncListeners.a(mixAlbumSyncManager.current, mixAlbumSyncManager.sum, 0);
                        return;
                    case 2:
                        mixAlbumSyncManager.current = mixAlbumSyncManager.current + 1;
                        mixAlbumSyncManager.mSyncListeners.a(mixAlbumSyncManager.current, mixAlbumSyncManager.sum, 2);
                        return;
                    case 3:
                        mixAlbumSyncManager.mSyncListeners.a(3);
                        return;
                    case 4:
                        mixAlbumSyncManager.filePull.a(mixAlbumSyncManager.takePhotomDirInfoPullListener);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static synchronized MixAlbumSyncManager getInstance(Context context) {
        MixAlbumSyncManager mixAlbumSyncManager;
        synchronized (MixAlbumSyncManager.class) {
            if (instance == null) {
                synchronized (MixAlbumSyncManager.class) {
                    if (instance == null) {
                        context = context;
                        instance = new MixAlbumSyncManager(context);
                    }
                }
            }
            mixAlbumSyncManager = instance;
        }
        return mixAlbumSyncManager;
    }

    public synchronized void addAlbumToList(DJISycAlbumModel dJISycAlbumModel) {
        if (this.showList != null && isInShowList(dJISycAlbumModel) == -1) {
            synchronized (this.showList) {
                this.showList.add(0, dJISycAlbumModel);
            }
        }
    }

    public synchronized void sortShowList() {
        Comparator anonymousClass1 = new Comparator<DJISycAlbumModel>(this) {
            final /* synthetic */ MixAlbumSyncManager a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((DJISycAlbumModel) obj, (DJISycAlbumModel) obj2);
            }

            public int a(DJISycAlbumModel dJISycAlbumModel, DJISycAlbumModel dJISycAlbumModel2) {
                if (dJISycAlbumModel == dJISycAlbumModel2) {
                    return 0;
                }
                if (dJISycAlbumModel != null && dJISycAlbumModel2 != null && dJISycAlbumModel.mLocalInfo == dJISycAlbumModel2.mLocalInfo) {
                    return 0;
                }
                if (dJISycAlbumModel == null || dJISycAlbumModel.mLocalInfo == null) {
                    return -1;
                }
                if (dJISycAlbumModel2 == null || dJISycAlbumModel2.mLocalInfo == null) {
                    return -1;
                }
                return MixAlbumSyncManager.compareLong(dJISycAlbumModel.mLocalInfo.h, dJISycAlbumModel2.mLocalInfo.h);
            }
        };
        synchronized (this.showList) {
            Collections.sort(this.showList, anonymousClass1);
        }
    }

    public static int compareLong(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    private String swithTime(long j, boolean z) {
        if (z) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(j));
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(j));
    }

    public synchronized void addAlbumToListFromLast(DJISycAlbumModel dJISycAlbumModel) {
        if (this.showList != null && isInShowList(dJISycAlbumModel) == -1) {
            synchronized (this.showList) {
                this.showList.add(dJISycAlbumModel);
            }
        }
    }

    public int isInShowList(DJISycAlbumModel dJISycAlbumModel) {
        int i = 0;
        while (i < this.showList.size()) {
            if (this.showList.get(i) != null && ((DJISycAlbumModel) this.showList.get(i)).mLocalInfo.f != null && dJISycAlbumModel != null && dJISycAlbumModel.mLocalInfo != null && ((DJISycAlbumModel) this.showList.get(i)).mLocalInfo.f.equals(dJISycAlbumModel.mLocalInfo.f)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public synchronized void deleteAlbumInList(DJISycAlbumModel dJISycAlbumModel) {
        if (this.showList != null) {
            synchronized (this.showList) {
                this.showList.remove(dJISycAlbumModel);
            }
        }
    }

    private MixAlbumSyncManager(Context context) {
        syncHandler = new d(this);
        this.fileMatch = f.getInstance();
        this.filePull = g.getInstance();
        this.fileDown = e.getInstance(context);
        this.takePhotomDirInfoPullListener = new dji.logic.album.a.d.a<DJIAlbumDirInfo>(this) {
            final /* synthetic */ MixAlbumSyncManager a;

            {
                this.a = r1;
            }

            public void a() {
                d.getInstance().a(true);
            }

            public void a(long j, long j2, long j3) {
            }

            public void a(long j, long j2) {
            }

            public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
                DJILogHelper.getInstance().LOGD("", "********************************onFailure***********************", true, true);
                if (dJIAlbumPullErrorType == DJIAlbumPullErrorType.h) {
                    d.getInstance().a(false);
                    this.a.mScanListeners.a(null);
                    MixAlbumSyncManager.isInThread = false;
                    return;
                }
                MixAlbumSyncManager.syncHandler.sendEmptyMessageDelayed(4, 1000);
            }

            public void a(DJIAlbumDirInfo dJIAlbumDirInfo) {
                DJILogHelper.getInstance().LOGD("", "********************************onSuccess***********************", true, true);
                if (dJIAlbumDirInfo != null) {
                    this.a.fileInfoList = dJIAlbumDirInfo.c;
                    if (!this.a.isScanLocal) {
                        this.a.fileMatch.a(this.a.showList, this.a.showMap, this.a.mNotShowMd5String, MixAlbumSyncManager.context);
                        this.a.isScanLocal = true;
                    }
                    new AsyncTask<Object, Integer, String>(this) {
                        ArrayList<DJISycAlbumModel> a;
                        final /* synthetic */ AnonymousClass2 b;

                        {
                            this.b = r1;
                        }

                        protected /* synthetic */ Object doInBackground(Object[] objArr) {
                            return a(objArr);
                        }

                        protected /* synthetic */ void onPostExecute(Object obj) {
                            a((String) obj);
                        }

                        protected String a(Object... objArr) {
                            this.a = e.getInstance(MixAlbumSyncManager.context).a();
                            return null;
                        }

                        protected void a(String str) {
                            super.onPostExecute(str);
                            this.b.a.fileMatch.a(this.b.a.fileInfoList, this.b.a.showList, this.b.a.showMap, this.b.a.readyLoadList, this.b.a.deleteLoadList, this.a, this.b.a.mDeleteDownString);
                            this.b.a.mAlbumGroupList.clear();
                            this.b.a.fileMatch.a(this.b.a.showList, this.b.a.mAlbumGroupList);
                            if (this.b.a.viewPagerListChange != null) {
                                this.b.a.viewPagerListChange.a();
                            }
                            Log.i("sort", "scan takePhotomDirInfoPullListener:" + this.b.a.mAlbumGroupList.size());
                            this.b.a.mScanListeners.a(this.b.a.mAlbumGroupList);
                            e instance = e.getInstance(MixAlbumSyncManager.context);
                            DJILogHelper.getInstance().LOGD("", this.b.a.deleteLoadList.size() + "mDeleteDownList.size()Scan", true, true);
                            instance.a(this.b.a.readyLoadList, this.b.a.deleteLoadList);
                            e.getInstance(MixAlbumSyncManager.context).b();
                            d.getInstance().a(false);
                            MixAlbumSyncManager.isInThread = false;
                        }
                    }.execute(new Object[]{""});
                }
            }
        };
        this.sdCardPullListener = new dji.logic.album.a.d.a<DJIAlbumDirInfo>(this) {
            final /* synthetic */ MixAlbumSyncManager a;

            {
                this.a = r1;
            }

            public void a() {
            }

            public void a(long j, long j2, long j3) {
            }

            public void a(long j, long j2) {
            }

            public void a(DJIAlbumDirInfo dJIAlbumDirInfo) {
            }

            public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
                this.a.filePull.a(this.a.sdCardPullListener);
            }
        };
    }

    public void registerScanListener(a aVar) {
        this.mScanListeners = aVar;
    }

    public void registerSyncListener(b bVar) {
        this.mSyncListeners = bVar;
    }

    public void registerViewPagerListener(c cVar) {
        this.viewPagerListChange = cVar;
    }

    public void initScanLocalThread() {
        if (!isInThread) {
            isInThread = true;
            getNotShowDb(context);
            Thread thread = new Thread(new Runnable(this) {
                final /* synthetic */ MixAlbumSyncManager a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.mAlbumGroupList.clear();
                    this.a.fileMatch.a(this.a.showList, this.a.showMap, this.a.mNotShowMd5String, MixAlbumSyncManager.context);
                    this.a.fileMatch.a(this.a.showList, this.a.mAlbumGroupList);
                    this.a.isScanLocal = true;
                    if (this.a.viewPagerListChange != null) {
                        this.a.viewPagerListChange.a();
                    }
                    Log.i("sort", "scan initScanLocalThread:" + this.a.mAlbumGroupList.size());
                    this.a.mScanListeners.a(this.a.mAlbumGroupList);
                    MixAlbumSyncManager.isInThread = false;
                }
            });
            if (this.mScanListeners == null) {
                isInThread = false;
            } else {
                thread.start();
            }
        }
    }

    public synchronized void initScanSynDelete() {
        if (!isInThread) {
            isInThread = true;
            Thread thread = new Thread(new Runnable(this) {
                final /* synthetic */ MixAlbumSyncManager a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.mAlbumGroupList.clear();
                    this.a.mScanListeners.a(0, 0);
                    this.a.fileMatch.a(this.a.showList, this.a.showMap, this.a.mNotShowMd5String, MixAlbumSyncManager.context);
                    ArrayList a = e.getInstance(MixAlbumSyncManager.context).a();
                    if (a.size() > 1) {
                        for (int i = 1; i < a.size(); i++) {
                            synchronized (this.a.showList) {
                                this.a.showList.add(a.get(i));
                            }
                        }
                    }
                    e.getInstance(MixAlbumSyncManager.context).b();
                    this.a.fileMatch.a(this.a.showList, this.a.mAlbumGroupList);
                    this.a.isScanLocal = true;
                    Log.i("sort", "initScanSynDelete:" + this.a.mAlbumGroupList.size());
                    this.a.mScanListeners.a(this.a.mAlbumGroupList);
                    MixAlbumSyncManager.isInThread = false;
                }
            });
            if (this.mScanListeners == null) {
                isInThread = false;
            } else {
                thread.start();
            }
        }
    }

    public static void scan(Context context, File file) {
        getInstance(context).scanPano(file);
    }

    public void scanOrg() {
        if (!isInThread) {
            isInThread = true;
            Thread thread = new Thread(new Runnable(this) {
                final /* synthetic */ MixAlbumSyncManager a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.mAlbumGroupList.clear();
                    DJILogHelper.getInstance().LOGD("", "mAlbumGroupListclear2", true, true);
                    this.a.fileMatch.a(this.a.showList, this.a.showMap, MixAlbumSyncManager.context);
                    this.a.fileMatch.a(this.a.showList, this.a.mAlbumGroupList);
                    Log.i("sort", "scanorg:" + this.a.mAlbumGroupList.size());
                    this.a.mScanListeners.a(this.a.mAlbumGroupList);
                    MixAlbumSyncManager.isInThread = false;
                }
            });
            if (this.mScanListeners == null) {
                isInThread = false;
            } else {
                thread.start();
            }
        }
    }

    public void scanPano(final File file) {
        if (dji.pilot.usercenter.f.c.a(file) && !isInThread) {
            isInThread = true;
            Thread thread = new Thread(new Runnable(this) {
                final /* synthetic */ MixAlbumSyncManager b;

                public void run() {
                    Log.d("pano", "start scan");
                    this.b.mAlbumGroupList.clear();
                    this.b.fileMatch.a(file, this.b.showList, this.b.showMap);
                    this.b.fileMatch.a(this.b.showList, this.b.mAlbumGroupList);
                    this.b.mScanListeners.a(this.b.mAlbumGroupList);
                    MixAlbumSyncManager.isInThread = false;
                }
            });
            if (this.mScanListeners == null) {
                isInThread = false;
            } else {
                thread.start();
            }
        }
    }

    public void scanPhotoFile(boolean z) {
        if (!isInThread) {
            isInThread = true;
            if (d.getInstance().f()) {
                d.getInstance().a(true);
                Thread thread = new Thread(new Runnable(this) {
                    final /* synthetic */ MixAlbumSyncManager a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.filePull.a(this.a.takePhotomDirInfoPullListener);
                        MixAlbumSyncManager.isInThread = false;
                    }
                });
                if (this.takePhotomDirInfoPullListener == null) {
                    d.getInstance().a(false);
                    isInThread = false;
                    return;
                }
                if (z) {
                    this.mScanListeners.a();
                }
                thread.start();
                return;
            }
            isInThread = false;
        }
    }

    public ArrayList<DJISycAlbumModel> getShowList() {
        return this.showList;
    }

    public void getDeleteDb(Context context) {
        try {
            this.mDeleteDown = com.dji.frame.c.c.c(context).c(DJIDeleteAlbumMd5.class);
            this.mDeleteDownString = new ArrayList();
            for (int i = 0; i < this.mDeleteDown.size(); i++) {
                this.mDeleteDownString.add(((DJIDeleteAlbumMd5) this.mDeleteDown.get(i)).getMd5());
            }
        } catch (Exception e) {
            this.mDeleteDown = new ArrayList();
            this.mDeleteDownString = new ArrayList();
        }
    }

    public void setDeleteDb(Context context) {
        com.dji.frame.c.c.c(context).a(DJIDeleteAlbumMd5.class);
        this.mDeleteDown.clear();
        this.mDeleteDownString.clear();
    }

    public void addDelete(Context context, String str) {
        DJIDeleteAlbumMd5 dJIDeleteAlbumMd5 = new DJIDeleteAlbumMd5();
        dJIDeleteAlbumMd5.setMd5(str);
        this.mDeleteDownString.add(str);
        this.mDeleteDown.add(dJIDeleteAlbumMd5);
        com.dji.frame.c.c.c(context).a(dJIDeleteAlbumMd5);
    }

    public void removeDelete(Context context, String str) {
        DJIDeleteAlbumMd5 dJIDeleteAlbumMd5 = new DJIDeleteAlbumMd5();
        dJIDeleteAlbumMd5.setMd5(str);
        if (this.mDeleteDownString.contains(dJIDeleteAlbumMd5)) {
            this.mDeleteDownString.remove(dJIDeleteAlbumMd5);
            com.dji.frame.c.c.c(context).f(dJIDeleteAlbumMd5);
        }
        if (this.mDeleteDown.contains(str)) {
            this.mDeleteDown.remove(str);
        }
    }

    public void getNotShowDb(Context context) {
        try {
            this.mNotShowMd5 = com.dji.frame.c.c.c(context).c(DJINotShowAlbumMd5.class);
            this.mNotShowMd5String = new ArrayList();
            for (int i = 0; i < this.mNotShowMd5.size(); i++) {
                this.mNotShowMd5String.add(((DJINotShowAlbumMd5) this.mNotShowMd5.get(i)).getMd5());
            }
        } catch (Exception e) {
            this.mNotShowMd5 = new ArrayList();
            this.mNotShowMd5String = new ArrayList();
        }
    }

    public void setNotShowDb(Context context) {
        com.dji.frame.c.c.c(context).a(DJINotShowAlbumMd5.class);
        this.mNotShowMd5.clear();
        this.mNotShowMd5String.clear();
    }

    public void addNotShow(Context context, String str) {
        DJINotShowAlbumMd5 dJINotShowAlbumMd5 = new DJINotShowAlbumMd5();
        dJINotShowAlbumMd5.setMd5(str);
        if (this.mNotShowMd5String != null) {
            this.mNotShowMd5String.add(str);
        }
        if (this.mNotShowMd5 != null) {
            this.mNotShowMd5.add(dJINotShowAlbumMd5);
        }
        com.dji.frame.c.c.c(context).a(dJINotShowAlbumMd5);
    }

    public void removeNotShow(Context context, String str) {
        DJINotShowAlbumMd5 dJINotShowAlbumMd5 = new DJINotShowAlbumMd5();
        dJINotShowAlbumMd5.setMd5(str);
        if (this.mNotShowMd5String.contains(dJINotShowAlbumMd5)) {
            this.mNotShowMd5String.remove(str);
            this.mNotShowMd5.remove(dJINotShowAlbumMd5);
            com.dji.frame.c.c.c(context).f(dJINotShowAlbumMd5);
        }
        if (this.mNotShowMd5.contains(str)) {
            this.mNotShowMd5.remove(str);
        }
    }

    public void clearAll() {
        this.mDeleteDown.clear();
        this.mDeleteDownString.clear();
    }

    public synchronized List<DJIVideoNewList> getNewDb(Context context) {
        this.videoNewList = com.dji.frame.c.c.c(context).c(DJIVideoNewList.class);
        if (this.videoNewList == null) {
            this.videoNewList = new ArrayList();
        }
        return this.videoNewList;
    }

    public synchronized void removeNewDb(Context context, String str) {
        DJIVideoNewList findDBVideoNew = findDBVideoNew(str);
        if (findDBVideoNew != null) {
            com.dji.frame.c.c.c(context).f(findDBVideoNew);
        }
    }

    public synchronized void insertDb(Context context, String str) {
        DJIVideoNewList dJIVideoNewList = new DJIVideoNewList();
        dJIVideoNewList.setName(str);
        com.dji.frame.c.c.c(context).a(dJIVideoNewList);
    }

    public synchronized DJIVideoNewList findDBVideoNew(String str) {
        DJIVideoNewList dJIVideoNewList;
        for (int i = 0; i < this.videoNewList.size(); i++) {
            if (str.equals(((DJIVideoNewList) this.videoNewList.get(i)).getName())) {
                dJIVideoNewList = (DJIVideoNewList) this.videoNewList.get(i);
                break;
            }
        }
        dJIVideoNewList = null;
        return dJIVideoNewList;
    }
}
