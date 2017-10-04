package com.here.a.a.a.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class n {
    private List<m> a;

    public n(List<m> list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        this.a = list;
    }

    public List<m> a() {
        return Collections.unmodifiableList(this.a);
    }

    public static n a(String str) {
        List list = null;
        String[] split = str.split("\\s");
        if (split.length > 0) {
            list = new ArrayList();
            for (String split2 : split) {
                String[] split3 = split2.split(",");
                if (split3.length == 2) {
                    list.add(new m(Double.parseDouble(split3[0]), Double.parseDouble(split3[1])));
                }
            }
        }
        return new n(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((n) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
