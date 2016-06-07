package com.namoo.blog.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.blog.domain.Author;
import com.namoo.blog.domain.Comment;
import com.namoo.blog.domain.Post;
import com.namoo.blog.shared.page.Page;
import com.namoo.blog.shared.page.PageInfo;

public class PostDaoTest extends AbstractDbUnitTest {

	private static final String DATASET_XML = "/com/namoo/blog/dao/PostDaoTest_dataset.xml";
	
	@Autowired
	private PostDao postDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private AuthorDao authorDao;
	
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreatePost() {
		//
		Post post = new Post();
		post.setSubject("포스트2");
		post.setContents("두번째 포스트");
		
		int postId = postDao.createPost(3, post);
		
		post = postDao.readPost(postId);
		
		assertThat("포스트2", is(post.getSubject()));
		assertThat("두번째 포스트", is(post.getContents()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadPost() {
		//
		Post post = postDao.readPost(2);
		
		assertThat("포스트2", is(post.getSubject()));
		assertThat("두번째 포스트", is(post.getContents()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllPosts() {
		//
		PageInfo pageInfo = new PageInfo(3, 5);
		Page<Post> page = postDao.readAllPosts(1, pageInfo);
		
		List<Post> posts = page.getResults();
		
		assertThat(4, is(posts.size()));
		assertThat(14, is(page.getTotalCount()));
		assertFalse(page.isNextPage());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdatePost() {
		//
		Post post = postDao.readPost(4);
		
		post.setSubject("제목");
		post.setContents("업데이트");
		
		postDao.updatePost(post);
		
		post = postDao.readPost(4);
		
		assertThat("제목", is(post.getSubject()));
		assertThat("업데이트", is(post.getContents()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeletePost() {
		//
		postDao.deletePost(3);
		
		Post post = postDao.readPost(3);
		
		assertNull(post);
		
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateComment() {
		//
		Comment comment = new Comment();
		comment.setComment("네번째 코멘트");
		comment.setCommenter(new Author("haroldkim"));
		
		postDao.createComment(1, comment);
		Post post = postDao.readPost(1);
		
		assertThat(4, is(post.getComments().size()));
		assertThat("네번째 코멘트", is(post.getComments().get(3).getComment()));
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteComment() {
		//
		postDao.deleteComment(4);
		postDao.deleteComment(5);
		
		assertThat(0, is(postDao.readPost(2).getComments().size()));
	}

}
