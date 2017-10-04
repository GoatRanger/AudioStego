package com.nokia.maps;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Base64;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.DiscoveryResultPage;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.MediaCollectionPage;
import com.here.android.mpa.search.Place;
import com.here.android.mpa.search.Request;
import com.here.android.mpa.search.ResultListener;
import com.here.android.mpa.search.RichTextFormatting;
import com.here.android.mpa.search.TransitSchedulePage;
import com.here.android.mpa.search.e;
import com.nokia.maps.PlacesCategoryGraph.CategoryGraphData;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;
import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

@Online
public abstract class PlacesBaseRequest<T> extends BaseNativeObject {
    private static k<Request<?>, PlacesBaseRequest<?>> j;
    private static final String k = PlacesBaseRequest.class.getName();
    private static String p;
    protected RichTextFormatting a;
    protected com.nokia.maps.dd.b b;
    protected com.nokia.maps.dd.a c;
    protected int d;
    protected GeoBoundingBox e;
    protected ErrorCode f;
    protected T g;
    protected boolean h;
    protected c i;
    private Map<String, String> l;
    private Map<String, String> m;
    private List<String> n;
    private List<dj> o;
    private PlacesBaseRequest<T> q;
    private ResultListener<T> r;
    private Class<?> s;
    private String t;
    private ch u;
    private p v;
    private boolean w;

    class a extends AsyncTask<PlacesBaseRequest<T>, Object, Void> {
        final /* synthetic */ PlacesBaseRequest a;

