package com.here.android.mpa.routing;

import com.here.android.mpa.common.Image;
import com.nokia.maps.LocalizedLabelImpl;
import com.nokia.maps.SignpostImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

@Online
public final class Signpost {
    private SignpostImpl a;

    @Online
    public static final class LocalizedLabel {
        private LocalizedLabelImpl a;

        private LocalizedLabel(LocalizedLabelImpl localizedLabelImpl) {
            this.a = localizedLabelImpl;
        }

        @Online
        public String getLanguage() {
            return this.a.getLanguage();
        }

        @Online
        public String getText() {
            return this.a.getText();
        }

        @Online
        public String getRouteName() {
            return this.a.getRouteName();
        }

        @Online
        public String getRouteDirection() {
            return this.a.getRouteDirection();
        }

        public String toString() {
            return this.a.toString();
        }

        static {
            LocalizedLabelImpl.a(new am<LocalizedLabel, LocalizedLabelImpl>() {
                public LocalizedLabel a(LocalizedLabelImpl localizedLabelImpl) {
                    return new LocalizedLabel(localizedLabelImpl);
                }
            });
        }
    }

    public int getForegroundColor() {
        return this.a.getForegroundColor();
    }

    public int getBackgroundColor() {
        return this.a.getBackgroundColor();
    }

    public List<LocalizedLabel> getExitDirections() {
        return this.a.b();
    }

    public Image getExitIcon() {
        return this.a.a();
    }

    public String getExitNumber() {
        return this.a.getExitNumber();
    }

    public String getExitText() {
        return this.a.getExitText();
    }

    private Signpost(SignpostImpl signpostImpl) {
        this.a = signpostImpl;
    }

    static {
        SignpostImpl.a(new k<Signpost, SignpostImpl>() {
            public SignpostImpl a(Signpost signpost) {
                return signpost.a;
            }
        }, new am<Signpost, SignpostImpl>() {
            public Signpost a(SignpostImpl signpostImpl) {
                return signpostImpl != null ? new Signpost(signpostImpl) : null;
            }
        });
    }
}
