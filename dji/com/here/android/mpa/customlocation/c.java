package com.here.android.mpa.customlocation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.customlocation.Request.Error;
import com.here.android.mpa.customlocation.Result.Geometry;
import com.here.android.mpa.customlocation.Result.LineStringGeometry;
import com.here.android.mpa.customlocation.Result.MultiLineStringGeometry;
import com.here.android.mpa.customlocation.Result.MultiPointGeometry;
import com.here.android.mpa.customlocation.Result.MultiPolygonGeometry;
import com.here.android.mpa.customlocation.Result.PointGeometry;
import com.here.android.mpa.customlocation.Result.PolygonGeometry;
import com.nokia.maps.ak;
import com.nokia.maps.bj;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

final class c extends a<d> {
    private static final String c = a.class.getSimpleName();
    private static final Type d = new TypeToken<List<Geometry>>() {
    }.getType();
    private static final Type e = new TypeToken<List<GeoCoordinate>>() {
    }.getType();
    private static final Type f = new TypeToken<List<List<GeoPolygon>>>() {
    }.getType();
    private static final Type g = new TypeToken<List<GeoPolygon>>() {
    }.getType();
    private static final Type h = new TypeToken<List<GeoCoordinate>>() {
    }.getType();
    private static final Type i = new TypeToken<List<GeoPolyline>>() {
    }.getType();

    static class a implements JsonDeserializer<GeoCoordinate> {
        a() {
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return a(jsonElement, type, jsonDeserializationContext);
        }

        public GeoCoordinate a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            return new GeoCoordinate(asJsonArray.get(1).getAsDouble(), asJsonArray.get(0).getAsDouble());
        }
    }

    static class b implements JsonDeserializer<GeoPolygon> {
        b() {
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return a(jsonElement, type, jsonDeserializationContext);
        }

        public GeoPolygon a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new GeoPolygon((List) jsonDeserializationContext.deserialize(jsonElement, c.e));
        }
    }

    static class c implements JsonDeserializer<GeoPolyline> {
        c() {
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return a(jsonElement, type, jsonDeserializationContext);
        }

        public GeoPolyline a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new GeoPolyline((List) jsonDeserializationContext.deserialize(jsonElement, c.e));
        }
    }

    static class d implements JsonDeserializer<Geometry> {
        private static final Type a = new TypeToken<Map<String, String>>() {
        }.getType();

        d() {
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return a(jsonElement, type, jsonDeserializationContext);
        }

        public Geometry a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = (JsonObject) jsonElement;
            if (jsonObject.get("feature").isJsonNull()) {
                throw new JsonParseException("Incomplete geometry");
            }
            Geometry a;
            JsonObject jsonObject2 = (JsonObject) jsonObject.get("feature");
            JsonObject jsonObject3 = (JsonObject) jsonObject2.get("geometry");
            String asString = jsonObject3.get("type").getAsString();
            if ("MultiPolygon".equals(asString)) {
                a = a(jsonObject3, jsonDeserializationContext);
            } else if ("Polygon".equals(asString)) {
                a = b(jsonObject3, jsonDeserializationContext);
            } else if ("MultiPoint".equals(asString)) {
                a = c(jsonObject3, jsonDeserializationContext);
            } else if ("Point".equals(asString)) {
                a = d(jsonObject3, jsonDeserializationContext);
            } else if ("MultiLineString".equals(asString)) {
                a = e(jsonObject3, jsonDeserializationContext);
            } else if ("LineString".equals(asString)) {
                a = f(jsonObject3, jsonDeserializationContext);
            } else {
                throw new JsonParseException("Not supported geometry type: " + asString);
            }
            a.a = jsonObject.get("geometryId").getAsInt();
            a.b = jsonObject.get("name").getAsString();
            a.c = (Map) jsonDeserializationContext.deserialize(jsonObject2.get("properties"), a);
            a.d = jsonObject2.get("id").getAsString();
            return a;
        }

        private MultiPolygonGeometry a(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            List list = (List) jsonDeserializationContext.deserialize(jsonObject.get("coordinates").getAsJsonArray(), c.f);
            MultiPolygonGeometry multiPolygonGeometry = new MultiPolygonGeometry();
            multiPolygonGeometry.e = list;
            return multiPolygonGeometry;
        }

        private PolygonGeometry b(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            List list = (List) jsonDeserializationContext.deserialize(jsonObject.get("coordinates").getAsJsonArray(), c.g);
            PolygonGeometry polygonGeometry = new PolygonGeometry();
            polygonGeometry.e = list;
            return polygonGeometry;
        }

        private MultiPointGeometry c(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            List list = (List) jsonDeserializationContext.deserialize(jsonObject.get("coordinates").getAsJsonArray(), c.h);
            MultiPointGeometry multiPointGeometry = new MultiPointGeometry();
            multiPointGeometry.e = list;
            return multiPointGeometry;
        }

        private PointGeometry d(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            GeoCoordinate geoCoordinate = (GeoCoordinate) jsonDeserializationContext.deserialize(jsonObject.get("coordinates").getAsJsonArray(), GeoCoordinate.class);
            PointGeometry pointGeometry = new PointGeometry();
            pointGeometry.e = geoCoordinate;
            return pointGeometry;
        }

        private MultiLineStringGeometry e(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            List list = (List) jsonDeserializationContext.deserialize(jsonObject.get("coordinates").getAsJsonArray(), c.i);
            MultiLineStringGeometry multiLineStringGeometry = new MultiLineStringGeometry();
            multiLineStringGeometry.e = list;
            return multiLineStringGeometry;
        }

        private LineStringGeometry f(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            GeoPolyline geoPolyline = (GeoPolyline) jsonDeserializationContext.deserialize(jsonObject.get("coordinates").getAsJsonArray(), GeoPolyline.class);
            LineStringGeometry lineStringGeometry = new LineStringGeometry();
            lineStringGeometry.e = geoPolyline;
            return lineStringGeometry;
        }
    }

    protected /* synthetic */ Object a(String str) throws ak {
        return b(str);
    }

    c(g gVar, WeakReference<f> weakReference) {
        super(gVar, weakReference);
    }

    protected d b(String str) throws ak {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Geometry.class, new d());
            gsonBuilder.registerTypeAdapter(GeoCoordinate.class, new a());
            gsonBuilder.registerTypeAdapter(GeoPolyline.class, new c());
            gsonBuilder.registerTypeAdapter(GeoPolygon.class, new b());
            Gson create = gsonBuilder.create();
            d dVar;
            if (this.a == g.GEOMETRY_ID) {
                Object obj;
                dVar = new d();
                try {
                    obj = (Geometry) create.fromJson(str, Geometry.class);
                } catch (JsonParseException e) {
                    obj = null;
                }
                dVar.a = new LinkedList();
                if (obj != null) {
                    dVar.a.add(obj);
                }
                dVar.status = "OK";
                return dVar;
            }
            List list = (List) create.fromJson(str, d);
            dVar = new d();
            dVar.a = list;
            dVar.status = "OK";
            return dVar;
        } catch (JsonParseException e2) {
            bj.c(c, "JsonParseException thrown: %s", new Object[]{e2.getLocalizedMessage()});
            return null;
        }
    }

    protected void a(d dVar, Error error) {
        f fVar = (f) this.b.get();
        if (fVar != null) {
            fVar.a(dVar, Error.NONE);
        }
    }
}
