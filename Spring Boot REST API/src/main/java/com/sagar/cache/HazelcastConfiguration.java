package com.sagar.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.UserCodeDeploymentConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
@ConditionalOnProperty(prefix = "caching", name = "provider.impl", havingValue = "hazelcast",matchIfMissing = false)
public class HazelcastConfiguration {

	/*
	 * @Bean public Config hazelCastConfig(){ Config config = new Config();
	 * config.setInstanceName("hazelcast-instance") // hazel case instance name
	 * .addMapConfig( new MapConfig() // create map .setName("configuration") //
	 * .setMaxSizeConfig(new MaxSizeConfig(200,
	 * MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)) //
	 * .setEvictionPolicy(EvictionPolicy.LRU) .setTimeToLiveSeconds(-1)); // cache
	 * will be available until it will remove manually. less then 0 means never
	 * expired. return config; }
	 */

	@Bean
	public Config hazelCastConfig() {
		final Config config = new Config();
		config.setClassLoader(Thread.currentThread().getContextClassLoader());

		final UserCodeDeploymentConfig distCLConfig = config.getUserCodeDeploymentConfig();
		distCLConfig.setEnabled(true).setClassCacheMode(UserCodeDeploymentConfig.ClassCacheMode.ETERNAL)
				.setProviderMode(UserCodeDeploymentConfig.ProviderMode.LOCAL_CLASSES_ONLY);

		return config;
	}

	@Bean
	public HazelcastInstance hazelcastInstance() {
		return Hazelcast.newHazelcastInstance(hazelCastConfig());
	}
}