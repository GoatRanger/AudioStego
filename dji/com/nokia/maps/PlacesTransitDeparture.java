package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.search.ExtendedAttribute;
import com.here.android.mpa.search.TransitDeparture;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlacesTransitDeparture {
    private static k<TransitDeparture, PlacesTransitDeparture> a;
    private static am<TransitDeparture, PlacesTransitDeparture> b;
    @SerializedName("direction")
    private String m_direction;
    @SerializedName("exception")
    private String m_exception;
    @SerializedName("extended")
    private List<PlacesAttribute> m_extendedAttributes = new ArrayList();
    @SerializedName("line")
    private String m_line;
    @SerializedName("operator")
    private String m_operator;
    @SerializedName("real")
    private Map<String, String> m_real = new LinkedTreeMap();
    @SerializedName("scheduled")
    private Map<String, String> m_scheduled = new LinkedTreeMap();

    public static void a(k<TransitDeparture, PlacesTransitDeparture> kVar, am<TransitDeparture, PlacesTransitDeparture> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesTransitDeparture a(TransitDeparture transitDeparture) {
        return (PlacesTransitDeparture) a.a(transitDeparture);
    }

    static TransitDeparture a(PlacesTransitDeparture placesTransitDeparture) {
        if (placesTransitDeparture != null) {
            return (TransitDeparture) b.a(placesTransitDeparture);
        }
        return null;
    }

    static {
        ce.a(TransitDeparture.class);
    }

    public String a() {
        return em.a(this.m_line);
    }

    public Map<String, String> b() {
        return this.m_scheduled != null ? this.m_scheduled : new LinkedTreeMap();
    }

    public Map<String, String> c() {
        return this.m_real != null ? this.m_real : new LinkedTreeMap();
    }

    public String d() {
        return em.a(this.m_direction);
    }

    public String e() {
        return em.a(this.m_exception);
    }

    public String f() {
        return em.a(this.m_operator);
    }

    public List<ExtendedAttribute> g() {
        List<ExtendedAttribute> arrayList = new ArrayList();
        if (this.m_extendedAttributes != null) {
            for (PlacesAttribute a : this.m_extendedAttributes) {
                arrayList.add(PlacesAttribute.a(a));
            }
        }
        return arrayList;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((this.m_exception == null ? 0 : this.m_exception.hashCode()) + (((this.m_direction == null ? 0 : this.m_direction.hashCode()) + 31) * 31)) * 31;
        if (this.m_extendedAttributes == null) {
            i = 0;
        } else {
            i = this.m_extendedAttributes.hashCode();
        }
        i = ((this.m_real == null ? 0 : this.m_real.hashCode()) + (((this.m_operator == null ? 0 : this.m_operator.hashCode()) + (((this.m_line == null ? 0 : this.m_line.hashCode()) + ((i + hashCode) * 31)) * 31)) * 31)) * 31;
        if (this.m_scheduled != null) {
            i2 = this.m_scheduled.hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            obj = (PlacesTransitDeparture) obj;
        } else if (TransitDeparture.class != obj.getClass()) {
            return false;
        } else {
            obj = a((TransitDeparture) obj);
        }
        if (this.m_direction == null) {
            if (!TextUtils.isEmpty(obj.m_direction)) {
                return false;
            }
        } else if (!this.m_direction.equals(obj.m_direction)) {
            return false;
        }
        if (this.m_exception == null) {
            if (!TextUtils.isEmpty(obj.m_exception)) {
                return false;
            }
        } else if (!this.m_exception.equals(obj.m_exception)) {
            return false;
        }
        if (this.m_extendedAttributes == null) {
            if (obj.m_extendedAttributes != null) {
                return false;
            }
        } else if (!this.m_extendedAttributes.equals(obj.m_extendedAttributes)) {
            return false;
        }
        if (this.m_line == null) {
            if (!TextUtils.isEmpty(obj.m_line)) {
                return false;
            }
        } else if (!this.m_line.equals(obj.m_line)) {
            return false;
        }
        if (this.m_operator == null) {
            if (!TextUtils.isEmpty(obj.m_operator)) {
                return false;
            }
        } else if (!this.m_operator.equals(obj.m_operator)) {
            return false;
        }
        if (this.m_real == null) {
            if (obj.m_real != null) {
                return false;
            }
        } else if (!this.m_real.equals(obj.m_real)) {
            return false;
        }
        if (this.m_scheduled == null) {
            if (obj.m_scheduled != null) {
                return false;
            }
            return true;
        } else if (this.m_scheduled.equals(obj.m_scheduled)) {
            return true;
        } else {
            return false;
        }
    }
}
