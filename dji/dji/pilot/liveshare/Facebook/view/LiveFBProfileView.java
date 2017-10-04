package dji.pilot.liveshare.Facebook.view;

import android.app.Activity;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.midware.data.manager.P3.ServiceManager;
import dji.pilot.R;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.Facebook.a.d;
import dji.pilot.liveshare.base.view.LiveBaseProfileView;
import dji.thirdparty.a.c;

public class LiveFBProfileView extends LiveBaseProfileView {
    public LiveFBProfileView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        ImageLoader.getInstance().displayImage(Profile.a().a(120, 120).toString(), this.mAvatarIv, this.options);
        this.mNameTv.setText(Profile.a().g());
        this.mStartBtn.setBackground(getResources().getDrawable(R.drawable.liveshare_facebook_start_btn));
    }

    protected void logout() {
        AccessToken.a(null);
        Profile.a(null);
        c.a().e(new a(3));
    }

    protected void start() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            new d(this.mActivity).checkPermission();
        } else {
            dji.setting.ui.widget.a.a(getContext(), (int) R.string.lp_live_facebook_need_connect_device).show();
        }
    }
}
