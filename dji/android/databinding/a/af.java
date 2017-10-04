package android.databinding.a;

import android.databinding.f;
import android.databinding.g;
import android.databinding.m;
import android.databinding.n;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.DialerKeyListener;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TextKeyListener;
import android.text.method.TextKeyListener.Capitalize;
import android.util.Log;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.android.databinding.library.baseAdapters.R;

@g(a = {@f(a = TextView.class, b = "android:autoLink", c = "setAutoLinkMask"), @f(a = TextView.class, b = "android:drawablePadding", c = "setCompoundDrawablePadding"), @f(a = TextView.class, b = "android:editorExtras", c = "setInputExtras"), @f(a = TextView.class, b = "android:inputType", c = "setRawInputType"), @f(a = TextView.class, b = "android:scrollHorizontally", c = "setHorizontallyScrolling"), @f(a = TextView.class, b = "android:textAllCaps", c = "setAllCaps"), @f(a = TextView.class, b = "android:textColorHighlight", c = "setHighlightColor"), @f(a = TextView.class, b = "android:textColorHint", c = "setHintTextColor"), @f(a = TextView.class, b = "android:textColorLink", c = "setLinkTextColor"), @f(a = TextView.class, b = "android:onEditorAction", c = "setOnEditorActionListener")})
public class af {
    public static final int a = 1;
    public static final int b = 3;
    public static final int c = 5;
    private static final String d = "TextViewBindingAdapters";

    public interface a {
        void a(Editable editable);
    }

    public interface b {
        void a(CharSequence charSequence, int i, int i2, int i3);
    }

    public interface c {
        void a(CharSequence charSequence, int i, int i2, int i3);
    }

    @android.databinding.c(a = {"android:text"})
    public static void a(TextView textView, CharSequence charSequence) {
        CharSequence text = textView.getText();
        if (charSequence == text) {
            return;
        }
        if (charSequence != null || text.length() != 0) {
            if (charSequence instanceof Spanned) {
                if (charSequence.equals(text)) {
                    return;
                }
            } else if (!a(charSequence, text)) {
                return;
            }
            textView.setText(charSequence);
        }
    }

    @m(a = "android:text", b = "android:textAttrChanged")
    public static String a(TextView textView) {
        return textView.getText().toString();
    }

    @android.databinding.c(a = {"android:autoText"})
    public static void a(TextView textView, boolean z) {
        KeyListener keyListener = textView.getKeyListener();
        Capitalize capitalize = Capitalize.NONE;
        int inputType = keyListener != null ? keyListener.getInputType() : 0;
        if ((inputType & 4096) != 0) {
            capitalize = Capitalize.CHARACTERS;
        } else if ((inputType & 8192) != 0) {
            capitalize = Capitalize.WORDS;
        } else if ((inputType & 16384) != 0) {
            capitalize = Capitalize.SENTENCES;
        }
        textView.setKeyListener(TextKeyListener.getInstance(z, capitalize));
    }

    @android.databinding.c(a = {"android:capitalize"})
    public static void a(TextView textView, Capitalize capitalize) {
        textView.setKeyListener(TextKeyListener.getInstance((textView.getKeyListener().getInputType() & 32768) != 0, capitalize));
    }

    @android.databinding.c(a = {"android:bufferType"})
    public static void a(TextView textView, BufferType bufferType) {
        textView.setText(textView.getText(), bufferType);
    }

    @android.databinding.c(a = {"android:digits"})
    public static void b(TextView textView, CharSequence charSequence) {
        if (charSequence != null) {
            textView.setKeyListener(DigitsKeyListener.getInstance(charSequence.toString()));
        } else if (textView.getKeyListener() instanceof DigitsKeyListener) {
            textView.setKeyListener(null);
        }
    }

