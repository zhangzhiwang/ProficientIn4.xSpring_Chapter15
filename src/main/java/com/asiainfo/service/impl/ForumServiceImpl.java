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
	
	@Cacheable(cacheNames="users")
	public Forum getForumById(int forumId) {
		System.out.println("从数据库读取");
		return forumDao.getForumById(forumId);
	}
	
	public int addForum(Forum forum) {
		return forumDao.addForum(forum);
	}
}
