package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.k;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static Bundle a(UUID uuid, ShareContent shareContent, boolean z) {
        ai.a((Object) shareContent, "shareContent");
        ai.a((Object) uuid, "callId");
        if (shareContent instanceof ShareLinkContent) {
            return a((ShareLinkContent) shareContent, z);
        }
        if (shareContent instanceof SharePhotoContent) {
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            return a(sharePhotoContent, q.a(sharePhotoContent, uuid), z);
        } else if (shareContent instanceof ShareVideoContent) {
            return a((ShareVideoContent) shareContent, z);
        } else {
            if (!(shareContent instanceof ShareOpenGraphContent)) {
                return null;
            }
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                return a(shareOpenGraphContent, q.a(uuid, shareOpenGraphContent), z);
            } catch (JSONException e) {
                throw new k("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + e.getMessage());
            }
        }
    }

    private static Bundle a(ShareLinkContent shareLinkContent, boolean z) {
        Bundle a = a((ShareContent) shareLinkContent, z);
        ah.a(a, n.x, shareLinkContent.b());
        ah.a(a, n.y, shareLinkContent.a());
        ah.a(a, n.w, shareLinkContent.c());
        return a;
    }

    private static Bundle a(SharePhotoContent sharePhotoContent, List<String> list, boolean z) {
        Bundle a = a((ShareContent) sharePhotoContent, z);
        a.putStringArrayList(n.B, new ArrayList(list));
        return a;
    }

    private static Bundle a(ShareVideoContent shareVideoContent, boolean z) {
        return null;
    }

    private static Bundle a(ShareOpenGraphContent shareOpenGraphContent, JSONObject jSONObject, boolean z) {
        Bundle a = a((ShareContent) shareOpenGraphContent, z);
        ah.a(a, n.O, shareOpenGraphContent.b());
        ah.a(a, n.N, shareOpenGraphContent.a().a());
        ah.a(a, n.M, jSONObject.toString());
        return a;
    }

    private static Bundle a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        ah.a(bundle, n.v, shareContent.h());
        ah.a(bundle, n.t, shareContent.j());
        ah.a(bundle, n.z, shareContent.k());
        bundle.putBoolean(n.A, z);
        Collection i = shareContent.i();
        if (!ah.a(i)) {
            bundle.putStringArrayList(n.u, new ArrayList(i));
        }
        return bundle;
    }
}
