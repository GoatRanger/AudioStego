package dji.setting.ui.battery;

public class a {
    private static int a = 0;

    public static void a() {
        if (a == 0) {
            dji.pilot.battery.a.a.getInstance().b(false);
        }
        a++;
    }

    public static void b() {
        a--;
        if (a == 0) {
            dji.pilot.battery.a.a.getInstance().e();
        }
    }
}
