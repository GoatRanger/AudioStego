package lecho.lib.hellocharts.view;

import lecho.lib.hellocharts.d.g;
import lecho.lib.hellocharts.e.m;
import lecho.lib.hellocharts.g.b;
import lecho.lib.hellocharts.g.d;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.f;
import lecho.lib.hellocharts.model.n;

public interface a {
    void animationDataFinished();

    void animationDataUpdate(float f);

    void callTouchListener();

    void cancelDataAnimation();

    b getAxesRenderer();

    lecho.lib.hellocharts.b.a getChartComputator();

    f getChartData();

    d getChartRenderer();

    Viewport getCurrentViewport();

    float getMaxZoom();

    Viewport getMaximumViewport();

    n getSelectedValue();

    lecho.lib.hellocharts.d.b getTouchHandler();

    float getZoomLevel();

    g getZoomType();

    boolean isContainerScrollEnabled();

    boolean isInteractive();

    boolean isScrollEnabled();

    boolean isValueSelectionEnabled();

    boolean isValueTouchEnabled();

    boolean isViewportCalculationEnabled();

    boolean isZoomEnabled();

    void moveTo(float f, float f2);

    void moveToWithAnimation(float f, float f2);

    void resetViewports();

    void selectValue(n nVar);

    void setChartRenderer(d dVar);

    void setContainerScrollEnabled(boolean z, lecho.lib.hellocharts.d.d dVar);

    void setCurrentViewport(Viewport viewport);

    void setCurrentViewportWithAnimation(Viewport viewport);

    void setCurrentViewportWithAnimation(Viewport viewport, long j);

    void setDataAnimationListener(lecho.lib.hellocharts.a.a aVar);

    void setInteractive(boolean z);

    void setMaxZoom(float f);

    void setMaximumViewport(Viewport viewport);

    void setScrollEnabled(boolean z);

    void setValueSelectionEnabled(boolean z);

    void setValueTouchEnabled(boolean z);

    void setViewportAnimationListener(lecho.lib.hellocharts.a.a aVar);

    void setViewportCalculationEnabled(boolean z);

    void setViewportChangeListener(m mVar);

    void setZoomEnabled(boolean z);

    void setZoomLevel(float f, float f2, float f3);

    void setZoomLevelWithAnimation(float f, float f2, float f3);

    void setZoomType(g gVar);

    void startDataAnimation();

    void startDataAnimation(long j);
}
