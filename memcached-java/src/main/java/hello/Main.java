package hello;

import java.io.IOException;
import net.spy.memcached.*;

public class Main {

    public static void main(String[] args) {
        try {
            MemcachedClient mc
                    = new MemcachedClient(
                            AddrUtil.getAddresses("localhost:11211"));
            mc.set("foo", 0, "bar");
            String value = (String) mc.get("foo");
            System.out.println(value);
        } catch (IOException e) {
            // handle exception
        }
    }
}
