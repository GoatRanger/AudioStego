package dji.pilot.liveshare.base.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.facebook.login.widget.LoginButton;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.setting.a.a;

public abstract class LiveBaseLoginView extends DJIRelativeLayout {
    protected static final String TAG = "LiveBaseLoginView";
    protected Activity mActivity;
    protected LoginButton mFBLoginBtn;
    protected DJIRelativeLayout mLoadingLy;
    protected DJIImageView mPlatformLogoIv;
    public Button mWBLoginBtn;

    protected abstract void initLogin();

    protected abstract void initTypeWidget();

    protected abstract void unInit();

    public LiveBaseLoginView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveBaseLoginView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_base_login);
        if (!isInEditMode()) {
            this.mPlatformLogoIv = (DJIImageView) findViewById(R.id.a5f);
            this.mFBLoginBtn = (LoginButton) findViewById(R.id.a5h);
            this.mWBLoginBtn = (Button) findViewById(R.id.a5i);
            this.mLoadingLy = (DJIRelativeLayout) findViewById(R.id.a5j);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
        initTypeWidget();
        initLogin();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        unInit();
    }
}
