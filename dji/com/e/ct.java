package com.e;

import com.amap.api.maps.AMapException;

public class ct extends Exception {
    private String a = AMapException.ERROR_UNKNOWN;
    private int b = -1;

    public ct(String str) {
        super(str);
        this.a = str;
        a(str);
    }

    private void a(String str) {
        if (AMapException.ERROR_IO.equals(str)) {
            this.b = 21;
        } else if (AMapException.ERROR_SOCKET.equals(str)) {
            this.b = 22;
        } else if (AMapException.ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.b = 23;
        } else if (AMapException.ERROR_INVALID_PARAMETER.equals(str)) {
            this.b = 24;
        } else if (AMapException.ERROR_NULL_PARAMETER.equals(str)) {
            this.b = 25;
        } else if (AMapException.ERROR_URL.equals(str)) {
            this.b = 26;
        } else if (AMapException.ERROR_UNKNOW_HOST.equals(str)) {
            this.b = 27;
        } else if (AMapException.ERROR_UNKNOW_SERVICE.equals(str)) {
            this.b = 28;
        } else if (AMapException.ERROR_PROTOCOL.equals(str)) {
            this.b = 29;
        } else if (AMapException.ERROR_CONNECTION.equals(str)) {
            this.b = 30;
        } else if (AMapException.ERROR_UNKNOWN.equals(str)) {
            this.b = 31;
        } else if (AMapException.ERROR_FAILURE_AUTH.equals(str)) {
            this.b = 32;
        } else if ("requeust is null".equals(str)) {
            this.b = 1;
        } else if ("request url is empty".equals(str)) {
            this.b = 2;
        } else if ("response is null".equals(str)) {
            this.b = 3;
        } else if ("thread pool has exception".equals(str)) {
            this.b = 4;
        } else if ("sdk name is invalid".equals(str)) {
            this.b = 5;
        } else if ("sdk info is null".equals(str)) {
            this.b = 6;
        } else if ("sdk packages is null".equals(str)) {
            this.b = 7;
        } else if ("线程池为空".equals(str)) {
            this.b = 8;
        } else if ("获取对象错误".equals(str)) {
            this.b = 101;
        } else {
            this.b = -1;
        }
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
