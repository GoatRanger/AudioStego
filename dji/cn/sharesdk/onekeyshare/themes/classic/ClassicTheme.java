package cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.onekeyshare.themes.classic.land.EditPageLand;
import cn.sharesdk.onekeyshare.themes.classic.land.PlatformPageLand;
import cn.sharesdk.onekeyshare.themes.classic.port.EditPagePort;
import cn.sharesdk.onekeyshare.themes.classic.port.PlatformPagePort;

public class ClassicTheme extends OnekeyShareThemeImpl {
    protected void showPlatformPage(Context context) {
        PlatformPage platformPagePort;
        if (context.getResources().getConfiguration().orientation == 1) {
            platformPagePort = new PlatformPagePort(this);
        } else {
            platformPagePort = new PlatformPageLand(this);
        }
        platformPagePort.show(context, null);
    }

    protected void showEditPage(Context context, Platform platform, ShareParams shareParams) {
        EditPage editPagePort;
        if (context.getResources().getConfiguration().orientation == 1) {
            editPagePort = new EditPagePort(this);
        } else {
            editPagePort = new EditPageLand(this);
        }
        editPagePort.setPlatform(platform);
        editPagePort.setShareParams(shareParams);
        editPagePort.show(context, null);
    }
}
