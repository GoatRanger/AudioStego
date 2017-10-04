package com.nokia.maps;

import com.here.android.mpa.search.Category;
import com.here.android.mpa.search.Category.Global;
import com.here.android.mpa.search.CategoryFilter;
import java.util.ArrayList;
import java.util.List;

public class dc {
    private static k<CategoryFilter, dc> b;
    private static am<CategoryFilter, dc> c;
    private List<String> a = new ArrayList();

    public static void a(k<CategoryFilter, dc> kVar, am<CategoryFilter, dc> amVar) {
        b = kVar;
        c = amVar;
    }

    static dc a(CategoryFilter categoryFilter) {
        return (dc) b.a(categoryFilter);
    }

    static {
        ce.a(CategoryFilter.class);
    }

    public void a(Global global) {
        dy.a((Object) global, "filter argument is null");
        this.a.add(global.toString());
    }

    public void a(Category category) {
        dy.a((Object) category, "filter argument is null");
        this.a.add(category.getId());
    }

    public void a(String str) {
        dy.a((Object) str, "filter argument is null");
        this.a.add(str);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.a) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (dc) obj;
        } else if (CategoryFilter.class != obj.getClass()) {
            return false;
        } else {
            obj = a((CategoryFilter) obj);
        }
        if (this.a == null) {
            if (obj.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(obj.a)) {
            return true;
        } else {
            return false;
        }
    }
}
