package dji.pilot.main.activity;

import android.content.Context;
import android.os.Bundle;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.thirdparty.afinal.a.b.c;

public class DJIDirectorPreviewActivity extends DJIBaseActivity {
    @c(a = 2131362054)
    private DJIImageView a;
    @c(a = 2131362053)
    private DJIImageView b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_director_preview);
        this.b.setOnClickListener(new 1(this));
        a();
    }

    private void a() {
        DJILogHelper.getInstance().LOGD("", getResources().getConfiguration().locale.getCountry(), false, true);
        if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
            this.a.setImageDrawable(getResources().getDrawable(R.drawable.director_comingsoon_cn));
        } else if (getResources().getConfiguration().locale.getCountry().equals("JP")) {
            this.a.setImageDrawable(getResources().getDrawable(R.drawable.director_comingsoon_jp));
        } else {
            this.a.setImageDrawable(getResources().getDrawable(R.drawable.director_comingsoon));
        }
    }

    protected void onStart() {
        super.onStart();
        e.b((Context) this);
    }

    protected void onStop() {
        super.onStop();
        e.c((Context) this);
    }
}
