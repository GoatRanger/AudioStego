package com.here.android.mpa.search;

import com.here.android.mpa.search.Category.Global;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dc;
import com.nokia.maps.k;

@Online
public class CategoryFilter {
    private dc a;

    public CategoryFilter() {
        this.a = new dc();
    }

    private CategoryFilter(dc dcVar) {
        this.a = dcVar;
    }

    public CategoryFilter add(Global global) {
        this.a.a(global);
        return this;
    }

    public CategoryFilter add(Category category) {
        this.a.a(category);
        return this;
    }

    public CategoryFilter add(String str) {
        this.a.a(str);
        return this;
    }

    public String toString() {
        return this.a.toString();
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(obj);
    }

    static {
        dc.a(new k<CategoryFilter, dc>() {
            public dc a(CategoryFilter categoryFilter) {
                return categoryFilter.a;
            }
        }, new am<CategoryFilter, dc>() {
            public CategoryFilter a(dc dcVar) {
                return dcVar != null ? new CategoryFilter(dcVar) : null;
            }
        });
    }
}
