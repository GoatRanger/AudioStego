package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ActivityContentDetailsLike extends GenericJson {
    @Key
    private ResourceId resourceId;

    public ResourceId getResourceId() {
        return this.resourceId;
    }

    public ActivityContentDetailsLike setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ActivityContentDetailsLike set(String str, Object obj) {
        return (ActivityContentDetailsLike) super.set(str, obj);
    }

    public ActivityContentDetailsLike clone() {
        return (ActivityContentDetailsLike) super.clone();
    }
}
