package com.nokia.maps;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.here.android.mpa.search.Address;
import com.nokia.maps.annotation.OnlineNative;
import dji.pilot.usercenter.protocol.d;
import java.util.Map;

public class PlacesAddress {
    private static k<Address, PlacesAddress> a;
    private static am<Address, PlacesAddress> b;
    @SerializedName("additionalData")
    private Map<String, String> m_additionalData = new LinkedTreeMap();
    @SerializedName("city")
    private String m_city;
    @SerializedName("country")
    private String m_country;
    @SerializedName("countryCode")
    private String m_countryCode;
    @SerializedName("county")
    private String m_county;
    @SerializedName("district")
    private String m_district;
    @SerializedName("floorNumber")
    private String m_floorNumber;
    @SerializedName("house")
    private String m_house;
    @SerializedName("label")
    private String m_label;
    @SerializedName("postalCode")
    private String m_postalCode;
    @SerializedName("state")
    private String m_state;
    @SerializedName("stateCode")
    private String m_stateCode;
    @SerializedName("street")
    private String m_street;
    @SerializedName("suiteNumberOrName")
    private String m_suiteNumberOrName;
    @SerializedName("text")
    private String m_text;

    public static void a(k<Address, PlacesAddress> kVar, am<Address, PlacesAddress> amVar) {
        a = kVar;
        b = amVar;
    }

    static PlacesAddress a(Address address) {
        return (PlacesAddress) a.a(address);
    }

    @OnlineNative
    static Address create(PlacesAddress placesAddress) {
        if (placesAddress != null) {
            return (Address) b.a(placesAddress);
        }
        return null;
    }

    static {
        ce.a(Address.class);
    }

    public PlacesAddress(Address address) {
        dy.a(address, "address argument is null");
        PlacesAddress a = a(address);
        a(a.b());
        n(a.c());
        c(a.d());
        d(a.e());
        e(a.f());
        f(a.g());
        g(a.h());
        h(a.i());
        i(a.j());
        k(a.l());
        l(a.m());
        m(a.n());
        this.m_additionalData = a.m_additionalData;
    }

    @OnlineNative
    PlacesAddress(PlacesAddressNative placesAddressNative) {
        a(placesAddressNative.getCity());
        b(placesAddressNative.getCountryCode());
        c(placesAddressNative.getCountryName());
        d(placesAddressNative.getCounty());
        e(placesAddressNative.getDistrict());
        f(placesAddressNative.getFloorNumber());
        g(placesAddressNative.getHouseNumber());
        h(placesAddressNative.getPostalCode());
        i(placesAddressNative.getState());
        k(placesAddressNative.getStreet());
        l(placesAddressNative.getSuiteNumberOrName());
        m(placesAddressNative.getText());
    }

    public void a(String str, String str2) {
        dy.a(str, "key argument is null");
        dy.a(!str.isEmpty(), "key argument is missing");
        this.m_additionalData.put(str, str2);
    }

    public Map<String, String> a() {
        return this.m_additionalData;
    }

    public void a(String str) {
        dy.a(str, "city argument is null");
        this.m_city = str;
    }

    public final String b() {
        return em.a(this.m_city);
    }

    public void b(String str) {
        dy.a(str, "countryCode argument is null");
        dy.a(str.matches("\\w{3}"), "countryCode argument is not 3-letters");
        this.m_countryCode = str;
    }

    private void n(String str) {
        dy.a(str, "countryCode argument is null");
        this.m_countryCode = str;
    }

    public final String c() {
        return em.a(this.m_countryCode);
    }

    public void c(String str) {
        dy.a(str, "countryName argument is null");
        this.m_country = str;
    }

    public final String d() {
        return em.a(this.m_country);
    }

    public void d(String str) {
        dy.a(str, "county argument is null");
        this.m_county = str;
    }

    public final String e() {
        return em.a(this.m_county);
    }

    public void e(String str) {
        dy.a(str, "district argument is null");
        this.m_district = str;
    }

    public final String f() {
        return em.a(this.m_district);
    }

    public void f(String str) {
        dy.a(str, "floorNumber argument is null");
        this.m_floorNumber = str;
    }

    public final String g() {
        return em.a(this.m_floorNumber);
    }

    public void g(String str) {
        dy.a(str, "houseNumber argument is null");
        this.m_house = str;
    }

    public final String h() {
        return em.a(this.m_house);
    }

    public void h(String str) {
        dy.a(str, "postalCode argument is null");
        this.m_postalCode = str;
    }

    public final String i() {
        return em.a(this.m_postalCode);
    }

    public void i(String str) {
        dy.a(str, "state argument is null");
        this.m_state = str;
    }

    public final String j() {
        return em.a(this.m_state);
    }

    public void j(String str) {
        dy.a(str, "stateCode argument is null");
        this.m_stateCode = str;
    }

    public final String k() {
        return em.a(this.m_stateCode);
    }

    public void k(String str) {
        dy.a(str, "street argument is null");
        this.m_street = str;
    }

    public final String l() {
        return em.a(this.m_street);
    }

    public void l(String str) {
        dy.a(str, "suiteNumberOrName argument is null");
        this.m_suiteNumberOrName = str;
    }

    public final String m() {
        return em.a(this.m_suiteNumberOrName);
    }

