package com.dji.frame.common;

import android.content.Context;
import android.media.SoundPool;
import com.dji.frame.R;
import java.util.HashMap;
import java.util.Map;

public class b {
    public static int a = 1;
    public static int b = 2;
    public static int c = 3;
    private SoundPool d;
    private Map<Integer, Integer> e = new HashMap();
    private Boolean f = Boolean.valueOf(false);

    public b(Context context) {
        a(context);
    }

    private void a(Context context) {
        this.d = new SoundPool(10, 1, 5);
        this.e.put(Integer.valueOf(a), Integer.valueOf(this.d.load(context, R.raw.effect_tick, 1)));
        this.e.put(Integer.valueOf(b), Integer.valueOf(this.d.load(context, R.raw.effect_tick, 1)));
        this.e.put(Integer.valueOf(c), Integer.valueOf(this.d.load(context, R.raw.effect_tick, 1)));
    }

    public void a(int i) {
        if (this.e.containsKey(Integer.valueOf(i)) && !this.f.booleanValue()) {
            this.d.play(((Integer) this.e.get(Integer.valueOf(i))).intValue(), 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    public void a(Boolean bool) {
        this.f = bool;
    }
}
