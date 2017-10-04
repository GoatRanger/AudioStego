package com.e;

import android.content.Context;
import android.text.TextUtils;
import java.lang.reflect.Constructor;

public class z {
    public static <T> T a(Context context, dc dcVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws ct {
        ac a;
        T t = null;
        try {
            a = s.a().a(context, dcVar);
        } catch (Throwable th) {
            dg.a(th, "InstanceFactory", "getInstance");
            a = null;
        }
        if (a != null) {
            try {
                if (a.a() && a.d) {
                    Class loadClass = a.loadClass(str);
                    if (loadClass != null) {
                        Constructor declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                        declaredConstructor.setAccessible(true);
                        t = declaredConstructor.newInstance(objArr);
                        return t;
                    }
                }
            } catch (Throwable th2) {
                dg.a(th2, "InstanceFactory", "getInstance()");
            } catch (Throwable th22) {
                dg.a(th22, "InstanceFactory", "getInstance()");
            } catch (Throwable th222) {
                dg.a(th222, "InstanceFactory", "getInstance()");
            } catch (Throwable th2222) {
                dg.a(th2222, "InstanceFactory", "getInstance()");
            } catch (Throwable th22222) {
                dg.a(th22222, "InstanceFactory", "getInstance()");
            } catch (Throwable th222222) {
                dg.a(th222222, "InstanceFactory", "getInstance()");
            } catch (Throwable th2222222) {
                dg.a(th2222222, "InstanceFactory", "getInstance()");
            }
        }
        if (cls != null) {
            try {
                Constructor declaredConstructor2 = cls.getDeclaredConstructor(clsArr);
                declaredConstructor2.setAccessible(true);
                t = declaredConstructor2.newInstance(objArr);
            } catch (Throwable e) {
                dg.a(e, "InstanceFactory", "getInstance()");
                throw new ct("获取对象错误");
            } catch (Throwable e2) {
                dg.a(e2, "InstanceFactory", "getInstance()");
                throw new ct("获取对象错误");
            } catch (Throwable e22) {
                dg.a(e22, "InstanceFactory", "getInstance()");
                throw new ct("获取对象错误");
            } catch (Throwable e222) {
                dg.a(e222, "InstanceFactory", "getInstance()");
                throw new ct("获取对象错误");
            } catch (Throwable e2222) {
                dg.a(e2222, "InstanceFactory", "getInstance()");
                throw new ct("获取对象错误");
            } catch (Throwable e22222) {
                dg.a(e22222, "InstanceFactory", "getInstance()");
                throw new ct("获取对象错误");
            }
        }
        return t;
    }

    public static void a(Context context, u uVar, dc dcVar) {
        if (uVar != null && !TextUtils.isEmpty(uVar.a()) && !TextUtils.isEmpty(uVar.b()) && !TextUtils.isEmpty(uVar.c())) {
            new t(context, uVar, dcVar).a();
        }
    }
}
