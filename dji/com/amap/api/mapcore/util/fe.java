package com.amap.api.mapcore.util;

import android.content.Context;
import java.lang.reflect.Constructor;

public class fe {
    public static <T> T a(Context context, dv dvVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws dk {
        fg a;
        T t = null;
        try {
            a = ex.a().a(context, dvVar);
        } catch (Throwable th) {
            eb.a(th, "InstanceFactory", "getInstance");
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
                eb.a(th2, "InstanceFactory", "getInstance()");
            } catch (Throwable th22) {
                eb.a(th22, "InstanceFactory", "getInstance()");
            } catch (Throwable th222) {
                eb.a(th222, "InstanceFactory", "getInstance()");
            } catch (Throwable th2222) {
                eb.a(th2222, "InstanceFactory", "getInstance()");
            } catch (Throwable th22222) {
                eb.a(th22222, "InstanceFactory", "getInstance()");
            } catch (Throwable th222222) {
                eb.a(th222222, "InstanceFactory", "getInstance()");
            } catch (Throwable th2222222) {
                eb.a(th2222222, "InstanceFactory", "getInstance()");
            }
        }
        if (cls != null) {
            try {
                Constructor declaredConstructor2 = cls.getDeclaredConstructor(clsArr);
                declaredConstructor2.setAccessible(true);
                t = declaredConstructor2.newInstance(objArr);
            } catch (Throwable e) {
                eb.a(e, "InstanceFactory", "getInstance()");
                throw new dk("获取对象错误");
            } catch (Throwable e2) {
                eb.a(e2, "InstanceFactory", "getInstance()");
                throw new dk("获取对象错误");
            } catch (Throwable e22) {
                eb.a(e22, "InstanceFactory", "getInstance()");
                throw new dk("获取对象错误");
            } catch (Throwable e222) {
                eb.a(e222, "InstanceFactory", "getInstance()");
                throw new dk("获取对象错误");
            } catch (Throwable e2222) {
                eb.a(e2222, "InstanceFactory", "getInstance()");
                throw new dk("获取对象错误");
            } catch (Throwable e22222) {
                eb.a(e22222, "InstanceFactory", "getInstance()");
                throw new dk("获取对象错误");
            }
        }
        return t;
    }
}
