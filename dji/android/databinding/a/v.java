package android.databinding.a;

import android.databinding.c;
import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

@p(a = {@o(a = RadioGroup.class, b = "android:checkedButton", d = "getCheckedRadioButtonId")})
public class v {
    @c(a = {"android:checkedButton"})
    public static void a(RadioGroup radioGroup, int i) {
        if (i != radioGroup.getCheckedRadioButtonId()) {
            radioGroup.check(i);
        }
    }

    @c(a = {"android:onCheckedChanged", "android:checkedButtonAttrChanged"}, b = false)
    public static void a(RadioGroup radioGroup, final OnCheckedChangeListener onCheckedChangeListener, final n nVar) {
        if (nVar == null) {
            radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        } else {
            radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(radioGroup, i);
                    }
                    nVar.a();
                }
            });
        }
    }
}
