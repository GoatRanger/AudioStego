package com.nokia.maps;

import android.os.StatFs;
import android.util.SparseArray;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.UnintializedMapEngineException;
import com.here.android.mpa.odml.MapLoader.Listener;
import com.here.android.mpa.odml.MapLoader.MapPackageAtCoordinateListener;
import com.here.android.mpa.odml.MapLoader.ResultCode;
import com.here.android.mpa.odml.MapPackage;
import com.here.android.mpa.odml.MapPackage.InstallationState;
import com.here.android.mpa.search.Address;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.ResultListener;
import com.here.android.mpa.search.ReverseGeocodeRequest2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public final class bq {
    private static final String a = bq.class.getSimpleName();
    private static final Map<String, String> p = new HashMap();
    private static final Map<String, String> q = new HashMap();
    private static volatile bq r = null;
    private static Object s = new Object();
    private MapsEngine b;
    private boolean c = false;
    private boolean d = false;
    private volatile boolean e = false;
    private Object f = new Object();
    private String g;
    private String h;
    private long i;
    private List<MapPackage> j = new ArrayList();
    private List<Listener> k = new CopyOnWriteArrayList();
    private List<MapPackageAtCoordinateListener> l = new CopyOnWriteArrayList();
    private List<Integer> m = new ArrayList();
    private p n;
    private boolean o = false;
    private final ApplicationContext$c t = new ApplicationContext$c(this) {
        final /* synthetic */ bq a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.o = false;
            this.a.c = false;
        }

        public void b() {
            this.a.o = true;
            this.a.c = false;
        }
    };
    private boolean u = false;
    private k v = new k();

    private abstract class g implements com.nokia.maps.MapsEngine.k {
        private int a;
        final /* synthetic */ bq f;

        public abstract void e();

        private g(bq bqVar) {
            this.f = bqVar;
            this.a = 0;
        }

        public void a(MapPackageSelection mapPackageSelection, String str, boolean z, boolean z2) {
        }

        public void a(String str, boolean z) {
        }

        public void a(String[] strArr, boolean z) {
        }

        public void a(int i) {
        }

        public void a(String str, int i) {
        }

        public final void b(MapPackageSelection mapPackageSelection) {
            for (Integer intValue : this.f.v.b()) {
                mapPackageSelection.c(intValue.intValue());
            }
            a(mapPackageSelection);
        }

        public void a(MapPackageSelection mapPackageSelection) {
        }

        public void a(long j, long j2) {
        }

        protected void a(Runnable runnable, Runnable runnable2, boolean z) {
            synchronized (this.f.f) {
                if (this.f.d) {
                    c();
                } else if (z) {
                    this.a = 0;
                    runnable.run();
                } else {
                    int i = this.a + 1;
                    this.a = i;
                    if (i <= 7) {
                        ez.a(runnable2, 1000);
                    } else {
                        bj.c(bq.a, "Map Loader operation timed out.", new Object[0]);
                        b();
                    }
                }
            }
        }

        protected void a() {
            this.a = 0;
            this.f.b.b((com.nokia.maps.MapsEngine.k) this);
            this.f.j();
            this.f.b(false);
        }

        protected void b() {
            this.a = 0;
            this.f.b.b((com.nokia.maps.MapsEngine.k) this);
            this.f.j();
            this.f.b(false);
        }

        protected void c() {
            this.a = 0;
            this.f.d = false;
            this.f.b.b((com.nokia.maps.MapsEngine.k) this);
            this.f.b(false);
        }

        protected void f() {
            this.a = 0;
            this.f.b.b((com.nokia.maps.MapsEngine.k) this);
            this.f.j();
            this.f.b(false);
        }

        protected void d() {
            this.a = 0;
            this.f.b.b((com.nokia.maps.MapsEngine.k) this);
            this.f.j();
            this.f.b(false);
        }

        protected void a(MapPackage mapPackage, InstallationState installationState) {
            if (mapPackage != null) {
                bu.a(mapPackage).a(installationState);
                List<MapPackage> children = mapPackage.getChildren();
                if (children.size() > 0) {
                    for (MapPackage a : children) {
                        a(a, installationState);
                    }
                }
            }
        }
    }

    private abstract class f extends g {
        protected List<String> c;
        protected final Comparator<String> d;
        final /* synthetic */ bq e;

        private f(bq bqVar) {
            this.e = bqVar;
            super();
            this.d = new Comparator<String>(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((String) obj, (String) obj2);
                }

                public int a(String str, String str2) {
                    return a(str, ".", 4).compareTo(a(str2, ".", 4));
                }

                private String a(String str, String str2, int i) {
                    String[] split = Pattern.compile(str2, 16).split(str);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Object obj : split) {
                        stringBuilder.append(String.format("%" + i + 's', new Object[]{obj}));
                    }
                    return stringBuilder.toString();
                }
            };
        }

        protected boolean g() {
            List arrayList = new ArrayList(this.c);
            Collections.sort(arrayList, Collections.reverseOrder(this.d));
            if (arrayList.isEmpty() || this.d.compare(this.e.g, arrayList.get(0)) >= 0) {
                this.e.h = this.e.g;
                return false;
            }
            bj.e(bq.a, "Updated map version found. Update from: " + this.e.g + " to: " + ((String) arrayList.get(0)) + " available.", new Object[0]);
            this.e.h = (String) arrayList.get(0);
            return true;
        }
    }

    private abstract class l extends f {
        private List<MapPackage> a;
        final /* synthetic */ bq b;
        private boolean g;
        private boolean h;

        protected abstract void a(MapPackage mapPackage, ResultCode resultCode);

        private l(bq bqVar) {
            this.b = bqVar;
            super();
            this.a = new ArrayList();
            this.g = true;
            this.h = false;
        }

        protected void a() {
            super.a();
            a(null, ResultCode.FATAL_ERROR);
        }

        protected void b() {
            super.b();
            if (this.h) {
                a(null, ResultCode.UNEXPECTED_ERROR);
            } else {
                a(null, ResultCode.SERVER_NOT_RESPONDING);
            }
        }

        protected void c() {
            super.c();
            a(null, ResultCode.OPERATION_CANCELLED);
        }

        protected void d() {
            super.d();
            a(null, ResultCode.NO_CONNECTIVITY);
        }

        public void e() {
            this.b.j();
            if (this.b.v.d()) {
                a(null, ResultCode.OPERATION_BUSY);
            } else if (this.b.v.c()) {
                synchronized (this.b.f) {
                    if (this.b.d) {
                        c();
                    } else {
                        this.b.b.a((com.nokia.maps.MapsEngine.k) this);
                        this.b.b.F();
                    }
                }
            } else {
                a(null, ResultCode.UNEXPECTED_ERROR);
            }
        }

        public void a(final String[] strArr, boolean z) {
            a(new Runnable(this) {
                final /* synthetic */ l b;

                public void run() {
                    this.b.c = new ArrayList(Arrays.asList(strArr));
                    this.b.h = true;
                    if (this.b.g()) {
                        this.b.b.b.a(this.b.b.h);
                    } else {
                        this.b.a(null, ResultCode.NO_UPDATE_TO_PERFORM);
                    }
                }
            }, new Runnable(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.b.F();
                }
            }, z);
        }

        public void a(long j, long j2) {
            if (!this.b.a(j)) {
                this.g = false;
                this.b.j();
            }
            for (Listener onInstallationSize : this.b.k) {
                onInstallationSize.onInstallationSize(j, j2);
            }
        }

        public void a(int i) {
            for (Listener onProgress : this.b.k) {
                onProgress.onProgress(i);
            }
        }

        public void a(MapPackageSelection mapPackageSelection) {
            try {
                this.b.a(mapPackageSelection, this.a);
                for (MapPackage mapPackage : this.b.j) {
                    int id = mapPackage.getId();
                    if (mapPackage.getInstallationState() == InstallationState.INSTALLED || mapPackage.getInstallationState() == InstallationState.PARTIALLY_INSTALLED) {
                        mapPackageSelection.b(id);
                    }
                }
                this.b.b.G();
            } catch (IndexOutOfBoundsException e) {
                a(null, ResultCode.UNEXPECTED_ERROR);
            }
        }

        public void a(final String str, int i) {
            Runnable anonymousClass3 = new Runnable(this) {
                final /* synthetic */ l b;

                public void run() {
                    this.b.b.g = str;
                    try {
                        for (MapPackage mapPackage : this.b.b.j) {
                            int id = mapPackage.getId();
                            for (MapPackage mapPackage2 : this.b.a) {
                                if (mapPackage2.getId() == id) {
                                    this.b.a(mapPackage2, mapPackage.getInstallationState());
                                }
                            }
                        }
                        this.b.b.b.D();
                        this.b.a((MapPackage) this.b.a.get(0), ResultCode.OPERATION_SUCCESSFUL);
                    } catch (IndexOutOfBoundsException e) {
                        this.b.a(null, ResultCode.UNEXPECTED_ERROR);
                    }
                }
            };
            Runnable anonymousClass4 = new Runnable(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.b.a(this.a.b.h);
                }
            };
            if (str.isEmpty() && i == 0) {
                a(null, ResultCode.OPERATION_CANCELLED);
            } else if (this.g) {
                a(anonymousClass3, anonymousClass4, i == 0);
            } else {
                a(null, ResultCode.NOT_ENOUGH_DISK_SPACE);
            }
        }
    }

    private abstract class c extends g {
        private final Runnable a;
        private boolean b;
        final /* synthetic */ bq c;
        private Object d;
        private List<MapPackage> e;
        private boolean g;

        protected abstract void a(MapPackage mapPackage, ResultCode resultCode);

        private c(bq bqVar) {
            this.c = bqVar;
            super();
            this.a = new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (this.a.d) {
                        if (!this.a.b) {
                            if (this.a.c.i()) {
                                ez.a(this.a.a, 2000);
                            } else {
                                this.a.c.b.b(this.a);
                                this.a.c.j();
                                this.a.a(null, ResultCode.NO_CONNECTIVITY);
                            }
                        }
                    }
                }
            };
            this.b = false;
            this.d = new Object();
            this.e = new ArrayList();
            this.g = false;
        }

        public void e() {
            if (MapsEngine.f().booleanValue()) {
                g();
            } else if (this.c.v.d()) {
                a(null, ResultCode.OPERATION_BUSY);
            } else if (!this.c.v.c()) {
                a(null, ResultCode.UNEXPECTED_ERROR);
            } else if (this.c.j.isEmpty()) {
                g();
            } else {
                a((MapPackage) this.c.j.get(0), ResultCode.OPERATION_SUCCESSFUL);
            }
        }

        private void g() {
            this.c.j();
            ez.a(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (this.a.c.f) {
                        if (this.a.c.d) {
                            this.a.c();
                        } else {
                            this.a.c.b.a(this.a);
                            if (this.a.c.g != null) {
                                this.a.c.b.a(this.a.c.g);
                            } else {
                                bj.c(bq.a, "MapVersion is null", new Object[0]);
                                this.a.b();
                            }
                            ez.a(this.a.a, 2000);
                        }
                    }
                }
            }, 2000);
        }

        protected void a() {
            super.a();
            h();
            a(null, ResultCode.FATAL_ERROR);
        }

        protected void b() {
            super.b();
            h();
            a(null, ResultCode.UNEXPECTED_ERROR);
        }

        protected void c() {
            super.c();
            h();
            a(null, ResultCode.OPERATION_CANCELLED);
        }

        protected void d() {
            super.d();
            a(null, ResultCode.NO_CONNECTIVITY);
        }

        public void a(String str, int i) {
            a(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        this.a.a((MapPackage) this.a.e.get(0), ResultCode.OPERATION_SUCCESSFUL);
                    } catch (IndexOutOfBoundsException e) {
                        this.a.a(null, ResultCode.OPERATION_CANCELLED);
                    }
                }
            }, new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.c.b.a(this.a.c.g);
                }
            }, this.g);
        }

        public void a(MapPackageSelection mapPackageSelection) {
            try {
                h();
                this.g = true;
                this.c.a(mapPackageSelection, this.e);
                this.c.j();
            } catch (IndexOutOfBoundsException e) {
                a(null, ResultCode.UNEXPECTED_ERROR);
            }
        }

        private void h() {
            synchronized (this.d) {
                ez.b(this.a);
                this.b = true;
            }
        }
    }

    private abstract class e extends g {
        private int a = -1;
        final /* synthetic */ bq b;
        private List<Integer> c = new ArrayList();
        private boolean d = false;
        private boolean e = true;
        private List<Integer> g = new ArrayList();
        private List<Integer> h = new ArrayList();

        protected abstract void a(MapPackage mapPackage, ResultCode resultCode);

        public e(bq bqVar, List<Integer> list) {
            this.b = bqVar;
            super();
            if (list == null || list.isEmpty()) {
                this.d = true;
            } else {
                this.c.addAll(list);
            }
        }

        protected void a() {
            super.a();
            a(null, ResultCode.FATAL_ERROR);
        }

        protected void b() {
            super.b();
            a(null, ResultCode.UNEXPECTED_ERROR);
        }

        protected void c() {
            boolean z = true;
            if (this.b.g != null) {
                if (this.b.e) {
                    this.b.m.addAll(this.h);
                } else {
                    z = false;
                }
            }
            if (z) {
                super.c();
                a(null, ResultCode.OPERATION_CANCELLED);
                return;
            }
            this.b.b.b((com.nokia.maps.MapsEngine.k) this);
            this.c.addAll(this.g);
            new d(this, this.c, false) {
                final /* synthetic */ e b;

                protected void a(MapPackage mapPackage, ResultCode resultCode) {
                    this.b.b.b.b((com.nokia.maps.MapsEngine.k) this);
                    this.b.b.d = false;
                    this.b.b.b(false);
                    this.b.b.a(resultCode);
                    for (Listener onInstallMapPackagesComplete : this.b.b.k) {
                        onInstallMapPackagesComplete.onInstallMapPackagesComplete(mapPackage, resultCode);
                    }
                }
            }.e();
        }

        protected void f() {
            super.f();
            a(null, ResultCode.INVALID_PARAMETERS);
        }

        protected void d() {
            super.d();
            a(null, ResultCode.NO_CONNECTIVITY);
        }

        public void e() {
            this.b.j();
            if (this.b.v.d()) {
                a(null, ResultCode.OPERATION_BUSY);
            } else if (!this.b.v.c()) {
                a(null, ResultCode.UNEXPECTED_ERROR);
            } else if (this.d) {
                a(null, ResultCode.INVALID_PARAMETERS);
            } else {
                ez.a(new Runnable(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        synchronized (this.a.b.f) {
                            if (this.a.b.d) {
                                this.a.c();
                            } else {
                                this.a.b.b.a(this.a);
                                if (this.a.b.g != null) {
                                    this.a.b.b.a(this.a.b.g);
                                } else {
                                    bj.c(bq.a, "MapVersion is null", new Object[0]);
                                    this.a.b();
                                }
                            }
                        }
                    }
                }, 2000);
            }
        }

        public void a(MapPackageSelection mapPackageSelection) {
            for (Integer num : this.c) {
                if (!mapPackageSelection.b(num.intValue())) {
                    this.d = true;
                }
                this.h.add(num);
                a(mapPackageSelection, num);
            }
            if (this.d) {
                f();
                return;
            }
            this.b.b.G();
            a(1);
        }

        private void a(MapPackageSelection mapPackageSelection, Integer num) {
            if (this.b.v.c.containsKey(num)) {
                Integer a = ((i) this.b.v.c.get(num)).a();
                Integer valueOf = Integer.valueOf(mapPackageSelection.getPackageIdFromIndex(a.intValue()));
                int packageChildrenCount = mapPackageSelection.getPackageChildrenCount(a.intValue());
                if (this.b.v.b.indexOfKey(valueOf.intValue()) >= 0) {
                    h hVar = (h) this.b.v.b.get(valueOf.intValue());
                    if (hVar != null) {
                        List a2 = hVar.a();
                        if (!a2.contains(num) && packageChildrenCount - a2.size() == 1) {
                            mapPackageSelection.b(valueOf.intValue());
                            this.h.add(valueOf);
                            this.g.add(valueOf);
                            a(mapPackageSelection, valueOf);
                        }
                    }
                }
            }
        }

        public void a(int i) {
            if (i > 2) {
                if (this.a < 0) {
                    this.a = i;
                }
                if (i < this.a) {
                    this.a = i;
                }
                i = ((((i - this.a) * 97) / (100 - this.a)) + 1) + 2;
            }
            for (Listener onProgress : this.b.k) {
                onProgress.onProgress(i);
            }
        }

        public void a(long j, long j2) {
            this.b.i = j;
            if (this.b.a(j)) {
                a(2);
            } else {
                this.b.b.b((com.nokia.maps.MapsEngine.k) this);
                this.b.j();
                new d(this, this.c, true) {
                    final /* synthetic */ e b;

                    protected void a(MapPackage mapPackage, ResultCode resultCode) {
                        this.b.b.b.b((com.nokia.maps.MapsEngine.k) this);
                        this.b.b.b(false);
                        this.b.b.a(resultCode);
                        for (Listener onInstallMapPackagesComplete : this.b.b.k) {
                            onInstallMapPackagesComplete.onInstallMapPackagesComplete(mapPackage, resultCode);
                        }
                    }
                }.e();
            }
            for (Listener onInstallationSize : this.b.k) {
                onInstallationSize.onInstallationSize(j, j2);
            }
        }

        public void a(String str, int i) {
            Runnable anonymousClass4 = new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        for (Integer num : this.a.c) {
                            for (MapPackage mapPackage : this.a.b.j) {
                                if (num.intValue() == mapPackage.getId()) {
                                    this.a.a(mapPackage, InstallationState.INSTALLED);
                                }
                            }
                        }
                        for (Integer num2 : this.a.g) {
                            for (MapPackage mapPackage2 : this.a.b.j) {
                                if (num2.intValue() == mapPackage2.getId()) {
                                    bu.a(mapPackage2).a(InstallationState.INSTALLED);
                                }
                            }
                        }
                        this.a.a(100);
                        this.a.a((MapPackage) this.a.b.j.get(0), ResultCode.OPERATION_SUCCESSFUL);
                    } catch (IndexOutOfBoundsException e) {
                        this.a.a(null, ResultCode.UNEXPECTED_ERROR);
                    }
                }
            };
            Runnable anonymousClass5 = new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.b.a(this.a.b.g);
                }
            };
            if (str.isEmpty() && i == 0) {
                a(null, ResultCode.OPERATION_CANCELLED);
            } else if (this.e) {
                a(anonymousClass4, anonymousClass5, i == 0);
            } else {
                a(null, ResultCode.NOT_ENOUGH_DISK_SPACE);
            }
        }
    }

    private abstract class m extends g {
        private List<Integer> a = new ArrayList();
        final /* synthetic */ bq b;
        private boolean c = false;
        private List<Integer> d = new ArrayList();

        protected abstract void a(MapPackage mapPackage, ResultCode resultCode);

        public m(bq bqVar, List<Integer> list) {
            this.b = bqVar;
            super();
            if (list == null || list.isEmpty()) {
                this.c = true;
            } else {
                this.a = list;
            }
        }

        protected void a() {
            super.a();
            a(null, ResultCode.FATAL_ERROR);
        }

        protected void b() {
            super.b();
            a(null, ResultCode.UNEXPECTED_ERROR);
        }

        protected void c() {
            super.c();
            a(null, ResultCode.OPERATION_CANCELLED);
        }

        protected void f() {
            super.f();
            a(null, ResultCode.INVALID_PARAMETERS);
        }

        protected void d() {
            super.d();
            a(null, ResultCode.NO_CONNECTIVITY);
        }

        public void e() {
            this.b.j();
            if (this.b.v.d()) {
                a(null, ResultCode.OPERATION_BUSY);
            } else if (!this.b.v.c()) {
                a(null, ResultCode.UNEXPECTED_ERROR);
            } else if (this.c) {
                a(null, ResultCode.INVALID_PARAMETERS);
            } else {
                synchronized (this.b.f) {
                    if (this.b.d) {
                        c();
                    } else {
                        this.b.b.a((com.nokia.maps.MapsEngine.k) this);
                        if (this.b.g != null) {
                            this.b.b.a(this.b.g);
                        } else {
                            bj.c(bq.a, "MapVersion is null", new Object[0]);
                            b();
                        }
                    }
                }
            }
        }

        public void a(MapPackageSelection mapPackageSelection) {
            for (Integer num : this.a) {
                if (!mapPackageSelection.c(num.intValue())) {
                    this.c = true;
                }
                a(mapPackageSelection, num);
            }
            if (this.c) {
                f();
            } else {
                this.b.b.G();
            }
        }

        private void a(MapPackageSelection mapPackageSelection, Integer num) {
            boolean z;
            Integer num2;
            Integer valueOf;
            Integer valueOf2 = Integer.valueOf(-1);
            Integer valueOf3 = Integer.valueOf(-1);
            if (this.b.v.c.containsKey(num)) {
                i iVar = (i) this.b.v.c.get(num);
                if (iVar != null) {
                    valueOf3 = iVar.a();
                    boolean b = iVar.b();
                    valueOf2 = Integer.valueOf(mapPackageSelection.getPackageIdFromIndex(valueOf3.intValue()));
                    Integer num3 = valueOf3;
                    z = b;
                    num2 = num3;
                    if (z) {
                        mapPackageSelection.c(valueOf2.intValue());
                        this.d.add(valueOf2);
                        for (int packageIdFromIndex : mapPackageSelection.getPackageChildrenIndices(r0.intValue())) {
                            valueOf = Integer.valueOf(mapPackageSelection.getPackageIdFromIndex(packageIdFromIndex));
                            if (!valueOf.equals(num)) {
                                mapPackageSelection.b(valueOf.intValue());
                            }
                        }
                        a(mapPackageSelection, valueOf2);
                    }
                }
            }
            num2 = valueOf3;
            z = false;
            if (z) {
                mapPackageSelection.c(valueOf2.intValue());
                this.d.add(valueOf2);
                while (r0 < r4) {
                    valueOf = Integer.valueOf(mapPackageSelection.getPackageIdFromIndex(packageIdFromIndex));
                    if (!valueOf.equals(num)) {
                        mapPackageSelection.b(valueOf.intValue());
                    }
                }
                a(mapPackageSelection, valueOf2);
            }
        }

        public void a(String str, int i) {
            a(new Runnable(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        for (Integer num : this.a.a) {
                            for (MapPackage mapPackage : this.a.b.j) {
                                if (num.intValue() == mapPackage.getId()) {
                                    this.a.a(mapPackage, InstallationState.NOT_INSTALLED);
                                }
                            }
                        }
                        for (Integer num2 : this.a.d) {
                            for (MapPackage mapPackage2 : this.a.b.j) {
                                if (num2.intValue() == mapPackage2.getId()) {
                                    bu.a(mapPackage2).a(InstallationState.NOT_INSTALLED);
                                }
                            }
                        }
                        this.a.a((MapPackage) this.a.b.j.get(0), ResultCode.OPERATION_SUCCESSFUL);
                    } catch (IndexOutOfBoundsException e) {
                        this.a.a(null, ResultCode.UNEXPECTED_ERROR);
                    }
                }
            }, new Runnable(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.b.a(this.a.b.g);
                }
            }, i == 0);
        }

        public void a(int i) {
            for (Listener onProgress : this.b.k) {
                onProgress.onProgress(i);
            }
        }
    }

    private abstract class b extends g {
        private final GeoCoordinate a;
        final /* synthetic */ bq b;

        protected abstract void a(MapPackage mapPackage, GeoCoordinate geoCoordinate, ResultCode resultCode);

        public b(bq bqVar, GeoCoordinate geoCoordinate) {
            this.b = bqVar;
            super();
            this.a = geoCoordinate;
        }

        public void e() {
            new ReverseGeocodeRequest2(this.a, Locale.ENGLISH).execute(new ResultListener<Location>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
                    a((Location) obj, errorCode);
                }

                public void a(Location location, ErrorCode errorCode) {
                    ResultCode resultCode;
                    ResultCode resultCode2 = ResultCode.OPERATION_SUCCESSFUL;
                    MapPackage mapPackage = null;
                    if (errorCode != ErrorCode.NONE) {
                        switch (errorCode) {
                            case OPERATION_NOT_ALLOWED:
                            case INVALID_CREDENTIALS:
                                resultCode = ResultCode.OPERATION_NOT_ALLOWED;
                                break;
                            case CANCEL:
                            case CANCELLED:
                                resultCode = ResultCode.OPERATION_CANCELLED;
                                break;
                            case SERVER_CONNECTION:
                            case NETWORK_COMMUNICATION:
                            case NETWORK_REQUIRED:
                            case NETWORK_SERVER:
                            case NETWORK_UNKNOWN:
                                resultCode = ResultCode.NO_CONNECTIVITY;
                                break;
                            default:
                                resultCode = ResultCode.UNEXPECTED_ERROR;
                                break;
                        }
                    }
                    if (location != null) {
                        mapPackage = this.a.a(location.getAddress());
                    }
                    resultCode = ResultCode.OPERATION_SUCCESSFUL;
                    this.a.a(mapPackage, this.a.a, resultCode);
                }
            });
        }

        private MapPackage a(Address address) {
            if (address == null || this.b.j.isEmpty()) {
                return null;
            }
            MapPackage mapPackage;
            MapPackage mapPackage2 = (MapPackage) this.b.j.get(0);
            String str = (String) bq.p.get(address.getCountryName());
            if (str == null) {
                str = (String) bq.p.get(address.getCountryCode());
            }
            if (str == null) {
                str = address.getCountryName();
            }
            loop0:
            for (MapPackage children : mapPackage2.getChildren()) {
                for (MapPackage mapPackage22 : children.getChildren()) {
                    if (str.equals(mapPackage22.getEnglishTitle())) {
                        mapPackage = mapPackage22;
                        break loop0;
                    }
                }
            }
            mapPackage = null;
            if (mapPackage != null) {
                String state;
                String str2 = (String) bq.q.get(address.getState());
                if (str2 == null) {
                    str2 = (String) bq.q.get(address.getStateCode());
                }
                if (str2 == null) {
                    state = address.getState();
                } else {
                    state = str2;
                }
                if (state != null) {
                    for (MapPackage mapPackage222 : mapPackage.getChildren()) {
                        if (state.equals(mapPackage222.getEnglishTitle())) {
                            break;
                        }
                    }
                }
                mapPackage222 = null;
                if (mapPackage222 == null) {
                    mapPackage222 = mapPackage;
                }
            } else {
                mapPackage222 = null;
            }
            return mapPackage222;
        }

        protected void c() {
            super.c();
            a(null, this.a, ResultCode.OPERATION_CANCELLED);
        }

        protected void a() {
            super.a();
            a(null, this.a, ResultCode.FATAL_ERROR);
        }

        protected void b() {
            super.b();
            a(null, this.a, ResultCode.UNEXPECTED_ERROR);
        }

        protected void d() {
            super.d();
            a(null, this.a, ResultCode.NO_CONNECTIVITY);
        }
    }

    private abstract class a extends f {
        final /* synthetic */ bq b;

        protected abstract void a(boolean z, ResultCode resultCode);

        private a(bq bqVar) {
            this.b = bqVar;
            super();
        }

        protected void a() {
            super.a();
            a(false, ResultCode.FATAL_ERROR);
        }

        protected void b() {
            super.b();
            a(false, ResultCode.SERVER_NOT_RESPONDING);
        }

        protected void c() {
            super.c();
            a(false, ResultCode.OPERATION_CANCELLED);
        }

        protected void d() {
            super.d();
            a(false, ResultCode.NO_CONNECTIVITY);
        }

        public void e() {
            synchronized (this.b.f) {
                if (this.b.d) {
                    c();
                } else {
                    this.b.j();
                    this.b.b.a((com.nokia.maps.MapsEngine.k) this);
                    this.b.b.F();
                }
            }
        }

        public void a(final String[] strArr, boolean z) {
            a(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.c = new ArrayList(Arrays.asList(strArr));
                    this.b.b.j();
                    if (this.b.g()) {
                        this.b.a(true, ResultCode.OPERATION_SUCCESSFUL);
                    } else {
                        this.b.a(false, ResultCode.OPERATION_SUCCESSFUL);
                    }
                }
            }, new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.b.F();
                }
            }, z);
        }
    }

    private abstract class d extends g {
        final /* synthetic */ bq a;
        private List<Integer> b = new ArrayList();
        private final boolean c;
        private int d = 0;

        protected abstract void a(MapPackage mapPackage, ResultCode resultCode);

        public d(bq bqVar, List<Integer> list, boolean z) {
            this.a = bqVar;
            super();
            this.b = list;
            this.c = z;
        }

        public void e() {
            this.a.j();
            ez.a(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.b.a(this.a);
                    if (this.a.a.g != null) {
                        this.a.a.b.a(this.a.a.g);
                        return;
                    }
                    bj.c(bq.a, "MapVersion is null", new Object[0]);
                    this.a.b();
                }
            }, 2000);
        }

        protected void a() {
            super.a();
            a(null, ResultCode.FATAL_ERROR);
        }

        protected void b() {
            super.b();
            a(null, ResultCode.UNEXPECTED_ERROR);
        }

        public void a(MapPackageSelection mapPackageSelection) {
            for (Integer num : this.b) {
                mapPackageSelection.c(num.intValue());
                a(mapPackageSelection, num);
            }
            this.a.b.G();
        }

        private void a(MapPackageSelection mapPackageSelection, Integer num) {
            if (this.a.v.b.indexOfKey(num.intValue()) >= 0) {
                h hVar = (h) this.a.v.b.get(num.intValue());
                if (hVar != null) {
                    List<Integer> a = hVar.a();
                    List<Integer> b = hVar.b();
                    for (Integer intValue : a) {
                        mapPackageSelection.b(intValue.intValue());
                    }
                    for (Integer intValue2 : b) {
                        a(mapPackageSelection, intValue2);
                    }
                }
            }
        }

        public void a(String str, int i) {
            a(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c) {
                        this.a.a(null, ResultCode.NOT_ENOUGH_DISK_SPACE);
                    } else {
                        this.a.a(null, ResultCode.OPERATION_CANCELLED);
                    }
                }
            }, new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.b.a(this.a.a.g);
                }
            }, i == 0);
        }

        protected void a(Runnable runnable, Runnable runnable2, boolean z) {
            if (z) {
                this.d = 0;
                runnable.run();
                return;
            }
            int i = this.d + 1;
            this.d = i;
            if (i <= 7) {
                ez.a(runnable2, 1000);
            } else {
                b();
            }
        }
    }

    private static class h {
        private List<Integer> a = new ArrayList();
        private List<Integer> b = new ArrayList();

        public void a(Integer num) {
            this.b.add(num);
        }

        public void b(Integer num) {
            this.a.add(num);
        }

        public List<Integer> a() {
            return this.b;
        }

        public List<Integer> b() {
            return this.a;
        }
    }

    private static class i {
        private boolean a;
        private Integer b;

        public i(Integer num, Boolean bool) {
            this.a = bool.booleanValue();
            this.b = num;
        }

        public Integer a() {
            return this.b;
        }

        public boolean b() {
            return this.a;
        }
    }

    private final class j extends g {
        final /* synthetic */ bq a;
        private final g b;

        public j(bq bqVar, g gVar) {
            this.a = bqVar;
            super();
            this.b = gVar;
        }

        public void e() {
            synchronized (this.a.f) {
                if (this.a.d) {
                    c();
                } else {
                    this.a.j();
                    this.a.b.a((com.nokia.maps.MapsEngine.k) this);
                    this.a.b.E();
                }
            }
        }

        protected void a() {
            this.a.b.b((com.nokia.maps.MapsEngine.k) this);
            this.b.a();
        }

        protected void c() {
            this.a.b.b((com.nokia.maps.MapsEngine.k) this);
            this.b.c();
        }

        protected void b() {
            this.a.b.b((com.nokia.maps.MapsEngine.k) this);
            this.b.b();
        }

        public void a(final String str, boolean z) {
            a(new Runnable(this) {
                final /* synthetic */ j b;

                public void run() {
                    this.b.a.b.b(this.b);
                    this.b.a.g = str;
                    this.b.b.e();
                }
            }, new Runnable(this) {
                final /* synthetic */ j a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.b.E();
                }
            }, z);
        }
    }

    private class k extends com.nokia.maps.MapsEngine.k.a {
        public boolean a;
        SparseArray<h> b;
        HashMap<Integer, i> c;
        final /* synthetic */ bq d;
        private String e;
        private boolean f;
        private boolean g;
        private Runnable h;
        private final List<Integer> i;

        private k(bq bqVar) {
            this.d = bqVar;
            this.a = true;
            this.f = false;
            this.g = false;
            this.h = null;
            this.i = new ArrayList();
            this.b = new SparseArray();
            this.c = new HashMap();
        }

        public void a(Runnable runnable) {
            if (MapsEngine.f().booleanValue() || this.f) {
                if (runnable != null) {
                    runnable.run();
                }
                if (this.d.u) {
                    this.d.b.cancelMapInstallation();
                    this.d.u = false;
                    return;
                }
                return;
            }
            this.d.b.a((com.nokia.maps.MapsEngine.k) this);
            this.h = runnable;
            this.a = this.d.b.beginODMLInstallation();
            if (!this.a) {
                this.h.run();
            }
        }

        public void a(MapPackageSelection mapPackageSelection, String str, boolean z, boolean z2) {
            this.d.b.b((com.nokia.maps.MapsEngine.k) this);
            this.g = z2;
            if (z) {
                this.e = str;
                this.f = true;
                this.d.j.clear();
                this.i.clear();
                this.b.clear();
                this.c.clear();
                this.d.m.clear();
                if (!(this.g || mapPackageSelection == null)) {
                    try {
                        a(mapPackageSelection);
                        this.d.a(mapPackageSelection, this.d.j);
                    } catch (IndexOutOfBoundsException e) {
                        this.f = false;
                        this.e = "";
                        this.d.j.clear();
                        this.i.clear();
                        this.b.clear();
                        this.c.clear();
                        this.d.m.clear();
                    }
                }
                if (this.g && this.d.u) {
                    this.d.b.cancelMapInstallation();
                    this.d.u = false;
                }
            } else {
                this.e = "";
                this.f = false;
            }
            Runnable runnable = this.h;
            this.h = null;
            if (runnable != null) {
                runnable.run();
            }
        }

        public void a() {
            this.f = false;
            this.e = "";
            if (!MapsEngine.f().booleanValue()) {
                this.d.b.endODMLInstallation();
            }
        }

        public List<Integer> b() {
            return this.i;
        }

        public boolean c() {
            return this.f;
        }

        public boolean d() {
            return this.g;
        }

        private void a(MapPackageSelection mapPackageSelection) {
            for (int i = 0; i < mapPackageSelection.getPackageCount(); i++) {
                boolean d = mapPackageSelection.d(i);
                boolean f = mapPackageSelection.f(i);
                boolean g = mapPackageSelection.g(i);
                int packageIdFromIndex = mapPackageSelection.getPackageIdFromIndex(i);
                if (d && !f) {
                    this.i.add(Integer.valueOf(packageIdFromIndex));
                }
                if (mapPackageSelection.getPackageChildrenCount(i) > 0) {
                    h hVar = new h();
                    Integer valueOf = Integer.valueOf(i);
                    if (f || g) {
                        d = true;
                    } else {
                        d = false;
                    }
                    i iVar = new i(valueOf, Boolean.valueOf(d));
                    for (int i2 : mapPackageSelection.getPackageChildrenIndices(i)) {
                        boolean f2 = mapPackageSelection.f(i2);
                        boolean hasChildPackageInstalled = mapPackageSelection.hasChildPackageInstalled(i2);
                        int i22 = mapPackageSelection.getPackageIdFromIndex(i22);
                        if (f2) {
                            hVar.a(Integer.valueOf(i22));
                        }
                        if (hasChildPackageInstalled) {
                            hVar.b(Integer.valueOf(i22));
                        }
                        this.c.put(Integer.valueOf(i22), iVar);
                    }
                    this.b.put(packageIdFromIndex, hVar);
                }
            }
        }
    }

    static {
        p.put("PRI", "USA");
        p.put("VIR", "USA");
        p.put(dji.pilot.flyunlimit.b.b, "USA");
        p.put("British Virgin Islands", "Virgin Islands");
        p.put("French-Reunion", "France");
        p.put("French-Guadeloupe", "France");
        p.put("French-Martinique", "France");
        p.put("French-Guiana", "France");
        p.put("Saint Barthelemy-France", "France");
        p.put("People's Republic of China", "China");
        q.put("VIR", "US Virgin Islands");
        q.put("Baden-Wurttemberg", "Baden-Wuerttemberg");
        q.put("Mecklenburg-Vorpommern", "Mecklenburg-Western Pomerania");
        q.put("Lower-Saxony", "Lower Saxony/Bremen");
        q.put("Bremen", "Lower Saxony/Bremen");
        q.put("North-Rhine-Westphalia", "North Rhine-Westphalia");
        q.put("Sachsen-Anhalt", "Saxony-Anhalt");
        q.put("Brandenburg", "Berlin/Brandenburg");
        q.put("Berlin", "Berlin/Brandenburg");
        q.put("Rhineland-Palatinate", "Rhineland-Palatinate/Saarland");
        q.put("Saarland", "Rhineland-Palatinate/Saarland");
        q.put("Schleswig-Holstein", "Schleswig-Holstein/Hamburg");
        q.put("Hamburg", "Schleswig-Holstein/Hamburg");
        q.put("Principality of Asturias", "Asturias");
        q.put("Chartered Community of Navarre", "Navarre");
        q.put("Castilla and Leon", "Castile and Leon");
        q.put("Community of Madrid", "Madrid");
        q.put("Castille-la Mancha", "Castilla la Mancha");
        q.put("Illes Balears", "Balearic Islands");
        q.put("Murcia Region", "Murcia");
        q.put("Autonomous City of Ceuta", "Ceuta");
        q.put("Autonomous City of Melilla", "Melilla");
        q.put("Mid-Pyrenees", "Midi-Pyrenees");
        q.put("Loire Region", "Pays-de-la-loire");
        q.put("Lower Normandy", "Normandy");
        q.put("Upper Normandy", "Normandy");
        q.put("Picardie", "Paris-Isle-of-France/Picardy");
        q.put("Ile-de-France", "Paris-Isle-of-France/Picardy");
        q.put("Provence-Alpes-Cote D'Azur", "Provence-Alpes-Azur");
        q.put("Guyane", "French Guiana");
        q.put("Saint-Barthélemy", "Saint Barthelemy");
        q.put("Aosta Valley", "Valle d'Aosta");
        q.put("Piedmont", "Piemonte");
        q.put("Tuscany", "Toscana");
        q.put("Marches", "Marche");
        q.put("Latium", "Lazio");
        q.put("Apulia", "Puglia");
        q.put("Sicily", "Sicilia");
        q.put("Sardinia", "Sardegna");
    }

    public static bq a() {
        if (r == null) {
            synchronized (s) {
                if (r == null) {
                    r = new bq();
                }
            }
        }
        return r;
    }

    private bq() {
        try {
            this.b = MapsEngine.c();
        } catch (Exception e) {
            bj.c(a, "MapLoader instace was requested before MapsEngine was initialized.", new Object[0]);
        }
        if (this.b == null) {
            throw new UnintializedMapEngineException();
        }
        this.n = l.a();
        this.c = true;
        ApplicationContext.b().check(21, this.t);
    }

    public void a(Listener listener) {
        if (listener != null && !this.k.contains(listener)) {
            this.k.add(listener);
        }
    }

    public void b(Listener listener) {
        this.k.remove(listener);
    }

    public void a(MapPackageAtCoordinateListener mapPackageAtCoordinateListener) {
        if (mapPackageAtCoordinateListener != null && !this.l.contains(mapPackageAtCoordinateListener)) {
            this.l.add(mapPackageAtCoordinateListener);
        }
    }

    public void b(MapPackageAtCoordinateListener mapPackageAtCoordinateListener) {
        this.l.remove(mapPackageAtCoordinateListener);
    }

    private boolean a(boolean z) {
        if (!this.c) {
            return false;
        }
        synchronized (this.f) {
            if (!this.d) {
                this.d = true;
                this.e = z;
                j();
            }
        }
        return true;
    }

    public synchronized boolean b() {
        return a(false);
    }

    public synchronized boolean c() {
        boolean a;
        if (this.o) {
            a = a(new c(this) {
                final /* synthetic */ bq a;

                {
                    this.a = r2;
                }

                protected void a(MapPackage mapPackage, ResultCode resultCode) {
                    boolean z;
                    this.a.b.b((com.nokia.maps.MapsEngine.k) this);
                    this.a.b(false);
                    if (resultCode == ResultCode.OPERATION_CANCELLED) {
                        this.a.d = false;
                    }
                    for (Listener onGetMapPackagesComplete : this.a.k) {
                        onGetMapPackagesComplete.onGetMapPackagesComplete(mapPackage, resultCode);
                    }
                    p c = this.a.n;
                    if (resultCode != ResultCode.OPERATION_SUCCESSFUL) {
                        z = true;
                    } else {
                        z = false;
                    }
                    c.a(z);
                }
            }, true);
        } else {
            for (Listener onGetMapPackagesComplete : this.k) {
                onGetMapPackagesComplete.onGetMapPackagesComplete(null, ResultCode.OPERATION_NOT_ALLOWED);
            }
            a = false;
        }
        return a;
    }

    private void a(ResultCode resultCode) {
        if (!ck.b()) {
            com.here.android.mpa.a.a.a().a(cj.a("map-loader", "transfer-finished"), 0.0d, (double) this.i, resultCode == ResultCode.OPERATION_SUCCESSFUL);
        }
    }

    public synchronized boolean a(List<Integer> list) {
        boolean a;
        if (this.o) {
            a = a(new e(this, list) {
                final /* synthetic */ bq a;

                protected void a(MapPackage mapPackage, ResultCode resultCode) {
                    boolean z;
                    this.a.b.b((com.nokia.maps.MapsEngine.k) this);
                    this.a.b(false);
                    for (Listener onInstallMapPackagesComplete : this.a.k) {
                        onInstallMapPackagesComplete.onInstallMapPackagesComplete(mapPackage, resultCode);
                    }
                    this.a.a(resultCode);
                    p c = this.a.n;
                    if (resultCode != ResultCode.OPERATION_SUCCESSFUL) {
                        z = true;
                    } else {
                        z = false;
                    }
                    c.b(z);
                }
            }, true);
        } else {
            for (Listener onInstallMapPackagesComplete : this.k) {
                onInstallMapPackagesComplete.onInstallMapPackagesComplete(null, ResultCode.OPERATION_NOT_ALLOWED);
            }
            a = false;
        }
        return a;
    }

    public synchronized boolean b(List<Integer> list) {
        boolean a;
        if (this.o) {
            a = a(new m(this, list) {
                final /* synthetic */ bq a;

                protected void a(MapPackage mapPackage, ResultCode resultCode) {
                    boolean z;
                    this.a.b.b((com.nokia.maps.MapsEngine.k) this);
                    this.a.b(false);
                    for (Listener onUninstallMapPackagesComplete : this.a.k) {
                        onUninstallMapPackagesComplete.onUninstallMapPackagesComplete(mapPackage, resultCode);
                    }
                    p c = this.a.n;
                    if (resultCode != ResultCode.OPERATION_SUCCESSFUL) {
                        z = true;
                    } else {
                        z = false;
                    }
                    c.c(z);
                }
            }, true);
        } else {
            for (Listener onUninstallMapPackagesComplete : this.k) {
                onUninstallMapPackagesComplete.onUninstallMapPackagesComplete(null, ResultCode.OPERATION_NOT_ALLOWED);
            }
            a = false;
        }
        return a;
    }

    public synchronized boolean a(final GeoCoordinate geoCoordinate) {
        boolean a;
        if (geoCoordinate != null) {
            if (geoCoordinate.isValid()) {
                if (this.o) {
                    a = a(new c(this) {
                        final /* synthetic */ bq b;

                        protected void a(MapPackage mapPackage, ResultCode resultCode) {
                            this.b.b.b((com.nokia.maps.MapsEngine.k) this);
                            this.b.b(false);
                            if (resultCode == ResultCode.OPERATION_CANCELLED) {
                                this.b.d = false;
                            }
                            if (resultCode != ResultCode.OPERATION_SUCCESSFUL) {
                                for (MapPackageAtCoordinateListener onGetMapPackageAtCoordinateComplete : this.b.l) {
                                    onGetMapPackageAtCoordinateComplete.onGetMapPackageAtCoordinateComplete(null, geoCoordinate, resultCode);
                                }
                            } else if (!this.b.b(geoCoordinate)) {
                                for (MapPackageAtCoordinateListener onGetMapPackageAtCoordinateComplete2 : this.b.l) {
                                    onGetMapPackageAtCoordinateComplete2.onGetMapPackageAtCoordinateComplete(null, geoCoordinate, ResultCode.OPERATION_BUSY);
                                }
                            }
                        }
                    }, true);
                } else {
                    for (MapPackageAtCoordinateListener onGetMapPackageAtCoordinateComplete : this.l) {
                        onGetMapPackageAtCoordinateComplete.onGetMapPackageAtCoordinateComplete(null, null, ResultCode.OPERATION_NOT_ALLOWED);
                    }
                    a = false;
                }
            }
        }
        for (MapPackageAtCoordinateListener onGetMapPackageAtCoordinateComplete2 : this.l) {
            onGetMapPackageAtCoordinateComplete2.onGetMapPackageAtCoordinateComplete(null, geoCoordinate, ResultCode.INVALID_PARAMETERS);
        }
        a = false;
        return a;
    }

    private boolean b(GeoCoordinate geoCoordinate) {
        return a(new b(this, geoCoordinate) {
            final /* synthetic */ bq a;

            protected void a(MapPackage mapPackage, GeoCoordinate geoCoordinate, ResultCode resultCode) {
                this.a.b.b((com.nokia.maps.MapsEngine.k) this);
                this.a.b(false);
                for (MapPackageAtCoordinateListener onGetMapPackageAtCoordinateComplete : this.a.l) {
                    onGetMapPackageAtCoordinateComplete.onGetMapPackageAtCoordinateComplete(mapPackage, geoCoordinate, resultCode);
                }
            }
        }, true);
    }

    public synchronized boolean d() {
        boolean a;
        if (this.o) {
            a = a(new a(this) {
                final /* synthetic */ bq a;

                {
                    this.a = r2;
                }

                protected void a(boolean z, ResultCode resultCode) {
                    this.a.b.b((com.nokia.maps.MapsEngine.k) this);
                    this.a.b(false);
                    if (z) {
                        for (Listener onCheckForUpdateComplete : this.a.k) {
                            onCheckForUpdateComplete.onCheckForUpdateComplete(z, this.a.g, this.a.h, resultCode);
                        }
                        return;
                    }
                    for (Listener onCheckForUpdateComplete2 : this.a.k) {
                        onCheckForUpdateComplete2.onCheckForUpdateComplete(z, this.a.g, this.a.g, resultCode);
                    }
                }
            }, true);
        } else {
            for (Listener onCheckForUpdateComplete : this.k) {
                onCheckForUpdateComplete.onCheckForUpdateComplete(false, this.g, this.g, ResultCode.OPERATION_NOT_ALLOWED);
            }
            a = false;
        }
        return a;
    }

    public synchronized boolean e() {
        boolean a;
        if (this.o) {
            a = a(new l(this) {
                final /* synthetic */ bq a;

                {
                    this.a = r2;
                }

                protected void a(MapPackage mapPackage, ResultCode resultCode) {
                    boolean z;
                    this.a.b.b((com.nokia.maps.MapsEngine.k) this);
                    this.a.b(false);
                    for (Listener onPerformMapDataUpdateComplete : this.a.k) {
                        onPerformMapDataUpdateComplete.onPerformMapDataUpdateComplete(mapPackage, resultCode);
                    }
                    p c = this.a.n;
                    if (resultCode != ResultCode.OPERATION_SUCCESSFUL) {
                        z = true;
                    } else {
                        z = false;
                    }
                    c.d(z);
                }
            }, true);
        } else {
            for (Listener onPerformMapDataUpdateComplete : this.k) {
                onPerformMapDataUpdateComplete.onPerformMapDataUpdateComplete(null, ResultCode.OPERATION_NOT_ALLOWED);
            }
            a = false;
        }
        return a;
    }

    private boolean a(final g gVar, final boolean z) {
        if (this.c) {
            return false;
        }
        b(true);
        this.d = false;
        this.e = false;
        this.v.a(new Runnable(this) {
            final /* synthetic */ bq c;

            public void run() {
                if (!this.c.v.a) {
                    gVar.a();
                } else if (!z || this.c.i()) {
                    this.c.a(gVar);
                } else {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            gVar.d();
                        }
                    });
                }
            }
        });
        return true;
    }

    private void a(g gVar) {
        if (this.g == null) {
            new j(this, gVar).e();
        } else {
            gVar.e();
        }
    }

    private void b(boolean z) {
        if (this.c != z) {
            this.c = z;
            if (this.c) {
                this.b.u();
                return;
            }
            this.b.t();
            this.v.a();
        }
    }

    private boolean i() {
        boolean z = false;
        try {
            z = MapsEngine.c().isOnline();
        } catch (Exception e) {
            bj.b(a, "MapLoader operation was called before maps engine was initialized", new Object[z]);
        }
        return z;
    }

    private void j() {
        this.b.cancelMapInstallation();
        this.b.cancelCompatibleMapVersionQuery();
    }

    private boolean a(long j) {
        StatFs statFs = new StatFs(MapSettings.a());
        if (((double) ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1024)) * 0.95d < ((double) j)) {
            return false;
        }
        return true;
    }

    private void a(MapPackageSelection mapPackageSelection, List<MapPackage> list) throws IndexOutOfBoundsException {
        mapPackageSelection.a("010");
        String[] packageNames = mapPackageSelection.getPackageNames();
        mapPackageSelection.a();
        String[] packageNames2 = mapPackageSelection.getPackageNames();
        MapPackage a = a(mapPackageSelection, 0, packageNames2, packageNames);
        list.add(a);
        a(a, mapPackageSelection, list, packageNames2, packageNames);
    }

    private void a(MapPackage mapPackage, MapPackageSelection mapPackageSelection, List<MapPackage> list, String[] strArr, String[] strArr2) throws IndexOutOfBoundsException {
        InstallationState installationState = mapPackage.getInstallationState();
        for (int i : mapPackageSelection.getPackageChildrenIndices(bu.a(mapPackage).h())) {
            MapPackage a = a(mapPackageSelection, i, strArr, strArr2);
            if (installationState == InstallationState.INSTALLED || installationState == InstallationState.PARTIALLY_INSTALLED) {
                bu.a(a).a(installationState);
            }
            bu.a(mapPackage).b.add(a);
            bu.a(a).a = mapPackage;
            list.add(a);
            if (mapPackageSelection.getPackageChildrenIndices(i).length != 0) {
                a(a, mapPackageSelection, list, strArr, strArr2);
            }
        }
    }

    private MapPackage a(MapPackageSelection mapPackageSelection, int i, String[] strArr, String[] strArr2) throws IndexOutOfBoundsException {
        MapPackage a = bu.a(new bu());
        bu a2 = bu.a(a);
        a2.d = i;
        a2.c = mapPackageSelection.getPackageIdFromIndex(i);
        a2.e = strArr[i];
        a2.f = strArr2[i];
        a2.g = mapPackageSelection.a(i);
        if (mapPackageSelection.f(i)) {
            a2.a(InstallationState.INSTALLED);
        } else if (mapPackageSelection.e(i)) {
            a2.a(InstallationState.PARTIALLY_INSTALLED);
        } else {
            a2.a(InstallationState.NOT_INSTALLED);
        }
        return a;
    }
}
