package dji.sdksharedlib.e;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

public class c {
    public static Application a() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[]) null);
    }
}
