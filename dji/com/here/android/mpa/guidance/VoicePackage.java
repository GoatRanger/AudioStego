package com.here.android.mpa.guidance;

import com.nokia.maps.VoicePackageImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Hashtable;

@HybridPlus
public final class VoicePackage {
    private VoicePackageImpl a;

    @HybridPlus
    public enum Gender {
        FEMALE,
        MALE
    }

    @HybridPlus
    public enum TravelMode {
        WALK_DRIVE,
        DRIVE,
        WALK,
        UNKNOWN
    }

    private VoicePackage(VoicePackageImpl voicePackageImpl) {
        this.a = voicePackageImpl;
    }

    public float getContentSize() {
        return this.a.getContentSize();
    }

    public Hashtable<String, String> getCustomAttributes() {
        return this.a.b();
    }

    public float getDownloadSize() {
        return this.a.getDownloadSize();
    }

    public Gender getGender() {
        return a(this.a.getGender());
    }

    public String getLocalizedGender() {
        return this.a.getLocalizedGender();
    }

    public long getId() {
        return this.a.getId();
    }

    public String getMarcCode() {
        return this.a.getMarcCode();
    }

    public String getBCP47Code() {
        return this.a.getBCP47Code();
    }

    public String getLocalizedLanguage() {
        return this.a.getLocalizedLanguage();
    }

    public String getName() {
        return this.a.getName();
    }

    public String getVersion() {
        return this.a.getVersion();
    }

    public boolean isLocal() {
        return this.a.isLocal();
    }

    public boolean isTts() {
        return this.a.isTts();
    }

    public boolean isTtsLanguageAvailable() {
        return this.a.a();
    }

    public String getQuality() {
        return this.a.getQuality();
    }

    public String getLocalizedQuality() {
        return this.a.getLocalizedQuality();
    }

    public String getLocalizedType() {
        return this.a.getLocalizedType();
    }

    public TravelMode getTravelMode() {
        return a(this.a.getTravelModeNative());
    }

    private static TravelMode a(int i) {
        switch (i) {
            case 1:
                return TravelMode.WALK_DRIVE;
            case 2:
                return TravelMode.DRIVE;
            case 3:
                return TravelMode.WALK;
            default:
                return TravelMode.UNKNOWN;
        }
    }

    private static Gender a(String str) {
        if (str.compareToIgnoreCase("f") == 0) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    static {
        VoicePackageImpl.a(new k<VoicePackage, VoicePackageImpl>() {
            public VoicePackageImpl a(VoicePackage voicePackage) {
                return voicePackage.a;
            }
        }, new am<VoicePackage, VoicePackageImpl>() {
            public VoicePackage a(VoicePackageImpl voicePackageImpl) {
                if (voicePackageImpl != null) {
                    return new VoicePackage(voicePackageImpl);
                }
                return null;
            }
        });
    }
}
