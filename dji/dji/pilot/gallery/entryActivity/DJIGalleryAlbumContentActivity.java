package dji.pilot.gallery.entryActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import dji.pilot.R;
import dji.pilot.gallery.entryActivity.a.a;
import dji.pilot.usercenter.protocol.d;

public class DJIGalleryAlbumContentActivity extends Activity {
    public static final String a = "intent_filename";
    private DJIGalleryFragment b;
    private a c;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        String stringExtra;
        super.onCreate(bundle);
        setContentView(R.layout.gallery_main_activity);
        String str = Environment.getExternalStorageDirectory() + "";
        String str2 = "";
        Intent intent = getIntent();
        if (intent != null) {
            stringExtra = intent.getStringExtra("intent_filename");
            if (!(stringExtra == null || stringExtra.equals(""))) {
                String[] split = stringExtra.split(d.t);
                str = stringExtra;
                stringExtra = split[split.length - 1];
                this.b = (DJIGalleryFragment) getFragmentManager().findFragmentById(R.id.gp);
                this.c = new e(this.b, str);
                this.b.a(this.c);
                this.b.a(stringExtra);
                this.b.b(false);
            }
        }
        stringExtra = str2;
        this.b = (DJIGalleryFragment) getFragmentManager().findFragmentById(R.id.gp);
        this.c = new e(this.b, str);
        this.b.a(this.c);
        this.b.a(stringExtra);
        this.b.b(false);
    }
}
