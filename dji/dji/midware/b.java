package dji.midware;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.media.DJIVideoDataRecver.a;
import dji.midware.natives.FPVController;
import dji.midware.usb.P3.UsbAccessoryService;

public class b {
    public static final boolean b = true;
    public final String a = "MidWare";

    public static void a(Context context) {
        FPVController.loadLibrary();
        dji.publics.DJIObject.b.getInstance().a(context);
        DJILogHelper.getInstance().init(context);
        ServiceManager.setContext(context);
        ServiceManager.getInstance().a();
        DJIVideoDataRecver.getInstance().setDecoderType(a.Hardware);
        i.build(context);
        UsbAccessoryService.registerAoaReceiver(context);
        ServiceManager.getInstance().b();
        dji.midware.c.a.getInstance().a(context);
    }

    public static void a() {
    }
}
