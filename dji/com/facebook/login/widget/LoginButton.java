package com.facebook.login.widget;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.AccessToken;
import com.facebook.FacebookButtonBase;
import com.facebook.Profile;
import com.facebook.R;
import com.facebook.d;
import com.facebook.h;
import com.facebook.internal.ah;
import com.facebook.internal.y;
import com.facebook.login.f;
import com.facebook.login.g;
import com.facebook.o;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LoginButton extends FacebookButtonBase {
    private static final String TAG = LoginButton.class.getName();
    private d accessTokenTracker;
    private boolean confirmLogout;
    private String loginLogoutEventName = com.facebook.internal.a.f;
    private f loginManager;
    private String loginText;
    private String logoutText;
    private a properties = new a();
    private boolean toolTipChecked;
    private long toolTipDisplayTime = ToolTipPopup.a;
    private c toolTipMode;
    private ToolTipPopup toolTipPopup;
    private com.facebook.login.widget.ToolTipPopup.a toolTipStyle = com.facebook.login.widget.ToolTipPopup.a.BLUE;

    static class a {
        private com.facebook.login.a a = com.facebook.login.a.FRIENDS;
        private List<String> b = Collections.emptyList();
        private y c = null;
        private com.facebook.login.c d = com.facebook.login.c.NATIVE_WITH_FALLBACK;

        a() {
        }

        public void a(com.facebook.login.a aVar) {
            this.a = aVar;
        }

        public com.facebook.login.a a() {
            return this.a;
        }

        public void a(List<String> list) {
            if (y.PUBLISH.equals(this.c)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            this.b = list;
            this.c = y.READ;
        }

        public void b(List<String> list) {
            if (y.READ.equals(this.c)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            } else if (ah.a((Collection) list)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            } else {
                this.b = list;
                this.c = y.PUBLISH;
            }
        }

        List<String> b() {
            return this.b;
        }

        public void c() {
            this.b = null;
            this.c = null;
        }

        public void a(com.facebook.login.c cVar) {
            this.d = cVar;
        }

        public com.facebook.login.c d() {
            return this.d;
        }
    }

    private class b implements OnClickListener {
        final /* synthetic */ LoginButton a;

        private b(LoginButton loginButton) {
            this.a = loginButton;
        }

        public void onClick(View view) {
            int i;
            this.a.callExternalOnClickListener(view);
            Context context = this.a.getContext();
            AccessToken a = AccessToken.a();
            if (a == null) {
                f loginManager = this.a.getLoginManager();
                loginManager.a(this.a.getDefaultAudience());
                loginManager.a(this.a.getLoginBehavior());
                if (y.PUBLISH.equals(this.a.properties.c)) {
                    if (this.a.getFragment() != null) {
                        loginManager.b(this.a.getFragment(), this.a.properties.b);
                    } else if (this.a.getNativeFragment() != null) {
                        loginManager.b(this.a.getNativeFragment(), this.a.properties.b);
                    } else {
                        loginManager.b(this.a.getActivity(), this.a.properties.b);
                    }
                } else if (this.a.getFragment() != null) {
                    loginManager.a(this.a.getFragment(), this.a.properties.b);
                } else if (this.a.getNativeFragment() != null) {
                    loginManager.a(this.a.getNativeFragment(), this.a.properties.b);
                } else {
                    loginManager.a(this.a.getActivity(), this.a.properties.b);
                }
            } else if (this.a.confirmLogout) {
                CharSequence string;
                CharSequence string2 = this.a.getResources().getString(R.string.com_facebook_loginview_log_out_action);
                CharSequence string3 = this.a.getResources().getString(R.string.com_facebook_loginview_cancel_action);
                Profile a2 = Profile.a();
                if (a2 == null || a2.g() == null) {
                    string = this.a.getResources().getString(R.string.com_facebook_loginview_logged_in_using_facebook);
                } else {
                    string = String.format(this.a.getResources().getString(R.string.com_facebook_loginview_logged_in_as), new Object[]{a2.g()});
                }
                Builder builder = new Builder(context);
                builder.setMessage(string).setCancelable(true).setPositiveButton(string2, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a.getLoginManager().c();
                    }
                }).setNegativeButton(string3, null);
                builder.create().show();
            } else {
                this.a.getLoginManager().c();
            }
            com.facebook.a.b c = com.facebook.a.b.c(this.a.getContext());
            Bundle bundle = new Bundle();
            String str = "logging_in";
            if (a != null) {
                i = 0;
            } else {
                i = 1;
            }
            bundle.putInt(str, i);
            c.a(this.a.loginLogoutEventName, null, bundle);
        }
    }

    public enum c {
        AUTOMATIC(com.facebook.internal.a.ab, 0),
        DISPLAY_ALWAYS("display_always", 1),
        NEVER_DISPLAY("never_display", 2);
        
        public static c d;
        private String e;
        private int f;

        static {
            d = AUTOMATIC;
        }

        public static c fromInt(int i) {
            for (c cVar : values()) {
                if (cVar.a() == i) {
                    return cVar;
                }
            }
            return null;
        }

        private c(String str, int i) {
            this.e = str;
            this.f = i;
        }

        public String toString() {
            return this.e;
        }

        public int a() {
            return this.f;
        }
    }

    public LoginButton(Context context) {
        super(context, null, 0, 0, com.facebook.internal.a.am, com.facebook.internal.a.as);
    }

    public LoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0, com.facebook.internal.a.am, com.facebook.internal.a.as);
    }

    public LoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0, com.facebook.internal.a.am, com.facebook.internal.a.as);
    }

    public void setDefaultAudience(com.facebook.login.a aVar) {
        this.properties.a(aVar);
    }

    public com.facebook.login.a getDefaultAudience() {
        return this.properties.a();
    }

    public void setReadPermissions(List<String> list) {
        this.properties.a((List) list);
    }

    public void setReadPermissions(String... strArr) {
        this.properties.a(Arrays.asList(strArr));
    }

    public void setPublishPermissions(List<String> list) {
        this.properties.b((List) list);
    }

    public void setPublishPermissions(String... strArr) {
        this.properties.b(Arrays.asList(strArr));
    }

    public void clearPermissions() {
        this.properties.c();
    }

    public void setLoginBehavior(com.facebook.login.c cVar) {
        this.properties.a(cVar);
    }

    public com.facebook.login.c getLoginBehavior() {
        return this.properties.d();
    }

    public void setToolTipStyle(com.facebook.login.widget.ToolTipPopup.a aVar) {
        this.toolTipStyle = aVar;
    }

    public void setToolTipMode(c cVar) {
        this.toolTipMode = cVar;
    }

    public c getToolTipMode() {
        return this.toolTipMode;
    }

    public void setToolTipDisplayTime(long j) {
        this.toolTipDisplayTime = j;
    }

    public long getToolTipDisplayTime() {
        return this.toolTipDisplayTime;
    }

    public void dismissToolTip() {
        if (this.toolTipPopup != null) {
            this.toolTipPopup.b();
            this.toolTipPopup = null;
        }
    }

    public void registerCallback(com.facebook.f fVar, h<g> hVar) {
        getLoginManager().a(fVar, (h) hVar);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.accessTokenTracker != null && !this.accessTokenTracker.c()) {
            this.accessTokenTracker.a();
            setButtonText();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.toolTipChecked && !isInEditMode()) {
            this.toolTipChecked = true;
            checkToolTipSettings();
        }
    }

    private void showToolTipPerSettings(com.facebook.internal.ah.b bVar) {
        if (bVar != null && bVar.c() && getVisibility() == 0) {
            displayToolTip(bVar.b());
        }
    }

    private void displayToolTip(String str) {
        this.toolTipPopup = new ToolTipPopup(str, this);
        this.toolTipPopup.a(this.toolTipStyle);
        this.toolTipPopup.a(this.toolTipDisplayTime);
        this.toolTipPopup.a();
    }

    private void checkToolTipSettings() {
        switch (this.toolTipMode) {
            case AUTOMATIC:
                final String a = ah.a(getContext());
                o.f().execute(new Runnable(this) {
                    final /* synthetic */ LoginButton b;

                    public void run() {
                        final com.facebook.internal.ah.b a = ah.a(a, false);
                        this.b.getActivity().runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.b.showToolTipPerSettings(a);
                            }
                        });
                    }
                });
                return;
            case DISPLAY_ALWAYS:
                displayToolTip(getResources().getString(R.string.com_facebook_tooltip_default));
                return;
            default:
                return;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setButtonText();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.accessTokenTracker != null) {
            this.accessTokenTracker.b();
        }
        dismissToolTip();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            dismissToolTip();
        }
    }

    List<String> getPermissions() {
        return this.properties.b();
    }

    void setProperties(a aVar) {
        this.properties = aVar;
    }

    protected void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        setInternalOnClickListener(new b());
        parseLoginButtonAttributes(context, attributeSet, i, i2);
        if (isInEditMode()) {
            setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
            this.loginText = "Log in with Facebook";
        } else {
            this.accessTokenTracker = new d(this) {
                final /* synthetic */ LoginButton a;

                {
                    this.a = r1;
                }

                protected void a(AccessToken accessToken, AccessToken accessToken2) {
                    this.a.setButtonText();
                }
            };
        }
        setButtonText();
    }

    protected int getDefaultStyleResource() {
        return R.style.com_facebook_loginview_default_style;
    }

    private void parseLoginButtonAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        this.toolTipMode = c.d;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.com_facebook_login_view, i, i2);
        try {
            this.confirmLogout = obtainStyledAttributes.getBoolean(R.styleable.com_facebook_login_view_com_facebook_confirm_logout, true);
            this.loginText = obtainStyledAttributes.getString(R.styleable.com_facebook_login_view_com_facebook_login_text);
            this.logoutText = obtainStyledAttributes.getString(R.styleable.com_facebook_login_view_com_facebook_logout_text);
            this.toolTipMode = c.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_login_view_com_facebook_tooltip_mode, c.d.a()));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    protected void onMeasure(int i, int i2) {
        int measureButtonWidth;
        FontMetrics fontMetrics = getPaint().getFontMetrics();
        int compoundPaddingBottom = getCompoundPaddingBottom() + (((int) Math.ceil((double) (Math.abs(fontMetrics.bottom) + Math.abs(fontMetrics.top)))) + getCompoundPaddingTop());
        Resources resources = getResources();
        String str = this.loginText;
        if (str == null) {
            str = resources.getString(R.string.com_facebook_loginview_log_in_button_long);
            measureButtonWidth = measureButtonWidth(str);
            if (resolveSize(measureButtonWidth, i) < measureButtonWidth) {
                str = resources.getString(R.string.com_facebook_loginview_log_in_button);
            }
        }
        measureButtonWidth = measureButtonWidth(str);
        str = this.logoutText;
        if (str == null) {
            str = resources.getString(R.string.com_facebook_loginview_log_out_button);
        }
        setMeasuredDimension(resolveSize(Math.max(measureButtonWidth, measureButtonWidth(str)), i), compoundPaddingBottom);
    }

    private int measureButtonWidth(String str) {
        return (measureTextWidth(str) + (getCompoundPaddingLeft() + getCompoundDrawablePadding())) + getCompoundPaddingRight();
    }

    private void setButtonText() {
        Resources resources = getResources();
        CharSequence charSequence;
        if (!isInEditMode() && AccessToken.a() != null) {
            if (this.logoutText != null) {
                charSequence = this.logoutText;
            } else {
                charSequence = resources.getString(R.string.com_facebook_loginview_log_out_button);
            }
            setText(charSequence);
        } else if (this.loginText != null) {
            setText(this.loginText);
        } else {
            charSequence = resources.getString(R.string.com_facebook_loginview_log_in_button_long);
            int width = getWidth();
            if (width != 0 && measureButtonWidth(charSequence) > width) {
                charSequence = resources.getString(R.string.com_facebook_loginview_log_in_button);
            }
            setText(charSequence);
        }
    }

    protected int getDefaultRequestCode() {
        return com.facebook.internal.f.b.Login.a();
    }

    f getLoginManager() {
        if (this.loginManager == null) {
            this.loginManager = f.getInstance();
        }
        return this.loginManager;
    }

    void setLoginManager(f fVar) {
        this.loginManager = fVar;
    }
}
