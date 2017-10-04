package com.nokia.maps;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.mapping.Location;
import com.here.android.mpa.mapping.MapBuildingObject;
import com.here.android.mpa.mapping.MapCartoMarker;
import com.here.android.mpa.mapping.SafetySpotObject;
import com.here.android.mpa.mapping.TrafficEvent;
import com.here.android.mpa.mapping.TrafficEventObject;
import com.here.android.mpa.mapping.TransitAccessInfo;
import com.here.android.mpa.mapping.TransitAccessObject;
import com.here.android.mpa.mapping.TransitLineObject;
import com.here.android.mpa.mapping.TransitLineSegmentObject;
import com.here.android.mpa.mapping.TransitStopInfo;
import com.here.android.mpa.mapping.TransitStopObject;
import com.nokia.maps.annotation.Online;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Online
public class ViewObjectImpl extends BaseNativeObject {
    private static String a = ViewObjectImpl.class.getSimpleName();
    private static k<ViewObject, ViewObjectImpl> c = null;
    private cq b;

    private native boolean contains(Object[] objArr);

    private static native void destroyNative(int i);

    private native boolean equalsNative(ViewObjectImpl viewObjectImpl);

    public native int hashCodeNative();

    static ViewObjectImpl a(ViewObject viewObject) {
        if (c != null) {
            return (ViewObjectImpl) c.a(viewObject);
        }
        return null;
    }

    public static void c(k<ViewObject, ViewObjectImpl> kVar) {
        c = kVar;
    }

    protected ViewObjectImpl() {
        this.b = new cq(ViewObjectImpl.class.getName());
        this.nativeptr = 0;
    }

    protected ViewObjectImpl(int i) {
        this.b = new cq(ViewObjectImpl.class.getName());
        this.nativeptr = i;
    }

    protected void finalize() {
        bj.a(a, "IN - nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
        final int i = this.nativeptr;
        this.nativeptr = 0;
        if (i != 0) {
            ez.a(new Runnable(this) {
                final /* synthetic */ ViewObjectImpl b;

                public void run() {
                    ViewObjectImpl.destroyNative(i);
                }
            });
        } else {
            bj.f("ViewObject", "VERY BAD 0 pointer view object " + getClass().toString() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Arrays.toString(getClass().getClasses()), new Object[0]);
        }
        bj.a(a, "OUT", new Object[0]);
    }

    public Type k() {
        return Type.UNKNOWN_OBJECT;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (ViewObjectImpl.class.isAssignableFrom(obj.getClass())) {
            return equalsNative((ViewObjectImpl) obj);
        }
        return false;
    }

    public static List<ViewObject> a(List<ViewObject> list, List<ViewObject> list2) {
        List arrayList = new ArrayList(list.size());
        List arrayList2 = new ArrayList(list.size());
        List arrayList3 = new ArrayList(list.size());
        List arrayList4 = new ArrayList(list.size());
        List arrayList5 = new ArrayList(list.size());
        for (ViewObject viewObject : list) {
            if (viewObject.getBaseType() != Type.PROXY_OBJECT) {
                arrayList.add(Integer.valueOf(viewObject.hashCode()));
            } else if (viewObject instanceof MapCartoMarker) {
                Location location = ((MapCartoMarker) viewObject).getLocation();
                if (location != null) {
                    arrayList3.add(location.getInfo());
                }
            } else if (viewObject instanceof SafetySpotObject) {
                GeoCoordinate coordinate = ((SafetySpotObject) viewObject).getSafetySpotInfo().getCoordinate();
                if (coordinate != null) {
                    arrayList5.add(coordinate);
                }
            } else if (viewObject instanceof TrafficEventObject) {
                TrafficEvent trafficEvent = ((TrafficEventObject) viewObject).getTrafficEvent();
                if (trafficEvent != null) {
                    arrayList4.add(Long.toString(TrafficEventImpl.a(trafficEvent).getId()));
                }
            } else {
                Object id;
                if (viewObject instanceof TransitAccessObject) {
                    TransitAccessInfo transitAccessInfo = ((TransitAccessObject) viewObject).getTransitAccessInfo();
                    if (transitAccessInfo != null) {
                        id = transitAccessInfo.getId();
                    }
                    id = null;
                } else if (viewObject instanceof TransitLineObject) {
                    id = ((TransitLineObject) viewObject).getLineId();
                } else if (viewObject instanceof TransitLineSegmentObject) {
                    id = ((TransitLineSegmentObject) viewObject).getLineId();
                } else if (viewObject instanceof TransitStopObject) {
                    TransitStopInfo transitStopInfo = ((TransitStopObject) viewObject).getTransitStopInfo();
                    if (transitStopInfo != null) {
                        id = transitStopInfo.getId();
                    }
                    id = null;
                } else {
                    if (viewObject instanceof MapBuildingObject) {
                        id = ((MapBuildingObject) viewObject).getIdentifier();
                    }
                    id = null;
                }
                if (id != null) {
                    arrayList2.add(id);
                }
            }
        }
        for (ViewObject viewObject2 : list2) {
            Object obj = null;
            int i;
            if (viewObject2.getBaseType() == Type.PROXY_OBJECT) {
                Object obj2;
                if (viewObject2 instanceof MapCartoMarker) {
                    Location location2 = ((MapCartoMarker) viewObject2).getLocation();
                    if (location2 == null || !arrayList3.contains(location2.getInfo())) {
                        obj2 = null;
                    } else {
                        obj2 = 1;
                    }
                    obj = obj2;
                } else if (viewObject2 instanceof SafetySpotObject) {
                    GeoCoordinate coordinate2 = ((SafetySpotObject) viewObject2).getSafetySpotInfo().getCoordinate();
                    if (coordinate2 != null && arrayList5.contains(coordinate2)) {
                        i = 1;
                    }
                } else if (viewObject2 instanceof TrafficEventObject) {
                    TrafficEvent trafficEvent2 = ((TrafficEventObject) viewObject2).getTrafficEvent();
                    if (trafficEvent2 != null && arrayList4.contains(Long.toString(TrafficEventImpl.a(trafficEvent2).getId()))) {
                        i = 1;
                    }
                } else {
                    if (viewObject2 instanceof TransitAccessObject) {
                        TransitAccessInfo transitAccessInfo2 = ((TransitAccessObject) viewObject2).getTransitAccessInfo();
                        if (transitAccessInfo2 != null) {
                            obj2 = transitAccessInfo2.getId();
                        }
                        obj2 = null;
                    } else if (viewObject2 instanceof TransitLineObject) {
                        obj2 = ((TransitLineObject) viewObject2).getLineId();
                    } else if (viewObject2 instanceof TransitLineSegmentObject) {
                        obj2 = ((TransitLineSegmentObject) viewObject2).getLineId();
                    } else if (viewObject2 instanceof TransitStopObject) {
                        TransitStopInfo transitStopInfo2 = ((TransitStopObject) viewObject2).getTransitStopInfo();
                        if (transitStopInfo2 != null) {
                            obj2 = transitStopInfo2.getId();
                        }
                        obj2 = null;
                    } else {
                        if (viewObject2 instanceof MapBuildingObject) {
                            obj2 = ((MapBuildingObject) viewObject2).getIdentifier();
                        }
                        obj2 = null;
                    }
                    if (arrayList2.contains(obj2)) {
                        i = 1;
                    }
                }
            } else if (arrayList.contains(Integer.valueOf(viewObject2.hashCode()))) {
                i = 1;
            }
            if (obj == null) {
                list.add(viewObject2);
            }
        }
        return list;
    }

    public int hashCode() {
        return hashCodeNative();
    }
}
