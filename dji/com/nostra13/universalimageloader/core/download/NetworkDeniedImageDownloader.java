package com.nostra13.universalimageloader.core.download;

import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.IOException;
import java.io.InputStream;

public class NetworkDeniedImageDownloader implements ImageDownloader {
    private final ImageDownloader wrappedDownloader;

    public NetworkDeniedImageDownloader(ImageDownloader imageDownloader) {
        this.wrappedDownloader = imageDownloader;
    }

    public InputStream getStream(String str, Object obj) throws IOException {
        switch (Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                throw new IllegalStateException();
            default:
                return this.wrappedDownloader.getStream(str, obj);
        }
    }
}
