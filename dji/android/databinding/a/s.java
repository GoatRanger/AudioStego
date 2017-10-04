package android.databinding.a;

import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

@g(a = {@f(a = NumberPicker.class, b = "android:format", c = "setFormatter"), @f(a = NumberPicker.class, b = "android:onScrollStateChange", c = "setOnScrollListener")})
@p(a = {@o(a = NumberPicker.class, b = "android:value")})
public class s {
    @c(a = {"android:value"})
    public static void a(NumberPicker numberPicker, int i) {
        if (numberPicker.getValue() != i) {
            numberPicker.setValue(i);
        }
    }

    @c(a = {"android:onValueChange", "android:valueAttrChanged"}, b = false)
    public static void a(NumberPicker numberPicker, final OnValueChangeListener onValueChangeListener, final n nVar) {
        if (nVar == null) {
            numberPicker.setOnValueChangedListener(onValueChangeListener);
        } else {
            numberPicker.setOnValueChangedListener(new OnValueChangeListener() {
                public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                    if (onValueChangeListener != null) {
                        onValueChangeListener.onValueChange(numberPicker, i, i2);
                    }
                    nVar.a();
                }
            });
        }
    }
}
