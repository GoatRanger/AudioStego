package android.databinding.a;

import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.support.v7.widget.SwitchCompat;

@g(a = {@f(a = SwitchCompat.class, b = "android:thumb", c = "setThumbDrawable"), @f(a = SwitchCompat.class, b = "android:track", c = "setTrackDrawable")})
public class ab {
    @c(a = {"android:switchTextAppearance"})
    public static void a(SwitchCompat switchCompat, int i) {
        switchCompat.setSwitchTextAppearance(null, i);
    }
}
