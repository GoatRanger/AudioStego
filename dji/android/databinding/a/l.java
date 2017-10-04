package android.databinding.a;

import android.content.res.ColorStateList;
import android.databinding.e;
import android.graphics.drawable.ColorDrawable;

public class l {
    @e
    public static ColorDrawable a(int i) {
        return new ColorDrawable(i);
    }

    @e
    public static ColorStateList b(int i) {
        return ColorStateList.valueOf(i);
    }
}
