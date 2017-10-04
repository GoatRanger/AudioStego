package dji.pilot2.media.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.ortiz.touch.TouchImageView;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.publics.DJIUI.DJIOriLayout;

public class DraftPhotoPreviewActivity extends DJIActivityNoFullScreen {
    public static final String a = "file_name";
    private ImageView b;
    private String c;
    private Bitmap d;
    private Options t;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        setContentView(R.layout.v2_activity_draft_photo_preview);
        DJIOriLayout.setOrientationByDevice(this);
        a();
        b();
        super.onCreate(bundle);
    }

    private void a() {
        this.c = getIntent().getStringExtra(a);
        this.t = new Options();
        this.t.inPreferredConfig = Config.RGB_565;
        try {
            this.d = BitmapFactory.decodeFile(this.c, this.t);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (this.d != null && !this.d.isRecycled()) {
                this.d.recycle();
            }
        }
    }

    private void b() {
        this.b = (TouchImageView) findViewById(R.id.c8g);
        this.b.setImageBitmap(this.d);
    }

    protected void onDestroy() {
        if (!(this.d == null || this.d.isRecycled())) {
            this.d.recycle();
        }
        super.onDestroy();
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c8f:
                finish();
                return;
            default:
                return;
        }
    }
}
