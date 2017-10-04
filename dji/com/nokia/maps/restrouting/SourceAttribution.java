package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class SourceAttribution {
    @Expose
    private String attribution;
    @Expose
    private List<Supplier> supplier = new ArrayList();

    public String a() {
        return a.a(this.attribution);
    }

    public List<Supplier> b() {
        return this.supplier;
    }
}
