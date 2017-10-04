package dji.pilot2.multimoment.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import dji.pilot.R;
import dji.pilot2.utils.n;
import java.util.ArrayList;
import java.util.List;

public class b {
    private static b b = new b();
    public List<a> a = null;

    private b() {
    }

    public static b getInstance() {
        return b;
    }

    public void a(Context context) {
    }

    private Bitmap[] a(Context context, int i) {
        Bitmap[] bitmapArr = new Bitmap[i];
        String o = n.o(context);
        for (int i2 = 0; i2 < i; i2++) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.RGB_565;
            options.inSampleSize = 1;
            StringBuffer stringBuffer = new StringBuffer(o);
            stringBuffer.append(String.valueOf(i2));
            stringBuffer.append(".png");
            bitmapArr[i2] = BitmapFactory.decodeFile(stringBuffer.toString(), options);
        }
        return bitmapArr;
    }

    public synchronized List<a> b(Context context) {
        if (this.a == null) {
            String[] stringArray = context.getResources().getStringArray(R.array.em);
            int length = stringArray.length;
            Bitmap[] a = a(context, length);
            this.a = new ArrayList();
            for (int i = 0; i < length; i++) {
                this.a.add(new a(stringArray[i], a[i]));
            }
        }
        return this.a;
    }
}
