package com.alibaba.sdk.android.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SortUtils {

    public static class SortInfo implements Serializable {
        private static final long serialVersionUID = 3959903664223165585L;
        public String[] after;
        public Boolean afterAll;
        public String[] before;
        public Boolean beforeAll;
        public String name;
    }

    public static void sorts(SortInfo[] sortInfoArr) {
        int i;
        Entry entry;
        int i2 = 0;
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        for (SortInfo sortInfo : sortInfoArr) {
            if (sortInfo.before != null) {
                for (Object obj : sortInfo.before) {
                    Set set = (Set) hashMap.get(obj);
                    if (set == null) {
                        set = new HashSet();
                        hashMap.put(obj, set);
                    }
                    set.add(sortInfo.name);
                }
            }
            Collection collection = (Set) hashMap.get(sortInfo.name);
            if (collection == null) {
                collection = new HashSet();
                hashMap.put(sortInfo.name, collection);
            }
            if (sortInfo.after != null) {
                Collections.addAll(collection, sortInfo.after);
            }
            hashMap2.put(sortInfo.name, sortInfo);
        }
        Object hashSet = new HashSet();
        for (SortInfo sortInfo2 : sortInfoArr) {
            SortInfo sortInfo22;
            a(sortInfo22.name, sortInfo22.name, hashSet, hashMap);
            ((Set) hashMap.get(sortInfo22.name)).addAll(hashSet);
            hashSet.clear();
        }
        Collection hashSet2 = new HashSet();
        Object<String> hashSet3 = new HashSet();
        for (SortInfo sortInfo222 : sortInfoArr) {
            String str = sortInfo222.name;
            if (sortInfo222.beforeAll != null && sortInfo222.beforeAll.booleanValue()) {
                hashSet2.add(str);
                hashSet2.addAll((Set) hashMap.get(str));
            } else if (sortInfo222.afterAll != null && sortInfo222.afterAll.booleanValue()) {
                hashSet3.add(str);
                for (Entry entry2 : hashMap.entrySet()) {
                    if (((Set) entry2.getValue()).contains(str)) {
                        hashSet3.add(entry2.getKey());
                    }
                }
            }
        }
        for (Entry entry3 : hashMap.entrySet()) {
            if (!hashSet2.contains(entry3.getKey())) {
                ((Set) entry3.getValue()).addAll(hashSet2);
            }
        }
        Collection hashSet4 = new HashSet(hashMap.keySet());
        hashSet4.removeAll(hashSet3);
        for (String str2 : hashSet3) {
            ((Set) hashMap.get(str2)).addAll(hashSet4);
        }
        hashSet = new HashSet();
        Set hashSet5 = new HashSet();
        List<String> arrayList = new ArrayList(sortInfoArr.length);
        while (hashMap.size() > 0) {
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                entry3 = (Entry) it.next();
                ((Set) entry3.getValue()).removeAll(hashSet5);
                ((Set) entry3.getValue()).retainAll(hashMap.keySet());
                if (((Set) entry3.getValue()).size() == 0) {
                    hashSet.add(entry3.getKey());
                    arrayList.add(entry3.getKey());
                    it.remove();
                }
            }
            if (hashSet.size() == 0) {
                throw new IllegalStateException("Circular found for " + hashMap);
            }
            hashSet5.clear();
            hashSet5.addAll(hashSet);
            hashSet.clear();
        }
        for (String str22 : arrayList) {
            int i3;
            sortInfo222 = (SortInfo) hashMap2.get(str22);
            if (sortInfo222 != null) {
                i = i2 + 1;
                sortInfoArr[i2] = sortInfo222;
                i3 = i;
            } else {
                i3 = i2;
            }
            i2 = i3;
        }
    }

    private static void a(String str, String str2, Set<String> set, Map<String, Set<String>> map) {
        Set<String> set2 = (Set) map.get(str2);
        if (set2 != null && !set2.contains(str)) {
            for (String str3 : set2) {
                if (set.add(str3)) {
                    a(str, str3, set, map);
                }
            }
        }
    }
}
