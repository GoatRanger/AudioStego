package dji.pilot2.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.dji.frame.c.f;
import com.here.odnp.config.OdnpConfigStatic;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.log.DJILogHelper;
import dji.logic.album.a.b;
import dji.logic.album.a.e;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.PhotoState;
import dji.pilot.R;
import dji.pilot.fpv.d.c$l;
import dji.pilot.fpv.d.c.j;
import dji.pilot2.library.a.g;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.pilot2.library.model.MediaInfoBean;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.utils.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import lecho.lib.hellocharts.model.h;

public class DJILibraryPhotoView extends RelativeLayout implements j, c$l {
    public static List<DJISycAlbumModel> B = new ArrayList();
    public static DJISycAlbumModel C = null;
    private static final int aA = 19;
    private static final int aB = 22;
    private static final SparseArray<DJISycAlbumModel> ac = new SparseArray(12);
    private static a an = null;
    private static final int ao = 1;
    private static final int ap = 2;
    private static final int aq = 7;
    private static final int ar = 9;
    private static final int as = 10;
    private static final int at = 11;
    private static final int au = 12;
    private static final int av = 13;
    private static final int aw = 14;
    private static final int ax = 15;
    private static final int ay = 16;
    private static final int az = 21;
    public View D;
    int E;
    private final int F;
    private final float G;
    private final int H;
    private Context I;
    private ExpandableListView J;
    private DJILinearLayout K;
    private DJITextView L;
    private View M;
    private ProgressBar N;
    private DJITextView O;
    private DJIImageView P;
    private DJITextView Q;
    private DJITextView R;
    private g S;
    private ImageLoader T;
    private OnGroupClickListener U;
    private e V;
    private List<dji.pilot.playback.litchi.a> W;
    private final String aC;
    private d aD;
    private List<View> aE;
    private List<dji.pilot.playback.litchi.a> aa;
    private OnClickListener ab;
    private ArrayList<MediaInfoBean> ad;
    private DJILibraryView ae;
    private b af;
    private MixAlbumSyncManager ag;
    private dji.pilot2.library.MixAlbumSyncManager.a ah;
    private dji.pilot.publics.b.a ai;
    private boolean aj;
    private int ak;
    private int al;
    private int am;

    public static class a extends Handler {
        private final WeakReference<DJILibraryPhotoView> a;

        public a(DJILibraryPhotoView dJILibraryPhotoView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJILibraryPhotoView);
        }

