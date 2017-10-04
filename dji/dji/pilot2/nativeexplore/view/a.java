package dji.pilot2.nativeexplore.view;

import android.content.Context;
import android.view.View;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class a<T> {
    protected T a;
    protected Context b;
    protected View c;
    protected ImageLoader d = ImageLoader.getInstance();

    public abstract View a();

    public abstract void a(T t);

    public abstract void b();

    public abstract void b(T t);

    public a(T t, Context context) {
        this.a = t;
        this.b = context;
        a(t);
    }
}
