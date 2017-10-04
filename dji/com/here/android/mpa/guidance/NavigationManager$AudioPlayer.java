package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class NavigationManager$AudioPlayer {
    @HybridPlus
    public static final float DEFAULT_AUDIO_VOLUME = -1.0f;
    final /* synthetic */ NavigationManager a;

    @HybridPlus
    public NavigationManager$AudioPlayer setStreamId(int i) {
        NavigationManager.a(this.a).c().a(i);
        return this;
    }

    @HybridPlus
    public int getStreamId() {
        return NavigationManager.a(this.a).c().b();
    }

    @HybridPlus
    public NavigationManager$AudioPlayer setVolume(float f) {
        NavigationManager.a(this.a).c().a(f);
        return this;
    }

    @HybridPlus
    public float getVolume() {
        return NavigationManager.a(this.a).c().c();
    }

    @HybridPlus
    public void stop() {
        NavigationManager.a(this.a).c().d();
    }

    @HybridPlus
    public void setDelegate(AudioPlayerDelegate audioPlayerDelegate) {
        NavigationManager.a(this.a).c().a(audioPlayerDelegate);
    }

    private NavigationManager$AudioPlayer(NavigationManager navigationManager) {
        this.a = navigationManager;
    }
}
