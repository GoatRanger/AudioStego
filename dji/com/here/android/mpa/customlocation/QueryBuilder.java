package com.here.android.mpa.customlocation;

import com.nokia.maps.annotation.HybridPlus;
import dji.pilot.usercenter.mode.n;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@HybridPlus
public class QueryBuilder {
    private StringBuilder a;

    @HybridPlus
    public enum Attribute {
        NAME1("name1"),
        NAME2("name2"),
        NAME3("name3"),
        CUSTOMER_LOCATION_ID("customerLocationId"),
        CUSTOMER_ID("customerId"),
        GEOCOORD_LATITUDE("displayLat"),
        GEOCOORD_LONGITUDE("displayLon"),
        ROUTE_GEOCOORD_LATITUDE("navLat"),
        ROUTE_GEOCOORD_LONGITUDE("navLon"),
        DESCRIPTION("description"),
        HOUSE_NUMBER("houseNumber"),
        STREET("street"),
        CITY(n.B),
        STATE("state"),
        POSTAL_CODE("postalCode"),
        COUNTRY("country"),
        COUNTY("county"),
        COUNTRY_NAME("countryName"),
        PHONE("phone"),
        FAX("fax");
        
        private String a;

        private Attribute(String str) {
            this.a = str;
        }

        public String toString() {
            return this.a;
        }
    }

    @HybridPlus
    public enum Condition {
        EXACT_MATCH("[x]/%s/%s"),
        GREATER_THAN("[>]/%s/%s"),
        LESS_THAN("[<]/%s/%s"),
        LESS_THAN_EQUALS("[<=]/%s/%s"),
        LIKE("[like]/%s/%s"),
        LIKE_POST_WILDCARD("[like*]/%s/%s"),
        LIKE_PRE_WILDCARD("[*like]/%s/%s"),
        NULL("[null]/%s/%s"),
        NOT("[not]/%s/%s");
        
        private String a;

        private Condition(String str) {
            this.a = str;
        }

        public String toString() {
            return this.a;
        }
    }

    @HybridPlus
    public enum Operation {
        AND("/[AND]/"),
        OR("/[OR]/");
        
        private String a;

        private Operation(String str) {
            this.a = str;
        }

        public String toString() {
            return this.a;
        }
    }

    public QueryBuilder(Condition condition, Attribute attribute, String str) {
        this(condition, attribute.toString(), str);
    }

    QueryBuilder(Condition condition, String str, String str2) {
        this.a = new StringBuilder();
        addCondition(null, condition, str, str2);
    }

    QueryBuilder addCondition(Operation operation, Condition condition, String str, String str2) {
        String encode;
        if (this.a.length() > 0) {
            this.a.append(operation.toString());
        }
        try {
            encode = URLEncoder.encode(str.toString(), Charset.defaultCharset().displayName());
        } catch (Exception e) {
            encode = str.toString();
        }
        try {
            str2 = URLEncoder.encode(str2, Charset.defaultCharset().displayName());
        } catch (Exception e2) {
        }
        this.a.append(String.format(condition.toString(), new Object[]{encode, str2}));
        return this;
    }

    public QueryBuilder addCondition(Operation operation, Condition condition, Attribute attribute, String str) {
        addCondition(operation, condition, attribute.toString(), str);
        return this;
    }

    String a() {
        return this.a.toString();
    }
}
