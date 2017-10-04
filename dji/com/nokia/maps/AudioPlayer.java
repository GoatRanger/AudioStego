package com.nokia.maps;

import android.content.Context;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import com.here.android.mpa.guidance.AudioPlayerDelegate;
import com.nokia.maps.annotation.HybridPlusNative;
import dji.pilot2.multimoment.activity.DJIMultiMomentFineActivity;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class AudioPlayer {
    private static boolean g = false;
    private static int h = 3;
    private static float i = -1.0f;
    private TextToSpeech a = null;
    private Context b;
    private Locale c = Locale.US;
    private final Semaphore d = new Semaphore(0, true);
    private final LinkedList<String[]> e = new LinkedList();
    private c f;
    private AudioPlayerDelegate j = null;
    private volatile e k = e.NOT_INITIALIZED;
    private final OnInitListener l = new OnInitListener(this) {
        final /* synthetic */ AudioPlayer a;

        {
            this.a = r1;
        }

        public void onInit(int i) {
            bj.e("AudioPlayer", "TTS initialization status = %s", new Object[]{Integer.valueOf(i)});
            synchronized (this) {
                if (i == 0) {
                    try {
                        this.a.k = e.INITIALIZED;
                        this.a.a(this.a.c);
                        if (this.a.a.getDefaultEngine().compareToIgnoreCase("com.google.android.tts") == 0) {
                            bj.d("AudioPlayer", "TTS speach rate adjusted to %s", new Object[]{Float.valueOf(0.9f)});
                            this.a.a.setSpeechRate(0.9f);
                        }
                    } catch (Throwable th) {
                        bj.c("AudioPlayer", "TTS engine initialization failed!", new Object[]{th});
                    }
                } else {
                    bj.b("AudioPlayer", "Could not initialize TextToSpeech.", new Object[0]);
                }
            }
        }
    };
    private d m;
    private final Object n = new Object();
    private Timer o;
    private volatile boolean p = false;
    private final a q = new a(this) {
        final /* synthetic */ AudioPlayer a;

        {
            this.a = r1;
        }

        public boolean a() {
            return this.a.p;
        }

        public boolean b() {
            return !this.a.p;
        }
    };
    private final b r = new b();

    public interface a {
        void a();

        void b();
    }

    private static class b extends fc<a> {

        public interface a {
            boolean a();

            boolean b();
        }

        private b() {
        }

        public void a(final a aVar) {
            ez.a(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    if (aVar.a()) {
                        this.b.a(new fc$a<a>(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(a aVar) {
                                aVar.a();
                            }
                        });
                    }
                }
            });
        }

        public void b(final a aVar) {
            ez.a(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    if (aVar.b()) {
                        this.b.a(new fc$a<a>(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void a(a aVar) {
                                aVar.b();
                            }
                        });
                    }
                }
            });
        }
    }

    private static class c extends Thread {
        Object a = new Object();
        boolean b = false;
        List<MediaPlayer> c = new ArrayList();
        private volatile boolean d = true;
        private final Semaphore e;
        private final LinkedList<String[]> f;
        private int g;
        private final b h = new b();
        private final a i = new a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean a() {
                return this.a.b;
            }

            public boolean b() {
                return !this.a.b;
            }
        };

        public c(Semaphore semaphore, LinkedList<String[]> linkedList) {
            setName("AudioPlayer");
            setPriority(5);
            this.e = semaphore;
            this.f = linkedList;
        }

        public void a(a aVar) {
            this.h.a(aVar);
        }

        private void b() {
            this.h.a(this.i);
        }

        private void c() {
            this.h.b(this.i);
        }

        public void a() {
            this.d = false;
            this.e.release();
            try {
                join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                bj.b("AudioPlayer", "thread interrupted", new Object[0]);
            }
        }

        public void run() {
            while (true) {
                try {
                    this.e.acquire();
                    this.e.drainPermits();
                    if (this.d) {
                        String[] strArr;
                        int i;
                        int i2;
                        MediaPlayer mediaPlayer;
                        synchronized (this.f) {
                            strArr = (String[]) this.f.remove();
                            this.g = strArr.length;
                            if (this.f.size() > 0) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                        }
                        synchronized (this.a) {
                            this.b = true;
                            for (i2 = 0; i2 < this.g; i2++) {
                                MediaPlayer mediaPlayer2 = new MediaPlayer();
                                mediaPlayer2.setAudioStreamType(AudioPlayer.h);
                                if (AudioPlayer.i != -1.0f) {
                                    mediaPlayer2.setVolume(AudioPlayer.i, AudioPlayer.i);
                                }
                                char[] toCharArray = strArr[i2].toCharArray();
                                int length = toCharArray.length;
                                for (int i3 = 0; i3 < length; i3++) {
                                    if (toCharArray[i3] == '\\') {
                                        toCharArray[i3] = '/';
                                    }
                                }
                                mediaPlayer2.setDataSource(new String(toCharArray));
                                mediaPlayer2.prepare();
                                this.c.add(mediaPlayer2);
                            }
                            this.g = 0;
                            b();
                        }
                        Iterator it = this.c.iterator();
                        while (it.hasNext() && this.d && this.b) {
                            mediaPlayer = (MediaPlayer) it.next();
                            try {
                                mediaPlayer.start();
                                int duration = mediaPlayer.getDuration();
                                Thread.sleep((long) (duration + 10));
                                if (!it.hasNext()) {
                                    i2 = 0;
                                    while (mediaPlayer.isPlaying() && i2 < duration) {
                                        Thread.sleep(50);
                                        i2 += 50;
                                    }
                                }
                            } catch (InterruptedException e) {
                            } catch (IllegalStateException e2) {
                            }
                        }
                        for (MediaPlayer mediaPlayer3 : this.c) {
                            a(mediaPlayer3);
                        }
                        synchronized (this.a) {
                            this.c.clear();
                            if (this.b) {
                                this.b = false;
                                c();
                            }
                        }
                        if (!AudioPlayer.g) {
                            for (String str : strArr) {
                                if (str.contains("/gen/")) {
                                    try {
                                        File file = new File(str);
                                        if (!file.delete()) {
                                            throw new IOException("Failed to delete " + file.getName());
                                        }
                                    } catch (Exception e3) {
                                        bj.b("AudioPlayer", "exception %s", new Object[]{e3});
                                    } catch (RuntimeException e4) {
                                        throw e4;
                                    }
                                }
                            }
                        }
                        if (i != 0) {
                            this.e.release();
                        }
                    } else {
                        return;
                    }
                } catch (RuntimeException e42) {
                    throw e42;
                } catch (Exception e5) {
                    bj.b("AudioPlayer", "exception %s", new Object[]{e5});
                }
            }
        }

        private void a(MediaPlayer mediaPlayer) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                bj.b("AudioPlayer", "Exception occurred when calling mp.release(): %s", new Object[]{e.getLocalizedMessage()});
            }
        }
    }

    private class d extends TimerTask {
        public boolean a;
        final /* synthetic */ AudioPlayer b;

        private d(AudioPlayer audioPlayer) {
            this.b = audioPlayer;
            this.a = false;
        }

        public void run() {
            synchronized (this.b.n) {
                if (!(this.a || this.b.a.isSpeaking())) {
                    this.b.p = false;
                    this.b.k();
                    this.b.o.cancel();
                }
            }
        }
    }

    private enum e {
        NOT_INITIALIZED,
        INITIALIZING,
        INITIALIZED
    }

    public AudioPlayer(Context context) {
        this.b = context;
        this.f = new c(this.d, this.e);
        this.f.start();
    }

    private void h() {
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
        this.a.shutdown();
        bj.e("AudioPlayer", "TTS is shut down", new Object[0]);
    }

    protected void finalize() {
        h();
        bj.e("AudioPlayer", "Audio Player finalized", new Object[0]);
    }

    synchronized void a() {
        bj.e("AudioPlayer", "initializing TTS engine", new Object[0]);
        this.a = new TextToSpeech(this.b.getApplicationContext(), this.l);
        this.k = e.INITIALIZING;
    }

    public void a(Locale locale) {
        if (locale != null && !this.c.equals(locale)) {
            bj.e("AudioPlayer", "TTS trying to set locale %s", new Object[]{locale});
            this.c = locale;
            if (this.k == e.NOT_INITIALIZED) {
                a();
            } else if (this.k == e.INITIALIZED) {
                int language = this.a.setLanguage(this.c);
                if (language == 1 || language == 0 || language == 2) {
                    i();
                }
                bj.d("AudioPlayer", "setting TTS language to %s, with result %s", new Object[]{locale, Integer.valueOf(language)});
            }
        }
    }

    private void i() {
        if (this.k == e.INITIALIZED) {
            HashMap hashMap = new HashMap();
            hashMap.put("streamType", String.valueOf(h));
            hashMap.put(DJIMultiMomentFineActivity.V, String.valueOf(0));
            this.a.speak(dji.thirdparty.g.b.b.a.c.ik_, 1, hashMap);
        }
    }

    public int b(Locale locale) {
        if (this.k == e.INITIALIZED) {
            return this.a.isLanguageAvailable(locale);
        }
        return -1;
    }

    @HybridPlusNative
    public void playText(String str) {
        if (!(!str.contains("audio=") || str.indexOf(34) == -1 || str.indexOf(34) == str.lastIndexOf(34))) {
            String substring = str.substring(str.indexOf(34) + 1, str.lastIndexOf(34));
            playFiles(new String[]{substring});
            substring = str.substring(str.lastIndexOf(34));
            str = substring.substring(substring.indexOf("\\") + 1);
            if (str.trim().length() <= 1) {
                return;
            }
        }
        if (str.contains("\\")) {
            bj.b(getClass().getPackage().getName(), "Missed some keywords", new Object[0]);
        }
        if (this.j == null || !this.j.playText(str)) {
            a(str);
        }
    }

    private void a(String str) {
        if (this.k == null) {
            throw new IllegalStateException("TTS engine is not initialized");
        } else if (this.k == e.INITIALIZING) {
            bj.e("AudioPlayer", "waiting for TTS engine to finish initialization", new Object[0]);
        } else {
            bj.e("AudioPlayer", "TTS is playing [" + str + dji.pilot.usercenter.protocol.d.H, new Object[0]);
            if (this.k == e.INITIALIZED) {
                synchronized (this.n) {
                    if (!this.p) {
                        this.p = true;
                        j();
                        if (this.o != null) {
                            this.o.cancel();
                            if (this.m != null) {
                                this.m.a = true;
                            }
                        }
                        this.o = new Timer();
                        this.m = new d();
                    }
                    try {
                        this.o.scheduleAtFixedRate(this.m, 200, 200);
                    } catch (Exception e) {
                        bj.c("AudioPlayer", "exception %s", new Object[]{e});
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("streamType", String.valueOf(h));
                    if (i != -1.0f) {
                        hashMap.put(DJIMultiMomentFineActivity.V, String.valueOf(i));
                    }
                    this.a.speak(str, 1, hashMap);
                }
            }
        }
    }

    @HybridPlusNative
    public void playFiles(String[] strArr) {
        if (this.j == null || !this.j.playFiles(strArr)) {
            a(strArr);
        }
    }

    private void a(String[] strArr) {
        bj.e(getClass().getName(), ">> playFiles - # of files = %d", new Object[]{Integer.valueOf(strArr.length)});
        synchronized (this.e) {
            this.e.add(strArr);
            if (this.e.isEmpty()) {
                bj.b(getClass().getPackage().getName(), "No audio files", new Object[0]);
            }
        }
        this.d.release();
    }

    public void a(a aVar) {
        this.r.a(new WeakReference(aVar));
        this.f.a(aVar);
    }

    private void j() {
        this.r.a(this.q);
    }

    private void k() {
        this.r.b(this.q);
    }

    public void a(int i) {
        h = i;
    }

    public int b() {
        return h;
    }

    public void a(float f) {
        boolean z = f == -1.0f || (f >= 0.0f && f <= 1.0f);
        dy.a(z, "Audio Volume has to be between [0.0f,1.0f] or DEFAULT_AUDIO_VOLUME");
        i = f;
    }

    public float c() {
        return i;
    }

    public void d() {
        if (this.p) {
            synchronized (this.n) {
                if (this.p) {
                    bj.e("AudioPlayer", "stopping TTS", new Object[0]);
                    this.a.stop();
                    this.o.cancel();
                    this.m.cancel();
                    this.m.a = true;
                    this.p = false;
                    k();
                }
            }
        }
        if (this.f.b) {
            synchronized (this.f.a) {
                if (this.f.b) {
                    for (MediaPlayer mediaPlayer : this.f.c) {
                        try {
                            mediaPlayer.stop();
                        } catch (IllegalStateException e) {
                        }
                        try {
                            mediaPlayer.release();
                        } catch (Exception e2) {
                            bj.b("AudioPlayer", "Exception occurred mp.release(): %s", new Object[]{e2.getLocalizedMessage()});
                        }
                    }
                    this.f.b = false;
                    this.f.c();
                }
            }
        }
    }

    public void a(AudioPlayerDelegate audioPlayerDelegate) {
        d();
        this.j = audioPlayerDelegate;
    }
}
