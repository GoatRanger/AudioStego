package com.nokia.maps;

import com.here.android.mpa.guidance.NavigationManager;
import com.here.android.mpa.guidance.VoicePackage;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

@HybridPlus
public class VoicePackageImpl extends BaseNativeObject {
    private static k<VoicePackage, VoicePackageImpl> a = null;
    private static am<VoicePackage, VoicePackageImpl> b;

    private native String[] getCustomAttributes_native();

    public native String getBCP47Code();

    public native float getContentSize();

    public native float getDownloadSize();

    public native String getGender();

    public native long getId();

    public native String getLocalizedGender();

    public native String getLocalizedLanguage();

    public native String getLocalizedQuality();

    public native String getLocalizedType();

    public native String getMarcCode();

    public native String getName();

    public native String getQuality();

    public native int getTravelModeNative();

    public native String getVersion();

    public native boolean isLocal();

    public native boolean isTts();

    static {
        ce.a(VoicePackage.class);
    }

    public static void a(k<VoicePackage, VoicePackageImpl> kVar, am<VoicePackage, VoicePackageImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static VoicePackage a(VoicePackageImpl voicePackageImpl) {
        if (voicePackageImpl != null) {
            return (VoicePackage) b.a(voicePackageImpl);
        }
        return null;
    }

    static List<VoicePackage> a(List<VoicePackageImpl> list) {
        List<VoicePackage> arrayList = new ArrayList();
        for (VoicePackageImpl a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    @HybridPlusNative
    private VoicePackageImpl(int i) {
        this.nativeptr = i;
    }

    public boolean a() {
        int isTtsLanguageAvailable = NavigationManager.getInstance().isTtsLanguageAvailable(new Locale(getBCP47Code()));
        if (isTtsLanguageAvailable == 0 || isTtsLanguageAvailable == 1 || isTtsLanguageAvailable == 2) {
            return true;
        }
        return false;
    }

    public Hashtable<String, String> b() {
        String[] customAttributes_native = getCustomAttributes_native();
        Hashtable<String, String> hashtable = new Hashtable();
        if (customAttributes_native == null || customAttributes_native.length == 0) {
            return hashtable;
        }
        for (int i = 0; i < customAttributes_native.length; i += 2) {
            hashtable.put(customAttributes_native[i], customAttributes_native[i + 1]);
        }
        return hashtable;
    }
}
