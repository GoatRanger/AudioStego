package com.e;

import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.pilot.usercenter.mode.n;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class bm {
    private StringBuilder a = new StringBuilder();

    private class a extends DefaultHandler {
        public AmapLoc a;
        final /* synthetic */ bm b;
        private String c;

        private a(bm bmVar) {
            this.b = bmVar;
            this.a = new AmapLoc();
            this.c = "";
        }

        public void characters(char[] cArr, int i, int i2) {
            this.c = String.valueOf(cArr, i, i2);
        }

        public void endElement(String str, String str2, String str3) {
            if (str2.equals("retype")) {
                this.a.setRetype(this.c);
            } else if (str2.equals("rdesc")) {
                this.a.setRdesc(this.c);
            } else if (str2.equals("adcode")) {
                this.a.setAdcode(this.c);
            } else if (str2.equals("citycode")) {
                this.a.setCitycode(this.c);
            } else if (str2.equals("radius")) {
                try {
                    this.a.setAccuracy(Float.parseFloat(this.c));
                } catch (Throwable th) {
                    bc.a(th, "parser", "endElement3");
                    this.a.setAccuracy(3891.0f);
                }
            } else if (str2.equals("cenx")) {
                try {
                    this.a.setLon(Double.parseDouble(this.c));
                } catch (Throwable th2) {
                    bc.a(th2, "parser", "endElement2");
                    this.a.setLon(0.0d);
                }
            } else if (str2.equals("ceny")) {
                try {
                    this.a.setLat(Double.parseDouble(this.c));
                } catch (Throwable th22) {
                    bc.a(th22, "parser", "endElement1");
                    this.a.setLat(0.0d);
                }
            } else if (str2.equals("desc")) {
                this.a.setDesc(this.c);
            } else if (str2.equals("country")) {
                this.a.setCountry(this.c);
            } else if (str2.equals(n.A)) {
                this.a.setProvince(this.c);
            } else if (str2.equals(n.B)) {
                this.a.setCity(this.c);
            } else if (str2.equals("district")) {
                this.a.setDistrict(this.c);
            } else if (str2.equals("road")) {
                this.a.setRoad(this.c);
            } else if (str2.equals("street")) {
                this.a.setStreet(this.c);
            } else if (str2.equals("number")) {
                this.a.setNumber(this.c);
            } else if (str2.equals(ParamKey.POINAME)) {
                this.a.setPoiname(this.c);
            } else if (str2.equals("BIZ")) {
                if (this.a.getExtra() == null) {
                    this.a.setExtra(new JSONObject());
                }
                try {
                    this.a.getExtra().put("BIZ", this.c);
                } catch (Throwable th222) {
                    bc.a(th222, "parser", "endElement");
                }
            } else if (str2.equals("cens")) {
                this.a.setCens(this.c);
            } else if (str2.equals("pid")) {
                this.a.setPoiid(this.c);
            } else if (str2.equals("flr")) {
                this.a.setFloor(this.c);
            } else if (str2.equals("coord")) {
                if (TextUtils.isEmpty(bc.f)) {
                    bc.f = this.c;
                }
                this.a.setCoord(this.c);
            } else if (str2.equals("mcell")) {
                this.a.setMcell(this.c);
            } else if (!str2.equals("gkeyloc") && !str2.equals("gkeygeo")) {
                if (str2.equals("apiTime")) {
                    this.a.setTime(Long.parseLong(this.c));
                } else if (str2.equals("aoiname")) {
                    this.a.setAoiname(this.c);
                }
            }
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            this.c = "";
        }
    }

    public AmapLoc a(String str) {
        InputStream byteArrayInputStream;
        if (this.a.length() > 0) {
            this.a.delete(0, this.a.length());
        }
        if (!str.contains("SuccessCode")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.reverse();
            try {
                str = new String(cy.b(stringBuilder.toString()), "UTF-8");
            } catch (Throwable e) {
                bc.a(e, "parser", "ParserApsResp1");
            }
            stringBuilder.delete(0, stringBuilder.length());
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (Throwable e2) {
            bc.a(e2, "parser", "ParserApsResp");
            byteArrayInputStream = null;
        }
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        DefaultHandler aVar = new a();
        try {
            SAXParser newSAXParser = newInstance.newSAXParser();
            if (byteArrayInputStream != null) {
                newSAXParser.parse(byteArrayInputStream, aVar);
            }
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            aVar.a.setProvider("network");
            return aVar.a;
        } catch (Throwable th) {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        }
    }

    public AmapLoc b(String str) {
        AmapLoc amapLoc = new AmapLoc();
        amapLoc.setErrorCode(7);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!(jSONObject.has("status") && jSONObject.has("info"))) {
                this.a.append("json is error " + str);
            }
            String string = jSONObject.getString("status");
            String string2 = jSONObject.getString("info");
            if (string.equals("1")) {
                this.a.append("json is error " + str);
            }
            if (string.equals("0")) {
                this.a.append("auth fail:" + string2);
            }
        } catch (Throwable th) {
            this.a.append("json exception error:" + th.getMessage());
            bc.a(th, "parser", "paseAuthFailurJson");
        }
        amapLoc.setLocationDetail(this.a.toString());
        return amapLoc;
    }
}
