package dji.thirdparty.afinal.f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class b {
    private static String c = "UTF-8";
    protected ConcurrentHashMap<String, String> a;
    protected ConcurrentHashMap<String, a> b;

    private static class a {
        public InputStream a;
        public String b;
        public String c;

        public a(InputStream inputStream, String str, String str2) {
            this.a = inputStream;
            this.b = str;
            this.c = str2;
        }

        public String a() {
            if (this.b != null) {
                return this.b;
            }
            return "nofilename";
        }
    }

    public b() {
        d();
    }

    public b(Map<String, String> map) {
        d();
        for (Entry entry : map.entrySet()) {
            a((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public b(String str, String str2) {
        d();
        a(str, str2);
    }

    public b(Object... objArr) {
        d();
        int length = objArr.length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Supplied arguments must be even");
        }
        for (int i = 0; i < length; i += 2) {
            a(String.valueOf(objArr[i]), String.valueOf(objArr[i + 1]));
        }
    }

    public void a(String str, String str2) {
        if (str != null && str2 != null) {
            this.a.put(str, str2);
        }
    }

    public void a(String str, File file) throws FileNotFoundException {
        a(str, new FileInputStream(file), file.getName());
    }

    public void a(String str, InputStream inputStream) {
        a(str, inputStream, null);
    }

    public void a(String str, InputStream inputStream, String str2) {
        a(str, inputStream, str2, null);
    }

    public void a(String str, InputStream inputStream, String str2, String str3) {
        if (str != null && inputStream != null) {
            this.b.put(str, new a(inputStream, str2, str3));
        }
    }

    public void a(String str) {
        this.a.remove(str);
        this.b.remove(str);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.a.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(com.alipay.sdk.h.a.b);
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.b.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(com.alipay.sdk.h.a.b);
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        return stringBuilder.toString();
    }

    public HttpEntity a() {
        if (this.b.isEmpty()) {
            try {
                return new UrlEncodedFormEntity(b(), c);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        HttpEntity dVar = new d();
        for (Entry entry : this.a.entrySet()) {
            dVar.a((String) entry.getKey(), (String) entry.getValue());
        }
        int size = this.b.entrySet().size() - 1;
        int i = 0;
        for (Entry entry2 : this.b.entrySet()) {
            a aVar = (a) entry2.getValue();
            if (aVar.a != null) {
                boolean z = i == size;
                if (aVar.c != null) {
                    dVar.a((String) entry2.getKey(), aVar.a(), aVar.a, aVar.c, z);
                } else {
                    dVar.a((String) entry2.getKey(), aVar.a(), aVar.a, z);
                }
            }
            i++;
        }
        return dVar;
    }

    private void d() {
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
    }

    protected List<BasicNameValuePair> b() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.a.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        return linkedList;
    }

    public String c() {
        return URLEncodedUtils.format(b(), c);
    }
}
