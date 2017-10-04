package dji.device.timelapse;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import dji.device.common.DJIUIEventManagerLongan.f;
import dji.device.common.DJIUIEventManagerLongan.i;
import dji.device.common.DJIUIEventManagerLongan.k;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.device.timelapse.LonganTimelapseMotionPhotoView.a;
import dji.device.timelapse.b.b;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class LonganTimelapseMotionLayout extends DJIRoundLinearLayoutCompat implements OnClickListener {
    LinearLayout a;
    RelativeLayout b;
    LinearLayout c;
    ImageView d;
    TextView e;
    HorizontalScrollView f;
    ScrollView g;
    LayoutParams h;
    RelativeLayout.LayoutParams i;
    FrameLayout.LayoutParams j;
    LayoutParams k;
    ArrayList<LonganTimelapseMotionPhotoView> l = null;
    b m = b.getInstance();
    final int n = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_width_land);
    final int o = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_height_land);
    final int p = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_width_port);
    final int q = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_height_port);
    final int r = getResources().getDimensionPixelOffset(R.dimen.longan_timelapse_motion_dur_btn_width);
    final int s = getResources().getDimensionPixelOffset(R.dimen.longan_timelapse_motion_dur_btn_height);
    final int t = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_margin);

    public LonganTimelapseMotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setHasFrame(true);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b();
        c();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        f();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
    }

    private void a() {
        this.l = new ArrayList();
        this.a = (LinearLayout) findViewById(R.id.longan_timelapse_motion_photo_ly);
        this.b = (RelativeLayout) findViewById(R.id.longan_timelapse_motion_photo_top_ly);
        this.c = (LinearLayout) findViewById(R.id.longan_timelapse_motion_back_ly);
        this.c.setOnClickListener(this);
        this.d = (ImageView) findViewById(R.id.longan_timelapse_motion_add_iv);
        this.d.setOnClickListener(this);
        this.e = (TextView) findViewById(R.id.longan_timelapse_motion_start_tv);
        this.e.setOnClickListener(this);
        this.f = (HorizontalScrollView) findViewById(R.id.longan_timelapse_motion_scroll_horizontal);
        this.g = (ScrollView) findViewById(R.id.longan_timelapse_motion_scroll_vertical);
        this.h = (LayoutParams) getLayoutParams();
        this.i = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
        this.j = (FrameLayout.LayoutParams) this.f.getLayoutParams();
        this.k = (LayoutParams) this.g.getLayoutParams();
        b();
        c.a().a(this);
    }

    private void b() {
        if (getResources().getConfiguration().orientation == 2) {
            c.a().e(f.LANDSCAPE);
            this.h.height = -2;
            this.h.width = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_ly_width_land);
            this.k.width = -1;
            this.k.height = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_frame_height_land);
            this.a.setOrientation(1);
            this.i.addRule(15, 0);
            this.i.addRule(14);
            return;
        }
        c.a().e(f.PORTRAIT);
        this.h.height = -2;
        this.h.width = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_ly_width_port);
        this.k.height = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_frame_height_port);
        this.k.width = -1;
        this.a.setOrientation(0);
        this.i.addRule(14, 0);
        this.i.addRule(15);
    }

    private void c() {
        for (int i = 0; i < this.l.size(); i++) {
            setPhotoSize((LonganTimelapseMotionPhotoView) this.l.get(i));
        }
        e();
    }

    private void d() {
        if (this.l.size() >= 2) {
            a(this.f);
            a(this.g);
        }
    }

    public void hide() {
        setVisibility(8);
    }

    public void show() {
        setVisibility(0);
    }

    public void onEventMainThread(i iVar) {
        if (iVar == i.GOT_SCREENSHORT) {
            a aVar;
            if (this.l.size() == 0) {
                aVar = a.FIRST;
            } else {
                aVar = a.LAST;
            }
            View longanTimelapseMotionPhotoView = new LonganTimelapseMotionPhotoView(getContext(), aVar, this.l.size() + 1);
            longanTimelapseMotionPhotoView.setPhoto(new BitmapDrawable(getResources(), iVar.c));
            for (int i = 0; i < this.l.size(); i++) {
                ((LonganTimelapseMotionPhotoView) this.l.get(i)).hideCancleBtn();
            }
            this.a.addView(longanTimelapseMotionPhotoView);
            this.l.add(longanTimelapseMotionPhotoView);
            c.a().e(k.ADD_ONE_POINT.a(this.l.size()));
            Log.d("point count", "" + this.l.size());
            e();
            setPhotoSize(longanTimelapseMotionPhotoView);
            d();
        }
    }

    private void e() {
        int i;
        LayoutParams layoutParams;
        if (getResources().getConfiguration().orientation == 1) {
            for (i = 0; i < this.l.size(); i++) {
                layoutParams = (LayoutParams) ((LonganTimelapseMotionPhotoView) this.l.get(i)).getLayoutParams();
                if (i == 0) {
                    if (this.l.size() == 1) {
                        layoutParams.setMargins(this.t, this.t, this.t, this.t);
                    } else {
                        layoutParams.setMargins(this.t, this.t, this.t / 2, this.t);
                    }
                } else if (i == this.l.size() - 1) {
                    layoutParams.setMargins(this.t / 2, this.t, this.t, this.t);
                } else {
                    layoutParams.setMargins(this.t / 2, this.t, this.t / 2, this.t);
                }
            }
            return;
        }
        for (i = 0; i < this.l.size(); i++) {
            layoutParams = (LayoutParams) ((LonganTimelapseMotionPhotoView) this.l.get(i)).getLayoutParams();
            if (i == 0) {
                if (this.l.size() == 1) {
                    layoutParams.setMargins(this.t, this.t, this.t, this.t);
                } else {
                    layoutParams.setMargins(this.t, this.t, this.t, this.t / 2);
                }
            } else if (i == this.l.size() - 1) {
                layoutParams.setMargins(this.t, this.t / 2, this.t, this.t);
            } else {
                layoutParams.setMargins(this.t, this.t / 2, this.t, this.t / 2);
            }
        }
    }

    private void a(int i) {
        if (i > 1) {
            View dJIMotionTimelapseDurButton = new DJIMotionTimelapseDurButton(getContext(), this.m.d);
            dJIMotionTimelapseDurButton.setNumber(this.l.size());
            a(dJIMotionTimelapseDurButton, i - 1);
            this.b.addView(dJIMotionTimelapseDurButton);
        }
    }

    private void a(DJIMotionTimelapseDurButton dJIMotionTimelapseDurButton, int i) {
        if (getResources().getConfiguration().orientation == 1) {
            dJIMotionTimelapseDurButton.setX((float) ((((this.b.getLeft() + (this.p * i)) - (this.r / 2)) + (this.t / 2)) + (this.t * i)));
            dJIMotionTimelapseDurButton.setY((float) (((this.b.getTop() + (this.q / 2)) - (this.s / 2)) + this.t));
            return;
        }
        dJIMotionTimelapseDurButton.setX((float) (((this.b.getLeft() + (this.n / 2)) - (this.r / 2)) + this.t));
        dJIMotionTimelapseDurButton.setY((float) ((((this.b.getTop() + (this.o * i)) - (this.s / 2)) + (this.t / 2)) + (this.t * i)));
    }

    private void a(final HorizontalScrollView horizontalScrollView) {
        horizontalScrollView.postDelayed(new Runnable(this) {
            final /* synthetic */ LonganTimelapseMotionLayout b;

            public void run() {
                horizontalScrollView.fullScroll(66);
            }
        }, 500);
    }

    private void a(final ScrollView scrollView) {
        scrollView.postDelayed(new Runnable(this) {
            final /* synthetic */ LonganTimelapseMotionLayout b;

            public void run() {
                scrollView.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            }
        }, 500);
    }

    private void setPhotoSize(LonganTimelapseMotionPhotoView longanTimelapseMotionPhotoView) {
        if (getResources().getConfiguration().orientation == 2) {
            longanTimelapseMotionPhotoView.getLayoutParams().height = this.o;
            longanTimelapseMotionPhotoView.getLayoutParams().width = this.n;
            return;
        }
        longanTimelapseMotionPhotoView.getLayoutParams().height = this.q;
        longanTimelapseMotionPhotoView.getLayoutParams().width = this.p;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_timelapse_motion_back_ly) {
            c.a().e(m.SHOW_SET_PARMS_LY);
        } else if (id == R.id.longan_timelapse_motion_add_iv) {
            if (k.ADD_ONE_POINT.g < 5) {
                c.a().e(i.PLEASE_TAKE_SCREENSHORT.a(2));
                this.m.d();
            }
        } else if (id == R.id.longan_timelapse_motion_start_tv && b.getInstance().c() == b.MOTION) {
            c.a().e(k.START);
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (cVar == dji.device.camera.a.b.c.NOT_TIMING) {
            f();
        }
    }

    public void onEventMainThread(k kVar) {
        if (kVar == k.DELETE_ONE_POINT) {
            this.m.e();
            this.a.removeView((View) this.l.get(this.l.size() - 1));
            this.l.remove(this.l.size() - 1);
            ((LonganTimelapseMotionPhotoView) this.l.get(this.l.size() - 1)).showCancleBtn();
            k kVar2 = k.ADD_ONE_POINT;
            kVar2.g--;
        }
    }

    private void f() {
        this.l.clear();
        k.ADD_ONE_POINT.g = 0;
        k.DURATION_CHANGED.h = 0;
        this.a.removeAllViews();
    }
}
