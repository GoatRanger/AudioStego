package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.here.android.mpa.search.Category;
import com.here.android.mpa.search.Category.Global;
import java.util.ArrayList;
import java.util.List;

public final class PlacesCategory {
    private static k<Category, PlacesCategory> a;
    private static am<Category, PlacesCategory> b;
    @SerializedName("name")
    private String m_exploreName;
    @SerializedName("href")
    private String m_href;
    @SerializedName("icon")
    private String m_icon;
    @SerializedName("id")
    private String m_id;
    @SerializedName("system")
    private String m_system;
    @SerializedName("title")
    private String m_title;
    @SerializedName("type")
    private String m_type;
    @SerializedName("within")
    private List<String> m_within = new ArrayList();

    public static void a(k<Category, PlacesCategory> kVar, am<Category, PlacesCategory> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesCategory a(Category category) {
        return (PlacesCategory) a.a(category);
    }

    static Category a(PlacesCategory placesCategory) {
        if (placesCategory != null) {
            return (Category) b.a(placesCategory);
        }
        return null;
    }

    static {
        ce.a(Category.class);
    }

    public static List<Category> a() {
        List<Category> arrayList = new ArrayList();
        for (Global global : Global.values()) {
            arrayList.add(PlacesCategoryGraph.a().a(global.toString()));
        }
        return arrayList;
    }

    public static Category a(Global global) {
        return PlacesCategoryGraph.a().a(global.toString());
    }

    public final String b() {
        return em.a(this.m_id);
    }

    public final String c() {
        String str = this.m_title;
        if (str == null) {
            str = this.m_exploreName;
        }
        return em.a(str);
    }

    public final String d() {
        return em.a(this.m_icon);
    }

    public List<Category> e() {
        List<Category> arrayList = new ArrayList();
        try {
            arrayList = PlacesCategoryGraph.a().c(b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public Category f() {
        Category category = null;
        try {
            category = PlacesCategoryGraph.a().b(b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.m_title == null ? 0 : this.m_title.hashCode()) + (((this.m_id == null ? 0 : this.m_id.hashCode()) + (((this.m_icon == null ? 0 : this.m_icon.hashCode()) + (((this.m_href == null ? 0 : this.m_href.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
        if (this.m_type != null) {
            i = this.m_type.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (PlacesCategory) obj;
        } else if (Category.class != obj.getClass()) {
            return false;
        } else {
            obj = a((Category) obj);
        }
        if (this.m_href == null) {
            if (!TextUtils.isEmpty(obj.m_href)) {
                return false;
            }
        } else if (!this.m_href.equals(obj.m_href)) {
            return false;
        }
        if (this.m_icon == null) {
            if (!TextUtils.isEmpty(obj.m_icon)) {
                return false;
            }
        } else if (!this.m_icon.equals(obj.m_icon)) {
            return false;
        }
        if (this.m_id == null) {
            if (!TextUtils.isEmpty(obj.m_id)) {
                return false;
            }
        } else if (!this.m_id.equals(obj.m_id)) {
            return false;
        }
        if (this.m_title == null) {
            if (!TextUtils.isEmpty(obj.m_title)) {
                return false;
            }
        } else if (!this.m_title.equals(obj.m_title)) {
            return false;
        }
        if (this.m_type == null) {
            if (TextUtils.isEmpty(obj.m_type)) {
                return true;
            }
            return false;
        } else if (this.m_type.equals(obj.m_type)) {
            return true;
        } else {
            return false;
        }
    }

    List<String> g() {
        return this.m_within;
    }
}
