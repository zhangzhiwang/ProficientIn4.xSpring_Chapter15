package com.asiainfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.cache.MyCache;
import com.asiainfo.dao.interfaces.IForumDao;
import com.asiainfo.entity.Forum;

@Service
@Transactional
public class ForumServiceImpl {
	@Autowired
	private MyCache<Forum> myCache;
	@Autowired
	private IForumDao forumDao;

	public int save(Forum forum) {
		return forumDao.addForum(forum);
	}

	public Forum getForumById(int forumId) {
		Forum forum = myCache.getValue(forumId);
		if (forum == null) {
			forum = forumDao.getForumById(forumId);
			myCache.addOrUpdateCache(forumId, forum);
		}

		return forum;
	}
}
