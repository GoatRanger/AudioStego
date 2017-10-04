package dji.pilot.playback.litchi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.ortiz.touch.TouchImageView;
import dji.logic.album.a.b.f;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.R;
import dji.pilot.usercenter.mode.PhotoPreviewInfo;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.main.a.a;
import dji.pilot2.publics.b.a$e;

@SuppressLint({"InflateParams"})
final class DJIPlayBackPhotoPreviewActivity$a extends PagerAdapter {
    final /* synthetic */ DJIPlayBackPhotoPreviewActivity a;
    private final LayoutInflater b;

    public DJIPlayBackPhotoPreviewActivity$a(DJIPlayBackPhotoPreviewActivity dJIPlayBackPhotoPreviewActivity, LayoutInflater layoutInflater) {
        this.a = dJIPlayBackPhotoPreviewActivity;
        this.b = layoutInflater;
    }

    public int getCount() {
        return DJIPlayBackPhotoPreviewActivity.h(this.a) == null ? 0 : DJIPlayBackPhotoPreviewActivity.h(this.a).size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int length = i % DJIPlayBackPhotoPreviewActivity.o(this.a).length;
        if (viewGroup.getChildCount() == DJIPlayBackPhotoPreviewActivity.o(this.a).length) {
            viewGroup.removeView(DJIPlayBackPhotoPreviewActivity.o(this.a)[length]);
        }
        if (DJIPlayBackPhotoPreviewActivity.o(this.a)[length].getParent() != null) {
            viewGroup.removeView(DJIPlayBackPhotoPreviewActivity.o(this.a)[length]);
        }
        DJIPlayBackPhotoPreviewActivity$b dJIPlayBackPhotoPreviewActivity$b = (DJIPlayBackPhotoPreviewActivity$b) DJIPlayBackPhotoPreviewActivity.o(this.a)[length].getTag();
        final ProgressBar progressBar = dJIPlayBackPhotoPreviewActivity$b.a;
        progressBar.setVisibility(8);
        ProgressBar progressBar2 = dJIPlayBackPhotoPreviewActivity$b.b;
        if (DJIPlayBackPhotoPreviewActivity.o(this.a)[length].getChildCount() >= 2) {
            DJIPlayBackPhotoPreviewActivity.o(this.a)[length].removeViewAt(0);
        }
        dJIPlayBackPhotoPreviewActivity$b.c = (TouchImageView) this.b.inflate(R.layout.photo_preview_item_img, null);
        dJIPlayBackPhotoPreviewActivity$b.c.setSoundEffectsEnabled(false);
        dJIPlayBackPhotoPreviewActivity$b.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPlayBackPhotoPreviewActivity$a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DJIPlayBackPhotoPreviewActivity.p(this.a.a);
            }
        });
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        DJIPlayBackPhotoPreviewActivity.o(this.a)[length].addView(dJIPlayBackPhotoPreviewActivity$b.c, 0, layoutParams);
        dJIPlayBackPhotoPreviewActivity$b.c.setImageDrawable(null);
        if (DJIPlayBackPhotoPreviewActivity.i(this.a) == 1) {
            DJIPlayBackPhotoPreviewActivity.y(this.a).displayImage(((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).o, dJIPlayBackPhotoPreviewActivity$b.c, DJIPlayBackPhotoPreviewActivity.x(this.a), new ImageLoadingListener(this) {
                final /* synthetic */ DJIPlayBackPhotoPreviewActivity$a b;

                public void onLoadingStarted(String str, View view) {
                    progressBar.setVisibility(0);
                    progressBar.setMax(100);
                    progressBar.setProgress(0);
                }

                public void onLoadingFailed(String str, View view, FailReason failReason) {
                    progressBar.setVisibility(8);
                }

                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    progressBar.setVisibility(8);
                }

                public void onLoadingCancelled(String str, View view) {
                    progressBar.setVisibility(8);
                }
            }, new ImageLoadingProgressListener(this) {
                final /* synthetic */ DJIPlayBackPhotoPreviewActivity$a b;

                public void onProgressUpdate(String str, View view, int i, int i2) {
                    if (i >= i2) {
                        i = i2;
                    }
                    int i3 = i2 == 0 ? 0 : (i * 100) / i2;
                    progressBar.setMax(100);
                    progressBar.setProgress(i3);
                }
            });
        } else {
            progressBar2.setVisibility(0);
            DJIAlbumFileInfo dJIAlbumFileInfo = new DJIAlbumFileInfo();
            dJIAlbumFileInfo.b = ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).s;
            dJIAlbumFileInfo.c = ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).t;
            dJIAlbumFileInfo.j = f.find(((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).v);
            dJIAlbumFileInfo.d = ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).u;
            dJIAlbumFileInfo.a = ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).q;
            dJIAlbumFileInfo.k = ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).w;
            dJIAlbumFileInfo.l = ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).y;
            dJIAlbumFileInfo.g = ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(i)).x;
            Log.d(this.a.TAG, "" + i);
            Log.d(this.a.TAG, "" + dJIAlbumFileInfo.j);
            if (dJIAlbumFileInfo.j == f.e) {
                DJIPlayBackPhotoPreviewActivity.y(this.a).displayImage(a$e.a + h.e + h.d + "/DJIPANO_" + dJIAlbumFileInfo.c + a.n, dJIPlayBackPhotoPreviewActivity$b.c, DJIPlayBackPhotoPreviewActivity.x(this.a), null);
                progressBar2.setVisibility(8);
            } else if (dJIAlbumFileInfo.j == f.d || dJIAlbumFileInfo.j == f.c) {
                if (DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC6310) {
                    if (DJIPlayBackPhotoPreviewActivity.z(this.a).b(dJIAlbumFileInfo.d())) {
                        dJIPlayBackPhotoPreviewActivity$b.c.setImageBitmap(DJIPlayBackPhotoPreviewActivity.z(this.a).a(dJIAlbumFileInfo.d()));
                        progressBar2.setVisibility(8);
                    } else if (DJIPlayBackPhotoPreviewActivity.z(this.a).d(dJIAlbumFileInfo.d())) {
                        dJIPlayBackPhotoPreviewActivity$b.c.setImageBitmap(DJIPlayBackPhotoPreviewActivity.z(this.a).c(dJIAlbumFileInfo.d()));
                        progressBar2.setVisibility(8);
                    } else {
                        dJIPlayBackPhotoPreviewActivity$b.c.setImageDrawable(this.a.getResources().getDrawable(R.drawable.black_drawable));
                        DJIPlayBackPhotoPreviewActivity.A(this.a).a(dJIPlayBackPhotoPreviewActivity$b.c, progressBar2, dJIAlbumFileInfo, this.a.getApplicationContext());
                    }
                } else if (DJIPlayBackPhotoPreviewActivity.z(this.a).b(dJIAlbumFileInfo.c())) {
                    dJIPlayBackPhotoPreviewActivity$b.c.setImageBitmap(DJIPlayBackPhotoPreviewActivity.z(this.a).a(dJIAlbumFileInfo.c()));
                    progressBar2.setVisibility(8);
                } else if (DJIPlayBackPhotoPreviewActivity.z(this.a).d(dJIAlbumFileInfo.c())) {
                    dJIPlayBackPhotoPreviewActivity$b.c.setImageBitmap(DJIPlayBackPhotoPreviewActivity.z(this.a).c(dJIAlbumFileInfo.c()));
                    progressBar2.setVisibility(8);
                } else {
                    DJIPlayBackPhotoPreviewActivity.A(this.a).a(dJIPlayBackPhotoPreviewActivity$b.c, progressBar2, dJIAlbumFileInfo);
                }
            } else if (c.b(dJIAlbumFileInfo.b())) {
                DJIPlayBackPhotoPreviewActivity.y(this.a).displayImage(a$e.a + h.e + h.d + d.t + dJIAlbumFileInfo.b(), dJIPlayBackPhotoPreviewActivity$b.c, DJIPlayBackPhotoPreviewActivity.x(this.a), null);
                progressBar2.setVisibility(8);
            } else if (DJIPlayBackPhotoPreviewActivity.z(this.a).b(dJIAlbumFileInfo.d())) {
                dJIPlayBackPhotoPreviewActivity$b.c.setImageBitmap(DJIPlayBackPhotoPreviewActivity.z(this.a).a(dJIAlbumFileInfo.d()));
                progressBar2.setVisibility(8);
            } else if (DJIPlayBackPhotoPreviewActivity.z(this.a).d(dJIAlbumFileInfo.d())) {
                dJIPlayBackPhotoPreviewActivity$b.c.setImageBitmap(DJIPlayBackPhotoPreviewActivity.z(this.a).c(dJIAlbumFileInfo.d()));
                progressBar2.setVisibility(8);
            } else {
                dJIPlayBackPhotoPreviewActivity$b.c.setImageDrawable(this.a.getResources().getDrawable(R.drawable.black_drawable));
                DJIPlayBackPhotoPreviewActivity.A(this.a).a(dJIPlayBackPhotoPreviewActivity$b.c, progressBar2, dJIAlbumFileInfo, this.a.getApplicationContext());
            }
            if (dJIPlayBackPhotoPreviewActivity$b.c != null) {
                if (dJIAlbumFileInfo.g == 1 && (dJIAlbumFileInfo.j == f.c || dJIAlbumFileInfo.j == f.d)) {
                    dJIPlayBackPhotoPreviewActivity$b.c.setRotation(90.0f);
                } else {
                    dJIPlayBackPhotoPreviewActivity$b.c.setRotation(0.0f);
                }
            }
        }
        viewGroup.addView(DJIPlayBackPhotoPreviewActivity.o(this.a)[length]);
        DJIPlayBackPhotoPreviewActivity.b(this.a).setObjectForPosition(DJIPlayBackPhotoPreviewActivity.o(this.a)[length], i);
        return DJIPlayBackPhotoPreviewActivity.o(this.a)[length];
    }
}