    @android.databinding.c(a = {"android:numeric"})
    public static void a(TextView textView, int i) {
        boolean z;
        boolean z2 = true;
        if ((i & 3) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((i & 5) == 0) {
            z2 = false;
        }
        textView.setKeyListener(DigitsKeyListener.getInstance(z, z2));
    }

    @android.databinding.c(a = {"android:phoneNumber"})
    public static void b(TextView textView, boolean z) {
        if (z) {
            textView.setKeyListener(DialerKeyListener.getInstance());
        } else if (textView.getKeyListener() instanceof DialerKeyListener) {
            textView.setKeyListener(null);
        }
    }

    private static void a(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    @android.databinding.c(a = {"android:drawableBottom"})
    public static void a(TextView textView, Drawable drawable) {
        a(drawable);
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        textView.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], drawable);
    }

    @android.databinding.c(a = {"android:drawableLeft"})
    public static void b(TextView textView, Drawable drawable) {
        a(drawable);
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        textView.setCompoundDrawables(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    @android.databinding.c(a = {"android:drawableRight"})
    public static void c(TextView textView, Drawable drawable) {
        a(drawable);
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        textView.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], drawable, compoundDrawables[3]);
    }

    @android.databinding.c(a = {"android:drawableTop"})
    public static void d(TextView textView, Drawable drawable) {
        a(drawable);
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        textView.setCompoundDrawables(compoundDrawables[0], drawable, compoundDrawables[2], compoundDrawables[3]);
    }

