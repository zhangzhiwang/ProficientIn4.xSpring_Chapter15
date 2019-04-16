package com.asiainfo.dao.interfaces;

import java.util.List;

import com.asiainfo.entity.Forum;

public interface IForumDao {
	Forum getForumById(int forumId);
	List<Forum> getAllForum();
	int addForum(Forum forum);
	int updateForumById(Forum forum);
}
