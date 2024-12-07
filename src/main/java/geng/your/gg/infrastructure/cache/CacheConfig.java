package geng.your.gg.infrastructure.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableCaching
@EnableScheduling
@Component
public class CacheConfig {

    private final ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();

    // 1분마다 캐시를 삭제합니다.
    @Scheduled(fixedRate = 60000)
    public void clearAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
    }
}
