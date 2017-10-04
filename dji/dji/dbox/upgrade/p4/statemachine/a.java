package dji.dbox.upgrade.p4.statemachine;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;

public class a {
    public static b a(Context context, g gVar, String str) {
        try {
            return (b) Class.forName("dji.dbox.upgrade.p4.statemachine.DJIUpCollectPack_" + str + "_Manager").getDeclaredConstructor(new Class[]{g.class, Context.class}).newInstance(new Object[]{gVar, context});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        } catch (NoSuchMethodException e6) {
            e6.printStackTrace();
        }
        return null;
    }
}
