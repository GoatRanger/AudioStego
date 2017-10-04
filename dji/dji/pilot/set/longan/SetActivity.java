package dji.pilot.set.longan;

import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.set.R;
import dji.pilot.set.view.LiveSetectPlatformView;
import dji.pilot.set.view.base.SetGroupView;
import dji.publics.DJIObject.DJIBaseActivityForVirtualKey;
import dji.thirdparty.a.c;
import java.util.Stack;

public class SetActivity extends DJIBaseActivityForVirtualKey implements OnClickListener {
    private FrameLayout mContentView;
    private Context mContext;
    private Stack<b> mHeadStack;
    private ObjectAnimator mPopInAnimator;
    private ObjectAnimator mPopInHeadBarAnimator;
    private LayoutTransition mPopOutSetHeadBarTransition;
    private LayoutTransition mPopOutTransition;
    private ObjectAnimator mPushInAnimator;
    private ObjectAnimator mPushInHeadBarAnimator;
    private ObjectAnimator mPushOutAnimator;
    private ObjectAnimator mPushOutHeadBarAnimator;
    private SetViewHeadBar mSetViewHeadBar;
    private FrameLayout mSetViewHeadBarContentView;
    private dji.pilot.set.longan.SetViewHeadBar.a mSetViewHeadBarOnclickInterface;
    private Stack<b> mStack;

