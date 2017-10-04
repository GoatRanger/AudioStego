package dji.pilot2.media.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.VideoView;
import com.dji.frame.c.b;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot2.media.view.DJIVideoPreviewSeekBar;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.lang.ref.WeakReference;

public class DJIVideoPreveiwActivity extends DJIBaseActivity {
    public static final String b = "key_filepath";
    private static final long c = 2500;
    private static final int d = 4096;
    dji.pilot2.media.view.DJIVideoPreviewSeekBar.a a = null;
    private DJITextView e = null;
    private DJITextView f = null;
    private DJILinearLayout g = null;
    private DJIRelativeLayout h = null;
    private DJIImageView i = null;
    private DJIImageView j;
    private DJIVideoPreviewSeekBar k = null;
    private VideoView l;
    private OnCompletionListener m = null;
    private OnErrorListener n = null;
    private a o = null;
    private volatile boolean p = false;
    private int q;
    private Animation r = null;
    private Animation s = null;
    private Animation t = null;
    private Animation u = null;
    private String v = null;
    private boolean w;
    private Handler x = new Handler();
    private Runnable y = new Runnable(this) {
        final /* synthetic */ DJIVideoPreveiwActivity a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.l.isPlaying()) {
                this.a.a(this.a.l.getCurrentPosition());
            }
            this.a.x.postDelayed(this.a.y, 50);
        }
    };
    private OnClickListener z = new OnClickListener(this) {
        final /* synthetic */ DJIVideoPreveiwActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cgy:
                    if (this.a.w) {
                        this.a.e();
                        return;
                    } else {
                        this.a.d();
                        return;
                    }
                case R.id.ch0:
                case R.id.ch4:
                    if (this.a.l.isPlaying()) {
                        this.a.l.pause();
                        this.a.i.setImageBitmap(BitmapFactory.decodeResource(this.a.getResources(), R.drawable.v2_video_player_start));
                        this.a.j.show();
                        return;
                    }
                    this.a.l.start();
                    this.a.o.sendEmptyMessageDelayed(4096, DJIVideoPreveiwActivity.c);
                    this.a.i.setImageBitmap(BitmapFactory.decodeResource(this.a.getResources(), R.drawable.v2_video_player_stop));
                    this.a.j.go();
                    return;
                case R.id.ch2:
                    this.a.finish();
                    return;
                default:
                    return;
            }
        }
    };

    private static final class a extends Handler {
        private final WeakReference<DJIVideoPreveiwActivity> a;

        public a(DJIVideoPreveiwActivity dJIVideoPreveiwActivity) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIVideoPreveiwActivity);
        }

        public void handleMessage(Message message) {
            DJIVideoPreveiwActivity dJIVideoPreveiwActivity = (DJIVideoPreveiwActivity) this.a.get();
            if (dJIVideoPreveiwActivity != null && !dJIVideoPreveiwActivity.isFinishing()) {
                switch (message.what) {
                    case 4096:
                        dJIVideoPreveiwActivity.e();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static void a(Context context, String str, int i) {
        if (str != null && str.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("key_filepath", str);
            b.a(context, DJIVideoPreveiwActivity.class, bundle, i);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.v = intent.getStringExtra("key_filepath");
            if (this.v != null || new File(this.v).exists()) {
                setContentView(R.layout.v2_activity_video_preview);
                if (getResources().getConfiguration().orientation != 2) {
                    setRequestedOrientation(0);
                }
                c();
                this.e = (DJITextView) findViewById(R.id.ch5);
                this.f = (DJITextView) findViewById(R.id.ch7);
                this.g = (DJILinearLayout) findViewById(R.id.ch3);
                this.h = (DJIRelativeLayout) findViewById(R.id.ch1);
                this.l = (VideoView) findViewById(R.id.cgz);
                this.i = (DJIImageView) findViewById(R.id.ch4);
                this.j = (DJIImageView) findViewById(R.id.ch0);
                this.k = (DJIVideoPreviewSeekBar) findViewById(R.id.ch6);
                a();
                findViewById(R.id.ch2).setOnClickListener(this.z);
                findViewById(R.id.cgy).setOnClickListener(this.z);
                this.i.setOnClickListener(this.z);
                this.j.setOnClickListener(this.z);
                this.k.setOnProgressChanged(this.a);
                this.l.setVideoPath(this.v);
                this.l.setOnCompletionListener(this.m);
                this.l.setOnErrorListener(this.n);
                this.x.post(this.y);
                this.e.setText("0:00");
                this.w = true;
                return;
            }
            finish();
            return;
        }
        finish();
    }

    protected void onResume() {
        super.onResume();
        System.putInt(getContentResolver(), "accelerometer_rotation", 0);
        this.l.start();
        this.i.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.v2_video_player_stop));
        this.j.go();
        this.o.sendEmptyMessageDelayed(4096, c);
    }

    protected void onPause() {
        super.onPause();
        System.putInt(getContentResolver(), "accelerometer_rotation", 1);
    }

    private void a() {
        this.m = new OnCompletionListener(this) {
            final /* synthetic */ DJIVideoPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.b();
            }
        };
        this.n = new OnErrorListener(this) {
            final /* synthetic */ DJIVideoPreveiwActivity a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.v("video error", String.format("error=%d, extra=%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
                Toast.makeText(this.a, this.a.getApplicationContext().getString(R.string.v2_video_player_error_msg), 1).show();
                this.a.finish();
                return true;
            }
        };
        this.a = new dji.pilot2.media.view.DJIVideoPreviewSeekBar.a(this) {
            final /* synthetic */ DJIVideoPreveiwActivity a;

            {
                this.a = r1;
            }

            public void a(int i, boolean z) {
                if (z) {
                    int b = (this.a.q * i) / 100;
                    this.a.p = true;
                    this.a.l.seekTo(b);
                    this.a.a(b);
                    Log.v("seek", String.format("progress=%d", new Object[]{Integer.valueOf(i)}));
                }
            }

            public void a(int i) {
                this.a.p = true;
                int b = (this.a.q * i) / 100;
                this.a.l.seekTo(b);
                this.a.a(b);
                this.a.o.removeMessages(4096);
                Log.v("seek", String.format("begin progress=%d", new Object[]{Integer.valueOf(i)}));
            }

            public void b(int i) {
                int b = (this.a.q * i) / 100;
                this.a.l.seekTo(b);
                this.a.a(b);
                this.a.p = false;
                this.a.o.sendEmptyMessageDelayed(4096, DJIVideoPreveiwActivity.c);
                Log.v("seek", String.format("end progress=%d", new Object[]{Integer.valueOf(i)}));
            }
        };
        this.l.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ DJIVideoPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.a.q = mediaPlayer.getDuration();
                int b = (this.a.q + 500) / 1000;
                int i = b / 60;
                b %= 60;
                this.a.f.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(b)}));
            }
        });
        this.o = new a(this);
    }

    private void b() {
        this.l.pause();
        this.l.seekTo(0);
        this.e.setText("0:00");
        this.k.setProgress(0.0f);
        d();
        this.i.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.v2_video_player_start));
        this.j.show();
    }

    private void a(int i) {
        if (this.q > 0 && !this.p) {
            this.k.setProgress((float) ((i * 100) / this.q));
        }
        int i2 = (i + 500) / 1000;
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = i2 / 60;
        i2 %= 60;
        this.e.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)}));
    }

    private void c() {
        this.r = AnimationUtils.loadAnimation(this, R.anim.br);
        this.s = AnimationUtils.loadAnimation(this, R.anim.bs);
        this.t = AnimationUtils.loadAnimation(this, R.anim.bc);
        this.u = AnimationUtils.loadAnimation(this, R.anim.bd);
    }

    private void d() {
        if (this.g.getVisibility() != 0) {
            this.g.show();
            this.g.startAnimation(this.t);
        }
        if (this.h.getVisibility() != 0) {
            this.h.show();
            this.h.startAnimation(this.r);
        }
        this.o.removeMessages(4096);
        this.w = true;
        this.o.sendEmptyMessageDelayed(4096, c);
    }

    private void e() {
        if (this.g.getVisibility() == 0) {
            this.g.go();
            this.g.startAnimation(this.u);
        }
        if (this.h.getVisibility() == 0) {
            this.h.go();
            this.h.startAnimation(this.s);
        }
        this.o.removeMessages(4096);
        this.w = false;
    }
}
