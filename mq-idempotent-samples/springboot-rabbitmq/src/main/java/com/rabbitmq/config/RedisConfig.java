/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rabbitmq.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : wh
 * @date : 2021/11/6 11:02
 * @description:
 */
@Configuration
public class RedisConfig {
    
    @Value("${redis.host:127.0.0.1}")
    private String redisLoginHost;
    @Value("${redis.port:6379}")
    private Integer redisLoginPort;
    @Value("${redis.password:123456}")
    private String redisLoginPassword;
    
    @Bean
    public RedissonClient redissonClient() {
        return createRedis(redisLoginHost, redisLoginPort, redisLoginPassword);
    }
    
    private RedissonClient createRedis(String redisHost, Integer redisPort, String redisPassword) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + redisHost + ":" + redisPort + "");
        singleServerConfig.setPassword(redisPassword);
        return Redisson.create(config);
    }
}
