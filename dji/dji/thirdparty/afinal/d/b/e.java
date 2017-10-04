package dji.thirdparty.afinal.d.b;

import android.database.Cursor;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class e {
    private static SimpleDateFormat h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String a;
    private String b;
    private String c;
    private Class<?> d;
    private Field e;
    private Method f;
    private Method g;

    public void a(Object obj, Cursor cursor, int i) {
        if (cursor != null) {
            try {
                if (this.d == String.class) {
                    if (this.g == null) {
                        this.e.set(obj, cursor.getString(i));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{cursor.getString(i)});
                } else if (this.d == Integer.TYPE || this.d == Integer.class) {
                    if (this.g == null) {
                        this.e.set(obj, Integer.valueOf(cursor.getInt(i)));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Integer.valueOf(cursor.getInt(i))});
                } else if (this.d == Float.TYPE || this.d == Float.class) {
                    if (this.g == null) {
                        this.e.set(obj, Float.valueOf(cursor.getFloat(i)));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Float.valueOf(cursor.getFloat(i))});
                } else if (this.d == Double.TYPE || this.d == Double.class) {
                    if (this.g == null) {
                        this.e.set(obj, Double.valueOf(cursor.getDouble(i)));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Double.valueOf(cursor.getDouble(i))});
                } else if (this.d == Long.TYPE || this.d == Long.class) {
                    if (this.g == null) {
                        this.e.set(obj, Long.valueOf(cursor.getLong(i)));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Long.valueOf(cursor.getLong(i))});
                } else if (this.d == Date.class || this.d == java.sql.Date.class) {
                    if (this.g == null) {
                        this.e.set(obj, d(cursor.getString(i)));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{d(cursor.getString(i))});
                } else if (this.d == Boolean.TYPE || this.d == Boolean.class) {
                    if (this.g == null) {
                        this.e.set(obj, Boolean.valueOf("1".equals(cursor.getString(i))));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Boolean.valueOf("1".equals(cursor.getString(i)))});
                } else if (this.g == null) {
                    this.e.set(obj, cursor.getString(i));
                } else {
                    this.g.invoke(obj, new Object[]{cursor.getString(i)});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(Object obj, Object obj2) {
        if (obj2 != null) {
            try {
                if (this.d == String.class) {
                    if (this.g == null) {
                        this.e.set(obj, obj2.toString());
                        return;
                    }
                    this.g.invoke(obj, new Object[]{obj2.toString()});
                } else if (this.d == Integer.TYPE || this.d == Integer.class) {
                    if (this.g == null) {
                        this.e.set(obj, Integer.valueOf(Integer.parseInt(obj2.toString())));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Integer.valueOf(Integer.parseInt(obj2.toString()))});
                } else if (this.d == Float.TYPE || this.d == Float.class) {
                    if (this.g == null) {
                        this.e.set(obj, Float.valueOf(Float.parseFloat(obj2.toString())));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Float.valueOf(Float.parseFloat(obj2.toString()))});
                } else if (this.d == Double.TYPE || this.d == Double.class) {
                    Log.d("", "afinaltest value=" + obj2 + "     tos=" + obj2.toString());
                    if (this.g == null) {
                        this.e.set(obj, Double.valueOf(Double.parseDouble(obj2.toString())));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Double.valueOf(Double.parseDouble(obj2.toString()))});
                } else if (this.d == Long.TYPE || this.d == Long.class) {
                    if (this.g == null) {
                        this.e.set(obj, Long.valueOf(Long.parseLong(obj2.toString())));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Long.valueOf(Long.parseLong(obj2.toString()))});
                } else if (this.d == Date.class || this.d == java.sql.Date.class) {
                    if (this.g == null) {
                        this.e.set(obj, d(obj2.toString()));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{d(obj2.toString())});
                } else if (this.d == Boolean.TYPE || this.d == Boolean.class) {
                    if (this.g == null) {
                        this.e.set(obj, Boolean.valueOf("1".equals(obj2.toString())));
                        return;
                    }
                    this.g.invoke(obj, new Object[]{Boolean.valueOf("1".equals(obj2.toString()))});
                } else if (this.g == null) {
                    this.e.set(obj, obj2);
                } else {
                    this.g.invoke(obj, new Object[]{obj2});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T a(Object obj) {
        if (obj != null) {
            try {
                if (this.f == null) {
                    return this.e.get(obj);
                }
                return this.f.invoke(obj, new Object[0]);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    private static Date d(String str) {
        if (str != null) {
            try {
                return h.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String b() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public Class<?> e() {
        return this.d;
    }

    public void b(Class<?> cls) {
        this.d = cls;
    }

    public Method f() {
        return this.f;
    }

    public void a(Method method) {
        this.f = method;
    }

    public Method g() {
        return this.g;
    }

    public void b(Method method) {
        this.g = method;
    }

    public Field h() {
        return this.e;
    }

    public void a(Field field) {
        this.e = field;
    }
}
