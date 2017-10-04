package dji.pilot.home.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.here.odnp.config.OdnpConfigStatic;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.natives.FPVController;
import dji.midware.natives.GroudStation;
import dji.pilot.R;
import dji.pilot.flightrecord.DJIRecordService;
import dji.pilot.flyforbid.FlyforbidUpdateService;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.usercenter.b.d;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.multimoment.template.TemplateController;
import dji.pilot2.multimoment.template.c;
import dji.publics.DJIUI.DJIImageView;
import java.io.File;
import java.io.IOException;

public class DJIRcMainActivity extends DJIFragmentActivityNoFullScreen {
    private static final String A = (Environment.getExternalStorageDirectory().getPath() + "/background_video_1.mp4");
    private static final String B = "/system/media";
    private static final String C = (Environment.getExternalStorageDirectory().getPath() + "/media");
    private static String D = null;
    private static final int F = 21;
    private static final int w = 4;
    private static final String z = "/system/media/background_video_1.mp4";
    private int E = 1;
    private a G;
    private Bitmap H;
    private Bitmap I;
    private Intent J;
    private Intent K;
    private Intent L;
    private Intent M;
    Options a = new Options();
    private SurfaceView r;
    private DJIImageView s;
    private MediaPlayer t;
    private AssetFileDescriptor[] u;
    private File v;
    private int x = 0;
    private boolean y = false;

    private class a extends Thread {
        public boolean a;
        public boolean b;
        final /* synthetic */ DJIRcMainActivity c;

        private a(DJIRcMainActivity dJIRcMainActivity) {
            this.c = dJIRcMainActivity;
            this.a = false;
            this.b = false;
        }

        public void run() {
            while (!this.a) {
                try {
                    Thread.sleep(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!this.b) {
                    this.c.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.l();
                        }
                    });
                }
            }
        }
    }

    private class b implements Callback {
        final /* synthetic */ DJIRcMainActivity a;

        private b(DJIRcMainActivity dJIRcMainActivity) {
            this.a = dJIRcMainActivity;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            try {
                this.a.a();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e2) {
                e2.printStackTrace();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.rc_main_layout);
        g();
        h();
        i();
        f();
    }

    private void f() {
        dji.pilot2.utils.a.a.getInstance().b(new dji.pilot2.utils.a.a.a(this) {
            final /* synthetic */ DJIRcMainActivity a;

            {
                this.a = r1;
            }

            public void a(String str, float f) {
            }

            public void a() {
                TemplateController.getInstance().getTemplates(this.a);
                c.getInstance().a(this.a);
                dji.pilot2.multimoment.template.b.getInstance().b(this.a);
            }

            public void b() {
            }
        });
        dji.pilot2.utils.a.a.getInstance().b((Context) this);
    }

    private void g() {
        DJIUpgradeP4Service.a(this);
        this.J = new Intent(this, DJIUpgradeP4Service.class);
        startService(this.J);
        this.K = new Intent(this, DJIGlobalService.class);
        startService(this.K);
        this.M = new Intent(this, FlyforbidUpdateService.class);
        startService(this.M);
        ServiceManager.getInstance().b();
        this.L = new Intent(this, DJIRecordService.class);
        startService(this.L);
    }

    private void h() {
        FPVController.loadLibrary();
        GroudStation.loadLibrary();
    }

    private void i() {
        k();
    }

    private void j() {
        if (f.getInstance().c()) {
            d.getInstance().a((Context) this);
            d.getInstance().a(true, new dji.pilot.usercenter.b.d.b());
        }
    }

    private void k() {
        this.s = (DJIImageView) findViewById(R.id.bjw);
        this.E %= 21;
        this.H = a(B + ("/pic" + this.E + ".png"));
        this.I = this.H;
        if (this.I != null) {
            this.s.setImageBitmap(this.I);
        }
        this.E++;
        this.G = new a();
        this.G.start();
    }

    private void l() {
        this.E %= 21;
        this.I = a(B + ("/pic" + this.E + ".png"));
        if (this.I != null && this.H != null) {
            Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{new BitmapDrawable(this.H), new BitmapDrawable(this.I)});
            this.s.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(1000);
            this.E++;
            this.H = this.I;
        }
    }

    private Bitmap a(String str) {
        Bitmap bitmap = null;
        try {
            if (new File(str).exists()) {
                this.a.inPreferredConfig = Config.RGB_565;
                bitmap = BitmapFactory.decodeFile(str);
            }
        } catch (Exception e) {
        }
        return bitmap;
    }

    protected void onResume() {
        super.onResume();
        if (this.t != null && this.y) {
            this.t.start();
            this.y = false;
        }
        if (this.G != null) {
            this.G.b = false;
        }
    }

    public void onPause() {
        super.onPause();
        if (this.t != null && this.t.isPlaying()) {
            this.t.pause();
            this.y = true;
        }
        if (this.G != null) {
            this.G.b = true;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.t != null) {
            this.t.stop();
            this.t.release();
        }
        if (this.G != null) {
            this.G.a = true;
        }
    }

    public void onBackPressed() {
    }

    private void m() {
        this.r = (SurfaceView) findViewById(R.id.bjv);
        this.r.getHolder().setKeepScreenOn(true);
        this.r.getHolder().addCallback(new b());
        n();
    }

    private void n() {
        this.v = new File(z);
        if (this.v.exists()) {
            D = z;
        } else {
            D = A;
        }
    }

    public void a() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
        if (this.t == null) {
            this.t = new MediaPlayer();
        }
        if (this.y) {
            this.t.setDisplay(this.r.getHolder());
            return;
        }
        this.t.reset();
        this.t.setVolume(0.0f, 0.0f);
        this.t.setAudioStreamType(3);
        this.t.setDataSource(D);
        this.t.setLooping(true);
        this.t.setDisplay(this.r.getHolder());
        this.t.prepareAsync();
        int i = this.x + 1;
        this.x = i;
        this.x = i % 4;
        this.t.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ DJIRcMainActivity a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.a.t.start();
            }
        });
        this.t.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ DJIRcMainActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
            }
        });
    }

    private void o() {
        try {
            this.t.reset();
            this.t.setVolume(0.0f, 0.0f);
            this.t.setDataSource(this.u[this.x].getFileDescriptor(), this.u[this.x].getStartOffset(), this.u[this.x].getLength());
            this.t.prepareAsync();
            int i = this.x + 1;
            this.x = i;
            this.x = i % 4;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
