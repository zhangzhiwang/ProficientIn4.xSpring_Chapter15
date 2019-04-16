package com.asiainfo.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.asiainfo.MyCacheAnnotation;
import com.asiainfo.dao.interfaces.IForumDao;
import com.asiainfo.entity.Forum;
import com.asiainfo.entity.User;
import com.asiainfo.entity.Vip;

@Service
@CacheConfig(cacheNames="users")
public class ForumServiceImpl {
	@Autowired
	private IForumDao forumDao;
	
	@Cacheable(cacheNames="users")// 如果没有指定key属性，则spring会自动生成key：如果有一个入參，则该入參作为缓存的key，如果有多个入參则将这多个入參合并为一起生成一个key，如果没有入參则用某个默认值作为key，详见课本p499
	public Forum getForumById(int forumId) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	public int addForum(Forum forum) {
		return forumDao.addForum(forum);
	}
	
	@Cacheable(cacheNames="users", key="#forum.forumId")// 可以用key属性指定缓存的的键，本例中用入參的forum对象的forumId属性作为缓存的key。注意：“#”后面的内容要和形參名一致
	public Forum getForumById2(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@Cacheable(cacheNames="users", key="#forum.forumId", condition="#forum.forumId < 10")// condition作为条件过滤，在调用方法前先进性条件判断，符合条件的进行缓存，所以condition的值不应该依赖方法的返回值
	public Forum getForumById3(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@Cacheable(cacheNames="users", unless="#forum.forumId > 10")// unless作为排除条件，在调用方法前先进性条件判断，符合条件的进行排除不进行缓存
	public Forum getForumById4(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@CachePut(cacheNames="users", key="#forum.forumId", unless="#forum.forumId > 10")// @CachePut和@Cacheable效果几乎一样，区别就是@CachePut每次都会强制执行方法并用方法的返回值更新缓存
	public Forum getForumById5(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@CacheEvict(cacheNames="users")// @CacheEvict用于更新和删除缓存，与@Cacheable不同的是在默认情况下@CacheEvict在方法执行后执行
	public Forum getForumById6(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@CacheEvict(cacheNames="users", allEntries=true, beforeInvocation=true)// allEntries清除所有缓存，默认为false；beforeInvocation是否在方法执行之前执行，默认为false
	public Forum getForumById7(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@Caching(cacheable= {
			@Cacheable(cacheNames="members", condition="#user instanceof T(com.asiainfo.entity.Member)"),
			@Cacheable(cacheNames="vips", condition="#user instanceof T(com.asiainfo.entity.Vip)")
	})	// 缓存注解组
	public User getUeser(User user) {
		return new Vip();
	}
	
	@MyCacheAnnotation
	public User getUeser2(User user) {
		return new Vip();
	}
	
//	@Cacheable(cacheNames="users")
	public Forum getForumById8(int forumId,Forum forum) {// 使用类级别的注解
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@PostConstruct
	@Cacheable(cacheNames="users")
	public List<Forum> initForumCache() {
		return forumDao.getAllForum();
	}
}
