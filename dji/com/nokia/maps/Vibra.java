package com.nokia.maps;

import android.content.Context;
import android.os.Vibrator;
import com.nokia.maps.annotation.HybridPlusNative;

class Vibra {
    private final a a;
    private Vibrator b;
    private boolean c = false;
    private Runnable d = new Runnable(this) {
        final /* synthetic */ Vibra a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a.b();
        }
    };

    interface a {
        void a();

        void b();
    }

    public Vibra(Context context, a aVar) {
        dy.a((Object) aVar, "Cannot pass null listener to Vibra");
        this.b = (Vibrator) context.getSystemService("vibrator");
        if (context.checkCallingOrSelfPermission("android.permission.VIBRATE") == 0) {
            this.c = true;
        }
        this.a = aVar;
    }

    @HybridPlusNative
    public void vibrate(long j, int i) {
        int i2 = 0;
        if (this.c && i > 0) {
            int i3 = (i * 2) + 1;
            long[] jArr = new long[i3];
            jArr[0] = 0;
            int i4 = 1;
            while (i4 < i3) {
                int i5 = i4 + 1;
                jArr[i4] = j;
                i4 = i5 + 1;
                jArr[i5] = 150;
            }
            this.a.a();
            try {
                this.b.vibrate(jArr, -1);
            } catch (Exception e) {
                i2 = 1;
            }
            if (i2 != 0) {
                this.d.run();
            } else {
                ez.a(this.d, (((long) i) * j) + (((long) (i - 1)) * 150));
            }
        }
    }
}
