package redis.resolver;

import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;

import java.util.Collection;

public class RedisResolver extends AbstractCacheResolver {
    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        context.getOperation().getCacheNames();
        return null;
    }
}
