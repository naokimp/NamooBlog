package com.namoo.blog.dao;

import java.util.List;

import com.namoo.blog.domain.Blog;

public interface BlogDao {
	//
	int createBlog(Blog blog);
	Blog readBlog(int blogId);
	List<Blog> readAllBlogs();
	void updateBlog(Blog blog);
	void deleteBlog(int blogId);
}
