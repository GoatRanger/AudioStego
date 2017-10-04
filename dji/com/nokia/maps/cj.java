package com.nokia.maps;

import android.text.TextUtils;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RouteOptions.Type;
import com.nokia.maps.dd.a;
import com.nokia.maps.dd.c;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;

public class cj {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[Type.values().length];
        static final /* synthetic */ int[] c = new int[TransportMode.values().length];

        static {
            e = new int[c.a().length];
            try {
                e[c.REVERSE_GEOCODE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                e[c.DISCOVER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                e[c.DISCOVER_AROUND.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                e[c.DISCOVER_EXPLORE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                e[c.DISCOVER_HERE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                e[c.DISCOVER_SEARCH.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                e[c.TILES.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                e[c.GEOCODE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                e[c.p.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                e[c.TEXT_SUGGESTIONS.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                e[c.TEXT_AUTOSUGGESTIONS.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            d = new int[a.a().length];
            try {
                d[a.OFFLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                d[a.ONLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                d[a.HYBRID.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            try {
                c[TransportMode.CAR.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                c[TransportMode.PEDESTRIAN.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                c[TransportMode.PUBLIC_TRANSPORT.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
            try {
                b[Type.FASTEST.ordinal()] = 1;
            } catch (NoSuchFieldError e18) {
            }
            try {
                b[Type.SHORTEST.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                b[Type.ECONOMIC.ordinal()] = 3;
            } catch (NoSuchFieldError e20) {
            }
            try {
                b[Type.BALANCED.ordinal()] = 4;
            } catch (NoSuchFieldError e21) {
            }
            a = new int[LocationMethod.values().length];
            try {
                a[LocationMethod.GPS.ordinal()] = 1;
            } catch (NoSuchFieldError e22) {
            }
            try {
                a[LocationMethod.NETWORK.ordinal()] = 2;
            } catch (NoSuchFieldError e23) {
            }
            try {
                a[LocationMethod.GPS_NETWORK.ordinal()] = 3;
            } catch (NoSuchFieldError e24) {
            }
            try {
                a[LocationMethod.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError e25) {
            }
        }
    }

    public static String a(String str, String str2) {
        return a(str, str2, null, null);
    }

    public static String a(String str, String str2, String str3, String str4) {
        return a(str, str2, str3, str4, null);
    }

    public static String a(String str, String str2, String str3, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("nma");
        stringBuffer.append("-");
        stringBuffer.append(str);
        stringBuffer.append("-");
        stringBuffer.append(str2);
        if (!TextUtils.isEmpty(str3)) {
            stringBuffer.append("-");
            stringBuffer.append(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            stringBuffer.append("-");
            stringBuffer.append(str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            stringBuffer.append("-");
            stringBuffer.append("err");
            stringBuffer.append("-");
            stringBuffer.append(str5);
        }
        return stringBuffer.toString();
    }

    public static String a(LocationMethod locationMethod, GeoPosition geoPosition, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(z ? "first" : "update");
        stringBuffer.append("-");
        switch (locationMethod) {
            case GPS:
                stringBuffer.append("gps");
                break;
            case NETWORK:
                stringBuffer.append(com.alipay.sdk.app.a.c.a);
                break;
            case GPS_NETWORK:
                stringBuffer.append("gpsnet");
                break;
            case NONE:
                stringBuffer.append("none");
                break;
        }
        stringBuffer.append("-");
        float longitudeAccuracy = geoPosition.getLongitudeAccuracy();
        if (longitudeAccuracy <= 10.0f) {
            stringBuffer.append("r10");
        } else if (longitudeAccuracy <= 50.0f) {
            stringBuffer.append("r50");
        } else if (longitudeAccuracy <= DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
            stringBuffer.append("r100");
        } else if (longitudeAccuracy <= 300.0f) {
            stringBuffer.append("r300");
        } else {
            stringBuffer.append("rgt300");
        }
        return stringBuffer.toString();
    }

    public static String a(RouteOptions routeOptions) {
        if (routeOptions == null) {
            return "unknmode";
        }
        StringBuffer stringBuffer = new StringBuffer();
        switch (AnonymousClass1.c[routeOptions.getTransportMode().ordinal()]) {
            case 1:
                stringBuffer.append("car");
                stringBuffer.append("-");
                switch (AnonymousClass1.b[routeOptions.getRouteType().ordinal()]) {
                    case 1:
                        stringBuffer.append("fast");
                        break;
                    case 2:
                        stringBuffer.append("short");
                        break;
                    case 3:
                        stringBuffer.append("economic");
                        break;
                    case 4:
                        stringBuffer.append("balanced");
                        break;
                    default:
                        break;
                }
            case 2:
                stringBuffer.append("ped");
                break;
            case 3:
                stringBuffer.append("pedpt");
                break;
            default:
                stringBuffer.append("unknmode");
                break;
        }
        return stringBuffer.toString();
    }

    public static String a(a aVar, c cVar) {
        StringBuffer stringBuffer = new StringBuffer();
        switch (aVar) {
            case OFFLINE:
                stringBuffer.append("offline");
                break;
            case ONLINE:
                stringBuffer.append("online");
                break;
            case HYBRID:
                stringBuffer.append("hybrid");
                break;
        }
        stringBuffer.append("-");
        switch (cVar) {
            case REVERSE_GEOCODE:
                stringBuffer.append("revgeo");
                break;
            case DISCOVER:
                stringBuffer.append("discovery");
                break;
            case DISCOVER_AROUND:
                stringBuffer.append("around");
                break;
            case DISCOVER_EXPLORE:
                stringBuffer.append(DJISupportShareWebviewFragment.T);
                break;
            case DISCOVER_HERE:
                stringBuffer.append("here");
                break;
            case DISCOVER_SEARCH:
                stringBuffer.append("search");
                break;
            case TILES:
                stringBuffer.append("tiles");
                break;
            case GEOCODE:
                stringBuffer.append("geocode");
                break;
            case p:
                stringBuffer.append("place");
                break;
            case TEXT_SUGGESTIONS:
            case TEXT_AUTOSUGGESTIONS:
                stringBuffer.append("txtsuggestion");
                break;
            default:
                stringBuffer.append("unknown");
                break;
        }
        return stringBuffer.toString();
    }
}
