package dji.thirdparty.b;

import dji.thirdparty.b.a.b.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public final class t {
    private final String[] a;

    public static final class a {
        private final List<String> a = new ArrayList(20);

        a a(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return b(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return b("", str.substring(1));
            }
            return b("", str);
        }

        public a b(String str) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                return a(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
            }
            throw new IllegalArgumentException("Unexpected header: " + str);
        }

        public a a(String str, String str2) {
            d(str, str2);
            return b(str, str2);
        }

        a b(String str, String str2) {
            this.a.add(str);
            this.a.add(str2.trim());
            return this;
        }

        public a c(String str) {
            int i = 0;
            while (i < this.a.size()) {
                if (str.equalsIgnoreCase((String) this.a.get(i))) {
                    this.a.remove(i);
                    this.a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public a c(String str, String str2) {
            d(str, str2);
            c(str);
            b(str, str2);
            return this;
        }

        private void d(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            } else if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            } else {
                int i;
                char charAt;
                int length = str.length();
                for (i = 0; i < length; i++) {
                    charAt = str.charAt(i);
                    if (charAt <= '\u001f' || charAt >= '') {
                        throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header name: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str}));
                    }
                }
                if (str2 == null) {
                    throw new IllegalArgumentException("value == null");
                }
                length = str2.length();
                for (i = 0; i < length; i++) {
                    charAt = str2.charAt(i);
                    if (charAt <= '\u001f' || charAt >= '') {
                        throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in %s value: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str, str2}));
                    }
                }
            }
        }

        public String d(String str) {
            for (int size = this.a.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase((String) this.a.get(size))) {
                    return (String) this.a.get(size + 1);
                }
            }
            return null;
        }

        public t a() {
            return new t();
        }
    }

    private t(a aVar) {
        this.a = (String[]) aVar.a.toArray(new String[aVar.a.size()]);
    }

    private t(String[] strArr) {
        this.a = strArr;
    }

    public String a(String str) {
        return a(this.a, str);
    }

    public Date b(String str) {
        String a = a(str);
        return a != null ? f.a(a) : null;
    }

    public int a() {
        return this.a.length / 2;
    }

    public String a(int i) {
        return this.a[i * 2];
    }

    public String b(int i) {
        return this.a[(i * 2) + 1];
    }

    public Set<String> b() {
        Set treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int a = a();
        for (int i = 0; i < a; i++) {
            treeSet.add(a(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public List<String> c(String str) {
        int a = a();
        List list = null;
        for (int i = 0; i < a; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (list == null) {
                    list = new ArrayList(2);
                }
                list.add(b(i));
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public a c() {
        a aVar = new a();
        Collections.addAll(aVar.a, this.a);
        return aVar;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int a = a();
        for (int i = 0; i < a; i++) {
            stringBuilder.append(a(i)).append(": ").append(b(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    public Map<String, List<String>> d() {
        Map<String, List<String>> linkedHashMap = new LinkedHashMap();
        int a = a();
        for (int i = 0; i < a; i++) {
            String a2 = a(i);
            List list = (List) linkedHashMap.get(a2);
            if (list == null) {
                list = new ArrayList(2);
                linkedHashMap.put(a2, list);
            }
            list.add(b(i));
        }
        return linkedHashMap;
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static t a(String... strArr) {
        if (strArr == null || strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        int i;
        String[] strArr2 = (String[]) strArr.clone();
        for (i = 0; i < strArr2.length; i++) {
            if (strArr2[i] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i] = strArr2[i].trim();
        }
        i = 0;
        while (i < strArr2.length) {
            String str = strArr2[i];
            String str2 = strArr2[i + 1];
            if (str.length() != 0 && str.indexOf(0) == -1 && str2.indexOf(0) == -1) {
                i += 2;
            } else {
                throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
            }
        }
        return new t(strArr2);
    }

    public static t a(Map<String, String> map) {
        if (map == null) {
            throw new IllegalArgumentException("Expected map with header names and values");
        }
        String[] strArr = new String[(map.size() * 2)];
        int i = 0;
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            String trim = ((String) entry.getKey()).trim();
            String trim2 = ((String) entry.getValue()).trim();
            if (trim.length() != 0 && trim.indexOf(0) == -1 && trim2.indexOf(0) == -1) {
                strArr[i] = trim;
                strArr[i + 1] = trim2;
                i += 2;
            } else {
                throw new IllegalArgumentException("Unexpected header: " + trim + ": " + trim2);
            }
        }
        return new t(strArr);
    }
}
