package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzg;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    private T zzapn;
    private Bundle zzapo;
    private LinkedList<zza> zzapp;
    private final zzf<T> zzapq = new zzf<T>(this) {
        final /* synthetic */ zza zzapr;

        {
            this.zzapr = r1;
        }

        public void zza(T t) {
            this.zzapr.zzapn = t;
            Iterator it = this.zzapr.zzapp.iterator();
            while (it.hasNext()) {
                ((zza) it.next()).zzb(this.zzapr.zzapn);
            }
            this.zzapr.zzapp.clear();
            this.zzapr.zzapo = null;
        }
    };

    private interface zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    private void zza(Bundle bundle, zza com_google_android_gms_dynamic_zza_zza) {
        if (this.zzapn != null) {
            com_google_android_gms_dynamic_zza_zza.zzb(this.zzapn);
            return;
        }
        if (this.zzapp == null) {
            this.zzapp = new LinkedList();
        }
        this.zzapp.add(com_google_android_gms_dynamic_zza_zza);
        if (bundle != null) {
            if (this.zzapo == null) {
                this.zzapo = (Bundle) bundle.clone();
            } else {
                this.zzapo.putAll(bundle);
            }
        }
        zza(this.zzapq);
    }

    public static void zzb(FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        CharSequence zzc = zzg.zzc(context, isGooglePlayServicesAvailable, GooglePlayServicesUtil.zzaf(context));
        CharSequence zzh = zzg.zzh(context, isGooglePlayServicesAvailable);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzc);
        linearLayout.addView(textView);
        if (zzh != null) {
            View button = new Button(context);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zzh);
            linearLayout.addView(button);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    context.startActivity(GooglePlayServicesUtil.zzbj(isGooglePlayServicesAvailable));
                }
            });
        }
    }

    private void zzer(int i) {
        while (!this.zzapp.isEmpty() && ((zza) this.zzapp.getLast()).getState() >= i) {
            this.zzapp.removeLast();
        }
    }

    public void onCreate(final Bundle bundle) {
        zza(bundle, new zza(this) {
            final /* synthetic */ zza zzapr;

            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.zzapr.zzapn.onCreate(bundle);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        zza(bundle, new zza(this) {
            final /* synthetic */ zza zzapr;

            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(this.zzapr.zzapn.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }
        });
        if (this.zzapn == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.zzapn != null) {
            this.zzapn.onDestroy();
        } else {
            zzer(1);
        }
    }

    public void onDestroyView() {
        if (this.zzapn != null) {
            this.zzapn.onDestroyView();
        } else {
            zzer(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        zza(bundle2, new zza(this) {
            final /* synthetic */ zza zzapr;

            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.zzapr.zzapn.onInflate(activity, bundle, bundle2);
            }
        });
    }

    public void onLowMemory() {
        if (this.zzapn != null) {
            this.zzapn.onLowMemory();
        }
    }

    public void onPause() {
        if (this.zzapn != null) {
            this.zzapn.onPause();
        } else {
            zzer(5);
        }
    }

    public void onResume() {
        zza(null, new zza(this) {
            final /* synthetic */ zza zzapr;

            {
                this.zzapr = r1;
            }

            public int getState() {
                return 5;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.zzapr.zzapn.onResume();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.zzapn != null) {
            this.zzapn.onSaveInstanceState(bundle);
        } else if (this.zzapo != null) {
            bundle.putAll(this.zzapo);
        }
    }

    public void onStart() {
        zza(null, new zza(this) {
            final /* synthetic */ zza zzapr;

            {
                this.zzapr = r1;
            }

            public int getState() {
                return 4;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.zzapr.zzapn.onStart();
            }
        });
    }

    public void onStop() {
        if (this.zzapn != null) {
            this.zzapn.onStop();
        } else {
            zzer(4);
        }
    }

    protected void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    protected abstract void zza(zzf<T> com_google_android_gms_dynamic_zzf_T);

    public T zzrZ() {
        return this.zzapn;
    }
}
