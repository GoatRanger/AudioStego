package android.databinding.a;

import android.databinding.c;
import android.util.SparseBooleanArray;
import android.widget.TableLayout;
import java.util.regex.Pattern;

public class ae {
    private static Pattern a = Pattern.compile("\\s*,\\s*");
    private static final int b = 20;

    @c(a = {"android:collapseColumns"})
    public static void a(TableLayout tableLayout, CharSequence charSequence) {
        SparseBooleanArray a = a(charSequence);
        for (int i = 0; i < 20; i++) {
            boolean z = a.get(i, false);
            if (z != tableLayout.isColumnCollapsed(i)) {
                tableLayout.setColumnCollapsed(i, z);
            }
        }
    }

    @c(a = {"android:shrinkColumns"})
    public static void b(TableLayout tableLayout, CharSequence charSequence) {
        int i = 0;
        if (charSequence == null || charSequence.length() <= 0 || charSequence.charAt(0) != '*') {
            tableLayout.setShrinkAllColumns(false);
            SparseBooleanArray a = a(charSequence);
            int size = a.size();
            while (i < size) {
                int keyAt = a.keyAt(i);
                boolean valueAt = a.valueAt(i);
                if (valueAt) {
                    tableLayout.setColumnShrinkable(keyAt, valueAt);
                }
                i++;
            }
            return;
        }
        tableLayout.setShrinkAllColumns(true);
    }

    @c(a = {"android:stretchColumns"})
    public static void c(TableLayout tableLayout, CharSequence charSequence) {
        int i = 0;
        if (charSequence == null || charSequence.length() <= 0 || charSequence.charAt(0) != '*') {
            tableLayout.setStretchAllColumns(false);
            SparseBooleanArray a = a(charSequence);
            int size = a.size();
            while (i < size) {
                int keyAt = a.keyAt(i);
                boolean valueAt = a.valueAt(i);
                if (valueAt) {
                    tableLayout.setColumnStretchable(keyAt, valueAt);
                }
                i++;
            }
            return;
        }
        tableLayout.setStretchAllColumns(true);
    }

    private static SparseBooleanArray a(CharSequence charSequence) {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        if (charSequence != null) {
            for (String parseInt : a.split(charSequence)) {
                try {
                    int parseInt2 = Integer.parseInt(parseInt);
                    if (parseInt2 >= 0) {
                        sparseBooleanArray.put(parseInt2, true);
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        return sparseBooleanArray;
    }
}
