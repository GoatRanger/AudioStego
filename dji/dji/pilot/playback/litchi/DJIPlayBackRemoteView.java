package dji.pilot.playback.litchi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.dji.frame.c.l;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.f;
import dji.logic.album.a.e;
import dji.logic.album.model.DJIAlbumDirInfo;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.model.P3.DataCameraDeleteFile;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.widget.b;
import dji.pilot.usercenter.mode.PhotoPreviewInfo;
import dji.pilot.usercenter.mode.VideoPreviewInfo;
import dji.pilot.usercenter.widget.DJIProgressBar;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import lecho.lib.hellocharts.model.h;

public class DJIPlayBackRemoteView extends DJIPlayBackBaseView {
    private static final String C = "yyyy-MM-dd HH:mm:ss";
    private static final int M = 1;
    private static final int N = 2;
    private static final SparseArray<DJIAlbumFileInfo> q = new SparseArray(12);
    private static final SparseArray<DJIAlbumFileInfo> r = new SparseArray(12);
    private boolean A;
    private boolean B;
    private d D;
    private DataCameraDeleteFile E;
    private DJIAlbumFileInfo F;
    private b G;
    private boolean H;
    private boolean I;
    private ArrayList<Integer> J;
    private ArrayList<Integer> K;
    private a L;
    private Thread O;
    private boolean P;
    private boolean Q;
    private int R;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    boolean e;
    private int f;
    private final float g;
    private ExpandableListView h;
    private View i;
    private ProgressBar j;
    private DJITextView k;
    private ImageView l;
    private OnGroupClickListener m;
    private OnClickListener n;
    private Context o;
    private g p;
    private dji.logic.album.a.d.a<DJIAlbumDirInfo> s;
    private OnScrollListener t;
    private e u;
    private DJIAlbumDirInfo v;
    private List<a> w;
    private b x;
    private h y;
    private boolean z;

    public static class a extends Handler {
        private final WeakReference<DJIPlayBackRemoteView> a;

        public a(DJIPlayBackRemoteView dJIPlayBackRemoteView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIPlayBackRemoteView);
        }

