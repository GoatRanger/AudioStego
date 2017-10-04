package com.dji.a.f;

import com.dji.a.a.a;
import java.util.HashMap;
import java.util.Map;
import org.msgpack.core.MessageBufferPacker;

public class g {
    public static byte[] a(HashMap<String, a> hashMap) {
        if (!a((Map) hashMap)) {
            return null;
        }
        Object[] toArray = hashMap.values().toArray();
        HashMap[] hashMapArr = new HashMap[toArray.length];
        for (int i = 0; i < toArray.length; i++) {
            hashMapArr[i] = ((a) toArray[i]).a();
        }
        return a(hashMapArr);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.util.HashMap<java.lang.String, java.lang.Object>[] r4) {
        /*
        r1 = org.msgpack.core.MessagePack.newDefaultBufferPacker();
        r0 = r4.length;	 Catch:{ IOException -> 0x0021 }
        r1.packArrayHeader(r0);	 Catch:{ IOException -> 0x0021 }
        r2 = r4.length;	 Catch:{ IOException -> 0x0021 }
        r0 = 0;
    L_0x000a:
        if (r0 >= r2) goto L_0x0014;
    L_0x000c:
        r3 = r4[r0];	 Catch:{ IOException -> 0x0021 }
        a(r1, r3);	 Catch:{ IOException -> 0x0021 }
        r0 = r0 + 1;
        goto L_0x000a;
    L_0x0014:
        r1.close();	 Catch:{ IOException -> 0x001c }
    L_0x0017:
        r0 = r1.toByteArray();
        return r0;
    L_0x001c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0017;
    L_0x0021:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x002e }
        r1.close();	 Catch:{ IOException -> 0x0029 }
        goto L_0x0017;
    L_0x0029:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0017;
    L_0x002e:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x0033 }
    L_0x0032:
        throw r0;
    L_0x0033:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dji.a.f.g.a(java.util.HashMap[]):byte[]");
    }

    private static void a(MessageBufferPacker messageBufferPacker, HashMap<String, Object> hashMap) {
        messageBufferPacker.packMapHeader(hashMap.size());
        for (String str : hashMap.keySet()) {
            messageBufferPacker.packString(str);
            Object obj = hashMap.get(str);
            if (obj instanceof Integer) {
                messageBufferPacker.packInt(((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                messageBufferPacker.packLong(((Long) obj).longValue());
            } else if (obj instanceof String) {
                messageBufferPacker.packString((String) obj);
            } else if (obj instanceof HashMap) {
                a(messageBufferPacker, (HashMap) obj);
            }
        }
    }

    private static boolean a(Map map) {
        if (map == null || map.size() <= 0) {
            return false;
        }
        return true;
    }
}
