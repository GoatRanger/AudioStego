package dji.c;

import android.media.MediaPlayer;

public class a {
    private MediaPlayer a;
    private boolean b;

    public void a(String str) {
        if (!this.b) {
            this.b = true;
            this.a = new MediaPlayer();
            try {
                this.a.setDataSource(str);
                this.a.prepare();
                this.a.start();
            } catch (Exception e) {
                this.a.stop();
                this.a.release();
                this.a = null;
                this.b = false;
                e.printStackTrace();
            }
        }
    }

    public void a() {
        if (this.b) {
            this.a.stop();
            this.a.release();
            this.a = null;
            this.b = false;
        }
    }
}
