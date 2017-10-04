package dji.pilot.liveshare.base.view;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIEditText;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public abstract class LiveBaseShareView extends DJIRelativeLayout implements OnClickListener {
    protected static final String TAG = LiveBaseLoginView.class.getName();
    protected Activity mActivity;
    protected DJIImageView mAvatarIv;
    protected DJIImageView mCheckInIv;
    protected DJILinearLayout mCheckInLy;
    protected DJITextView mCheckInPlaceTv;
    protected DJIEditText mDescriptionEt;
    protected ProgressBar mLoadingPb;
    protected DJITextView mProfileNameTv;
    protected DJIImageView mShareTypeIv;
    protected DJILinearLayout mShareTypeLy;
    protected DJITextView mShareTypeTv;
    protected Button mStartBtn;
    protected DisplayImageOptions options;
    protected String shareType;

    protected abstract void initTypeWidget();

    protected abstract void startLive();

    public LiveBaseShareView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveBaseShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    protected void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_base_share);
        if (!isInEditMode()) {
            this.mAvatarIv = (DJIImageView) findViewById(R.id.a65);
            this.mProfileNameTv = (DJITextView) findViewById(R.id.a66);
            this.mDescriptionEt = (DJIEditText) findViewById(R.id.a6f);
            this.mStartBtn = (Button) findViewById(R.id.a68);
            this.mStartBtn.setOnClickListener(this);
            this.mShareTypeLy = (DJILinearLayout) findViewById(R.id.a6_);
            this.mShareTypeLy.setOnClickListener(this);
            this.mShareTypeIv = (DJIImageView) findViewById(R.id.a6a);
            this.mShareTypeTv = (DJITextView) findViewById(R.id.a6b);
            this.mLoadingPb = (ProgressBar) findViewById(R.id.a69);
            this.mCheckInLy = (DJILinearLayout) findViewById(R.id.a6c);
            this.mCheckInIv = (DJIImageView) findViewById(R.id.a6e);
            this.mCheckInIv.setOnClickListener(this);
            this.mCheckInPlaceTv = (DJITextView) findViewById(R.id.a6d);
            initShareType();
            this.options = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(true).build();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a68:
                startLive();
                this.mStartBtn.setEnabled(false);
                this.mLoadingPb.setVisibility(0);
                return;
            case R.id.a6_:
                enterShareSelect();
                return;
            case R.id.a6e:
                enterCheckInSelect();
                return;
            default:
                return;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        initTypeWidget();
        updateCheckInPlaceDisplay();
        Log.d(TAG, "onAttachedToWindow");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (c.a().c(this)) {
            c.a().d(this);
        }
        Log.d(TAG, "onDetachedFromWindow");
    }

    protected void initShareType() {
        this.shareType = dji.pilot.f.a.a.E;
        Log.d(TAG, "currentShareType2:" + this.shareType);
        if (this.shareType.equals("public")) {
            this.mShareTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_all));
            this.mShareTypeTv.setText(R.string.liveshare_base_share_type_public_title);
        } else if (this.shareType.equals(dji.pilot.f.a.a.A)) {
            this.mShareTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_friend));
            this.mShareTypeTv.setText(R.string.liveshare_base_share_type_friend_title);
        } else if (this.shareType.equals(dji.pilot.f.a.a.B)) {
            this.mShareTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_private));
            this.mShareTypeTv.setText(R.string.liveshare_base_share_type_private_title);
        }
        this.mDescriptionEt.setText(dji.pilot.f.a.a.C);
    }

    protected void enterCheckInSelect() {
        c.a().e(new dji.pilot.f.a.a(19));
    }

    protected void enterShareSelect() {
        dji.pilot.f.a.a.C = this.mDescriptionEt.getText().toString();
        c.a().e(new dji.pilot.f.a.a(7));
    }

    private void updateCheckInPlaceDisplay() {
        if (dji.pilot.f.a.a.H == null || dji.pilot.f.a.a.G == null) {
            this.mCheckInPlaceTv.setText("");
            this.mCheckInIv.setImageResource(R.drawable.ic_fblive_location_nor);
            return;
        }
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.om));
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.f4));
        String string = getResources().getString(R.string.liveshare_checkin_at);
        Object obj = string + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dji.pilot.f.a.a.H;
        CharSequence spannableStringBuilder = new SpannableStringBuilder(obj);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, string.length(), 33);
        spannableStringBuilder.setSpan(foregroundColorSpan2, string.length(), obj.length(), 33);
        this.mCheckInPlaceTv.setText(spannableStringBuilder);
        this.mCheckInIv.setImageResource(R.drawable.ic_fblive_location_sel);
    }

    public void onEventMainThread(dji.pilot.f.a.a aVar) {
        switch (aVar.I) {
            case 9:
                this.mLoadingPb.setVisibility(8);
                this.mStartBtn.setEnabled(true);
                return;
            case 259:
                updateCheckInPlaceDisplay();
                return;
            default:
                return;
        }
    }
}
