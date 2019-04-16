package com.asiainfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Caching(cacheable= {
		@Cacheable(cacheNames="members", condition="#user instanceof T(com.asiainfo.entity.Member)"),
		@Cacheable(cacheNames="vips", condition="#user instanceof T(com.asiainfo.entity.Vip)")
})
public @interface MyCacheAnnotation {

}
