package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class CdnSettings extends GenericJson {
    @Key
    private String format;
    @Key
    private IngestionInfo ingestionInfo;
    @Key
    private String ingestionType;

    public String getFormat() {
        return this.format;
    }

    public CdnSettings setFormat(String str) {
        this.format = str;
        return this;
    }

    public IngestionInfo getIngestionInfo() {
        return this.ingestionInfo;
    }

    public CdnSettings setIngestionInfo(IngestionInfo ingestionInfo) {
        this.ingestionInfo = ingestionInfo;
        return this;
    }

    public String getIngestionType() {
        return this.ingestionType;
    }

    public CdnSettings setIngestionType(String str) {
        this.ingestionType = str;
        return this;
    }

    public CdnSettings set(String str, Object obj) {
        return (CdnSettings) super.set(str, obj);
    }

    public CdnSettings clone() {
        return (CdnSettings) super.clone();
    }
}
