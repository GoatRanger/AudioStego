package dji.thirdparty.afinal.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

public class e implements CookieStore {
    private static final String a = "CookiePrefsFile";
    private static final String b = "names";
    private static final String c = "cookie_";
    private final ConcurrentHashMap<String, Cookie> d = new ConcurrentHashMap();
    private final SharedPreferences e;

    public class a implements Serializable {
        private static final long b = 6374381828722046732L;
        final /* synthetic */ e a;
        private final transient Cookie c;
        private transient BasicClientCookie d;

        public a(e eVar, Cookie cookie) {
            this.a = eVar;
            this.c = cookie;
        }

        public Cookie a() {
            Cookie cookie = this.c;
            if (this.d != null) {
                return this.d;
            }
            return cookie;
        }

        private void a(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(this.c.getName());
            objectOutputStream.writeObject(this.c.getValue());
            objectOutputStream.writeObject(this.c.getComment());
            objectOutputStream.writeObject(this.c.getDomain());
            objectOutputStream.writeObject(this.c.getExpiryDate());
            objectOutputStream.writeObject(this.c.getPath());
            objectOutputStream.writeInt(this.c.getVersion());
            objectOutputStream.writeBoolean(this.c.isSecure());
        }

        private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            this.d = new BasicClientCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
            this.d.setComment((String) objectInputStream.readObject());
            this.d.setDomain((String) objectInputStream.readObject());
            this.d.setExpiryDate((Date) objectInputStream.readObject());
            this.d.setPath((String) objectInputStream.readObject());
            this.d.setVersion(objectInputStream.readInt());
            this.d.setSecure(objectInputStream.readBoolean());
        }
    }

    public e(Context context) {
        int i = 0;
        this.e = context.getSharedPreferences(a, 0);
        String string = this.e.getString(b, null);
        if (string != null) {
            String[] split = TextUtils.split(string, ",");
            int length = split.length;
            while (i < length) {
                String str = split[i];
                String string2 = this.e.getString(c + str, null);
                if (string2 != null) {
                    Cookie a = a(string2);
                    if (a != null) {
                        this.d.put(str, a);
                    }
                }
                i++;
            }
            clearExpired(new Date());
        }
    }

    public void addCookie(Cookie cookie) {
        String name = cookie.getName();
        if (cookie.isExpired(new Date())) {
            this.d.remove(name);
        } else {
            this.d.put(name, cookie);
        }
        Editor edit = this.e.edit();
        edit.putString(b, TextUtils.join(",", this.d.keySet()));
        edit.putString(c + name, a(new a(this, cookie)));
        edit.commit();
    }

    public void clear() {
        this.d.clear();
        Editor edit = this.e.edit();
        for (String str : this.d.keySet()) {
            edit.remove(c + str);
        }
        edit.remove(b);
        edit.commit();
    }

    public boolean clearExpired(Date date) {
        Editor edit = this.e.edit();
        boolean z = false;
        for (Entry entry : this.d.entrySet()) {
            boolean z2;
            String str = (String) entry.getKey();
            if (((Cookie) entry.getValue()).isExpired(date)) {
                this.d.remove(str);
                edit.remove(c + str);
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        if (z) {
            edit.putString(b, TextUtils.join(",", this.d.keySet()));
        }
        edit.commit();
        return z;
    }

    public List<Cookie> getCookies() {
        return new ArrayList(this.d.values());
    }

    protected String a(a aVar) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(aVar);
            return a(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    protected Cookie a(String str) {
        try {
            return ((a) new ObjectInputStream(new ByteArrayInputStream(b(str))).readObject()).a();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuffer.append('0');
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString().toUpperCase();
    }

    protected byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
