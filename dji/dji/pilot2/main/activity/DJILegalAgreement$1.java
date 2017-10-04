package dji.pilot2.main.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot2.publics.a.a;
import dji.thirdparty.a.c;

class DJILegalAgreement$1 implements OnClickListener {
    final /* synthetic */ DJILegalAgreement a;

    DJILegalAgreement$1(DJILegalAgreement dJILegalAgreement) {
        this.a = dJILegalAgreement;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.f0) {
            a.getInstance().a("1.0.1", String.valueOf(System.currentTimeMillis()));
            if (DJILegalAgreement.a(this.a)) {
                this.a.startActivity(new Intent(this.a, DJIMainFragmentActivity.class));
            }
            this.a.finish();
        } else if (view.getId() == R.id.ha) {
            Builder builder = new Builder(this.a);
            builder.setTitle(R.string.fly_unlimit_legal_agreement_dlg_title).setMessage(R.string.fly_unlimit_legal_agreement_dlg_message).setPositiveButton(R.string.fly_unlimit_legal_agreement_dlg_ok, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJILegalAgreement$1 a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    a.getInstance().b();
                    dialogInterface.dismiss();
                    c.a().e(a.a.b);
                    this.a.a.finish();
                }
            });
            builder.setNegativeButton(R.string.fly_unlimit_legal_agreement_dlg_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJILegalAgreement$1 a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    }
}
