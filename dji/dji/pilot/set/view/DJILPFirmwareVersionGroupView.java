package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.set.view.base.SetGroupView;
import java.lang.reflect.InvocationTargetException;

public class DJILPFirmwareVersionGroupView extends SetGroupView {
    String a = "N/A";

    public DJILPFirmwareVersionGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            Class cls = Class.forName("dji.pilot.reflect.SetReflect");
            this.a = (String) cls.getMethod("getFirmwareVersion", new Class[0]).invoke(cls, new Object[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
        this.b.setText(this.a);
    }
}