    @android.databinding.c(a = {"android:drawableStart"})
    public static void e(TextView textView, Drawable drawable) {
        if (VERSION.SDK_INT < 17) {
            b(textView, drawable);
            return;
        }
        a(drawable);
        Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
        textView.setCompoundDrawablesRelative(drawable, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
    }

    @android.databinding.c(a = {"android:drawableEnd"})
    public static void f(TextView textView, Drawable drawable) {
        if (VERSION.SDK_INT < 17) {
            c(textView, drawable);
            return;
        }
        a(drawable);
        Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
        textView.setCompoundDrawablesRelative(compoundDrawablesRelative[0], compoundDrawablesRelative[1], drawable, compoundDrawablesRelative[3]);
    }

    @android.databinding.c(a = {"android:imeActionLabel"})
    public static void c(TextView textView, CharSequence charSequence) {
        textView.setImeActionLabel(charSequence, textView.getImeActionId());
    }

    @android.databinding.c(a = {"android:imeActionId"})
    public static void b(TextView textView, int i) {
        textView.setImeActionLabel(textView.getImeActionLabel(), i);
    }

    @android.databinding.c(a = {"android:inputMethod"})
    public static void d(TextView textView, CharSequence charSequence) {
        try {
            textView.setKeyListener((KeyListener) Class.forName(charSequence.toString()).newInstance());
        } catch (Throwable e) {
            Log.e(d, "Could not create input method: " + charSequence, e);
        } catch (Throwable e2) {
            Log.e(d, "Could not create input method: " + charSequence, e2);
        } catch (Throwable e22) {
            Log.e(d, "Could not create input method: " + charSequence, e22);
        }
    }

    @android.databinding.c(a = {"android:lineSpacingExtra"})
    public static void a(TextView textView, float f) {
        if (VERSION.SDK_INT >= 16) {
            textView.setLineSpacing(f, textView.getLineSpacingMultiplier());
        } else {
            textView.setLineSpacing(f, 1.0f);
        }
    }

    @android.databinding.c(a = {"android:lineSpacingMultiplier"})
    public static void b(TextView textView, float f) {
        if (VERSION.SDK_INT >= 16) {
            textView.setLineSpacing(textView.getLineSpacingExtra(), f);
        } else {
            textView.setLineSpacing(0.0f, f);
        }
    }

    @android.databinding.c(a = {"android:maxLength"})
    public static void c(TextView textView, int i) {
        InputFilter[] inputFilterArr;
        int i2 = 1;
        Object filters = textView.getFilters();
        if (filters == null) {
            inputFilterArr = new InputFilter[]{new LengthFilter(i)};
        } else {
            Object obj;
            int i3 = 0;
            while (i3 < filters.length) {
                obj = filters[i3];
                if (obj instanceof LengthFilter) {
                    int i4 = VERSION.SDK_INT >= 21 ? ((LengthFilter) obj).getMax() != i ? 1 : 0 : 1;
                    if (i4 != 0) {
                        filters[i3] = new LengthFilter(i);
                    }
                    if (i2 != 0) {
                        inputFilterArr = new InputFilter[(filters.length + 1)];
                        System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                        inputFilterArr[inputFilterArr.length - 1] = new LengthFilter(i);
                    } else {
                        obj = filters;
                    }
                } else {
                    i3++;
                }
            }
            i2 = 0;
            if (i2 != 0) {
                obj = filters;
            } else {
                inputFilterArr = new InputFilter[(filters.length + 1)];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                inputFilterArr[inputFilterArr.length - 1] = new LengthFilter(i);
            }
        }
        textView.setFilters(inputFilterArr);
    }

    @android.databinding.c(a = {"android:password"})
    public static void c(TextView textView, boolean z) {
        if (z) {
            textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textView.setTransformationMethod(null);
        }
    }

    @android.databinding.c(a = {"android:shadowColor"})
    public static void d(TextView textView, int i) {
        if (VERSION.SDK_INT >= 16) {
            textView.setShadowLayer(textView.getShadowRadius(), textView.getShadowDx(), textView.getShadowDy(), i);
        }
    }

    @android.databinding.c(a = {"android:shadowDx"})
    public static void c(TextView textView, float f) {
        if (VERSION.SDK_INT >= 16) {
            int shadowColor = textView.getShadowColor();
            textView.setShadowLayer(textView.getShadowRadius(), f, textView.getShadowDy(), shadowColor);
        }
    }

    @android.databinding.c(a = {"android:shadowDy"})
    public static void d(TextView textView, float f) {
        if (VERSION.SDK_INT >= 16) {
            int shadowColor = textView.getShadowColor();
            textView.setShadowLayer(textView.getShadowRadius(), textView.getShadowDx(), f, shadowColor);
        }
    }

    @android.databinding.c(a = {"android:shadowRadius"})
    public static void e(TextView textView, float f) {
        if (VERSION.SDK_INT >= 16) {
            textView.setShadowLayer(f, textView.getShadowDx(), textView.getShadowDy(), textView.getShadowColor());
        }
    }

    @android.databinding.c(a = {"android:textSize"})
    public static void f(TextView textView, float f) {
        textView.setTextSize(0, f);
    }

    private static boolean a(CharSequence charSequence, CharSequence charSequence2) {
        boolean z;
        boolean z2;
        if (charSequence == null) {
            z = true;
        } else {
            z = false;
        }
        if (charSequence2 == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z != z2) {
            return true;
        }
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        if (length != charSequence2.length()) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    @android.databinding.c(a = {"android:beforeTextChanged", "android:onTextChanged", "android:afterTextChanged", "android:textAttrChanged"}, b = false)
    public static void a(TextView textView, final b bVar, final c cVar, final a aVar, final n nVar) {
        TextWatcher textWatcher;
        if (bVar == null && aVar == null && cVar == null && nVar == null) {
            textWatcher = null;
        } else {
            Object anonymousClass1 = new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (bVar != null) {
                        bVar.a(charSequence, i, i2, i3);
                    }
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (cVar != null) {
                        cVar.a(charSequence, i, i2, i3);
                    }
                    if (nVar != null) {
                        nVar.a();
                    }
                }

                public void afterTextChanged(Editable editable) {
                    if (aVar != null) {
                        aVar.a(editable);
                    }
                }
            };
        }
        TextWatcher textWatcher2 = (TextWatcher) r.a(textView, textWatcher, R.id.textWatcher);
        if (textWatcher2 != null) {
            textView.removeTextChangedListener(textWatcher2);
        }
        if (textWatcher != null) {
            textView.addTextChangedListener(textWatcher);
        }
    }
}
