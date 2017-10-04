package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.util.List;

public final class VideoConversionPings extends GenericJson {
    @Key
    private List<VideoConversionPing> pings;

    public List<VideoConversionPing> getPings() {
        return this.pings;
    }

    public VideoConversionPings setPings(List<VideoConversionPing> list) {
        this.pings = list;
        return this;
    }

    public VideoConversionPings set(String str, Object obj) {
        return (VideoConversionPings) super.set(str, obj);
    }

    public VideoConversionPings clone() {
        return (VideoConversionPings) super.clone();
    }
}
