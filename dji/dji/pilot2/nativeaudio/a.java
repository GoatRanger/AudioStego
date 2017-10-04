package dji.pilot2.nativeaudio;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import dji.pilot.R;
import dji.pilot2.nativeaudio.a.b;
import dji.pilot2.nativeaudio.model.c;
import java.util.ArrayList;

public class a extends Fragment {
    private ListView a;
    private b b;
    private ArrayList<c> c = new ArrayList();
    private int[] d = new int[]{R.drawable.v2_audio_playlist_icon, R.drawable.v2_audio_zhuanji_icon, R.drawable.v2_audio_player_icon, R.drawable.v2_audio_songs_icon};
    private int[] e = new int[]{R.string.v2_audio_playlist, R.string.v2_audio_zhuanji, R.string.v2_audio_player, R.string.v2_audio_songs};
    private int f = 4;
    private OnItemClickListener g = new OnItemClickListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i == 3) {
                Intent intent = new Intent(this.a.getActivity(), DJIAudioPlayActivity.class);
                intent.putExtra(DJIAudioPlayActivity.L, true);
                this.a.startActivityForResult(intent, 100);
                return;
            }
            intent = new Intent(this.a.getActivity(), DJINativeSecondListActivity.class);
            intent.putExtra(DJINativeSecondListActivity.d, this.a.getResources().getString(this.a.e[i]));
            intent.putExtra(DJINativeSecondListActivity.t, i);
            this.a.startActivityForResult(intent, 100);
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_native_audio_fragment, null);
        a();
        this.a = (ListView) inflate.findViewById(R.id.cud);
        this.a.setSelector(R.drawable.v2_audioitem_selector);
        this.b = new b(getActivity());
        this.b.a(dji.pilot2.nativeaudio.a.b.a.AdapterNormal);
        this.b.b(this.c);
        this.a.setOnItemClickListener(this.g);
        this.a.setAdapter(this.b);
        this.b.notifyDataSetChanged();
        return inflate;
    }

    private void a() {
        for (int i = 0; i < this.f; i++) {
            c cVar = new c();
            cVar.a = this.d[i];
            cVar.b = getResources().getString(this.e[i]);
            this.c.add(cVar);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onStop() {
        super.onStop();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Log.i("zhang", "fragment:" + i2);
        if (i2 == -1) {
            getActivity().setResult(-1, intent);
            getActivity().finish();
        }
    }
}
