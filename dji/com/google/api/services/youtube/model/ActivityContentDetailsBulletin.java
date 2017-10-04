package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ActivityContentDetailsBulletin extends GenericJson {
    @Key
    private ResourceId resourceId;

    public ResourceId getResourceId() {
        return this.resourceId;
    }

    public ActivityContentDetailsBulletin setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ActivityContentDetailsBulletin set(String str, Object obj) {
        return (ActivityContentDetailsBulletin) super.set(str, obj);
    }

    public ActivityContentDetailsBulletin clone() {
        return (ActivityContentDetailsBulletin) super.clone();
    }
}
