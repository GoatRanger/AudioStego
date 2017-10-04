package com.here.android.mpa.odml;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.Hybrid;
import com.nokia.maps.bq;
import java.util.List;

public final class MapLoader {
    private static volatile MapLoader a = null;
    private static Object b = new Object();
    private bq c = bq.a();

    @Hybrid
    public interface Listener {

        @Hybrid
        public static abstract class Adapter implements Listener {
            public void onProgress(int i) {
            }

            public void onInstallationSize(long j, long j2) {
            }

            public void onCheckForUpdateComplete(boolean z, String str, String str2, ResultCode resultCode) {
            }

            public void onGetMapPackagesComplete(MapPackage mapPackage, ResultCode resultCode) {
            }

            public void onInstallMapPackagesComplete(MapPackage mapPackage, ResultCode resultCode) {
            }

            public void onPerformMapDataUpdateComplete(MapPackage mapPackage, ResultCode resultCode) {
            }

            public void onUninstallMapPackagesComplete(MapPackage mapPackage, ResultCode resultCode) {
            }
        }

        void onCheckForUpdateComplete(boolean z, String str, String str2, ResultCode resultCode);

        void onGetMapPackagesComplete(MapPackage mapPackage, ResultCode resultCode);

        void onInstallMapPackagesComplete(MapPackage mapPackage, ResultCode resultCode);

        void onInstallationSize(long j, long j2);

        void onPerformMapDataUpdateComplete(MapPackage mapPackage, ResultCode resultCode);

        void onProgress(int i);

        void onUninstallMapPackagesComplete(MapPackage mapPackage, ResultCode resultCode);
    }

    @Hybrid
    public interface MapPackageAtCoordinateListener {
        void onGetMapPackageAtCoordinateComplete(MapPackage mapPackage, GeoCoordinate geoCoordinate, ResultCode resultCode);
    }

    @Hybrid
    public enum ResultCode {
        OPERATION_SUCCESSFUL,
        INVALID_PARAMETERS,
        NO_CONNECTIVITY,
        NO_UPDATE_TO_PERFORM,
        NOT_ENOUGH_DISK_SPACE,
        OPERATION_CANCELLED,
        SERVER_NOT_RESPONDING,
        UNEXPECTED_ERROR,
        FATAL_ERROR,
        OPERATION_BUSY,
        OPERATION_NOT_ALLOWED
    }

    @Hybrid
    public static MapLoader getInstance() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new MapLoader();
                }
            }
        }
        return a;
    }

    @Hybrid
    public void addListener(Listener listener) {
        this.c.a(listener);
    }

    @Hybrid
    public void removeListener(Listener listener) {
        this.c.b(listener);
    }

    @Hybrid
    public void addMapPackageAtCoordinateListener(MapPackageAtCoordinateListener mapPackageAtCoordinateListener) {
        this.c.a(mapPackageAtCoordinateListener);
    }

    @Hybrid
    public void removeMapPackageAtCoordinateListener(MapPackageAtCoordinateListener mapPackageAtCoordinateListener) {
        this.c.b(mapPackageAtCoordinateListener);
    }

    @Hybrid
    public boolean getMapPackages() {
        return this.c.c();
    }

    @Hybrid
    public boolean installMapPackages(List<Integer> list) {
        return this.c.a((List) list);
    }

    @Hybrid
    public boolean uninstallMapPackages(List<Integer> list) {
        return this.c.b((List) list);
    }

    @Hybrid
    public boolean getMapPackageAtCoordinate(GeoCoordinate geoCoordinate) {
        return this.c.a(geoCoordinate);
    }

    @Hybrid
    public boolean checkForMapDataUpdate() {
        return this.c.d();
    }

    @Hybrid
    public boolean performMapDataUpdate() {
        return this.c.e();
    }

    @Hybrid
    public boolean cancelCurrentOperation() {
        return this.c.b();
    }

    private MapLoader() {
    }
}
