package dji.pilot.playback.litchi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.ortiz.touch.TouchImageView;
import com.tencent.android.tpush.common.MessageKey;
import dji.log.DJILogHelper;
import dji.logic.album.a.d.a;
import dji.logic.album.a.e;
import dji.logic.album.a.f;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.widget.DJIProgressBar;
import dji.pilot2.b;
import dji.pilot2.library.DJILibraryPhotoView;
import dji.pilot2.library.MixAlbumSyncManager;
import dji.pilot2.library.MixAlbumSyncManager.d;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class h implements a<DJIAlbumFile> {
    private static final int M = 20;
    private static final int N = 1;
    private static final int O = 2;
    private static final int P = 3;
    private static final int Q = 4;
    private static final int R = 7;
    private static final int S = 8;
    private static final int T = 9;
    private static final int U = 10;
    private static final int V = 1001;
    private static final int W = 1002;
    public static String c = "";
    public static String d = "";
    public static final String e = Environment.getExternalStorageDirectory().getPath();
    private static volatile h f;
    private DJISycAlbumModel A;
    private int B = -1;
    private long C = 0;
    private boolean D = false;
    private boolean E = false;
    private boolean F = false;
    private Object G;
    private DJIPhotoPreveiwActivity H = null;
    private int I = -1;
    private int J = 1;
    private int K = 0;
    private d L;
    public boolean a = false;
    public boolean b = false;
    private ExecutorService g;
    private String[] h = new String[]{MessageKey.MSG_ACCEPT_TIME_START};
    private Context i;
    private e j;
    private Handler k;
    private ImageView l;
    private DJITextView m;
    private DJITextView n;
    private TouchImageView o;
    private Button p;
    private DJIStateImageView q;
    private ProgressBar r;
    private Runnable s;
    private DJIProgressBar t = null;
    private ExpandableListView u;
    private b v;
    private DJIAlbumFileInfo w;
    private Thread x;
    private List<Runnable> y;
    private SparseArray<DJIAlbumFileInfo> z;

    public static h getInstance() {
        if (f == null) {
            synchronized (h.class) {
                if (f == null) {
                    f = new h();
                }
            }
        }
        return f;
    }

    protected h() {
        c = "/DJI/" + DJIApplication.f + "/CACHE_IMAGE";
        d = "/DJI/" + DJIApplication.f + "/DJI Album";
        b();
    }

    public void b() {
        this.G = new Object();
        if (!j()) {
            this.g = Executors.newSingleThreadExecutor();
            if (this.j == null) {
                this.j = f.a(b.a.a(), ProductType.litchiS, d + dji.pilot.usercenter.protocol.d.t);
            }
        }
    }

    public void c() {
        if (j()) {
            this.b = true;
            this.j.c();
            this.y = this.g.shutdownNow();
            l();
            try {
                this.g.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.a = false;
            this.g = null;
            b();
            this.b = false;
        }
    }

    private boolean j() {
        if (this.g == null || this.j == null) {
            return false;
        }
        return true;
    }

    private void a(Thread thread) {
        this.g.execute(thread);
    }

    public void d() {
        this.j.c();
        l();
    }

    public void e() {
        if (this.y != null) {
            int size = this.y.size();
            int i;
            if (size > 20) {
                for (i = size - 20; i < size; i++) {
                    this.g.execute((Runnable) this.y.get(i));
                }
                return;
            }
            for (i = 0; i < size; i++) {
                this.g.execute((Runnable) this.y.get(i));
            }
        }
    }

    public void f() {
        this.y = this.g.shutdownNow();
    }

    public e g() {
        return f.j;
    }

    public void a(final DJIImageView dJIImageView, final DJIAlbumFileInfo dJIAlbumFileInfo, final boolean z) {
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h d;

            public void run() {
                this.d.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.d.B = 1;
                h.f.l = dJIImageView;
                h.f.w = dJIAlbumFileInfo;
                h.f.E = z;
                this.d.j.a(dJIAlbumFileInfo, this.d);
                this.d.k();
            }
        });
        a(this.x);
    }

    public void a(final TouchImageView touchImageView, final ProgressBar progressBar, final DJIAlbumFileInfo dJIAlbumFileInfo) {
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h d;

            public void run() {
                this.d.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.d.B = 10;
                h.f.o = touchImageView;
                h.f.w = dJIAlbumFileInfo;
                h.f.r = progressBar;
                this.d.j.a(dJIAlbumFileInfo, this.d);
                this.d.k();
            }
        });
        a(this.x);
    }

    public void a(TouchImageView touchImageView, ProgressBar progressBar, DJIAlbumFileInfo dJIAlbumFileInfo, Context context, Runnable runnable) {
        final DJIAlbumFileInfo dJIAlbumFileInfo2 = dJIAlbumFileInfo;
        final Context context2 = context;
        final TouchImageView touchImageView2 = touchImageView;
        final ProgressBar progressBar2 = progressBar;
        final Runnable runnable2 = runnable;
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h f;

            public void run() {
                this.f.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.f.B = 2;
                h.f.w = dJIAlbumFileInfo2;
                h.f.i = context2;
                h.f.o = touchImageView2;
                h.f.r = progressBar2;
                h.f.s = runnable2;
                this.f.j.b(dJIAlbumFileInfo2, this.f);
                this.f.k();
            }
        });
        a(this.x);
    }

    public void a(TouchImageView touchImageView, ProgressBar progressBar, DJIAlbumFileInfo dJIAlbumFileInfo, Context context) {
        a(touchImageView, progressBar, dJIAlbumFileInfo, context, null);
    }

    public void a(final DJISycAlbumModel dJISycAlbumModel) {
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h b;

            public void run() {
                this.b.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.b.B = 8;
                h.f.w = dJISycAlbumModel.mRemoteInfo;
                dJISycAlbumModel.downloadState = dji.pilot2.library.d.n;
                h.f.A = dJISycAlbumModel;
                this.b.j.b(dJISycAlbumModel.mRemoteInfo, this.b);
                this.b.k();
            }
        });
        a(this.x);
    }

    public void b(final DJISycAlbumModel dJISycAlbumModel) {
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h b;

            public void run() {
                this.b.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.b.B = 9;
                h.f.w = dJISycAlbumModel.mRemoteInfo;
                dJISycAlbumModel.downloadState = dji.pilot2.library.d.m;
                h.f.A = dJISycAlbumModel;
                this.b.j.a(dJISycAlbumModel.mRemoteInfo, this.b);
                this.b.k();
            }
        });
        a(this.x);
    }

    public void a(final DJIAlbumFileInfo dJIAlbumFileInfo) {
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h b;

            public void run() {
                this.b.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.b.B = 3;
                h.f.w = dJIAlbumFileInfo;
                this.b.j.c(dJIAlbumFileInfo, this.b);
                this.b.k();
                DJILogHelper.getInstance().LOGD("Thread", "finish", false, true);
            }
        });
        a(this.x);
    }

    public void a(Context context, DJIProgressBar dJIProgressBar, DJITextView dJITextView, DJITextView dJITextView2, DJIAlbumFileInfo dJIAlbumFileInfo, Handler handler) {
        f.I = -1;
        f.i = context;
        f.t = dJIProgressBar;
        f.m = dJITextView;
        f.n = dJITextView2;
        f.a = true;
        f.k = handler;
        a(dJIAlbumFileInfo);
    }

    public void b(final DJIAlbumFileInfo dJIAlbumFileInfo) {
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h b;

            public void run() {
                this.b.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.b.B = 7;
                h.f.w = dJIAlbumFileInfo;
                this.b.j.c(dJIAlbumFileInfo, this.b);
                this.b.k();
            }
        });
        a(this.x);
    }

    public void a(Context context, Button button, DJIStateImageView dJIStateImageView, DJIPhotoPreveiwActivity dJIPhotoPreveiwActivity, ImageView imageView, Handler handler, DJIAlbumFileInfo dJIAlbumFileInfo) {
        f.I = -1;
        f.i = context;
        f.p = button;
        f.q = dJIStateImageView;
        f.k = handler;
        f.H = dJIPhotoPreveiwActivity;
        f.l = imageView;
        b(dJIAlbumFileInfo);
    }

    public void a(final ImageView imageView, final DJIAlbumFileInfo dJIAlbumFileInfo) {
        this.x = new Thread(new Runnable(this) {
            final /* synthetic */ h c;

            public void run() {
                this.c.h[0] = MessageKey.MSG_ACCEPT_TIME_START;
                this.c.B = 4;
                h.f.l = imageView;
                h.f.w = dJIAlbumFileInfo;
                this.c.j.a(dJIAlbumFileInfo, this.c);
                this.c.k();
            }
        });
        a(this.x);
    }

    public void a(Context context, DJIProgressBar dJIProgressBar, ImageView imageView, DJITextView dJITextView, DJITextView dJITextView2, List<DJIAlbumFileInfo> list, ExpandableListView expandableListView, b bVar, SparseArray<DJIAlbumFileInfo> sparseArray) {
        f.I = list.size();
        f.i = context;
        f.a = true;
        f.J = 1;
        f.K = 0;
        f.t = dJIProgressBar;
        f.m = dJITextView;
        f.n = dJITextView2;
        f.u = expandableListView;
        f.v = bVar;
        f.z = sparseArray;
        f.u.setEnabled(false);
        for (DJIAlbumFileInfo dJIAlbumFileInfo : list) {
            a(imageView, dJIAlbumFileInfo);
            a(dJIAlbumFileInfo);
        }
    }

    private void k() {
        synchronized (this.h) {
            while (this.h[0].equals(MessageKey.MSG_ACCEPT_TIME_START)) {
                try {
                    this.h.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void l() {
        synchronized (this.h) {
            this.h[0] = "over";
            this.h.notify();
        }
    }

    public void a() {
        switch (this.B) {
            case 2:
                this.o.setTag(this.w.d());
                return;
            case 3:
                this.t.setProgress(0);
                this.D = false;
                return;
            case 7:
                if (this.p != null) {
                    this.p.setText("0%");
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(long j, long j2, long j3) {
        if (this.B == 3 && this.n != null) {
            this.n.setText(this.i.getString(R.string.playback_download_rate, new Object[]{Long.valueOf(j3 / 1000)}));
        }
    }

    public void a(long j, long j2) {
        if (j2 <= j) {
            if (this.B == 3) {
                if (j2 > this.C + 50000) {
                    this.C = j2;
                    this.t.setMax(10000);
                    this.t.setProgress((int) ((((float) j2) * 10000.0f) / ((float) j)));
                }
            } else if (this.B == 7 && this.p != null) {
                if ((j2 * 100) / j >= 100) {
                    this.p.setVisibility(8);
                    this.q.setVisibility(8);
                    this.F = true;
                    return;
                }
                this.p.setText(this.i.getString(R.string.v2_photo_downloading) + ((j2 * 100) / j) + "%");
            }
        }
    }

    public void a(DJIAlbumFile dJIAlbumFile) {
        String str;
        String str2;
        File file;
        switch (this.B) {
            case 1:
                if (this.l != null && this.E && this.l.getTag().equals(this.w.b())) {
                    this.l.setImageBitmap(dJIAlbumFile.e);
                    break;
                }
            case 2:
                if (this.o != null) {
                    if (this.s != null) {
                        this.s.run();
                    }
                    this.o.setImageBitmap(dJIAlbumFile.e);
                    this.r.setVisibility(8);
                    long currentTimeMillis = System.currentTimeMillis() - this.w.b;
                    MixAlbumSyncManager instance = MixAlbumSyncManager.getInstance(this.i);
                    if (currentTimeMillis > 43200000 || currentTimeMillis < 0) {
                        instance.addNotShow(this.i, this.w.a());
                        break;
                    }
                }
                break;
            case 3:
                str = e + c + dji.pilot.usercenter.protocol.d.t + this.w.b();
                str2 = e + d + dji.pilot.usercenter.protocol.d.t + this.w.b();
                file = new File(e + d);
                if (!file.exists()) {
                    file.mkdir();
                }
                if (this.w.j == dji.logic.album.a.b.f.a || this.w.j == dji.logic.album.a.b.f.f) {
                    c.a(str, str2);
                } else if (this.w.j == dji.logic.album.a.b.f.d || this.w.j == dji.logic.album.a.b.f.c) {
                    MixAlbumSyncManager.getInstance(this.i).insertDb(this.i, this.w.b());
                    c.a(str, str2);
                }
                if (this.z != null) {
                    this.z.put(this.w.hashCode(), this.w);
                }
                File file2 = new File(str2);
                a(file2);
                dji.pilot.usercenter.b.a.getInstance().b(file2);
                this.C = 0;
                if (this.I > 0 && this.I > this.J) {
                    this.J++;
                    this.m.setText(this.i.getString(R.string.playback_downloading, new Object[]{Integer.valueOf(this.J), Integer.valueOf(this.I)}));
                    this.t.setProgress(this.t.getMax());
                } else if (this.I == this.J) {
                    this.m.setText(this.i.getString(R.string.playback_download_mult_complete, new Object[]{Integer.valueOf(this.I - this.K), Integer.valueOf(this.K)}));
                    this.t.setProgress(this.t.getMax());
                    h();
                } else if (this.I == -1) {
                    this.m.setText(R.string.playback_download_complete);
                    this.t.setProgress(this.t.getMax());
                    h();
                    if (this.k != null) {
                        this.k.sendEmptyMessage(1001);
                    }
                }
                MixAlbumSyncManager.getInstance(this.i).removeNotShow(this.i, this.w.a());
                c.a().e(dji.pilot2.library.a.MEDIASDDOWNLOADEND);
                break;
            case 4:
                this.l.setImageBitmap(dJIAlbumFile.e);
                break;
            case 7:
                str = e + c + dji.pilot.usercenter.protocol.d.t + this.w.b();
                str2 = e + d + dji.pilot.usercenter.protocol.d.t + this.w.b();
                file = new File(e + d);
                if (!file.exists()) {
                    file.mkdir();
                }
                if (this.w.j == dji.logic.album.a.b.f.a || this.w.j == dji.logic.album.a.b.f.f) {
                    c.a(str, str2);
                }
                file = new File(str2);
                a(file);
                dji.pilot.usercenter.b.a.getInstance().b(file);
                file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
                this.F = true;
                if (this.p != null) {
                    this.p.setVisibility(8);
                }
                if (this.q != null) {
                    this.q.setVisibility(8);
                }
                if (this.k != null) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    message.what = 1001;
                    message.setData(bundle);
                    this.k.sendMessage(message);
                }
                if (DJILibraryPhotoView.C != null) {
                    DJILibraryPhotoView.C.isOrg = true;
                    DJILibraryPhotoView.C.orgPath = str2;
                }
                c.a().e(dji.pilot2.library.a.MEDIASDDOWNLOADEND);
                break;
            case 10:
                if (!(this.o == null || this.o.getTag() == null || !this.o.getTag().equals(this.w.b()))) {
                    this.o.setImageBitmap(dJIAlbumFile.e);
                    break;
                }
        }
        l();
    }

    public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
        switch (this.B) {
            case 2:
                this.r.setVisibility(8);
                break;
            case 3:
                this.C = 0;
                if (!this.D) {
                    if (this.I > 0 && this.I > this.J) {
                        this.K++;
                        this.J++;
                        this.m.setText(this.i.getString(R.string.playback_downloading, new Object[]{Integer.valueOf(this.J), Integer.valueOf(this.I)}));
                    } else if (this.I == this.J) {
                        this.K++;
                        this.m.setText(this.i.getString(R.string.playback_download_mult_complete, new Object[]{Integer.valueOf(this.I - this.K), Integer.valueOf(this.K)}));
                        h();
                    } else if (this.I == -1) {
                        this.m.setText(R.string.playback_download_fail);
                        h();
                        if (this.k != null) {
                            this.k.sendEmptyMessage(1002);
                        }
                    }
                    this.D = true;
                    break;
                }
                break;
            case 7:
                if (this.p != null) {
                    this.p.setText(this.i.getString(R.string.v2_photo_preview_download_fail));
                    break;
                }
                break;
            case 8:
                this.A.downloadState = dji.pilot2.library.d.p;
                break;
            case 9:
                this.A.downloadState = dji.pilot2.library.d.q;
                break;
            case 10:
                this.r.setVisibility(8);
                break;
        }
        l();
    }

    public void h() {
        int i = 0;
        this.I = -1;
        this.a = false;
        if (this.n != null) {
            this.n.setText("");
        }
        if (this.v != null && this.u != null) {
            this.u.setEnabled(true);
            this.v.notifyDataSetChanged();
            while (i < this.v.getGroupCount()) {
                this.u.expandGroup(i);
                i++;
            }
        }
    }

    private void a(File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        try {
            intent.setData(Uri.fromFile(file));
            this.i.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
