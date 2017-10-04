package dji.pilot2.nativeaudio;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.nativeaudio.a.c;
import dji.pilot2.nativeaudio.model.NetworkAudioListModel;
import dji.pilot2.nativeaudio.model.NetworkAudioListModel.AccountModel;
import dji.pilot2.nativeaudio.model.NetworkAudioListModel.MultiLangugeNameModel;
import dji.pilot2.nativeaudio.model.NetworkAudioListModel.NetworkAudioTemplateModel;
import dji.pilot2.nativeaudio.model.TemplateDownloadEvent;
import dji.pilot2.utils.k;
import dji.thirdparty.afinal.f.a;

public class b extends Fragment implements OnClickListener {
    private GridView a;
    private View b;
    private ImageView c;
    private View d;
    private NetworkAudioListModel e;
    private c f;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_fragment_network_audio, null);
        this.a = (GridView) inflate.findViewById(R.id.cod);
        this.b = inflate.findViewById(R.id.d5a);
        this.b.setBackgroundColor(getActivity().getResources().getColor(R.color.j5));
        this.c = (ImageView) inflate.findViewById(R.id.d5c);
        ((AnimationDrawable) this.c.getBackground()).start();
        this.d = inflate.findViewById(R.id.d5b);
        this.c.setVisibility(0);
        this.d.setOnClickListener(this);
        this.f = new c(getActivity());
        this.a.setAdapter(null);
        this.a.setAdapter(this.f);
        this.a.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(this.a.getActivity(), NetworkAudioPreviewActivity.class);
                NetworkAudioTemplateModel networkAudioTemplateModel = (NetworkAudioTemplateModel) adapterView.getItemAtPosition(i);
                intent.putExtra(NetworkAudioPreviewActivity.b, networkAudioTemplateModel.title);
                intent.putExtra(NetworkAudioPreviewActivity.c, networkAudioTemplateModel.subtitle);
                intent.putExtra(NetworkAudioPreviewActivity.d, networkAudioTemplateModel.desc);
                intent.putExtra(NetworkAudioPreviewActivity.t, networkAudioTemplateModel.duration);
                intent.putExtra(NetworkAudioPreviewActivity.u, networkAudioTemplateModel.authorModel.name);
                intent.putExtra(NetworkAudioPreviewActivity.v, networkAudioTemplateModel.authorModel.avatar);
                intent.putExtra(NetworkAudioPreviewActivity.w, networkAudioTemplateModel.video);
                intent.putExtra(NetworkAudioPreviewActivity.x, networkAudioTemplateModel.zip_Android);
                intent.putExtra(NetworkAudioPreviewActivity.a, networkAudioTemplateModel.id);
                this.a.startActivity(intent);
            }
        });
        a();
        dji.thirdparty.a.c.a().a(this);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        dji.thirdparty.a.c.a().d(this);
    }

    private void a() {
        this.c.setVisibility(0);
        if (this.e == null || this.e.result != 0) {
            com.dji.frame.c.c.b(getActivity()).a(k.w(), new a<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    this.a.c.setVisibility(4);
                    if (str != null) {
                        DJILogHelper.getInstance().LOGI("Lyric", "result: " + str);
                        this.a.e = (NetworkAudioListModel) h.b(str, NetworkAudioListModel.class);
                        if (this.a.e == null || this.a.e.result != 0) {
                            DJILogHelper.getInstance().LOGI("Lyric", "model null");
                            if (this.a.e == null || this.a.e.result != 0 || this.a.e.templates == null) {
                                this.a.b.setVisibility(0);
                                return;
                            }
                            return;
                        }
                        DJILogHelper.getInstance().LOGI("Lyric", "model not null");
                        for (int i = 0; i < this.a.e.templates.size(); i++) {
                            NetworkAudioTemplateModel networkAudioTemplateModel = (NetworkAudioTemplateModel) this.a.e.templates.get(i);
                            networkAudioTemplateModel.titleModel = (MultiLangugeNameModel) h.b(networkAudioTemplateModel.title, MultiLangugeNameModel.class);
                            networkAudioTemplateModel.subtitleModel = (MultiLangugeNameModel) h.b(networkAudioTemplateModel.subtitle, MultiLangugeNameModel.class);
                            networkAudioTemplateModel.descModel = (MultiLangugeNameModel) h.b(networkAudioTemplateModel.desc, MultiLangugeNameModel.class);
                            networkAudioTemplateModel.authorModel = (AccountModel) h.b(networkAudioTemplateModel.author, AccountModel.class);
                        }
                        this.a.f.a(this.a.e.templates);
                        this.a.f.notifyDataSetChanged();
                        this.a.b.setVisibility(4);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    this.a.c.setVisibility(4);
                    if (this.a.e == null || this.a.e.result != 0 || this.a.e.templates == null) {
                        this.a.b.setVisibility(0);
                    }
                }
            });
        }
    }

    public void onEventMainThread(TemplateDownloadEvent templateDownloadEvent) {
        DJILogHelper.getInstance().LOGI("Lyric", "fragment get download event");
        if (templateDownloadEvent.isDownloadSuccess) {
            this.f.a();
            this.f.notifyDataSetChanged();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d5b:
                a();
                return;
            default:
                return;
        }
    }
}
