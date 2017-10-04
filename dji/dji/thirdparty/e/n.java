package dji.thirdparty.e;

import com.google.api.client.http.HttpMethods;
import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.t;
import dji.thirdparty.b.u;
import dji.thirdparty.b.w;
import dji.thirdparty.b.x;
import dji.thirdparty.e.b.b;
import dji.thirdparty.e.b.c;
import dji.thirdparty.e.b.d;
import dji.thirdparty.e.b.e;
import dji.thirdparty.e.b.f;
import dji.thirdparty.e.b.g;
import dji.thirdparty.e.b.h;
import dji.thirdparty.e.b.i;
import dji.thirdparty.e.b.j;
import dji.thirdparty.e.b.k;
import dji.thirdparty.e.b.l;
import dji.thirdparty.e.b.m;
import dji.thirdparty.e.b.o;
import dji.thirdparty.e.b.p;
import dji.thirdparty.e.b.q;
import dji.thirdparty.e.b.r;
import dji.thirdparty.e.b.s;
import dji.thirdparty.e.b.v;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class n<T> {
    static final String a = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern b = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    static final Pattern c = Pattern.compile(a);
    final dji.thirdparty.b.e.a d;
    final c<?> e;
    private final u f;
    private final e<ae, T> g;
    private final String h;
    private final String i;
    private final t j;
    private final w k;
    private final boolean l;
    private final boolean m;
    private final boolean n;
    private final i<?>[] o;

    static final class a<T> {
        final m a;
        final Method b;
        final Annotation[] c;
        final Annotation[][] d;
        final Type[] e;
        Type f;
        boolean g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        String m;
        boolean n;
        boolean o;
        boolean p;
        String q;
        t r;
        w s;
        Set<String> t;
        i<?>[] u;
        e<ae, T> v;
        c<?> w;

        public a(m mVar, Method method) {
            this.a = mVar;
            this.b = method;
            this.c = method.getAnnotations();
            this.e = method.getGenericParameterTypes();
            this.d = method.getParameterAnnotations();
        }

        public n a() {
            this.w = b();
            this.f = this.w.a();
            if (this.f == l.class || this.f == ad.class) {
                throw a("'" + o.a(this.f).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
            }
            int i;
            this.v = c();
            for (Annotation a : this.c) {
                a(a);
            }
            if (this.m == null) {
                throw a("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if (!this.n) {
                if (this.p) {
                    throw a("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                } else if (this.o) {
                    throw a("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            int length = this.d.length;
            this.u = new i[length];
            for (i = 0; i < length; i++) {
                Type type = this.e[i];
                if (o.d(type)) {
                    throw a(i, "Parameter type must not include a type variable or wildcard: %s", type);
                }
                Annotation[] annotationArr = this.d[i];
                if (annotationArr == null) {
                    throw a(i, "No Retrofit annotation found.", new Object[0]);
                }
                this.u[i] = a(i, type, annotationArr);
            }
            if (this.q == null && !this.l) {
                throw a("Missing either @%s URL or @Url parameter.", this.m);
            } else if (!this.o && !this.p && !this.n && this.i) {
                throw a("Non-body HTTP method cannot contain @Body.", new Object[0]);
            } else if (this.o && !this.g) {
                throw a("Form-encoded method must contain at least one @Field.", new Object[0]);
            } else if (!this.p || this.h) {
                return new n(this);
            } else {
                throw a("Multipart method must contain at least one @Part.", new Object[0]);
            }
        }

        private c<?> b() {
            Type genericReturnType = this.b.getGenericReturnType();
            if (o.d(genericReturnType)) {
                throw a("Method return type must not include a type variable or wildcard: %s", genericReturnType);
            } else if (genericReturnType == Void.TYPE) {
                throw a("Service methods cannot return void.", new Object[0]);
            } else {
                try {
                    return this.a.a(genericReturnType, this.b.getAnnotations());
                } catch (Throwable e) {
                    throw a(e, "Unable to create call adapter for %s", genericReturnType);
                }
            }
        }

        private void a(Annotation annotation) {
            if (annotation instanceof b) {
                a(HttpMethods.DELETE, ((b) annotation).a(), false);
            } else if (annotation instanceof f) {
                a(HttpMethods.GET, ((f) annotation).a(), false);
            } else if (annotation instanceof g) {
                a(HttpMethods.HEAD, ((g) annotation).a(), false);
                if (!Void.class.equals(this.f)) {
                    throw a("HEAD method must use Void as response type.", new Object[0]);
                }
            } else if (annotation instanceof m) {
                a("PATCH", ((m) annotation).a(), true);
            } else if (annotation instanceof dji.thirdparty.e.b.n) {
                a(HttpMethods.POST, ((dji.thirdparty.e.b.n) annotation).a(), true);
            } else if (annotation instanceof o) {
                a(HttpMethods.PUT, ((o) annotation).a(), true);
            } else if (annotation instanceof l) {
                a(HttpMethods.OPTIONS, ((l) annotation).a(), false);
            } else if (annotation instanceof h) {
                h hVar = (h) annotation;
                a(hVar.a(), hVar.b(), hVar.c());
            } else if (annotation instanceof j) {
                String[] a = ((j) annotation).a();
                if (a.length == 0) {
                    throw a("@Headers annotation is empty.", new Object[0]);
                }
                this.r = a(a);
            } else if (annotation instanceof k) {
                if (this.o) {
                    throw a("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.p = true;
            } else if (!(annotation instanceof e)) {
            } else {
                if (this.p) {
                    throw a("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.o = true;
            }
        }

        private void a(String str, String str2, boolean z) {
            if (this.m != null) {
                throw a("Only one HTTP method is allowed. Found: %s and %s.", this.m, str);
            }
            this.m = str;
            this.n = z;
            if (!str2.isEmpty()) {
                int indexOf = str2.indexOf(63);
                if (indexOf != -1 && indexOf < str2.length() - 1) {
                    if (n.b.matcher(str2.substring(indexOf + 1)).find()) {
                        throw a("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", str2.substring(indexOf + 1));
                    }
                }
                this.q = str2;
                this.t = n.a(str2);
            }
        }

        private t a(String[] strArr) {
            dji.thirdparty.b.t.a aVar = new dji.thirdparty.b.t.a();
            for (String str : strArr) {
                String str2;
                int indexOf = str2.indexOf(58);
                if (indexOf == -1 || indexOf == 0 || indexOf == str2.length() - 1) {
                    throw a("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str2);
                }
                String substring = str2.substring(0, indexOf);
                str2 = str2.substring(indexOf + 1).trim();
                if (AsyncHttpClient.HEADER_CONTENT_TYPE.equalsIgnoreCase(substring)) {
                    this.s = w.a(str2);
                } else {
                    aVar.a(substring, str2);
                }
            }
            return aVar.a();
        }

        private i<?> a(int i, Type type, Annotation[] annotationArr) {
            i<?> iVar = null;
            for (Annotation a : annotationArr) {
                i<?> a2 = a(i, type, annotationArr, a);
                if (a2 != null) {
                    if (iVar != null) {
                        throw a(i, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                    }
                    iVar = a2;
                }
            }
            if (iVar != null) {
                return iVar;
            }
            throw a(i, "No Retrofit annotation found.", new Object[0]);
        }

        private i<?> a(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof v) {
                if (this.l) {
                    throw a(i, "Multiple @Url method annotations found.", new Object[0]);
                } else if (this.j) {
                    throw a(i, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.k) {
                    throw a(i, "A @Url parameter must not come after a @Query", new Object[0]);
                } else if (this.q != null) {
                    throw a(i, "@Url cannot be used with @%s URL", this.m);
                } else {
                    this.l = true;
                    if (type == u.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                        return new k();
                    }
                    throw a(i, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                }
            } else if (annotation instanceof r) {
                if (this.k) {
                    throw a(i, "A @Path parameter must not come after a @Query.", new Object[0]);
                } else if (this.l) {
                    throw a(i, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.q == null) {
                    throw a(i, "@Path can only be used with relative url on @%s", this.m);
                } else {
                    this.j = true;
                    r rVar = (r) annotation;
                    r1 = rVar.a();
                    a(i, r1);
                    return new g(r1, this.a.c(type, annotationArr), rVar.b());
                }
            } else if (annotation instanceof s) {
                s sVar = (s) annotation;
                r1 = sVar.a();
                r2 = sVar.b();
                r0 = o.a(type);
                this.k = true;
                if (Iterable.class.isAssignableFrom(r0)) {
                    if (type instanceof ParameterizedType) {
                        return new h(r1, this.a.c(o.a(0, (ParameterizedType) type), annotationArr), r2).a();
                    }
                    throw a(i, r0.getSimpleName() + " must include generic type (e.g., " + r0.getSimpleName() + "<String>)", new Object[0]);
                } else if (!r0.isArray()) {
                    return new h(r1, this.a.c(type, annotationArr), r2);
                } else {
                    return new h(r1, this.a.c(n.a(r0.getComponentType()), annotationArr), r2).b();
                }
            } else if (annotation instanceof dji.thirdparty.e.b.t) {
                r0 = o.a(type);
                if (Map.class.isAssignableFrom(r0)) {
                    r0 = o.b(type, r0, Map.class);
                    if (r0 instanceof ParameterizedType) {
                        r0 = (ParameterizedType) r0;
                        r1 = o.a(0, r0);
                        if (String.class != r1) {
                            throw a(i, "@QueryMap keys must be of type String: " + r1, new Object[0]);
                        }
                        return new i(this.a.c(o.a(1, r0), annotationArr), ((dji.thirdparty.e.b.t) annotation).a());
                    }
                    throw a(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw a(i, "@QueryMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof i) {
                r1 = ((i) annotation).a();
                r0 = o.a(type);
                if (Iterable.class.isAssignableFrom(r0)) {
                    if (type instanceof ParameterizedType) {
                        return new d(r1, this.a.c(o.a(0, (ParameterizedType) type), annotationArr)).a();
                    }
                    throw a(i, r0.getSimpleName() + " must include generic type (e.g., " + r0.getSimpleName() + "<String>)", new Object[0]);
                } else if (!r0.isArray()) {
                    return new d(r1, this.a.c(type, annotationArr));
                } else {
                    return new d(r1, this.a.c(n.a(r0.getComponentType()), annotationArr)).b();
                }
            } else if (annotation instanceof c) {
                if (this.o) {
                    c cVar = (c) annotation;
                    r1 = cVar.a();
                    r2 = cVar.b();
                    this.g = true;
                    r0 = o.a(type);
                    if (Iterable.class.isAssignableFrom(r0)) {
                        if (type instanceof ParameterizedType) {
                            return new b(r1, this.a.c(o.a(0, (ParameterizedType) type), annotationArr), r2).a();
                        }
                        throw a(i, r0.getSimpleName() + " must include generic type (e.g., " + r0.getSimpleName() + "<String>)", new Object[0]);
                    } else if (!r0.isArray()) {
                        return new b(r1, this.a.c(type, annotationArr), r2);
                    } else {
                        return new b(r1, this.a.c(n.a(r0.getComponentType()), annotationArr), r2).b();
                    }
                }
                throw a(i, "@Field parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof d) {
                if (this.o) {
                    r0 = o.a(type);
                    if (Map.class.isAssignableFrom(r0)) {
                        r0 = o.b(type, r0, Map.class);
                        if (r0 instanceof ParameterizedType) {
                            r0 = (ParameterizedType) r0;
                            r1 = o.a(0, r0);
                            if (String.class != r1) {
                                throw a(i, "@FieldMap keys must be of type String: " + r1, new Object[0]);
                            }
                            r1 = this.a.c(o.a(1, r0), annotationArr);
                            this.g = true;
                            return new c(r1, ((d) annotation).a());
                        }
                        throw a(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw a(i, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                throw a(i, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof p) {
                if (this.p) {
                    p pVar = (p) annotation;
                    this.h = true;
                    String a = pVar.a();
                    r1 = o.a(type);
                    if (!a.isEmpty()) {
                        t a2 = t.a(AsyncHttpClient.HEADER_CONTENT_DISPOSITION, "form-data; name=\"" + a + "\"", "Content-Transfer-Encoding", pVar.b());
                        if (Iterable.class.isAssignableFrom(r1)) {
                            if (type instanceof ParameterizedType) {
                                r0 = o.a(0, (ParameterizedType) type);
                                if (!x.b.class.isAssignableFrom(o.a(r0))) {
                                    return new e(a2, this.a.a(r0, annotationArr, this.c)).a();
                                }
                                throw a(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                            }
                            throw a(i, r1.getSimpleName() + " must include generic type (e.g., " + r1.getSimpleName() + "<String>)", new Object[0]);
                        } else if (r1.isArray()) {
                            r0 = n.a(r1.getComponentType());
                            if (!x.b.class.isAssignableFrom(r0)) {
                                return new e(a2, this.a.a(r0, annotationArr, this.c)).b();
                            }
                            throw a(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        } else if (!x.b.class.isAssignableFrom(r1)) {
                            return new e(a2, this.a.a(type, annotationArr, this.c));
                        } else {
                            throw a(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                    } else if (Iterable.class.isAssignableFrom(r1)) {
                        if (!(type instanceof ParameterizedType)) {
                            throw a(i, r1.getSimpleName() + " must include generic type (e.g., " + r1.getSimpleName() + "<String>)", new Object[0]);
                        } else if (x.b.class.isAssignableFrom(o.a(o.a(0, (ParameterizedType) type)))) {
                            return j.a.a();
                        } else {
                            throw a(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                    } else if (r1.isArray()) {
                        if (x.b.class.isAssignableFrom(r1.getComponentType())) {
                            return j.a.b();
                        }
                        throw a(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    } else if (x.b.class.isAssignableFrom(r1)) {
                        return j.a;
                    } else {
                        throw a(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                }
                throw a(i, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            } else if (annotation instanceof q) {
                if (this.p) {
                    this.h = true;
                    r0 = o.a(type);
                    if (Map.class.isAssignableFrom(r0)) {
                        r0 = o.b(type, r0, Map.class);
                        if (r0 instanceof ParameterizedType) {
                            r0 = (ParameterizedType) r0;
                            r1 = o.a(0, r0);
                            if (String.class != r1) {
                                throw a(i, "@PartMap keys must be of type String: " + r1, new Object[0]);
                            }
                            r0 = o.a(1, r0);
                            if (!x.b.class.isAssignableFrom(o.a(r0))) {
                                return new f(this.a.a(r0, annotationArr, this.c), ((q) annotation).a());
                            }
                            throw a(i, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                        }
                        throw a(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw a(i, "@PartMap parameter type must be Map.", new Object[0]);
                }
                throw a(i, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
            } else if (!(annotation instanceof dji.thirdparty.e.b.a)) {
                return null;
            } else {
                if (this.o || this.p) {
                    throw a(i, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                } else if (this.i) {
                    throw a(i, "Multiple @Body method annotations found.", new Object[0]);
                } else {
                    try {
                        r1 = this.a.a(type, annotationArr, this.c);
                        this.i = true;
                        return new a(r1);
                    } catch (Throwable e) {
                        throw a(e, i, "Unable to create @Body converter for %s", type);
                    }
                }
            }
        }

        private void a(int i, String str) {
            if (!n.c.matcher(str).matches()) {
                throw a(i, "@Path parameter name must match %s. Found: %s", n.b.pattern(), str);
            } else if (!this.t.contains(str)) {
                throw a(i, "URL \"%s\" does not contain \"{%s}\".", this.q, str);
            }
        }

        private e<ae, T> c() {
            try {
                return this.a.b(this.f, this.b.getAnnotations());
            } catch (Throwable e) {
                throw a(e, "Unable to create converter for %s", this.f);
            }
        }

        private RuntimeException a(String str, Object... objArr) {
            return a(null, str, objArr);
        }

        private RuntimeException a(Throwable th, String str, Object... objArr) {
            return new IllegalArgumentException(String.format(str, objArr) + "\n    for method " + this.b.getDeclaringClass().getSimpleName() + "." + this.b.getName(), th);
        }

        private RuntimeException a(Throwable th, int i, String str, Object... objArr) {
            return a(th, str + " (parameter #" + (i + 1) + ")", objArr);
        }

        private RuntimeException a(int i, String str, Object... objArr) {
            return a(str + " (parameter #" + (i + 1) + ")", objArr);
        }
    }

    n(a<T> aVar) {
        this.d = aVar.a.a();
        this.e = aVar.w;
        this.f = aVar.a.b();
        this.g = aVar.v;
        this.h = aVar.m;
        this.i = aVar.q;
        this.j = aVar.r;
        this.k = aVar.s;
        this.l = aVar.n;
        this.m = aVar.o;
        this.n = aVar.p;
        this.o = aVar.u;
    }

    ab a(Object... objArr) throws IOException {
        int length;
        int i = 0;
        k kVar = new k(this.h, this.f, this.i, this.j, this.k, this.l, this.m, this.n);
        i[] iVarArr = this.o;
        if (objArr != null) {
            length = objArr.length;
        } else {
            length = 0;
        }
        if (length != iVarArr.length) {
            throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + iVarArr.length + ")");
        }
        while (i < length) {
            iVarArr[i].a(kVar, objArr[i]);
            i++;
        }
        return kVar.a();
    }

    T a(ae aeVar) throws IOException {
        return this.g.a(aeVar);
    }

    static Set<String> a(String str) {
        Matcher matcher = b.matcher(str);
        Set<String> linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    static Class<?> a(Class<?> cls) {
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        if (Short.TYPE == cls) {
            return Short.class;
        }
        return cls;
    }
}
