package android.databinding.a;

import android.databinding.c;
import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import com.android.databinding.library.baseAdapters.R;

@p(a = {@o(a = DatePicker.class, b = "android:year"), @o(a = DatePicker.class, b = "android:month"), @o(a = DatePicker.class, b = "android:day", d = "getDayOfMonth")})
public class m {

    private static class a implements OnDateChangedListener {
        OnDateChangedListener a;
        n b;
        n c;
        n d;

        private a() {
        }

        public void a(OnDateChangedListener onDateChangedListener, n nVar, n nVar2, n nVar3) {
            this.a = onDateChangedListener;
            this.b = nVar;
            this.c = nVar2;
            this.d = nVar3;
        }

        public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
            if (this.a != null) {
                this.a.onDateChanged(datePicker, i, i2, i3);
            }
            if (this.b != null) {
                this.b.a();
            }
            if (this.c != null) {
                this.c.a();
            }
            if (this.d != null) {
                this.d.a();
            }
        }
    }

    @c(a = {"android:year", "android:month", "android:day", "android:onDateChanged", "android:yearAttrChanged", "android:monthAttrChanged", "android:dayAttrChanged"}, b = false)
    public static void a(DatePicker datePicker, int i, int i2, int i3, OnDateChangedListener onDateChangedListener, n nVar, n nVar2, n nVar3) {
        if (i == 0) {
            i = datePicker.getYear();
        }
        if (i3 == 0) {
            i3 = datePicker.getDayOfMonth();
        }
        if (nVar == null && nVar2 == null && nVar3 == null) {
            datePicker.init(i, i2, i3, onDateChangedListener);
            return;
        }
        OnDateChangedListener onDateChangedListener2 = (a) r.a(datePicker, R.id.onDateChanged);
        if (onDateChangedListener2 == null) {
            onDateChangedListener2 = new a();
            r.a(datePicker, onDateChangedListener2, R.id.onDateChanged);
        }
        onDateChangedListener2.a(onDateChangedListener, nVar, nVar2, nVar3);
        datePicker.init(i, i2, i3, onDateChangedListener2);
    }
}
