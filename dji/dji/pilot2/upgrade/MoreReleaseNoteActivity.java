package dji.pilot2.upgrade;

import android.app.Activity;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIScrollTextView;

public class MoreReleaseNoteActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_upgrade_more_release_note_activity);
        a();
    }

    private void a() {
        ((DJIScrollTextView) findViewById(R.id.d0z)).setText(P4UpgradeActivity.a.content);
    }
}
