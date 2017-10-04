package dji.thirdparty.afinal;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

class c$b extends HttpEntityWrapper {
    public c$b(HttpEntity httpEntity) {
        super(httpEntity);
    }

    public InputStream getContent() throws IOException {
        return new GZIPInputStream(this.wrappedEntity.getContent());
    }

    public long getContentLength() {
        return -1;
    }
}
