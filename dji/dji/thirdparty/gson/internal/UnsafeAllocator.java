package dji.thirdparty.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator {

    class AnonymousClass1 extends UnsafeAllocator {
        private final /* synthetic */ Method val$allocateInstance;
        private final /* synthetic */ Object val$unsafe;

        AnonymousClass1(Method method, Object obj) {
            this.val$allocateInstance = method;
            this.val$unsafe = obj;
        }

        public <T> T newInstance(Class<T> cls) throws Exception {
            return this.val$allocateInstance.invoke(this.val$unsafe, new Object[]{cls});
        }
    }

    class AnonymousClass2 extends UnsafeAllocator {
        private final /* synthetic */ Method val$newInstance;

        AnonymousClass2(Method method) {
            this.val$newInstance = method;
        }

        public <T> T newInstance(Class<T> cls) throws Exception {
            return this.val$newInstance.invoke(null, new Object[]{cls, Object.class});
        }
    }

    class AnonymousClass3 extends UnsafeAllocator {
        private final /* synthetic */ int val$constructorId;
        private final /* synthetic */ Method val$newInstance;

        AnonymousClass3(Method method, int i) {
            this.val$newInstance = method;
            this.val$constructorId = i;
        }

        public <T> T newInstance(Class<T> cls) throws Exception {
            return this.val$newInstance.invoke(null, new Object[]{cls, Integer.valueOf(this.val$constructorId)});
        }
    }

    public abstract <T> T newInstance(Class<T> cls) throws Exception;

    public static UnsafeAllocator create() {
        try {
            Class cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new AnonymousClass1(cls.getMethod("allocateInstance", new Class[]{Class.class}), declaredField.get(null));
        } catch (Exception e) {
            try {
                Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});
                declaredMethod.setAccessible(true);
                return new AnonymousClass2(declaredMethod);
            } catch (Exception e2) {
                try {
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                    declaredMethod2.setAccessible(true);
                    int intValue = ((Integer) declaredMethod2.invoke(null, new Object[]{Object.class})).intValue();
                    Method declaredMethod3 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
                    declaredMethod3.setAccessible(true);
                    return new AnonymousClass3(declaredMethod3, intValue);
                } catch (Exception e3) {
                    return new UnsafeAllocator() {
                        public <T> T newInstance(Class<T> cls) {
                            throw new UnsupportedOperationException("Cannot allocate " + cls);
                        }
                    };
                }
            }
        }
    }
}
