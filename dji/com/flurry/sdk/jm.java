package com.flurry.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;

public class jm extends ih {
    public WeakReference<Context> a;
    public jl b;
    public a c;
    public long d;

    public enum a {
        CREATE,
        SESSION_ID_CREATED,
        START,
        END,
        FINALIZE
    }

    public jm() {
        super("com.flurry.android.sdk.FlurrySessionEvent");
    }
}
