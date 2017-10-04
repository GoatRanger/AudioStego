package dji.publics.widget.djiviewpager;

import android.content.res.Resources;
import android.util.TypedValue;

public class a {
    public static int a(Resources resources, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, resources.getDisplayMetrics());
    }
}
