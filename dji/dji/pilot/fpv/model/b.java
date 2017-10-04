package dji.pilot.fpv.model;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.MediaStore.Images.Media;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.mine.e.a;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;

public class b {
    private static String a = "";
    private static Display b;

    public static int a(Context context, int i) {
        return (int) context.getResources().getDimension(i);
    }

    public static String a() {
        return a;
    }

    public static void a(Context context) {
        if (a.equals("")) {
            switch (context.getResources().getConfiguration().screenLayout & 15) {
                case 1:
                    a = "small";
                    break;
                case 2:
                    a = "normal";
                    break;
                case 3:
                    a = "large";
                    break;
                case 4:
                    a = "xlarge";
                    break;
                default:
                    a = a.a;
                    break;
            }
            DJILogHelper.getInstance().LOGD("", "sizename=" + a);
        }
    }

    public static void b(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        context.startActivity(intent);
    }

    public static int c(Context context) {
        if (b == null) {
            b = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        }
        return b.getRotation();
    }

    public static final void d(Context context) {
        Toast.makeText(context, R.string.gs_turnon_gps, 0).show();
        context.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    public static boolean e(Context context) {
        int intExtra = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1);
        if (intExtra == 1 || intExtra == 2) {
            return true;
        }
        return false;
    }

    public static b a(Context context, String str) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        DefaultHandler cVar = new c();
        try {
            newInstance.newSAXParser().parse(context.openFileInput(str), cVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.a(cVar);
    }
}
