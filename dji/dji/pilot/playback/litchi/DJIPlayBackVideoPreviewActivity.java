package dji.pilot.playback.litchi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.VideoView;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.h.b.b;
import dji.midware.media.i.d;
import dji.midware.media.i.d.e;
import dji.midware.media.i.d.f;
import dji.midware.media.i.h;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.k;
import dji.pilot.usercenter.mode.VideoPreviewInfo;
import dji.pilot.usercenter.widget.DJIProgressBar;
import dji.pilot.usercenter.widget.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;

public class DJIPlayBackVideoPreviewActivity extends DJIBaseActivity {
    private static Context N = null;
    private static int S = 0;
    public static final String a = "key_source";
    private static final int aA = 1001;
    private static final int aB = 1002;
    private static int aD = 200;
    public static final String b = "key_videoinfo";
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 1;
    private static final long k = 10000;
    private static final long l = 1000;
    private static final int m = 4096;
    private static final int n = 8192;
    private static final int o = 4097;
    @c(a = 2131364877)
    private SeekBar A;
    @c(a = 2131364876)
    private DJITextView B;
    @c(a = 2131364875)
    private DJITextView C;
    @c(a = 2131364861)
    private DJIRelativeLayout D;
    @c(a = 2131364878)
    private DJIRelativeLayout E;
    @c(a = 2131364825)
    private DJIProgressBar F;
    @c(a = 2131364844)
    private DJITextView G;
    @c(a = 2131364823)
    private DJITextView H;
    @c(a = 2131364862)
    private DJITextView I;
    @c(a = 2131364824)
    private DJITextView J;
    @c(a = 2131364822)
    private DJIRelativeLayout K;
    @c(a = 2131364839)
    private DJIImageView L;
    @c(a = 2131364860)
    private DJIImageView M;
    private a O = null;
    private OnItemClickListener P = null;
    private OnSeekBarChangeListener Q = null;
    private OnClickListener R = null;
    private VideoPreviewInfo T = null;
    private OnCompletionListener U = null;
    private OnErrorListener V = null;
    private boolean W = false;
    private boolean X = false;
    private a Y = null;
    private Animation Z = null;
    private b aC = null;
    private DisplayMetrics aE = new DisplayMetrics();
    private Point aF = new Point();
    private boolean aG = false;
    private boolean aH = false;
    private long aI = 0;
    private Animation aa = null;
    private Animation ab = null;
    private Animation ac = null;
    private dji.pilot.publics.widget.b ad = null;
    private dji.pilot.publics.widget.b ae = null;
    private dji.pilot.publics.widget.b af = null;
    private k ag = null;
    private DJIAlbumFileInfo ah = null;
    private h ai = null;
    private d aj = null;
    private e ak = null;
    private d.a al = null;
    private d.b am = null;
    private d.c an = null;
    private d.d ao = null;
    private f ap = null;
    private h aq = null;
    private dji.pilot.publics.c.f ar = null;
    private h.a as = null;
    private int at = 0;
    private int au = 0;
    private TextureView av = null;
    private SurfaceTextureListener aw = null;
    private DJIVideoDecoder ax = null;
    private String ay = "%02d:%02d:%02d";
    private boolean az = false;
    protected Handler g = null;
    int h;
    int i;
    int j = 0;
    private int p = 1;
    @c(a = 2131364863)
    private DJIRelativeLayout q;
    @c(a = 2131364864)
    private DJIImageView r;
    @c(a = 2131364865)
    private DJITextView s;
    @c(a = 2131364866)
    private DJIImageView t;
    @c(a = 2131364841)
    private DJIStateImageView u;
    @c(a = 2131364858)
    private VideoView v;
    @c(a = 2131364869)
    private DJIRelativeLayout w;
    @c(a = 2131364870)
    private DJIRelativeLayout x;
    @c(a = 2131364872)
    private DJIStateImageView y;
    @c(a = 2131364873)
    private DJIStateImageView z;

