package dji.pilot.liveshare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.Facebook.view.LiveFBFinishView;
import dji.pilot.liveshare.Facebook.view.LiveFBLoginView;
import dji.pilot.liveshare.Facebook.view.LiveFBManageView;
import dji.pilot.liveshare.Facebook.view.LiveFBProfileView;
import dji.pilot.liveshare.Facebook.view.LiveFBPublicView;
import dji.pilot.liveshare.Facebook.view.LiveFBShareView;
import dji.pilot.liveshare.Weibo.view.LiveWBFinishView;
import dji.pilot.liveshare.Weibo.view.LiveWBLoginView;
import dji.pilot.liveshare.Weibo.view.LiveWBManageView;
import dji.pilot.liveshare.Weibo.view.LiveWBProfileView;
import dji.pilot.liveshare.Weibo.view.LiveWBPublicView;
import dji.pilot.liveshare.Weibo.view.LiveWBShareView;
import dji.pilot.liveshare.base.view.LiveBaseCheckInView;
import dji.pilot.liveshare.base.view.LiveBaseFinishView;
import dji.pilot.liveshare.base.view.LiveBaseLoginView;
import dji.pilot.liveshare.base.view.LiveBaseManageView;
import dji.pilot.liveshare.base.view.LiveBaseProfileView;
import dji.pilot.liveshare.base.view.LiveBasePublicView;
import dji.pilot.liveshare.base.view.LiveBaseShareView;
import dji.pilot.liveshare.custom.LiveCustomCreateView;
import dji.pilot.liveshare.custom.LiveCustomManageView;
import dji.pilot.liveshare.custom.LiveCustomStartView;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.b;
import dji.thirdparty.a.c;

