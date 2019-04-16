package com.aisainfo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.entity.Forum;
import com.asiainfo.service.impl.ForumServiceImpl;

public class SpringMybatisTest {
	@Test
	public void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ForumServiceImpl forumServiceImpl = (ForumServiceImpl) applicationContext.getBean("forumServiceImpl");
//		Forum forum = forumServiceImpl.getForumById(1);
//		System.out.println(forum);

		Forum forum = new Forum();
		forum.setForumId(3);
		forum.setForumName("a");
		forum.setForumDesc("desc");
		
//		int result = forumServiceImpl.addForum(forum);
//		System.out.println(result);
		Forum forumRet = forumServiceImpl.getForumById4(1, forum);
		forumRet = forumServiceImpl.getForumById4(1, forum);
		System.out.println(forumRet);
	}
}
