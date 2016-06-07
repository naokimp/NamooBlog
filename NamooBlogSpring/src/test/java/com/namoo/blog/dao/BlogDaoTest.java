package com.namoo.blog.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.blog.domain.Author;
import com.namoo.blog.domain.Blog;

public class BlogDaoTest extends AbstractDbUnitTest {
	
	private static final String DATASET_XML = "/com/namoo/blog/dao/BlogDaoTest_dataset.xml";
	
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private AuthorDao authorDao;
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateBlog() {
		//
		Blog blog = new Blog();
		blog.setName("나무소리 블로그");
		blog.setOwner(new Author("hyunohkim"));
		
		int blogId = blogDao.createBlog(blog);
		
		blog = blogDao.readBlog(blogId);
		assertThat("나무소리 블로그", is(blog.getName()));
		assertThat("hyunohkim", is(blog.getOwner().getId()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadBlog() {
		//
		Blog blog = blogDao.readBlog(1);
		
		// assertion
		assertThat("블로그", is(blog.getName()));
		assertThat("hyunohkim", is(blog.getOwner().getId()));
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllBlogs() {
		//
		List<Blog> blogs = blogDao.readAllBlogs();

		// assertion
		assertThat(3, is(blogs.size()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateBlog() {
		//
		Blog blog = blogDao.readBlog(1);
		
		blog.setName("업데이트");
		
		blogDao.updateBlog(blog);
		
		blog = blogDao.readBlog(1);
		
		assertThat("업데이트", is(blog.getName()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteBlog() {
		//
		blogDao.deleteBlog(1);
		
		assertNull(blogDao.readBlog(1));
	}
	
}