        public void handleMessage(Message message) {
            final DJILibraryPhotoView dJILibraryPhotoView = (DJILibraryPhotoView) this.a.get();
            if (dJILibraryPhotoView != null) {
                int i;
                Toast makeText;
                switch (message.what) {
                    case 1:
                        dJILibraryPhotoView.N.setVisibility(8);
                        dJILibraryPhotoView.P.show();
                        dJILibraryPhotoView.O.setText(R.string.v2_photolist_empty);
                        dJILibraryPhotoView.ae.setVisibility(0);
                        if (dJILibraryPhotoView.aa == null) {
                            dJILibraryPhotoView.Q.go();
                            dJILibraryPhotoView.updateSynState();
                            return;
                        }
                        new AsyncTask<Object, Integer, String>(this) {
                            final /* synthetic */ a b;

                            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                                return a(objArr);
                            }

                            protected /* synthetic */ void onPostExecute(Object obj) {
                                a((String) obj);
                            }

                            protected String a(Object... objArr) {
                                dJILibraryPhotoView.W = DJILibraryPhotoView.b(dJILibraryPhotoView.I, dJILibraryPhotoView.aa, dJILibraryPhotoView.aj);
                                return null;
                            }

                            protected void a(String str) {
                                super.onPostExecute(str);
                                dJILibraryPhotoView.S = new g(dJILibraryPhotoView.I, dJILibraryPhotoView.am, dJILibraryPhotoView.al, dJILibraryPhotoView.W, DJILibraryPhotoView.ac, DJILibraryPhotoView.B, dJILibraryPhotoView.T, dJILibraryPhotoView.ab);
                                dJILibraryPhotoView.J.setAdapter(dJILibraryPhotoView.S);
                                dJILibraryPhotoView.J.setOnGroupClickListener(new OnGroupClickListener(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                                        return true;
                                    }
                                });
                                dJILibraryPhotoView.aj = false;
                                dJILibraryPhotoView.notifyDataChanged();
                                if (dJILibraryPhotoView.W == null) {
                                    dJILibraryPhotoView.R.setVisibility(8);
                                } else if (dJILibraryPhotoView.W == null || dJILibraryPhotoView.W.size() != 0) {
                                    dJILibraryPhotoView.R.setVisibility(8);
                                } else {
                                    dJILibraryPhotoView.R.setVisibility(8);
                                }
                                dJILibraryPhotoView.Q.go();
                                dJILibraryPhotoView.updateSynState();
                            }
                        }.execute(new Object[]{""});
                        return;
                    case 2:
                        Log.i("sort", "add input >>>>>>>>");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        CharSequence format = simpleDateFormat.format(new Date());
                        i = 0;
                        int i2 = 0;
                        while (i < dJILibraryPhotoView.W.size()) {
                            Log.i("zcf", "pbrv.mAlbumGroup.get(t).getDate():" + ((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(i)).a());
                            Log.i("zcf", "today" + format);
                            if (((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(i)).a().contains(format) || ((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(i)).a().equals(format)) {
                                i2 = i;
                            }
                            i++;
                        }
                        if (dJILibraryPhotoView.W == null || dJILibraryPhotoView.W.size() <= 0 || !((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(i2)).a().contains(format)) {
                            dji.pilot.playback.litchi.a s = dJILibraryPhotoView.getAlbumFromDb();
                            if (s != null) {
                                if (dJILibraryPhotoView.W == null) {
                                    dJILibraryPhotoView.W = new ArrayList();
                                    dJILibraryPhotoView.S = new g(dJILibraryPhotoView.I, dJILibraryPhotoView.am, dJILibraryPhotoView.al, dJILibraryPhotoView.W, DJILibraryPhotoView.ac, DJILibraryPhotoView.B, dJILibraryPhotoView.T, dJILibraryPhotoView.ab);
                                    dJILibraryPhotoView.S.a(DJILibraryPhotoView.an);
                                    dJILibraryPhotoView.J.setAdapter(dJILibraryPhotoView.S);
                                    dJILibraryPhotoView.J.setGroupIndicator(null);
                                    dJILibraryPhotoView.J.setOnGroupClickListener(dJILibraryPhotoView.U);
                                    dJILibraryPhotoView.J.setEmptyView(dJILibraryPhotoView.M);
                                }
                                dJILibraryPhotoView.W.add(0, s);
                            }
                        } else {
                            ArrayList q = dJILibraryPhotoView.ad;
                            List f = ((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(i2)).f();
                            if (q != null && q.size() > 0) {
                                for (i = 0; i < q.size(); i++) {
                                    if (f.a(((MediaInfoBean) q.get(i)).getAbsPath()).booleanValue()) {
                                        dji.pilot.usercenter.mode.g gVar = new dji.pilot.usercenter.mode.g();
                                        gVar.e = ((MediaInfoBean) q.get(i)).getAbsPath();
                                        Bitmap decodeFile = BitmapFactory.decodeFile(gVar.e);
                                        if (decodeFile != null) {
                                            dJILibraryPhotoView.af.a(a$e.a + gVar.e, decodeFile);
                                        }
                                        gVar.f = ((MediaInfoBean) q.get(i)).getTitle();
                                        gVar.g = 31;
                                        DJISycAlbumModel dJISycAlbumModel = new DJISycAlbumModel(gVar, true);
                                        MixAlbumSyncManager.getInstance(dJILibraryPhotoView.I).addAlbumToList(dJISycAlbumModel);
                                        dJISycAlbumModel.fileLevel = 5;
                                        dJISycAlbumModel.islocal = false;
                                        dJISycAlbumModel.sortTime = date.getTime();
                                        f.add(0, dJISycAlbumModel);
                                    }
                                }
                            }
                        }
                        dJILibraryPhotoView.notifyDataChanged();
                        return;
                    case 7:
                        if (ServiceManager.getInstance().isRemoteOK() && e.a == 0) {
                            dJILibraryPhotoView.notifyDataChanged();
                            c.a().e(a.PhotoDIsConnect);
                            return;
                        }
                        dJILibraryPhotoView.notifyDataChanged();
                        return;
                    case 9:
                        if (DJILibraryPhotoView.C != null) {
                            dJILibraryPhotoView.a(DJILibraryPhotoView.C, false, true, dJILibraryPhotoView.D, null);
                            return;
                        }
                        return;
                    case 10:
                        if (DJILibraryPhotoView.C != null) {
                            dJILibraryPhotoView.a(DJILibraryPhotoView.C, false, true, dJILibraryPhotoView.D, null);
                            return;
                        }
                        return;
                    case 11:
                        if (DJILibraryPhotoView.C != null) {
                            dJILibraryPhotoView.a(DJILibraryPhotoView.C);
                            if (DJILibraryPhotoView.C.isOrg) {
                                String str = DJILibraryPhotoView.C.orgPath;
                                dJILibraryPhotoView.b(DJILibraryPhotoView.C.mLocalInfo.e);
                                dJILibraryPhotoView.b(str);
                            } else if (DJILibraryPhotoView.C.fileLevel != 5) {
                                dJILibraryPhotoView.b(DJILibraryPhotoView.C.mLocalInfo.e);
                            }
                            if (DJILibraryPhotoView.C.isThumb) {
                                dJILibraryPhotoView.b(DJILibraryPhotoView.C.mThumbmAbsPath);
                            }
                        }
                        if (dJILibraryPhotoView.W == null) {
                            dJILibraryPhotoView.R.setVisibility(8);
                            return;
                        }
                        if (dJILibraryPhotoView.W.size() == 0) {
                            dJILibraryPhotoView.R.setVisibility(8);
                            dJILibraryPhotoView.W.clear();
                        } else {
                            dJILibraryPhotoView.R.setVisibility(8);
                        }
                        DJILibraryPhotoView.B.remove(DJILibraryPhotoView.C);
                        int indexOfValue = DJILibraryPhotoView.ac.indexOfValue(DJILibraryPhotoView.C);
                        if (indexOfValue != -1) {
                            DJILibraryPhotoView.ac.removeAt(indexOfValue);
                        }
                        if (DJILibraryPhotoView.B.size() == 0) {
                            c.a().e(a.MainButtonClear);
                            DJILibraryPhotoView.ac.clear();
                        } else {
                            dJILibraryPhotoView.ae.enterSelectMode(DJILibraryPhotoView.B.size(), 2);
                        }
                        if (dJILibraryPhotoView.W == null || dJILibraryPhotoView.W.size() == 0 || ((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(0)).c == null || (dJILibraryPhotoView.W.size() == 1 && ((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(0)).c.size() == 0)) {
                            dJILibraryPhotoView.P.show();
                        }
                        dJILibraryPhotoView.notifyDataChanged();
                        makeText = Toast.makeText(dJILibraryPhotoView.I, R.string.v2_library_12, 0);
                        makeText.setGravity(17, 0, 0);
                        makeText.show();
                        return;
                    case 12:
                        for (i = 0; i < DJILibraryPhotoView.B.size(); i++) {
                            if (DJILibraryPhotoView.B.get(i) != null) {
                                dJILibraryPhotoView.a((DJISycAlbumModel) DJILibraryPhotoView.B.get(i));
                                if (((DJISycAlbumModel) DJILibraryPhotoView.B.get(i)).isOrg) {
                                    String str2 = ((DJISycAlbumModel) DJILibraryPhotoView.B.get(i)).orgPath;
                                    dJILibraryPhotoView.b(((DJISycAlbumModel) DJILibraryPhotoView.B.get(i)).mLocalInfo.e);
                                    dJILibraryPhotoView.b(str2);
                                } else {
                                    dJILibraryPhotoView.b(((DJISycAlbumModel) DJILibraryPhotoView.B.get(i)).mLocalInfo.e);
                                }
                                if (((DJISycAlbumModel) DJILibraryPhotoView.B.get(i)).isThumb) {
                                    dJILibraryPhotoView.b(((DJISycAlbumModel) DJILibraryPhotoView.B.get(i)).mThumbmAbsPath);
                                }
                            }
                        }
                        if (dJILibraryPhotoView.W == null) {
                            dJILibraryPhotoView.R.setVisibility(8);
                            return;
                        }
                        if (dJILibraryPhotoView.W.size() == 0) {
                            dJILibraryPhotoView.R.setVisibility(8);
                            dJILibraryPhotoView.W.clear();
                        } else {
                            dJILibraryPhotoView.R.setVisibility(8);
                        }
                        if (dJILibraryPhotoView.W == null || dJILibraryPhotoView.W.size() == 0 || ((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(0)).c == null || (dJILibraryPhotoView.W.size() == 1 && ((dji.pilot.playback.litchi.a) dJILibraryPhotoView.W.get(0)).c.size() == 0)) {
                            dJILibraryPhotoView.P.show();
                        }
                        dJILibraryPhotoView.notifyDataChanged();
                        makeText = Toast.makeText(dJILibraryPhotoView.I, R.string.v2_library_12, 0);
                        makeText.setGravity(17, 0, 0);
                        makeText.show();
                        return;
                    case 13:
                        dJILibraryPhotoView.ag.scanOrg();
                        return;
                    case 14:
                        dJILibraryPhotoView.beginPullFile(false);
                        return;
                    case 15:
                        dJILibraryPhotoView.ag.setDeleteDb(dJILibraryPhotoView.I);
                        dJILibraryPhotoView.ag.setNotShowDb(dJILibraryPhotoView.I);
                        if (ServiceManager.getInstance().isRemoteOK()) {
                            dJILibraryPhotoView.af.a();
                            dJILibraryPhotoView.ag.initScanSynDelete();
                        } else {
                            dJILibraryPhotoView.af.a();
                            dJILibraryPhotoView.clearAlbumDirectoryInfo();
                        }
                        dJILibraryPhotoView.R.setVisibility(8);
                        dJILibraryPhotoView.notifyDataChanged();
                        return;
                    case 16:
                        dJILibraryPhotoView.Q.go();
                        return;
                    case 19:
                        dJILibraryPhotoView.updateSynState();
                        return;
                    case 21:
                        dJILibraryPhotoView.ae.exitSelectMode();
                        DJILibraryPhotoView.B.clear();
                        dJILibraryPhotoView.N.setVisibility(0);
                        dJILibraryPhotoView.O.setText(R.string.v2_photolist_loading);
                        dJILibraryPhotoView.P.go();
                        dJILibraryPhotoView.O.show();
                        return;
                    case 22:
                        dJILibraryPhotoView.notifyDataChanged();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJILibraryPhotoView(Context context) {
        this(context, null);
    }

    public DJILibraryPhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJILibraryPhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.F = 3;
        this.G = h.l;
        this.H = 2;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = ImageLoader.getInstance();
        this.U = null;
        this.V = null;
        this.W = new ArrayList();
        this.aa = null;
        this.ab = null;
        this.ad = new ArrayList();
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.aj = false;
        this.aC = "DJILibraryPhotoView";
        this.D = null;
        this.aE = new ArrayList();
        this.E = 20;
        if (!isInEditMode()) {
            this.I = context;
            init();
        }
    }

    public List<dji.pilot.playback.litchi.a> getCurAlbumGroup() {
        return this.W;
    }

    public static void setCurrentPreviewAlbum(DJISycAlbumModel dJISycAlbumModel) {
        C = dJISycAlbumModel;
    }

    public static boolean isPhotoSelected(DJISycAlbumModel dJISycAlbumModel) {
        return ac.indexOfKey(dJISycAlbumModel.hashCode()) >= 0;
    }

    public void setParentView(DJILibraryView dJILibraryView) {
        this.ae = dJILibraryView;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            initMember();
            initWidget();
            this.af.a();
            this.ag.initScanLocalThread();
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ DJILibraryPhotoView a;

                {
                    this.a = r1;
                }

                public void run() {
                    HashMap hashMap = new HashMap();
                    hashMap.put(dji.pilot.fpv.d.d.dF, "" + MixAlbumSyncManager.getInstance(this.a.getContext()).showList.size());
                    dji.pilot.fpv.d.e.a(j.eB_, hashMap);
                }
            }, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
        }
    }

    public void invalidViews() {
        Log.i("DJILibraryPhotoView", "invalidViews");
        this.ad = DJIScanerMediaManager.getInstance(this.I).getMediaList(false);
        an.sendEmptyMessage(2);
        HashMap hashMap = new HashMap();
        hashMap.put(dji.pilot.fpv.d.d.dF, "" + this.ad.size());
        dji.pilot.fpv.d.e.a(c$l.cC_, hashMap);
    }

    public void addInpuVideosToAlbum(ArrayList<MediaInfoBean> arrayList) {
        Log.i("DJILibraryPhotoView", "addInpuVideosToAlbum");
        if (arrayList != null && arrayList.size() > 0) {
            dji.pilot.playback.litchi.a aVar = new dji.pilot.playback.litchi.a();
            List arrayList2 = new ArrayList();
            aVar.a(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            for (int i = 0; i < arrayList.size(); i++) {
                dji.pilot.usercenter.mode.g gVar = new dji.pilot.usercenter.mode.g();
                gVar.e = ((MediaInfoBean) arrayList.get(i)).getAbsPath();
                Log.i("DJILibraryPhotoView", "path is " + gVar.e);
                gVar.k = ((MediaInfoBean) arrayList.get(i)).getTitle();
                gVar.g = 31;
                DJISycAlbumModel dJISycAlbumModel = new DJISycAlbumModel(gVar, true);
                MixAlbumSyncManager.getInstance(this.I).addAlbumToListFromLast(dJISycAlbumModel);
                dJISycAlbumModel.fileLevel = 5;
                dJISycAlbumModel.islocal = true;
                arrayList2.add(dJISycAlbumModel);
            }
            aVar.c(arrayList2);
            this.aa.add(0, aVar);
            an.sendEmptyMessage(1);
        }
    }

    public void onEventMainThread(a aVar) {
        Toast makeText;
        switch (aVar) {
            case PhotoSelect:
                an.sendEmptyMessage(9);
                return;
            case PhotoUnSelect:
                an.sendEmptyMessage(10);
                return;
            case PhotoDelete:
                an.sendEmptyMessage(11);
                return;
            case PhotoMainDelete:
                for (int i = 0; i < B.size(); i++) {
                    if (B.get(i) != null) {
                        a((DJISycAlbumModel) B.get(i));
                        if (((DJISycAlbumModel) B.get(i)).isOrg) {
                            b(((DJISycAlbumModel) B.get(i)).mLocalInfo.e);
                            b(((DJISycAlbumModel) B.get(i)).orgPath);
                        } else if (((DJISycAlbumModel) B.get(i)).fileLevel != 5) {
                            b(((DJISycAlbumModel) B.get(i)).mLocalInfo.e);
                        }
                        if (((DJISycAlbumModel) B.get(i)).isThumb) {
                            b(((DJISycAlbumModel) B.get(i)).mThumbmAbsPath);
                        }
                    }
                }
                if (this.W == null || this.W.size() == 0) {
                    this.R.setVisibility(8);
                    this.W.clear();
                } else {
                    this.R.setVisibility(8);
                }
                B.remove(C);
                makeText = Toast.makeText(this.I, R.string.v2_library_12, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
                return;
            case PhotoAllDelete:
                an.sendEmptyMessage(15);
                makeText = Toast.makeText(this.I, R.string.v2_library_12, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
                return;
            case MEDIASDDOWNLOADEND:
                an.sendEmptyMessage(13);
                return;
            case PhotoTake:
                an.sendEmptyMessage(14);
                return;
            case PhotoDownNotify:
                if (!an.hasMessages(7)) {
                    an.sendEmptyMessageDelayed(7, 200);
                    return;
                }
                return;
            case PhotoDIsConnect:
                this.Q.go();
                return;
            default:
                return;
        }
    }

    private static List<dji.pilot.playback.litchi.a> b(Context context, List<dji.pilot.playback.litchi.a> list, boolean z) {
        Log.i("sort", "sortGroupsForAdapter");
        ArrayList arrayList = new ArrayList();
        Iterator it = DJIScanerMediaManager.getInstance(context).getImageFromDb().iterator();
        while (it.hasNext()) {
            MediaInfoBean mediaInfoBean = (MediaInfoBean) it.next();
            Log.i("sort", "info bean :" + mediaInfoBean.getAddDate());
            if (f.a(mediaInfoBean.getAbsPath()).booleanValue()) {
                int i;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    dji.pilot.playback.litchi.a aVar = (dji.pilot.playback.litchi.a) list.get(i2);
                    Log.i("sort", "info bean1 :" + aVar.a());
                    if (mediaInfoBean.getAddDate().contains(aVar.a())) {
                        Log.i("sort", "add" + mediaInfoBean.getTitle());
                        aVar.c.add(a(context, mediaInfoBean));
                        aVar.a(aVar.b() + 1);
                        i = 1;
                        break;
                    }
                }
                i = 0;
                if (i == 0) {
                    String substring;
                    Log.i("sort", "not find");
                    DJISycAlbumModel a = a(context, mediaInfoBean);
                    dji.pilot.playback.litchi.a aVar2 = new dji.pilot.playback.litchi.a();
                    int indexOf = mediaInfoBean.getAddDate().indexOf(32);
                    String addDate = mediaInfoBean.getAddDate();
                    if (indexOf != -1) {
                        substring = mediaInfoBean.getAddDate().substring(0, indexOf);
                        Log.i("sort", "sort date:" + substring);
                    } else {
                        substring = addDate;
                    }
                    aVar2.a(substring);
                    List arrayList2 = new ArrayList();
                    arrayList2.add(a);
                    aVar2.a(aVar2.b() + 1);
                    aVar2.c(arrayList2);
                    list.add(aVar2);
                }
            }
        }
        sortPic(list);
        MixAlbumSyncManager.getInstance(context).sortShowList();
        return list;
    }

    private static DJISycAlbumModel a(Context context, MediaInfoBean mediaInfoBean) {
        dji.pilot.usercenter.mode.g gVar = new dji.pilot.usercenter.mode.g();
        gVar.e = mediaInfoBean.getAbsPath();
        gVar.f = mediaInfoBean.getTitle();
        try {
            gVar.h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(mediaInfoBean.getAddDate()).getTime();
            Log.i("sort", "localAlbum.mLastMofified:" + gVar.h);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("sort", "localAlbum.mLastMofified: error");
        }
        gVar.g = 31;
        DJISycAlbumModel dJISycAlbumModel = new DJISycAlbumModel(gVar, true);
        MixAlbumSyncManager.getInstance(context).addAlbumToListFromLast(dJISycAlbumModel);
        dJISycAlbumModel.fileLevel = 5;
        dJISycAlbumModel.islocal = false;
        return dJISycAlbumModel;
    }

    public static void sortPic(List<dji.pilot.playback.litchi.a> list) {
        Collections.sort(list, new Comparator<dji.pilot.playback.litchi.a>() {
            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((dji.pilot.playback.litchi.a) obj, (dji.pilot.playback.litchi.a) obj2);
            }

            public int a(dji.pilot.playback.litchi.a aVar, dji.pilot.playback.litchi.a aVar2) {
                return aVar2.a().compareTo(aVar.a());
            }
        });
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                List f = ((dji.pilot.playback.litchi.a) list.get(i)).f();
                if (f != null && f.size() > 0) {
                    Collections.sort(f, new Comparator<DJISycAlbumModel>() {
                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return a((DJISycAlbumModel) obj, (DJISycAlbumModel) obj2);
                        }

                        public int a(DJISycAlbumModel dJISycAlbumModel, DJISycAlbumModel dJISycAlbumModel2) {
                            return DJILibraryPhotoView.b(dJISycAlbumModel2.mLocalInfo.h, true).compareTo(DJILibraryPhotoView.b(dJISycAlbumModel.mLocalInfo.h, true));
                        }
                    });
                }
            }
        }
    }

    private static String b(long j, boolean z) {
        if (z) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(j));
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(j));
    }

    private String a(String str) {
        return str;
    }

    private dji.pilot.playback.litchi.a getAlbumFromDb() {
        dji.pilot.playback.litchi.a aVar = new dji.pilot.playback.litchi.a();
        List arrayList = new ArrayList();
        ArrayList mediaList = DJIScanerMediaManager.getInstance(this.I).getMediaList(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = simpleDateFormat.format(new Date());
        Log.i("date", "date is :" + format);
        aVar.a(format);
        if (mediaList != null && mediaList.size() > 0) {
            aVar.a(mediaList.size());
            for (int i = 0; i < mediaList.size(); i++) {
                dji.pilot.usercenter.mode.g gVar = new dji.pilot.usercenter.mode.g();
                gVar.e = ((MediaInfoBean) mediaList.get(i)).getAbsPath();
                Log.i("DJILibraryPhotoView", "path is " + gVar.e);
                Bitmap decodeFile = BitmapFactory.decodeFile(gVar.e);
                if (decodeFile != null) {
                    this.af.a(a$e.a + gVar.e, decodeFile);
                }
                gVar.f = ((MediaInfoBean) mediaList.get(i)).getTitle();
                gVar.g = 31;
                gVar.y = true;
                DJISycAlbumModel dJISycAlbumModel = new DJISycAlbumModel(gVar, true);
                MixAlbumSyncManager.getInstance(this.I).addAlbumToList(dJISycAlbumModel);
                dJISycAlbumModel.fileLevel = 5;
                dJISycAlbumModel.islocal = false;
                dJISycAlbumModel.sortTime = date.getTime();
                arrayList.add(0, dJISycAlbumModel);
            }
        }
        aVar.c(arrayList);
        return aVar;
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (d.getInstance().f() && dataCameraGetPushStateInfo.getMode() == MODE.TAKEPHOTO && !dataCameraGetPushStateInfo.getIsTimePhotoing() && !dataCameraGetPushStateInfo.getIsStoring() && !d.getInstance().h() && dataCameraGetPushStateInfo.getPhotoState() == PhotoState.NO) {
            beginPullFile(false);
        }
    }

    public void onEventMainThread(dji.playback.entryActivity.e.a aVar) {
        this.ag.initScanLocalThread();
    }

    public void init() {
        an = new a(this);
        this.V = dji.pilot.playback.litchi.h.getInstance().g();
        this.aa = new ArrayList();
        this.ag = MixAlbumSyncManager.getInstance(this.I);
        this.af = b.getInstance(this.I);
        this.aD = new d(this.I);
        this.ah = new dji.pilot2.library.MixAlbumSyncManager.a(this) {
            final /* synthetic */ DJILibraryPhotoView a;

            {
                this.a = r1;
            }

            public void a() {
                DJILibraryPhotoView.an.sendEmptyMessage(21);
            }

            public void a(int i, int i2) {
                DJILibraryPhotoView.an.sendEmptyMessage(22);
            }

            public void a(ArrayList<dji.pilot.playback.litchi.a> arrayList) {
                DJILogHelper.getInstance().LOGD("", "********************************onFinish***********************", true, true);
                if (arrayList != null) {
                    Log.i("sort", "scan finish!:" + arrayList.size());
                    for (int i = 0; i < arrayList.size(); i++) {
                    }
                    this.a.aa = arrayList;
                    DJILibraryPhotoView.an.sendEmptyMessageDelayed(1, 200);
                }
            }
        };
        this.ag.registerScanListener(this.ah);
    }

    public void initWidget() {
        this.J = (ExpandableListView) findViewById(R.id.cqd);
        this.K = (DJILinearLayout) findViewById(R.id.cqf);
        this.L = (DJITextView) findViewById(R.id.cqg);
        this.M = findViewById(R.id.cqh);
        this.O = (DJITextView) findViewById(R.id.cqk);
        this.N = (ProgressBar) findViewById(R.id.cqi);
        this.P = (DJIImageView) findViewById(R.id.cqj);
        this.Q = (DJITextView) findViewById(R.id.cqe);
        this.R = (DJITextView) findViewById(R.id.cqc);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.I.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (DJIOriLayout.getDeviceType() != DJIDeviceType.Pad ? i2 < i : i2 > i) {
            i2 = i;
        }
        this.ak = i2 - (getResources().getDimensionPixelSize(R.dimen.n4) * 4);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.al = this.ak / 6;
        } else {
            this.al = this.ak / 3;
        }
        this.am = this.al;
        this.W = new ArrayList();
        this.S = new g(this.I, this.am, this.al, this.W, ac, B, this.T, this.ab);
        this.S.a(an);
        this.J.setAdapter(this.S);
        this.J.setGroupIndicator(null);
        this.J.setOnGroupClickListener(this.U);
        this.J.setEmptyView(this.M);
        this.N.setVisibility(8);
        this.P.show();
        this.O.setText(R.string.v2_photolist_connect_lose);
        an.sendEmptyMessageDelayed(19, 2000);
        this.ai = new dji.pilot.publics.b.a();
        this.ai.a(new dji.pilot.publics.b.a.a(this) {
            final /* synthetic */ DJILibraryPhotoView a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a() {
                DJILibraryPhotoView.an.sendEmptyMessageDelayed(19, 0);
            }
        });
    }

    public void initMember() {
        this.ab = new OnClickListener(this) {
            final /* synthetic */ DJILibraryPhotoView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                dji.pilot2.library.a.h hVar = (dji.pilot2.library.a.h) view.getTag();
                if (view.getId() == R.id.cpu) {
                    DJILibraryPhotoView.C = hVar.d;
                    this.a.a(hVar.d, false, true, hVar.e, null);
                    return;
                }
                boolean z;
                this.a.D = view.findViewById(R.id.cpt);
                DJILibraryPhotoView.C = hVar.d;
                Log.i("video", "holder.SaAlbum:" + hVar.d.mLocalInfo.f);
                if (DJILibraryPhotoView.ac.indexOfKey(hVar.d.hashCode()) >= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (hVar.d.isOrg) {
                    if (this.a.isFileEnable(hVar.d.mLocalInfo.e)) {
                        d.getInstance().d(true);
                        DJIPhotoPreveiwActivity.a(this.a.I, hVar.d.mLocalInfo.e, true, false, z, dji.pilot.publics.objects.a.a);
                    }
                } else if (hVar.d.fileLevel == 1) {
                    d.getInstance().d(true);
                    DJIPhotoPreveiwActivity.a(this.a.I, hVar.d.mLocalInfo.e, false, false, z, dji.pilot.publics.objects.a.a);
                } else if (hVar.d.fileLevel == 3) {
                    d.getInstance().d(true);
                    DJIPhotoPreveiwActivity.a(this.a.I, hVar.d.mLocalInfo.e, false, true, z, dji.pilot.publics.objects.a.a);
                } else if (hVar.d.fileLevel == 5) {
                    d.getInstance().d(true);
                    DJIPhotoPreveiwActivity.a(this.a.I, hVar.d.mLocalInfo.e, true, false, z, true, dji.pilot.publics.objects.a.a);
                }
            }
        };
    }

    public boolean isFileEnable(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            int available = new FileInputStream(file).available();
            Log.i(dji.pilot.college.b.b.l, "size:" + available);
            if (available > 0) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void registerEventBus() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    public void unregisterEventBus() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void clearSelect() {
        ac.clear();
        B.clear();
        if (this.aE.size() > 0) {
            for (int i = 0; i < this.aE.size(); i++) {
                this.aD.b((View) this.aE.get(i), this.J, this.S);
            }
        }
        this.aE.clear();
    }

    public void notifyDataChanged() {
        if (this.S != null && this.S != null) {
            this.S.notifyDataSetChanged();
            for (int i = 0; i < this.S.getGroupCount(); i++) {
                this.J.expandGroup(i);
            }
        }
    }

    public void cancelCurrentTask() {
        if (this.V != null) {
            this.V.c();
        }
    }

    private void b(String str) {
        if (str != null) {
            dji.pilot.storage.a.b(this.I, str);
        }
    }

    private void a(DJISycAlbumModel dJISycAlbumModel) {
        Boolean valueOf = Boolean.valueOf(false);
        for (int i = 0; i < this.W.size(); i++) {
            dji.pilot.playback.litchi.a aVar = (dji.pilot.playback.litchi.a) this.W.get(i);
            List list = aVar.c;
            if (list.contains(dJISycAlbumModel)) {
                String[] split;
                if (dJISycAlbumModel.fileLevel == 1 || dJISycAlbumModel.fileLevel == 3) {
                    split = dJISycAlbumModel.mLocalInfo.f.split("_");
                    this.ag.addDelete(this.I, split[1]);
                    this.ag.showMap.remove(split[1]);
                    this.ag.showList.remove(dJISycAlbumModel);
                    list.remove(dJISycAlbumModel);
                } else if (dJISycAlbumModel.fileLevel == 4) {
                    split = dJISycAlbumModel.orgPath.split("_");
                    try {
                        this.ag.showMap.remove(split[1]);
                        this.ag.addDelete(this.I, split[1]);
                    } catch (Exception e) {
                        this.ag.showMap.remove(split[0]);
                    }
                    this.ag.showList.remove(dJISycAlbumModel);
                    list.remove(dJISycAlbumModel);
                } else if (dJISycAlbumModel.fileLevel == 5) {
                    Log.i("zxc", "delete");
                    list.remove(dJISycAlbumModel);
                    DJIScanerMediaManager.getInstance(this.I).deleteMediaFromDb(dJISycAlbumModel.mLocalInfo.f);
                    this.ag.showList.remove(dJISycAlbumModel);
                }
            }
            if (valueOf.booleanValue()) {
                this.W.set(i, aVar);
                return;
            }
        }
    }

    private void a(DJISycAlbumModel dJISycAlbumModel, boolean z, boolean z2, View view, dji.pilot2.library.a.h hVar) {
        int hashCode = dJISycAlbumModel.hashCode();
        Log.i("zcx", "selected size:" + ac.size());
        if (z2) {
            dji.pilot2.library.a.h hVar2;
            if (ac.indexOfKey(hashCode) >= 0) {
                ac.delete(hashCode);
                B.remove(dJISycAlbumModel);
                if (view != null) {
                    view.setBackground(getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                    hVar2 = (dji.pilot2.library.a.h) view.getTag();
                    if (hVar2 != null) {
                        Log.i("zcx", "resize this");
                        this.aD.b(hVar2.a, this.J, this.S);
                        this.aE.remove(hVar2.a);
                    }
                } else if (hVar != null) {
                    this.aD.b(hVar.a, this.J, this.S);
                    this.aE.remove(hVar.a);
                }
            } else {
                ac.put(hashCode, dJISycAlbumModel);
                B.add(dJISycAlbumModel);
                if (view != null) {
                    view.setBackground(getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                    if (view != null) {
                        hVar2 = (dji.pilot2.library.a.h) view.getTag();
                        if (hVar2 != null) {
                            this.aD.c(hVar2.b);
                            this.aD.a(hVar2.a, this.J, this.S);
                            this.aE.add(hVar2.a);
                        }
                    }
                } else {
                    this.aD.c(hVar.b);
                    this.aD.a(hVar.a, this.J, this.S);
                    this.aE.add(hVar.a);
                }
            }
        } else if (z) {
            if (ac.indexOfKey(hashCode) < 0) {
                ac.put(hashCode, dJISycAlbumModel);
            }
        } else if (ac.indexOfKey(hashCode) >= 0) {
            ac.delete(hashCode);
        }
        if (ac.size() > 0) {
            this.ae.enterSelectMode(ac.size(), 2);
            return;
        }
        this.ae.exitSelectMode();
        B.clear();
    }

    public void clearAlbumDirectoryInfo() {
        ac.clear();
        B.clear();
        this.ag.initScanLocalThread();
    }

    public void beginPullFile(boolean z) {
        this.aj = true;
        if (!d.getInstance().f()) {
            updateSynState();
        } else if (z) {
            this.ag.scanPhotoFile(z);
        } else {
            this.ag.scanPhotoFile(false);
        }
    }

    public void updateSynState() {
        int g = d.getInstance().g();
        if (this.E != g) {
            this.E = g;
            switch (this.E) {
                case 0:
                    setSynView(0);
                    return;
                case 1:
                    setSynView(R.string.v2_library_syn_pic_error1);
                    return;
                case 2:
                    setSynView(R.string.v2_library_syn_pic_error2);
                    return;
                case 3:
                    setSynView(R.string.v2_library_syn_pic_error3);
                    return;
                case 4:
                    setSynView(R.string.v2_library_syn_pic_error4);
                    return;
                case 5:
                    setSynView(R.string.v2_library_syn_pic_error5);
                    return;
                case 6:
                    setSynView(R.string.v2_library_syn_pic_error6);
                    return;
                case 7:
                    setSynView(R.string.v2_library_syn_pic_error7);
                    return;
                case 8:
                    setSynView(R.string.v2_library_syn_pic_error8);
                    return;
                case 9:
                    setSynView(R.string.v2_library_syn_pic_error_sleep);
                    return;
                default:
                    return;
            }
        }
    }

    private void setSynView(int i) {
        if (i == 0) {
            this.K.go();
            return;
        }
        this.K.show();
        this.L.setText(i);
    }
}
