
package com.chanhlt.memcached.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {

	private static Logger logger = LoggerFactory.getLogger(Subscriber.class);

	@Override
    public void onMessage(String channel, String message) {
        long id = Thread.currentThread().getId();
	    logger.info("Thread {}: Message received. Channel: {}, Msg: {}", id, channel, message);
    }

	@Override
    public void onPMessage(String pattern, String channel, String message) {

	}

	@Override
    public void onSubscribe(String channel, int subscribedChannels) {

	}

	@Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

	}

	@Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

	}

    @Override
	public void onPSubscribe(String pattern, int subscribedChannels) {

	}

}