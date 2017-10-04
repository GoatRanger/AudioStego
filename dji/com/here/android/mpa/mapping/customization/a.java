package com.here.android.mpa.mapping.customization;

import com.nokia.maps.CustomizableFontStyleImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.k;

@Internal
public class a {
    private CustomizableFontStyleImpl a;

    private a() {
    }

    private a(CustomizableFontStyleImpl customizableFontStyleImpl) {
        this.a = customizableFontStyleImpl;
    }

    static {
        CustomizableFontStyleImpl.a(new k<a, CustomizableFontStyleImpl>() {
            public CustomizableFontStyleImpl a(a aVar) {
                return aVar.a;
            }
        }, new am<a, CustomizableFontStyleImpl>() {
            public a a(CustomizableFontStyleImpl customizableFontStyleImpl) {
                if (customizableFontStyleImpl != null) {
                    return new a(customizableFontStyleImpl);
                }
                return null;
            }
        });
    }
}
