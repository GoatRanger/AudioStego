package dji.pilot.liveshare.base.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import dji.pilot.R;
import dji.pilot.publics.widget.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public abstract class LiveBaseProfileView extends DJIRelativeLayout implements OnClickListener {
    protected static final String TAG = "LiveBaseProfileView";
    protected b logoutDialog;
    protected Activity mActivity;
    protected DJIImageView mAvatarIv;
    protected DJITextView mLogoutTv;
    protected DJITextView mNameTv;
    protected DJIImageView mProfileLogoIv;
    protected Button mStartBtn;
    protected DisplayImageOptions options;

    protected abstract void initTypeWidget();

    protected abstract void logout();

    public LiveBaseProfileView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveBaseProfileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.logoutDialog = null;
        init();
    }

    private void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_base_profile);
        if (!isInEditMode()) {
            this.mProfileLogoIv = (DJIImageView) findViewById(R.id.a5p);
            this.mAvatarIv = (DJIImageView) findViewById(R.id.a5q);
            this.mNameTv = (DJITextView) findViewById(R.id.a5r);
            this.mStartBtn = (Button) findViewById(R.id.a5u);
            this.mLogoutTv = (DJITextView) findViewById(R.id.a5t);
            this.mStartBtn.setOnClickListener(this);
            this.mLogoutTv.setOnClickListener(this);
            this.options = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(true).build();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
        initTypeWidget();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
    }

    protected void start() {
        c.a().e(new dji.pilot.f.a.a(4));
    }

    private void handleLogout() {
        if (this.logoutDialog != null) {
            this.logoutDialog.show();
            return;
        }
        this.logoutDialog = new b(this.mActivity);
        this.logoutDialog.d((int) R.string.ok).e(R.string.cancel).a(new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LiveBaseProfileView.this.logout();
                dialogInterface.dismiss();
            }
        }).b(new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).b((int) R.string.liveshare_base_live_logout_title).show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a5t:
                handleLogout();
                return;
            case R.id.a5u:
                start();
                return;
            default:
                return;
        }
    }
}
