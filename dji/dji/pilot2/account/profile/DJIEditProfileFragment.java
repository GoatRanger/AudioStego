package dji.pilot2.account.profile;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJIStateButton;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.f.c;
import dji.pilot.usercenter.f.e;
import dji.pilot2.account.profile.a.b;
import dji.pilot2.main.fragment.DJIMineFragment;
import dji.pilot2.usercenter.widget.DJIProfileRoundImageView;
import dji.pilot2.utils.m;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.Locale;

public class DJIEditProfileFragment extends Fragment implements OnClickListener, b {
    public static final String a = Environment.getExternalStorageDirectory().getPath();
    private static final int b = 16;
    private static final int c = 32;
    private static final int d = 30000;
    private static String u = (a + "/DCIM/Camera/profile_capture.jpg");
    private dji.pilot2.account.profile.a.a e;
    private DJIStateImageView f;
    private DJIProfileRoundImageView g;
    private DJIEditText h;
    private DJIStateButton i;
    private DJIStateButton j;
    private DJIStateButton k;
    private DJITextView l;
    private DJIImageView m;
    private dji.pilot2.share.b.a n = null;
    private PopupWindow o = null;
    private dji.pilot2.usercenter.b.a p = null;
    private Bitmap q = null;
    private int r = 0;
    private String s;
    private boolean t = false;

    public enum a {
        TRUE,
        FALSE
    }

    public void a(Bitmap bitmap) {
        if (bitmap != null) {
            this.g.setImageBitmap(bitmap);
        }
    }

    public void a(boolean z, String str, String str2) {
        this.l.setText(str);
        if (getActivity() != null) {
            this.l.setTextColor(getActivity().getResources().getColor(R.color.gd));
        }
        this.m.setVisibility(8);
        this.s = str2;
        this.t = true;
    }

    public void a(boolean z, String str) {
        c();
        if (z) {
            b();
        } else {
            Toast.makeText(getActivity(), str, 0).show();
        }
    }

