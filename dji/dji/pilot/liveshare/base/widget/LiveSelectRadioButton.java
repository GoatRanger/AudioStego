package dji.pilot.liveshare.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import dji.pilot.R;
import dji.pilot.R$styleable;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public class LiveSelectRadioButton extends DJILinearLayout {
    private static final String TAG = LiveSelectRadioButton.class.getName();
    private static final String TYPE_FRIEND = "friend";
    private static final String TYPE_PRIVATE = "private";
    private static final String TYPE_PUBLIC = "public";
    private DJIImageView mRadioSelector;
    private DJITextView mShareDesTv;
    private DJITextView mShareTitleTv;
    private DJIImageView mShareTypeIv;
    private String type = "private";

    public LiveSelectRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LiveShare);
        this.type = obtainStyledAttributes.getString(0);
        Log.d(TAG, "TYPE:" + this.type);
        obtainStyledAttributes.recycle();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        if (!isInEditMode()) {
            init();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_base_select_item);
        this.mRadioSelector = (DJIImageView) findViewById(R.id.a60);
        this.mShareTypeIv = (DJIImageView) findViewById(R.id.a61);
        this.mShareTitleTv = (DJITextView) findViewById(R.id.a62);
        this.mShareDesTv = (DJITextView) findViewById(R.id.a63);
        setType();
    }

    private void setType() {
        if (this.type.equals("public")) {
            this.mShareTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_all));
            this.mShareTitleTv.setText(R.string.liveshare_base_share_type_public_title);
        } else if (this.type.equals("friend")) {
            this.mShareTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_friend));
            this.mShareTitleTv.setText(R.string.liveshare_base_share_type_friend_title);
        } else if (this.type.equals("private")) {
            this.mShareTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_private));
            this.mShareTitleTv.setText(R.string.liveshare_base_share_type_private_title);
        }
        if (dji.pilot.f.a.a.E.equals(this.type)) {
            setSelector(true);
        } else {
            setSelector(false);
        }
        setPlatform();
    }

    public void setPlatform() {
        String string = getResources().getString(R.string.setting_ui_general_platform_facebook);
        switch (dji.pilot.f.a.a.D) {
            case 0:
                string = getResources().getString(R.string.setting_ui_general_platform_facebook);
                break;
            case 1:
                string = getResources().getString(R.string.setting_ui_general_platform_youtube);
                break;
            case 2:
                string = getResources().getString(R.string.setting_ui_general_platform_weibo);
                if (this.type.equals("friend")) {
                    go();
                    break;
                }
                break;
        }
        if (this.type.equals("public")) {
            this.mShareDesTv.setText(getResources().getString(R.string.liveshare_base_share_type_public_desc, new Object[]{string}));
        } else if (this.type.equals("friend")) {
            this.mShareDesTv.setText(getResources().getString(R.string.liveshare_base_share_type_friend_desc, new Object[]{string}));
        } else if (this.type.equals("private")) {
            this.mShareDesTv.setText(getResources().getString(R.string.liveshare_base_share_type_private_desc));
        }
    }

    public void setSelector(boolean z) {
        if (z) {
            switch (dji.pilot.f.a.a.D) {
                case 0:
                case 1:
                    this.mRadioSelector.setImageResource(R.drawable.liveshare_ic_selector_select);
                    return;
                case 2:
                    this.mRadioSelector.setImageResource(R.drawable.liveshare_ic_selector_select_weibo);
                    return;
                default:
                    return;
            }
        }
        this.mRadioSelector.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_selector_normal));
    }

    public void onEventMainThread(dji.pilot.f.a.a aVar) {
        if (aVar.I != 257) {
            return;
        }
        if (aVar.J.equals(this.type)) {
            setSelector(true);
        } else {
            setSelector(false);
        }
    }
}
