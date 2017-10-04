package com.nokia.maps;

import com.here.android.mpa.common.Image;
import com.here.android.mpa.routing.Signpost;
import com.here.android.mpa.routing.Signpost.LocalizedLabel;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public class SignpostImpl extends BaseNativeObject {
    private static k<Signpost, SignpostImpl> b = null;
    private static am<Signpost, SignpostImpl> c;
    private cq a;

    private native void destroySignpostNative();

    private native List<LocalizedLabelImpl> getExitDirectionsNative();

    private native ImageImpl getExitIcon();

    public native int getBackgroundColor();

    public native String getExitNumber();

    public native String getExitText();

    public native int getForegroundColor();

    static {
        ce.a(Signpost.class);
    }

    public static void a(k<Signpost, SignpostImpl> kVar, am<Signpost, SignpostImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static Signpost a(SignpostImpl signpostImpl) {
        if (signpostImpl != null) {
            return (Signpost) c.a(signpostImpl);
        }
        return null;
    }

    private SignpostImpl() {
        this.a = new cq(SignpostImpl.class.getName());
        this.nativeptr = 0;
    }

    @OnlineNative
    private SignpostImpl(int i) {
        this.a = new cq(SignpostImpl.class.getName());
        this.nativeptr = i;
    }

    public Image a() {
        return ImageImpl.a(getExitIcon());
    }

    public List<LocalizedLabel> b() {
        return LocalizedLabelImpl.a(getExitDirectionsNative());
    }

    protected void finalize() {
        destroySignpostNative();
    }
}
