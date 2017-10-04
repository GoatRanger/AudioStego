package dji.pilot.publics.c;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class a {
    public static boolean a = false;
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;
    public static boolean e = false;
    private static String f = ".DJI.configs";

    public static void a(Context context) {
        boolean z = true;
        File file = new File(context.getExternalFilesDir(null), f);
        if (file.exists()) {
            dji.pilot.c.a.j = 0;
            try {
                boolean z2;
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                randomAccessFile.seek(36);
                a = randomAccessFile.readInt() == 1;
                randomAccessFile.skipBytes(2);
                short readShort = randomAccessFile.readShort();
                if (readShort < (short) 0 || readShort > (short) 2) {
                    dji.pilot.c.a.j = 0;
                } else {
                    dji.pilot.c.a.j = readShort;
                }
                randomAccessFile.skipBytes(5);
                byte readByte = randomAccessFile.readByte();
                b = (readByte & 1) != 0;
                if ((readByte & 2) != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                c = z2;
                if ((readByte & 4) != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                d = z2;
                if ((readByte & 8) == 0) {
                    z = false;
                }
                e = z;
                randomAccessFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
