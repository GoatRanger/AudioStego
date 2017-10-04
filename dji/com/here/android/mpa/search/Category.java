package com.here.android.mpa.search;

import com.nokia.maps.PlacesCategory;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

@Online
public class Category {
    private PlacesCategory a;

    @Online
    public enum Global {
        ACCOMMODATION("accommodation"),
        ADMINISTRATIVE_AREAS_BUILDINGS("administrative-areas-buildings"),
        BUSINESS_SERVICES("business-services"),
        EAT_DRINK("eat-drink"),
        FACILITIES("facilities"),
        GOING_OUT("going-out"),
        LEISURE_OUTDOOR("leisure-outdoor"),
        NATURAL_GEOGRAPHICAL("natural-geographical"),
        SHOPPING("shopping"),
        SIGHTS_MUSEUMS("sights-museums"),
        TRANSPORT("transport");
        
        private String a;

        private Global(String str) {
            this.a = str;
        }

        public String toString() {
            return this.a;
        }
    }

    private Category(PlacesCategory placesCategory) {
        this.a = placesCategory;
    }

    @Online
    public static List<Category> globalCategories() {
        return PlacesCategory.a();
    }

    @Online
    public static Category globalCategory(Global global) {
        return PlacesCategory.a(global);
    }

    public String getId() {
        return this.a.b();
    }

    public String getName() {
        return this.a.c();
    }

    public String getIconUrl() {
        return this.a.d();
    }

    public List<Category> getSubCategories() {
        return this.a.e();
    }

    public Category getParent() {
        return this.a.f();
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
        PlacesCategory.a(new k<Category, PlacesCategory>() {
            public PlacesCategory a(Category category) {
                return category.a;
            }
        }, new am<Category, PlacesCategory>() {
            public Category a(PlacesCategory placesCategory) {
                return placesCategory != null ? new Category(placesCategory) : null;
            }
        });
    }
}
