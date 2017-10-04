package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
    @Expose
    private String href;
    @Expose
    private List<SupplierNote> note = new ArrayList();
    @Expose
    private String title;

    public String a() {
        return a.a(this.title);
    }

    public String b() {
        return a.a(this.href);
    }

    public List<SupplierNote> c() {
        return this.note;
    }
}
