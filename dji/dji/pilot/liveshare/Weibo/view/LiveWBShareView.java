package dji.pilot.liveshare.Weibo.view;

import android.app.Activity;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.pilot.R;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.Weibo.b.b;
import dji.pilot.liveshare.base.view.LiveBaseShareView;

public class LiveWBShareView extends LiveBaseShareView {
    public LiveWBShareView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        String readUserAvatar = b.readUserAvatar(this.mActivity);
        CharSequence readUserName = b.readUserName(this.mActivity);
        ImageLoader.getInstance().displayImage(readUserAvatar, this.mAvatarIv, this.options);
        this.mProfileNameTv.setText(readUserName);
        this.mStartBtn.setBackground(getResources().getDrawable(R.drawable.liveshare_weibo_start_btn));
        this.mCheckInLy.setVisibility(8);
    }

    protected void startLive() {
        dji.pilot.liveshare.Weibo.a.b instance = dji.pilot.liveshare.Weibo.a.b.getInstance(this.mActivity);
        if (!this.mDescriptionEt.getText().toString().equals("")) {
            instance.setSummary(this.mDescriptionEt.getText().toString());
        }
        instance.setPublished(a.E);
        instance.createLiveUrl();
    }
}
