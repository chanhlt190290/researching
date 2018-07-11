package com.chanhlt.memcached.redis;

import redis.clients.jedis.Jedis;

public class Publisher {

    private final Jedis publisherJedis;
    private final String channel;

    public Publisher(Jedis publisherJedis, String channel) {
        this.publisherJedis = publisherJedis;
        this.channel = channel;
    }

    public void publish(String message) {
        publisherJedis.publish(channel, message);
    }
}