    public void m(String str) {
        dy.a(str, "text argument is null");
        this.m_text = str;
    }

    public final String n() {
        return em.a(this.m_text);
    }

    public String toString() {
        return "PlacesAddress [City: " + b() + ", CountryCode: " + c() + ", getCountryName: " + d() + ", getCounty: " + e() + ", getDistrict: " + f() + ", getFloorNumber: " + g() + ", getHouseNumber: " + h() + ", getPostalCode: " + i() + ", getState: " + j() + ", getStreet: " + l() + ", getSuiteNumberOrName: " + m() + ", getText: " + n() + d.H;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((this.m_street == null ? 0 : this.m_street.hashCode()) + (((this.m_stateCode == null ? 0 : this.m_stateCode.hashCode()) + (((this.m_state == null ? 0 : this.m_state.hashCode()) + (((this.m_postalCode == null ? 0 : this.m_postalCode.hashCode()) + (((this.m_label == null ? 0 : this.m_label.hashCode()) + (((this.m_house == null ? 0 : this.m_house.hashCode()) + (((this.m_floorNumber == null ? 0 : this.m_floorNumber.hashCode()) + (((this.m_district == null ? 0 : this.m_district.hashCode()) + (((this.m_county == null ? 0 : this.m_county.hashCode()) + (((this.m_countryCode == null ? 0 : this.m_countryCode.hashCode()) + (((this.m_country == null ? 0 : this.m_country.hashCode()) + (((this.m_city == null ? 0 : this.m_city.hashCode()) + (((this.m_additionalData == null ? 0 : this.m_additionalData.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.m_suiteNumberOrName == null) {
            i = 0;
        } else {
            i = this.m_suiteNumberOrName.hashCode();
        }
        i = (i + hashCode) * 31;
        if (this.m_text != null) {
            i2 = this.m_text.hashCode();
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
            obj = (PlacesAddress) obj;
        } else if (Address.class != obj.getClass()) {
            return false;
        } else {
            obj = a((Address) obj);
        }
        if (this.m_additionalData == null) {
            if (obj.m_additionalData != null) {
                return false;
            }
        } else if (!this.m_additionalData.equals(obj.m_additionalData)) {
            return false;
        }
        if (this.m_city == null) {
            if (!TextUtils.isEmpty(obj.m_city)) {
                return false;
            }
        } else if (!this.m_city.equals(obj.m_city)) {
            return false;
        }
        if (this.m_country == null) {
            if (!TextUtils.isEmpty(obj.m_country)) {
                return false;
            }
        } else if (!this.m_country.equals(obj.m_country)) {
            return false;
        }
        if (this.m_countryCode == null) {
            if (!TextUtils.isEmpty(obj.m_countryCode)) {
                return false;
            }
        } else if (!this.m_countryCode.equals(obj.m_countryCode)) {
            return false;
        }
        if (this.m_county == null) {
            if (!TextUtils.isEmpty(obj.m_county)) {
                return false;
            }
        } else if (!this.m_county.equals(obj.m_county)) {
            return false;
        }
        if (this.m_district == null) {
            if (!TextUtils.isEmpty(obj.m_district)) {
                return false;
            }
        } else if (!this.m_district.equals(obj.m_district)) {
            return false;
        }
        if (this.m_floorNumber == null) {
            if (!TextUtils.isEmpty(obj.m_floorNumber)) {
                return false;
            }
        } else if (!this.m_floorNumber.equals(obj.m_floorNumber)) {
            return false;
        }
        if (this.m_house == null) {
            if (!TextUtils.isEmpty(obj.m_house)) {
                return false;
            }
        } else if (!this.m_house.equals(obj.m_house)) {
            return false;
        }
        if (this.m_label == null) {
            if (!TextUtils.isEmpty(obj.m_label)) {
                return false;
            }
        } else if (!this.m_label.equals(obj.m_label)) {
            return false;
        }
        if (this.m_postalCode == null) {
            if (!TextUtils.isEmpty(obj.m_postalCode)) {
                return false;
            }
        } else if (!this.m_postalCode.equals(obj.m_postalCode)) {
            return false;
        }
        if (this.m_state == null) {
            if (!TextUtils.isEmpty(obj.m_state)) {
                return false;
            }
        } else if (!this.m_state.equals(obj.m_state)) {
            return false;
        }
        if (this.m_stateCode == null) {
            if (!TextUtils.isEmpty(obj.m_stateCode)) {
                return false;
            }
        } else if (!this.m_stateCode.equals(obj.m_stateCode)) {
            return false;
        }
        if (this.m_street == null) {
            if (!TextUtils.isEmpty(obj.m_street)) {
                return false;
            }
        } else if (!this.m_street.equals(obj.m_street)) {
            return false;
        }
        if (this.m_suiteNumberOrName == null) {
            if (!TextUtils.isEmpty(obj.m_suiteNumberOrName)) {
                return false;
            }
        } else if (!this.m_suiteNumberOrName.equals(obj.m_suiteNumberOrName)) {
            return false;
        }
        if (this.m_text == null) {
            if (TextUtils.isEmpty(obj.m_text)) {
                return true;
            }
            return false;
        } else if (this.m_text.equals(obj.m_text)) {
            return true;
        } else {
            return false;
        }
    }
}
