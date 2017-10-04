package com.e;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.amap.api.fence.Fence;
import com.amap.api.location.AMapLocation;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class cd {
    Context a;
    private Hashtable<PendingIntent, ArrayList<Fence>> b = new Hashtable();

    public cd(Context context) {
        this.a = context;
    }

    private void a(PendingIntent pendingIntent, Fence fence, int i) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("fenceid", fence.b);
        bundle.putInt("event", i);
        intent.putExtras(bundle);
        try {
            pendingIntent.send(this.a, 0, intent);
        } catch (Throwable th) {
            bc.a(th, "FenceManager", "fcIntent");
        }
    }

    private boolean a(PendingIntent pendingIntent, List<String> list) {
        boolean z = false;
        if (!(b() || list == null || list.isEmpty() || !this.b.containsKey(pendingIntent))) {
            Iterator it = ((ArrayList) this.b.get(pendingIntent)).iterator();
            while (it != null && it.hasNext()) {
                boolean z2;
                Fence fence = (Fence) it.next();
                if (list.contains(fence.b) || a(fence)) {
                    it.remove();
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
        }
        return z;
    }

    private boolean a(Fence fence) {
        return fence.b() != -1 && fence.b() <= br.b();
    }

    private boolean a(List<String> list) {
        if (b() || list == null || list.isEmpty()) {
            return false;
        }
        Iterator it = this.b.entrySet().iterator();
        boolean z = false;
        while (it != null && it.hasNext()) {
            Entry entry = (Entry) it.next();
            Iterator it2 = ((ArrayList) entry.getValue()).iterator();
            while (it2 != null && it2.hasNext()) {
                boolean z2;
                Fence fence = (Fence) it2.next();
                if (list.contains(fence.b) || a(fence)) {
                    it2.remove();
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
            if (((ArrayList) entry.getValue()).isEmpty()) {
                it.remove();
            }
        }
        return z;
    }

    private boolean b() {
        return this.b.isEmpty();
    }

    public void a() {
        this.b.clear();
    }

    public void a(AMapLocation aMapLocation) {
        if (!b()) {
            Iterator it = this.b.entrySet().iterator();
            while (it != null && it.hasNext()) {
                Entry entry = (Entry) it.next();
                Iterator it2 = ((ArrayList) entry.getValue()).iterator();
                while (it2.hasNext()) {
                    Fence fence = (Fence) it2.next();
                    if (!a(fence)) {
                        float a = br.a(new double[]{fence.d, fence.c, aMapLocation.getLatitude(), aMapLocation.getLongitude()});
                        float accuracy = aMapLocation.getAccuracy();
                        accuracy = accuracy >= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition ? a - (fence.e + DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) : a - (accuracy + fence.e);
                        Object obj = null;
                        if (accuracy > 0.0f) {
                            if (fence.g != 0) {
                                obj = 1;
                            }
                            fence.g = 0;
                        } else {
                            if (fence.g != 1) {
                                obj = 1;
                            }
                            fence.g = 1;
                        }
                        if (obj != null) {
                            switch (fence.g) {
                                case 0:
                                    fence.h = -1;
                                    if ((fence.a() & 2) != 2) {
                                        break;
                                    }
                                    a((PendingIntent) entry.getKey(), fence, 2);
                                    break;
                                case 1:
                                    fence.h = br.b();
                                    if ((fence.a() & 1) != 1) {
                                        break;
                                    }
                                    a((PendingIntent) entry.getKey(), fence, 1);
                                    break;
                                default:
                                    break;
                            }
                        } else if ((fence.a() & 4) == 4 && fence.h > 0 && br.b() - fence.h > fence.c()) {
                            fence.h = br.b();
                            a((PendingIntent) entry.getKey(), fence, 4);
                        }
                    }
                }
            }
        }
    }

    public boolean a(PendingIntent pendingIntent) {
        if (pendingIntent == null || !this.b.containsKey(pendingIntent)) {
            return false;
        }
        List arrayList = new ArrayList();
        Iterator it = ((ArrayList) this.b.get(pendingIntent)).iterator();
        while (it.hasNext()) {
            arrayList.add(((Fence) it.next()).b);
        }
        return a(arrayList);
    }

    public boolean a(PendingIntent pendingIntent, String str) {
        if (pendingIntent == null || !this.b.containsKey(pendingIntent) || TextUtils.isEmpty(str)) {
            return false;
        }
        List arrayList = new ArrayList();
        arrayList.add(str);
        return a(pendingIntent, arrayList);
    }

    public boolean a(Fence fence, PendingIntent pendingIntent) {
        if (pendingIntent == null || fence == null) {
            return false;
        }
        if (TextUtils.isEmpty(fence.b)) {
            return false;
        }
        if (fence.e < DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
            return false;
        }
        if (fence.e > 1000.0f) {
            return false;
        }
        if (!b() && !this.b.containsKey(pendingIntent)) {
            return false;
        }
        if (fence.a() == 0) {
            return false;
        }
        if (fence.a() > 7) {
            return false;
        }
        Iterator it = this.b.entrySet().iterator();
        int i = 0;
        while (it != null && it.hasNext()) {
            i = ((ArrayList) ((Entry) it.next()).getValue()).size() + i;
        }
        if (i > 20) {
            return false;
        }
        fence.g = -1;
        ArrayList arrayList;
        if (b()) {
            arrayList = new ArrayList();
            arrayList.add(fence);
            this.b.put(pendingIntent, arrayList);
        } else {
            arrayList = (ArrayList) this.b.get(pendingIntent);
            Fence fence2 = null;
            it = arrayList.iterator();
            while (it.hasNext()) {
                Fence fence3 = (Fence) it.next();
                if (!fence3.b.equals(fence.b)) {
                    fence3 = fence2;
                }
                fence2 = fence3;
            }
            if (fence2 != null) {
                arrayList.remove(fence2);
            }
            arrayList.add(fence);
            this.b.put(pendingIntent, arrayList);
        }
        return true;
    }
}
