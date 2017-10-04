package dji.pilot2.account.forget;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.publics.e.d;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJIStateButton;
import dji.pilot2.account.forget.a.b;
import dji.pilot2.share.b.a;
import dji.pilot2.utils.m;
import dji.publics.DJIUI.DJITextView;

public class DJIAccountForgetFragment extends Fragment implements OnClickListener, b {
    private b a;
    private DJIEditText b;
    private DJIStateButton c;
    private a d = null;
    private TextWatcher e = new TextWatcher(this) {
        final /* synthetic */ DJIAccountForgetFragment a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (m.c(this.a.b.getText().toString())) {
                this.a.b(this.a.c);
            } else {
                this.a.a(this.a.c);
            }
        }
    };

    public void a(boolean z, String str) {
        b();
        if (z) {
            Intent intent = new Intent();
            intent.putExtra("email", str);
            intent.setClass(getActivity(), DJIForgetSuccessActivity.class);
            startActivity(intent);
            getActivity().finish();
            return;
        }
        Toast.makeText(getActivity(), str, 1).show();
    }

    public void a(Object obj) {
        this.a = (b) obj;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_fragment_account_forget, viewGroup);
        this.b = (DJIEditText) inflate.findViewById(R.id.cn6);
        this.b.addTextChangedListener(this.e);
        this.c = (DJIStateButton) inflate.findViewById(R.id.cn7);
        this.c.setOnClickListener(this);
        inflate.findViewById(R.id.ci).setOnClickListener(this);
        ((DJITextView) inflate.findViewById(R.id.c4d)).setText(R.string.home_account_forgot_pwd);
        inflate.findViewById(R.id.c1).setVisibility(8);
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ci:
                getActivity().finish();
                return;
            case R.id.cn7:
                a();
                return;
            default:
                return;
        }
    }

    private void a() {
        String obj = this.b.getText().toString();
        if (m.c(obj) || !d.b(obj)) {
            Toast.makeText(getActivity(), getResources().getString(R.string.home_account_email_illegal), 0).show();
            return;
        }
        a(getActivity());
        this.a.a(obj);
    }

    private void a(Button button) {
        button.setBackgroundResource(R.drawable.btn_sign_enable);
        button.setTextColor(getActivity().getResources().getColor(R.color.om));
        button.setEnabled(true);
    }

    private void b(Button button) {
        button.setBackgroundResource(R.drawable.btn_sign_disable);
        button.setTextColor(getActivity().getResources().getColor(R.color.ju));
        button.setEnabled(false);
    }

    private void a(Context context) {
        if (this.d == null) {
            this.d = new a(context);
        }
        if (this.d != null && !this.d.isShowing()) {
            this.d.show();
        }
    }

    private void b() {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
            this.d = null;
        }
    }
}
