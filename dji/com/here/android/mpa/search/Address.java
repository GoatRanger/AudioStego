package com.here.android.mpa.search;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.nokia.maps.PlacesAddress;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.Map;

@Online
public class Address {
    private PlacesAddress a;

    public Address() {
        this.a = new PlacesAddress();
    }

    public Address(Address address) {
        this.a = new PlacesAddress(address);
    }

    private Address(PlacesAddress placesAddress) {
        this.a = placesAddress;
    }

    public Address setCity(String str) {
        this.a.a(str);
        return this;
    }

    public String getCity() {
        return this.a.b();
    }

    public Address setCountryCode(String str) {
        this.a.b(str);
        return this;
    }

    public String getCountryCode() {
        return this.a.c();
    }

    public Address setCountryName(String str) {
        this.a.c(str);
        return this;
    }

    public String getCountryName() {
        return this.a.d();
    }

    public Address setCounty(String str) {
        this.a.d(str);
        return this;
    }

    public String getCounty() {
        return this.a.e();
    }

    public Address setDistrict(String str) {
        this.a.e(str);
        return this;
    }

    public String getDistrict() {
        return this.a.f();
    }

    public Address setFloorNumber(String str) {
        this.a.f(str);
        return this;
    }

    public String getFloorNumber() {
        return this.a.g();
    }

    public Address setHouseNumber(String str) {
        this.a.g(str);
        return this;
    }

    public String getHouseNumber() {
        return this.a.h();
    }

    public Address setPostalCode(String str) {
        this.a.h(str);
        return this;
    }

    public String getPostalCode() {
        return this.a.i();
    }

    public Address setState(String str) {
        this.a.i(str);
        return this;
    }

    public String getState() {
        return this.a.j();
    }

    public Address setStateCode(String str) {
        this.a.j(str);
        return this;
    }

    public String getStateCode() {
        return this.a.k();
    }

    public Address setStreet(String str) {
        this.a.k(str);
        return this;
    }

    public String getStreet() {
        return this.a.l();
    }

    public Address setSuiteNumberOrName(String str) {
        this.a.l(str);
        return this;
    }

    public String getSuiteNumberOrName() {
        return this.a.m();
    }

    public Address setText(String str) {
        this.a.m(str);
        return this;
    }

    public String getText() {
        return this.a.n();
    }

    public Map<String, String> getAdditionalData() {
        return this.a.a();
    }

    public void addAdditionalData(String str, String str2) {
        this.a.a(str, str2);
    }

    public String toString() {
        return a(a(a(a(a(a(a(a(new StringBuilder(), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getSuiteNumberOrName()), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getHouseNumber()), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getStreet()), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getCity()), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getCounty()), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getState()), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getCountryCode()), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, getCountryName()).toString();
    }

    private StringBuilder a(StringBuilder stringBuilder, String str, String str2) {
        if (!(stringBuilder == null || str2 == null || str2.isEmpty())) {
            if (str == null) {
                str = "No label: ";
            }
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(str);
            stringBuilder.append(str2);
        }
        return stringBuilder;
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
        PlacesAddress.a(new k<Address, PlacesAddress>() {
            public PlacesAddress a(Address address) {
                return address != null ? address.a : null;
            }
        }, new am<Address, PlacesAddress>() {
            public Address a(PlacesAddress placesAddress) {
                return placesAddress != null ? new Address(placesAddress) : null;
            }
        });
    }
}
