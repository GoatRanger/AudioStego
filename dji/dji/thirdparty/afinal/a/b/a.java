package dji.thirdparty.afinal.a.b;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import dji.thirdparty.afinal.e.d;
import java.lang.reflect.Method;

public class a implements OnClickListener, OnLongClickListener, OnItemClickListener, OnItemLongClickListener, OnItemSelectedListener {
    private Object a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public a(Object obj) {
        this.a = obj;
    }

    public a a(String str) {
        this.b = str;
        return this;
    }

    public a b(String str) {
        this.c = str;
        return this;
    }

    public a c(String str) {
        this.g = str;
        return this;
    }

    public a d(String str) {
        this.d = str;
        return this;
    }

    public a e(String str) {
        this.e = str;
        return this;
    }

    public a f(String str) {
        this.f = str;
        return this;
    }

    public boolean onLongClick(View view) {
        return b(this.a, this.c, view);
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return d(this.a, this.g, adapterView, view, Integer.valueOf(i), Long.valueOf(j));
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        e(this.a, this.e, adapterView, view, Integer.valueOf(i), Long.valueOf(j));
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        f(this.a, this.f, adapterView);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        c(this.a, this.d, adapterView, view, Integer.valueOf(i), Long.valueOf(j));
    }

    public void onClick(View view) {
        a(this.a, this.b, view);
    }

    private static Object a(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[]{View.class});
            if (declaredMethod != null) {
                return declaredMethod.invoke(obj, objArr);
            }
            throw new d("no such method:" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean b(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return false;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[]{View.class});
            if (declaredMethod != null) {
                Object invoke = declaredMethod.invoke(obj, objArr);
                if (invoke != null) {
                    return Boolean.valueOf(invoke.toString()).booleanValue();
                }
                return false;
            }
            throw new d("no such method:" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Object c(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[]{AdapterView.class, View.class, Integer.TYPE, Long.TYPE});
            if (declaredMethod != null) {
                return declaredMethod.invoke(obj, objArr);
            }
            throw new d("no such method:" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean d(Object obj, String str, Object... objArr) {
        if (obj == null) {
            throw new d("invokeItemLongClickMethod: handler is null :");
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[]{AdapterView.class, View.class, Integer.TYPE, Long.TYPE});
            if (declaredMethod != null) {
                Object invoke = declaredMethod.invoke(obj, objArr);
                return Boolean.valueOf(invoke == null ? false : Boolean.valueOf(invoke.toString()).booleanValue()).booleanValue();
            }
            throw new d("no such method:" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Object e(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[]{AdapterView.class, View.class, Integer.TYPE, Long.TYPE});
            if (declaredMethod != null) {
                return declaredMethod.invoke(obj, objArr);
            }
            throw new d("no such method:" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object f(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[]{AdapterView.class});
            if (declaredMethod != null) {
                return declaredMethod.invoke(obj, objArr);
            }
            throw new d("no such method:" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
