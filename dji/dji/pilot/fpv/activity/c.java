package dji.pilot.fpv.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.newfpv.topbar.DJIFpvTopBarBaseView;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class c extends dji.pilot.publics.objects.c implements OnClickListener {
    private DJITextView a = null;
    private DJIImageView b = null;
    private DJITextView c = null;

    public c(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.guidance_use_tip_dlg);
        this.a = (DJITextView) findViewById(R.id.sj);
        this.b = (DJIImageView) findViewById(R.id.sl);
        this.c = (DJITextView) findViewById(R.id.ap1);
        this.a.setText(R.string.guidance_know_use);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(DJIBaseActivity.screenWidth - b.a(this.N, R.dimen.lw), DJIBaseActivity.screenHeight - b.a(this.N, R.dimen.kz), 0, 17, false, false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.sl) {
            dismiss();
            dji.thirdparty.a.c.a().e(dji.pilot.fpv.topbar.DJIFpvTopBaseView.c.MC_SHOWBY_GUIDANCE);
            dji.thirdparty.a.c.a().e(DJIFpvTopBarBaseView.b.MC_SHOWBY_GUIDANCE);
        } else if (id == R.id.ap1) {
            dismiss();
            dji.thirdparty.a.c.a().e(dji.pilot.fpv.topbar.DJIFpvTopBaseView.c.MC_SHOWBY_GUIDANCE);
            dji.thirdparty.a.c.a().e(DJIFpvTopBarBaseView.b.MC_SHOWBY_GUIDANCE);
        }
    }
}
