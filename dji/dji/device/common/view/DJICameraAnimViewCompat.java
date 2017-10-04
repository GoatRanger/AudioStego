package dji.device.common.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.AttributeSet;
import dji.gs.c.e;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJICameraAnimViewCompat extends DJIRelativeLayout {
    private MediaPlayer a;
    private int b;
    private int c = 200;
    private AnimatorListener d = new AnimatorListenerAdapter(this) {
        final /* synthetic */ DJICameraAnimViewCompat a;

        {
            this.a = r1;
        }

        public void onAnimationStart(Animator animator) {
            this.a.show();
        }

        public void onAnimationEnd(Animator animator) {
            this.a.animate().setDuration((long) this.a.c).alpha(0.0f).setListener(this.a.e).setStartDelay((long) this.a.b).start();
        }
    };
    private AnimatorListener e = new AnimatorListenerAdapter(this) {
        final /* synthetic */ DJICameraAnimViewCompat a;

        {
            this.a = r1;
        }

        public void onAnimationEnd(Animator animator) {
            this.a.hide();
        }
    };

    public DJICameraAnimViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void startTakePic(TYPE type, int i) {
        int i2;
        switch (type) {
            case SINGLE:
            case HDR:
            case FULLVIEW:
            case TIME:
                this.b = 200;
                i2 = R.raw.shutter_1;
                break;
            case AEB:
            case BURST:
                switch (i) {
                    case 3:
                        this.b = 500;
                        i2 = R.raw.shutter_3;
                        break;
                    case 5:
                        this.b = e.g;
                        i2 = R.raw.shutter_5;
                        break;
                    case 7:
                        this.b = 1000;
                        i2 = R.raw.shutter_7;
                        break;
                    default:
                        this.b = 500;
                        i2 = R.raw.shutter_3;
                        break;
                }
            default:
                this.b = 200;
                i2 = R.raw.shutter_1;
                break;
        }
        try {
            this.a = MediaPlayer.create(getContext(), i2);
            this.a.setOnCompletionListener(new OnCompletionListener(this) {
                final /* synthetic */ DJICameraAnimViewCompat a;

                {
                    this.a = r1;
                }

                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
            this.a.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startVideo() {
        try {
            this.a = MediaPlayer.create(getContext(), R.raw.video_voice);
            this.a.setOnCompletionListener(new OnCompletionListener(this) {
                final /* synthetic */ DJICameraAnimViewCompat a;

                {
                    this.a = r1;
                }

                public void onCompletion(MediaPlayer mediaPlayer) {
                    this.a.a.release();
                }
            });
            this.a.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopVideo() {
        try {
            this.a = MediaPlayer.create(getContext(), R.raw.end_video_record);
            this.a.setOnCompletionListener(new OnCompletionListener(this) {
                final /* synthetic */ DJICameraAnimViewCompat a;

                {
                    this.a = r1;
                }

                public void onCompletion(MediaPlayer mediaPlayer) {
                    this.a.a.release();
                }
            });
            this.a.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
