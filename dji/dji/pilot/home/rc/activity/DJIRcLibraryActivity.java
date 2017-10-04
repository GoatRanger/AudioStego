package dji.pilot.home.rc.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.d.c.j;
import dji.pilot.fpv.d.c.k;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.library.DJILibraryPhotoView;
import dji.pilot2.library.a;
import dji.pilot2.library.c;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.pilot2.media.activity.DJIPhotoEditorActivity;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.multimoment.videolib.d;
import dji.pilot2.publics.object.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class DJIRcLibraryActivity extends DJIActivityNoFullScreen implements OnClickListener, j, k, c {
    private static final int K = 0;
    private static final int L = 1;
    private static final int M = 2;
    private static final int N = 3;
    private static final int O = 4;
    private static int P = 0;
    private int Q = 1;
    private volatile boolean R;
    private DJIRelativeLayout S;
    private DJIRelativeLayout T = null;
    private DJIRelativeLayout U;
    private DJIImageView V = null;
    private DJITextView W = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.rc_main_library);
        b();
    }

    private void b() {
        this.S = (DJIRelativeLayout) findViewById(R.id.bjx);
        this.T = (DJIRelativeLayout) findViewById(R.id.bk0);
        this.U = (DJIRelativeLayout) findViewById(R.id.bjz);
        this.W = (DJITextView) findViewById(R.id.bk3);
        this.V = (DJIImageView) findViewById(R.id.bk2);
        this.V.setOnClickListener(this);
        this.W.setOnClickListener(this);
    }

    public void a(int i, int i2) {
        Log.i("zvb", "enter" + i2);
        Log.i("zvb", "enter typeMode" + i);
        P = i;
        this.S.setBackgroundColor(getResources().getColor(R.color.na));
        this.T.setBackgroundColor(getResources().getColor(R.color.na));
        if (i == 1) {
            this.W.setClickable(true);
            this.W.show();
            this.V.show();
            this.Q = 1;
            if (i2 > 1) {
                this.W.setText(getResources().getString(R.string.v2_library_video_selected_mult));
                this.W.setEnabled(true);
            } else {
                this.W.setText(getResources().getString(R.string.v2_library_video_selected_single));
                this.W.setEnabled(true);
            }
            this.T.show();
        } else if (i == 2) {
            this.Q = 2;
            this.V.show();
            this.W.show();
            if (i2 == 1) {
                this.W.setClickable(true);
                this.W.setText(getResources().getString(R.string.v2_photo_preview_share_photo));
                this.W.setEnabled(true);
            } else {
                this.W.setClickable(false);
                this.W.setText(getResources().getString(R.string.v2_photo_preview_share_photo));
                this.W.setEnabled(false);
            }
            this.T.show();
        } else if (i == 3) {
            this.Q = 3;
            this.V.go();
            this.W.setText(getResources().getString(R.string.v2_lib_input));
            if (i2 == 0) {
                this.W.setClickable(false);
                this.W.setEnabled(false);
                this.U.go();
                this.W.go();
                this.T.go();
                this.S.setBackgroundColor(getResources().getColor(R.color.om));
                this.T.setBackgroundColor(getResources().getColor(R.color.om));
                return;
            }
            this.U.show();
            this.W.show();
            this.W.setClickable(true);
            this.W.setEnabled(true);
            this.T.show();
            this.S.setBackgroundColor(getResources().getColor(R.color.na));
            this.T.setBackgroundColor(getResources().getColor(R.color.na));
        } else if (i == 4) {
            this.Q = 4;
            this.W.setText(getResources().getString(R.string.delete));
            this.V.go();
            if (i2 == 0) {
                this.W.setClickable(false);
                this.W.setEnabled(false);
                this.U.go();
                this.W.go();
                this.S.setBackgroundColor(getResources().getColor(R.color.om));
                this.T.setBackgroundColor(getResources().getColor(R.color.om));
                return;
            }
            this.W.setClickable(true);
            this.W.setEnabled(true);
            this.W.show();
            this.U.go();
            this.S.setBackgroundColor(getResources().getColor(R.color.na));
            this.T.setBackgroundColor(getResources().getColor(R.color.na));
            this.T.show();
        }
    }

    public void a() {
        P = 0;
        this.S.setBackgroundColor(getResources().getColor(R.color.om));
        this.T.go();
        this.U.show();
    }

    private void f() {
        CharSequence string;
        Builder bVar = new b(this);
        if (this.Q == 1) {
            string = getResources().getString(R.string.fpv_playback_del_moment);
        } else {
            string = getResources().getString(R.string.fpv_playback_del_image);
        }
        bVar.setMessage(string);
        bVar.setPositiveButton(R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIRcLibraryActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.g();
            }
        });
        bVar.setNegativeButton(R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIRcLibraryActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    private void g() {
        if (this.Q == 1) {
            dji.thirdparty.a.c.a().e(a.MomentMainDelete);
        } else {
            dji.thirdparty.a.c.a().e(a.PhotoMainDelete);
        }
        a();
        ((DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c)).a.exitSelectMode();
    }

    private void a(int i) {
        Builder bVar = new b(this);
        bVar.setMessage(getResources().getString(R.string.v2_multimoment_selectduration_limit));
        bVar.setNeutralButton(R.string.v2_library_02, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIRcLibraryActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bk2:
                f();
                return;
            case R.id.bk3:
                if (this.R) {
                    this.R = false;
                    DJILibraryFragment dJILibraryFragment = (DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c);
                    if (this.Q == 2) {
                        DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) DJILibraryPhotoView.B.get(0);
                        if (dJISycAlbumModel != null) {
                            Intent intent = new Intent();
                            intent.setClass(this, DJIPhotoEditorActivity.class);
                            if (dJISycAlbumModel.isOrg) {
                                intent.putExtra("key_filepath", dJISycAlbumModel.orgPath);
                            } else {
                                intent.putExtra("key_filepath", dJISycAlbumModel.mLocalInfo.e);
                            }
                            startActivity(intent);
                        }
                    } else if (this.Q == 1) {
                        List list = dJILibraryFragment.a.r.q;
                        String[] strArr = new String[list.size()];
                        int[] iArr = new int[list.size()];
                        int i = 0;
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            strArr[i2] = ((g) list.get(i2)).e;
                            iArr[i2] = d.a(strArr[i2]);
                            i += iArr[i2];
                        }
                        if (i <= dji.pilot2.multimoment.videolib.c.b || list.size() <= 1) {
                            if (strArr.length == 1) {
                                e.b(k.bx_);
                            } else {
                                e.b(k.bw_);
                            }
                            Intent intent2 = new Intent(this, DJIMultiMomentEditActivity.class);
                            intent2.putExtra(DJIMultiMomentEditActivity.M, strArr);
                            intent2.putExtra("duration", iArr);
                            startActivity(intent2);
                        } else {
                            a(i);
                            this.R = true;
                            return;
                        }
                    } else if (this.Q == 3) {
                        Log.i("zc", "click input !");
                        r1 = (DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c);
                        if (r1 != null) {
                            r1.a(DJIScanerMediaManager.getInstance(this).getFlagVideo());
                        }
                    } else if (this.Q == 4) {
                        Log.i("zc", "click delete !");
                        r1 = (DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c);
                        if (r1 != null) {
                            r1.a();
                        }
                    }
                    dJILibraryFragment.a.exitSelectMode();
                    a();
                    this.R = true;
                    return;
                }
                return;
            default:
                return;
        }
    }
}
