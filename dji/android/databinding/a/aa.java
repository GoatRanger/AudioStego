package android.databinding.a;

import android.annotation.TargetApi;
import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.widget.Switch;

@g(a = {@f(a = Switch.class, b = "android:thumb", c = "setThumbDrawable"), @f(a = Switch.class, b = "android:track", c = "setTrackDrawable")})
@TargetApi(14)
public class aa {
    @c(a = {"android:switchTextAppearance"})
    public static void a(Switch switchR, int i) {
        switchR.setSwitchTextAppearance(null, i);
    }
}
