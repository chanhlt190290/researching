package com.chanhlt.memcached.redis;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;


public class Publisher {

    // private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final Jedis publisherJedis;

    private final String channel;

    public Publisher(Jedis publisherJedis, String channel) {
        this.publisherJedis = publisherJedis;
        this.channel = channel;
    }

    public void publish(String message){
        publisherJedis.publish(channel, message);
    }
}