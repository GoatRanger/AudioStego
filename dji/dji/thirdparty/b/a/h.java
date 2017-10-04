package dji.thirdparty.b.a;

import android.util.Log;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import dji.pilot2.nativeexplore.explorepost.ExplorePostSelectActivity;
import dji.thirdparty.b.a.d.e;
import dji.thirdparty.b.a.d.f;
import dji.thirdparty.b.z;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class h {
    private static final h a = c();

    private static class a extends h {
        private static final int a = 4000;
        private final Class<?> b;
        private final g<Socket> c;
        private final g<Socket> d;
        private final g<Socket> e;
        private final g<Socket> f;

        public a(Class<?> cls, g<Socket> gVar, g<Socket> gVar2, g<Socket> gVar3, g<Socket> gVar4) {
            this.b = cls;
            this.c = gVar;
            this.d = gVar2;
            this.e = gVar3;
            this.f = gVar4;
        }

        public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (AssertionError e) {
                if (j.a(e)) {
                    throw new IOException(e);
                }
                throw e;
            } catch (Throwable e2) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e2);
                throw iOException;
            }
        }

        public X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
            Object a;
            Object a2 = h.a((Object) sSLSocketFactory, this.b, "sslParameters");
            if (a2 == null) {
                try {
                    a = h.a((Object) sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
                } catch (ClassNotFoundException e) {
                    return super.a(sSLSocketFactory);
                }
            }
            a = a2;
            X509TrustManager x509TrustManager = (X509TrustManager) h.a(a, X509TrustManager.class, "x509TrustManager");
            return x509TrustManager != null ? x509TrustManager : (X509TrustManager) h.a(a, X509TrustManager.class, "trustManager");
        }

        public f a(X509TrustManager x509TrustManager) {
            f a = dji.thirdparty.b.a.d.a.a(x509TrustManager);
            return a != null ? a : super.a(x509TrustManager);
        }

        public void a(SSLSocket sSLSocket, String str, List<z> list) {
            if (str != null) {
                this.c.b(sSLSocket, Boolean.valueOf(true));
                this.d.b(sSLSocket, str);
            }
            if (this.f != null && this.f.a((Object) sSLSocket)) {
                this.f.d(sSLSocket, h.a((List) list));
            }
        }

        public String b(SSLSocket sSLSocket) {
            if (this.e == null || !this.e.a((Object) sSLSocket)) {
                return null;
            }
            byte[] bArr = (byte[]) this.e.d(sSLSocket, new Object[0]);
            return bArr != null ? new String(bArr, j.c) : null;
        }

        public void b(String str) {
            int i = 0;
            int length = str.length();
            while (i < length) {
                int min;
                int indexOf = str.indexOf(10, i);
                if (indexOf == -1) {
                    indexOf = length;
                }
                while (true) {
                    min = Math.min(indexOf, i + a);
                    Log.d("OkHttp", str.substring(i, min));
                    if (min >= indexOf) {
                        break;
                    }
                    i = min;
                }
                i = min + 1;
            }
        }
    }

    private static class b extends h {
        private final Method a;
        private final Method b;
        private final Method c;
        private final Class<?> d;
        private final Class<?> e;

        public b(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
            this.a = method;
            this.b = method2;
            this.c = method3;
            this.d = cls;
            this.e = cls2;
        }

        public void a(SSLSocket sSLSocket, String str, List<z> list) {
            Object newProxyInstance;
            List arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                z zVar = (z) list.get(i);
                if (zVar != z.HTTP_1_0) {
                    arrayList.add(zVar.toString());
                }
            }
            try {
                newProxyInstance = Proxy.newProxyInstance(h.class.getClassLoader(), new Class[]{this.d, this.e}, new c(arrayList));
                this.a.invoke(null, new Object[]{sSLSocket, newProxyInstance});
            } catch (InvocationTargetException e) {
                newProxyInstance = e;
                throw new AssertionError(newProxyInstance);
            } catch (IllegalAccessException e2) {
                newProxyInstance = e2;
                throw new AssertionError(newProxyInstance);
            }
        }

        public void a(SSLSocket sSLSocket) {
            try {
                this.c.invoke(null, new Object[]{sSLSocket});
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new AssertionError();
            }
        }

        public String b(SSLSocket sSLSocket) {
            try {
                c cVar = (c) Proxy.getInvocationHandler(this.b.invoke(null, new Object[]{sSLSocket}));
                if (cVar.b || cVar.c != null) {
                    return cVar.b ? null : cVar.c;
                }
                d.a.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                return null;
            } catch (InvocationTargetException e) {
                throw new AssertionError();
            } catch (IllegalAccessException e2) {
                throw new AssertionError();
            }
        }
    }

    private static class c implements InvocationHandler {
        private final List<String> a;
        private boolean b;
        private String c;

        public c(List<String> list) {
            this.a = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class returnType = method.getReturnType();
            if (objArr == null) {
                objArr = j.b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.valueOf(true);
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.b = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.a;
            } else {
                if ((name.equals("selectProtocol") || name.equals(ExplorePostSelectActivity.a)) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                    List list = (List) objArr[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.a.contains(list.get(i))) {
                            name = (String) list.get(i);
                            this.c = name;
                            return name;
                        }
                    }
                    name = (String) this.a.get(0);
                    this.c = name;
                    return name;
                } else if ((!name.equals("protocolSelected") && !name.equals(DJIPhotoPreveiwActivity.D)) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                } else {
                    this.c = (String) objArr[0];
                    return null;
                }
            }
        }
    }

    public static h a() {
        return a;
    }

    public String b() {
        return "OkHttp";
    }

    public void a(String str) {
        System.out.println(str);
    }

    public X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
        try {
            Object a = a((Object) sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
            if (a == null) {
                return null;
            }
            return (X509TrustManager) a(a, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public f a(X509TrustManager x509TrustManager) {
        return new e(x509TrustManager.getAcceptedIssuers());
    }

    public void a(SSLSocket sSLSocket, String str, List<z> list) {
    }

    public void a(SSLSocket sSLSocket) {
    }

    public String b(SSLSocket sSLSocket) {
        return null;
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void b(String str) {
        System.out.println(str);
    }

    private static h c() {
        Class cls;
        g gVar;
        g gVar2;
        g gVar3;
        try {
            cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException e) {
            cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
        }
        try {
            g gVar4 = new g(null, "setUseSessionTickets", Boolean.TYPE);
            g gVar5 = new g(null, "setHostname", String.class);
            try {
                Class.forName("android.net.Network");
                gVar = new g(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                try {
                    gVar2 = new g(null, "setAlpnProtocols", byte[].class);
                    gVar3 = gVar;
                } catch (ClassNotFoundException e2) {
                    gVar2 = null;
                    gVar3 = gVar;
                    return new a(cls, gVar4, gVar5, gVar3, gVar2);
                }
            } catch (ClassNotFoundException e3) {
                gVar = null;
                gVar2 = null;
                gVar3 = gVar;
                return new a(cls, gVar4, gVar5, gVar3, gVar2);
            }
            return new a(cls, gVar4, gVar5, gVar3, gVar2);
        } catch (ClassNotFoundException e4) {
            try {
                String str = "org.eclipse.jetty.alpn.ALPN";
                Class cls2 = Class.forName(str);
                cls = Class.forName(str + "$Provider");
                Class cls3 = Class.forName(str + "$ClientProvider");
                Class cls4 = Class.forName(str + "$ServerProvider");
                return new b(cls2.getMethod("put", new Class[]{SSLSocket.class, cls}), cls2.getMethod("get", new Class[]{SSLSocket.class}), cls2.getMethod("remove", new Class[]{SSLSocket.class}), cls3, cls4);
            } catch (ClassNotFoundException e5) {
                return new h();
            } catch (NoSuchMethodException e6) {
                return new h();
            }
        }
    }

    static byte[] a(List<z> list) {
        dji.thirdparty.c.c cVar = new dji.thirdparty.c.c();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            z zVar = (z) list.get(i);
            if (zVar != z.HTTP_1_0) {
                cVar.b(zVar.toString().length());
                cVar.a(zVar.toString());
            }
        }
        return cVar.x();
    }

    static <T> T a(Object obj, Class<T> cls, String str) {
        Class cls2 = obj.getClass();
        while (cls2 != Object.class) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 == null || !cls.isInstance(obj2)) {
                    return null;
                }
                return cls.cast(obj2);
            } catch (NoSuchFieldException e) {
                cls2 = cls2.getSuperclass();
            } catch (IllegalAccessException e2) {
                throw new AssertionError();
            }
        }
        if (!str.equals("delegate")) {
            Object a = a(obj, Object.class, "delegate");
            if (a != null) {
                return a(a, (Class) cls, str);
            }
        }
        return null;
    }
}
