package dji.pilot.playback.litchi;

import android.support.v4.view.ViewPager.OnPageChangeListener;

class DJIPlayBackPhotoPreviewActivity$8 implements OnPageChangeListener {
    final /* synthetic */ DJIPlayBackPhotoPreviewActivity a;

    DJIPlayBackPhotoPreviewActivity$8(DJIPlayBackPhotoPreviewActivity dJIPlayBackPhotoPreviewActivity) {
        this.a = dJIPlayBackPhotoPreviewActivity;
    }

    public void onPageSelected(int i) {
        DJIPlayBackPhotoPreviewActivity.a(this.a, i);
        DJIPlayBackPhotoPreviewActivity.b(this.a, i);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageScrollStateChanged(int i) {
    }
}
