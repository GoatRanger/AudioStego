package com.here.android.mpa.venues3d;

import com.here.android.mpa.search.Address;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.List;

public final class Content extends BaseNativeObject {
    private Address a = null;
    private List<String> b = null;

    @HybridPlusNative
    private native Address getAddressNative();

    @HybridPlusNative
    private native String getCategoryIdNative();

    @HybridPlusNative
    private native String getContentIdNative();

    @HybridPlusNative
    private native List<String> getSearchTagsNative();

    @HybridPlus
    public native String getName();

    @HybridPlus
    public native String getParentCategoryId();

    @HybridPlus
    public native String getPhoneNumber();

    @HybridPlus
    public native String getPlaceCategoryId();

    @HybridPlus
    public native String getUniqueVenueId();

    @HybridPlus
    public native String getWebsite();

    @HybridPlusNative
    private Content(int i) {
        this.nativeptr = i;
    }

    @HybridPlus
    public Address getAddress() {
        if (this.a == null) {
            this.a = getAddressNative();
        }
        return this.a;
    }

    @HybridPlus
    public List<String> getSearchTags() {
        if (this.b == null) {
            this.b = getSearchTagsNative();
        }
        return this.b;
    }

    @HybridPlus
    public String getContentId() {
        return getContentIdNative();
    }
}
