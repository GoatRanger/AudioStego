package dji.thirdparty.afinal;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import dji.thirdparty.afinal.a.b.a;
import dji.thirdparty.afinal.a.b.b;
import dji.thirdparty.afinal.a.b.c;
import java.lang.reflect.Field;

public abstract class FinalActivity extends Activity {
    public final String TAG = getClass().getSimpleName();

    public void setContentView(int i) {
        super.setContentView(i);
        initView();
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        initView();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        initView();
    }

    private void initView() {
        Class cls = getClass();
        while (cls != null && !cls.getName().startsWith("android.")) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    try {
                        field.setAccessible(true);
                        if (field.get(this) == null) {
                            c cVar = (c) field.getAnnotation(c.class);
                            if (cVar != null) {
                                int a = cVar.a();
                                if (a == 0) {
                                    String b = cVar.b();
                                    if (!b.equals(null)) {
                                        a = getResources().getIdentifier(b, "id", getPackageName());
                                    }
                                }
                                field.set(this, findViewById(a));
                                setListener(field, cVar.c(), a.a);
                                setListener(field, cVar.d(), a.b);
                                setListener(field, cVar.e(), a.c);
                                setListener(field, cVar.f(), a.d);
                                b g = cVar.g();
                                if (!TextUtils.isEmpty(g.a())) {
                                    setViewSelectListener(field, g.a(), g.b());
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            cls = cls.getSuperclass();
        }
    }

    private void setViewSelectListener(Field field, String str, String str2) throws Exception {
        Object obj = field.get(this);
        if (obj instanceof View) {
            ((AbsListView) obj).setOnItemSelectedListener(new a(this).e(str).f(str2));
        }
    }

    private void setListener(Field field, String str, a aVar) throws Exception {
        if (str != null && str.trim().length() != 0) {
            Object obj = field.get(this);
            switch (1.a[aVar.ordinal()]) {
                case 1:
                    if (obj instanceof View) {
                        ((View) obj).setOnClickListener(new a(this).a(str));
                        return;
                    }
                    return;
                case 2:
                    if (obj instanceof AbsListView) {
                        ((AbsListView) obj).setOnItemClickListener(new a(this).d(str));
                        return;
                    }
                    return;
                case 3:
                    if (obj instanceof View) {
                        ((View) obj).setOnLongClickListener(new a(this).b(str));
                        return;
                    }
                    return;
                case 4:
                    if (obj instanceof AbsListView) {
                        ((AbsListView) obj).setOnItemLongClickListener(new a(this).c(str));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void finish() {
        super.finish();
    }
}