    public static void a(Context context, VideoPreviewInfo videoPreviewInfo, int i, int i2) {
        N = context;
        if (videoPreviewInfo != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("key_videoinfo", videoPreviewInfo);
            bundle.putInt("key_source", i);
            Intent intent = new Intent(context, DJIPlayBackVideoPreviewActivity.class);
            intent.setFlags(131072);
            intent.putExtras(bundle);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, 1);
                com.dji.frame.c.b.a(context, i2);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.playback_video_preview_view);
        a(getIntent());
        U();
        m();
        p();
        this.g = new Handler(new 1(this));
        if (S == 1) {
            h();
            i();
        } else if (S == 2) {
            j();
            k();
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventBackgroundThread(o.ConnectOK);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.X) {
            this.X = false;
            this.v.start();
            this.y.setImageResource(R.drawable.fpv_playback_video_pause);
            this.u.go();
        }
    }

    protected void onPause() {
        if (S == 0) {
            if (this.v.isPlaying()) {
                this.X = true;
                this.v.pause();
                H();
            }
        } else if (S == 1) {
            if (this.aj != null && this.aj.q() == 10) {
                this.aj.m();
                H();
            }
        } else if (S == 2 && this.aq != null && this.aq.h()) {
            this.aq.e();
        }
        super.onPause();
    }

    protected void onDestroy() {
        V();
        if (this.aj != null) {
            this.aj.a();
            this.aj = null;
        }
        if (this.aq != null) {
            this.aq.d();
            this.aq.k();
            this.aq = null;
        }
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b((Context) this);
    }

    protected void onStop() {
        super.onStop();
        dji.pilot.fpv.d.e.c((Context) this);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        I();
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Configuration configuration = N.getResources().getConfiguration();
        this.j = configuration.orientation;
        e();
        d();
        a(configuration);
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.d(this.TAG, "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        e();
        d();
        a(configuration);
    }

    private void d() {
        LayoutParams layoutParams = (LayoutParams) this.av.getLayoutParams();
        layoutParams.width = this.h;
        layoutParams.height = (int) (((float) this.h) / dji.midware.util.a.b.a);
        this.M.setLayoutParams(layoutParams);
        if (this.ah.g == 1) {
            layoutParams.width = (int) (((float) this.h) / dji.midware.util.a.b.a);
            layoutParams.height = (int) (((float) layoutParams.width) / dji.midware.util.a.b.a);
            this.av.setLayoutParams(layoutParams);
            this.av.setRotation(90.0f);
            return;
        }
        this.av.setLayoutParams(layoutParams);
    }

    private void a(Configuration configuration) {
        this.j = configuration.orientation;
        LayoutParams layoutParams = (LayoutParams) this.A.getLayoutParams();
        if (configuration.orientation == 2) {
            layoutParams.addRule(3, 0);
            layoutParams.addRule(1, R.id.bil);
            layoutParams.addRule(0, R.id.bio);
            layoutParams.addRule(15);
            return;
        }
        layoutParams.addRule(1, 0);
        layoutParams.addRule(0, 0);
        layoutParams.addRule(15, 0);
        layoutParams.addRule(3, R.id.bil);
    }

    @SuppressLint({"NewApi"})
    private void e() {
        if (VERSION.SDK_INT < 17) {
            getWindowManager().getDefaultDisplay().getMetrics(this.aE);
            this.h = this.aE.widthPixels;
            this.i = this.aE.heightPixels;
            return;
        }
        getWindowManager().getDefaultDisplay().getRealSize(this.aF);
        this.h = this.aF.x;
        this.i = this.aF.y;
    }

    @SuppressLint({"NewApi"})
    private float f() {
        int i;
        int i2;
        int i3;
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            if (i >= i2) {
                i3 = i2;
                i2 = i;
                i = i3;
            }
        } else {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            i3 = point.x > point.y ? point.y : point.x;
            i2 = point.x > point.y ? point.x : point.y;
            i = i3;
        }
        return (((float) i2) * 1.0f) / ((float) i);
    }

    public void finish() {
        if (S == 0) {
            this.Y.removeMessages(4096);
            this.Y.removeMessages(4097);
            this.v.stopPlayback();
        } else {
            if (this.aj != null) {
                this.aj.o();
                this.aj.a();
                this.aj = null;
            }
            this.Y.removeMessages(4096);
            this.Y.removeMessages(4097);
        }
        if (this.ax != null) {
            this.ax.setSurface(null);
            if (this.aC != null) {
                this.aC.c();
                this.aC = null;
            }
            this.ax.release();
            this.ax = null;
        }
        DJILogHelper.getInstance().LOGD("", "finish playback", false, true);
        super.finish();
        com.dji.frame.c.b.a((Context) this, dji.pilot.publics.objects.a.b);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.ai.a) {
            return super.onKeyDown(i, keyEvent);
        }
        A();
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Intent r7) {
        /*
        r6 = this;
        r5 = 1;
        r4 = 0;
        if (r7 == 0) goto L_0x00b1;
    L_0x0004:
        r0 = "key_source";
        r0 = r7.getIntExtra(r0, r4);
        S = r0;
        r0 = "key_videoinfo";
        r0 = r7.getParcelableExtra(r0);
        r0 = (dji.pilot.usercenter.mode.VideoPreviewInfo) r0;
        r6.T = r0;
        r0 = S;
        if (r0 == r5) goto L_0x001f;
    L_0x001a:
        r0 = S;
        r1 = 2;
        if (r0 != r1) goto L_0x009b;
    L_0x001f:
        r0 = new dji.logic.album.model.DJIAlbumFileInfo;
        r0.<init>();
        r6.ah = r0;
        r0 = r6.ah;
        r1 = r6.T;
        r2 = r1.t;
        r0.b = r2;
        r0 = r6.ah;
        r1 = r6.T;
        r2 = r1.u;
        r0.c = r2;
        r0 = r6.ah;
        r1 = r6.T;
        r1 = r1.w;
        r1 = dji.logic.album.a.b.f.find(r1);
        r0.j = r1;
        r0 = r6.ah;
        r1 = r6.T;
        r1 = r1.v;
        r0.d = r1;
        r0 = r6.ah;
        r1 = r6.T;
        r2 = r1.p;
        r0.a = r2;
        r0 = r6.ah;
        r1 = r6.T;
        r1 = r1.q;
        r0.f = r1;
        r0 = r6.ah;
        r1 = r6.T;
        r1 = r1.r;
        r0.g = r1;
        r0 = r6.ah;
        r1 = r6.T;
        r1 = r1.s;
        r1 = dji.logic.album.a.b.d.find(r1);
        r0.i = r1;
        r0 = r6.ah;
        r1 = r6.T;
        r1 = r1.x;
        r0.k = r1;
        r0 = r6.ah;
        r1 = r6.T;
        r1 = r1.y;
        r0.l = r1;
        r0 = r6.ah;
        r0 = r0.b();
        r0 = dji.pilot.playback.litchi.c.b(r0);
        if (r0 == 0) goto L_0x00b2;
    L_0x008a:
        r6.az = r5;
        r0 = r6.L;
        r0.show();
    L_0x0091:
        r0 = r6.ah;
        r0 = r0.i;
        r0 = r0.c();
        if (r0 == 0) goto L_0x009b;
    L_0x009b:
        r0 = N;
        r0 = r0 instanceof android.app.Activity;
        if (r0 == 0) goto L_0x00b1;
    L_0x00a1:
        r0 = N;
        r0 = (android.app.Activity) r0;
        r0 = r0.getIntent();
        r1 = "isSensor";
        r0 = r0.getBooleanExtra(r1, r4);
        if (r0 != r5) goto L_0x00b1;
    L_0x00b1:
        return;
    L_0x00b2:
        r6.az = r4;
        r0 = r6.L;
        r0.go();
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.playback.litchi.DJIPlayBackVideoPreviewActivity.a(android.content.Intent):void");
    }

    private void g() {
        this.U = new 12(this);
        this.V = new 18(this);
    }

    private void h() {
        DJILogHelper.getInstance().LOGD("mediaPlayer", "DJIMediaPlayer=" + i.getInstance().c(), false, true);
        this.aj = dji.midware.media.i.e.a(i.getInstance().c());
        this.av = (TextureView) findViewById(R.id.bi_);
        e();
        LayoutParams layoutParams = (LayoutParams) this.av.getLayoutParams();
        layoutParams.width = this.h;
        layoutParams.height = (int) (((float) this.h) / dji.midware.util.a.b.a);
        this.M.setLayoutParams(layoutParams);
        if (this.ah.g == 1) {
            layoutParams.width = (int) (((float) this.h) / dji.midware.util.a.b.a);
            layoutParams.height = (int) (((float) layoutParams.width) / dji.midware.util.a.b.a);
            this.av.setLayoutParams(layoutParams);
            this.av.setRotation(90.0f);
        } else {
            this.av.setLayoutParams(layoutParams);
        }
        Log.d(this.TAG, "init size+++width:" + layoutParams.width + "height:" + layoutParams.height);
        this.av.setVisibility(0);
        this.v.setVisibility(8);
    }

    private void i() {
        this.an = new 19(this);
        this.al = new 20(this);
        this.am = new 21(this);
        this.ak = new 22(this);
        this.ao = new 23(this);
        this.ap = new 24(this);
        this.aw = new 2(this);
        this.ai.c();
        this.aj.a(this.ah);
        this.C.setText(a(0) + dji.pilot.usercenter.protocol.d.t);
        this.B.setText(a(this.aj.h()));
        this.aj.a(this.al);
        this.aj.a(this.an);
        this.aj.a(this.am);
        this.aj.a(this.ak);
        this.aj.a(this.ap);
        this.aj.a(this.ao);
        this.av.setSurfaceTextureListener(this.aw);
        this.g.sendEmptyMessageDelayed(100, 1000);
    }

    private void j() {
        this.av = (TextureView) findViewById(R.id.bi_);
        this.aq = new h();
        LayoutParams layoutParams = (LayoutParams) this.av.getLayoutParams();
        layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
        this.M.setLayoutParams(layoutParams);
        if (this.ah.g == 1) {
            layoutParams.width = (int) (((float) this.h) / dji.midware.util.a.b.a);
            layoutParams.height = (int) (((float) layoutParams.width) / dji.midware.util.a.b.a);
            this.av.setLayoutParams(layoutParams);
            this.av.setRotation(90.0f);
        } else {
            this.av.setLayoutParams(layoutParams);
        }
        this.av.setVisibility(0);
        this.v.setVisibility(8);
    }

    private void k() {
        this.aw = new 3(this);
        this.as = new 4(this);
        this.aq.a(this.as);
        this.av.setSurfaceTextureListener(this.aw);
        this.C.setText("");
        a(0, 0);
    }

    private void a(b bVar) {
        this.ax = new DJIVideoDecoder(this, false, bVar);
        this.ax.setRecvDataCallBack(new 5(this));
        FPVController.native_setDecodeMode(false);
    }

    private void b(b bVar) {
        this.ar = new dji.pilot.publics.c.f(this, bVar);
        this.ar.a(new 6(this));
        FPVController.native_setDecodeMode(g.b((Context) this, "DecodeMode", false));
    }

    private String a(int i) {
        int i2 = i / 3600;
        int i3 = i % 3600;
        int i4 = i3 / 60;
        i3 %= 60;
        return String.format(this.ay, new Object[]{Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i3)});
    }

    private void l() {
        this.Z = AnimationUtils.loadAnimation(this, R.anim.bt);
        this.aa = AnimationUtils.loadAnimation(this, R.anim.bu);
        this.ab = AnimationUtils.loadAnimation(this, R.anim.be);
        this.ac = AnimationUtils.loadAnimation(this, R.anim.bf);
    }

    private void m() {
        l();
        g();
        this.Y = new a(this);
        this.R = new 7(this);
        this.P = new 8(this);
        this.Q = new 9(this);
    }

    private Bitmap n() {
        return this.av.getBitmap(Bitmap.createBitmap(this.av.getWidth() / 2, this.av.getHeight() / 2, Config.RGB_565));
    }

    private void o() {
        if (!this.M.isShown()) {
            this.M.setBackground(new BitmapDrawable(getResources(), n()));
            if (this.ah.g == 1) {
                if (this.M.getRotation() == 0.0f) {
                    this.M.setRotation(90.0f);
                }
            } else if (this.M.getRotation() != 0.0f) {
                this.M.setRotation(0.0f);
            }
            this.M.show();
        }
        float progress = ((float) this.A.getProgress()) / 1000.0f;
        DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "Progress :" + this.A.getProgress(), false, true);
        DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "percentage :" + progress, false, true);
        progress *= (float) this.aq.g();
        this.aI = (long) progress;
        if (this.aq.l()) {
            DJILogHelper.getInstance().LOGD(this.TAG, "seek after over, time:" + progress, false, true);
            this.aq.a(this.ah.d, (int) progress);
            return;
        }
        this.aq.a((long) ((int) progress));
        DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "ms :" + progress + "ms", false, true);
    }

    private void p() {
        this.ai = h.getInstance();
        this.r.setOnClickListener(this.R);
        this.t.setOnClickListener(this.R);
        this.y.setOnClickListener(this.R);
        this.z.setOnClickListener(this.R);
        this.u.setOnClickListener(this.R);
        this.G.setOnClickListener(this.R);
        this.s.setText(this.T.e);
        if (S == 1 || S == 2) {
            this.s.go();
        }
        if (S == 1) {
            this.t.hide();
            this.A.setOnSeekBarChangeListener(this.Q);
            I();
        } else if (S == 2) {
            this.t.hide();
            this.A.setOnSeekBarChangeListener(this.Q);
            I();
        } else {
            this.v.setOnCompletionListener(this.U);
            this.v.setOnErrorListener(this.V);
            this.A.setOnSeekBarChangeListener(this.Q);
            this.t.show();
            this.v.setVideoPath(this.T.o);
            this.v.start();
            I();
        }
    }

    public void finishThis() {
        if (S == 1) {
            setResult(this.p, new Intent());
            finish();
            return;
        }
        finish();
    }

    private void q() {
        this.aj.o();
        this.G.show();
        this.K.show();
        this.H.setText(R.string.playback_downloading_one);
        this.ai.a(this, this.F, this.H, this.J, this.ah, this.g);
    }

    private void r() {
        this.ai.c();
    }

    private void s() {
        if (this.ad == null) {
            this.ad = dji.pilot.publics.widget.b.a(this, R.string.fpv_playback_del_video, R.string.btn_dlg_no, new 10(this), R.string.btn_dlg_yes, new 11(this));
            this.ad.setCancelable(true);
            this.ad.setCanceledOnTouchOutside(true);
        }
        if (this.ad != null && !this.ad.isShowing()) {
            this.ad.a(R.string.fpv_playback_del_video);
            this.ad.show();
        }
    }

    private void t() {
        if (this.ad != null && this.ad.isShowing()) {
            this.ad.dismiss();
            this.ad = null;
        }
    }

    private void u() {
        t();
    }

    private void v() {
        t();
        if (this.aj != null) {
            this.aj.o();
        }
        b((int) R.string.app_deleting);
        Intent intent = new Intent();
        intent.putExtra("Pos", -1);
        setResult(-1, intent);
        finish();
        E();
    }

    private void w() {
        if (this.ae == null) {
            this.ae = dji.pilot.publics.widget.b.a(this, R.string.fpv_playback_download_files, R.string.btn_dlg_no, new 13(this), R.string.btn_dlg_yes, new 14(this));
            this.ae.setCancelable(true);
            this.ae.setCanceledOnTouchOutside(true);
        }
        if (this.ae != null && !this.ae.isShowing()) {
            this.ae.a(R.string.fpv_playback_download_files);
            this.ae.show();
        }
    }

    private void x() {
        if (this.ae != null && this.ae.isShowing()) {
            this.ae.dismiss();
            this.ae = null;
        }
    }

    private void y() {
        x();
    }

    private void z() {
        x();
        this.u.setEnabled(false);
        this.E.show();
        this.x.go();
        q();
    }

    private void A() {
        if (this.af == null) {
            this.af = dji.pilot.publics.widget.b.a(this, R.string.fpv_playback_download_cancel, R.string.btn_dlg_no, new 15(this), R.string.btn_dlg_yes, new 16(this));
            this.af.setCancelable(true);
            this.af.setCanceledOnTouchOutside(true);
        }
        if (this.af != null && !this.af.isShowing()) {
            this.af.a(R.string.fpv_playback_download_cancel);
            this.af.show();
        }
    }

    private void B() {
        if (this.af != null && this.af.isShowing()) {
            this.af.dismiss();
            this.af = null;
        }
    }

    private void C() {
        B();
    }

    private void D() {
        B();
        r();
        this.E.go();
        this.u.setEnabled(true);
        this.x.show();
    }

    private void b(int i) {
        if (this.ag == null) {
            this.ag = k.a(this, i);
        }
        if (this.ag != null && !this.ag.isShowing()) {
            this.ag.show();
        }
    }

    private void E() {
        if (this.ag != null && this.ag.isShowing()) {
            this.ag.dismiss();
            this.ag = null;
        }
    }

    private void F() {
        if (this.O == null) {
            this.O = new a(this);
            if (S == 0) {
                this.O.a(1).a(this.P);
            } else if (S == 1) {
            }
        }
        this.O.a(this.t, 0, 0);
    }

    private void G() {
        if (this.O != null) {
            this.O.a();
        }
    }

    private void a(int i, long j) {
        G();
        if (S != 0) {
            return;
        }
        dji.pilot.usercenter.f.e.b bVar;
        Intent a;
        if (j == 2131299361) {
            bVar = new dji.pilot.usercenter.f.e.b();
            a = dji.pilot.usercenter.f.e.a(this, Scheme.FILE.crop(this.T.o), dji.pilot.usercenter.f.e.c.a, bVar);
            if (bVar.b) {
                startActivity(a);
            } else {
                Toast.makeText(getApplicationContext(), R.string.share_youku_install, 0).show();
            }
        } else if (j == 2131299363) {
            bVar = new dji.pilot.usercenter.f.e.b();
            a = dji.pilot.usercenter.f.e.a(getBaseContext(), Scheme.FILE.crop(this.T.o), dji.pilot.usercenter.f.e.c.b, bVar);
            if (!bVar.b) {
                Toast.makeText(getApplicationContext(), R.string.share_youtube_install, 0).show();
            } else if (bVar.a) {
                startActivity(a);
            } else {
                Toast.makeText(getApplicationContext(), R.string.usercenter_cloudalbum_share_notsupport, 0).show();
            }
        }
    }

    private void H() {
        if (S == 1) {
            if (this.aj != null) {
                DJILogHelper.getInstance().LOGD("", "isPlaying=" + this.aj.q(), false, true);
                if (this.v.isPlaying() || this.aj.q() == 10 || this.aj.q() == 1 || (this.aq != null && this.aq.h())) {
                    this.y.setImageResource(R.drawable.fpv_playback_video_pause);
                    this.u.go();
                    return;
                }
                this.y.setImageResource(R.drawable.fpv_playback_video_play);
                this.u.show();
            }
        } else if (S != 2) {
        } else {
            if (this.aq == null || !this.aq.h()) {
                this.y.setImageResource(R.drawable.fpv_playback_video_play);
                this.u.show();
                return;
            }
            this.y.setImageResource(R.drawable.fpv_playback_video_pause);
            this.u.go();
        }
    }

    private void I() {
        if (this.q.getVisibility() != 0) {
            this.q.show();
            this.q.startAnimation(this.Z);
            this.w.show();
            this.w.startAnimation(this.ab);
            T();
            if (this.az) {
                this.L.show();
                this.L.setAnimation(this.Z);
            }
        }
        this.Y.removeMessages(4096);
        this.Y.sendEmptyMessageDelayed(4096, k);
        if (!this.Y.hasMessages(4097)) {
            this.Y.sendEmptyMessage(4097);
        }
    }

    private void J() {
        if (this.q.getVisibility() == 0) {
            this.q.go();
            this.q.startAnimation(this.aa);
            this.w.go();
            this.w.startAnimation(this.ac);
            if (this.az) {
                this.L.go();
                this.L.setAnimation(this.aa);
            }
        }
        this.Y.removeMessages(4096);
        this.Y.removeMessages(4097);
    }

    private void K() {
        if (S == 0) {
            this.u.show();
            this.v.seekTo(0);
            this.A.setProgress(0);
            this.A.setSecondaryProgress(0);
            H();
            return;
        }
        DJILogHelper.getInstance().LOGD("", "big show1", false, true);
        this.u.show();
        this.A.setProgress(0);
        this.A.setSecondaryProgress(0);
        H();
    }

    private void L() {
        if (this.v.isPlaying()) {
            this.v.pause();
        } else {
            this.v.start();
        }
        H();
    }

    private void M() {
        if (this.aq.h()) {
            this.aq.e();
        } else if (this.aq.i()) {
            this.aq.c();
        } else if (this.aq.l()) {
            this.aq.a(this.ah.d, -1);
        }
        DJILogHelper.getInstance().LOGD("", "touch player operator", false, true);
    }

    private void N() {
        if (this.aj.q() == 10) {
            this.aj.m();
        } else if (this.aj.q() == 2 || this.aj.q() == 3) {
            DJILogHelper.getInstance().LOGD("", "resume", false, true);
            this.aj.n();
        } else if (this.aj.q() == 0) {
            this.aj.k();
        }
        H();
    }

    private void O() {
        if (this.v.isPlaying()) {
            this.v.pause();
            this.v.seekTo(0);
            this.A.setProgress(0);
            this.A.setSecondaryProgress(0);
        } else {
            this.v.start();
        }
        H();
    }

    private void P() {
        this.Y.removeMessages(4097);
        this.aj.o();
        this.A.setProgress(0);
        this.A.setSecondaryProgress(0);
        H();
    }

    private void Q() {
        this.aq.d();
    }

    private void a(int i, boolean z) {
        if (!z) {
            return;
        }
        long duration;
        if (S == 0) {
            duration = (long) this.v.getDuration();
            long j = (((long) i) * duration) / 1000;
            this.v.seekTo((int) j);
            a((int) (j / 1000), (int) (duration / 1000));
        } else if (S == 1) {
            duration = (((long) this.aj.h()) * ((long) i)) / 1000;
            DJILogHelper.getInstance().LOGD("", "progress=" + i + " newposition=" + duration, true, true);
            this.aj.b((int) duration);
            this.C.setText(a((int) duration) + dji.pilot.usercenter.protocol.d.t);
        }
    }

    private void R() {
        this.W = true;
        this.Y.removeMessages(4097);
    }

    public void a() {
        this.W = false;
        if (!this.Y.hasMessages(4097)) {
            this.Y.sendEmptyMessage(4097);
        }
    }

    @SuppressLint({"DefaultLocale"})
    private String c(int i) {
        int[] e = dji.pilot.fpv.d.b.e(i);
        return String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])});
    }

    private void a(int i, int i2) {
        this.B.setText(getString(R.string.fpv_playback_videotime, new Object[]{c(i), c(i2)}));
    }

    private void S() {
        if (this.aj != null) {
            T();
            if (!this.W && this.w.getVisibility() == 0) {
                this.Y.sendEmptyMessageDelayed(4097, 1000);
            }
        }
    }

    private int T() {
        int i = 0;
        if (!this.W) {
            int duration;
            if (S == 0) {
                i = this.v.getCurrentPosition();
                duration = this.v.getDuration();
                if (duration > 0) {
                    this.A.setProgress((int) ((((long) i) * 1000) / ((long) duration)));
                    this.A.setSecondaryProgress(this.v.getBufferPercentage() * 10);
                }
            } else if (S != 2) {
                i = this.aj.i();
                duration = this.aj.h();
                if (duration > 0) {
                    this.A.setProgress((int) ((((long) i) * 1000) / ((long) duration)));
                }
            }
        }
        return i;
    }

    private void U() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private void V() {
        dji.thirdparty.a.c.a().d((Object) this);
    }

    public void onEventBackgroundThread(p pVar) {
        switch (17.a[pVar.ordinal()]) {
            case 2:
                finishThis();
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (17.b[oVar.ordinal()]) {
            case 1:
                W();
                return;
            case 2:
                finishThis();
                return;
            default:
                return;
        }
    }

    private void W() {
        if (i.getInstance().c() == ProductType.KumquatS) {
            aD = 2500;
        } else {
            aD = 200;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.getMode() != MODE.PLAYBACK) {
            finishThis();
        }
    }
}
