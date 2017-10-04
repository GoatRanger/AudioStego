package dji.pilot2.account.a;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;

public class a extends dji.pilot2.publics.object.a implements OnClickListener {
    private a b;

    public interface a {
        void a();

        void b();
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public a(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public a(Context context, int i) {
        super(context, i);
    }

    public a(Context context) {
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_avatar_bottom_sheet_dialog);
        a();
    }

    private void a() {
        findViewById(R.id.cho).setOnClickListener(this);
        findViewById(R.id.chq).setOnClickListener(this);
        findViewById(R.id.re).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re:
                dismiss();
                return;
            case R.id.cho:
                if (this.b != null) {
                    this.b.a();
                }
                dismiss();
                return;
            case R.id.chq:
                if (this.b != null) {
                    this.b.b();
                }
                dismiss();
                return;
            default:
                return;
        }
    }
}
