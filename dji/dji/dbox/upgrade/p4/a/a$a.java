package dji.dbox.upgrade.p4.a;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class a$a {
    public static boolean a = false;
    public static Queue<File> b = new LinkedList();

    public static class a {
        public static String a = "P4";
        public static String b = "01.02.0503";
        public static String c = "1234567890";
        public static String d = "f17f91fa-215f-4f28-a76e-bc2b73c93c6a";
    }

    static {
        b.add(new File(a.e + "1.txt"));
        b.add(new File(a.e + "2.txt"));
    }
}
