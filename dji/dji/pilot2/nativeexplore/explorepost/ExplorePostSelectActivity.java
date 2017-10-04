package dji.pilot2.nativeexplore.explorepost;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;

public class ExplorePostSelectActivity extends DJIActivityNoFullScreen implements OnClickListener {
    public static final String a = "select";
    public static final int b = 11;
    private boolean c = true;
    private FrameLayout d;
    private c t;

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 11:
                DJILogHelper.getInstance().LOGI("bob", "ExplorePostSelectActivity onActivityResult ");
                finish();
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_explorepost_select_activity);
        this.d = (FrameLayout) findViewById(R.id.cmt);
        this.c = getIntent().getBooleanExtra(a, false);
        Fragment dVar = new d();
        this.t = new c(this, dVar);
        dVar.a(this.t);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(a, this.c);
        dVar.setArguments(bundle2);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.cmt, dVar);
        beginTransaction.commit();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    public void onClick(View view) {
    }
}
