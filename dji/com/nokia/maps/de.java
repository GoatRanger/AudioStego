package com.nokia.maps;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class de implements CookieStore {
    private boolean a = false;
    private final ConcurrentHashMap<String, HttpCookie> b;
    private final ConcurrentHashMap<String, HttpCookie> c;
    private final SharedPreferences d;

    public de(Context context) {
        int i = 0;
        this.d = context.getSharedPreferences("PlacesCookiePrefsFile", 0);
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        String string = this.d.getString("names", null);
        if (string != null) {
            String[] split = TextUtils.split(string, ",");
            int length = split.length;
            while (i < length) {
                String str = split[i];
                String string2 = this.d.getString("places_cookie_" + str, null);
                if (string2 != null) {
                    HttpCookie a = a(string2);
                    if (a != null) {
                        this.b.put(str, a);
                    }
                }
                i++;
            }
            a();
        }
    }

    public void add(URI uri, HttpCookie httpCookie) {
        if (!this.a || !httpCookie.getDiscard()) {
            String str = httpCookie.getName() + httpCookie.getDomain();
            if (httpCookie.getDiscard()) {
                this.c.put(str, httpCookie);
                return;
            }
            if (httpCookie.hasExpired()) {
                this.b.remove(str);
            } else {
                this.b.put(str, httpCookie);
            }
            Editor edit = this.d.edit();
            edit.putString("names", TextUtils.join(",", this.b.keySet()));
            edit.putString("places_cookie_" + str, a(new ds(httpCookie)));
            edit.commit();
        }
    }

    public boolean removeAll() {
        Editor edit = this.d.edit();
        for (String str : this.b.keySet()) {
            edit.remove("places_cookie_" + str);
        }
        edit.remove("names");
        edit.commit();
        this.b.clear();
        this.c.clear();
        return true;
    }

    private boolean a() {
        Editor edit = this.d.edit();
        boolean z = false;
        for (Entry entry : this.b.entrySet()) {
            boolean z2;
            String str = (String) entry.getKey();
            if (((HttpCookie) entry.getValue()).hasExpired()) {
                this.b.remove(str);
                edit.remove("places_cookie_" + str);
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        if (z) {
            edit.putString("names", TextUtils.join(",", this.b.keySet()));
        }
        edit.commit();
        return z;
    }

    public List<HttpCookie> getCookies() {
        List<HttpCookie> arrayList = new ArrayList(this.b.values());
        arrayList.addAll(new ArrayList(this.c.values()));
        return arrayList;
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        String str = httpCookie.getName() + httpCookie.getDomain();
        if (httpCookie.getDiscard()) {
            this.c.remove(str);
        } else {
            this.b.remove(str);
            Editor edit = this.d.edit();
            edit.remove("places_cookie_" + str);
            edit.commit();
        }
        return true;
    }

    protected String a(ds dsVar) {
        ObjectOutputStream objectOutputStream;
        if (dsVar == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(dsVar);
            objectOutputStream.close();
            return a(byteArrayOutputStream.toByteArray());
        } catch (Throwable e) {
            Log.d("PlacesCookieStore", "IOException in encodeCookie", e);
            return null;
        } catch (Throwable th) {
            objectOutputStream.close();
        }
    }

    protected HttpCookie a(String str) {
        ObjectInputStream objectInputStream;
        HttpCookie a;
        Throwable e;
        Throwable th;
        try {
            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(b(str)));
            a = ((ds) objectInputStream.readObject()).a();
            try {
                objectInputStream.close();
            } catch (IOException e2) {
                e = e2;
                Log.d("PlacesCookieStore", "IOException in decodeCookie", e);
                return a;
            } catch (ClassNotFoundException e3) {
                e = e3;
                Log.d("PlacesCookieStore", "ClassNotFoundException in decodeCookie", e);
                return a;
            }
        } catch (Throwable e4) {
            th = e4;
            a = null;
            e = th;
            Log.d("PlacesCookieStore", "IOException in decodeCookie", e);
            return a;
        } catch (Throwable e42) {
            th = e42;
            a = null;
            e = th;
            Log.d("PlacesCookieStore", "ClassNotFoundException in decodeCookie", e);
            return a;
        } catch (Throwable th2) {
            objectInputStream.close();
        }
        return a;
    }

    protected String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString().toUpperCase(Locale.US);
    }

    protected byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public List<HttpCookie> get(URI uri) {
        return null;
    }

    public List<URI> getURIs() {
        return null;
    }
}
