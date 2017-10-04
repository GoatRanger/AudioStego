package dji.c;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import java.io.File;

public class b {
    protected boolean a;
    private MediaRecorder b;
    private a c;
    private int d = 600;
    private int e = 300;
    private Handler f = new Handler(new Callback(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            this.a.e();
            this.a.f.sendEmptyMessageDelayed(0, (long) this.a.e);
            return false;
        }
    });

    public interface a {
        void a();

        void a(int i);

        void b();

        void c();
    }

    public b(a aVar) {
        this.c = aVar;
    }

    public boolean a() {
        return this.a;
    }

    public void a(String str) {
        if (!this.a) {
            this.a = true;
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
                this.b = new MediaRecorder();
                this.b.setAudioSource(1);
                this.b.setOutputFormat(1);
                this.b.setAudioEncoder(1);
                this.b.setOutputFile(file.getAbsolutePath());
                this.b.setOnErrorListener(new OnErrorListener(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
                        this.a.b.stop();
                        this.a.b.release();
                        this.a.b = null;
                        this.a.a = false;
                        this.a.d();
                        if (this.a.c != null) {
                            this.a.c.c();
                        }
                    }
                });
                this.b.prepare();
                this.b.start();
                c();
                if (this.c != null) {
                    this.c.a();
                }
            } catch (Exception e) {
                this.a = false;
                d();
                if (this.c != null) {
                    this.c.c();
                }
                e.printStackTrace();
            }
        }
    }

    private void c() {
        if (this.c != null) {
            this.f.sendEmptyMessageDelayed(0, (long) this.e);
        }
    }

    private void d() {
        this.f.removeMessages(0);
    }

    public void b() {
        if (this.a) {
            this.b.stop();
            this.b.release();
            this.b = null;
            this.a = false;
            d();
            if (this.c != null) {
                this.c.b();
            }
        }
    }

    private void e() {
        if (this.b != null) {
            int maxAmplitude = this.b.getMaxAmplitude() / this.d;
            this.c.a((maxAmplitude > 1 ? (int) (Math.log10((double) maxAmplitude) * 20.0d) : 0) / 2);
        }
    }
}
