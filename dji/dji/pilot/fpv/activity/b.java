package dji.pilot.fpv.activity;

import android.content.Context;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.view.DJIGuidanceAvoidView;
import dji.pilot.publics.e.e;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJITextView;
import java.text.DecimalFormat;

public class b extends c {
    private DJIGuidanceAvoidView a = null;
    private DJITextView b = null;
    private DJITextView c = null;

    public b(Context context) {
        super(context);
        b();
    }

    public b a(int[] iArr) {
        this.a.updateData(iArr);
        return this;
    }

    public b a(int i) {
        float f = (((float) i) * 1.0f) / 10.0f;
        int v = DJIGenSettingDataManager.getInstance().v();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        if (v == 0) {
            f = e.f(f);
            this.c.setText(getContext().getResources().getString(R.string.flight_record_distance_ft_format, new Object[]{decimalFormat.format((double) f)}));
        } else {
            this.c.setText(getContext().getResources().getString(R.string.flight_record_distance_m_format, new Object[]{decimalFormat.format((double) f)}));
        }
        return this;
    }

    public b a(String str) {
        this.b.setText(str);
        return this;
    }

    private void b() {
        setContentView(R.layout.fpv_guidance_avoid_dlg);
        this.a = (DJIGuidanceAvoidView) findViewById(R.id.a2s);
        this.b = (DJITextView) findViewById(R.id.a2u);
        this.c = (DJITextView) findViewById(R.id.a2t);
    }

    protected void onCreate(Bundle bundle) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.lw);
        a(dimensionPixelSize, dimensionPixelSize + getContext().getResources().getDimensionPixelSize(R.dimen.rk), 0, 17, false, false);
        o();
    }
}
