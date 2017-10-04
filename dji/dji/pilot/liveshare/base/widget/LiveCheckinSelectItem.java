package dji.pilot.liveshare.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import dji.pilot.R;
import dji.pilot.liveshare.base.view.LiveBaseCheckInView.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LiveCheckinSelectItem extends RelativeLayout {
    private a place;
    private DJIImageView selectorIv;
    private DJITextView titleTv;

    public LiveCheckinSelectItem(Context context) {
        super(context);
        init(context);
    }

    public LiveCheckinSelectItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public LiveCheckinSelectItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fpv_liveshare_checkin_select_item, this);
        this.titleTv = (DJITextView) findViewById(R.id.a6i);
        this.selectorIv = (DJIImageView) findViewById(R.id.a6j);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(dji.pilot.f.a.a aVar) {
        if (aVar.I != 259) {
            return;
        }
        if (this.place.id == null) {
            setItemSelected(dji.pilot.f.a.a.G == null);
        } else {
            setItemSelected(this.place.id.equals(dji.pilot.f.a.a.G));
        }
    }

    public void setItemSelected(boolean z) {
        if (z) {
            switch (dji.pilot.f.a.a.D) {
                case 0:
                case 1:
                    this.titleTv.setTextColor(getResources().getColor(R.color.f_));
                    this.selectorIv.setImageResource(R.drawable.liveshare_ic_selector_select);
                    return;
                case 2:
                    this.titleTv.setTextColor(getResources().getColor(R.color.om));
                    this.selectorIv.setImageResource(R.drawable.liveshare_ic_selector_select_weibo);
                    return;
                default:
                    this.titleTv.setTextColor(getResources().getColor(R.color.f_));
                    this.selectorIv.setImageResource(R.drawable.liveshare_ic_selector_select);
                    return;
            }
        }
        this.titleTv.setTextColor(getResources().getColor(R.color.om));
        this.selectorIv.setImageDrawable(getResources().getDrawable(R.drawable.liveshare_ic_selector_normal));
    }

    public void setPlace(a aVar) {
        this.place = aVar;
        this.titleTv.setText(aVar.name == null ? getResources().getString(R.string.liveshare_checkin_hide_place) : aVar.name);
    }

    public a getPlace() {
        return this.place;
    }
}
