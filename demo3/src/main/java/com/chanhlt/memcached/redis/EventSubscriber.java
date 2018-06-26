package com.chanhlt.memcached.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class EventSubscriber implements DisposableBean, Runnable {

    private static Thread thread;
    private boolean isRunning = true;

    private static Logger logger = LoggerFactory.getLogger(EventSubscriber.class);

    @Autowired
    JedisPool jedisPool;

    @Autowired
    Subscriber subscriber;

    private EventSubscriber() {
        
        Thread t = getThreadByName("Subscriber");
        System.out.println("###################################################THREAD "+t);
        if (t != null) {
            thread = t;
        } else {
            thread = new Thread(this);
            thread.setName("Subscriber");
        }

    }

    private Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName))
                return t;
        }
        return null;
    }

    @Override
    public void run() {
        if (isRunning) {
            try {
                long id = Thread.currentThread().getId();
                logger.info("{}: Subscribing to \"commonChannel\". This thread will be blocked.", id);
                Jedis subscriberJedis = jedisPool.getResource();
                subscriberJedis.subscribe(subscriber, Constants.CHANNEL_NAME);
                logger.info("Subscription ended.");
            } catch (Exception e) {
                logger.error("Subscribing failed.", e);
            }
        }
    }

    @Override
    public void destroy() {
        isRunning = false;
    }

    public void start() {
        if (!thread.isAlive()) {
            thread.start();
        }
        isRunning = true;
    }

}