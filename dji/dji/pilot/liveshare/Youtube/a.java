package dji.pilot.liveshare.Youtube;

import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.Scopes;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Builder;
import com.google.api.services.youtube.YouTube.LiveBroadcasts.Bind;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.CdnSettings;
import com.google.api.services.youtube.model.LiveBroadcast;
import com.google.api.services.youtube.model.LiveBroadcastContentDetails;
import com.google.api.services.youtube.model.LiveBroadcastSnippet;
import com.google.api.services.youtube.model.LiveBroadcastStatus;
import com.google.api.services.youtube.model.LiveStream;
import com.google.api.services.youtube.model.LiveStreamSnippet;
import com.google.api.services.youtube.model.MonitorStreamInfo;
import com.here.odnp.config.OdnpConfigStatic;
import dji.pilot2.mine.b.e;
import dji.pilot2.share.d.d;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a {
    public static final String APP_NAME = "DJIPilot";
    private static final int MSG_CREATE_STREAMING_FINISHED = 0;
    private static final int MSG_CREATE_STREAMING_START = 1;
    private static final String[] SCOPES = new String[]{Scopes.PROFILE, YouTubeScopes.YOUTUBE};
    private static LiveBroadcast returnedBroadcast;
    private static LiveStream returnedStream;
    private static YouTube yt;
    String broadcastId;
    Credential credential;
    private String description;
    private String ingestionAddress;
    final JsonFactory jsonFactory = new GsonFactory();
    String mEmail = null;
    private String privacy;
    private String shareLink;
    private int stage = -1;
    private String title;
    final HttpTransport transport = AndroidHttp.newCompatibleTransport();
    private String watchUrl = null;

    private static final class a {
        private static final a mInstance = new a();

        private a() {
        }
    }

    public static a getInstance() {
        return a.mInstance;
    }

    protected int createYTBroadcast() {
        try {
            this.credential = d.getInstance().a().c();
            yt = new Builder(this.transport, this.jsonFactory, this.credential).setApplicationName(APP_NAME).build();
            LiveBroadcastSnippet liveBroadcastSnippet = new LiveBroadcastSnippet();
            liveBroadcastSnippet.setTitle(this.title);
            liveBroadcastSnippet.setDescription(this.description);
            liveBroadcastSnippet.setScheduledStartTime(new DateTime(new Date()));
            LiveBroadcastContentDetails liveBroadcastContentDetails = new LiveBroadcastContentDetails();
            MonitorStreamInfo monitorStreamInfo = new MonitorStreamInfo();
            monitorStreamInfo.setEnableMonitorStream(Boolean.valueOf(false));
            monitorStreamInfo.setBroadcastStreamDelayMs(Long.valueOf(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL));
            liveBroadcastContentDetails.setMonitorStream(monitorStreamInfo);
            LiveBroadcastStatus liveBroadcastStatus = new LiveBroadcastStatus();
            liveBroadcastStatus.setPrivacyStatus(this.privacy);
            LiveBroadcast liveBroadcast = new LiveBroadcast();
            liveBroadcast.setKind("youtube#liveBroadcast");
            liveBroadcast.setSnippet(liveBroadcastSnippet);
            liveBroadcast.setStatus(liveBroadcastStatus);
            returnedBroadcast = (LiveBroadcast) yt.liveBroadcasts().insert("snippet,status", liveBroadcast).execute();
            Log.e("youtubeLiveActivity", returnedBroadcast.getId());
            LiveStreamSnippet liveStreamSnippet = new LiveStreamSnippet();
            liveStreamSnippet.setTitle(this.title);
            CdnSettings cdnSettings = new CdnSettings();
            cdnSettings.setFormat("720p");
            cdnSettings.setIngestionType("rtmp");
            LiveStream liveStream = new LiveStream();
            liveStream.setKind("youtube#liveStream");
            liveStream.setSnippet(liveStreamSnippet);
            liveStream.setCdn(cdnSettings);
            Log.e("youtubeLiveActivity", "execute");
            returnedStream = (LiveStream) yt.liveStreams().insert("snippet,cdn", liveStream).execute();
            Log.e("youtubeLiveActivity", returnedStream.getId());
            Log.e("youtubeLiveActivity", "execute2");
            Bind bind = yt.liveBroadcasts().bind(returnedBroadcast.getId(), "id,contentDetails");
            bind.setStreamId(returnedStream.getId());
            returnedBroadcast = (LiveBroadcast) bind.execute();
            this.broadcastId = returnedBroadcast.getId();
            this.ingestionAddress = returnedStream.getCdn().getIngestionInfo().getIngestionAddress();
            this.shareLink = returnedBroadcast.getContentDetails().getMonitorStream().getEmbedHtml();
            return 0;
        } catch (GoogleJsonResponseException e) {
            Log.e("GoogleResponseException", "" + e.getDetails().getMessage());
            e.printStackTrace();
            return e.getDetails().getCode();
        } catch (IOException e2) {
            Log.e("YouTubeStreaming", "" + e2.getMessage());
            e2.printStackTrace();
            return 102;
        } catch (Throwable th) {
            Log.e("YouTubeStreaming", "" + th.getMessage());
            th.printStackTrace();
            return 103;
        }
    }

    public void deleteYTBroadcast() {
        Log.e("delete Youtube", "delete");
        try {
            this.credential = d.getInstance().a().c();
            yt = new Builder(this.transport, this.jsonFactory, this.credential).setApplicationName(APP_NAME).build();
            yt.liveStreams().delete(returnedStream.getId()).execute();
            yt.liveBroadcasts().delete(returnedBroadcast.getId()).execute();
            returnedBroadcast = null;
            returnedStream = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finishYTBroadcast() {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... voidArr) {
                Log.e("delete Youtube", "asynctask");
                try {
                    a.getInstance();
                    if (a.returnedBroadcast == null) {
                        Log.e(e.p, "returnedBroadcast is null");
                    }
                    a.getYt().liveBroadcasts().transition("complete", a.this.broadcastId, "id,contentDetails").execute();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                }
                return null;
            }
        }.execute(new Void[0]);
        returnedBroadcast = null;
        returnedStream = null;
    }

    public String getIngestionAddress() {
        return this.ingestionAddress;
    }

    public void setIngestionAddress(String str) {
        this.ingestionAddress = str;
    }

    public LiveStream getReturnedStream() {
        return returnedStream;
    }

    public void setReturnedStream(LiveStream liveStream) {
        returnedStream = liveStream;
    }

    public LiveBroadcast getReturnedBroadcast() {
        return returnedBroadcast;
    }

    public void setReturnedBroadcast(LiveBroadcast liveBroadcast) {
        returnedBroadcast = liveBroadcast;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getPrivacy() {
        return this.privacy;
    }

    public void setPrivacy(String str) {
        this.privacy = str;
    }

    public Credential getCredential() {
        return this.credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public static YouTube getYt() {
        return yt;
    }

    public static void setYt(YouTube youTube) {
        yt = youTube;
    }

    public String getShareLink() {
        return this.shareLink;
    }

    public void setShareLink(String str) {
        this.shareLink = str;
    }

    public String getmEmail() {
        return this.mEmail;
    }

    public void setmEmail(String str) {
        this.mEmail = str;
    }

    public int getStage() {
        return this.stage;
    }

    public void setStage(int i) {
        this.stage = i;
    }

    public String getWatchUrl() {
        return this.watchUrl;
    }

    public void setWatchUrl(String str) {
        this.watchUrl = str;
    }

    public static String getMatcher(String str, String str2) {
        String str3 = "";
        Matcher matcher = Pattern.compile(str).matcher(str2);
        while (matcher.find()) {
            str3 = matcher.group(0);
        }
        return str3;
    }
}