    public void a(boolean z, String str, String str2, int i, String str3, String str4) {
        if (z) {
            this.h.setText(str2);
            a(i);
            if (m.c(str) || getActivity().getIntent().getBooleanExtra(DJIEditProfileActivity.a, false)) {
                this.g.setImageResource(R.drawable.profile_edit_no_avatar);
            } else {
                h();
            }
            if (m.c(str3)) {
                this.e.b();
                this.t = false;
                getView().postDelayed(new Runnable(this) {
                    final /* synthetic */ DJIEditProfileFragment a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (!this.a.t) {
                            DJILogHelper.getInstance().LOGD("DJIEditProfilePresenter", "check location timeout");
                            this.a.e.c();
                            this.a.e.a(Locale.getDefault().getCountry());
                        }
                    }
                }, 30000);
                return;
            }
            this.e.a(str4);
        }
    }

    public void a(Object obj) {
        this.e = (dji.pilot2.account.profile.a.a) obj;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_fragment_profile, viewGroup);
        this.f = (DJIStateImageView) inflate.findViewById(R.id.ci);
        this.f.setOnClickListener(this);
        inflate.findViewById(R.id.c1).setVisibility(8);
        this.g = (DJIProfileRoundImageView) inflate.findViewById(R.id.coh);
        this.g.setOnClickListener(this);
        this.h = (DJIEditText) inflate.findViewById(R.id.coi);
        this.i = (DJIStateButton) inflate.findViewById(R.id.col);
        this.i.setOnClickListener(this);
        this.j = (DJIStateButton) inflate.findViewById(R.id.com);
        this.j.setOnClickListener(this);
        this.k = (DJIStateButton) inflate.findViewById(R.id.coo);
        this.k.setOnClickListener(this);
        this.l = (DJITextView) inflate.findViewById(R.id.cos);
        this.m = (DJIImageView) inflate.findViewById(R.id.cnm);
        inflate.findViewById(R.id.cot).setOnClickListener(this);
        inflate.findViewById(R.id.coq).setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ci:
                b();
                return;
            case R.id.coh:
                g();
                return;
            case R.id.col:
                a(1);
                return;
            case R.id.com:
                a(2);
                return;
            case R.id.coo:
                a(0);
                return;
            case R.id.coq:
                a();
                return;
            case R.id.cot:
                a(getActivity());
                this.e.a(this.g.getWidth(), this.g.getHeight());
                this.e.a(this.h.getText().toString(), this.r, this.l.getText().toString(), this.s);
                return;
            default:
                return;
        }
    }

    private void a() {
        this.e.c();
        i();
    }

    private void a(int i) {
        if (isAdded()) {
            switch (this.r) {
                case 1:
                    this.i.setBackgroundResource(R.drawable.profile_chose_male);
                    this.i.setTextColor(getActivity().getResources().getColor(R.color.gd));
                    break;
                case 2:
                    this.j.setBackgroundResource(R.drawable.profile_chose_female);
                    this.j.setTextColor(getActivity().getResources().getColor(R.color.gd));
                    break;
                default:
                    this.k.setBackgroundResource(R.drawable.profile_chose_secret);
                    this.k.setTextColor(getActivity().getResources().getColor(R.color.gd));
                    break;
            }
            this.r = i;
            switch (this.r) {
                case 1:
                    this.i.setBackgroundResource(R.drawable.profile_chose_male_chosen);
                    this.i.setTextColor(getActivity().getResources().getColor(R.color.om));
                    return;
                case 2:
                    this.j.setBackgroundResource(R.drawable.profile_chose_female_chosen);
                    this.j.setTextColor(getActivity().getResources().getColor(R.color.om));
                    return;
                default:
                    this.k.setBackgroundResource(R.drawable.profile_chose_secret_chosen);
                    this.k.setTextColor(getActivity().getResources().getColor(R.color.om));
                    return;
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        Cursor query;
        if (16 == i) {
            if (i2 != -1) {
                return;
            }
            if (intent != null) {
                try {
                    data = intent.getData();
                    if (data != null) {
                        query = getActivity().getContentResolver().query(data, null, null, null, null);
                        if (query != null) {
                            if (query.moveToFirst()) {
                                this.e.a(query.getString(query.getColumnIndex("_data")), this.g.getWidth(), this.g.getHeight());
                            }
                            query.close();
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            }
            try {
                this.e.a(u, this.g.getWidth(), this.g.getHeight());
            } catch (Exception e2) {
            }
        } else if (32 == i && i2 == -1) {
            data = intent.getData();
            query = getActivity().getContentResolver().query(data, null, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("_data");
                    if (columnIndex == -1) {
                        this.e.a(data, this.g.getWidth(), this.g.getHeight());
                    } else {
                        this.e.a(query.getString(columnIndex), this.g.getWidth(), this.g.getHeight());
                    }
                }
                query.close();
            }
        }
    }

    public void onResume() {
        super.onResume();
        if (this.s != null) {
            this.e.a(this.s);
        }
    }

    private void b() {
        getActivity().setResult(-1);
        DJIMineFragment.P.sendEmptyMessage(4);
        getActivity().finish();
    }

    private void a(Context context) {
        if (this.n == null) {
            this.n = new dji.pilot2.share.b.a(context);
        }
        if (this.n != null && !this.n.isShowing()) {
            this.n.show();
        }
    }

    private void c() {
        if (this.n != null && this.n.isShowing()) {
            this.n.dismiss();
            this.n = null;
        }
    }

    private void d() {
        f();
        startActivityForResult(e.e(u), 16);
    }

    private void e() {
        f();
        try {
            startActivityForResult(e.f(null), 32);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void f() {
        if (this.o != null && this.o.isShowing()) {
            this.o.dismiss();
        }
    }

    private void g() {
        dji.pilot2.account.a.a aVar = new dji.pilot2.account.a.a(getActivity());
        aVar.a(new dji.pilot2.account.a.a.a(this) {
            final /* synthetic */ DJIEditProfileFragment a;

            {
                this.a = r1;
            }

            public void a() {
                dji.pilot.fpv.d.e.a("UserCenter_ProfileView_Button_EditAvatarImage_Capture");
                this.a.d();
            }

            public void b() {
                dji.pilot.fpv.d.e.a("UserCenter_ProfileView_Button_EditAvatarImage_Gallery");
                this.a.e();
            }
        });
        aVar.show();
    }

    private void h() {
        String e = f.getInstance().e();
        if (this.q == null && c.a(e)) {
            this.q = BitmapFactory.decodeFile(e);
        }
        if (this.q != null) {
            this.g.setImageBitmap(this.q);
        }
    }

    private void i() {
        this.p = null;
        if (this.p == null) {
            this.p = new dji.pilot2.usercenter.b.a(getActivity());
            this.p.a(new dji.pilot2.usercenter.b.a.a(this) {
                final /* synthetic */ DJIEditProfileFragment a;

                {
                    this.a = r1;
                }

                public void a(dji.pilot.usercenter.e.b bVar) {
                    if (bVar != null && !m.c(bVar.b)) {
                        this.a.a(true, bVar.b, bVar.a);
                    }
                }
            });
        }
        if (!this.p.isShowing()) {
            this.p.show();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        j();
        DJITextView dJITextView = (DJITextView) getActivity().findViewById(R.id.c4d);
        if (getActivity().getIntent().getBooleanExtra(DJIEditProfileActivity.a, false)) {
            dJITextView.setText(getActivity().getResources().getString(R.string.profile_register));
        } else {
            dJITextView.setText(getActivity().getResources().getString(R.string.mine_profile_edit));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        k();
    }

    private void j() {
    }

    private void k() {
    }
}
