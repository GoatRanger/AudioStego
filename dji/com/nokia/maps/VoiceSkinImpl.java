package com.nokia.maps;

import com.here.android.mpa.guidance.VoiceSkin;
import com.here.android.mpa.guidance.VoiceSkin.OutputType;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class VoiceSkinImpl extends BaseNativeObject {
    private static k<VoiceSkin, VoiceSkinImpl> a = null;
    private static am<VoiceSkin, VoiceSkinImpl> b;

    private native void destroyNative();

    private native int native_getOutputType();

    public native String getDescription();

    public native String getGender();

    public native long getId();

    public native String getLanguage();

    public native String getLanguageCode();

    public native String getLocalDirectory();

    public native String getMarcCode();

    public native String getQuality();

    public native String getSpeaker();

    public native String getVersion();

    static {
        ce.a(VoiceSkin.class);
    }

    public static void a(k<VoiceSkin, VoiceSkinImpl> kVar, am<VoiceSkin, VoiceSkinImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static VoiceSkinImpl a(VoiceSkin voiceSkin) {
        if (a != null) {
            return (VoiceSkinImpl) a.a(voiceSkin);
        }
        return null;
    }

    static VoiceSkin a(VoiceSkinImpl voiceSkinImpl) {
        if (voiceSkinImpl != null) {
            return (VoiceSkin) b.a(voiceSkinImpl);
        }
        return null;
    }

    static List<VoiceSkin> a(List<VoiceSkinImpl> list) {
        List<VoiceSkin> arrayList = new ArrayList();
        for (VoiceSkinImpl a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    public VoiceSkinImpl() {
        this.nativeptr = 0;
    }

    @HybridPlusNative
    private VoiceSkinImpl(int i) {
        this.nativeptr = i;
    }

    public OutputType a() {
        return OutputType.values()[native_getOutputType()];
    }

    public boolean b() {
        String localDirectory = getLocalDirectory();
        if (localDirectory.length() > 0) {
            return a(new File(localDirectory.substring(0, localDirectory.lastIndexOf(d.t))));
        }
        return false;
    }

    private static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int length = listFiles.length;
                int i = 0;
                while (i < length && a(listFiles[i])) {
                    i++;
                }
            }
        }
        File file2 = new File(file.getAbsolutePath() + System.currentTimeMillis());
        if (file.renameTo(file2)) {
            return file2.delete();
        }
        return false;
    }
}
