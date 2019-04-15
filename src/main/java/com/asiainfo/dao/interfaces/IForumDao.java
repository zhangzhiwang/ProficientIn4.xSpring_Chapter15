package com.asiainfo.dao.interfaces;

import com.asiainfo.entity.Forum;

public interface IForumDao {
	Forum getForumById(int forumId);
	int addForum(Forum forum);
	int updateForumById(Forum forum);
}
