package com.nokia.maps;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.j.i;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.Address;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.NavigationPosition;
import com.nokia.maps.dd.c;
import dji.pilot.college.b.b;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class bi extends cn<Void, List<Location>> {
    private static final String a = bi.class.getSimpleName();
    private Builder b;
    private Locale c;

    protected /* synthetic */ Object a(byte[] bArr) throws ak {
        return b(bArr);
    }

    bi(c cVar) {
        switch (cVar) {
            case GEOCODE:
                this.b = b(MapsEngine.j() + "/6.2" + "/geocode" + ".json");
                return;
            case REVERSE_GEOCODE:
                this.b = b(MapsEngine.k() + "/6.2" + "/reversegeocode" + ".json");
                return;
            default:
                throw new IllegalArgumentException("Unsupported request type:" + cVar);
        }
    }

    public void a(Address address) {
        if (address != null) {
            Object countryName = address.getCountryName();
            if (TextUtils.isEmpty(countryName)) {
                countryName = address.getCountryCode();
                if (!TextUtils.isEmpty(countryName)) {
                    this.b.appendQueryParameter("country", countryName);
                }
            } else {
                this.b.appendQueryParameter("country", countryName);
            }
            countryName = address.getState();
            if (!TextUtils.isEmpty(countryName)) {
                this.b.appendQueryParameter("state", countryName);
            }
            countryName = address.getCity();
            if (!TextUtils.isEmpty(countryName)) {
                this.b.appendQueryParameter(n.B, countryName);
            }
            countryName = address.getPostalCode();
            if (!TextUtils.isEmpty(countryName)) {
                this.b.appendQueryParameter("postalcode", countryName);
            }
            countryName = address.getStreet();
            if (!TextUtils.isEmpty(countryName)) {
                this.b.appendQueryParameter("street", countryName);
            }
            countryName = address.getHouseNumber();
            if (!TextUtils.isEmpty(countryName)) {
                this.b.appendQueryParameter("housenumber", countryName);
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.appendQueryParameter("searchtext", str);
        }
    }

    public void a(GeoCoordinate geoCoordinate, int i) {
        if (geoCoordinate != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(geoCoordinate.getLatitude());
            stringBuilder.append(",");
            stringBuilder.append(geoCoordinate.getLongitude());
            if (i > 0) {
                stringBuilder.append(",");
                stringBuilder.append(i);
            }
            this.b.appendQueryParameter("prox", stringBuilder.toString());
        }
    }

    public void a(GeoBoundingBox geoBoundingBox) {
        if (geoBoundingBox != null) {
            this.b.appendQueryParameter("bbox", c(geoBoundingBox));
        }
    }

    public void b(GeoBoundingBox geoBoundingBox) {
        if (geoBoundingBox != null) {
            this.b.appendQueryParameter("mapview", c(geoBoundingBox));
        }
    }

    public void a(int i) {
        if (i > 0) {
            this.b.appendQueryParameter("maxresults", Integer.toString(i));
        }
    }

    public void a(Locale locale) {
        this.c = locale;
    }

    void a() {
        c();
    }

    void b() {
        this.b.appendQueryParameter("mode", "retrieveAddresses");
        c();
    }

    private void c() {
        if (!TextUtils.isEmpty(du.a(this.c))) {
            this.b.appendQueryParameter(b.n, du.a(this.c));
        }
        if (VERSION.SDK_INT >= 11) {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{this.b.toString()});
            return;
        }
        execute(new String[]{this.b.toString()});
    }

    private Builder b(String str) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        Object applicationId = ConnectionInfoImpl.getApplicationId();
        Object applicationCode = ConnectionInfoImpl.getApplicationCode();
        if (!TextUtils.isEmpty(applicationId)) {
            buildUpon.appendQueryParameter("app_id", applicationId);
        }
        if (!TextUtils.isEmpty(applicationCode)) {
            buildUpon.appendQueryParameter("app_code", applicationCode);
        }
        buildUpon.appendQueryParameter("gen", "8");
        buildUpon.appendQueryParameter("politicalview", MapsEngine.g());
        return buildUpon;
    }

    private List<Location> a(JSONObject jSONObject) {
        List<Location> arrayList = new ArrayList();
        try {
            if (jSONObject.has("View")) {
                JSONArray jSONArray = jSONObject.getJSONArray("View");
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.isNull(i)) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has("Result")) {
                            JSONArray jSONArray2 = jSONObject2.getJSONArray("Result");
                            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                if (!jSONArray2.isNull(i2)) {
                                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                                    if (jSONObject3.has("Location")) {
                                        arrayList.add(b(jSONObject3.getJSONObject("Location")));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            a(e);
        }
        return arrayList;
    }

    private Location b(JSONObject jSONObject) {
        PlacesLocation placesLocation = new PlacesLocation();
        try {
            if (jSONObject.has("LocationId")) {
                placesLocation.a(jSONObject.getString("LocationId"));
            }
            placesLocation.a(d(jSONObject));
            placesLocation.a(f(jSONObject));
            placesLocation.a(c(jSONObject));
            List e = e(jSONObject);
            if (!e.isEmpty()) {
                placesLocation.a(e);
            }
        } catch (Exception e2) {
            a(e2);
        }
        return PlacesLocation.a(placesLocation);
    }

    private Address c(JSONObject jSONObject) {
        PlacesAddress placesAddress;
        Exception e;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("Address");
            placesAddress = new PlacesAddress();
            try {
                if (jSONObject2.has("Label")) {
                    placesAddress.m(jSONObject2.getString("Label"));
                }
                if (jSONObject2.has("Country") && !jSONObject2.getString("Country").isEmpty()) {
                    placesAddress.b(jSONObject2.getString("Country"));
                }
                if (jSONObject2.has("State")) {
                    placesAddress.j(jSONObject2.getString("State"));
                }
                if (jSONObject2.has("County")) {
                    placesAddress.d(jSONObject2.getString("County"));
                }
                if (jSONObject2.has("City")) {
                    placesAddress.a(jSONObject2.getString("City"));
                }
                if (jSONObject2.has("District")) {
                    placesAddress.e(jSONObject2.getString("District"));
                }
                if (jSONObject2.has("Street")) {
                    placesAddress.k(jSONObject2.getString("Street"));
                }
                if (jSONObject2.has("HouseNumber")) {
                    placesAddress.g(jSONObject2.getString("HouseNumber"));
                }
                if (jSONObject2.has("PostalCode")) {
                    placesAddress.h(jSONObject2.getString("PostalCode"));
                }
                if (jSONObject2.has("FloorNumber")) {
                    placesAddress.f(jSONObject2.getString("FloorNumber"));
                }
                if (jSONObject2.has("Suite")) {
                    placesAddress.l(jSONObject2.getString("Suite"));
                }
                if (jSONObject2.has("AdditionalData")) {
                    JSONArray jSONArray = jSONObject2.getJSONArray("AdditionalData");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (!jSONArray.isNull(i)) {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                            if (jSONObject3.getString(d.M).contentEquals("CountryName")) {
                                placesAddress.c(jSONObject3.getString("value"));
                            } else if (jSONObject3.getString(d.M).contentEquals("StateName")) {
                                placesAddress.i(jSONObject3.getString("value"));
                            } else {
                                placesAddress.a(jSONObject3.getString(d.M), jSONObject3.getString("value"));
                            }
                        }
                    }
                }
            } catch (JSONException e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            placesAddress = null;
            e = exception;
            a(e);
            return PlacesAddress.create(placesAddress);
        }
        return PlacesAddress.create(placesAddress);
    }

    private GeoCoordinate d(JSONObject jSONObject) {
        GeoCoordinateImpl geoCoordinateImpl = new GeoCoordinateImpl();
        try {
            if (jSONObject.has("DisplayPosition")) {
                geoCoordinateImpl = g(jSONObject.getJSONObject("DisplayPosition"));
            }
        } catch (Exception e) {
            a(e);
        }
        return GeoCoordinateImpl.create(geoCoordinateImpl);
    }

    private List<NavigationPosition> e(JSONObject jSONObject) {
        List<NavigationPosition> arrayList = new ArrayList();
        try {
            if (jSONObject.has("NavigationPosition")) {
                JSONArray jSONArray = jSONObject.getJSONArray("NavigationPosition");
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.isNull(i)) {
                        arrayList.add(PlacesNavigationPosition.a(PlacesNavigationPosition.a(GeoCoordinateImpl.create(g(jSONArray.getJSONObject(i))))));
                    }
                }
            }
        } catch (Exception e) {
            a(e);
        }
        return arrayList;
    }

    private GeoBoundingBox f(JSONObject jSONObject) {
        GeoBoundingBoxImpl geoBoundingBoxImpl;
        try {
            if (jSONObject.has("MapView")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("MapView");
                if (jSONObject2.has("TopLeft") && jSONObject2.has("BottomRight")) {
                    geoBoundingBoxImpl = new GeoBoundingBoxImpl(g(jSONObject2.getJSONObject("TopLeft")), g(jSONObject2.getJSONObject("BottomRight")));
                    return GeoBoundingBoxImpl.create(geoBoundingBoxImpl);
                }
            }
        } catch (Exception e) {
            a(e);
        }
        geoBoundingBoxImpl = null;
        return GeoBoundingBoxImpl.create(geoBoundingBoxImpl);
    }

    private GeoCoordinateImpl g(JSONObject jSONObject) {
        try {
            if (jSONObject.has("Latitude") && jSONObject.has("Longitude")) {
                return new GeoCoordinateImpl(jSONObject.getDouble("Latitude"), jSONObject.getDouble("Longitude"));
            }
        } catch (Exception e) {
            a(e);
        }
        return null;
    }

    protected List<Location> b(byte[] bArr) throws ak {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, Charset.defaultCharset().name()));
            if (jSONObject.has("Response")) {
                return a(jSONObject.getJSONObject("Response"));
            }
        } catch (Exception e) {
            a(e);
        } catch (UnsupportedEncodingException e2) {
            bj.c(a, "parseResult: got UnsupportedEncodingException", new Object[]{e2});
            throw new ak(e2.getMessage());
        }
        return Collections.emptyList();
    }

    private static String c(GeoBoundingBox geoBoundingBox) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(geoBoundingBox.getTopLeft().getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(geoBoundingBox.getTopLeft().getLongitude());
        stringBuilder.append(i.b);
        stringBuilder.append(geoBoundingBox.getBottomRight().getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(geoBoundingBox.getBottomRight().getLongitude());
        return stringBuilder.toString();
    }
}
