package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import java.io.File;

public class WXEmojiObject implements IMediaObject {
    public byte[] emojiData;
    public String emojiPath;

    public WXEmojiObject(byte[] bArr) {
        this.emojiData = bArr;
    }

    public WXEmojiObject(String str) {
        this.emojiPath = str;
    }

    public void setEmojiData(byte[] bArr) {
        this.emojiData = bArr;
    }

    public void setEmojiPath(String str) {
        this.emojiPath = str;
    }

    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wxemojiobject_emojiData", this.emojiData);
        bundle.putString("_wxemojiobject_emojiPath", this.emojiPath);
    }

    public void unserialize(Bundle bundle) {
        this.emojiData = bundle.getByteArray("_wxemojiobject_emojiData");
        this.emojiPath = bundle.getString("_wxemojiobject_emojiPath");
    }

    public int type() {
        return 8;
    }

    public boolean checkArgs() {
        if ((this.emojiData == null || this.emojiData.length == 0) && TextUtils.isEmpty(this.emojiPath)) {
            d.a().d("MicroMsg.SDK.WXEmojiObject", new Object[]{"checkArgs fail, both arguments is null"});
            return false;
        } else if (this.emojiData == null || this.emojiData.length <= MediaHttpUploader.DEFAULT_CHUNK_SIZE) {
            if (this.emojiPath != null) {
                File file = new File(this.emojiPath);
                if (!file.exists()) {
                    d.a().d("MicroMsg.SDK.WXEmojiObject", new Object[]{"checkArgs fail, emojiPath not found"});
                    return false;
                } else if (file.length() > 10485760) {
                    d.a().d("MicroMsg.SDK.WXEmojiObject", new Object[]{"checkArgs fail, emojiSize is too large"});
                    return false;
                }
            }
            return true;
        } else {
            d.a().d("MicroMsg.SDK.WXEmojiObject", new Object[]{"checkArgs fail, emojiData is too large"});
            return false;
        }
    }
}
