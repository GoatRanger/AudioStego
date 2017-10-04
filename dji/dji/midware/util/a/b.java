package dji.midware.util.a;

import android.os.Environment;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class b {
    public static boolean a = false;
    private static b d;
    public BufferedWriter b = null;
    public BufferedWriter c = null;

    public static b getInstance() {
        if (d == null) {
            d = new b();
        }
        return d;
    }

    private b() {
        if (this.b == null) {
            try {
                this.b = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/dji_streamdelay_frame.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.c == null) {
            try {
                this.c = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/dji_streamdelay_packet.txt"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
