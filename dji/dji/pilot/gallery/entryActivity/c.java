package dji.pilot.gallery.entryActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.gallery.entryActivity.d.b;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJITextView;

public class c extends dji.pilot.publics.objects.c implements OnClickListener {
    private Context a;
    private DJIStateTextView b;
    private DJIStateTextView c;
    private a d;
    private DJITextView e;
    private DJITextView f;
    private String g;
    private String h;

    public interface a {
        void a();

        void b();
    }

    public c(Context context) {
        super(context);
        this.a = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gallery_delete_confirm);
        b();
        setCanceledOnTouchOutside(true);
    }

    private void b() {
        this.b = (DJIStateTextView) findViewById(R.id.ag1);
        this.c = (DJIStateTextView) findViewById(R.id.ag2);
        this.e = (DJITextView) findViewById(R.id.afy);
        this.f = (DJITextView) findViewById(R.id.afz);
        this.e.setText(this.g);
        this.f.setText(this.h);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a(int i) {
        this.g = String.format(this.a.getResources().getString(R.string.v2_hg_delete_num), new Object[]{Integer.valueOf(i)});
    }

    public void a(b bVar) {
        String str = "";
        if (bVar == b.Type_IMG) {
            str = this.a.getResources().getString(R.string.v2_hg_Photo);
        } else {
            str = this.a.getResources().getString(R.string.v2_hg_Video);
        }
        this.g = String.format(this.a.getResources().getString(R.string.v2_hg_delete_single_dialog_title), new Object[]{str});
    }

    public void a(String str) {
        this.h = str;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ag1:
                if (this.d != null) {
                    this.d.a();
                }
                dismiss();
                return;
            case R.id.ag2:
                if (this.d != null) {
                    this.d.b();
                }
                dismiss();
                return;
            default:
                return;
        }
    }

    public static void a(Context context, int i, a aVar) {
        c cVar = new c(context);
        cVar.a(aVar);
        cVar.a(i);
        cVar.a(context.getResources().getString(R.string.v2_hg_delete_dialog_content));
        cVar.show();
    }

    public static void a(Context context, b bVar, a aVar) {
        c cVar = new c(context);
        cVar.a(aVar);
        cVar.a(bVar);
        String str = "";
        if (bVar == b.Type_IMG) {
            str = context.getResources().getString(R.string.v2_hg_Photo);
        } else {
            str = context.getResources().getString(R.string.v2_hg_Video);
        }
        cVar.a(String.format(context.getResources().getString(R.string.v2_hg_delete_single_dialog_content), new Object[]{str}));
        cVar.show();
    }
}
