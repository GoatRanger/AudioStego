package dji.pilot.gallery.previewActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.gallery.previewActivity.widget.GalleryHScrollView;
import dji.pilot.gallery.previewActivity.widget.GalleryHScrollView.a;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.io.IOException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class DJIGalleryVideoActivity extends DJIBaseActivity implements Callback {
    public static final String a = "path";
    public static final String b = "title";
    public static final String c = "duration";
    public static final String d = "width";
    public static final String e = "height";
    public static int f;
    public static int g;
    private DJIStateImageView A;
    private DJITextView B;
    private DJIStateTextView C;
    private MediaPlayer D = new MediaPlayer();
    private SurfaceHolder E;
    private Boolean F;
    private Timer G;
    protected GalleryHScrollView h;
    protected LinearLayout i;
    protected DJIImageView j;
    protected View k;
    protected SurfaceView l;
    protected DJIImageView m;
    protected LinearLayout n;
    protected DJITextView o;
    protected DJITextView p;
    protected a q;
    protected int r;
    protected String s;
    protected String t;
    protected int u;
    protected int v;
    protected int w;
    protected TranslateAnimation x;
    protected TranslateAnimation y;
    private RelativeLayout z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_djiplayback_video);
        Intent intent = getIntent();
        this.s = intent.getStringExtra("path");
        this.t = intent.getStringExtra("title");
        this.u = intent.getIntExtra("duration", 0);
        this.v = intent.getIntExtra("width", 0);
        this.w = intent.getIntExtra("height", 0);
        if (this.s == null || "".equals(this.s)) {
            finish();
        }
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (getResources().getConfiguration().orientation == 2) {
                int i;
                f = displayMetrics.widthPixels > displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
                if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
                    i = displayMetrics.widthPixels;
                } else {
                    i = displayMetrics.heightPixels;
                }
                g = i;
            } else {
                f = displayMetrics.widthPixels;
                g = displayMetrics.widthPixels;
            }
        } else {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (getResources().getConfiguration().orientation == 2) {
                f = point.x > point.y ? point.x : point.y;
                g = point.y > point.x ? point.x : point.y;
            } else {
                f = point.x;
                g = point.y;
            }
        }
        a();
        c();
        b();
        a(this.l, this.v, this.w);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        e();
        super.onStop();
    }

    private void a() {
        this.z = (RelativeLayout) findViewById(R.id.ge);
        this.A = (DJIStateImageView) findViewById(R.id.ago);
        this.B = (DJITextView) findViewById(R.id.agp);
        this.C = (DJIStateTextView) findViewById(R.id.agq);
        this.B.setText(this.t);
        this.C.setVisibility(8);
        this.A.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.z.setVisibility(8);
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
    }

    private void b() {
        float b = (float) dji.publics.e.a.b(this, 40.0f);
        this.x = new TranslateAnimation(0.0f, 0.0f, b, 0.0f);
        this.x.setDuration(120);
        this.x.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.h.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.y = new TranslateAnimation(0.0f, 0.0f, 0.0f, b);
        this.y.setDuration(120);
        this.y.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.h.setVisibility(4);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void c() {
        this.l = (SurfaceView) findViewById(R.id.gh);
        this.k = findViewById(R.id.gf);
        this.h = (GalleryHScrollView) findViewById(R.id.gk);
        this.i = (LinearLayout) findViewById(R.id.gn);
        this.j = (DJIImageView) findViewById(R.id.go);
        this.n = (LinearLayout) findViewById(R.id.gj);
        this.o = (DJITextView) findViewById(R.id.gl);
        this.p = (DJITextView) findViewById(R.id.gm);
        this.m = (DJIImageView) findViewById(R.id.gi);
        this.q = new a(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3, int i4) {
                if (this.a.D != null && !this.a.D.isPlaying()) {
                    int scrollX = (this.a.h.getScrollX() * this.a.u) / this.a.h.getTotalWidth();
                    DJILogHelper.getInstance().LOGI("bob", "onScrollChanged seekD = " + scrollX);
                    this.a.D.seekTo(scrollX);
                }
            }

            public void a(int i) {
            }

            public void b(int i) {
            }

            public void c(int i) {
                if (this.a.D != null && this.a.D.isPlaying()) {
                    this.a.D.pause();
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.m.setVisibility(0);
                        }
                    });
                }
            }
        };
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.D != null && !this.a.D.isPlaying()) {
                    this.a.D.start();
                    this.a.m.setVisibility(4);
                }
            }
        });
        this.h.init(this.i, this.u, this.s, this.q);
        this.l.getHolder().addCallback(this);
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.D != null && this.a.D.isPlaying()) {
                    if (this.a.h.getVisibility() == 0) {
                        this.a.h.startAnimation(this.a.y);
                        this.a.n.setVisibility(4);
                        this.a.j.setVisibility(4);
                        return;
                    }
                    this.a.h.startAnimation(this.a.x);
                    this.a.n.setVisibility(0);
                    this.a.j.setVisibility(0);
                }
            }
        });
    }

    private void a(View view, int i, int i2) {
        if (getResources().getConfiguration().orientation != 2) {
            DJILogHelper.getInstance().LOGI("bob", "videoWidth = " + i + " videoHeight=" + i2);
            if (i2 < i) {
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                if (i2 < i) {
                    float f = ((float) i) / ((float) f);
                    layoutParams.height = (int) Math.ceil((double) (((float) i2) / f));
                    layoutParams.width = f;
                    DJILogHelper.getInstance().LOGI("bob", "vWidth = " + f + " params.height=" + layoutParams.height);
                }
                DJILogHelper.getInstance().LOGI("bob", "params.width = " + layoutParams.width + " params.height=" + layoutParams.height);
                layoutParams.addRule(13);
                view.setLayoutParams(layoutParams);
            }
        } else if (i2 > i) {
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            if (i2 > i) {
                float f2 = (float) (f < g ? f : g);
                float f3 = ((float) i2) / f2;
                layoutParams2.height = (int) f2;
                layoutParams2.width = (int) Math.ceil((double) (((float) i) / f3));
                DJILogHelper.getInstance().LOGI("bob", "vWidth = " + f3 + " params.height=" + layoutParams2.height + "params.width =" + layoutParams2.width);
            }
            DJILogHelper.getInstance().LOGI("bob", " params.height=" + layoutParams2.height + "params.width = " + layoutParams2.width);
            layoutParams2.addRule(13);
            view.setLayoutParams(layoutParams2);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.E = surfaceHolder;
        this.F = Boolean.valueOf(true);
        if (this.D != null) {
            d();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.E = null;
        this.F = Boolean.valueOf(false);
        e();
    }

    private void d() {
        if (this.D == null) {
            this.D = new MediaPlayer();
        }
        if (this.D.isPlaying()) {
            this.D.stop();
        }
        this.D.reset();
        this.D.setDisplay(this.E);
        try {
            this.D.setDataSource(this.s);
            this.D.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            DJILogHelper.getInstance().LOGI("bob", " setDataSource prepare exception");
        }
        this.D.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.F = Boolean.valueOf(false);
                this.a.E = null;
                this.a.j.setVisibility(0);
                this.a.D.stop();
                this.a.D.reset();
                this.a.D.release();
                this.a.D = null;
                if (this.a.G != null) {
                    this.a.G.cancel();
                    this.a.G = null;
                }
                this.a.finish();
            }
        });
        this.D.getCurrentPosition();
        this.D.getDuration();
        this.G = new Timer("progress");
        this.G.schedule(new TimerTask(this) {
            final /* synthetic */ DJIGalleryVideoActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass8 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.D != null && this.a.a.D.isPlaying()) {
                            this.a.a.a(this.a.a.D.getCurrentPosition(), this.a.a.D.getDuration());
                        }
                    }
                });
            }
        }, 0, 100);
        this.D.start();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void e() {
        if (this.D != null) {
            if (this.D.isPlaying()) {
                this.D.stop();
            }
            this.D.reset();
            this.D.release();
            this.D = null;
            if (this.G != null) {
                this.G.cancel();
                this.G = null;
            }
        }
    }

    public void a(int i, int i2) {
        if (this.h.getVisibility() == 0) {
            this.h.smoothScrollTo((this.h.getTotalWidth() * i) / i2, 0);
            int i3 = i / 60000;
            int i4 = (i % 60000) / 1000;
            this.o.setText(String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
            i3 = i2 / 60000;
            i4 = (i2 % 60000) / 1000;
            this.p.setText(String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
        }
    }
}
