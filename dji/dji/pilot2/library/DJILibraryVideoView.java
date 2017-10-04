package dji.pilot2.library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$l;
import dji.pilot.playback.litchi.a;
import dji.pilot.usercenter.b.a$a;
import dji.pilot.usercenter.b.a$b;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.cutmoment.DJICutMomentActivity;
import dji.pilot2.library.a.e;
import dji.pilot2.library.a.f;
import dji.pilot2.library.a.h;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.library.model.MediaInfoBean;
import dji.pilot2.library.widget.DJIHorizontalListView;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.pilot2.media.activity.DJIMomentPreveiwActivity;
import dji.pilot2.utils.d;
import dji.pilot2.utils.p;
import dji.playback.entryActivity.e.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"HandlerLeak"})
public class DJILibraryVideoView extends RelativeLayout implements c$l {
    private static boolean af = false;
    private static final int ag = 1;
    private static final int ah = 2;
    private static final int ai = 3;
    private static final int aj = 4;
    private static final int ak = 5;
    private static final int al = 6;
    private static final int am = 7;
    private static final int an = 8;
    private static final int ao = 9;
    private static final int ap = 10;
    private static final int s = 1;
    private DJITextView A;
    private DJIRelativeLayout B;
    private DJIRelativeLayout C;
    private View D;
    private List<a$a> E;
    private List<g> F;
    private HashMap<String, g> G;
    private List<g> H;
    private DJIHorizontalListView I;
    private int J;
    private int K;
    private int L;
    private int M;
    private ExpandableListView N;
    private f O;
    private List<a> P;
    private List<g> Q;
    private List<View> R;
    private OnClickListener S;
    private DJILibraryView T;
    private DJIImageView U;
    private DJITextView V;
    private g W;
    private DJITextView aa;
    private DJILibraryFragment ab;
    private FrameLayout ac;
    private boolean ad;
    private int ae;
    private dji.pilot2.widget.a aq;
    private final String ar;
    private h as;
    private d at;
    public SparseArray<g> p;
    public List<g> q;
    Handler r;
    private Context t;
    private dji.pilot.usercenter.b.a u;
    private a$b v;
    private OnItemClickListener w;
    private e x;
    private View y;
    private ProgressBar z;

    public DJILibraryVideoView(Context context) {
        this(context, null);
    }

