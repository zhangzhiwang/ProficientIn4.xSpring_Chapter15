package com.asiainfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.interfaces.IForumDao;
import com.asiainfo.entity.Forum;

@Service
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
	
	@Cacheable(cacheNames="users", key="#forum.forumId", condition="#forum.forumId < 10")// condition作为条件过滤，在调用方法前先进性条件判断，符合条件的进行缓存
	public Forum getForumById3(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	@Cacheable(cacheNames="users", key="#forum.forumId", unless="#forum.forumId > 10")// unless作为排除条件，在调用方法前先进性条件判断，符合条件的进行排除不进行缓存
	public Forum getForumById4(int forumId,Forum forum) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
}
