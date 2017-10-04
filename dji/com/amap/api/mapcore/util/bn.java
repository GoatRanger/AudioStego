package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.mapcore.util.cc.a;
import com.amap.api.maps.AMap;
import java.io.IOException;

public class bn extends gc implements a {
    private cc a;
    private ce b;
    private cg c;
    private Context e;
    private Bundle f;
    private AMap g;
    private boolean h;

    public bn(cg cgVar, Context context) {
        this.f = new Bundle();
        this.h = false;
        this.c = cgVar;
        this.e = context;
    }

    public bn(cg cgVar, Context context, AMap aMap) {
        this(cgVar, context);
        this.g = aMap;
    }

    public void a() {
        if (this.c.x()) {
            this.c.a(ch.a.file_io_exception);
            return;
        }
        try {
            g();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b() {
        this.h = true;
        if (this.a != null) {
            this.a.c();
        } else {
            e();
        }
        if (this.b != null) {
            this.b.a();
        }
    }

    private String f() {
        return dj.b(this.e);
    }

    private void g() throws IOException {
        this.a = new cc(new cd(this.c.getUrl(), f(), this.c.y(), 1, this.c.z()), this.c.getUrl(), this.e, this.c);
        this.a.a((a) this);
        this.b = new ce(this.c, this.c);
        if (!this.h) {
            this.a.a();
        }
    }

    public void c() {
        this.g = null;
        if (this.f != null) {
            this.f.clear();
            this.f = null;
        }
    }

    public void d() {
        if (this.b != null) {
            this.b.b();
        }
    }
}
