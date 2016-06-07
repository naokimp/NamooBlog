package com.namoo.blog.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namoo.blog.dao.BlogDao;
import com.namoo.blog.dao.PostDao;
import com.namoo.blog.domain.Blog;
import com.namoo.blog.domain.Post;
import com.namoo.blog.service.facade.BlogService;
import com.namoo.blog.shared.page.Page;
import com.namoo.blog.shared.page.PageInfo;

@Service
@Transactional
public class BlogServiceLogic implements BlogService {
	//
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private PostDao postDao;
	
	@Override
	public Blog getBlog(int blogId) {
		//
		return blogDao.readBlog(blogId);
	}
	
	@Override
	public Page<Post> getPosts(int blogId, PageInfo pageInfo) {
		// 
		return postDao.readAllPosts(blogId, pageInfo);
	}

	@Override
	public Post getPost(int postId) {
		//
		return postDao.readPost(postId);
	}

	@Override
	public int registerBlog(Blog blog) {
		//
		int blogNo = blogDao.createBlog(blog);
		
		// 예외가 발생하는 경우를 만들어냄.
		String a = null;
		a.toString();
		
		return blogNo;
	}

}
