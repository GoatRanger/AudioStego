package com.alibaba.sdk.android.config;

public interface PropertyChangeListener {
    public static final String PROPETY_NAME_KEY = "property_name";

    void propertyChanged(String str, String str2, String str3);
}
