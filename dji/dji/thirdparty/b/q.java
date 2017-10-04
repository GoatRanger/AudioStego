package dji.thirdparty.b;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public interface q {
    public static final q a = new q() {
        public List<InetAddress> a(String str) throws UnknownHostException {
            if (str != null) {
                return Arrays.asList(InetAddress.getAllByName(str));
            }
            throw new UnknownHostException("hostname == null");
        }
    };

    List<InetAddress> a(String str) throws UnknownHostException;
}
