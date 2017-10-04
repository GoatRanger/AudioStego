package dji.pilot.fpv.control;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.midware.data.model.P3.DataSpecialControl;

class b$9 implements OnClickListener {
    final /* synthetic */ b a;

    b$9(b bVar) {
        this.a = bVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DataSpecialControl.getInstance().setRecordType(b.o(this.a)).start(20);
        dialogInterface.dismiss();
    }
}
