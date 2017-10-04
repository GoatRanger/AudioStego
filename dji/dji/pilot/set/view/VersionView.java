package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.pilot.set.R;
import dji.pilot.set.view.a.b;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class VersionView extends LinearLayout {
    private ArrayList<b> a;

    public VersionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        CharSequence charSequence;
        Object obj;
        super.onAttachedToWindow();
        TextView textView = (TextView) findViewById(R.id.version_app_value);
        String str = "1.1";
        try {
            Class cls = Class.forName("dji.pilot.reflect.SetReflect");
            charSequence = (String) cls.getMethod("getAppVersion", new Class[0]).invoke(cls, new Object[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            obj = str;
        } catch (NoSuchMethodException e2) {
            obj = str;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            obj = str;
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
            obj = str;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            obj = str;
        }
        textView.setText(charSequence);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
