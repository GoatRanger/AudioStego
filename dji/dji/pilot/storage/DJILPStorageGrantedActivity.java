package dji.pilot.storage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import dji.pilot.phonecamera.R;

public class DJILPStorageGrantedActivity extends Activity {
    private static final int b = 42;
    private String a = DJILPStorageGrantedActivity.class.getSimpleName();
    private FrameLayout c;
    private Button d;
    private boolean e = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.lp_storage_permission_tutorial_layout);
        b();
    }

    @TargetApi(21)
    void a() {
        Log.d(this.a, "openFolderChooserDialogSAF");
        startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 42);
    }

    @TargetApi(21)
    public void onActivityResult(int i, int i2, Intent intent) {
        Log.d(this.a, "onActivityResult: " + i);
        if (i == 42 && i2 == -1 && intent != null) {
            Uri data = intent.getData();
            Log.d(this.a, "returned treeUri: " + data);
            getContentResolver().takePersistableUriPermission(data, intent.getFlags() & 3);
            a(data);
            if (this.e) {
                b(data);
                finish();
                return;
            }
            Toast.makeText(this, getText(R.string.lp_camera_storage_set_failing_2), 0).show();
            finish();
        } else if (i == 42) {
            Log.d(this.a, "SAF dialog cancelled");
            String str = "";
            if (intent != null) {
                str = intent.getData().toString();
            }
            if (str.length() == 0) {
                Log.d(this.a, "no SAF save location was set");
                Toast.makeText(this, getText(R.string.lp_camera_storage_SAF_cancel), 0).show();
            }
            finish();
        }
    }

    @TargetApi(21)
    private void a(Uri uri) {
        if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
            String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
            Log.d(this.a, "checkAndCreateDir id: " + treeDocumentId);
            String[] split = treeDocumentId.split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                this.e = true;
            } else if (split.length >= 2) {
                this.e = false;
                Toast.makeText(this, getText(R.string.lp_camera_storage_set_failing_2), 0).show();
            } else {
                if (!a.a((Context) this, uri)) {
                    this.e = false;
                }
                this.e = true;
            }
        }
    }

    private void b(Uri uri) {
        a.a((Context) this, uri.toString());
    }

    private void b() {
        this.c = (FrameLayout) findViewById(R.id.lp_storage_framelayout);
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.lp_storage_turorial_1_layout, null, false);
        this.c.removeAllViews();
        this.c.addView(inflate);
        this.d = (Button) this.c.findViewById(R.id.lp_start_setting_step_1);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJILPStorageGrantedActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        if (!c()) {
            ((ImageView) this.c.findViewById(R.id.lp_storage_iv_1)).setImageDrawable(getResources().getDrawable(R.mipmap.lp_storage_toturial_en_1));
            ((ImageView) this.c.findViewById(R.id.lp_storage_iv_2)).setImageDrawable(getResources().getDrawable(R.mipmap.lp_storage_toturial_en_2));
            ((ImageView) this.c.findViewById(R.id.lp_storage_iv_3)).setImageDrawable(getResources().getDrawable(R.mipmap.lp_storage_toturial_en_3));
            ((ImageView) this.c.findViewById(R.id.lp_storage_iv_4)).setImageDrawable(getResources().getDrawable(R.mipmap.lp_storage_toturial_en_4));
        }
    }

    private boolean c() {
        return "zh".equals(getResources().getConfiguration().locale.getLanguage());
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
