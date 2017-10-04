package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.internal.ah;
import com.facebook.k;
import com.facebook.share.model.AppGroupCreationContent;
import com.facebook.share.model.AppGroupCreationContent.a;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import java.util.Locale;
import org.json.JSONObject;

public class s {
    public static Bundle a(AppGroupCreationContent appGroupCreationContent) {
        Bundle bundle = new Bundle();
        ah.a(bundle, "name", appGroupCreationContent.a());
        ah.a(bundle, "description", appGroupCreationContent.b());
        a c = appGroupCreationContent.c();
        if (c != null) {
            ah.a(bundle, n.p, c.toString().toLowerCase(Locale.ENGLISH));
        }
        return bundle;
    }

    public static Bundle a(GameRequestContent gameRequestContent) {
        Bundle bundle = new Bundle();
        ah.a(bundle, "message", gameRequestContent.a());
        ah.a(bundle, "to", gameRequestContent.c());
        ah.a(bundle, "title", gameRequestContent.d());
        ah.a(bundle, "data", gameRequestContent.e());
        if (gameRequestContent.f() != null) {
            ah.a(bundle, "action_type", gameRequestContent.f().toString().toLowerCase(Locale.ENGLISH));
        }
        ah.a(bundle, "object_id", gameRequestContent.g());
        if (gameRequestContent.h() != null) {
            ah.a(bundle, n.g, gameRequestContent.h().toString().toLowerCase(Locale.ENGLISH));
        }
        ah.a(bundle, n.h, gameRequestContent.i());
        return bundle;
    }

    public static Bundle a(ShareLinkContent shareLinkContent) {
        Bundle bundle = new Bundle();
        ah.a(bundle, n.i, shareLinkContent.h());
        return bundle;
    }

    public static Bundle a(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle bundle = new Bundle();
        ah.a(bundle, "action_type", shareOpenGraphContent.a().a());
        try {
            JSONObject a = q.a(q.a(shareOpenGraphContent), false);
            if (a != null) {
                ah.a(bundle, n.j, a.toString());
            }
            return bundle;
        } catch (Throwable e) {
            throw new k("Unable to serialize the ShareOpenGraphContent to JSON", e);
        }
    }

    public static Bundle b(ShareLinkContent shareLinkContent) {
        Bundle bundle = new Bundle();
        ah.a(bundle, "name", shareLinkContent.b());
        ah.a(bundle, "description", shareLinkContent.a());
        ah.a(bundle, "link", ah.a(shareLinkContent.h()));
        ah.a(bundle, "picture", ah.a(shareLinkContent.c()));
        return bundle;
    }

    public static Bundle a(ShareFeedContent shareFeedContent) {
        Bundle bundle = new Bundle();
        ah.a(bundle, "to", shareFeedContent.a());
        ah.a(bundle, "link", shareFeedContent.b());
        ah.a(bundle, "picture", shareFeedContent.f());
        ah.a(bundle, "source", shareFeedContent.g());
        ah.a(bundle, "name", shareFeedContent.c());
        ah.a(bundle, n.am, shareFeedContent.d());
        ah.a(bundle, "description", shareFeedContent.e());
        return bundle;
    }
}
