package com.nokia.maps;

import com.here.android.mpa.routing.Signpost.LocalizedLabel;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class LocalizedLabelImpl extends BaseNativeObject {
    private static am<LocalizedLabel, LocalizedLabelImpl> a;

    private native void destroyNative();

    public native String getLanguage();

    public native String getRouteDirection();

    public native String getRouteName();

    public native String getText();

    static {
        ce.a(LocalizedLabel.class);
    }

    public static void a(am<LocalizedLabel, LocalizedLabelImpl> amVar) {
        a = amVar;
    }

    static LocalizedLabel a(LocalizedLabelImpl localizedLabelImpl) {
        if (localizedLabelImpl != null) {
            return (LocalizedLabel) a.a(localizedLabelImpl);
        }
        return null;
    }

    static List<LocalizedLabel> a(List<LocalizedLabelImpl> list) {
        List<LocalizedLabel> arrayList = new ArrayList();
        for (LocalizedLabelImpl a : list) {
            LocalizedLabel a2 = a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    @OnlineNative
    private LocalizedLabelImpl(int i) {
        this.nativeptr = i;
    }

    public String toString() {
        return a(a(a(a(new StringBuilder(), "Language: ", getLanguage()), "Text: ", getText()), "Route name: ", getRouteName()), "Route direction: ", getRouteDirection()).toString();
    }

    private StringBuilder a(StringBuilder stringBuilder, String str, String str2) {
        if (str2 != null) {
            stringBuilder.append(str);
            stringBuilder.append(str2);
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }
}
