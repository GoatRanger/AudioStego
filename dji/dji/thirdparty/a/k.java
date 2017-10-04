package dji.thirdparty.a;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class k {
    private static final int a = 1032;
    private static final Map<String, List<j>> b = new HashMap();
    private static final Map<Class<?>, Class<?>> c = new ConcurrentHashMap();

    k() {
    }

    List<j> a(Class<?> cls, String str) {
        String str2 = cls.getName() + '.' + str;
        synchronized (b) {
            List<j> list = (List) b.get(str2);
        }
        if (list != null) {
            return list;
        }
        List<j> arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name = cls2.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            for (Method method : cls2.getMethods()) {
                String name2 = method.getName();
                if (name2.startsWith(str)) {
                    int modifiers = method.getModifiers();
                    if ((modifiers & 1) != 0 && (modifiers & a) == 0) {
                        Class[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            m mVar;
                            name = name2.substring(str.length());
                            if (name.length() == 0) {
                                mVar = m.PostThread;
                            } else if (name.equals("MainThread")) {
                                mVar = m.MainThread;
                            } else if (name.equals("BackgroundThread")) {
                                mVar = m.BackgroundThread;
                            } else if (name.equals("Async")) {
                                mVar = m.Async;
                            } else if (!c.containsKey(cls2)) {
                                throw new d("Illegal onEvent method, check for typos: " + method);
                            }
                            Class cls3 = parameterTypes[0];
                            stringBuilder.setLength(0);
                            stringBuilder.append(name2);
                            stringBuilder.append('>').append(cls3.getName());
                            if (hashSet.add(stringBuilder.toString())) {
                                arrayList.add(new j(method, mVar, cls3));
                            }
                        } else {
                            continue;
                        }
                    } else if (!c.containsKey(cls2)) {
                        Log.d(c.b, "Skipping method (not public, static or abstract): " + cls2 + "." + name2);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            throw new d("Subscriber " + cls + " has no public methods called " + str);
        }
        synchronized (b) {
            b.put(str2, arrayList);
        }
        return arrayList;
    }

    static void a() {
        synchronized (b) {
            b.clear();
        }
    }

    static void a(Class<?> cls) {
        if (b.isEmpty()) {
            c.put(cls, cls);
            return;
        }
        throw new IllegalStateException("This method must be called before registering anything");
    }

    public static void b() {
        c.clear();
    }
}
