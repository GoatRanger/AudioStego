package dji.pilot.fpv.camera.control;

import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.pilot.publics.objects.h;

public interface b {
    public static final long a = 700;
    public static final int b = 3;
    public static final String c = "key_focus_tip";

    public interface a extends h<b> {
        void a();

        void a(int i);

        void b();

        boolean b(int i);

        void c();

        boolean c(int i);

        void d();

        void e();
    }

    public interface b extends dji.pilot.newfpv.h<dji.pilot.newfpv.f.b> {
        void handleMenuEvent(boolean z);

        void handleVisibilityEvent(boolean z);

        void hidePIVView();

        void hideRecordingView();

        void hideStoringView();

        void setPIVViewEnable(boolean z);

        void setPhotoViewEnable(boolean z);

        void setPlayBackViewEnable(boolean z);

        void setSelfEnable(boolean z);

        void setSettingViewEnable(boolean z);

        void setSwitchViewEnable(boolean z);

        void setVideoViewEnable(boolean z);

        void showPIVView();

        void showPhotoView();

        void showRecordingView();

        void showStoringView();

        void showVideoView();

        void startAnimVideo();

        void startTakePhoto(TYPE type, int i);

        void stopAnimVideo();

        void updatePhotoView(int i, int i2);

        void updateSettingView(int i, int i2);

        void updateVideoTime(String str);
    }
}
