package dji.pilot.usercenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.usercenter.f.e;
import dji.publics.DJIUI.DJITextView;

public class DJIModifyNameActivity extends DJIBaseActivity implements OnClickListener {
    public static final String a = "key_name";
    private EditText b = null;
    private String c;
    private DJITextView d = null;
    private DJITextView e = null;
    private TextWatcher f = new TextWatcher(this) {
        final /* synthetic */ DJIModifyNameActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.c == null || !this.a.c.equals(charSequence.toString())) {
                this.a.d.setEnabled(true);
            } else {
                this.a.d.setEnabled(false);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        a();
    }

    private void a() {
        setContentView(R.layout.usercenter_myinfo_modify_name);
        this.b = (EditText) findViewById(R.id.c3h);
        this.d = (DJITextView) findViewById(R.id.c3f);
        this.e = (DJITextView) findViewById(R.id.c3c);
        this.e.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.b.addTextChangedListener(this.f);
        if (this.c != null) {
            this.b.setText(this.c);
        }
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra(a);
        }
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
        if (e.d(this.b.getEditableText().toString())) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "illegal name", 0).show();
        }
    }
}
