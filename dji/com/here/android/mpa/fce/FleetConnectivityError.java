package com.here.android.mpa.fce;

import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.List;

@HybridPlus
public class FleetConnectivityError {
    private final int a;
    private final String b;
    private final Type c;
    private final List<Issue> d;

    @HybridPlus
    public static class Issue {
        private final String a;
        private final String b;

        @HybridPlusNative
        Issue(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String getMessage() {
            return this.a;
        }

        public String getCode() {
            return this.b;
        }
    }

    @HybridPlus
    public enum Type {
        SERVER_ERROR,
        CONNECTION_ERROR
    }

    @HybridPlusNative
    FleetConnectivityError(int i, String str, Type type, List<Issue> list) {
        this.a = i;
        this.b = str;
        this.c = type;
        this.d = list;
    }

    public int getResponseCode() {
        return this.a;
    }

    public String getErrorId() {
        return this.b;
    }

    public Type getType() {
        return this.c;
    }

    public List<Issue> getIssues() {
        return this.d;
    }
}
