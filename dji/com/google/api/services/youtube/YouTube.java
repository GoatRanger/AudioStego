package com.google.api.services.youtube;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.model.Activity;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelBannerResource;
import com.google.api.services.youtube.model.ChannelSection;
import com.google.api.services.youtube.model.InvideoBranding;
import com.google.api.services.youtube.model.LiveBroadcast;
import com.google.api.services.youtube.model.LiveStream;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.Video;
import java.io.IOException;

public class YouTube extends AbstractGoogleJsonClient {
    public static final String DEFAULT_BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String DEFAULT_ROOT_URL = "https://www.googleapis.com/";
    public static final String DEFAULT_SERVICE_PATH = "youtube/v3/";

    public class Activities {
        public Insert insert(String str, Activity activity) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, activity);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }
    }

    public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {
        public Builder(HttpTransport httpTransport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
            super(httpTransport, jsonFactory, YouTube.DEFAULT_ROOT_URL, YouTube.DEFAULT_SERVICE_PATH, httpRequestInitializer, false);
        }

        public YouTube build() {
            return new YouTube(this);
        }

        public Builder setRootUrl(String str) {
            return (Builder) super.setRootUrl(str);
        }

        public Builder setServicePath(String str) {
            return (Builder) super.setServicePath(str);
        }

        public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
        }

        public Builder setApplicationName(String str) {
            return (Builder) super.setApplicationName(str);
        }

        public Builder setSuppressPatternChecks(boolean z) {
            return (Builder) super.setSuppressPatternChecks(z);
        }

        public Builder setSuppressRequiredParameterChecks(boolean z) {
            return (Builder) super.setSuppressRequiredParameterChecks(z);
        }

        public Builder setSuppressAllChecks(boolean z) {
            return (Builder) super.setSuppressAllChecks(z);
        }

        public Builder setYouTubeRequestInitializer(YouTubeRequestInitializer youTubeRequestInitializer) {
            return (Builder) super.setGoogleClientRequestInitializer((GoogleClientRequestInitializer) youTubeRequestInitializer);
        }

        public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer googleClientRequestInitializer) {
            return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
        }
    }

    public class ChannelBanners {
        public Insert insert(ChannelBannerResource channelBannerResource) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, channelBannerResource);
            YouTube.this.initialize(insert);
            return insert;
        }

        public Insert insert(ChannelBannerResource channelBannerResource, AbstractInputStreamContent abstractInputStreamContent) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, channelBannerResource, abstractInputStreamContent);
            YouTube.this.initialize(insert);
            return insert;
        }
    }

    public class ChannelSections {
        public Delete delete(String str) throws IOException {
            AbstractGoogleClientRequest delete = new Delete(this, str);
            YouTube.this.initialize(delete);
            return delete;
        }

        public Insert insert(String str, ChannelSection channelSection) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, channelSection);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }

        public Update update(String str, ChannelSection channelSection) throws IOException {
            AbstractGoogleClientRequest update = new Update(this, str, channelSection);
            YouTube.this.initialize(update);
            return update;
        }
    }

    public class Channels {
        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }

        public Update update(String str, Channel channel) throws IOException {
            AbstractGoogleClientRequest update = new Update(this, str, channel);
            YouTube.this.initialize(update);
            return update;
        }
    }

    public class GuideCategories {
        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }
    }

    public class I18nLanguages {
        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }
    }

    public class I18nRegions {
        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }
    }

    public class LiveBroadcasts {
        public Bind bind(String str, String str2) throws IOException {
            AbstractGoogleClientRequest bind = new Bind(this, str, str2);
            YouTube.this.initialize(bind);
            return bind;
        }

        public Control control(String str, String str2) throws IOException {
            AbstractGoogleClientRequest control = new Control(this, str, str2);
            YouTube.this.initialize(control);
            return control;
        }

        public Delete delete(String str) throws IOException {
            AbstractGoogleClientRequest delete = new Delete(this, str);
            YouTube.this.initialize(delete);
            return delete;
        }

        public Insert insert(String str, LiveBroadcast liveBroadcast) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, liveBroadcast);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }

        public Transition transition(String str, String str2, String str3) throws IOException {
            AbstractGoogleClientRequest transition = new Transition(this, str, str2, str3);
            YouTube.this.initialize(transition);
            return transition;
        }

        public Update update(String str, LiveBroadcast liveBroadcast) throws IOException {
            AbstractGoogleClientRequest update = new Update(this, str, liveBroadcast);
            YouTube.this.initialize(update);
            return update;
        }
    }

    public class LiveStreams {
        public Delete delete(String str) throws IOException {
            AbstractGoogleClientRequest delete = new Delete(this, str);
            YouTube.this.initialize(delete);
            return delete;
        }

        public Insert insert(String str, LiveStream liveStream) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, liveStream);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }

        public Update update(String str, LiveStream liveStream) throws IOException {
            AbstractGoogleClientRequest update = new Update(this, str, liveStream);
            YouTube.this.initialize(update);
            return update;
        }
    }

    public class PlaylistItems {
        public Delete delete(String str) throws IOException {
            AbstractGoogleClientRequest delete = new Delete(this, str);
            YouTube.this.initialize(delete);
            return delete;
        }

        public Insert insert(String str, PlaylistItem playlistItem) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, playlistItem);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }

        public Update update(String str, PlaylistItem playlistItem) throws IOException {
            AbstractGoogleClientRequest update = new Update(this, str, playlistItem);
            YouTube.this.initialize(update);
            return update;
        }
    }

    public class Playlists {
        public Delete delete(String str) throws IOException {
            AbstractGoogleClientRequest delete = new Delete(this, str);
            YouTube.this.initialize(delete);
            return delete;
        }

        public Insert insert(String str, Playlist playlist) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, playlist);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }

        public Update update(String str, Playlist playlist) throws IOException {
            AbstractGoogleClientRequest update = new Update(this, str, playlist);
            YouTube.this.initialize(update);
            return update;
        }
    }

    public class Search {
        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }
    }

    public class Subscriptions {
        public Delete delete(String str) throws IOException {
            AbstractGoogleClientRequest delete = new Delete(this, str);
            YouTube.this.initialize(delete);
            return delete;
        }

        public Insert insert(String str, Subscription subscription) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, subscription);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }
    }

    public class Thumbnails {
        public Set set(String str) throws IOException {
            AbstractGoogleClientRequest set = new Set(this, str);
            YouTube.this.initialize(set);
            return set;
        }

        public Set set(String str, AbstractInputStreamContent abstractInputStreamContent) throws IOException {
            AbstractGoogleClientRequest set = new Set(this, str, abstractInputStreamContent);
            YouTube.this.initialize(set);
            return set;
        }
    }

    public class VideoCategories {
        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }
    }

    public class Videos {
        public Delete delete(String str) throws IOException {
            AbstractGoogleClientRequest delete = new Delete(this, str);
            YouTube.this.initialize(delete);
            return delete;
        }

        public GetRating getRating(String str) throws IOException {
            AbstractGoogleClientRequest getRating = new GetRating(this, str);
            YouTube.this.initialize(getRating);
            return getRating;
        }

        public Insert insert(String str, Video video) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, video);
            YouTube.this.initialize(insert);
            return insert;
        }

        public Insert insert(String str, Video video, AbstractInputStreamContent abstractInputStreamContent) throws IOException {
            AbstractGoogleClientRequest insert = new Insert(this, str, video, abstractInputStreamContent);
            YouTube.this.initialize(insert);
            return insert;
        }

        public List list(String str) throws IOException {
            AbstractGoogleClientRequest list = new List(this, str);
            YouTube.this.initialize(list);
            return list;
        }

        public Rate rate(String str, String str2) throws IOException {
            AbstractGoogleClientRequest rate = new Rate(this, str, str2);
            YouTube.this.initialize(rate);
            return rate;
        }

        public Update update(String str, Video video) throws IOException {
            AbstractGoogleClientRequest update = new Update(this, str, video);
            YouTube.this.initialize(update);
            return update;
        }
    }

    public class Watermarks {
        public Set set(String str, InvideoBranding invideoBranding) throws IOException {
            AbstractGoogleClientRequest set = new Set(this, str, invideoBranding);
            YouTube.this.initialize(set);
            return set;
        }

        public Set set(String str, InvideoBranding invideoBranding, AbstractInputStreamContent abstractInputStreamContent) throws IOException {
            AbstractGoogleClientRequest set = new Set(this, str, invideoBranding, abstractInputStreamContent);
            YouTube.this.initialize(set);
            return set;
        }

        public Unset unset(String str) throws IOException {
            AbstractGoogleClientRequest unset = new Unset(this, str);
            YouTube.this.initialize(unset);
            return unset;
        }
    }

    static {
        boolean z = GoogleUtils.MAJOR_VERSION.intValue() == 1 && GoogleUtils.MINOR_VERSION.intValue() >= 15;
        Preconditions.checkState(z, "You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.18.0-rc of the YouTube Data API library.", GoogleUtils.VERSION);
    }

    public YouTube(HttpTransport httpTransport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
        this(new Builder(httpTransport, jsonFactory, httpRequestInitializer));
    }

    YouTube(Builder builder) {
        super(builder);
    }

    protected void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
        super.initialize(abstractGoogleClientRequest);
    }

    public Activities activities() {
        return new Activities();
    }

    public ChannelBanners channelBanners() {
        return new ChannelBanners();
    }

    public ChannelSections channelSections() {
        return new ChannelSections();
    }

    public Channels channels() {
        return new Channels();
    }

    public GuideCategories guideCategories() {
        return new GuideCategories();
    }

    public I18nLanguages i18nLanguages() {
        return new I18nLanguages();
    }

    public I18nRegions i18nRegions() {
        return new I18nRegions();
    }

    public LiveBroadcasts liveBroadcasts() {
        return new LiveBroadcasts();
    }

    public LiveStreams liveStreams() {
        return new LiveStreams();
    }

    public PlaylistItems playlistItems() {
        return new PlaylistItems();
    }

    public Playlists playlists() {
        return new Playlists();
    }

    public Search search() {
        return new Search();
    }

    public Subscriptions subscriptions() {
        return new Subscriptions();
    }

    public Thumbnails thumbnails() {
        return new Thumbnails();
    }

    public VideoCategories videoCategories() {
        return new VideoCategories();
    }

    public Videos videos() {
        return new Videos();
    }

    public Watermarks watermarks() {
        return new Watermarks();
    }
}
