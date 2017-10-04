package dji.pilot.usercenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import dji.pilot.R;

public class DJISetPasswordActivity extends Activity implements OnClickListener {
    private EditText a;
    private EditText b;
    private EditText c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.usercenter_myinfo_set_password);
        b();
        a();
    }

    private void a() {
        View findViewById = findViewById(R.id.c3f);
        View findViewById2 = findViewById(R.id.c3c);
        findViewById.setOnClickListener(this);
        findViewById2.setOnClickListener(this);
    }

    private void b() {
        this.a = (EditText) findViewById(R.id.c3l);
        this.b = (EditText) findViewById(R.id.c3m);
        this.c = (EditText) findViewById(R.id.c3n);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c3c:
                finish();
                return;
            case R.id.c3f:
                c();
                return;
            default:
                return;
        }
    }

    private void c() {
        if (!this.b.getEditableText().toString().equals(this.c.getEditableText().toString())) {
            Toast.makeText(this, "Passwords do not match", 0).show();
        }
    }
}
