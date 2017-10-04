package antistatic.spinnerwheel.a;

import android.content.Context;

public class c<T> extends b {
    private T[] k;

    public c(Context context, T[] tArr) {
        super(context);
        this.k = tArr;
    }

    public CharSequence f(int i) {
        if (i < 0 || i >= this.k.length) {
            return null;
        }
        Object obj = this.k[i];
        if (obj instanceof CharSequence) {
            return (CharSequence) obj;
        }
        return obj.toString();
    }

    public int h() {
        return this.k.length;
    }
}
