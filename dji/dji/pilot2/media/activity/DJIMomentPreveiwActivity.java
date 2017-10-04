package dji.pilot2.media.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.VideoView;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.media.i.g;
import dji.midware.media.i.g.b;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.mode.VideoPreviewInfo;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.library.h;
import dji.pilot2.media.view.DJIVideoPreviewSeekBar;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.multimoment.videolib.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;

public class DJIMomentPreveiwActivity extends DJIActivityNoFullScreen {
    private static h ah = null;
    public static final String b = "key_manage";
    public static final String c = "key_videoinfo";
    public static final String d = "key_selected";
    public static final String t = "key_input";
    public static final String u = "key_path";
    private static final long w = 2500;
    private static final int x = 4096;
    private DJITextView A = null;
    private DJITextView B = null;
    private DJITextView C = null;
    private View D = null;
    private View E = null;
    private DJIImageView F = null;
    private DJIImageView G = null;
    private DJIImageView H = null;
    private DJIImageView I = null;
    private DJILinearLayout J = null;
    private DJIRelativeLayout K = null;
    private DJIVideoPreviewSeekBar L = null;
    private DJIVideoPreviewSeekBar M = null;
    private VideoView N;
    private VideoView O;
    private View P;
    private View Q;
    private OnCompletionListener R = null;
    private OnErrorListener S = null;
    private a T = null;
    private boolean U = false;
    private volatile boolean V = false;
    private boolean W = false;
    private int X;
    private Animation Y = null;
    private Animation Z = null;
    dji.pilot2.media.view.DJIVideoPreviewSeekBar.a a = null;
    private Animation aa = null;
    private Animation ab = null;
    private VideoPreviewInfo ac = null;
    private String ad;
    private boolean ae;
    private boolean af;
    private DJITextView ag;
    private g ai;
    private SurfaceView aj;
    private boolean ak = true;
    private dji.midware.media.i.g.a al;
    private b am;
    private volatile boolean an;
    private boolean ao;
    private Handler ap = new Handler();
    private Runnable aq = new Runnable(this) {
        final /* synthetic */ DJIMomentPreveiwActivity a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.U) {
                if (this.a.O.isPlaying()) {
                    this.a.a(this.a.O.getCurrentPosition());
                }
            } else if (this.a.ak) {
                if (this.a.N.isPlaying()) {
                    this.a.a(this.a.N.getCurrentPosition());
                }
            } else if (this.a.ai.h()) {
                this.a.a(this.a.ai.f());
            }
            this.a.ap.postDelayed(this.a.aq, 100);
        }
    };
    private OnClickListener ar = new OnClickListener(this) {
        final /* synthetic */ DJIMomentPreveiwActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.c_4:
                    if (DJIMomentPreveiwActivity.ah != null) {
                        DJIMomentPreveiwActivity.ah.a(this.a.W);
                    }
                    this.a.a();
                    return;
                case R.id.c_6:
                case R.id.c_o:
                    if (this.a.W) {
                        this.a.W = false;
                        this.a.H.setImageResource(R.drawable.v2_media_unselect);
                        this.a.I.setImageResource(R.drawable.v2_media_unselect);
                        if (!this.a.ae) {
                            c.a().e(dji.pilot2.library.a.MomentUnSelect);
                            return;
                        }
                        return;
                    }
                    this.a.W = true;
                    this.a.H.setImageResource(R.drawable.v2_media_select);
                    this.a.I.setImageResource(R.drawable.v2_media_select);
                    if (!this.a.ae) {
                        c.a().e(dji.pilot2.library.a.MomentSelect);
                        return;
                    }
                    return;
                case R.id.c_7:
                    if (this.a.N.isPlaying()) {
                        this.a.N.pause();
                        this.a.F.show();
                        return;
                    }
                    this.a.N.start();
                    this.a.F.go();
                    this.a.T.sendEmptyMessageDelayed(4096, DJIMomentPreveiwActivity.w);
                    this.a.q();
                    return;
                case R.id.c_a:
                    if (!this.a.N.isPlaying()) {
                        this.a.N.start();
                        this.a.F.go();
                        this.a.q();
                        return;
                    }
                    return;
                case R.id.c_e:
                    if (this.a.getResources().getConfiguration().orientation != 2 && this.a.ak) {
                        this.a.setRequestedOrientation(0);
                        this.a.U = true;
                        this.a.a(true);
                        this.a.E.setVisibility(8);
                        this.a.D.setVisibility(0);
                        int currentPosition = this.a.N.getCurrentPosition();
                        if (currentPosition <= 0) {
                            currentPosition = 100;
                        }
                        this.a.a(currentPosition);
                        this.a.O.seekTo(currentPosition);
                        if (this.a.N.isPlaying()) {
                            this.a.N.pause();
                            this.a.O.start();
                            this.a.G.go();
                            this.a.T.sendEmptyMessageDelayed(4096, DJIMomentPreveiwActivity.w);
                        } else {
                            this.a.p();
                            this.a.G.show();
                            this.a.k();
                        }
                        this.a.N.setVisibility(4);
                        this.a.O.setVisibility(0);
                        return;
                    }
                    return;
                case R.id.c_f:
                    this.a.m();
                    return;
                case R.id.c_g:
                    this.a.finish();
                    return;
                case R.id.c_h:
                    if (this.a.an) {
                        this.a.an = false;
                        c.a().e(dji.pilot2.library.a.SELECTCLEAR);
                        int[] iArr = new int[]{d.a(new String[]{Uri.parse(this.a.ac.o).getPath()}[0])};
                        Intent intent = new Intent(this.a, DJIMultiMomentEditActivity.class);
                        intent.putExtra("duration", iArr);
                        intent.putExtra(DJIMultiMomentEditActivity.M, r0);
                        this.a.startActivity(intent);
                        this.a.finish();
                        this.a.an = true;
                        return;
                    }
                    return;
                case R.id.c_j:
                    if (this.a.O.isPlaying()) {
                        this.a.O.pause();
                        this.a.G.show();
                        this.a.k();
                        return;
                    }
                    this.a.O.start();
                    this.a.G.go();
                    this.a.T.sendEmptyMessageDelayed(4096, DJIMomentPreveiwActivity.w);
                    this.a.q();
                    return;
                case R.id.c_n:
                case R.id.c_u:
                    this.a.i();
                    return;
                case R.id.c_p:
                    if (!this.a.O.isPlaying()) {
                        this.a.O.start();
                        this.a.G.go();
                        this.a.T.sendEmptyMessageDelayed(4096, DJIMomentPreveiwActivity.w);
                        this.a.q();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    Callback v = new Callback(this) {
        final /* synthetic */ DJIMomentPreveiwActivity a;

        {
            this.a = r1;
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.i("videoeditor", " ondestoryed");
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Log.i("videoeditor", " oncreate and play");
            if (this.a.ai != null && !this.a.ak) {
                this.a.ai.a(surfaceHolder);
                this.a.ai.b();
                if (this.a.P != null) {
                    this.a.P.setVisibility(8);
                }
                this.a.ai.c();
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }
    };
    private DJITextView y = null;
    private DJITextView z = null;

    private static final class a extends Handler {
        private final WeakReference<DJIMomentPreveiwActivity> a;

        public a(DJIMomentPreveiwActivity dJIMomentPreveiwActivity) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIMomentPreveiwActivity);
        }

        public void handleMessage(Message message) {
            DJIMomentPreveiwActivity dJIMomentPreveiwActivity = (DJIMomentPreveiwActivity) this.a.get();
            if (dJIMomentPreveiwActivity != null && !dJIMomentPreveiwActivity.isFinishing()) {
                switch (message.what) {
                    case 4096:
                        dJIMomentPreveiwActivity.l();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static void a(Context context, VideoPreviewInfo videoPreviewInfo, boolean z, int i) {
        if (videoPreviewInfo != null && videoPreviewInfo.o != null && videoPreviewInfo.o.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("key_videoinfo", videoPreviewInfo);
            bundle.putBoolean(d, z);
            ah = null;
            com.dji.frame.c.b.a(context, DJIMomentPreveiwActivity.class, bundle, i);
        }
    }

    public static void a(Context context, String str, boolean z, boolean z2, int i, h hVar, boolean z3) {
        if (str != null) {
            Bundle bundle = new Bundle();
            Log.i("zc", "init :" + str);
            bundle.putBoolean(t, z2);
            bundle.putBoolean(b, z3);
            bundle.putBoolean(d, z);
            bundle.putString(u, str);
            ah = hVar;
            com.dji.frame.c.b.a(context, DJIMomentPreveiwActivity.class, bundle, i);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.an = true;
        if (intent != null) {
            this.ae = intent.getBooleanExtra(t, false);
            if (this.ae) {
                this.ad = intent.getStringExtra(u);
                this.W = intent.getBooleanExtra(d, false);
                this.af = intent.getBooleanExtra(b, false);
            } else {
                this.ac = (VideoPreviewInfo) intent.getParcelableExtra("key_videoinfo");
                this.W = intent.getBooleanExtra(d, false);
                if (this.ac == null) {
                    e.a("DJIMomentPreveiwActivity video info null");
                    finish();
                    return;
                }
            }
            setContentView(R.layout.v2_activity_moment_preview);
            DJIOriLayout.setOrientationByDevice(this);
            this.D = findViewById(R.id.c_j);
            this.E = findViewById(R.id.c_3);
            this.C = (DJITextView) findViewById(R.id.c_5);
            this.ag = (DJITextView) findViewById(R.id.c_g);
            if (this.af) {
                this.ag.setText(getResources().getString(R.string.v2_lib_managesure));
            } else {
                this.ag.setText(getResources().getString(R.string.v2_lib_input));
            }
            this.ag.go();
            this.J = (DJILinearLayout) findViewById(R.id.c_q);
            this.K = (DJIRelativeLayout) findViewById(R.id.c_m);
            j();
            this.y = (DJITextView) findViewById(R.id.c_b);
            this.z = (DJITextView) findViewById(R.id.c_d);
            this.A = (DJITextView) findViewById(R.id.c_r);
            this.B = (DJITextView) findViewById(R.id.c_t);
            this.N = (VideoView) findViewById(R.id.c_8);
            this.P = findViewById(R.id.c__);
            this.Q = findViewById(R.id.c_l);
            this.aj = (SurfaceView) findViewById(R.id.c_9);
            this.aj.getHolder().setType(3);
            this.aj.getHolder().addCallback(this.v);
            WindowManager windowManager = getWindowManager();
            windowManager.getDefaultDisplay().getSize(new Point());
            this.O = (VideoView) findViewById(R.id.c_k);
            this.L = (DJIVideoPreviewSeekBar) findViewById(R.id.c_c);
            this.M = (DJIVideoPreviewSeekBar) findViewById(R.id.c_s);
            this.F = (DJIImageView) findViewById(R.id.c_a);
            this.G = (DJIImageView) findViewById(R.id.c_p);
            this.H = (DJIImageView) findViewById(R.id.c_6);
            this.I = (DJIImageView) findViewById(R.id.c_o);
            if (this.W) {
                this.H.setImageResource(R.drawable.v2_media_select);
                this.I.setImageResource(R.drawable.v2_media_select);
            } else {
                this.H.setImageResource(R.drawable.v2_media_unselect);
                this.I.setImageResource(R.drawable.v2_media_unselect);
            }
            this.y.setText("0:00");
            this.A.setText("0:00");
            DJIStateImageView dJIStateImageView = (DJIStateImageView) findViewById(R.id.c_f);
            dJIStateImageView.setOnClickListener(this.ar);
            View findViewById = findViewById(R.id.c_i);
            findViewById.setOnClickListener(this.ar);
            findViewById.setVisibility(8);
            Button button = (Button) findViewById(R.id.c_h);
            button.setOnClickListener(this.ar);
            findViewById(R.id.c_4).setOnClickListener(this.ar);
            findViewById(R.id.c_n).setOnClickListener(this.ar);
            findViewById(R.id.c_u).setOnClickListener(this.ar);
            findViewById(R.id.c_e).setOnClickListener(this.ar);
            findViewById(R.id.c_7).setOnClickListener(this.ar);
            findViewById(R.id.c_j).setOnClickListener(this.ar);
            this.F.setOnClickListener(this.ar);
            this.G.setOnClickListener(this.ar);
            this.H.setOnClickListener(this.ar);
            this.I.setOnClickListener(this.ar);
            this.ag.setOnClickListener(this.ar);
            this.ai = new dji.g.b.a();
            g();
            this.ai.a(new dji.midware.media.i.g.a(this) {
                final /* synthetic */ DJIMomentPreveiwActivity a;

                {
                    this.a = r1;
                }

                public void a(g gVar) {
                    Log.i("videoeditor", "complete!");
                    this.a.h();
                }
            });
            this.ai.a(this.am);
            this.L.setOnProgressChanged(this.a);
            this.M.setOnProgressChanged(this.a);
            if (this.ae) {
                Log.i("zty", "video path:" + this.ad);
                this.N.setVideoPath(this.ad);
                this.O.setVideoPath(this.ad);
                this.C.setVisibility(8);
                button.setVisibility(8);
                dJIStateImageView.go();
            } else {
                this.ag.go();
                if (this.ac.o.contains(".mp4")) {
                    this.N.setVideoPath(this.ac.o);
                    this.O.setVideoPath(this.ac.o);
                    this.aj.setVisibility(8);
                    this.N.setOnCompletionListener(this.R);
                    this.N.setOnErrorListener(this.S);
                    this.O.setOnCompletionListener(this.R);
                    this.O.setOnErrorListener(this.S);
                } else {
                    Log.i("videoeditor", " play mov");
                    this.ak = false;
                    this.N.setVisibility(8);
                    this.aj.setVisibility(0);
                    this.ai.j();
                    this.ai.a(3);
                    this.ai.a(this.ac.o);
                    Log.i("videoeditor", " play duration:" + this.ai.g());
                    this.X = this.ai.g();
                }
            }
            Log.i("videoeditor", " init");
            if (this.ad != null) {
                if (this.ad.toLowerCase().contains(".mp4")) {
                    Log.i("videoeditor", " mp4");
                    this.aj.setVisibility(8);
                    this.N.setOnCompletionListener(this.R);
                    this.N.setOnErrorListener(this.S);
                    this.O.setOnCompletionListener(this.R);
                    this.O.setOnErrorListener(this.S);
                    this.ak = true;
                } else if (this.ad.toLowerCase().contains(".mov")) {
                    Log.i("videoeditor", " mov");
                    this.ak = false;
                    this.N.setVisibility(8);
                    this.aj.setVisibility(0);
                    this.ai.a(3);
                    this.ai.j();
                    this.ai.a(this.ad);
                    this.X = this.ai.g();
                }
            }
            this.ap.post(this.aq);
            a(false);
            return;
        }
        finish();
    }

    private int f() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    protected void onResume() {
        super.onResume();
        System.putInt(getContentResolver(), "accelerometer_rotation", 0);
        if (this.ak) {
            this.N.start();
        }
        this.F.go();
        this.G.setVisibility(4);
    }

    protected void onPause() {
        super.onPause();
        System.putInt(getContentResolver(), "accelerometer_rotation", 1);
    }

    private void a(boolean z) {
        if (z) {
            LayoutParams attributes = getWindow().getAttributes();
            attributes.flags |= 1024;
            getWindow().setAttributes(attributes);
            return;
        }
        attributes = getWindow().getAttributes();
        attributes.flags &= -1025;
        getWindow().setAttributes(attributes);
    }

    private void g() {
        this.aj.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Log.i("videoeditor", "surface onclick");
                if (!this.a.ak && this.a.ai != null) {
                    if (this.a.ai.h()) {
                        this.a.ai.e();
                    } else {
                        this.a.ai.c();
                    }
                }
            }
        });
        this.R = new OnCompletionListener(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.h();
            }
        };
        this.S = new OnErrorListener(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.v("video error", String.format("error=%d, extra=%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
                if (this.a.U) {
                    this.a.O.stopPlayback();
                    this.a.O.resume();
                    this.a.O.start();
                } else {
                    this.a.N.stopPlayback();
                    this.a.N.resume();
                    this.a.N.start();
                }
                return true;
            }
        };
        this.a = new dji.pilot2.media.view.DJIVideoPreviewSeekBar.a(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z) {
                if (z) {
                    int h = (this.a.X * i) / 100;
                    this.a.V = true;
                    if (this.a.U) {
                        if (this.a.ak) {
                            this.a.O.seekTo(h);
                        } else {
                            this.a.ai.a((long) h);
                        }
                    } else if (this.a.ak) {
                        this.a.N.seekTo(h);
                    } else {
                        this.a.ai.a((long) h);
                    }
                    this.a.a(h);
                    Log.v("seek", String.format("progress=%d", new Object[]{Integer.valueOf(i)}));
                }
            }

            public void a(int i) {
                this.a.V = true;
                int h = (this.a.X * i) / 100;
                if (this.a.U) {
                    this.a.O.seekTo(h);
                } else if (this.a.ak) {
                    this.a.N.seekTo(h);
                } else {
                    this.a.ai.a((long) h);
                }
                this.a.a(h);
                Log.v("seek", String.format("begin progress=%d", new Object[]{Integer.valueOf(i)}));
            }

            public void b(int i) {
                int h = (this.a.X * i) / 100;
                if (this.a.U) {
                    this.a.O.seekTo(h);
                } else if (this.a.ak) {
                    this.a.N.seekTo(h);
                } else {
                    this.a.ai.a((long) h);
                }
                this.a.a(h);
                this.a.V = false;
                Log.v("seek", String.format("end progress=%d", new Object[]{Integer.valueOf(i)}));
            }
        };
        this.ao = true;
        this.N.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                if (this.a.ao) {
                    if (this.a.P != null) {
                        this.a.P.setVisibility(4);
                    }
                    if (this.a.Q != null) {
                        this.a.Q.setVisibility(4);
                    }
                    this.a.ao = false;
                }
                DJILogHelper.getInstance().LOGI("Lyric", "on prepared");
                this.a.X = mediaPlayer.getDuration();
                int intValue = new BigDecimal(((double) ((float) this.a.X)) / 1000.0d).setScale(0, 4).intValue();
                int i = intValue / 60;
                intValue %= 60;
                this.a.z.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(intValue)}));
                this.a.B.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(intValue)}));
            }
        });
        this.al = new dji.midware.media.i.g.a(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void a(g gVar) {
                this.a.h();
            }
        };
        this.am = new b(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void a(g gVar) {
            }
        };
        this.T = new a(this);
    }

    private void h() {
        if (this.U) {
            this.N.pause();
            this.O.seekTo(0);
            this.G.show();
            k();
        } else {
            if (this.ak) {
                this.O.pause();
                this.N.seekTo(0);
            } else {
                this.ai.a(0);
            }
            this.F.show();
        }
        this.M.setProgress(0.0f);
        this.A.setText("0:00");
        this.L.setProgress(0.0f);
        this.y.setText("0:00");
    }

    private void a(int i) {
        if (i < 0) {
            i = 0;
        }
        if (this.U) {
            if (this.X > 0 && !this.V) {
                this.M.setProgress((((float) i) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) this.X));
            }
            int i2 = (i + 500) / 1000;
            int i3 = i2 / 60;
            i2 %= 60;
            this.A.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)}));
            return;
        }
        if (this.X > 0 && !this.V) {
            this.L.setProgress((((float) i) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) this.X));
        }
        i2 = (i + 500) / 1000;
        i3 = i2 / 60;
        i2 %= 60;
        this.y.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)}));
    }

    private void i() {
        if (getResources().getConfiguration().orientation != 1) {
            setRequestedOrientation(1);
            this.U = false;
            a(false);
            this.E.setVisibility(0);
            this.D.setVisibility(8);
            int currentPosition = this.O.getCurrentPosition();
            if (currentPosition <= 0) {
                currentPosition = 100;
            }
            a(currentPosition);
            this.N.seekTo(currentPosition);
            if (this.O.isPlaying()) {
                this.O.pause();
                this.N.start();
                this.F.go();
            } else {
                p();
                this.F.show();
            }
            this.N.setVisibility(0);
            this.O.setVisibility(4);
        }
    }

    private void j() {
        this.Y = AnimationUtils.loadAnimation(this, R.anim.bt);
        this.Z = AnimationUtils.loadAnimation(this, R.anim.bu);
        this.aa = AnimationUtils.loadAnimation(this, R.anim.be);
        this.ab = AnimationUtils.loadAnimation(this, R.anim.bf);
    }

    private void k() {
        if (this.J.getVisibility() != 0) {
            this.J.show();
            this.J.startAnimation(this.aa);
        }
        if (this.K.getVisibility() != 0) {
            this.K.show();
            this.K.startAnimation(this.Y);
        }
        this.T.removeMessages(4096);
    }

    private void l() {
        if (this.J.getVisibility() == 0) {
            this.J.go();
            this.J.startAnimation(this.ab);
        }
        if (this.K.getVisibility() == 0) {
            this.K.go();
            this.K.startAnimation(this.Z);
        }
        this.T.removeMessages(4096);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.U) {
                i();
            } else {
                a();
            }
        }
        return false;
    }

    public void a() {
        if (ah != null) {
            ah.a(this.W);
        }
        finish();
        ah = null;
    }

    private void m() {
        Builder bVar = new dji.pilot2.publics.object.b(this);
        bVar.setMessage(R.string.fpv_playback_del_moment);
        bVar.setPositiveButton(R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.o();
            }
        });
        bVar.setNegativeButton(R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIMomentPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.n();
            }
        });
        bVar.show();
    }

    private void n() {
    }

    private void o() {
        c.a().e(dji.pilot2.library.a.MomentDelete);
        finish();
    }

    private void p() {
        if (VERSION.SDK_INT >= 21) {
            if (this.P != null) {
                this.P.setVisibility(0);
                DJILogHelper.getInstance().LOGI("Lyric", "show black cover");
            }
            if (this.Q != null) {
                this.Q.setVisibility(0);
                DJILogHelper.getInstance().LOGI("Lyric", "show black cover full screen");
            }
        }
    }

    private void q() {
        if (VERSION.SDK_INT >= 21) {
            if (this.P != null) {
                this.P.setVisibility(4);
            }
            if (this.Q != null) {
                this.Q.setVisibility(4);
            }
        }
    }
}
