package dji.pilot.visual.a;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;

public class e {
    private final Context a = DJIApplication.a();
    private MediaPlayer b = null;

    public enum a {
        BEEP_NONE(0),
        BEEP_100(R.raw.tomato_beap_100),
        BEEP_250(R.raw.tomato_beap_250),
        BEEP_500(R.raw.tomato_beap_500),
        BEEP_1000(R.raw.tomato_beap_1000);
        
        private final int f;

        private a(int i) {
            this.f = i;
        }

        private int a() {
            return this.f;
        }
    }

    public void a(a aVar, boolean z) {
        if (this.b == null) {
            if (a.BEEP_NONE != aVar) {
                this.b = new MediaPlayer();
            }
        } else if (this.b.isPlaying()) {
            this.b.stop();
            this.b.reset();
        }
        if (a.BEEP_NONE != aVar) {
            try {
                this.b.setAudioStreamType(3);
                AssetFileDescriptor openRawResourceFd = this.a.getResources().openRawResourceFd(aVar.a());
                if (openRawResourceFd != null) {
                    this.b.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                    openRawResourceFd.close();
                    this.b.prepare();
                    this.b.setLooping(z);
                    this.b.start();
                }
            } catch (Exception e) {
            }
        }
    }

    public void a() {
        if (this.b != null && this.b.isPlaying()) {
            try {
                this.b.stop();
                this.b.reset();
            } catch (Exception e) {
            }
        }
    }

    public void b() {
        if (this.b != null) {
            try {
                if (this.b.isPlaying()) {
                    this.b.stop();
                }
                this.b.reset();
                this.b.release();
            } catch (Exception e) {
            }
            this.b = null;
        }
    }
}
