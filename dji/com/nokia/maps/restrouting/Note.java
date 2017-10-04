package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Note {
    @Expose
    private List<AdditionalDatum> additionalData = new ArrayList();
    @Expose
    private String code;
    @Expose
    private String text;
    @Expose
    private String type;

    public String a() {
        return a.a(this.type);
    }

    public void a(String str) {
        this.type = str;
    }

    public String b() {
        return a.a(this.code);
    }

    public void b(String str) {
        this.code = str;
    }

    public String c() {
        return a.a(this.text);
    }

    public void c(String str) {
        this.text = str;
    }
}
