package dji.pilot2.usercenter.activate;

public interface b {
    boolean canGoNext();

    boolean canGoPre();

    boolean canShowTopView();

    boolean handleGoNext();

    boolean handleGoPre();

    boolean onResume();

    boolean onShow();

    void setMainTopViewControl(g gVar);
}
