package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ActivityContentDetailsFavorite extends GenericJson {
    @Key
    private ResourceId resourceId;

    public ResourceId getResourceId() {
        return this.resourceId;
    }

    public ActivityContentDetailsFavorite setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ActivityContentDetailsFavorite set(String str, Object obj) {
        return (ActivityContentDetailsFavorite) super.set(str, obj);
    }

    public ActivityContentDetailsFavorite clone() {
        return (ActivityContentDetailsFavorite) super.clone();
    }
}
