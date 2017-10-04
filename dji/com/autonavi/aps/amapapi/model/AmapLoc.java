package com.autonavi.aps.amapapi.model;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.e.bc;
import com.e.br;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.multimoment.activity.DJIMultiMomentFineActivity;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class AmapLoc implements Parcelable {
    public static final Creator<AmapLoc> CREATOR = new Creator<AmapLoc>() {
        public AmapLoc a(Parcel parcel) {
            return new AmapLoc(parcel);
        }

        public AmapLoc[] a(int i) {
            return null;
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    };
    private String A = "";
    private String B = "";
    private int C = -1;
    private String D = "";
    private String E = "";
    private String F = "";
    private boolean G = true;
    private boolean H = true;
    private JSONObject I = null;
    private String a = "";
    private double b = 0.0d;
    private double c = 0.0d;
    private double d = 0.0d;
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = 0.0f;
    private long h = 0;
    private String i = "new";
    private int j = 0;
    private String k = "success";
    private int l = 0;
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private String v = "";
    private String w = "";
    private String x = "";
    private String y = "";
    private String z = null;

    public AmapLoc(Parcel parcel) {
        boolean z = true;
        this.a = parcel.readString();
        this.i = parcel.readString();
        this.k = parcel.readString();
        this.j = parcel.readInt();
        this.g = parcel.readFloat();
        this.f = parcel.readFloat();
        this.e = parcel.readFloat();
        this.b = parcel.readDouble();
        this.c = parcel.readDouble();
        this.d = parcel.readDouble();
        this.h = parcel.readLong();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = parcel.readString();
        this.y = parcel.readString();
        this.z = parcel.readString();
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.D = parcel.readString();
        this.m = parcel.readString();
        this.C = parcel.readInt();
        this.l = parcel.readInt();
        this.E = parcel.readString();
        this.G = parcel.readByte() != (byte) 0;
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.H = z;
        this.F = parcel.readString();
    }

    public AmapLoc(AmapLoc amapLoc) {
        if (amapLoc != null) {
            this.a = amapLoc.a;
            this.b = amapLoc.b;
            this.c = amapLoc.c;
            this.d = amapLoc.d;
            this.e = amapLoc.e;
            this.f = amapLoc.f;
            this.g = amapLoc.g;
            this.i = amapLoc.i;
            this.n = amapLoc.n;
            this.p = amapLoc.p;
            this.q = amapLoc.q;
            this.r = amapLoc.r;
            this.s = amapLoc.s;
            this.t = amapLoc.t;
            this.u = amapLoc.u;
            this.w = amapLoc.w;
            this.y = amapLoc.y;
            this.z = amapLoc.z;
            this.A = amapLoc.A;
            this.B = amapLoc.B;
            this.C = amapLoc.C;
            this.D = amapLoc.D;
            this.h = amapLoc.h;
            this.v = amapLoc.v;
            this.x = amapLoc.x;
            this.E = amapLoc.E;
            setOffset(this.G);
            setReversegeo(this.H);
            this.F = amapLoc.F;
        }
    }

    public AmapLoc(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (br.a(jSONObject, n.n)) {
                    setProvider(jSONObject.getString(n.n));
                }
                if (br.a(jSONObject, "lon")) {
                    setLon(jSONObject.getDouble("lon"));
                }
                if (br.a(jSONObject, n.x)) {
                    setLat(jSONObject.getDouble(n.x));
                }
                if (br.a(jSONObject, "altitude")) {
                    setAltitude(jSONObject.getDouble("altitude"));
                }
                if (br.a(jSONObject, "acc")) {
                    a(jSONObject.getString("acc"));
                }
                if (br.a(jSONObject, "accuracy")) {
                    setAccuracy((float) jSONObject.getLong("accuracy"));
                }
                if (br.a(jSONObject, DJIMultiMomentFineActivity.U)) {
                    setSpeed((float) jSONObject.getLong(DJIMultiMomentFineActivity.U));
                }
                if (br.a(jSONObject, "dir")) {
                    setBearing((float) jSONObject.getLong("dir"));
                }
                if (br.a(jSONObject, "bearing")) {
                    setBearing((float) jSONObject.getLong("bearing"));
                }
                if (br.a(jSONObject, "type")) {
                    setType(jSONObject.getString("type"));
                }
                if (br.a(jSONObject, "retype")) {
                    setRetype(jSONObject.getString("retype"));
                }
                if (br.a(jSONObject, "citycode")) {
                    setCitycode(jSONObject.getString("citycode"));
                }
                if (br.a(jSONObject, "desc")) {
                    setDesc(jSONObject.getString("desc"));
                }
                if (br.a(jSONObject, "adcode")) {
                    setAdcode(jSONObject.getString("adcode"));
                }
                if (br.a(jSONObject, "country")) {
                    setCountry(jSONObject.getString("country"));
                }
                if (br.a(jSONObject, n.A)) {
                    setProvince(jSONObject.getString(n.A));
                }
                if (br.a(jSONObject, n.B)) {
                    setCity(jSONObject.getString(n.B));
                }
                if (br.a(jSONObject, "road")) {
                    setRoad(jSONObject.getString("road"));
                }
                if (br.a(jSONObject, "street")) {
                    setStreet(jSONObject.getString("street"));
                }
                if (br.a(jSONObject, "number")) {
                    setNumber(jSONObject.getString("number"));
                }
                if (br.a(jSONObject, ParamKey.POINAME)) {
                    setPoiname(jSONObject.getString(ParamKey.POINAME));
                }
                if (br.a(jSONObject, "aoiname")) {
                    setAoiname(jSONObject.getString("aoiname"));
                }
                if (br.a(jSONObject, "errorCode")) {
                    setErrorCode(jSONObject.getInt("errorCode"));
                }
                if (br.a(jSONObject, "errorInfo")) {
                    setErrorInfo(jSONObject.getString("errorInfo"));
                }
                if (br.a(jSONObject, "locationType")) {
                    setLocationType(jSONObject.getInt("locationType"));
                }
                if (br.a(jSONObject, "locationDetail")) {
                    setLocationDetail(jSONObject.getString("locationDetail"));
                }
                if (br.a(jSONObject, "cens")) {
                    setCens(jSONObject.getString("cens"));
                }
                if (br.a(jSONObject, ParamKey.POIID)) {
                    setPoiid(jSONObject.getString(ParamKey.POIID));
                }
                if (br.a(jSONObject, "pid")) {
                    setPoiid(jSONObject.getString("pid"));
                }
                if (br.a(jSONObject, "floor")) {
                    setFloor(jSONObject.getString("floor"));
                }
                if (br.a(jSONObject, "flr")) {
                    setFloor(jSONObject.getString("flr"));
                }
                if (br.a(jSONObject, "coord")) {
                    setCoord(jSONObject.getString("coord"));
                }
                if (br.a(jSONObject, "mcell")) {
                    setMcell(jSONObject.getString("mcell"));
                }
                if (br.a(jSONObject, n.ax)) {
                    setTime(jSONObject.getLong(n.ax));
                }
                if (br.a(jSONObject, "district")) {
                    setDistrict(jSONObject.getString("district"));
                }
                if (br.a(jSONObject, "isOffset")) {
                    setOffset(jSONObject.getBoolean("isOffset"));
                }
                if (br.a(jSONObject, "isReversegeo")) {
                    setReversegeo(jSONObject.getBoolean("isReversegeo"));
                }
            } catch (Throwable th) {
                bc.a(th, "AmapLoc", "AmapLoc");
            }
        }
    }

    private void a(String str) {
        this.e = Float.parseFloat(str);
    }

    public int describeContents() {
        return 0;
    }

    public float getAccuracy() {
        return this.e;
    }

    public String getAdcode() {
        return this.r;
    }

    public double getAltitude() {
        return this.d;
    }

    public String getAoiname() {
        return this.F;
    }

    public float getBearing() {
        return this.g;
    }

    public String getCens() {
        return this.z;
    }

    public String getCity() {
        return this.u;
    }

    public String getCitycode() {
        return this.p;
    }

    public int getCoord() {
        return this.C;
    }

    public String getCountry() {
        return this.s;
    }

    public String getDesc() {
        return this.q;
    }

    public String getDistrict() {
        return this.v;
    }

    public int getErrorCode() {
        return this.j;
    }

    public String getErrorInfo() {
        return this.k;
    }

    public JSONObject getExtra() {
        return this.I;
    }

    public String getFloor() {
        return this.B;
    }

    public double getLat() {
        return this.c;
    }

    public String getLocationDetail() {
        return this.m;
    }

    public int getLocationType() {
        return this.l;
    }

    public double getLon() {
        return this.b;
    }

    public String getMcell() {
        return this.D;
    }

    public AmapLoc getMcellLoc() {
        Object mcell = getMcell();
        if (TextUtils.isEmpty(mcell)) {
            return null;
        }
        String[] split = mcell.split(",");
        if (split.length != 3) {
            return null;
        }
        AmapLoc amapLoc = new AmapLoc();
        amapLoc.setProvider(getProvider());
        amapLoc.setLon(split[0]);
        amapLoc.setLat(split[1]);
        amapLoc.setAccuracy(Float.parseFloat(split[2]));
        amapLoc.setCitycode(getCitycode());
        amapLoc.setAdcode(getAdcode());
        amapLoc.setCountry(getCountry());
        amapLoc.setProvince(getProvince());
        amapLoc.setCity(getCity());
        amapLoc.setTime(getTime());
        amapLoc.setType(getType());
        amapLoc.setCoord(String.valueOf(getCoord()));
        return br.a(amapLoc) ? amapLoc : null;
    }

    public String getNumber() {
        return this.E;
    }

    public String getPoiid() {
        return this.A;
    }

    public String getPoiname() {
        return this.y;
    }

    public String getProvider() {
        return this.a;
    }

    public String getProvince() {
        return this.t;
    }

    public String getRdesc() {
        return this.o;
    }

    public String getRetype() {
        return this.n;
    }

    public String getRoad() {
        return this.w;
    }

    public float getSpeed() {
        return this.f;
    }

    public String getStreet() {
        return this.x;
    }

    public long getTime() {
        return this.h;
    }

    public String getType() {
        return this.i;
    }

    public boolean hasAccuracy() {
        return this.e > 0.0f;
    }

    public boolean hasAltitude() {
        return this.d > 0.0d;
    }

    public boolean hasBearing() {
        return this.g > 0.0f;
    }

    public boolean hasSpeed() {
        return this.f > 0.0f;
    }

    public boolean isOffset() {
        return this.G;
    }

    public boolean isReversegeo() {
        return this.H;
    }

    public void setAccuracy(float f) {
        a(String.valueOf(Math.round(f)));
    }

    public void setAdcode(String str) {
        this.r = str;
    }

    public void setAltitude(double d) {
        this.d = d;
    }

    public void setAoiname(String str) {
        this.F = str;
    }

    public void setBearing(float f) {
        this.g = f;
    }

    public void setBuiltInLocationAdjust(Location location) {
        this.c = location.getLatitude();
        this.b = location.getLongitude();
        this.d = location.getAltitude();
        this.f = location.getSpeed();
        this.g = location.getBearing();
        this.e = location.getAccuracy();
    }

    public void setCens(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (Object obj : str.split("\\*")) {
                if (!TextUtils.isEmpty(obj)) {
                    String[] split = obj.split(",");
                    setLon(Double.parseDouble(split[0]));
                    setLat(Double.parseDouble(split[1]));
                    setAccuracy((float) Integer.parseInt(split[2]));
                    break;
                }
            }
            this.z = str;
        }
    }

    public void setCity(String str) {
        this.u = str;
    }

    public void setCitycode(String str) {
        this.p = str;
    }

    public void setCoord(int i) {
        setCoord(String.valueOf(i));
    }

    public void setCoord(String str) {
        if (TextUtils.isEmpty(str)) {
            this.C = -1;
        } else if (this.a.equals("gps")) {
            this.C = 0;
        } else if (str.equals("0")) {
            this.C = 0;
        } else if (str.equals("1")) {
            this.C = 1;
        } else {
            this.C = -1;
        }
    }

    public void setCountry(String str) {
        this.s = str;
    }

    public void setDesc(String str) {
        this.q = str;
    }

    public void setDistrict(String str) {
        this.v = str;
    }

    public void setErrorCode(int i) {
        if (this.j == 0) {
            switch (i) {
                case 0:
                    this.k = "success";
                    break;
                case 1:
                    this.k = "重要参数为空";
                    break;
                case 2:
                    this.k = "WIFI信息不足";
                    break;
                case 3:
                    this.k = "请求参数获取出现异常";
                    break;
                case 4:
                    this.k = "网络连接异常";
                    break;
                case 5:
                    this.k = "解析XML出错";
                    break;
                case 6:
                    this.k = "定位结果错误";
                    break;
                case 7:
                    this.k = "KEY错误";
                    break;
                case 8:
                    this.k = "其他错误";
                    break;
                case 9:
                    this.k = "初始化异常";
                    break;
                case 10:
                    this.k = "定位服务启动失败";
                    break;
                case 11:
                    this.k = "错误的基站信息，请检查是否插入SIM卡";
                    break;
                case 12:
                    this.k = "缺少定位权限";
                    break;
            }
            this.j = i;
        }
    }

    public void setErrorInfo(String str) {
        this.k = str;
    }

    public void setExtra(JSONObject jSONObject) {
        this.I = jSONObject;
    }

    public void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                str = null;
                bc.a(th, "AmapLoc", "setFloor");
            }
        }
        this.B = str;
    }

    public void setLat(double d) {
        this.c = br.c(d);
    }

    public void setLat(String str) {
        this.c = Double.parseDouble(str);
    }

    public void setLocationDetail(String str) {
        if (this.m == null || this.m.length() == 0) {
            this.m = str;
        }
    }

    public void setLocationType(int i) {
        this.l = i;
    }

    public void setLon(double d) {
        this.b = br.c(d);
    }

    public void setLon(String str) {
        this.b = Double.parseDouble(str);
    }

    public void setMcell(String str) {
        this.D = str;
    }

    public void setNumber(String str) {
        this.E = str;
    }

    public void setOffset(boolean z) {
        this.G = z;
    }

    public void setPoiid(String str) {
        this.A = str;
    }

    public void setPoiname(String str) {
        this.y = str;
    }

    public void setProvider(String str) {
        this.a = str;
    }

    public void setProvince(String str) {
        this.t = str;
    }

    public void setRdesc(String str) {
        this.o = str;
    }

    public void setRetype(String str) {
        this.n = str;
    }

    public void setReversegeo(boolean z) {
        this.H = z;
    }

    public void setRoad(String str) {
        this.w = str;
    }

    public void setSpeed(float f) {
        this.f = f;
    }

    public void setStreet(String str) {
        this.x = str;
    }

    public void setTime(long j) {
        this.h = j;
    }

    public void setType(String str) {
        this.i = str;
    }

    public Location toBuiltInLocation() {
        Location location = new Location(getProvider());
        location.setTime(this.h);
        if (VERSION.SDK_INT >= 17) {
            location.setElapsedRealtimeNanos(this.h);
        }
        location.setLatitude(this.c);
        location.setLongitude(this.b);
        location.setAltitude(this.d);
        location.setSpeed(this.f);
        location.setBearing(this.g);
        location.setAccuracy(this.e);
        return location;
    }

    public String toStr() {
        return toStr(1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toStr(int r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0111 }
        r1.<init>();	 Catch:{ Throwable -> 0x0111 }
        switch(r7) {
            case 1: goto L_0x000c;
            case 2: goto L_0x00d6;
            case 3: goto L_0x00dd;
            default: goto L_0x0009;
        };	 Catch:{ Throwable -> 0x0111 }
    L_0x0009:
        if (r1 != 0) goto L_0x011c;
    L_0x000b:
        return r0;
    L_0x000c:
        r2 = "altitude";
        r4 = r6.d;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "speed";
        r3 = r6.f;	 Catch:{ Throwable -> 0x0111 }
        r4 = (double) r3;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "bearing";
        r3 = r6.g;	 Catch:{ Throwable -> 0x0111 }
        r4 = (double) r3;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "retype";
        r3 = r6.n;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "citycode";
        r3 = r6.p;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "desc";
        r3 = r6.q;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "adcode";
        r3 = r6.r;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "country";
        r3 = r6.s;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "province";
        r3 = r6.t;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "city";
        r3 = r6.u;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "district";
        r3 = r6.v;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "road";
        r3 = r6.w;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "street";
        r3 = r6.x;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "number";
        r3 = r6.E;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "poiname";
        r3 = r6.y;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "cens";
        r3 = r6.z;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "poiid";
        r3 = r6.A;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "floor";
        r3 = r6.B;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "coord";
        r3 = r6.C;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "mcell";
        r3 = r6.D;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "errorCode";
        r3 = r6.j;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "errorInfo";
        r3 = r6.k;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "locationType";
        r3 = r6.l;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "locationDetail";
        r3 = r6.m;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "aoiname";
        r3 = r6.F;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = r6.I;	 Catch:{ Throwable -> 0x0111 }
        if (r2 == 0) goto L_0x00d6;
    L_0x00c1:
        r2 = "offpct";
        r2 = com.e.br.a(r1, r2);	 Catch:{ Throwable -> 0x0111 }
        if (r2 == 0) goto L_0x00d6;
    L_0x00c9:
        r2 = "offpct";
        r3 = r6.I;	 Catch:{ Throwable -> 0x0111 }
        r4 = "offpct";
        r3 = r3.getString(r4);	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
    L_0x00d6:
        r2 = "time";
        r4 = r6.h;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
    L_0x00dd:
        r2 = "provider";
        r3 = r6.a;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "lon";
        r4 = r6.b;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "lat";
        r4 = r6.c;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "accuracy";
        r3 = r6.e;	 Catch:{ Throwable -> 0x0111 }
        r4 = (double) r3;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r4);	 Catch:{ Throwable -> 0x0111 }
        r2 = "type";
        r3 = r6.i;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "isOffset";
        r3 = r6.G;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        r2 = "isReversegeo";
        r3 = r6.H;	 Catch:{ Throwable -> 0x0111 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0111 }
        goto L_0x0009;
    L_0x0111:
        r1 = move-exception;
        r2 = "AmapLoc";
        r3 = "toStr";
        com.e.bc.a(r1, r2, r3);
        r1 = r0;
        goto L_0x0009;
    L_0x011c:
        r0 = r1.toString();
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.model.AmapLoc.toStr(int):java.lang.String");
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = (byte) 1;
        parcel.writeString(this.a);
        parcel.writeString(this.i);
        parcel.writeString(this.k);
        parcel.writeInt(this.j);
        parcel.writeFloat(this.g);
        parcel.writeFloat(this.f);
        parcel.writeFloat(this.e);
        parcel.writeDouble(this.b);
        parcel.writeDouble(this.c);
        parcel.writeDouble(this.d);
        parcel.writeLong(this.h);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.y);
        parcel.writeString(this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeString(this.D);
        parcel.writeString(this.m);
        parcel.writeInt(this.C);
        parcel.writeInt(this.l);
        parcel.writeString(this.E);
        parcel.writeByte(this.G ? (byte) 1 : (byte) 0);
        if (!this.H) {
            b = (byte) 0;
        }
        parcel.writeByte(b);
        parcel.writeString(this.F);
    }
}
