package org.identifiers.cloud.ws.resolver.configuration;

import org.identifiers.cloud.ws.resolver.daemons.models.ResolverDataSourcer;
import org.identifiers.cloud.ws.resolver.daemons.models.ResolverDataSourcerFromWs;
import org.identifiers.cloud.ws.resolver.models.ResolverDataFetcher;
import org.identifiers.cloud.ws.resolver.models.ResolverDataFetcherFromDataBackend;
import org.identifiers.cloud.ws.resolver.services.ResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * Project: resolver
 * Package: org.identifiers.org.cloud.ws.resolver.configuration
 * Timestamp: 2018-01-17 16:25
 * ---
 */
@Configuration
@EnableRedisRepositories
public class ApplicationConfig {
    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Autowired
    @Qualifier("ResolveFirstResolutionStrategy")
    private ResolutionService selectedResolutionService;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost,
                redisPort);
        return new JedisConnectionFactory(redisStandaloneConfiguration);    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    // TODO - I should probably use @Component for this
    @Bean
    public ResolverDataSourcer resolverDataSourcer() {
        return new ResolverDataSourcerFromWs();
    }

    // Resolver Data Fetcher
    @Bean
    public ResolverDataFetcher resolverDataFetcher() {
        return new ResolverDataFetcherFromDataBackend();
    }

    @Bean
    public ResolutionService resolutionService() {
        return selectedResolutionService;
    }
}
