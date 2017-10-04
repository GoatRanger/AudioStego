package dji.pilot2.simulator;

import com.unity3d.player.UnityPlayer;

public class e {
    public static final String a = "JavaBridge";

    public static void a(String str, String str2, String str3) {
        UnityPlayer.UnitySendMessage(str, str2, str3);
    }
}
