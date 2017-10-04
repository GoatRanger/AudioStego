package dji.thirdparty.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class d {
    public static int a = 0;
    public static Class<?> b;

    @TargetApi(11)
    public static class a extends DialogFragment implements OnClickListener {
        public Dialog onCreateDialog(Bundle bundle) {
            return d.a(getActivity(), getArguments(), this);
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            d.a(dialogInterface, i, getActivity(), getArguments());
        }
    }

    public static class b extends DialogFragment implements OnClickListener {
        public Dialog onCreateDialog(Bundle bundle) {
            return d.a(getActivity(), getArguments(), this);
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            d.a(dialogInterface, i, getActivity(), getArguments());
        }
    }

    public static Dialog a(Context context, Bundle bundle, OnClickListener onClickListener) {
        Builder builder = new Builder(context);
        builder.setTitle(bundle.getString(e.d));
        builder.setMessage(bundle.getString(e.e));
        if (a != 0) {
            builder.setIcon(a);
        }
        builder.setPositiveButton(17039370, onClickListener);
        return builder.create();
    }

    public static void a(DialogInterface dialogInterface, int i, Activity activity, Bundle bundle) {
        if (b != null) {
            try {
                e.a.a.b().e(b.newInstance());
            } catch (Throwable e) {
                throw new RuntimeException("Event cannot be constructed", e);
            }
        }
        if (bundle.getBoolean(e.f, false) && activity != null) {
            activity.finish();
        }
    }
}
