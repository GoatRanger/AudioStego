package dji.pilot.liveshare.Facebook.view;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import com.facebook.Profile;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.log.DJILogHelper;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.Facebook.a.c;
import dji.pilot.liveshare.Facebook.a.d;
import dji.pilot.liveshare.b;
import dji.pilot.liveshare.base.view.LiveBaseShareView;
import java.util.List;

public class LiveFBShareView extends LiveBaseShareView implements c {
    private d steamManager;

    public LiveFBShareView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        String uri = Profile.a().a(120, 120).toString();
        CharSequence g = Profile.a().g();
        ImageLoader.getInstance().displayImage(uri, this.mAvatarIv, this.options);
        this.mProfileNameTv.setText(g);
        this.mCheckInLy.setVisibility(0);
    }

    protected void startLive() {
        if (this.steamManager == null) {
            this.steamManager = new d(this.mActivity);
        }
        if (!this.mDescriptionEt.getText().toString().equals("")) {
            this.steamManager.setDescription(this.mDescriptionEt.getText().toString());
        }
        this.steamManager.createLiveVideoUrl(this);
    }

    private void handleLiveThread() {
        new Thread(new Runnable() {
            public void run() {
                LiveFBShareView.this.steamManager.startStreaming();
                dji.thirdparty.a.c.a().e(new a(6));
            }
        }).start();
    }

    public void onCreateLiveVideoUrl(String str, List<String> list) {
        Log.d("FBLive", "startStream");
        DJILogHelper.getInstance().LOGE("FBLive", "Start Streaming Facebook", false, true);
        handleLiveThread();
        b.getInstance().setLaunch(true);
        dji.thirdparty.a.c.a().e(new a(5));
    }

    public void onReadLiveVideo() {
    }

    public void onUpdateLiveVideo() {
    }

    public void onFailure(String str) {
        this.mLoadingPb.setVisibility(8);
        this.mStartBtn.setEnabled(true);
        Toast.makeText(this.mActivity, str, 0).show();
    }
}
