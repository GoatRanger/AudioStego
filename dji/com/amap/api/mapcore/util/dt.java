package com.amap.api.mapcore.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Locale;

public class dt {
    public static Proxy a(Context context) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return a(context, new URI("http://restapi.amap.com"));
            }
            return b(context);
        } catch (Throwable th) {
            eb.a(th, "ProxyUtil", "getProxy");
            return null;
        }
    }

    private static Proxy b(Context context) {
        Cursor query;
        String string;
        String a;
        Cursor cursor;
        int i;
        Throwable th;
        String toLowerCase;
        Object obj;
        Cursor cursor2;
        Object obj2 = null;
        int i2 = -1;
        if (c(context)) {
            try {
                query = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            string = query.getString(query.getColumnIndex("apn"));
                            if (string != null) {
                                string = string.toLowerCase(Locale.US);
                            }
                            if (string != null && string.contains("ctwap")) {
                                a = a();
                                int b = b();
                                try {
                                    Object obj3;
                                    if (TextUtils.isEmpty(a) || a.equals("null")) {
                                        obj3 = null;
                                        a = null;
                                    } else {
                                        obj3 = 1;
                                    }
                                    if (obj3 == null) {
                                        try {
                                            a = "10.0.0.200";
                                        } catch (Throwable e) {
                                            Throwable th2 = e;
                                            cursor = query;
                                            i = b;
                                            th = th2;
                                            eb.a(th, "ProxyUtil", "getHostProxy");
                                            string = dq.o(context);
                                            if (string != null) {
                                                toLowerCase = string.toLowerCase(Locale.US);
                                                string = a();
                                                i = b();
                                                if (toLowerCase.indexOf("ctwap") != -1) {
                                                    if (!(TextUtils.isEmpty(string) || string.equals("null"))) {
                                                        obj2 = 1;
                                                        a = string;
                                                    }
                                                    if (obj2 == null) {
                                                        a = "10.0.0.200";
                                                    }
                                                    if (i == -1) {
                                                        i2 = 80;
                                                        if (cursor != null) {
                                                            cursor.close();
                                                        }
                                                        if (a(a, i2)) {
                                                            return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                        }
                                                        return null;
                                                    }
                                                } else if (toLowerCase.indexOf("wap") != -1) {
                                                    if (!TextUtils.isEmpty(string) || string.equals("null")) {
                                                        obj = null;
                                                        string = a;
                                                    } else {
                                                        obj = 1;
                                                    }
                                                    if (obj == null) {
                                                        string = "10.0.0.200";
                                                    }
                                                    i2 = 80;
                                                    a = string;
                                                    if (cursor != null) {
                                                        cursor.close();
                                                    }
                                                    if (a(a, i2)) {
                                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                    }
                                                    return null;
                                                }
                                            }
                                            i2 = i;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            if (a(a, i2)) {
                                                return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                            }
                                            return null;
                                        } catch (Throwable e2) {
                                            i2 = b;
                                            th = e2;
                                            eb.a(th, "ProxyUtil", "getHostProxy1");
                                            th.printStackTrace();
                                            if (query != null) {
                                                query.close();
                                            }
                                            if (a(a, i2)) {
                                                return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                            }
                                            return null;
                                        }
                                    }
                                    if (b == -1) {
                                        b = 80;
                                    }
                                    i2 = b;
                                    if (query != null) {
                                        query.close();
                                    }
                                } catch (Throwable e22) {
                                    a = null;
                                    cursor2 = query;
                                    i = b;
                                    th = e22;
                                    cursor = cursor2;
                                    eb.a(th, "ProxyUtil", "getHostProxy");
                                    string = dq.o(context);
                                    if (string != null) {
                                        toLowerCase = string.toLowerCase(Locale.US);
                                        string = a();
                                        i = b();
                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                            obj2 = 1;
                                            a = string;
                                            if (obj2 == null) {
                                                a = "10.0.0.200";
                                            }
                                            if (i == -1) {
                                                i2 = 80;
                                                if (cursor != null) {
                                                    cursor.close();
                                                }
                                                if (a(a, i2)) {
                                                    return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                }
                                                return null;
                                            }
                                        } else if (toLowerCase.indexOf("wap") != -1) {
                                            if (TextUtils.isEmpty(string)) {
                                            }
                                            obj = null;
                                            string = a;
                                            if (obj == null) {
                                                string = "10.0.0.200";
                                            }
                                            i2 = 80;
                                            a = string;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            if (a(a, i2)) {
                                                return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                            }
                                            return null;
                                        }
                                    }
                                    i2 = i;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (a(a, i2)) {
                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                    }
                                    return null;
                                } catch (Throwable e222) {
                                    i2 = b;
                                    a = null;
                                    th = e222;
                                    eb.a(th, "ProxyUtil", "getHostProxy1");
                                    th.printStackTrace();
                                    if (query != null) {
                                        query.close();
                                    }
                                    if (a(a, i2)) {
                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                    }
                                    return null;
                                }
                                if (a(a, i2)) {
                                    return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                }
                            } else if (string != null) {
                                if (string.contains("wap")) {
                                    a = a();
                                    int b2 = b();
                                    try {
                                        Object obj4;
                                        if (TextUtils.isEmpty(a) || a.equals("null")) {
                                            obj4 = null;
                                            a = null;
                                        } else {
                                            obj4 = 1;
                                        }
                                        if (obj4 == null) {
                                            try {
                                                a = "10.0.0.172";
                                            } catch (SecurityException e3) {
                                                th = e3;
                                                cursor2 = query;
                                                i = b2;
                                                cursor = cursor2;
                                                try {
                                                    eb.a(th, "ProxyUtil", "getHostProxy");
                                                    string = dq.o(context);
                                                    if (string != null) {
                                                        toLowerCase = string.toLowerCase(Locale.US);
                                                        string = a();
                                                        i = b();
                                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                                            obj2 = 1;
                                                            a = string;
                                                            if (obj2 == null) {
                                                                a = "10.0.0.200";
                                                            }
                                                            if (i == -1) {
                                                                i2 = 80;
                                                                if (cursor != null) {
                                                                    cursor.close();
                                                                }
                                                                if (a(a, i2)) {
                                                                    return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                                }
                                                                return null;
                                                            }
                                                        } else if (toLowerCase.indexOf("wap") != -1) {
                                                            if (TextUtils.isEmpty(string)) {
                                                            }
                                                            obj = null;
                                                            string = a;
                                                            if (obj == null) {
                                                                string = "10.0.0.200";
                                                            }
                                                            i2 = 80;
                                                            a = string;
                                                            if (cursor != null) {
                                                                try {
                                                                    cursor.close();
                                                                } catch (Throwable th3) {
                                                                    eb.a(th3, "ProxyUtil", "getHostProxy2");
                                                                    th3.printStackTrace();
                                                                }
                                                            }
                                                            if (a(a, i2)) {
                                                                return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                            }
                                                            return null;
                                                        }
                                                    }
                                                    i2 = i;
                                                    if (cursor != null) {
                                                        cursor.close();
                                                    }
                                                    if (a(a, i2)) {
                                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                    }
                                                    return null;
                                                } catch (Throwable th4) {
                                                    th3 = th4;
                                                    query = cursor;
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable e2222) {
                                                            eb.a(e2222, "ProxyUtil", "getHostProxy2");
                                                            e2222.printStackTrace();
                                                        }
                                                    }
                                                    throw th3;
                                                }
                                            } catch (Throwable th5) {
                                                th3 = th5;
                                                i2 = b2;
                                                try {
                                                    eb.a(th3, "ProxyUtil", "getHostProxy1");
                                                    th3.printStackTrace();
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable th32) {
                                                            eb.a(th32, "ProxyUtil", "getHostProxy2");
                                                            th32.printStackTrace();
                                                        }
                                                    }
                                                    if (a(a, i2)) {
                                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                    }
                                                    return null;
                                                } catch (Throwable th6) {
                                                    th32 = th6;
                                                    if (query != null) {
                                                        query.close();
                                                    }
                                                    throw th32;
                                                }
                                            }
                                        }
                                        if (b2 == -1) {
                                            i2 = 80;
                                        } else {
                                            i2 = b2;
                                        }
                                        if (query != null) {
                                            try {
                                                query.close();
                                            } catch (Throwable th322) {
                                                eb.a(th322, "ProxyUtil", "getHostProxy2");
                                                th322.printStackTrace();
                                            }
                                        }
                                    } catch (SecurityException e4) {
                                        th322 = e4;
                                        a = null;
                                        int i3 = b2;
                                        cursor = query;
                                        i = i3;
                                        eb.a(th322, "ProxyUtil", "getHostProxy");
                                        string = dq.o(context);
                                        if (string != null) {
                                            toLowerCase = string.toLowerCase(Locale.US);
                                            string = a();
                                            i = b();
                                            if (toLowerCase.indexOf("ctwap") != -1) {
                                                obj2 = 1;
                                                a = string;
                                                if (obj2 == null) {
                                                    a = "10.0.0.200";
                                                }
                                                if (i == -1) {
                                                    i2 = 80;
                                                    if (cursor != null) {
                                                        cursor.close();
                                                    }
                                                    if (a(a, i2)) {
                                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                    }
                                                    return null;
                                                }
                                            } else if (toLowerCase.indexOf("wap") != -1) {
                                                if (TextUtils.isEmpty(string)) {
                                                }
                                                obj = null;
                                                string = a;
                                                if (obj == null) {
                                                    string = "10.0.0.200";
                                                }
                                                i2 = 80;
                                                a = string;
                                                if (cursor != null) {
                                                    cursor.close();
                                                }
                                                if (a(a, i2)) {
                                                    return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                                }
                                                return null;
                                            }
                                        }
                                        i2 = i;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (a(a, i2)) {
                                            return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                        }
                                        return null;
                                    } catch (Throwable th7) {
                                        th322 = th7;
                                        i2 = b2;
                                        a = null;
                                        eb.a(th322, "ProxyUtil", "getHostProxy1");
                                        th322.printStackTrace();
                                        if (query != null) {
                                            query.close();
                                        }
                                        if (a(a, i2)) {
                                            return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                        }
                                        return null;
                                    }
                                    if (a(a, i2)) {
                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                    }
                                }
                            }
                        }
                    } catch (SecurityException e5) {
                        th322 = e5;
                        cursor = query;
                        a = null;
                        i = -1;
                        eb.a(th322, "ProxyUtil", "getHostProxy");
                        string = dq.o(context);
                        if (string != null) {
                            toLowerCase = string.toLowerCase(Locale.US);
                            string = a();
                            i = b();
                            if (toLowerCase.indexOf("ctwap") != -1) {
                                obj2 = 1;
                                a = string;
                                if (obj2 == null) {
                                    a = "10.0.0.200";
                                }
                                if (i == -1) {
                                    i2 = 80;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (a(a, i2)) {
                                        return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                    }
                                    return null;
                                }
                            } else if (toLowerCase.indexOf("wap") != -1) {
                                if (TextUtils.isEmpty(string)) {
                                }
                                obj = null;
                                string = a;
                                if (obj == null) {
                                    string = "10.0.0.200";
                                }
                                i2 = 80;
                                a = string;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (a(a, i2)) {
                                    return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                                }
                                return null;
                            }
                        }
                        i2 = i;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a(a, i2)) {
                            return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                        }
                        return null;
                    } catch (Throwable th8) {
                        th322 = th8;
                        a = null;
                        eb.a(th322, "ProxyUtil", "getHostProxy1");
                        th322.printStackTrace();
                        if (query != null) {
                            query.close();
                        }
                        if (a(a, i2)) {
                            return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                        }
                        return null;
                    }
                }
                a = null;
                if (query != null) {
                    query.close();
                }
            } catch (SecurityException e6) {
                th322 = e6;
                cursor = null;
                i = -1;
                a = null;
                eb.a(th322, "ProxyUtil", "getHostProxy");
                string = dq.o(context);
                if (string != null) {
                    toLowerCase = string.toLowerCase(Locale.US);
                    string = a();
                    i = b();
                    if (toLowerCase.indexOf("ctwap") != -1) {
                        obj2 = 1;
                        a = string;
                        if (obj2 == null) {
                            a = "10.0.0.200";
                        }
                        if (i == -1) {
                            i2 = 80;
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (a(a, i2)) {
                                return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                            }
                            return null;
                        }
                    } else if (toLowerCase.indexOf("wap") != -1) {
                        if (TextUtils.isEmpty(string)) {
                        }
                        obj = null;
                        string = a;
                        if (obj == null) {
                            string = "10.0.0.200";
                        }
                        i2 = 80;
                        a = string;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a(a, i2)) {
                            return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                        }
                        return null;
                    }
                }
                i2 = i;
                if (cursor != null) {
                    cursor.close();
                }
                if (a(a, i2)) {
                    return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                }
                return null;
            } catch (Throwable th9) {
                th322 = th9;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th322;
            }
            try {
                if (a(a, i2)) {
                    return new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i2));
                }
            } catch (Throwable th3222) {
                eb.a(th3222, "ProxyUtil", "getHostProxy2");
                th3222.printStackTrace();
            }
        }
        return null;
    }

    private static boolean a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    private static String a() {
        String defaultHost;
        try {
            defaultHost = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            eb.a(th, "ProxyUtil", "getDefHost");
            defaultHost = null;
        }
        if (defaultHost == null) {
            return "null";
        }
        return defaultHost;
    }

    private static Proxy a(Context context, URI uri) {
        if (c(context)) {
            try {
                List select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    return null;
                }
                Proxy proxy = (Proxy) select.get(0);
                return (proxy == null || proxy.type() == Type.DIRECT) ? null : proxy;
            } catch (Throwable th) {
                eb.a(th, "ProxyUtil", "getProxySelectorCfg");
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return dq.m(context) == 0;
    }

    private static int b() {
        int i = -1;
        try {
            i = android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            eb.a(th, "ProxyUtil", "getDefPort");
        }
        return i;
    }
}
