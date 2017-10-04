package dji.pilot2.mine.activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.usercenter.f.e;
import dji.pilot2.mine.b.f;
import dji.pilot2.mine.b.f.a;
import dji.pilot2.mine.jsonbean.RepairEvent;
import dji.pilot2.mine.jsonbean.UploadAttachmentBean;
import dji.thirdparty.a.c;

public class RepairWebviewActivity extends WebActivity {
    public static final String a = "key_force_landscap";
    private static final int z = 1024;
    private String A = "";
    private ProgressDialog B;
    public boolean r = false;
    private f y;

    protected void onCreate(Bundle bundle) {
        this.y = f.getInstance();
        this.r = getIntent().getBooleanExtra("key_force_landscap", false);
        c.a().a(this);
        super.onCreate(bundle);
    }

    private void a() {
        try {
            startActivityForResult(e.f(null), 1024);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1024) {
            final String a = f.a(intent.getData(), getContentResolver());
            if ("".equals(a)) {
                Toast.makeText(this, getString(R.string.mine_repair_file_not_correct), 1).show();
                return;
            }
            f();
            new Thread(new Runnable(this) {
                final /* synthetic */ RepairWebviewActivity b;

                public void run() {
                    this.b.a(f.a(a, "note_text", this.b.A));
                }
            }).start();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.r && getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
    }

    private void f() {
        if (this.B == null) {
            this.B = new ProgressDialog(this, 0);
            this.B.setMessage(getString(R.string.mine_repair_file_uploading));
            this.B.setCancelable(true);
        }
        this.B.show();
    }

    private void g() {
        if (this.B != null) {
            this.B.dismiss();
        }
    }

    private void a(UploadAttachmentBean uploadAttachmentBean) {
        this.y.a((Context) this, uploadAttachmentBean, new a(this) {
            final /* synthetic */ RepairWebviewActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.u.b("javascript:getUploadedImageUrl('" + str + "')");
                this.a.g();
            }

            public void b(String str) {
                this.a.g();
                Toast.makeText(this.a, str, 1).show();
            }
        });
    }

    public void onEventMainThread(RepairEvent.a aVar) {
        this.A = aVar.a();
        a();
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a().d(this);
    }

    public void onBackPressed() {
        c();
    }
}
