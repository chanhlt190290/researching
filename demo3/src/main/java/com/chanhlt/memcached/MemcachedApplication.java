package com.chanhlt.memcached;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.chanhlt.memcached.entiry.Employee;
import com.chanhlt.memcached.redis.Constants;
import com.chanhlt.memcached.redis.EventSubscriber;
import com.chanhlt.memcached.redis.Publisher;
import com.chanhlt.memcached.redis.Subscriber;
import com.chanhlt.memcached.repository.EmployeeRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class MemcachedApplication {

	// public static final String CHANNEL_NAME = "commonChannel";

	// private static Logger logger =
	// LoggerFactory.getLogger(MemcachedApplication.class);

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	EventSubscriber subscriber;

	public static void main(String[] args) {
		SpringApplication.run(MemcachedApplication.class, args);

	}

	private Thread getThreadByName(String threadName) {
		for (Thread t : Thread.getAllStackTraces().keySet()) {
			if (t.getName().equals(threadName))
				return t;
		}
		return null;
	}

	@Bean
	public CommandLineRunner commandLineRunner(org.springframework.context.ApplicationContext ctx) {
		return args -> {
			// String[] beanNames = ctx.getBeanDefinitionNames();
			// Arrays.sort(beanNames);
			// for (String beanName : beanNames) {
			// System.out.println(beanName);
			// }

			// Publisher publisher = new Publisher(getRedisClient(),
			// Constants.CHANNEL_NAME);

			subscriber.start();

			// Thread thread = getPublisherThread();

			// thread.start();

			// Jedis redis = getRedisClient();

			// for (int i = 1; i <= 100000; i++) {
			// Employee employee = new Employee();
			// employee.setName("Employee " + (5000 + i));
			// employee.setScore(System.currentTimeMillis() % 100);
			// employeeRepo.save(employee);
			// redis.zadd("top-100", employee.getScore(), String.valueOf(employee.getId()));
			// redis.zremrangeByRank("top-100", 0, -101);
			// }

			// List<Employee> employees = employeeRepo.findAll();
			// for(Employee employee : employees){
			// System.out.println(employee.getName());
			// redis.zadd("top-100", employee.getScore(), String.valueOf(employee.getId()));
			// // redis.zremrangeByRank("top-100", 0, -101);
			// }

		};
	}

	@Bean
	MemcachedClient getMemcachedClient() {
		MemcachedClient memcacheClient = null;
		try {
			String address = "127.0.0.1:11211";
			memcacheClient = new MemcachedClient(
					new ConnectionFactoryBuilder().setDaemon(true).setFailureMode(FailureMode.Retry).build(),
					AddrUtil.getAddresses(address));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return memcacheClient;
	}

	@Bean
	Jedis getRedisClient() {
		Jedis jedis = getJedisPool().getResource();
		return jedis;
	}

	@Bean
	JedisPool getJedisPool() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		final JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379, 0);
		return jedisPool;
	}

	@Bean
	Subscriber getSubscriber() {
		return new Subscriber();
	}

	@Bean
	Publisher getPublisher() {
		return new Publisher(getRedisClient(), Constants.CHANNEL_NAME);
	}
}
