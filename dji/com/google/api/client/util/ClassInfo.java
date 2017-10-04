package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.WeakHashMap;

public final class ClassInfo {
    private static final Map<Class<?>, ClassInfo> CACHE = new WeakHashMap();
    private static final Map<Class<?>, ClassInfo> CACHE_IGNORE_CASE = new WeakHashMap();
    private final Class<?> clazz;
    private final boolean ignoreCase;
    private final IdentityHashMap<String, FieldInfo> nameToFieldInfoMap = new IdentityHashMap();
    final List<String> names;

    public static ClassInfo of(Class<?> cls) {
        return of(cls, false);
    }

    public static ClassInfo of(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        ClassInfo classInfo;
        Map map = z ? CACHE_IGNORE_CASE : CACHE;
        synchronized (map) {
            classInfo = (ClassInfo) map.get(cls);
            if (classInfo == null) {
                classInfo = new ClassInfo(cls, z);
                map.put(cls, classInfo);
            }
        }
        return classInfo;
    }

    public Class<?> getUnderlyingClass() {
        return this.clazz;
    }

    public final boolean getIgnoreCase() {
        return this.ignoreCase;
    }

    public FieldInfo getFieldInfo(String str) {
        if (str != null) {
            if (this.ignoreCase) {
                str = str.toLowerCase();
            }
            str = str.intern();
        }
        return (FieldInfo) this.nameToFieldInfoMap.get(str);
    }

    public Field getField(String str) {
        FieldInfo fieldInfo = getFieldInfo(str);
        return fieldInfo == null ? null : fieldInfo.getField();
    }

    public boolean isEnum() {
        return this.clazz.isEnum();
    }

    public Collection<String> getNames() {
        return this.names;
    }

    private ClassInfo(Class<?> cls, boolean z) {
        this.clazz = cls;
        this.ignoreCase = z;
        boolean z2 = (z && cls.isEnum()) ? false : true;
        Preconditions.checkArgument(z2, "cannot ignore case on an enum: " + cls);
        Collection treeSet = new TreeSet(new Comparator<String>() {
            public int compare(String str, String str2) {
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                return str2 == null ? 1 : str.compareTo(str2);
            }
        });
        for (Field field : cls.getDeclaredFields()) {
            FieldInfo of = FieldInfo.of(field);
            if (of != null) {
                Object intern;
                String name = of.getName();
                if (z) {
                    intern = name.toLowerCase().intern();
                } else {
                    String str = name;
                }
                FieldInfo fieldInfo = (FieldInfo) this.nameToFieldInfoMap.get(intern);
                boolean z3 = fieldInfo == null;
                String str2 = "two fields have the same %sname <%s>: %s and %s";
                Object[] objArr = new Object[4];
                objArr[0] = z ? "case-insensitive " : "";
                objArr[1] = intern;
                objArr[2] = field;
                objArr[3] = fieldInfo == null ? null : fieldInfo.getField();
                Preconditions.checkArgument(z3, str2, objArr);
                this.nameToFieldInfoMap.put(intern, of);
                treeSet.add(intern);
            }
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            ClassInfo of2 = of(superclass, z);
            treeSet.addAll(of2.names);
            for (Entry entry : of2.nameToFieldInfoMap.entrySet()) {
                str = (String) entry.getKey();
                if (!this.nameToFieldInfoMap.containsKey(str)) {
                    this.nameToFieldInfoMap.put(str, entry.getValue());
                }
            }
        }
        this.names = treeSet.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(treeSet));
    }

    public Collection<FieldInfo> getFieldInfos() {
        return Collections.unmodifiableCollection(this.nameToFieldInfoMap.values());
    }
}