        public void handleMessage(Message message) {
            DJIPlayBackRemoteView dJIPlayBackRemoteView = (DJIPlayBackRemoteView) this.a.get();
            if (dJIPlayBackRemoteView != null) {
                switch (message.what) {
                    case 1:
                        if (dJIPlayBackRemoteView.Q) {
                            dJIPlayBackRemoteView.Q = false;
                            Toast.makeText(dJIPlayBackRemoteView.o, dJIPlayBackRemoteView.o.getString(R.string.playback_delete_fail), 0).show();
                        }
                        if (DJIPlayBackRemoteView.q.size() > 0) {
                            dJIPlayBackRemoteView.p.b.setEnabled(true);
                            dJIPlayBackRemoteView.p.c.setEnabled(true);
                        } else {
                            dJIPlayBackRemoteView.p.b.setEnabled(false);
                            dJIPlayBackRemoteView.p.c.setEnabled(false);
                        }
                        DJIPlayBackActivity dJIPlayBackActivity = (DJIPlayBackActivity) dJIPlayBackRemoteView.o;
                        if (dJIPlayBackRemoteView.w.size() == 0) {
                            dJIPlayBackActivity.f.setVisibility(4);
                        } else {
                            dJIPlayBackActivity.f.setVisibility(0);
                        }
                        dJIPlayBackRemoteView.k.setText(R.string.usercenter_album_empty);
                        dJIPlayBackRemoteView.h.setEnabled(true);
                        dJIPlayBackRemoteView.notifyDataChanged();
                        return;
                    case 2:
                        if (dJIPlayBackRemoteView.Q) {
                            dJIPlayBackRemoteView.Q = false;
                            Toast.makeText(dJIPlayBackRemoteView.o, dJIPlayBackRemoteView.o.getString(R.string.playback_delete_fail), 0).show();
                        }
                        dJIPlayBackRemoteView.h.setEnabled(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIPlayBackRemoteView(Context context) {
        this(context, null);
    }

    public DJIPlayBackRemoteView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIPlayBackRemoteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 4;
        this.g = h.l;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.a = true;
        this.z = false;
        this.A = false;
        this.B = false;
        this.b = false;
        this.c = false;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = false;
        this.I = false;
        this.J = null;
        this.K = null;
        this.L = null;
        this.O = null;
        this.P = true;
        this.d = false;
        this.Q = false;
        this.R = 0;
        this.e = false;
        if (!isInEditMode()) {
            this.o = context;
            k();
        }
    }

    public void attachFragment(g gVar) {
        this.p = gVar;
    }

    public void detachFragment() {
        this.p = null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        this.e = false;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        b();
        super.onConfigurationChanged(configuration);
    }

    private void b() {
        if (getResources().getConfiguration().orientation == 2) {
            this.f = 4;
        } else {
            this.f = 3;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.o.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int dimensionPixelSize = (displayMetrics.widthPixels - ((this.f + 1) * getResources().getDimensionPixelSize(R.dimen.n3))) / this.f;
        this.x = new b(this.o, this.f, (int) (((float) dimensionPixelSize) * h.l), dimensionPixelSize, this.w, q, r, this.n, true);
        this.h.setAdapter(this.x);
        notifyDataChanged();
    }

    public ExpandableListView getListView() {
        return this.h;
    }

    public void checkDownload() {
        r.clear();
        c.a();
        for (int i = 0; i < this.v.c.size(); i++) {
            if (c.b(((DJIAlbumFileInfo) this.v.c.get(i)).b())) {
                r.put(((DJIAlbumFileInfo) this.v.c.get(i)).hashCode(), this.v.c.get(i));
            }
        }
    }

    public void selectPic(DJIAlbumFileInfo dJIAlbumFileInfo) {
        q.put(dJIAlbumFileInfo.hashCode(), dJIAlbumFileInfo);
        notifyDataChanged();
    }

    public void selectAllPic() {
        if (this.w != null) {
            int i = 0;
            while (i < this.w.size()) {
                int i2 = 0;
                while (i2 < ((a) this.w.get(i)).e().size()) {
                    if (((DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i2)).j == f.a || ((DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i2)).j == f.e || ((DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i2)).j == f.d || ((DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i2)).j == f.b || ((DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i2)).j == f.c || ((DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i2)).j == f.f) {
                        selectPic((DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i2));
                    }
                    i2++;
                }
                i++;
            }
            if (q.size() > 0) {
                this.p.b.setEnabled(true);
                this.p.c.setEnabled(true);
                return;
            }
            this.p.b.setEnabled(false);
            this.p.c.setEnabled(false);
        }
    }

    public void clearSelects() {
        q.clear();
        notifyDataChanged();
        if (q.size() > 0) {
            this.p.b.setEnabled(true);
            this.p.c.setEnabled(true);
            return;
        }
        this.p.b.setEnabled(false);
        this.p.c.setEnabled(false);
    }

    public void downloadSelects(DJIImageView dJIImageView, DJITextView dJITextView, DJITextView dJITextView2, DJIProgressBar dJIProgressBar) {
        cancelCurrentTask();
        int size = q.size();
        if (size == 0) {
            Toast.makeText(this.o, R.string.usercenter_album_select_none, 0).show();
            return;
        }
        List arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            DJIAlbumFileInfo dJIAlbumFileInfo = (DJIAlbumFileInfo) q.valueAt(i);
            f fVar = dJIAlbumFileInfo.j;
            if (r.indexOfKey(dJIAlbumFileInfo.hashCode()) >= 0) {
                i3 = i2;
            } else {
                if (fVar != f.b && (!dJIAlbumFileInfo.j.b() || !dJIAlbumFileInfo.i.c())) {
                    arrayList.add(q.valueAt(i));
                } else if (i2 == 0) {
                    Toast.makeText(this.o, R.string.fpv_playback_download_desc, 1).show();
                    i3 = 1;
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (arrayList.size() > 0) {
            dJITextView.setText(this.o.getString(R.string.playback_downloading, new Object[]{Integer.valueOf(1), Integer.valueOf(arrayList.size())}));
            this.y.a(this.o, dJIProgressBar, dJIImageView, dJITextView, dJITextView2, arrayList, this.h, this.x, r);
            return;
        }
        this.p.g();
    }

    public void deleteSelects() {
        if (q.size() == 0) {
            Toast.makeText(this.o, R.string.usercenter_album_select_none, 0).show();
        } else {
            d();
        }
    }

    public void deleteAlbum(final int i) {
        this.H = false;
        this.O = new Thread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackRemoteView b;

            public void run() {
                int i = 0;
                while (this.b.y.b) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (i != -1) {
                    List list = this.b.v.c;
                    int i2 = 0;
                    while (i2 < list.size()) {
                        int i3 = i + 1;
                        if (i3 > i) {
                            break;
                        }
                        i2++;
                        i = i3;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(((DJIAlbumFileInfo) list.get(i2)).d));
                    this.b.F = (DJIAlbumFileInfo) list.get(i2);
                    this.b.E.setIndexs(arrayList);
                    this.b.E.setNum(arrayList.size());
                    this.b.E.start(this.b.D);
                } else if (this.b.F != null) {
                    DJILogHelper.getInstance().LOGD("", "start delete", false, true);
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(Integer.valueOf(this.b.F.d));
                    this.b.E.setIndexs(arrayList2);
                    this.b.E.setNum(arrayList2.size());
                    this.b.E.start(this.b.D);
                }
            }
        });
        this.O.start();
    }

    private void c() {
        int i;
        int i2 = 0;
        List list = this.v.c;
        List arrayList = new ArrayList();
        DJILogHelper.getInstance().LOGD("fail", "mFailList Size:" + this.K.size(), false, true);
        for (int i3 = 0; i3 < this.K.size(); i3++) {
        }
        for (i = 0; i < list.size(); i++) {
            for (int i4 = 0; i4 < this.K.size(); i4++) {
                if (((DJIAlbumFileInfo) list.get(i)).d == ((Integer) this.K.get(i4)).intValue()) {
                    arrayList.add(list.get(i));
                }
            }
        }
        DJILogHelper.getInstance().LOGD("fail", "my fail list:" + arrayList.size(), false, true);
        for (i = 0; i < arrayList.size(); i++) {
            q.delete(((DJIAlbumFileInfo) arrayList.get(i)).hashCode());
        }
        while (i2 < q.size()) {
            list.remove(q.valueAt(i2));
            i2++;
        }
        q.clear();
        this.K.clear();
        sortPic(list, this.w, true);
        this.L.sendEmptyMessage(1);
    }

    private void d() {
        if (this.G == null) {
            this.G = b.a(this.o, (int) R.string.fpv_playback_del_image, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackRemoteView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.f();
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackRemoteView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.g();
                }
            });
            this.G.setCancelable(true);
            this.G.setCanceledOnTouchOutside(true);
        }
        if (this.G != null && !this.G.isShowing()) {
            this.G.a((int) R.string.fpv_playback_del_image);
            this.G.show();
        }
    }

    private void e() {
        if (this.G != null && this.G.isShowing()) {
            this.G.dismiss();
            this.G = null;
        }
    }

    private void f() {
        e();
    }

    private void g() {
        int i;
        int i2 = 0;
        e();
        this.h.setEnabled(false);
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < q.size(); i++) {
            arrayList.add(q.valueAt(i));
        }
        this.J = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.J.add(Integer.valueOf(((DJIAlbumFileInfo) it.next()).d));
        }
        if (this.J.size() == this.v.c.size()) {
            this.H = false;
            this.F = null;
            this.E.setNum(-1);
            this.E.start(this.D);
            DJILogHelper.getInstance().LOGD("playback", "进入全部删除的逻辑", false, true);
        } else if (this.J.size() <= 20) {
            this.H = false;
            this.F = null;
            this.E.setIndexs(this.J);
            this.E.setNum(this.J.size());
            this.E.start(this.D);
        } else {
            this.H = true;
            arrayList = new ArrayList();
            for (i = 0; i < 20; i++) {
                arrayList.add(this.J.get(i));
            }
            while (i2 < 20) {
                this.J.remove(arrayList.get(i2));
                i2++;
            }
            this.E.setIndexs(arrayList);
            this.E.setNum(arrayList.size());
            this.E.start(this.D);
        }
    }

