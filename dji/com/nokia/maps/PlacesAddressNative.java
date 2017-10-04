package com.nokia.maps;

import com.here.android.mpa.search.Address;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class PlacesAddressNative extends BaseNativeObject {
    private boolean a = true;

    private native void createNative();

    private native void destroyNative();

    public native String getCity();

    public native String getCountryCode();

    public native String getCountryName();

    public native String getCounty();

    public native String getDistrict();

    public native String getFloorNumber();

    public native String getHouseNumber();

    public native String getPostalCode();

    public native String getState();

    public native String getStateCode();

    public native String getStreet();

    public native String getSuiteNumberOrName();

    public native String getText();

    public native void setCity(String str);

    public native void setCountryCode(String str);

    public native void setCountryName(String str);

    public native void setCounty(String str);

    public native void setDistrict(String str);

    public native void setFloorNumber(String str);

    public native void setHouseNumber(String str);

    public native void setPostalCode(String str);

    public native void setState(String str);

    public native void setStateCode(String str);

    public native void setStreet(String str);

    public native void setSuiteNumberOrName(String str);

    public native void setText(String str);

    @OnlineNative
    public PlacesAddressNative() {
        createNative();
    }

    @OnlineNative
    public PlacesAddressNative(Address address) {
        dy.a((Object) address, "Address argument is null");
        createNative();
        setCity(address.getCity());
        setCountryCode(address.getCountryCode());
        setCountryName(address.getCountryName());
        setCounty(address.getCounty());
        setDistrict(address.getDistrict());
        setFloorNumber(address.getFloorNumber());
        setHouseNumber(address.getHouseNumber());
        setPostalCode(address.getPostalCode());
        setState(address.getState());
        setStateCode(address.getStateCode());
        setStreet(address.getStreet());
        setSuiteNumberOrName(address.getSuiteNumberOrName());
        setText(address.getText());
    }

    @OnlineNative
    private PlacesAddressNative(int i) {
        this.nativeptr = i;
    }

    @OnlineNative
    private PlacesAddressNative(int i, boolean z) {
        this.nativeptr = i;
        this.a = z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSuiteNumberOrName() == null ? 0 : getSuiteNumberOrName().hashCode()) + (((getStreet() == null ? 0 : getStreet().hashCode()) + (((getStateCode() == null ? 0 : getStateCode().hashCode()) + (((getState() == null ? 0 : getState().hashCode()) + (((getPostalCode() == null ? 0 : getPostalCode().hashCode()) + (((getHouseNumber() == null ? 0 : getHouseNumber().hashCode()) + (((getFloorNumber() == null ? 0 : getFloorNumber().hashCode()) + (((getDistrict() == null ? 0 : getDistrict().hashCode()) + (((getCounty() == null ? 0 : getCounty().hashCode()) + (((getCountryName() == null ? 0 : getCountryName().hashCode()) + (((getCountryCode() == null ? 0 : getCountryCode().hashCode()) + (((getCity() == null ? 0 : getCity().hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (getText() != null) {
            i = getText().hashCode();
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlacesAddressNative placesAddressNative = (PlacesAddressNative) obj;
        if (getCity().equals(placesAddressNative.getCity()) && getCountryCode().equals(placesAddressNative.getCountryCode()) && getCountryName().equals(placesAddressNative.getCountryName()) && getCounty().equals(placesAddressNative.getCounty()) && getDistrict().equals(placesAddressNative.getDistrict()) && getFloorNumber().equals(placesAddressNative.getFloorNumber()) && getHouseNumber().equals(placesAddressNative.getHouseNumber()) && getPostalCode().equals(placesAddressNative.getPostalCode()) && getState().equals(placesAddressNative.getState()) && getStateCode().equals(placesAddressNative.getStateCode()) && getStreet().equals(placesAddressNative.getStreet()) && getSuiteNumberOrName().equals(placesAddressNative.getSuiteNumberOrName()) && getText().equals(placesAddressNative.getText())) {
            return true;
        }
        return false;
    }

    protected void finalize() {
        bj.e("PlacesAddress", "nativeptr=0x%X", new Object[]{Integer.valueOf(this.nativeptr)});
        if (this.a) {
            destroyNative();
        }
    }
}
