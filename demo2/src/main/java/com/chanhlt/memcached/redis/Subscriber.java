
package com.chanhlt.memcached.redis;

import java.io.IOException;

import com.chanhlt.memcached.socket.MyMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {

	private static Logger logger = LoggerFactory.getLogger(Subscriber.class);

	@Override
	public void onMessage(String channel, String message) {
		long id = Thread.currentThread().getId();
		logger.info("Thread {}: Message received. Channel: {}, Msg: {}", id, channel, message);

		MyMessageHandler myMessageHandler=new MyMessageHandler();

		System.out.println("##################################### BROADCAST "+ myMessageHandler.getClients().size() +" ######################################");
		for (WebSocketSession webSocketSession : myMessageHandler.getClients()) {
			try {
				webSocketSession.sendMessage(new TextMessage(String.format("Channel: %s, Message: %s", channel, message)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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