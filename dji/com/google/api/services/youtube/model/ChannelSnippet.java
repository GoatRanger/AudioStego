package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

public final class ChannelSnippet extends GenericJson {
    @Key
    private String description;
    @Key
    private DateTime publishedAt;
    @Key
    private ThumbnailDetails thumbnails;
    @Key
    private String title;

    public String getDescription() {
        return this.description;
    }

    public ChannelSnippet setDescription(String str) {
        this.description = str;
        return this;
    }

    public DateTime getPublishedAt() {
        return this.publishedAt;
    }

    public ChannelSnippet setPublishedAt(DateTime dateTime) {
        this.publishedAt = dateTime;
        return this;
    }

    public ThumbnailDetails getThumbnails() {
        return this.thumbnails;
    }

    public ChannelSnippet setThumbnails(ThumbnailDetails thumbnailDetails) {
        this.thumbnails = thumbnailDetails;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public ChannelSnippet setTitle(String str) {
        this.title = str;
        return this;
    }

    public ChannelSnippet set(String str, Object obj) {
        return (ChannelSnippet) super.set(str, obj);
    }

    public ChannelSnippet clone() {
        return (ChannelSnippet) super.clone();
    }
}
