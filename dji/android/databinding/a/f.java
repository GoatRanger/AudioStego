package android.databinding.a;

import android.databinding.c;
import android.databinding.g;
import android.widget.AutoCompleteTextView;
import android.widget.AutoCompleteTextView.Validator;

@g(a = {@android.databinding.f(a = AutoCompleteTextView.class, b = "android:completionThreshold", c = "setThreshold"), @android.databinding.f(a = AutoCompleteTextView.class, b = "android:popupBackground", c = "setDropDownBackgroundDrawable"), @android.databinding.f(a = AutoCompleteTextView.class, b = "android:onDismiss", c = "setOnDismissListener"), @android.databinding.f(a = AutoCompleteTextView.class, b = "android:onItemClick", c = "setOnItemClickListener")})
public class f {

    public interface a {
        CharSequence a(CharSequence charSequence);
    }

    public interface b {
        boolean a(CharSequence charSequence);
    }

    @c(a = {"android:fixText", "android:isValid"}, b = false)
    public static void a(AutoCompleteTextView autoCompleteTextView, final a aVar, final b bVar) {
        if (aVar == null && bVar == null) {
            autoCompleteTextView.setValidator(null);
        } else {
            autoCompleteTextView.setValidator(new Validator() {
                public boolean isValid(CharSequence charSequence) {
                    if (bVar != null) {
                        return bVar.a(charSequence);
                    }
                    return true;
                }

                public CharSequence fixText(CharSequence charSequence) {
                    if (aVar != null) {
                        return aVar.a(charSequence);
                    }
                    return charSequence;
                }
            });
        }
    }

    @c(a = {"android:onItemSelected", "android:onNothingSelected"}, b = false)
    public static void a(AutoCompleteTextView autoCompleteTextView, android.databinding.a.e.a aVar, e.c cVar) {
        if (aVar == null && cVar == null) {
            autoCompleteTextView.setOnItemSelectedListener(null);
        } else {
            autoCompleteTextView.setOnItemSelectedListener(new android.databinding.a.e.b(aVar, cVar, null));
        }
    }
}
