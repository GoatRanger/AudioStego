package cn.sharesdk.onekeyshare.themes.classic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeySharePage;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.onekeyshare.themes.classic.land.FriendListPageLand;
import cn.sharesdk.onekeyshare.themes.classic.port.FriendListPagePort;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.R;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EditPage extends OnekeySharePage implements TextWatcher, OnClickListener, Runnable {
    protected AsyncImageView aivThumb;
    protected EditText etContent;
    private OnekeyShareThemeImpl impl;
    protected LinearLayout llBottom;
    protected LinearLayout llPage;
    protected int maxBodyHeight;
    protected Platform platform;
    protected RelativeLayout rlThumb;
    protected RelativeLayout rlTitle;
    protected ShareParams sp;
    protected ScrollView svContent;
    protected Bitmap thumb;
    protected TextView tvAt;
    protected TextView tvCancel;
    protected TextView tvShare;
    protected TextView tvTextCouter;
    protected XView xvRemove;

    public EditPage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
        this.impl = onekeyShareThemeImpl;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public void setShareParams(ShareParams shareParams) {
        this.sp = shareParams;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (isDialogMode()) {
            System.err.println("Theme classic does not support dialog mode!");
        }
        activity.getWindow().setSoftInputMode(37);
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-789517));
    }

    private void cancelAndFinish() {
        ShareSDK.logDemoEvent(5, this.platform);
        finish();
    }

    private void shareAndFinish() {
        int stringRes = R.getStringRes(this.activity, "ssdk_oks_sharing");
        if (stringRes > 0) {
            Toast.makeText(this.activity, stringRes, 0).show();
        }
        if (isDisableSSO()) {
            this.platform.SSOSetting(true);
        }
        this.platform.setPlatformActionListener(getCallback());
        this.platform.share(this.sp);
        finish();
    }

    private void showThumb(Bitmap bitmap) {
        PicViewerPage picViewerPage = new PicViewerPage(this.impl);
        picViewerPage.setImageBitmap(bitmap);
        picViewerPage.show(this.activity, null);
    }

    private void removeThumb() {
        this.sp.setImageArray(null);
        this.sp.setImageData(null);
        this.sp.setImagePath(null);
        this.sp.setImageUrl(null);
    }

    private void showFriendList() {
        FriendListPage friendListPagePort;
        if (this.activity.getResources().getConfiguration().orientation == 1) {
            friendListPagePort = new FriendListPagePort(this.impl);
        } else {
            friendListPagePort = new FriendListPageLand(this.impl);
        }
        friendListPagePort.setPlatform(this.platform);
        friendListPagePort.showForResult(this.platform.getContext(), null, this);
    }

    public void onResult(HashMap<String, Object> hashMap) {
        CharSequence joinSelectedUser = getJoinSelectedUser(hashMap);
        if (!TextUtils.isEmpty(joinSelectedUser)) {
            this.etContent.append(joinSelectedUser);
        }
    }

    private String getJoinSelectedUser(HashMap<String, Object> hashMap) {
        if (hashMap == null || !hashMap.containsKey(DJIPhotoPreveiwActivity.D)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) hashMap.get(DJIPhotoPreveiwActivity.D);
        if ("FacebookMessenger".equals(((Platform) hashMap.get("platform")).getName())) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuilder.append('@').append((String) it.next()).append(' ');
        }
        return stringBuilder.toString();
    }

    protected boolean isShowAtUserLayout(String str) {
        return "SinaWeibo".equals(str) || "TencentWeibo".equals(str) || "Facebook".equals(str) || "Twitter".equals(str);
    }

    public void onClick(View view) {
        if (view.equals(this.tvCancel)) {
            cancelAndFinish();
        } else if (view.equals(this.tvShare)) {
            this.sp.setText(this.etContent.getText().toString().trim());
            shareAndFinish();
        } else if (view.equals(this.aivThumb)) {
            showThumb(this.thumb);
        } else if (view.equals(this.xvRemove)) {
            this.maxBodyHeight = 0;
            this.rlThumb.setVisibility(8);
            this.llPage.measure(0, 0);
            onTextChanged(this.etContent.getText(), 0, 0, 0);
            removeThumb();
        } else if (view.equals(this.tvAt)) {
            showFriendList();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.tvTextCouter.setText(String.valueOf(charSequence.length()));
        if (this.maxBodyHeight == 0) {
            this.maxBodyHeight = (this.llPage.getHeight() - this.rlTitle.getHeight()) - this.llBottom.getHeight();
        }
        if (this.maxBodyHeight > 0) {
            this.svContent.post(this);
        }
    }

    public void run() {
        int height = this.svContent.getChildAt(0).getHeight();
        LayoutParams layoutParams = (LayoutParams) R.forceCast(this.svContent.getLayoutParams());
        if (height > this.maxBodyHeight && layoutParams.height != this.maxBodyHeight) {
            layoutParams.height = this.maxBodyHeight;
            this.svContent.setLayoutParams(layoutParams);
        } else if (height < this.maxBodyHeight && layoutParams.height == this.maxBodyHeight) {
            layoutParams.height = -2;
            this.svContent.setLayoutParams(layoutParams);
        }
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onPause() {
        DeviceHelper.getInstance(this.activity).hideSoftInput(getContentView());
        super.onPause();
    }
}
