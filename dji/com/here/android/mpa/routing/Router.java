package com.here.android.mpa.routing;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public interface Router<T, S extends Enum<?>> {

    @HybridPlus
    public interface Listener<T, S extends Enum<?>> {
        void onCalculateRouteFinished(T t, S s);

        void onProgress(int i);
    }

    void calculateRoute(RoutePlan routePlan, Listener<T, S> listener);

    void cancel();

    boolean isBusy();
}
