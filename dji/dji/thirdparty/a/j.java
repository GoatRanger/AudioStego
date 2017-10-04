package dji.thirdparty.a;

import java.lang.reflect.Method;

final class j {
    final Method a;
    final m b;
    final Class<?> c;
    String d;

    j(Method method, m mVar, Class<?> cls) {
        this.a = method;
        this.b = mVar;
        this.c = cls;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        a();
        j jVar = (j) obj;
        jVar.a();
        return this.d.equals(jVar.d);
    }

    private synchronized void a() {
        if (this.d == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.a.getDeclaringClass().getName());
            stringBuilder.append('#').append(this.a.getName());
            stringBuilder.append('(').append(this.c.getName());
            this.d = stringBuilder.toString();
        }
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