    public enum a {
        CLOSE
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.set_activity);
        c.a().a(this);
        this.mContext = getApplicationContext();
        Log.v("On Create", "s");
        initView();
        initParams();
    }

    public void onWindowFocusChanged(boolean z) {
    }

    protected void onDestroy() {
        c.a().d(this);
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    private void initView() {
        this.mContentView = (FrameLayout) findViewById(R.id.set_content);
        this.mSetViewHeadBarContentView = (FrameLayout) findViewById(R.id.set_view_head_bar_content);
        if (i.getInstance().c() == ProductType.LonganMobile) {
            ((SetGroupView) this.mContentView.findViewById(R.id.phone_camera_settings)).setVisibility(0);
            ((SetGroupView) this.mContentView.findViewById(R.id.camera_settings)).setVisibility(8);
            ((SetGroupView) this.mContentView.findViewById(R.id.lp_general_settings)).setVisibility(0);
            ((SetGroupView) this.mContentView.findViewById(R.id.general_settings)).setVisibility(8);
            ((SetGroupView) this.mContentView.findViewById(R.id.lp_gimbal_settings)).setVisibility(0);
            ((SetGroupView) this.mContentView.findViewById(R.id.gimbal_settings)).setVisibility(8);
            ((LiveSetectPlatformView) this.mContentView.findViewById(R.id.lp_live_enter)).setVisibility(0);
        }
        this.mSetViewHeadBarOnclickInterface = new dji.pilot.set.longan.SetViewHeadBar.a(this) {
            final /* synthetic */ SetActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.finish();
            }

            public void b() {
                this.a.popView();
            }
        };
    }

    private void initParams() {
        setupInAnimations();
        this.mStack = new Stack();
        this.mStack.push(new b(this.mContentView.getChildAt(0), R.string.app_setting));
        View childAt = this.mSetViewHeadBarContentView.getChildAt(0);
        ((SetViewHeadBar) childAt).setOnclickListenerInterface(this.mSetViewHeadBarOnclickInterface);
        this.mHeadStack = new Stack();
        this.mHeadStack.push(new b(childAt, R.string.app_setting));
    }

    public void onEventMainThread(b bVar) {
        pushView(bVar);
    }

    public void onEventMainThread(dji.pilot.set.longan.b.a aVar) {
        if (((b) this.mStack.peek()).b() == aVar.a()) {
            popView();
        }
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.CLOSE) {
            finish();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.set_back) {
            popView();
        } else if (id == R.id.set_done) {
            finish();
        }
    }

    private void pushView(b bVar) {
        if (this.mStack.size() > 0) {
            this.mContentView.setLayoutTransition(null);
            this.mSetViewHeadBarContentView.setLayoutTransition(null);
            int a = bVar.a();
            View b = bVar.b();
            int a2 = ((b) this.mStack.peek()).a();
            View b2 = ((b) this.mStack.peek()).b();
            this.mStack.push(bVar);
            this.mContentView.addView(b);
            b bVar2 = new b(R.layout.set_activity_head_bar, a, this.mContext);
            View b3 = bVar2.b();
            ((SetViewHeadBar) b3).setOnclickListenerInterface(this.mSetViewHeadBarOnclickInterface);
            ((SetViewHeadBar) b3).setTitle(a);
            ((SetViewHeadBar) b3).setDoneVisibility(true);
            View b4 = ((b) this.mHeadStack.peek()).b();
            this.mHeadStack.push(bVar2);
            this.mSetViewHeadBarContentView.addView(b3);
            this.mPushOutAnimator.setTarget(b2);
            this.mPushOutAnimator.start();
            this.mPushInAnimator.setTarget(b);
            this.mPushInAnimator.setFloatValues(new float[]{(float) this.mContentView.getWidth(), 0.0f});
            this.mPushInAnimator.start();
            this.mPushOutHeadBarAnimator.setTarget(b4);
            this.mPushOutHeadBarAnimator.start();
            this.mPushInHeadBarAnimator.setTarget(b3);
            this.mPushInHeadBarAnimator.setFloatValues(new float[]{(float) this.mSetViewHeadBarContentView.getWidth(), 0.0f});
            this.mPushInHeadBarAnimator.start();
            if (a2 != 0) {
                ((SetViewHeadBar) b3).setBackVisibility(true);
                ((SetViewHeadBar) b3).setBackText(a2);
                return;
            }
            ((SetViewHeadBar) b3).setBackVisibility(false);
        }
    }

    private void popView() {
        if (this.mStack.size() <= 1) {
            finish();
            return;
        }
        int a;
        b bVar = (b) this.mStack.pop();
        b bVar2 = (b) this.mHeadStack.pop();
        View b = ((b) this.mStack.peek()).b();
        View b2 = bVar.b();
        View b3 = ((b) this.mHeadStack.peek()).b();
        View b4 = bVar2.b();
        ((b) this.mStack.peek()).a();
        if (this.mStack.size() > 1) {
            a = ((b) this.mStack.get(this.mStack.size() - 2)).a();
        } else {
            a = 0;
        }
        this.mContentView.setLayoutTransition(this.mPopOutTransition);
        this.mContentView.removeView(b2);
        this.mPopInAnimator.setTarget(b);
        this.mPopInAnimator.start();
        this.mSetViewHeadBarContentView.setLayoutTransition(this.mPopOutSetHeadBarTransition);
        this.mSetViewHeadBarContentView.removeView(b4);
        this.mPopInHeadBarAnimator.setTarget(b3);
        this.mPopInHeadBarAnimator.start();
        if (this.mStack.size() == 1) {
            ((SetViewHeadBar) b3).setDoneVisibility(false);
        } else {
            ((SetViewHeadBar) b3).setDoneVisibility(true);
        }
        if (a != 0) {
            ((SetViewHeadBar) b3).setBackText(a);
        }
    }

    private void setupInAnimations() {
        this.mPushInAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_in_right);
        this.mPushOutAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.fade_out);
        this.mPopInAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.fade_in);
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_out_right);
        this.mPushOutAnimator.setStartDelay(100);
        this.mPopOutTransition = new LayoutTransition();
        this.mPopOutTransition.setAnimator(3, objectAnimator);
        this.mPopOutTransition.setDuration(objectAnimator.getDuration());
        this.mPushInHeadBarAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_in_right);
        this.mPushOutHeadBarAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.fade_out);
        this.mPopInHeadBarAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.fade_in);
        objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_out_right);
        this.mPushOutHeadBarAnimator.setStartDelay(100);
        this.mPopOutSetHeadBarTransition = new LayoutTransition();
        this.mPopOutSetHeadBarTransition.setAnimator(3, objectAnimator);
        this.mPopOutSetHeadBarTransition.setDuration(objectAnimator.getDuration());
    }

    public void onBackPressed() {
        if (this.mStack.size() > 1) {
            popView();
        } else {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.v("onConf", "s");
    }

    private void blur(Bitmap bitmap, ImageView imageView) {
        System.currentTimeMillis();
        Bitmap createBitmap = Bitmap.createBitmap((int) (((float) imageView.getMeasuredWidth()) / 8.0f), (int) (((float) imageView.getMeasuredHeight()) / 8.0f), Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate(((float) (-imageView.getLeft())) / 8.0f, ((float) (-imageView.getTop())) / 8.0f);
        canvas.scale(1.0f / 8.0f, 1.0f / 8.0f);
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        imageView.setImageDrawable(new BitmapDrawable(getResources(), dji.pilot.set.view.base.a.a(createBitmap, (int) 2.0f, true)));
    }
}
