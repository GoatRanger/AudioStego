package dji.pilot.gallery.entryActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import dji.pilot.R;
import dji.pilot.gallery.entryActivity.a.a;
import dji.thirdparty.afinal.FinalActivity;

public class DJIGalleryMainActivity extends FinalActivity {
    public static final String a = "intent_filename";
    private DJIGalleryFragment b;
    private a c;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gallery_main_activity);
        String str = Environment.getExternalStorageDirectory() + "";
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("intent_filename");
            if (!(stringExtra == null || stringExtra.equals(""))) {
                str = stringExtra;
            }
        }
        this.b = (DJIGalleryFragment) getFragmentManager().findFragmentById(R.id.gp);
        this.c = new e(this.b, str);
        this.b.a(this.c);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
