package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;

public class SupplierNote {
    @Expose
    private String href;
    @Expose
    private String hrefText;
    @Expose
    private String text;
    @Expose
    private String type;

    public String a() {
        return this.type;
    }

    public String b() {
        return this.text;
    }

    public String c() {
        return this.href;
    }

    public String d() {
        return this.hrefText;
    }
}