    public DJILibraryVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJILibraryVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.t = null;
        this.u = dji.pilot.usercenter.b.a.getInstance();
        this.v = null;
        this.w = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.N = null;
        this.O = null;
        this.p = new SparseArray(12);
        this.R = new ArrayList();
        this.q = new ArrayList();
        this.T = null;
        this.W = null;
        this.aa = null;
        this.ad = false;
        this.ae = 0;
        this.ar = "DJILibraryVideoView";
        this.r = new Handler(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        this.a.u.d(this.a.t);
                        this.a.u.c(this.a.t);
                        return;
                    case 2:
                        if (this.a.W != null) {
                            this.a.a(this.a.W, false, true, null, this.a.as);
                            return;
                        }
                        return;
                    case 3:
                        if (this.a.W != null) {
                            this.a.a(this.a.W, false, true, null, this.a.as);
                            return;
                        }
                        return;
                    case 4:
                        if (this.a.W != null) {
                            this.a.u.b(Uri.parse(this.a.W.b().o).getPath());
                            this.a.b(true);
                        }
                        this.a.q.remove(this.a.W);
                        if (this.a.q.size() == 0) {
                            this.a.U.go();
                            this.a.I.setEnabled(true);
                            if (this.a.F.size() == 0) {
                                this.a.V.setEnabled(false);
                                this.a.V.setTextColor(this.a.getResources().getColor(R.color.kq));
                            } else {
                                this.a.V.setTextColor(this.a.getResources().getColor(R.color.n8));
                                this.a.V.setEnabled(true);
                            }
                            this.a.T.exitSelectMode();
                            c.a().e(a.MainButtonClear);
                        } else {
                            this.a.T.enterSelectMode(this.a.q.size(), 1);
                        }
                        Toast makeText = Toast.makeText(this.a.t, R.string.v2_library_13, 0);
                        makeText.setGravity(17, 0, 0);
                        makeText.show();
                        return;
                    case 5:
                        this.a.u.d(this.a.t);
                        this.a.u.c(this.a.t);
                        return;
                    case 6:
                        this.a.u.d(this.a.t);
                        this.a.u.c(this.a.t);
                        return;
                    case 7:
                        this.a.i();
                        return;
                    case 8:
                        this.a.F.remove(this.a.W);
                        if (this.a.F.size() == 0) {
                            this.a.r.sendEmptyMessage(1);
                            return;
                        } else {
                            this.a.f();
                            return;
                        }
                    case 9:
                        DJILogHelper.getInstance().LOGI("zcx", "app finished>>>>>: " + dji.pilot.usercenter.b.a.a);
                        this.a.b(true);
                        return;
                    default:
                        return;
                }
            }
        };
        if (!isInEditMode()) {
            this.t = context;
            initListen();
        }
    }

    public void setParentView(DJILibraryView dJILibraryView) {
        this.T = dJILibraryView;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            e();
        }
    }

    public void setSuperFragment(DJILibraryFragment dJILibraryFragment) {
        this.ab = dJILibraryFragment;
    }

    public void invalidViews() {
        Log.i("DJILibraryVideoView", "invalidViews");
        ArrayList mediaList = DJIScanerMediaManager.getInstance(this.t).getMediaList(true);
        addInpuVideosToAlbum(mediaList);
        Log.i("DJILibraryVideoView", "size is " + this.F.size());
        this.U.go();
        this.I.setEnabled(true);
        this.V.setEnabled(true);
        this.x = new e(this.t, this.F, this.K, this.J);
        this.I.setAdapter(this.x);
        this.x.notifyDataSetChanged();
        this.z.setVisibility(8);
        this.A.setVisibility(0);
        this.B.setVisibility(8);
        this.C.setVisibility(0);
        this.A.setText(R.string.usercenter_cloudalbum_empty);
        DJIScanerMediaManager.getInstance(this.t).resetLists();
        if (this.F.size() == 0) {
            this.y.setVisibility(0);
            this.V.setEnabled(false);
            this.V.setTextColor(getResources().getColor(R.color.kq));
            DJILogHelper.getInstance().LOGI("bob", "enable false f");
        } else {
            this.y.setVisibility(8);
            this.I.setVisibility(0);
            this.V.setTextColor(getResources().getColor(R.color.n8));
            this.V.setEnabled(true);
            DJILogHelper.getInstance().LOGI("bob", "enable false d");
        }
        HashMap hashMap = new HashMap();
        hashMap.put(dji.pilot.fpv.d.d.dF, "" + mediaList.size());
        HashMap hashMap2 = new HashMap();
        Iterator it = mediaList.iterator();
        long j = 0;
        while (it.hasNext()) {
            j = ((MediaInfoBean) it.next()).getDuration() + j;
        }
        hashMap2.put(dji.pilot.fpv.d.d.dG, "" + j);
        dji.pilot.fpv.d.e.a(c$l.et_, hashMap);
        dji.pilot.fpv.d.e.a(c$l.f, hashMap2);
    }

    public void deleteVideoView() throws FileNotFoundException {
        ArrayList selectedPaths = DJIScanerMediaManager.getInstance(this.t).getSelectedPaths();
        Iterator it = this.F.iterator();
        if (selectedPaths == null || selectedPaths.size() <= 0) {
            return;
        }
        while (it.hasNext()) {
            g gVar = (g) it.next();
            for (int i = 0; i < selectedPaths.size(); i++) {
                if (gVar.e.equals(selectedPaths.get(i))) {
                    try {
                        it.remove();
                        DJIScanerMediaManager.getInstance(this.t).deleteMediaFromDbByPath((String) selectedPaths.get(i));
                        if (!(gVar.e == null || gVar.y)) {
                            dji.pilot.storage.a.b(this.t, gVar.e);
                        }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        DJIScanerMediaManager.getInstance(this.t).deleteMediaFromDbByPath((String) selectedPaths.get(i));
                        if (!(gVar.e == null || gVar.y)) {
                            dji.pilot.storage.a.b(this.t, gVar.e);
                        }
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        DJIScanerMediaManager.getInstance(this.t).deleteMediaFromDbByPath((String) selectedPaths.get(i));
                        if (!(gVar.e == null || gVar.y)) {
                            dji.pilot.storage.a.b(this.t, gVar.e);
                        }
                    }
                }
            }
        }
        ((Activity) this.t).getFragmentManager().beginTransaction().hide(((Activity) this.t).getFragmentManager().findFragmentById(R.id.cr1)).commit();
        DJILogHelper.getInstance().LOGI("bob", "deleteVideoView delete " + this.F.size());
        if (this.F.size() == 0) {
            this.y.setVisibility(0);
            this.V.setEnabled(false);
            this.V.setTextColor(getResources().getColor(R.color.kq));
        } else {
            this.y.setVisibility(8);
            this.V.setTextColor(getResources().getColor(R.color.n8));
            this.V.setEnabled(true);
        }
        f();
    }

    public void addInpuVideosToAlbum(ArrayList<MediaInfoBean> arrayList) {
        Log.i("DJILibraryVideoView", "addInpuVideosToAlbum:" + arrayList.size());
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                g gVar = new g();
                gVar.e = ((MediaInfoBean) arrayList.get(i)).getAbsPath();
                gVar.w = ((MediaInfoBean) arrayList.get(i)).getMediaId();
                a(gVar.w);
                gVar.x = ((MediaInfoBean) arrayList.get(i)).getSubNailpath();
                Log.i("DJILibraryVideoView", "sub path is " + ((MediaInfoBean) arrayList.get(i)).getSubNailpath());
                Log.i("DJILibraryVideoView", "abs path is " + gVar.e);
                gVar.k = ((MediaInfoBean) arrayList.get(i)).getTitle();
                gVar.f = ((MediaInfoBean) arrayList.get(i)).getTitle();
                gVar.g = 28;
                gVar.n = (int) ((MediaInfoBean) arrayList.get(i)).getDuration();
                gVar.y = true;
                gVar.j = true;
                if (com.dji.frame.c.f.a(gVar.e).booleanValue()) {
                    this.F.add(0, gVar);
                    this.u.a(this.F);
                }
            }
        }
    }

    private void a(int i) {
        Log.i("videos", "id:" + i);
        if (this.F != null) {
            for (g gVar : this.F) {
                if (i == gVar.w) {
                    Log.i("videos", "delete");
                    this.F.remove(gVar);
                    return;
                }
            }
        }
    }

    public void onEventMainThread(a aVar) {
        switch (aVar) {
            case MomentSelect:
                this.r.sendEmptyMessage(2);
                return;
            case MomentUnSelect:
                this.r.sendEmptyMessage(3);
                return;
            case MomentDelete:
                this.r.sendEmptyMessage(4);
                return;
            case MomentMainDelete:
                b();
                return;
            case VideoAllDelete:
                this.r.sendEmptyMessage(5);
                return;
            case MEDIASDDOWNLOADEND:
                if (!this.r.hasMessages(1)) {
                    this.r.sendEmptyMessage(1);
                    return;
                }
                return;
            case ResetLibraryLayout:
                this.r.sendEmptyMessage(7);
                return;
            case CacheDelete:
                this.r.sendEmptyMessage(8);
                return;
            case AppScanFinish:
                DJILogHelper.getInstance().LOGI("zcx", "app scan finish!");
                b(true);
                this.u.a(this.v);
                return;
            case MomentUpdateByAdd:
                DJILogHelper.getInstance().LOGI("zcx", "add moment!");
                b(false);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(dji.midware.media.j.c.a aVar) {
        DJILogHelper.getInstance().LOGE("Jackson", "EventVideoCacheCompletion");
        if (aVar.a() != null) {
            try {
                MixAlbumSyncManager.getInstance(this.t).insertDb(this.t, new File(aVar.a()).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!this.r.hasMessages(1)) {
            this.r.sendEmptyMessage(1);
            this.ad = true;
        }
    }

    public void onEventBackgroundThread(b bVar) {
        if (!this.r.hasMessages(1)) {
            this.r.sendEmptyMessage(1);
            this.ad = true;
        }
    }

    private void b() {
        if (this.q.size() > 0) {
            for (int i = 0; i < this.q.size(); i++) {
                this.W = (g) this.q.get(i);
                this.u.b(Uri.parse(this.W.b().o).getPath());
                b(true);
            }
        }
        Toast makeText = Toast.makeText(this.t, R.string.v2_library_13, 0);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    public void initListen() {
        this.F = new ArrayList();
        this.G = new HashMap();
        this.v = new a$b(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public void a(String str, int i, Object obj) {
            }

            public void b(String str, int i, Object obj) {
                DJILogHelper.getInstance().LOGI("DJILibraryVideoView", "scan end!:" + this.a.ad);
                Log.i("zcxv", "scan end :" + this.a.ad);
                if (this.a.ad) {
                    this.a.c();
                } else if (DJILibraryVideoView.af) {
                    this.a.d();
                    DJILibraryVideoView.af = false;
                } else {
                    this.a.b(true);
                }
            }

            public void c(String str, int i, Object obj) {
                DJILogHelper.getInstance().LOGI("DJILibraryVideoView", "begin!");
            }

            public void a(int i) {
            }
        };
        this.w = new OnItemClickListener(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.E != null && this.a.E.size() != 0) {
                    g gVar = (g) this.a.F.get(i);
                    if (gVar.e == null) {
                        return;
                    }
                    if (com.dji.frame.c.f.a(gVar.e).booleanValue()) {
                        this.a.a(gVar);
                        return;
                    }
                    this.a.F.remove(gVar);
                    this.a.f();
                }
            }
        };
    }

    private void a(g gVar) {
        this.W = gVar;
        if (gVar.j) {
            gVar.j = false;
            MixAlbumSyncManager.getInstance(this.t).removeNewDb(this.t, gVar.f);
            if (this.x != null) {
                this.x.notifyDataSetChanged();
            }
        }
        String[] strArr = new String[]{Uri.parse(gVar.b().o).getPath()};
        Intent intent = new Intent(this.t, DJICutMomentActivity.class);
        intent.putExtra(DJICutMomentActivity.K, strArr);
        intent.putExtra(DJICutMomentActivity.N, gVar.y);
        intent.setAction("android.intent.action.INSERT");
        ((Activity) this.t).startActivityForResult(intent, 4097);
    }

    public void handleNewMemontCreate(ArrayList<String> arrayList, boolean z) {
        if (arrayList != null && arrayList.size() != 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                File file = new File((String) arrayList.get(i));
                if (file.isFile() && file.exists()) {
                    this.u.a(file, z);
                }
            }
            b(false);
        }
    }

    private void a(g gVar, boolean z, boolean z2, View view, h hVar) {
        int hashCode = gVar.hashCode();
        if (z2) {
            h hVar2;
            if (this.p.indexOfKey(hashCode) >= 0) {
                this.p.delete(hashCode);
                this.q.remove(gVar);
                if (view != null) {
                    view.setBackground(getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                    hVar2 = (h) view.getTag();
                    if (hVar2 != null) {
                        this.at.a(hVar2.a, this.O, this.N);
                        this.R.remove(hVar2.a);
                    }
                } else if (hVar != null) {
                    this.at.a(hVar.a, this.O, this.N);
                    this.R.remove(hVar.a);
                }
            } else {
                this.p.put(hashCode, gVar);
                this.q.add(gVar);
                if (view != null) {
                    view.setBackground(getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                    hVar2 = (h) view.getTag();
                    if (hVar2 != null) {
                        this.at.c(hVar2.b);
                        this.at.b(hVar2.a, this.O, this.N);
                        this.R.add(hVar2.a);
                    }
                } else if (hVar != null) {
                    this.at.c(hVar.b);
                    this.at.b(hVar.a, this.O, this.N);
                    this.R.add(hVar.a);
                }
            }
        } else if (z) {
            if (this.p.indexOfKey(hashCode) < 0) {
                this.p.put(hashCode, gVar);
            }
        } else if (this.p.indexOfKey(hashCode) >= 0) {
            this.p.delete(hashCode);
        }
        if (this.p.size() > 0) {
            this.U.show();
            this.I.setEnabled(false);
            this.V.setEnabled(false);
            this.V.setTextColor(getResources().getColor(R.color.kq));
            this.T.enterSelectMode(this.p.size(), 1);
            return;
        }
        this.U.go();
        this.I.setEnabled(true);
        if (this.F.size() == 0) {
            this.V.setEnabled(false);
            this.V.setTextColor(getResources().getColor(R.color.kq));
        } else {
            this.V.setTextColor(getResources().getColor(R.color.n8));
            this.V.setEnabled(true);
        }
        this.T.exitSelectMode();
    }

    public void clearSelect() {
        this.U.go();
        this.I.setEnabled(true);
        if (this.F.size() == 0) {
            this.V.setEnabled(false);
            this.V.setTextColor(getResources().getColor(R.color.kq));
        } else {
            this.V.setTextColor(getResources().getColor(R.color.n8));
            this.V.setEnabled(true);
        }
        this.p.clear();
        this.q.clear();
        if (this.R.size() > 0) {
            for (int i = 0; i < this.R.size(); i++) {
                this.at.a((View) this.R.get(i), this.O, this.N);
            }
        }
        this.R.clear();
    }

    public void setParentViewPager(ViewPager viewPager) {
        this.I.setViewPager(viewPager);
    }

    private void c() {
        this.E = this.u.b();
        if (this.E != null) {
            DJILogHelper.getInstance().LOGD("DJILibraryVideoView", "mAlbums size is " + this.E.size());
        }
        if (this.E.size() > 0) {
            this.H = ((a$a) this.E.get(0)).d;
            if (this.H.size() > 0) {
                for (int i = 0; i < this.H.size(); i++) {
                    g gVar = (g) this.H.get(i);
                    if (dji.pilot.usercenter.f.d.b(gVar.g) && b.a(gVar.f) && gVar.n >= 10) {
                        boolean z;
                        if (this.F.size() > 0) {
                            z = false;
                            for (g gVar2 : this.F) {
                                if (gVar2.f.equals(gVar.f)) {
                                    z = false;
                                    break;
                                }
                                z = true;
                            }
                        } else {
                            z = true;
                        }
                        if (z) {
                            this.F.add(0, gVar);
                            gVar.j = true;
                            this.G.put(gVar.f, gVar);
                        }
                    }
                }
            }
        }
        this.ad = false;
        f();
    }

    private void b(boolean z) {
        this.E = this.u.b();
        if (this.E != null) {
            DJILogHelper.getInstance().LOGD("DJILibraryVideoView", "mAlbums size is " + this.E.size());
        }
        if (z) {
            int i;
            this.F.clear();
            this.G.clear();
            if (this.E.size() > 0) {
                int i2;
                this.H = ((a$a) this.E.get(0)).d;
                if (this.H != null) {
                    int size = this.H.size();
                    DJILogHelper.getInstance().LOGD("DJILibraryVideoView", "mListCount  is " + size);
                    i = size;
                } else {
                    boolean z2 = false;
                }
                ArrayList videoFromDb = DJIScanerMediaManager.getInstance(this.t).getVideoFromDb();
                if (videoFromDb != null) {
                    for (i2 = 0; i2 < videoFromDb.size(); i2++) {
                        g gVar = new g();
                        gVar.e = ((MediaInfoBean) videoFromDb.get(i2)).getAbsPath();
                        Log.i("DJILibraryVideoView", "path is " + gVar.e);
                        gVar.k = ((MediaInfoBean) videoFromDb.get(i2)).getTitle();
                        Log.i("DJILibraryVideoView", "title is " + gVar.k);
                        gVar.f = ((MediaInfoBean) videoFromDb.get(i2)).getTitle();
                        gVar.w = ((MediaInfoBean) videoFromDb.get(i2)).getMediaId();
                        if (b.a(gVar.f)) {
                            gVar.g = 28;
                            gVar.n = (int) ((MediaInfoBean) videoFromDb.get(i2)).getDuration();
                            if (com.dji.frame.c.f.a(gVar.e).booleanValue()) {
                                gVar.y = true;
                                try {
                                    gVar.h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(((MediaInfoBean) videoFromDb.get(i2)).getAddDate()).getTime();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                this.F.add(0, gVar);
                            }
                        } else {
                            Log.i("DJILibraryVideoView", "not video ");
                        }
                    }
                }
                for (i2 = 0; i2 < i; i2++) {
                    g gVar2 = (g) this.H.get(i2);
                    if (dji.pilot.usercenter.f.d.b(gVar2.g) && b.a(gVar2.f) && gVar2.n >= 10) {
                        this.F.add(gVar2);
                        this.G.put(gVar2.f, gVar2);
                    }
                }
                a(this.F);
            }
            List newDb = MixAlbumSyncManager.getInstance(this.t).getNewDb(this.t);
            if (newDb.size() > 0) {
                for (i = 0; i < newDb.size(); i++) {
                    if (this.G.containsKey(((DJIVideoNewList) newDb.get(i)).getName())) {
                        ((g) this.G.get(((DJIVideoNewList) newDb.get(i)).getName())).j = true;
                    }
                }
            }
            this.u.a(this.F);
            this.x = new e(this.t, this.F, this.K, this.J);
            this.I.setAdapter(this.x);
            this.I.setEmptyView(this.y);
            this.z.setVisibility(8);
            this.A.setVisibility(0);
            this.A.setText(R.string.usercenter_cloudalbum_empty);
            if (this.F.size() == 0) {
                this.y.setVisibility(0);
                this.V.setEnabled(false);
                this.V.setTextColor(getResources().getColor(R.color.kq));
                DJILogHelper.getInstance().LOGI("bob", "enable false f");
            } else {
                this.y.setVisibility(8);
                this.I.setVisibility(0);
                this.V.setTextColor(getResources().getColor(R.color.n8));
                this.V.setEnabled(true);
                DJILogHelper.getInstance().LOGI("bob", "enable false d");
            }
        }
        if (this.E.size() > 1) {
            this.Q = ((a$a) this.E.get(1)).d;
            DJILogHelper.getInstance().LOGD("DJILibraryVideoView", ">>>>>>>>>><<<<<<<<<<<unSortAlbum:" + this.Q.size());
            sortPic(this.Q, this.P);
        }
        DJILogHelper.getInstance().LOGD("DJILibraryVideoView", ">>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<mAlbumGroup:" + this.P.size());
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.O = new f(this.t, this.M, this.L, this.P, this.p, this.q, this.S, this.ae);
        } else {
            this.O = new f(this.t, this.M, this.L, this.P, this.p, this.q, this.S, this.ae);
        }
        this.N.setAdapter(this.O);
        this.N.setEmptyView(this.D);
        this.N.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return true;
            }
        });
        if (this.P.size() == 0) {
            this.aa.setVisibility(8);
        } else {
            this.aa.setVisibility(0);
            if (dji.pilot2.widget.a.a(this.t, 2)) {
                h();
            }
        }
        DJILogHelper.getInstance().LOGI("bob", "cachealbum scene");
        if (this.P.size() == 0 && this.F.size() == 0) {
            this.B.setVisibility(0);
            this.C.setVisibility(8);
        } else {
            this.B.setVisibility(8);
            this.C.setVisibility(0);
        }
        f();
    }

    private void a(List<g> list) {
        Collections.sort(list, new Comparator<g>(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((g) obj, (g) obj2);
            }

            public int a(g gVar, g gVar2) {
                return gVar2.b().h.compareTo(gVar.b().h);
            }
        });
    }

    private void d() {
        this.E = this.u.b();
        if (this.E.size() > 1) {
            this.Q = ((a$a) this.E.get(1)).d;
            sortPic(this.Q, this.P);
        }
        if (this.O != null) {
            this.O.notifyDataSetChanged();
            for (int i = 0; i < this.O.getGroupCount(); i++) {
                if (this.N != null) {
                    this.N.expandGroup(i);
                }
            }
        }
        if (this.P.size() != 0 && dji.pilot2.widget.a.a(this.t, 2)) {
            h();
        }
    }

    private void e() {
        DJILogHelper.getInstance().LOGI("DJILibraryVideoView", "init");
        this.at = new d(this.t);
        this.I = (DJIHorizontalListView) findViewById(R.id.cqr);
        this.I.setOnItemClickListener(this.w);
        this.ac = (FrameLayout) findViewById(R.id.cr1);
        this.U = (DJIImageView) findViewById(R.id.cqv);
        this.y = findViewById(R.id.cqs);
        this.A = (DJITextView) findViewById(R.id.cqu);
        this.z = (ProgressBar) findViewById(R.id.cqt);
        this.B = (DJIRelativeLayout) findViewById(R.id.cql);
        this.C = (DJIRelativeLayout) findViewById(R.id.cqn);
        this.D = findViewById(R.id.cr0);
        this.aa = (DJITextView) findViewById(R.id.cqc);
        this.V = (DJITextView) findViewById(R.id.cqw);
        this.V.setVisibility(0);
        this.V.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Log.i("zc", "cache manage click !");
                this.a.ac.setVisibility(0);
                if (this.a.F != null && this.a.F.size() > 0) {
                    String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    ArrayList arrayList = new ArrayList();
                    DJIScanerMediaManager.a aVar = new DJIScanerMediaManager.a();
                    ArrayList arrayList2 = new ArrayList();
                    aVar.c = true;
                    aVar.b = format;
                    for (int i = 0; i < this.a.F.size(); i++) {
                        DJIScanerMediaManager.c cVar = new DJIScanerMediaManager.c();
                        cVar.d = ((g) this.a.F.get(i)).n;
                        Log.i("zc", "mDutation is :" + cVar.d);
                        cVar.b = ((g) this.a.F.get(i)).e;
                        cVar.a = i;
                        Log.i("zc", "video path is " + cVar.b);
                        if (com.dji.frame.c.f.a(cVar.b).booleanValue()) {
                            arrayList2.add(cVar);
                        }
                    }
                    aVar.d = arrayList2;
                    arrayList.add(aVar);
                    Fragment aVar2 = new dji.pilot2.library.b.a(this.a.t, true, arrayList);
                    if (this.a.ab != null) {
                        Log.i("zc", "not null");
                        aVar2.a(this.a.T);
                    }
                    FragmentTransaction beginTransaction = ((Activity) this.a.t).getFragmentManager().beginTransaction();
                    beginTransaction.replace(R.id.cr1, aVar2);
                    beginTransaction.commit();
                    this.a.T.enterSelectMode(0, 4);
                }
            }
        });
        WindowManager windowManager;
        DisplayMetrics displayMetrics;
        int i;
        int i2;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.K = getResources().getDimensionPixelSize(R.dimen.h4);
            this.J = (this.K * 9) / 16;
            windowManager = (WindowManager) this.t.getSystemService("window");
            displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            if (i2 <= i) {
                i2 = i;
            }
            this.ae = 6;
            this.M = (i2 / this.ae) - 10;
            this.L = this.M;
        } else {
            this.ae = 3;
            windowManager = (WindowManager) this.t.getSystemService("window");
            displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            if (i <= i2) {
                i2 = i;
            }
            this.K = getResources().getDimensionPixelSize(R.dimen.h3);
            this.J = getResources().getDimensionPixelSize(R.dimen.h3);
            this.M = i2 / this.ae;
            this.L = i2 / this.ae;
        }
        this.N = (ExpandableListView) findViewById(R.id.cqz);
        this.N.setGroupIndicator(null);
        this.P = new ArrayList();
        this.S = new OnClickListener(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean z = true;
                if (view.getId() == R.id.cpu) {
                    h hVar = (h) view.getTag();
                    this.a.W = hVar.c;
                    this.a.a(hVar.c, false, true, hVar.e, null);
                    return;
                }
                h hVar2 = (h) view.getTag();
                this.a.as = hVar2;
                this.a.W = hVar2.c;
                if (this.a.p.get(hVar2.c.hashCode()) == null) {
                    z = false;
                }
                Log.i("videoeditor", "info :" + hVar2.c.e);
                Log.i("videoeditor", "file type :" + hVar2.c.g);
                DJIMomentPreveiwActivity.a(this.a.t, hVar2.c.b(), z, dji.pilot.publics.objects.a.a);
            }
        };
        this.ad = false;
        this.u.a(this.v);
        DJILogHelper.getInstance().LOGI("zcx", "AppScanFinished is " + dji.pilot.usercenter.b.a.a);
        new Thread(new Runnable(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public void run() {
                while (!dji.pilot.usercenter.b.a.a) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.a.r.sendEmptyMessage(9);
            }
        }).start();
    }

    private void f() {
        if (this.x != null) {
            this.x.notifyDataSetChanged();
        }
        if (this.O != null) {
            this.O.notifyDataSetChanged();
            for (int i = 0; i < this.O.getGroupCount(); i++) {
                if (this.N != null) {
                    this.N.expandGroup(i);
                }
            }
        }
    }

    public void dismissManager() {
        if (this.ac != null) {
            this.ac.setVisibility(8);
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.u.b(this.v);
        unregisterEventBus();
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

    public void sortPic(List<g> list, List<a> list2) {
        Collections.sort(list, new Comparator<g>(this) {
            final /* synthetic */ DJILibraryVideoView a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((g) obj, (g) obj2);
            }

            public int a(g gVar, g gVar2) {
                return gVar2.b().h.compareTo(gVar.b().h);
            }
        });
        initAlbumGroup(list, list2);
    }

    public void initAlbumGroup(List<g> list, List<a> list2) {
        List arrayList = new ArrayList();
        list2.clear();
        if (list.size() > 0) {
            a aVar;
            int i = 0;
            String str = null;
            int i2 = 0;
            while (i < list.size()) {
                int i3;
                String trim;
                if (i <= 0 || !dji.pilot.usercenter.f.d.b(((g) list.get(i - 1)).g)) {
                    i3 = i2 + 1;
                    trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                    arrayList.add(list.get(i));
                } else if (((g) list.get(i)).b().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim())) {
                    i3 = i2 + 1;
                    trim = ((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim();
                    arrayList.add(list.get(i));
                } else {
                    aVar = new a();
                    aVar.a(str);
                    aVar.a(0);
                    aVar.b(i2);
                    aVar.a(arrayList);
                    list2.add(aVar);
                    trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                    i3 = 1;
                    arrayList.clear();
                    arrayList.add(list.get(i));
                }
                i++;
                i2 = i3;
                str = trim;
            }
            aVar = new a();
            aVar.a(str);
            aVar.a(0);
            aVar.b(i2);
            aVar.a(arrayList);
            list2.add(aVar);
            DJILogHelper.getInstance().LOGD("DJILibraryVideoView", "init album finish and the size is " + list2.size());
        }
    }

    private void g() {
        this.aq = new dji.pilot2.widget.a(this.t, R.style.hf, 2);
        LayoutParams attributes = this.aq.getWindow().getAttributes();
        int[] c = p.c(this.t);
        attributes.width = c[0];
        attributes.height = c[1] + 100;
        this.aq.getWindow().setAttributes(attributes);
        this.aq.a(0.0f);
        c = new int[2];
        View childAt = this.N.getChildAt(1);
        if (childAt != null) {
            ImageView imageView = (ImageView) childAt.findViewById(R.id.cpt);
            imageView.getLocationInWindow(c);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.fj);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.fj);
            this.aq.a(c[0] - dimensionPixelSize2, c[1] - dimensionPixelSize2, (imageView.getWidth() / 2) + dimensionPixelSize2, dimensionPixelSize);
            this.aq.show();
        }
    }

    private void h() {
        if (this.T == null || this.T.p == null || this.T.p.b == null) {
            Log.i(dji.pilot2.widget.a.a, " null");
        } else if (!(this.T.p.b instanceof DJIMainFragmentActivity)) {
        } else {
            if (((DJIMainFragmentActivity) this.T.p.b).a() == 1) {
                showTipLibrary();
            } else {
                dji.pilot2.widget.a.a(true);
            }
        }
    }

    public void showTipLibrary() {
        if (this.P.size() != 0) {
            dji.pilot2.widget.a.b(this.t, 2);
            this.r.postDelayed(new Runnable(this) {
                final /* synthetic */ DJILibraryVideoView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.g();
                }
            }, 200);
        }
    }

    private void i() {
        this.T.goToVideoTab();
        this.N.smoothScrollToPosition(0);
    }
}
