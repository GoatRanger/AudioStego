package com.here.android.mpa.guidance;

import com.nokia.maps.VoiceCatalogImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class VoiceCatalog {
    public static final int ID_BEEPS_VIBRATE = 1003;
    private static volatile VoiceCatalog b;
    private static Object c = new Object();
    VoiceCatalogImpl a;

    @HybridPlus
    public enum Error {
        NONE(0),
        UNKNOWN(1),
        NOT_ENOUGH_DISK_SPACE(3);
        
        private final int a;

        private Error(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @HybridPlus
    public interface OnDownloadDoneListener {
        void onDownloadDone(Error error);
    }

    @HybridPlus
    public interface OnProgressListener {
        void onProgress(int i);
    }

    public static VoiceCatalog getInstance() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new VoiceCatalog();
                }
            }
        }
        return b;
    }

    private VoiceCatalog() {
        this.a = VoiceCatalogImpl.a();
    }

    private VoiceCatalog(VoiceCatalogImpl voiceCatalogImpl) {
        this.a = voiceCatalogImpl;
    }

    public boolean deleteVoiceSkin(long j) {
        return this.a.c(j);
    }

    public boolean deleteVoiceSkin(VoiceSkin voiceSkin) {
        return this.a.a(voiceSkin);
    }

    public boolean downloadCatalog(OnDownloadDoneListener onDownloadDoneListener) {
        return this.a.a(onDownloadDoneListener);
    }

    public boolean downloadVoice(long j, OnDownloadDoneListener onDownloadDoneListener) {
        return this.a.a(j, onDownloadDoneListener);
    }

    public List<VoicePackage> getCatalogList() {
        return this.a.c();
    }

    public boolean isLocalCatalogAvailable() {
        return this.a.isLocalCatalogAvailable();
    }

    public VoiceSkin getLocalVoiceSkin(long j) {
        return this.a.b(j);
    }

    public List<VoiceSkin> getLocalVoiceSkins() {
        return this.a.b();
    }

    public boolean isDownloading() {
        return this.a.e();
    }

    public boolean isLocalVoiceSkin(long j) {
        return this.a.a(j);
    }

    public void refreshVoiceSkins() {
        this.a.d();
    }

    public void cancel() {
        this.a.cancel();
    }

    public void setOnProgressEventListener(OnProgressListener onProgressListener) {
        this.a.a(onProgressListener);
    }

    static {
        VoiceCatalogImpl.a(new k<VoiceCatalog, VoiceCatalogImpl>() {
            public VoiceCatalogImpl a(VoiceCatalog voiceCatalog) {
                return voiceCatalog.a;
            }
        }, new am<VoiceCatalog, VoiceCatalogImpl>() {
            public VoiceCatalog a(VoiceCatalogImpl voiceCatalogImpl) {
                if (voiceCatalogImpl != null) {
                    return new VoiceCatalog(voiceCatalogImpl);
                }
                return null;
            }
        });
    }
}
