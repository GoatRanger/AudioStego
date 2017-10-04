package android.databinding.a;

import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import java.util.List;

public class c {
    @android.databinding.c(a = {"android:entries"})
    public static <T extends CharSequence> void a(AbsSpinner absSpinner, T[] tArr) {
        Object obj = null;
        if (tArr != null) {
            SpinnerAdapter adapter = absSpinner.getAdapter();
            if (adapter == null || adapter.getCount() != tArr.length) {
                int i = 1;
            } else {
                for (int i2 = 0; i2 < tArr.length; i2++) {
                    if (!tArr[i2].equals(adapter.getItem(i2))) {
                        obj = 1;
                        break;
                    }
                }
            }
            if (obj != null) {
                SpinnerAdapter arrayAdapter = new ArrayAdapter(absSpinner.getContext(), 17367048, tArr);
                arrayAdapter.setDropDownViewResource(17367049);
                absSpinner.setAdapter(arrayAdapter);
                return;
            }
            return;
        }
        absSpinner.setAdapter(null);
    }

    @android.databinding.c(a = {"android:entries"})
    public static <T> void a(AbsSpinner absSpinner, List<T> list) {
        if (list != null) {
            SpinnerAdapter adapter = absSpinner.getAdapter();
            if (adapter instanceof t) {
                ((t) adapter).a(list);
                return;
            } else {
                absSpinner.setAdapter(new t(absSpinner.getContext(), list, 17367048, 17367049, 0));
                return;
            }
        }
        absSpinner.setAdapter(null);
    }
}
