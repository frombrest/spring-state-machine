/*
 * Copyright 2019 MobileIron, Inc.
 * All rights reserved.
 */

package io.state.machine.statemachine.persister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.statemachine.data.redis.RedisPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.redis.RedisStateMachineRepository;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import io.state.machine.statemachine.events.SMEvents;
import io.state.machine.statemachine.states.SMState;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO: Document this class.
 */

@Slf4j
@Configuration
public class SMpersister {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return jedisConnectionFactory();
    }

    private JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        String host = "localhost";
        int port = 6379;
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        log.info("Connecting to Redis on host {} and port {}", host, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public StateMachineRuntimePersister<SMState, SMEvents, String> stateMachineRuntimePersister(
            RedisStateMachineRepository redisStateMachineRepository) {
        return new RedisPersistingStateMachineInterceptor<>(redisStateMachineRepository);
    }

}
