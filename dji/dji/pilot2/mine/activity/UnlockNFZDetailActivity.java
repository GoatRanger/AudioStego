package dji.pilot2.mine.activity;

import android.os.Bundle;
import android.view.View;
import dji.pilot.R;
import dji.pilot.flyunlimit.jsonbean.UnlockListItem;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.publics.DJIUI.DJITextView;

public class UnlockNFZDetailActivity extends DJIActivityNoFullScreen {
    public static final String a = "key_ser_unlocklistitem";
    private DJIStateTextView b;
    private DJITextView c;
    private DJITextView d;
    private DJITextView t;
    private DJITextView u;
    private DJITextView v;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_unlock_nfz_detail);
        a();
        b();
    }

    private void a() {
        this.b = (DJIStateTextView) findViewById(R.id.cgr);
        this.c = (DJITextView) findViewById(R.id.cgs);
        this.d = (DJITextView) findViewById(R.id.cgt);
        this.t = (DJITextView) findViewById(R.id.cgu);
        this.u = (DJITextView) findViewById(R.id.cgv);
        this.v = (DJITextView) findViewById(R.id.cgw);
    }

    private void b() {
        UnlockListItem unlockListItem = (UnlockListItem) getIntent().getSerializableExtra(a);
        this.b.setText(unlockListItem.places);
        this.c.setText(unlockListItem.places);
        this.d.setText(unlockListItem.location);
        this.t.setText(unlockListItem.begin_at + " - " + unlockListItem.end_at);
        this.u.setText(unlockListItem.status);
        this.v.setText(unlockListItem.areas_type);
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.cgq:
                finish();
                return;
            default:
                return;
        }
    }
}
