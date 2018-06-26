package hello;

//import net.spy.memcached.*;
import com.lambdaworks.redis.*;

public class Main {

    public static void main(String[] args) {

        RedisClient redisClient = new RedisClient(
                RedisURI.create("redis://localhost:6379"));
        try (RedisConnection<String, String> connection = redisClient.connect()) {
            String ping = connection.ping();
            System.out.println("ping ==> " + ping);
            System.out.println("Connected to Redis");
        }
        redisClient.shutdown();
    }
}
