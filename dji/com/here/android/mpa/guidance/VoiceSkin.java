package com.here.android.mpa.guidance;

import com.nokia.maps.VoiceSkinImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@HybridPlus
public final class VoiceSkin {
    private VoiceSkinImpl a;

    @HybridPlus
    public enum OutputType {
        AUDIO(0),
        TEXT(1),
        TTS(2),
        NONE(3);
        
        private int a;

        private OutputType(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    private VoiceSkin(VoiceSkinImpl voiceSkinImpl) {
        this.a = voiceSkinImpl;
    }

    public boolean delete() {
        return this.a.b();
    }

    public String getDescription() {
        return this.a.getDescription();
    }

    public String getGender() {
        return this.a.getGender();
    }

    public long getId() {
        return this.a.getId();
    }

    public String getLanguage() {
        return this.a.getLanguage();
    }

    public String getMarcCode() {
        return this.a.getMarcCode();
    }

    public String getLanguageCode() {
        return this.a.getLanguageCode();
    }

    public OutputType getOutputType() {
        return this.a.a();
    }

    public String getSpeaker() {
        return this.a.getSpeaker();
    }

    public String getVersion() {
        return this.a.getVersion();
    }

    public String getQuality() {
        return this.a.getQuality();
    }

    static {
        VoiceSkinImpl.a(new k<VoiceSkin, VoiceSkinImpl>() {
            public VoiceSkinImpl a(VoiceSkin voiceSkin) {
                return voiceSkin.a;
            }
        }, new am<VoiceSkin, VoiceSkinImpl>() {
            public VoiceSkin a(VoiceSkinImpl voiceSkinImpl) {
                return new VoiceSkin(voiceSkinImpl);
            }
        });
    }
}
