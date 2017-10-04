package dji.pilot2.share.d;

import android.content.Context;
import android.content.res.Resources;
import java.util.Random;

public class a {
    private Random a = new Random();
    private int b;
    private Context c;
    private dji.pilot2.share.model.a d;

    public a(Context context) {
        this.c = context;
    }

    public dji.pilot2.share.model.a a() {
        if (this.d == null) {
            b();
        }
        return this.d;
    }

    public void b() {
        this.d = a(c());
    }

    private int c() {
        int nextInt;
        do {
            nextInt = this.a.nextInt(5) + 1;
        } while (this.b == nextInt);
        this.b = nextInt;
        return nextInt;
    }

    private dji.pilot2.share.model.a a(int i) {
        try {
            Resources resources = this.c.getResources();
            return new dji.pilot2.share.model.a(resources.getString(resources.getIdentifier("v2_share_text_title_" + i, "string", this.c.getPackageName())), resources.getString(resources.getIdentifier("v2_share_text_desc_" + i, "string", this.c.getPackageName())));
        } catch (Exception e) {
            return new dji.pilot2.share.model.a();
        }
    }
}
