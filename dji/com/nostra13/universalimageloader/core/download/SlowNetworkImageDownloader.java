package com.nostra13.universalimageloader.core.download;

import com.nostra13.universalimageloader.core.assist.FlushedInputStream;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.IOException;
import java.io.InputStream;

public class SlowNetworkImageDownloader implements ImageDownloader {
    private final ImageDownloader wrappedDownloader;

    public SlowNetworkImageDownloader(ImageDownloader imageDownloader) {
        this.wrappedDownloader = imageDownloader;
    }

    public InputStream getStream(String str, Object obj) throws IOException {
        InputStream stream = this.wrappedDownloader.getStream(str, obj);
        switch (Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                return new FlushedInputStream(stream);
            default:
                return stream;
        }
    }
}
