package com.namoo.blog.service.facade;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.blog.dao.AbstractDbUnitTest;
import com.namoo.blog.domain.Author;
import com.namoo.blog.domain.Blog;
import com.namoo.blog.domain.Post;
import com.namoo.blog.shared.page.Page;
import com.namoo.blog.shared.page.PageInfo;

public class BlogServiceTest extends AbstractDbUnitTest{

	private static final String DATASET_XML = "/com/namoo/blog/service/facade/BlogServiceTest_dataset.xml";
	
	@Autowired
	private BlogService blogService;

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testGetBlog() {
		//
		Blog blog = blogService.getBlog(1);
		//
		assertThat("블로그", is(blog.getName()));
		assertThat(2, is(blog.getPosts().size()));
		
	}
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testGetPosts() {
		//
		int blogId = 1;
		PageInfo pageInfo = new PageInfo(1, 5);
		Page<Post> page = blogService.getPosts(blogId, pageInfo);
		
		List<Post> posts = page.getResults();
		
		// assertion
		assertThat(5, is(posts.size()));
		assertThat(14, is(page.getTotalCount()));
		assertTrue(page.isNextPage());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testGetPost() {
		//
		Post post = blogService.getPost(1);
		assertThat("포스트1", is(post.getSubject()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testRegisterBlog() {
		//
		Blog blog = new Blog();
		blog.setName("테스트");
		blog.setOwner(new Author("hyunohkim"));
		int blogId = 0;
		try {
			blogId = blogService.registerBlog(blog);
			
		} catch(Exception e) {
			
		}
		//
		blog = blogService.getBlog(blogId);
		assertNotNull(blog);
	}

}
