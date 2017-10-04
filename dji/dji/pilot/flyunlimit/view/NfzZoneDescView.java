package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import dji.midware.data.forbid.FlyForbidProtocol.LevelType;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class NfzZoneDescView extends DJIRelativeLayout implements a {
    private DJIStateTextView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private LevelType e = LevelType.CAN_UNLIMIT;

    public NfzZoneDescView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIStateTextView) findViewById(R.id.tb);
            this.b = (ImageView) findViewById(R.id.t9);
            this.c = (TextView) findViewById(R.id.t_);
            this.d = (TextView) findViewById(R.id.ta);
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NfzZoneDescView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    View view2 = (View) this.a.getParent();
                    if (view2 instanceof DJIStageView) {
                        ((DJIStageView) view2).destoryStageView(true);
                    }
                }
            });
        }
    }

    public void setType(LevelType levelType) {
        this.e = levelType;
        a();
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        a();
    }

    private void a() {
        if (this.e == LevelType.CAN_UNLIMIT) {
            this.b.setImageDrawable(getResources().getDrawable(R.drawable.yellow_zone_img));
            this.d.setText(R.string.flight_forbid_yellow_zone_desc_txt);
            this.c.setText(R.string.flight_forbid_yellow_zone_title);
        } else if (this.e == LevelType.CAN_NOT_UNLIMIT) {
            this.b.setImageDrawable(getResources().getDrawable(R.drawable.geo_red_zone_img));
            this.d.setText(R.string.flight_forbid_geo_red_zone_desc_txt);
            this.c.setText(R.string.geo_red_zone_title);
        }
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
