package dji.pilot.publics.c;

import android.content.Context;
import android.os.Vibrator;

public class e {
    private Vibrator a;
    private Context b;

    private static final class a {
        private static final e a = new e();

        private a() {
        }
    }

    public static e getInstance() {
        return a.a;
    }

    public void a(Context context) {
        if (this.b == null) {
            this.a = (Vibrator) context.getSystemService("vibrator");
            this.b = context.getApplicationContext();
        }
    }

    public void a() {
        if (this.a != null && this.a.hasVibrator()) {
            this.a.cancel();
        }
        this.b = null;
        this.a = null;
    }

    public void a(long[] jArr, int i) {
        if (this.a != null && this.a.hasVibrator()) {
            this.a.vibrate(jArr, i);
        }
    }

    private e() {
        this.a = null;
        this.b = null;
    }
}