public class DJILiveShareActivity extends DJIBaseActivity implements OnClickListener {
    private static final int TYPE_CUSTOM = 3;
    private static final int TYPE_FACEBOOK = 0;
    private static final int TYPE_WEIBO = 2;
    private static final int TYPE_YOUTUBE = 1;
    public int currentType;
    private View currentView;
    private b dialog;
    private LiveBaseCheckInView mCheckInView;
    private ImageButton mCloseBtn;
    private FrameLayout mContentLy;
    private LiveCustomCreateView mCustomCreateView;
    private LiveCustomManageView mCustomManageView;
    private LiveCustomStartView mCustomStartView;
    private LiveBaseFinishView mFinishView;
    private LiveBaseLoginView mLoginView;
    private LiveBaseManageView mManagerView;
    private LiveBaseProfileView mProfileView;
    private LiveBasePublicView mPublicView;
    private LiveBaseShareView mShareView;
    private Animation slideLeftInAnim;
    private Animation slideLeftOutAnim;
    private Animation slideRightInAnim;
    private Animation slideRightOutAnim;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fpv_liveshare_base_view);
        getWindow().setGravity(17);
        c.a().a(this);
        this.currentType = getIntent().getIntExtra("type", 0);
        a.D = this.currentType;
        init();
    }

    private void init() {
        this.mCloseBtn = (ImageButton) findViewById(R.id.a6g);
        this.mContentLy = (FrameLayout) findViewById(R.id.a6h);
        this.mCloseBtn.setOnClickListener(this);
        if (b.getInstance().isLaunch()) {
            switchToManage();
        } else if (this.currentType == 3) {
            switchToCustomCreate();
        } else {
            switchToLogin();
        }
        this.slideRightInAnim = AnimationUtils.loadAnimation(this, R.anim.bp);
        this.slideRightOutAnim = AnimationUtils.loadAnimation(this, R.anim.bq);
        this.slideLeftInAnim = AnimationUtils.loadAnimation(this, R.anim.bi);
        this.slideLeftOutAnim = AnimationUtils.loadAnimation(this, R.anim.bk);
    }

    private void switchToCustomCreate() {
        this.mCustomCreateView = new LiveCustomCreateView(this);
        if (this.currentView != null) {
            this.currentView.startAnimation(this.slideRightOutAnim);
            this.mCustomCreateView.startAnimation(this.slideLeftInAnim);
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mCustomCreateView);
        this.currentView = this.mCustomCreateView;
    }

    private void switchToCustomStart() {
        this.mCustomStartView = new LiveCustomStartView(this);
        if (this.currentView != null) {
            this.currentView.startAnimation(this.slideLeftOutAnim);
            this.mCustomStartView.startAnimation(this.slideRightInAnim);
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mCustomStartView);
        this.currentView = this.mCustomStartView;
    }

    private void switchToLogin() {
        switch (this.currentType) {
            case 0:
                this.mLoginView = new LiveFBLoginView(this);
                break;
            case 2:
                this.mLoginView = new LiveWBLoginView(this);
                break;
        }
        if (this.currentView != null) {
            this.currentView.startAnimation(this.slideRightOutAnim);
            this.mLoginView.startAnimation(this.slideLeftInAnim);
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mLoginView);
        this.currentView = this.mLoginView;
        this.mCloseBtn.setVisibility(0);
    }

    private void switchToProfile() {
        switch (this.currentType) {
            case 0:
                this.mProfileView = new LiveFBProfileView(this);
                break;
            case 2:
                this.mProfileView = new LiveWBProfileView(this);
                break;
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mProfileView);
        this.currentView = this.mProfileView;
        this.mCloseBtn.setVisibility(0);
    }

    private void switchToShare() {
        switch (this.currentType) {
            case 0:
                this.mShareView = new LiveFBShareView(this);
                break;
            case 2:
                this.mShareView = new LiveWBShareView(this);
                break;
        }
        if (this.currentView == this.mProfileView) {
            this.currentView.startAnimation(this.slideLeftOutAnim);
            this.mShareView.startAnimation(this.slideRightInAnim);
        } else if (this.currentView == this.mPublicView || this.currentView == this.mCheckInView) {
            this.currentView.startAnimation(this.slideRightOutAnim);
            this.mShareView.startAnimation(this.slideLeftInAnim);
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mShareView);
        this.currentView = this.mShareView;
        this.mCloseBtn.setVisibility(0);
    }

    private void switchToPublic() {
        switch (this.currentType) {
            case 0:
                this.mPublicView = new LiveFBPublicView(this);
                break;
            case 2:
                this.mPublicView = new LiveWBPublicView(this);
                break;
        }
        if (this.currentView != null) {
            this.currentView.startAnimation(this.slideLeftOutAnim);
            this.mPublicView.startAnimation(this.slideRightInAnim);
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mPublicView);
        this.currentView = this.mPublicView;
        this.mCloseBtn.setVisibility(8);
    }

    private void switchToCheckIn() {
        if (this.mCheckInView == null) {
            this.mCheckInView = new LiveBaseCheckInView(this);
        }
        if (this.currentView != null) {
            this.currentView.startAnimation(this.slideLeftOutAnim);
            this.mCheckInView.startAnimation(this.slideRightInAnim);
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mCheckInView);
        this.currentView = this.mCheckInView;
        this.mCloseBtn.setVisibility(8);
    }

    private void switchToManage() {
        switch (this.currentType) {
            case 0:
                this.mManagerView = new LiveFBManageView(this);
                break;
            case 2:
                this.mManagerView = new LiveWBManageView(this);
                break;
            case 3:
                this.mCustomManageView = new LiveCustomManageView(this);
                this.mContentLy.removeAllViews();
                this.mContentLy.addView(this.mCustomManageView);
                this.currentView = this.mCustomManageView;
                return;
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mManagerView);
        this.currentView = this.mManagerView;
        this.mCloseBtn.setVisibility(0);
    }

    private void switchToFinish() {
        switch (this.currentType) {
            case 0:
            case 3:
                this.mFinishView = new LiveFBFinishView(this);
                break;
            case 2:
                this.mFinishView = new LiveWBFinishView(this);
                break;
        }
        if (this.currentView != null) {
            this.currentView.startAnimation(this.slideLeftOutAnim);
            this.mFinishView.startAnimation(this.slideRightInAnim);
        }
        this.mContentLy.removeAllViews();
        this.mContentLy.addView(this.mFinishView);
        this.currentView = this.mFinishView;
        this.mCloseBtn.setVisibility(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a6g:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().d(this);
        a.a();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (this.currentType) {
            case 0:
                if (((LiveFBLoginView) this.mLoginView).callbackManager != null) {
                    ((LiveFBLoginView) this.mLoginView).callbackManager.a(i, i2, intent);
                    return;
                }
                return;
            case 2:
                if (((LiveWBLoginView) this.mLoginView).mSsoHandler != null) {
                    ((LiveWBLoginView) this.mLoginView).mSsoHandler.authorizeCallBack(i, i2, intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(a aVar) {
        switch (aVar.I) {
            case 1:
                switchToProfile();
                return;
            case 2:
            case 9:
                if (aVar.L == 0) {
                    Toast.makeText(this, "error: unknow", 0).show();
                    return;
                } else if (aVar.L == 20014) {
                    if (this.dialog == null) {
                        this.dialog = new b(this);
                    }
                    this.dialog.a((int) R.string.liveshare_custom_error_title).b((int) R.string.liveshare_weibo_error_description).d((int) R.string.ok).a(new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    this.dialog.show();
                    return;
                } else {
                    Toast.makeText(this, "error: " + aVar.L, 0).show();
                    return;
                }
            case 3:
                switchToLogin();
                return;
            case 4:
                switchToShare();
                return;
            case 5:
            case 16:
                finish();
                return;
            case 7:
                switchToPublic();
                return;
            case 8:
                switchToFinish();
                return;
            case 17:
                switchToCustomStart();
                return;
            case 18:
                switchToCustomCreate();
                return;
            case 19:
                switchToCheckIn();
                return;
            default:
                return;
        }
    }
}
