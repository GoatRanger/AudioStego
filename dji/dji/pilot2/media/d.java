package dji.pilot2.media;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.view.SurfaceHolder;
import dji.midware.media.i.g;
import dji.midware.media.i.g.a;
import dji.midware.media.i.g.b;
import java.io.IOException;

class d implements g {
    private MediaPlayer a;
    private int b;
    private boolean c;
    private a d;
    private b e;

    public d() {
        this.a = null;
        this.a = new MediaPlayer();
        this.a.setOnSeekCompleteListener(new OnSeekCompleteListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSeekComplete(MediaPlayer mediaPlayer) {
                this.a.e.a(this.a);
            }
        });
        this.a.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.d.a(this.a);
            }
        });
    }

    public void a(String str) {
        try {
            this.b = 0;
            this.a.setDataSource(str);
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

    public void a() {
        this.a.release();
    }

    public void a(Object obj) {
        this.a.setDisplay((SurfaceHolder) obj);
    }

    public void b() {
        if (this.b == 0) {
            this.b = 0;
            try {
                this.a.prepare();
                this.b = this.a.getDuration();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void c() {
        this.a.start();
    }

    public void d() {
        this.a.stop();
    }

    public void e() {
        this.a.pause();
    }

    public int f() {
        return this.a.getCurrentPosition();
    }

    public int g() {
        return this.b;
    }

    public void a(long j) {
        this.a.seekTo((int) j);
    }

    public boolean h() {
        return this.a.isPlaying();
    }

    public boolean i() {
        return this.c;
    }

    public void j() {
        this.a.reset();
    }

    public void a(int i) {
        this.a.setAudioStreamType(i);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a(b bVar) {
        this.e = bVar;
    }
}
