package dji.phone.j;

import android.content.Context;
import dji.pilot.fpv.R;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class e {
    public static final int a = 0;
    public static final int b = 1;
    private static e c = new e();

    public static e getInstance() {
        return c;
    }

    private e() {
    }

    public String a(Context context, int i) {
        switch (i) {
            case 0:
                return b(context, R.raw.fragment_no_effect);
            case 1:
                return b(context, R.raw.fragment_no_effect);
            default:
                return b(context, R.raw.fragment_no_effect);
        }
    }

    public String b(Context context, int i) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i)));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append('\n');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
