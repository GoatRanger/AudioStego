package com.google.a.b.a;

import com.google.a.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class v extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public w a(r rVar) {
        String c = u.c(rVar);
        if (!c.startsWith("sms:") && !c.startsWith("SMS:") && !c.startsWith("mms:") && !c.startsWith("MMS:")) {
            return null;
        }
        String str;
        String str2;
        Map d = u.d(c);
        int i = 0;
        if (d == null || d.isEmpty()) {
            str = null;
            str2 = null;
        } else {
            String str3 = (String) d.get("subject");
            str = (String) d.get("body");
            str2 = str3;
            i = 1;
        }
        int indexOf = c.indexOf(63, 4);
        if (indexOf < 0 || r0 == 0) {
            str3 = c.substring(4);
        } else {
            str3 = c.substring(4, indexOf);
        }
        indexOf = -1;
        List arrayList = new ArrayList(1);
        List arrayList2 = new ArrayList(1);
        while (true) {
            int indexOf2 = str3.indexOf(44, indexOf + 1);
            if (indexOf2 > indexOf) {
                a(arrayList, arrayList2, str3.substring(indexOf + 1, indexOf2));
                indexOf = indexOf2;
            } else {
                a(arrayList, arrayList2, str3.substring(indexOf + 1));
                return new w((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2, str);
            }
        }
    }

    private static void a(Collection<String> collection, Collection<String> collection2, String str) {
        Object obj = null;
        int indexOf = str.indexOf(59);
        if (indexOf < 0) {
            collection.add(str);
            collection2.add(null);
            return;
        }
        collection.add(str.substring(0, indexOf));
        String substring = str.substring(indexOf + 1);
        if (substring.startsWith("via=")) {
            obj = substring.substring(4);
        }
        collection2.add(obj);
    }
}