    private void h() {
        this.u = h.getInstance().g();
        this.s = new dji.logic.album.a.d.a<DJIAlbumDirInfo>(this) {
            final /* synthetic */ DJIPlayBackRemoteView a;

            {
                this.a = r1;
            }

            public void a() {
                DJILogHelper.getInstance().LOGD("DirInfo", "DirInfo Pull Start", false, true);
                if (this.a.d) {
                    Log.d("test playback", "onStart");
                    this.a.u.c();
                }
            }

            public void a(long j, long j2, long j3) {
            }

            public void a(long j, long j2) {
            }

            public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
                DJILogHelper.getInstance().LOGD("DirInfo", "DirInfo Pull Fail: " + dJIAlbumPullErrorType.toString(), false, true);
                if (!this.a.d) {
                    if (dji.pilot.fpv.d.b.h(null)) {
                        if (this.a.R < 2) {
                            this.a.getAlbumDirectoryInfo();
                            this.a.R = this.a.R + 1;
                            return;
                        }
                        this.a.h.setEmptyView(this.a.i);
                        this.a.j.setVisibility(8);
                        this.a.l.setVisibility(0);
                        this.a.k.setText(R.string.usercenter_album_load_retry);
                    } else if (this.a.R < 3) {
                        this.a.getAlbumDirectoryInfo();
                        this.a.R = this.a.R + 1;
                    } else {
                        this.a.h.setEmptyView(this.a.i);
                        this.a.j.setVisibility(8);
                        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
                        if (sDCardState == SDCardState.None || sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal) {
                            this.a.k.setText(R.string.usercenter_album_load_clouds_no_sdcard);
                        } else {
                            this.a.k.setText(R.string.usercenter_album_load_clouds_fail);
                        }
                    }
                }
            }

            public void a(DJIAlbumDirInfo dJIAlbumDirInfo) {
                this.a.v = dJIAlbumDirInfo;
                if (dJIAlbumDirInfo.c.size() == 0) {
                    this.a.h.setEmptyView(this.a.i);
                    this.a.j.setVisibility(8);
                    this.a.k.setText(R.string.usercenter_album_empty);
                } else {
                    this.a.h.setEmptyView(this.a.i);
                    this.a.j.setVisibility(8);
                }
                this.a.j();
            }
        };
        this.E = DataCameraDeleteFile.getInstance();
        this.D = new d(this) {
            final /* synthetic */ DJIPlayBackRemoteView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                int i = 0;
                DJILogHelper.getInstance().LOGD("FileDelete", "File Delete Success", false, true);
                DJILogHelper.getInstance().LOGD("FileDelete", "delTmp = " + this.a.F, false, true);
                if (this.a.H) {
                    if (this.a.J.size() > 20) {
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < 20; i2++) {
                            arrayList.add(this.a.J.get(i2));
                        }
                        while (i < 20) {
                            this.a.J.remove(arrayList.get(i));
                            i++;
                        }
                        this.a.E.setIndexs(arrayList);
                        this.a.E.setNum(arrayList.size());
                        this.a.E.start(this.a.D);
                        return;
                    }
                    this.a.H = false;
                    this.a.E.setIndexs(this.a.J);
                    this.a.E.setNum(this.a.J.size());
                    this.a.E.start(this.a.D);
                } else if (this.a.F != null) {
                    List list = this.a.v.c;
                    list.remove(this.a.F);
                    this.a.sortPic(list, this.a.w, true);
                    this.a.F = null;
                    this.a.L.sendEmptyMessage(1);
                } else {
                    this.a.c();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                int i = 0;
                this.a.Q = true;
                DJILogHelper.getInstance().LOGD("FileDelete", "File Delete Fail:" + aVar.toString(), false, true);
                if (this.a.H) {
                    if (this.a.E.getFailNum() != null) {
                        this.a.K.addAll(this.a.E.getFailNum());
                    } else {
                        this.a.I = true;
                    }
                    if (this.a.J.size() > 20) {
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < 20; i2++) {
                            arrayList.add(this.a.J.get(i2));
                        }
                        while (i < 20) {
                            this.a.J.remove(arrayList.get(i));
                            i++;
                        }
                        this.a.E.setIndexs(arrayList);
                        this.a.E.setNum(arrayList.size());
                        this.a.E.start(this.a.D);
                        return;
                    }
                    this.a.H = false;
                    this.a.E.setIndexs(this.a.J);
                    this.a.E.setNum(this.a.J.size());
                    this.a.E.start(this.a.D);
                } else if (this.a.F != null) {
                    this.a.F = null;
                    this.a.P = false;
                    this.a.L.sendEmptyMessage(1);
                } else {
                    if (this.a.E.getFailNum() == null || this.a.I) {
                        this.a.I = true;
                    } else {
                        this.a.K.addAll(this.a.E.getFailNum());
                        this.a.c();
                    }
                    if (this.a.I) {
                        this.a.getAlbumDirectoryInfo();
                        this.a.I = false;
                        this.a.L.sendEmptyMessage(2);
                    }
                }
            }
        };
        this.K = new ArrayList();
        i();
    }

    private void i() {
        boolean h = dji.pilot.fpv.d.b.h(null);
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        if (!h || instance.getMode() == MODE.NEW_PLAYBACK) {
            getAlbumDirectoryInfo();
            return;
        }
        dji.pilot.c.d.a = instance.getMode();
        Log.d("test playback", "tmp mode:" + dji.pilot.c.d.a);
        DataCameraSetMode instance2 = DataCameraSetMode.getInstance();
        instance2.a(MODE.NEW_PLAYBACK);
        instance2.start(new d(this) {
            final /* synthetic */ DJIPlayBackRemoteView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
                    this.a.getAlbumDirectoryInfo();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.d("test playback", "switch failed");
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass7 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.h.setEmptyView(this.a.a.i);
                        this.a.a.j.setVisibility(8);
                        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
                        if (sDCardState == SDCardState.None || sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal) {
                            this.a.a.k.setText(R.string.usercenter_album_load_clouds_no_sdcard);
                        } else {
                            this.a.a.k.setText(R.string.usercenter_album_load_clouds_fail);
                        }
                    }
                });
            }
        });
    }

    private void j() {
        List list = this.v.c;
        checkDownload();
        this.w.clear();
        sortPic(list, this.w, true);
        notifyDataChanged();
    }

    private void getAlbumDirectoryInfo() {
        if (this.u != null && this.s != null) {
            cancelCurrentTask();
            this.u.a(this.s);
        }
    }

    public void cancelCurrentTask() {
        if (this.u != null) {
            this.u.c();
        }
    }

    private void k() {
        this.n = new OnClickListener(this) {
            final /* synthetic */ DJIPlayBackRemoteView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    this.a.c((DJIAlbumFileInfo) view.getTag());
                } catch (Exception e) {
                }
            }
        };
        this.m = new OnGroupClickListener(this) {
            final /* synthetic */ DJIPlayBackRemoteView a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                if (this.a.p.k() == 1) {
                    for (int i2 = 0; i2 < ((a) this.a.w.get(i)).e().size(); i2++) {
                        this.a.selectPic((DJIAlbumFileInfo) ((a) this.a.w.get(i)).e().get(i2));
                    }
                    if (DJIPlayBackRemoteView.q.size() > 0) {
                        this.a.p.b.setEnabled(true);
                        this.a.p.c.setEnabled(true);
                        if (DJIPlayBackRemoteView.q.size() == this.a.v.c.size()) {
                            this.a.p.i = true;
                            this.a.p.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.fpv_playback_selectall));
                        } else {
                            this.a.p.i = false;
                            this.a.p.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.fpv_playback_unselectall));
                        }
                    } else {
                        this.a.p.b.setEnabled(false);
                        this.a.p.c.setEnabled(false);
                        this.a.p.i = false;
                        this.a.p.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.fpv_playback_unselectall));
                    }
                }
                return true;
            }
        };
        h();
    }

    protected void onFinishInflate() {
        Log.d("kevin 10.23", "onFinishInflate");
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.h = (ExpandableListView) findViewById(R.id.bgy);
            this.i = findViewById(R.id.bgz);
            this.k = (DJITextView) findViewById(R.id.bh2);
            this.j = (ProgressBar) findViewById(R.id.bh0);
            this.j.setVisibility(0);
            this.l = (ImageView) findViewById(R.id.bh1);
            this.l.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIPlayBackRemoteView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.getAlbumDirectoryInfo();
                    this.a.h.setEmptyView(this.a.i);
                    this.a.j.setVisibility(0);
                    this.a.l.setVisibility(8);
                    this.a.k.setText(R.string.usercenter_cloudalbum_loading);
                }
            });
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.o.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int dimensionPixelSize = (displayMetrics.widthPixels - ((this.f + 1) * getResources().getDimensionPixelSize(R.dimen.n3))) / this.f;
            int i = (int) (((float) dimensionPixelSize) * h.l);
            this.h.setGroupIndicator(null);
            this.h.setOnGroupClickListener(this.m);
            q.clear();
            this.y = h.getInstance();
            this.w = new ArrayList();
            this.x = new b(this.o, this.f, i, dimensionPixelSize, this.w, q, r, this.n, true);
            this.h.setAdapter(this.x);
            this.t = new OnScrollListener(this) {
                final /* synthetic */ DJIPlayBackRemoteView a;

                {
                    this.a = r1;
                }

                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                }

                public void onScrollStateChanged(AbsListView absListView, int i) {
                    switch (i) {
                        case 0:
                            if (this.a.z || this.a.A) {
                                this.a.z = false;
                                this.a.A = false;
                                this.a.c = false;
                                this.a.b = false;
                                this.a.notifyDataChanged();
                                return;
                            }
                            return;
                        case 1:
                            if (!this.a.A) {
                                this.a.A = true;
                                this.a.c = true;
                                return;
                            }
                            return;
                        case 2:
                            if (!this.a.z) {
                                this.a.z = true;
                                this.a.B = false;
                                this.a.b = true;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            };
            this.h.setOnScrollListener(this.t);
            this.L = new a(this);
            b();
        }
    }

    public void notifyDataChanged() {
        this.x.notifyDataSetChanged();
        for (int i = 0; i < this.x.getGroupCount(); i++) {
            this.h.expandGroup(i);
        }
    }

    private void a(DJIAlbumFileInfo dJIAlbumFileInfo) {
        dji.pilot.fpv.d.e.a("PlayBack_AlbumView_Button_SelectVideo");
        DJIPlayBackVideoPreviewActivity.a(this.o, transformVideoPreviewInfo(dJIAlbumFileInfo), 1, dji.pilot.publics.objects.a.a);
        this.F = dJIAlbumFileInfo;
    }

    private void b(DJIAlbumFileInfo dJIAlbumFileInfo) {
        dji.pilot.fpv.d.e.a("PlayBack_AlbumView_Button_SelectImage");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = false;
        int i2 = 0;
        while (i < this.w.size()) {
            int i3 = i2;
            boolean z2 = z;
            for (int i4 = 0; i4 < ((a) this.w.get(i)).e().size(); i4++) {
                DJIAlbumFileInfo dJIAlbumFileInfo2 = (DJIAlbumFileInfo) ((a) this.w.get(i)).e().get(i4);
                PhotoPreviewInfo transformPreviewInfo = transformPreviewInfo(dJIAlbumFileInfo2);
                if (transformPreviewInfo != null) {
                    if (dJIAlbumFileInfo.equals(dJIAlbumFileInfo2)) {
                        i3 = z2;
                    }
                    arrayList.add(transformPreviewInfo);
                    z2++;
                }
            }
            i++;
            z = z2;
            i2 = i3;
        }
        Bundle a = DJIPlayBackPhotoPreviewActivity.a(2, arrayList, i2);
        Intent intent = new Intent(this.o, DJIPlayBackPhotoPreviewActivity.class);
        intent.setFlags(131072);
        intent.putExtras(a);
        if (this.o instanceof Activity) {
            Activity activity = (Activity) this.o;
            if (activity.getIntent().getBooleanExtra("isSensor", false)) {
                intent.putExtra("isSensor", true);
            }
            activity.startActivityForResult(intent, 1);
            com.dji.frame.c.b.a(this.o, dji.pilot.publics.objects.a.a);
        }
    }

    public PhotoPreviewInfo transformPreviewInfo(DJIAlbumFileInfo dJIAlbumFileInfo) {
        PhotoPreviewInfo photoPreviewInfo = null;
        if (dJIAlbumFileInfo.j == f.b || dJIAlbumFileInfo.j == f.a || dJIAlbumFileInfo.j == f.e || dJIAlbumFileInfo.j == f.c || dJIAlbumFileInfo.j == f.d || dJIAlbumFileInfo.j == f.f) {
            photoPreviewInfo = new PhotoPreviewInfo();
            photoPreviewInfo.e = dJIAlbumFileInfo.d();
            photoPreviewInfo.h = l.a(new Date(dJIAlbumFileInfo.b), C);
            photoPreviewInfo.s = dJIAlbumFileInfo.b;
            photoPreviewInfo.t = dJIAlbumFileInfo.c;
            photoPreviewInfo.v = dJIAlbumFileInfo.j.a();
            photoPreviewInfo.u = dJIAlbumFileInfo.d;
            photoPreviewInfo.q = dJIAlbumFileInfo.a;
            photoPreviewInfo.r = dJIAlbumFileInfo.i.a();
            photoPreviewInfo.w = dJIAlbumFileInfo.k;
            photoPreviewInfo.y = dJIAlbumFileInfo.l;
            photoPreviewInfo.x = dJIAlbumFileInfo.g;
            if (dJIAlbumFileInfo.j == f.c || dJIAlbumFileInfo.j == f.d) {
                photoPreviewInfo.p = dJIAlbumFileInfo.f;
            }
        }
        return photoPreviewInfo;
    }

    public VideoPreviewInfo transformVideoPreviewInfo(DJIAlbumFileInfo dJIAlbumFileInfo) {
        if (dJIAlbumFileInfo.j != f.c && dJIAlbumFileInfo.j != f.d) {
            return null;
        }
        VideoPreviewInfo videoPreviewInfo = new VideoPreviewInfo();
        videoPreviewInfo.e = dJIAlbumFileInfo.b();
        videoPreviewInfo.h = l.a(new Date(dJIAlbumFileInfo.b), C);
        videoPreviewInfo.t = dJIAlbumFileInfo.b;
        videoPreviewInfo.u = dJIAlbumFileInfo.c;
        videoPreviewInfo.w = dJIAlbumFileInfo.j.a();
        videoPreviewInfo.v = dJIAlbumFileInfo.d;
        videoPreviewInfo.p = dJIAlbumFileInfo.a;
        videoPreviewInfo.q = dJIAlbumFileInfo.f;
        videoPreviewInfo.s = dJIAlbumFileInfo.i.a();
        videoPreviewInfo.x = dJIAlbumFileInfo.k;
        videoPreviewInfo.y = dJIAlbumFileInfo.l;
        return videoPreviewInfo;
    }

    private void c(DJIAlbumFileInfo dJIAlbumFileInfo) {
        int k = this.p.k();
        if (k == -1) {
            b(dJIAlbumFileInfo);
        } else if (k == 1) {
            if (dJIAlbumFileInfo.j == f.a || dJIAlbumFileInfo.j == f.b || dJIAlbumFileInfo.j == f.f || dJIAlbumFileInfo.j == f.c || dJIAlbumFileInfo.j == f.d || dJIAlbumFileInfo.j == f.e) {
                k = dJIAlbumFileInfo.hashCode();
                if (q.indexOfKey(k) >= 0) {
                    q.delete(k);
                } else {
                    q.put(k, dJIAlbumFileInfo);
                }
                notifyDataChanged();
            }
            if (q.size() > 0) {
                this.p.b.setEnabled(true);
                this.p.c.setEnabled(true);
                if (q.size() == this.v.c.size()) {
                    this.p.i = true;
                    this.p.d.setImageDrawable(getResources().getDrawable(R.drawable.fpv_playback_selectall));
                    return;
                }
                this.p.i = false;
                this.p.d.setImageDrawable(getResources().getDrawable(R.drawable.fpv_playback_unselectall));
                return;
            }
            this.p.b.setEnabled(false);
            this.p.c.setEnabled(false);
            this.p.i = false;
            this.p.d.setImageDrawable(getResources().getDrawable(R.drawable.fpv_playback_unselectall));
        }
    }

    public void onEventMainThread(dji.midware.sockets.P3.a.a aVar) {
        if (dji.pilot.fpv.d.b.h(null) && !this.e && aVar == dji.midware.sockets.P3.a.a.a) {
            this.e = true;
            getAlbumDirectoryInfo();
        } else if (aVar == dji.midware.sockets.P3.a.a.b) {
            this.e = false;
        }
    }
}
