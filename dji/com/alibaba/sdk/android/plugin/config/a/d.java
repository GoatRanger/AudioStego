package com.alibaba.sdk.android.plugin.config.a;

import com.alibaba.sdk.android.config.PropertyChangeListener;

final class d implements Runnable {
    final /* synthetic */ PropertyChangeListener[] a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ b e;

    d(b bVar, PropertyChangeListener[] propertyChangeListenerArr, String str, String str2, String str3) {
        this.e = bVar;
        this.a = propertyChangeListenerArr;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public final void run() {
        for (PropertyChangeListener propertyChanged : this.a) {
            try {
                propertyChanged.propertyChanged(this.b, this.c, this.d);
            } catch (Exception e) {
            }
        }
    }
}
