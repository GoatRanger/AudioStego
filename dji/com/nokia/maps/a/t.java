package com.nokia.maps.a;

import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ApplicationContext$c;
import com.nokia.maps.annotation.InternalNative;
import com.nokia.maps.ez;
import java.util.Stack;

public abstract class t implements ApplicationContext$c {
    private Stack<Integer> a = new Stack();

    protected abstract void c();

    protected abstract void d();

    public t(int[] iArr) {
        for (int valueOf : iArr) {
            this.a.push(Integer.valueOf(valueOf));
        }
    }

    public void e() {
        if (this.a.isEmpty()) {
            d();
        } else {
            ApplicationContext.b().check(((Integer) this.a.pop()).intValue(), this);
        }
    }

    @InternalNative
    public void a() {
        ez.a(new Runnable(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e();
            }
        });
    }

    @InternalNative
    public void b() {
        ez.a(new Runnable(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        });
    }
}