        a(PlacesBaseRequest placesBaseRequest) {
            this.a = placesBaseRequest;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((PlacesBaseRequest[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected Void a(PlacesBaseRequest<T>... placesBaseRequestArr) {
            if (!isCancelled()) {
                if (!ck.b()) {
                    synchronized (this.a) {
                        this.a.u = new ch();
                    }
                }
                if (this.a.q instanceof di) {
                    this.a.f = ((di) this.a.q).d();
                } else if (this.a.q instanceof do) {
                    this.a.f = ((do) this.a.q).d();
                } else if (this.a.q instanceof dp) {
                    this.a.f = ((dp) this.a.q).d();
                } else {
                    bj.f(PlacesBaseRequest.k, "Request is not a Geocode/ReverseGeocode request", new Object[0]);
                    this.a.f = ErrorCode.BAD_REQUEST;
                }
                if (this.a.f != ErrorCode.NONE) {
                    this.a.a(false);
                }
            }
            return null;
        }

        protected void a(Void voidR) {
            this.a.q = null;
        }
    }

    class b extends AsyncTask<PlacesBaseRequest<T>, Object, Void> {
        final /* synthetic */ PlacesBaseRequest a;

        b(PlacesBaseRequest placesBaseRequest) {
            this.a = placesBaseRequest;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((PlacesBaseRequest[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected Void a(PlacesBaseRequest<T>... placesBaseRequestArr) {
            MapsEngine b = MapsEngine.b(null);
            b.w();
            cc A = b.A();
            boolean z = true;
            while (z) {
                if (this.a.h) {
                    this.a.cancelNative();
                    z = false;
                } else {
                    synchronized (A) {
                        z = this.a.q.poll();
                    }
                    if (z) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
            b.v();
            return null;
        }

        protected void a(Void voidR) {
            this.a.h = false;
        }
    }

    private native synchronized void addCustomHeaderNative(String str, String str2);

    private native synchronized void addUrlParameterNative(String str, String str2);

    private final native synchronized boolean cancelNative();

    protected static native void destroyNative(int i);

    private native int executeNative();

    private native synchronized boolean poll();

    public native String[] getCookiesStrNative();

    public static void a(k<Request<?>, PlacesBaseRequest<?>> kVar) {
        j = kVar;
    }

    protected PlacesBaseRequest() {
        super(true);
        this.a = RichTextFormatting.HTML;
        this.b = dd.a;
        this.c = com.nokia.maps.dd.a.ONLINE;
        this.d = 20;
        this.l = new HashMap();
        this.m = new HashMap();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.f = ErrorCode.NONE;
        this.h = false;
        this.v = l.a();
        this.i = c.UNKNOWN;
        this.q = this;
        this.t = "";
        this.s = du.a((Object) this);
        if (p == null) {
            p = "Basic " + Base64.encodeToString((ConnectionInfoImpl.getApplicationId() + ":" + ConnectionInfoImpl.getApplicationCode()).getBytes(Charset.forName("UTF-8")), 0);
        }
    }

    protected PlacesBaseRequest(int i) {
        this();
        bj.e(k, "constructor nativeptr=0x%X", new Object[]{Integer.valueOf(i)});
        this.nativeptr = i;
    }

    protected void finalize() {
        ez.a(new Runnable(this) {
            int a = this.b.nativeptr;
            final /* synthetic */ PlacesBaseRequest b;

            {
                this.b = r2;
            }

            public void run() {
                bj.e(PlacesBaseRequest.k, "destroyNative ptr=0x%X", new Object[]{Integer.valueOf(this.a)});
                PlacesBaseRequest.destroyNative(this.a);
            }
        });
    }

    protected void a(c cVar) {
        this.i = cVar;
    }

    static String a() {
        return p;
    }

    public synchronized void a(com.nokia.maps.dd.a aVar) {
        this.c = aVar;
    }

    public synchronized ErrorCode a(ResultListener<T> resultListener) {
        ErrorCode errorCode;
        dy.a((Object) resultListener, "Result listener is null");
        errorCode = ErrorCode.UNKNOWN;
        if (this.r == null) {
            if (this.c != null) {
                this.r = resultListener;
                if (df.a() == com.here.android.mpa.search.a.a.ALLOWED) {
                    String str = null;
                    for (HttpCookie httpCookie : MapsEngine.d().getCookies()) {
                        String str2;
                        String name = httpCookie.getName();
                        String value = httpCookie.getValue();
                        if (str == null) {
                            str2 = name;
                        } else {
                            str2 = str.concat(name);
                        }
                        str = str2.concat("=").concat(value).concat("; ");
                    }
                    if (str != null) {
                        b("Cookie", str);
                    }
                } else if (df.a() == com.here.android.mpa.search.a.a.DO_NOT_TRACK) {
                    b("DNT", "1");
                }
                switch (this.i) {
                    case GEOCODE:
                    case REVERSE_GEOCODE:
                        errorCode = c((ResultListener) resultListener);
                        break;
                    case CATEGORY_GRAPH:
                    case DISCOVER:
                    case DISCOVER_AROUND:
                    case DISCOVER_EXPLORE:
                    case DISCOVER_HERE:
                    case DISCOVER_SEARCH:
                    case p:
                    case TEXT_SUGGESTIONS:
                    case TEXT_AUTOSUGGESTIONS:
                    case MEDIA_EDITORIAL_COLLECTION_PAGE:
                    case MEDIA_IMAGE_COLLECTION_PAGE:
                    case MEDIA_RATING_COLLECTION_PAGE:
                    case MEDIA_REVIEW_COLLECTION_PAGE:
                    case TRANSIT_SCHEDULE_PAGE:
                    case TILES:
                    case k:
                        errorCode = b((ResultListener) resultListener);
                        break;
                    default:
                        errorCode = ErrorCode.UNKNOWN;
                        break;
                }
            }
            throw new IllegalArgumentException("ConnectivityMode is not set");
        }
        errorCode = ErrorCode.BUSY;
        return errorCode;
    }

    private synchronized ErrorCode b(ResultListener<T> resultListener) {
        ErrorCode errorCode;
        String str;
        bj.e(k, "execute nativeptr=0x%X", new Object[]{Integer.valueOf(this.nativeptr)});
        if (!ck.b()) {
            this.u = new ch();
        }
        errorCode = ErrorCode.NONE;
        if (this.d != 20 || du.a() == com.nokia.maps.dd.a.OFFLINE) {
            addUrlParameterNative(dji.pilot.college.b.b.l, Integer.toString(this.d));
        }
        if (this.a != dd.b) {
            switch (this.a) {
                case HTML:
                    str = "html";
                    break;
                default:
                    str = "plain";
                    break;
            }
            addUrlParameterNative("tf", str);
        }
        if (!this.n.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (String str2 : this.n) {
                if (stringBuffer.length() != 0) {
                    stringBuffer.append(",");
                }
                stringBuffer.append(str2);
            }
            addUrlParameterNative("show_refs", stringBuffer.toString());
        }
        str2 = du.a(this.o);
        if (!str2.isEmpty()) {
            addUrlParameterNative("image_dimensions", str2);
        }
        if (du.a() != com.nokia.maps.dd.a.OFFLINE) {
            addUrlParameterNative("X-Political-View", MapsEngine.g());
        }
        for (Entry entry : this.l.entrySet()) {
            addUrlParameterNative((String) entry.getKey(), (String) entry.getValue());
        }
        if (this.b != dd.a) {
            addCustomHeaderNative("X-Mobility-Mode", this.b.toString().toLowerCase(Locale.US));
        }
        if (!this.m.keySet().contains("Accept-Language")) {
            addCustomHeaderNative("Accept-Language", du.a(b()));
        }
        for (Entry entry2 : this.m.entrySet()) {
            addCustomHeaderNative((String) entry2.getKey(), (String) entry2.getValue());
        }
        errorCode = ErrorCode.values()[executeNative()];
        if (errorCode == ErrorCode.NONE) {
            ez.a(new Runnable(this) {
                final /* synthetic */ PlacesBaseRequest a;

                {
                    this.a = r1;
                }

                @SuppressLint({"NewApi"})
                public void run() {
                    if (VERSION.SDK_INT >= 11) {
                        new b(this.a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new PlacesBaseRequest[]{this.a.q});
                    } else {
                        new b(this.a).execute(new PlacesBaseRequest[]{this.a.q});
                    }
                    bj.e(PlacesBaseRequest.k, "execute - nativeptr=0x%X", new Object[]{Integer.valueOf(this.a.nativeptr)});
                }
            });
        }
        if (errorCode != ErrorCode.NONE) {
            a(false);
            this.v.a(this.i, true, false);
        }
        bj.e(k, "execute nativeptr=0x%X, error=%s", new Object[]{Integer.valueOf(this.nativeptr), errorCode});
        return errorCode;
    }

    protected Locale b() {
        return null;
    }

    private synchronized ErrorCode c(ResultListener<T> resultListener) {
        ez.a(new Runnable(this) {
            final /* synthetic */ PlacesBaseRequest a;

            {
                this.a = r1;
            }

            @SuppressLint({"NewApi"})
            public void run() {
                if (VERSION.SDK_INT >= 11) {
                    new a(this.a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new PlacesBaseRequest[]{this.a.q});
                } else {
                    new a(this.a).execute(new PlacesBaseRequest[]{this.a.q});
                }
                bj.e(PlacesBaseRequest.k, "execute - nativeptr=0x%X", new Object[]{Integer.valueOf(this.a.nativeptr)});
            }
        });
        return ErrorCode.NONE;
    }

    protected synchronized void a(boolean z) {
        if (!(ck.b() || this.u == null)) {
            this.u.a(cj.a("places", cj.a(this.c, this.i)), 0.0d, z);
        }
    }

    public synchronized void c() {
        this.r = null;
        this.h = true;
        a(false);
    }

    protected ErrorCode d() {
        return ErrorCode.BAD_REQUEST;
    }

    public void a(RichTextFormatting richTextFormatting) {
        dy.a((Object) richTextFormatting, "value argument is null");
        this.a = richTextFormatting;
    }

    public synchronized int e() {
        return this.d;
    }

    public synchronized void a(int i) {
        dy.a(i > 0, "Collection value is invalid (must be greater than 0)");
        this.d = i;
    }

    public synchronized void a(String str, String str2) {
        boolean z = true;
        synchronized (this) {
            dy.a((Object) str, "Name is null");
            dy.a(!str.isEmpty(), "Name is empty");
            dy.a((Object) str2, "Value is null");
            if (str2.isEmpty()) {
                z = false;
            }
            dy.a(z, "Value is empty");
            this.l.put(str, str2);
        }
    }

    public synchronized void b(String str, String str2) {
        boolean z = true;
        synchronized (this) {
            dy.a((Object) str, "Name is null");
            dy.a(!str.isEmpty(), "Name is empty");
            dy.a((Object) str2, "Value is null");
            if (str2.isEmpty()) {
                z = false;
            }
            dy.a(z, "Value is empty");
            this.m.put(str, str2);
        }
    }

    public void a(List<String> list) {
        this.n = list;
    }

    public void a(GeoBoundingBox geoBoundingBox) {
        dy.a((Object) geoBoundingBox, "Map viewport is missing.");
        GeoCoordinate topLeft = geoBoundingBox.getTopLeft();
        GeoCoordinate bottomRight = geoBoundingBox.getBottomRight();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(topLeft.getLongitude());
        stringBuilder.append(",");
        stringBuilder.append(bottomRight.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(bottomRight.getLongitude());
        stringBuilder.append(",");
        stringBuilder.append(topLeft.getLatitude());
        b("X-Map-Viewport", stringBuilder.toString());
    }

    public void a(int i, int i2) {
        boolean z = true;
        dy.a(i >= 0, "Width must be a positive value");
        if (i2 < 0) {
            z = false;
        }
        dy.a(z, "Height must be a positive value");
        if (i > 0 || i2 > 0) {
            this.o.add(new dj(i, i2));
        }
    }

    public void a(String str) {
        dy.a((Object) str, "User authentication token is null.");
        dy.a((Object) str, "User authentication token is invalid (empty).");
        b("Authorization", "Bearer " + str);
    }

    @OnlineNative
    protected synchronized void onError(int i) {
        this.f = ErrorCode.values()[i];
        bj.e(k + " cb", "nativeptr=0x%X, onError=%s", new Object[]{Integer.valueOf(this.nativeptr), this.f});
        ez.a(new Runnable(this) {
            final /* synthetic */ PlacesBaseRequest a;

            {
                this.a = r1;
            }

            public void run() {
                boolean z = true;
                synchronized (this.a) {
                    if (this.a.r != null) {
                        bj.e(PlacesBaseRequest.k + " cb onCompleted", "nativeptr=0x%X", new Object[]{Integer.valueOf(this.a.nativeptr)});
                        if (this.a.f == ErrorCode.INVALID_PARAMETERS && this.a.g == null) {
                            this.a.f = ErrorCode.BAD_REQUEST;
                        }
                        if (this.a.f == ErrorCode.NONE && this.a.g == null) {
                            this.a.f = ErrorCode.NOT_FOUND;
                        }
                        if (this.a.f != ErrorCode.NONE) {
                            this.a.v.a(this.a.i, true, false);
                        }
                        PlacesBaseRequest placesBaseRequest = this.a;
                        if (this.a.f != ErrorCode.NONE) {
                            z = false;
                        }
                        placesBaseRequest.a(z);
                        this.a.r.onCompleted(this.a.g, this.a.f);
                        this.a.r = null;
                    }
                }
            }
        });
    }

    @OnlineNative
    protected synchronized void onResult(String str) {
        boolean z = true;
        synchronized (this) {
            try {
                bj.e(k + " cb", "onResult() - result=%s", new Object[]{str});
                if (df.a() == com.here.android.mpa.search.a.a.ALLOWED) {
                    de d = MapsEngine.d();
                    if (r5 != 0) {
                        for (String c : getCookiesStrNative()) {
                            HttpCookie c2 = c(c);
                            if (c2 != null) {
                                d.add(null, c2);
                            }
                        }
                    }
                }
                b(str);
                p pVar = this.v;
                c cVar = this.i;
                if (this.f == ErrorCode.NONE) {
                    z = false;
                }
                pVar.a(cVar, z, this.w);
            } catch (Exception e) {
                e.printStackTrace();
                this.r.onCompleted(null, ErrorCode.UNKNOWN);
            }
        }
    }

    private synchronized void b(String str) {
        boolean z = false;
        boolean z2 = true;
        synchronized (this) {
            this.t = str;
            this.w = false;
            if (this.i == c.k) {
                this.g = this.t;
            } else if (this.s == DiscoveryResultPage.class) {
                PlacesDiscoveryResultPage placesDiscoveryResultPage;
                PlacesDiscoveryResult placesDiscoveryResult = (PlacesDiscoveryResult) dt.a().a(this.t, PlacesDiscoveryResult.class);
                if (placesDiscoveryResult == null || placesDiscoveryResult.a() == null) {
                    placesDiscoveryResultPage = null;
                } else {
                    placesDiscoveryResultPage = PlacesDiscoveryResultPage.a(placesDiscoveryResult.a());
                }
                if (placesDiscoveryResultPage == null) {
                    placesDiscoveryResultPage = (PlacesDiscoveryResultPage) dt.a().a(this.t, PlacesDiscoveryResultPage.class);
                }
                if (placesDiscoveryResultPage != null) {
                    placesDiscoveryResultPage.a(this.m);
                    this.g = PlacesDiscoveryResultPage.a(placesDiscoveryResultPage);
                    if (placesDiscoveryResultPage.d().isEmpty()) {
                        z2 = false;
                    }
                    this.w = z2;
                }
            } else if (this.s == MediaCollectionPage.class) {
                PlacesMediaCollectionPage placesMediaCollectionPage;
                switch (this.i) {
                    case MEDIA_EDITORIAL_COLLECTION_PAGE:
                        placesMediaCollectionPage = (PlacesMediaCollectionPage) dt.a().a(this.t, dh.class);
                        break;
                    case MEDIA_IMAGE_COLLECTION_PAGE:
                        placesMediaCollectionPage = (PlacesMediaCollectionPage) dt.a().a(this.t, dl.class);
                        break;
                    case MEDIA_RATING_COLLECTION_PAGE:
                        placesMediaCollectionPage = (PlacesMediaCollectionPage) dt.a().a(this.t, dn.class);
                        break;
                    case MEDIA_REVIEW_COLLECTION_PAGE:
                        placesMediaCollectionPage = (PlacesMediaCollectionPage) dt.a().a(this.t, dr.class);
                        break;
                    default:
                        placesMediaCollectionPage = null;
                        break;
                }
                this.g = PlacesMediaCollectionPage.a(placesMediaCollectionPage);
                if (!(placesMediaCollectionPage == null || placesMediaCollectionPage.e().isEmpty())) {
                    z = true;
                }
                this.w = z;
            } else if (this.s == Place.class) {
                this.g = PlacesPlace.a((PlacesPlace) dt.a().a(this.t, PlacesPlace.class));
                this.w = true;
            } else if (this.i == c.TEXT_AUTOSUGGESTIONS) {
                this.g = ((PlacesTextAutoSuggestionResult) dt.a().a(this.t, PlacesTextAutoSuggestionResult.class)).a();
                if (((List) this.g).isEmpty()) {
                    z2 = false;
                }
                this.w = z2;
            } else if (this.s == List.class) {
                this.g = ((PlacesTextSuggestionResult) dt.a().a(this.t, PlacesTextSuggestionResult.class)).a();
                if (((List) this.g).isEmpty()) {
                    z2 = false;
                }
                this.w = z2;
            } else if (this.s == CategoryGraphData.class) {
                this.g = dt.a().a(this.t, CategoryGraphData.class);
                this.w = !((CategoryGraphData) this.g).a().isEmpty();
            } else if (this.s == e.class) {
                this.g = PlacesTilesLink.a((PlacesTilesLink) dt.a().a(this.t, PlacesTilesLink.class));
                this.w = true;
            } else if (this.s == TransitSchedulePage.class) {
                this.g = PlacesTransitSchedulePage.a((PlacesTransitSchedulePage) dt.a().a(this.t, PlacesTransitSchedulePage.class));
                this.w = true;
            } else {
                bj.d(k, "Unparsed return type=%s", new Object[]{this.s});
            }
        }
    }

    private synchronized HttpCookie c(String str) {
        HttpCookie httpCookie;
        String[] split = str.split("[=;]");
        Object obj = 1;
        httpCookie = null;
        int i = 0;
        while (i < split.length) {
            HttpCookie httpCookie2;
            Object obj2;
            if (obj != null) {
                httpCookie2 = new HttpCookie(split[i], split[i + 1]);
                obj2 = null;
            } else {
                httpCookie2 = httpCookie;
                obj2 = obj;
            }
            if (split[i].contains("Path")) {
                httpCookie2.setPath(split[i + 1]);
            } else if (split[i].contains("Domain")) {
                httpCookie2.setDomain(split[i + 1]);
            } else if (split[i].contains("Expires")) {
                Date parse;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd-MMM-yyyy hh:mm:ss z", Locale.US);
                Date date = new Date();
                try {
                    parse = simpleDateFormat.parse(split[i + 1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                    parse = date;
                }
                httpCookie2.setMaxAge(TimeUnit.SECONDS.convert(parse.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS));
            } else {
                continue;
            }
            i++;
            obj = obj2;
            httpCookie = httpCookie2;
        }
        return httpCookie;
    }
}
