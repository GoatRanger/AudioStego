package dji.pilot.playback.litchi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import com.dji.frame.c.l;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions$Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.f;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSingleChoice;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.a;
import dji.pilot.publics.widget.b;
import dji.pilot.usercenter.mode.PhotoPreviewInfo;
import dji.pilot.usercenter.mode.VideoPreviewInfo;
import dji.pilot.usercenter.protocol.d;
import dji.pilot.usercenter.widget.DJIProgressBar;
import dji.pilot2.media.view.DJIPhotoViewPager;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.djiviewpager.DJIViewPager;
import dji.thirdparty.afinal.a.b.c;
import java.util.ArrayList;
import java.util.Date;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public class DJIPlayBackPhotoPreviewActivity extends DJIBaseActivity {
    private static final int V = 1001;
    private static final int W = 1002;
    private static final int X = 1003;
    public static final String a = "key_source";
    public static final String b = "key_list";
    public static final String c = "key_pos";
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    private static final String h = "yyyy-MM-dd HH:mm:ss";
    private a A = null;
    private int B = 0;
    private OnClickListener C = null;
    private ImageLoader D = null;
    private DisplayImageOptions E;
    private int F = 0;
    private ArrayList<PhotoPreviewInfo> G = null;
    private OnPageChangeListener H = null;
    private OnViewTapListener I = null;
    private Animation J = null;
    private Animation K = null;
    private Animation L = null;
    private Animation M = null;
    private b N = null;
    private b O = null;
    private b P = null;
    private a Q = null;
    private h R = null;
    private int S = 1;
    private boolean T = false;
    private dji.logic.album.a.b U = null;
    private Handler Y = null;
    private PhotoPreviewInfo Z = null;
    private int aa = 0;
    private final int g = 4096;
    @c(a = 2131364834)
    private DJIRelativeLayout i;
    @c(a = 2131364835)
    private DJIImageView j;
    @c(a = 2131364836)
    private DJITextView k;
    @c(a = 2131364837)
    private DJIImageView l;
    @c(a = 2131364838)
    private DJIImageView m;
    @c(a = 2131364842)
    private DJIImageView n;
    @c(a = 2131364843)
    private DJIImageView o;
    @c(a = 2131364833)
    private DJIPhotoViewPager p;
    @c(a = 2131364840)
    private DJIRelativeLayout q;
    @c(a = 2131364822)
    private DJIRelativeLayout r;
    @c(a = 2131364825)
    private DJIProgressBar s;
    @c(a = 2131364844)
    private DJITextView t;
    @c(a = 2131364823)
    private DJITextView u;
    @c(a = 2131364824)
    private DJITextView v;
    @c(a = 2131364839)
    private DJIImageView w;
    @c(a = 2131364841)
    private DJIImageView x;
    private final ViewGroup[] y = new ViewGroup[3];
    private dji.pilot.usercenter.activity.a z = null;

    public static Bundle a(int i, ArrayList<PhotoPreviewInfo> arrayList, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("key_source", i);
        bundle.putParcelableArrayList("key_list", arrayList);
        bundle.putInt(c, i2);
        return bundle;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.playback_photo_preview_view);
        a(getIntent());
        this.U = dji.logic.album.a.b.getInstance(this);
        x();
        c();
        d();
        this.Y = new Handler(new 1(this));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    protected void onDestroy() {
        this.R.c();
        y();
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
        e.b((Context) this);
    }

    protected void onStop() {
        super.onStop();
        e.c((Context) this);
    }

    public void onBackPressed() {
        finishThis();
    }

    public void finish() {
        super.finish();
        this.R.b = true;
        com.dji.frame.c.b.a((Context) this, dji.pilot.publics.objects.a.b);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.R.a) {
            return super.onKeyDown(i, keyEvent);
        }
        t();
        return false;
    }

    private void b(PhotoPreviewInfo photoPreviewInfo) {
        e.a("PlayBack_AlbumView_Button_SelectVideo");
        if (dji.pilot.publics.e.a.c(i.getInstance().c())) {
            DJILogHelper.getInstance().LOGD("", "wm220 视频回放 index:" + photoPreviewInfo.u, false, true);
            this.Z = photoPreviewInfo;
            DataCameraSingleChoice.getInstance().setKey(photoPreviewInfo.u).start(new 6(this));
            return;
        }
        DJIPlayBackVideoPreviewActivity.a(this, a(photoPreviewInfo), 1, dji.pilot.publics.objects.a.a);
    }

    public VideoPreviewInfo a(PhotoPreviewInfo photoPreviewInfo) {
        if (photoPreviewInfo.v != f.c.a() && photoPreviewInfo.v != f.d.a()) {
            return null;
        }
        VideoPreviewInfo videoPreviewInfo = new VideoPreviewInfo();
        videoPreviewInfo.e = photoPreviewInfo.e;
        videoPreviewInfo.h = l.a(new Date(photoPreviewInfo.s), h);
        videoPreviewInfo.t = photoPreviewInfo.s;
        videoPreviewInfo.u = photoPreviewInfo.t;
        videoPreviewInfo.w = photoPreviewInfo.v;
        videoPreviewInfo.v = photoPreviewInfo.u;
        videoPreviewInfo.p = photoPreviewInfo.q;
        videoPreviewInfo.q = photoPreviewInfo.p;
        videoPreviewInfo.r = photoPreviewInfo.x;
        videoPreviewInfo.s = photoPreviewInfo.r;
        videoPreviewInfo.x = photoPreviewInfo.w;
        videoPreviewInfo.y = photoPreviewInfo.y;
        return videoPreviewInfo;
    }

    private void a() {
        this.C = new 7(this);
        this.H = new 8(this);
        this.I = new 9(this);
    }

    private void b() {
        this.J = AnimationUtils.loadAnimation(this, R.anim.bt);
        this.K = AnimationUtils.loadAnimation(this, R.anim.bu);
        this.L = AnimationUtils.loadAnimation(this, R.anim.be);
        this.M = AnimationUtils.loadAnimation(this, R.anim.bf);
    }

    private void c() {
        this.D = ImageLoader.getInstance();
        this.R = h.getInstance();
        this.E = new DisplayImageOptions$Builder().imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(200)).considerExifParams(true).bitmapConfig(Config.RGB_565).cacheInMemory(true).cacheOnDisc(false).build();
        a();
        b();
    }

    @SuppressLint({"InflateParams"})
    private void a(LayoutInflater layoutInflater) {
        for (int i = 0; i < this.y.length; i++) {
            b bVar = new b(null);
            ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.photo_preview_item, null);
            bVar.a = (ProgressBar) viewGroup.findViewById(R.id.bg9);
            bVar.b = (ProgressBar) viewGroup.findViewById(R.id.bg_);
            viewGroup.setTag(bVar);
            viewGroup.setId(i + 4096);
            viewGroup.setOnClickListener(this.C);
            this.y[i] = viewGroup;
        }
    }

    private void d() {
        LayoutInflater from = LayoutInflater.from(this);
        a(from);
        if (this.B == 1) {
            this.o.hide();
            this.m.show();
            this.l.show();
        } else {
            this.l.hide();
            this.m.hide();
            this.o.show();
        }
        this.j.setOnClickListener(this.C);
        this.l.setOnClickListener(this.C);
        this.m.setOnClickListener(this.C);
        this.n.setOnClickListener(this.C);
        this.o.setOnClickListener(this.C);
        this.t.setOnClickListener(this.C);
        this.x.setOnClickListener(this.C);
        this.A = new a(this, from);
        this.p.setOffscreenPageLimit(1);
        this.p.setAdapter(this.A);
        this.p.setTransitionEffect(DJIViewPager.b.a);
        this.p.setOnPageChangeListener(this.H);
        this.p.setCurrentItem(this.F);
        this.p.setCurrentItem(this.F);
        a(this.F);
    }

    private void a(Intent intent) {
        if (intent != null) {
            this.B = intent.getIntExtra("key_source", 0);
            this.G = intent.getParcelableArrayListExtra("key_list");
            this.F = intent.getIntExtra(c, 0);
            if (this.G == null) {
                this.F = 0;
            } else if (this.F >= this.G.size()) {
                this.F = this.G.size() - 1;
            }
        }
        if (!intent.getBooleanExtra("isSensor", false)) {
        }
    }

    private void e() {
        if (this.i.isShown()) {
            this.i.go();
            this.i.startAnimation(this.K);
            this.q.go();
            this.q.startAnimation(this.M);
            if (this.T) {
                this.w.go();
                this.w.startAnimation(this.K);
                return;
            }
            return;
        }
        this.i.show();
        this.i.startAnimation(this.J);
        this.q.show();
        this.q.startAnimation(this.L);
        if (this.T) {
            this.w.show();
            this.w.startAnimation(this.J);
        }
    }

    private void f() {
        if (this.r.isShown()) {
            this.r.go();
            this.t.go();
            this.o.show();
            this.n.show();
        } else {
            this.r.show();
            this.t.show();
            this.o.go();
            this.n.go();
        }
        a(this.F);
    }

    private void a(int i) {
        if (this.G != null && this.G.size() > i) {
            PhotoPreviewInfo photoPreviewInfo = (PhotoPreviewInfo) this.G.get(i);
            this.k.setText((i + 1) + d.t + this.G.size());
            DJIAlbumFileInfo dJIAlbumFileInfo = new DJIAlbumFileInfo();
            dJIAlbumFileInfo.b = photoPreviewInfo.s;
            dJIAlbumFileInfo.c = photoPreviewInfo.t;
            dJIAlbumFileInfo.j = f.find(photoPreviewInfo.v);
            dJIAlbumFileInfo.d = photoPreviewInfo.u;
            dJIAlbumFileInfo.a = photoPreviewInfo.q;
            dJIAlbumFileInfo.k = photoPreviewInfo.w;
            dJIAlbumFileInfo.l = photoPreviewInfo.y;
            if (c.b(dJIAlbumFileInfo.b())) {
                this.w.show();
                this.o.setEnabled(false);
                this.T = true;
            } else {
                this.w.go();
                this.o.setEnabled(true);
                this.T = false;
            }
            if (dJIAlbumFileInfo.j == f.e) {
                this.o.setEnabled(false);
                this.T = false;
            }
            if (dJIAlbumFileInfo.j == f.b || dJIAlbumFileInfo.j == f.e || dJIAlbumFileInfo.j == f.a || dJIAlbumFileInfo.j == f.f) {
                this.x.go();
            } else {
                this.x.show();
            }
        }
        this.A.notifyDataSetChanged();
    }

    private PhotoPreviewInfo g() {
        int currentItem = this.p.getCurrentItem();
        if (this.G == null || this.G.size() <= currentItem) {
            return null;
        }
        return (PhotoPreviewInfo) this.G.get(currentItem);
    }

    private void h() {
        if (this.O == null) {
            this.O = b.a(this, R.string.fpv_playback_del_image, R.string.btn_dlg_no, new 10(this), R.string.btn_dlg_yes, new 11(this));
            this.O.setCancelable(true);
            this.O.setCanceledOnTouchOutside(true);
        }
        if (this.O != null && !this.O.isShowing()) {
            this.O.a(R.string.fpv_playback_del_image);
            this.O.show();
        }
    }

    private void i() {
        if (this.O != null && this.O.isShowing()) {
            this.O.dismiss();
            this.O = null;
        }
    }

    private void j() {
        i();
    }

    private void k() {
        i();
        Intent intent = new Intent();
        intent.putExtra("Pos", this.p.getCurrentItem());
        setResult(-1, intent);
        finish();
    }

    private void l() {
        if (this.z == null) {
            this.z = new dji.pilot.usercenter.activity.a(this);
        }
        if (!this.z.isShowing()) {
            this.z.a(g().a());
            this.z.show();
        }
    }

    private void m() {
        startActivity(Intent.createChooser(dji.pilot.usercenter.f.e.a((Context) this, Scheme.FILE.crop(g().o), null), getString(R.string.app_share)));
    }

    private void n() {
        if (this.x.getVisibility() == 0) {
            this.x.setEnabled(false);
        }
        DJIAlbumFileInfo dJIAlbumFileInfo = new DJIAlbumFileInfo();
        dJIAlbumFileInfo.b = g().s;
        dJIAlbumFileInfo.c = g().t;
        dJIAlbumFileInfo.j = f.find(g().v);
        dJIAlbumFileInfo.d = g().u;
        dJIAlbumFileInfo.a = g().q;
        dJIAlbumFileInfo.k = g().w;
        dJIAlbumFileInfo.l = g().y;
        this.u.setText(R.string.playback_downloading_one);
        this.R.a(this, this.s, this.u, this.v, dJIAlbumFileInfo, this.Y);
    }

    private void o() {
        if (this.N == null) {
            this.N = b.a(this, R.string.fpv_playback_download_files, R.string.btn_dlg_no, new 12(this), R.string.btn_dlg_yes, new 13(this));
            this.N.setCancelable(true);
            this.N.setCanceledOnTouchOutside(true);
        }
        if (this.N != null && !this.N.isShowing()) {
            this.N.a(R.string.fpv_playback_download_files);
            this.N.show();
        }
    }

    private void p() {
        if (this.Q == null) {
            this.Q = a.a(this, R.string.fpv_playback_download_files, R.string.fpv_playback_download_desc, R.string.btn_dlg_yes, new 2(this));
            this.Q.setCancelable(true);
            this.Q.setCanceledOnTouchOutside(true);
        }
        if (this.Q != null && !this.Q.isShowing()) {
            this.Q.show();
        }
    }

    private void q() {
        if (this.N != null && this.N.isShowing()) {
            this.N.dismiss();
            this.N = null;
        }
    }

    private void r() {
        q();
    }

    private void s() {
        q();
        this.p.setPagingEnabled(false);
        this.o.setEnabled(false);
        f();
        n();
    }

    private void t() {
        if (this.P == null) {
            this.P = b.a(this, R.string.fpv_playback_download_cancel, R.string.btn_dlg_no, new 3(this), R.string.btn_dlg_yes, new 4(this));
            this.P.setCancelable(true);
            this.P.setCanceledOnTouchOutside(true);
        }
        if (this.P != null && !this.P.isShowing()) {
            this.P.a(R.string.fpv_playback_download_cancel);
            this.P.show();
        }
    }

    private void u() {
        if (this.P != null && this.P.isShowing()) {
            this.P.dismiss();
            this.P = null;
        }
    }

    private void v() {
        u();
    }

    private void w() {
        u();
        f();
        h.getInstance().c();
        this.p.setPagingEnabled(true);
        this.o.setEnabled(true);
        if (this.x.getVisibility() == 0) {
            this.x.setEnabled(true);
        }
    }

    public void finishThis() {
        if (this.B == 2) {
            setResult(this.S, new Intent());
            finish();
            return;
        }
        finish();
    }

    private void x() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private void y() {
        dji.thirdparty.a.c.a().d((Object) this);
    }

    public void onEventBackgroundThread(p pVar) {
        switch (5.a[pVar.ordinal()]) {
            case 2:
                finishThis();
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.getMode() != MODE.PLAYBACK) {
            finishThis();
        }
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        if (this.isVisible) {
            if (dataRcGetPushParams.isWheelChanged()) {
                if (dataRcGetPushParams.isWheelPositive()) {
                    this.p.setCurrentItem(this.p.getCurrentItem() - 1);
                } else {
                    this.p.setCurrentItem(this.p.getCurrentItem() + 1);
                }
            }
            int wheelClickStatus = dataRcGetPushParams.getWheelClickStatus();
            if (this.aa != wheelClickStatus) {
                this.aa = wheelClickStatus;
                if (this.aa == 1 && this.x.isShown()) {
                    b((PhotoPreviewInfo) this.G.get(this.F));
                }
            }
        }
    }
}
