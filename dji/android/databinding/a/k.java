package android.databinding.a;

import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

@g(a = {@f(a = CompoundButton.class, b = "android:buttonTint", c = "setButtonTintList"), @f(a = CompoundButton.class, b = "android:onCheckedChanged", c = "setOnCheckedChangeListener")})
@p(a = {@o(a = CompoundButton.class, b = "android:checked")})
public class k {
    @c(a = {"android:checked"})
    public static void a(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked() != z) {
            compoundButton.setChecked(z);
        }
    }

    @c(a = {"android:onCheckedChanged", "android:checkedAttrChanged"}, b = false)
    public static void a(CompoundButton compoundButton, final OnCheckedChangeListener onCheckedChangeListener, final n nVar) {
        if (nVar == null) {
            compoundButton.setOnCheckedChangeListener(onCheckedChangeListener);
        } else {
            compoundButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(compoundButton, z);
                    }
                    nVar.a();
                }
            });
        }
    }
}
