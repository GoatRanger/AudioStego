package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;
import com.google.api.services.youtube.model.LiveBroadcast;
import java.math.BigInteger;

public class YouTube$LiveBroadcasts$Control extends YouTubeRequest<LiveBroadcast> {
    private static final String REST_PATH = "liveBroadcasts/control";
    @Key
    private Boolean displaySlate;
    @Key
    private String id;
    @Key
    private BigInteger offsetTimeMs;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ LiveBroadcasts this$1;
    @Key
    private DateTime walltime;

    protected YouTube$LiveBroadcasts$Control(LiveBroadcasts liveBroadcasts, String str, String str2) {
        this.this$1 = liveBroadcasts;
        super(liveBroadcasts.this$0, HttpMethods.POST, REST_PATH, null, LiveBroadcast.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
        this.part = (String) Preconditions.checkNotNull(str2, "Required parameter part must be specified.");
    }

    public YouTube$LiveBroadcasts$Control setAlt(String str) {
        return (YouTube$LiveBroadcasts$Control) super.setAlt(str);
    }

    public YouTube$LiveBroadcasts$Control setFields(String str) {
        return (YouTube$LiveBroadcasts$Control) super.setFields(str);
    }

    public YouTube$LiveBroadcasts$Control setKey(String str) {
        return (YouTube$LiveBroadcasts$Control) super.setKey(str);
    }

    public YouTube$LiveBroadcasts$Control setOauthToken(String str) {
        return (YouTube$LiveBroadcasts$Control) super.setOauthToken(str);
    }

    public YouTube$LiveBroadcasts$Control setPrettyPrint(Boolean bool) {
        return (YouTube$LiveBroadcasts$Control) super.setPrettyPrint(bool);
    }

    public YouTube$LiveBroadcasts$Control setQuotaUser(String str) {
        return (YouTube$LiveBroadcasts$Control) super.setQuotaUser(str);
    }

    public YouTube$LiveBroadcasts$Control setUserIp(String str) {
        return (YouTube$LiveBroadcasts$Control) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$LiveBroadcasts$Control setId(String str) {
        this.id = str;
        return this;
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveBroadcasts$Control setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveBroadcasts$Control setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public Boolean getDisplaySlate() {
        return this.displaySlate;
    }

    public YouTube$LiveBroadcasts$Control setDisplaySlate(Boolean bool) {
        this.displaySlate = bool;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveBroadcasts$Control setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public BigInteger getOffsetTimeMs() {
        return this.offsetTimeMs;
    }

    public YouTube$LiveBroadcasts$Control setOffsetTimeMs(BigInteger bigInteger) {
        this.offsetTimeMs = bigInteger;
        return this;
    }

    public DateTime getWalltime() {
        return this.walltime;
    }

    public YouTube$LiveBroadcasts$Control setWalltime(DateTime dateTime) {
        this.walltime = dateTime;
        return this;
    }

    public YouTube$LiveBroadcasts$Control set(String str, Object obj) {
        return (YouTube$LiveBroadcasts$Control) super.set(str, obj);
    }
}
