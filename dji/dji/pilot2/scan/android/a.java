package dji.pilot2.scan.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import dji.pilot.R;
import java.io.Closeable;

public final class a implements OnCompletionListener, OnErrorListener, Closeable {
    private static final String a = a.class.getSimpleName();
    private static final float b = 0.1f;
    private static final long c = 200;
    private final Activity d;
    private MediaPlayer e = null;
    private boolean f;
    private boolean g;

    public a(Activity activity) {
        this.d = activity;
        a();
    }

    public synchronized void a() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.d);
        this.f = a(defaultSharedPreferences, this.d);
        this.g = defaultSharedPreferences.getBoolean(PreferencesActivity.i, false);
        if (this.f && this.e == null) {
            this.d.setVolumeControlStream(3);
            this.e = a(this.d);
        }
    }

    public synchronized void b() {
        if (this.f && this.e != null) {
            this.e.start();
        }
        if (this.g) {
            ((Vibrator) this.d.getSystemService("vibrator")).vibrate(200);
        }
    }

    private static boolean a(SharedPreferences sharedPreferences, Context context) {
        boolean z = sharedPreferences.getBoolean(PreferencesActivity.h, true);
        if (!z || ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return z;
        }
        return false;
    }

    private MediaPlayer a(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        AssetFileDescriptor openRawResourceFd;
        try {
            openRawResourceFd = context.getResources().openRawResourceFd(R.raw.beep);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.setVolume(b, b);
            mediaPlayer.prepare();
            return mediaPlayer;
        } catch (Throwable e) {
            Log.w(a, e);
            mediaPlayer.release();
            return null;
        } catch (Throwable th) {
            openRawResourceFd.close();
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(0);
    }

    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            this.d.finish();
        } else {
            mediaPlayer.release();
            this.e = null;
            a();
        }
        return true;
    }

    public synchronized void close() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
    }
}
