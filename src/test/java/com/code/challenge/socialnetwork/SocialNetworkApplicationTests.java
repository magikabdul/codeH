package com.code.challenge.socialnetwork;

import com.code.challenge.socialnetwork.domain.Post;
import com.code.challenge.socialnetwork.domain.User;
import com.code.challenge.socialnetwork.service.SocialNetworkBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocialNetworkApplicationTests {
	@Autowired
	private SocialNetworkBoard socialNetworkBoard;

	@Test
	public void testAddUser() {
		//Given
		User user = new User("John", "Doe", "nn");

		//When
		socialNetworkBoard.addUser(user);
		int usersQuantity = socialNetworkBoard.getListOfRegisteredUsers().size();
		long userId = socialNetworkBoard.getListOfRegisteredUsers().stream().findFirst().orElse(null).getId();
		String userFirstName = socialNetworkBoard.getListOfRegisteredUsers().stream().findFirst().orElse(null).getFirstName();
		String userLastName = socialNetworkBoard.getListOfRegisteredUsers().stream().findFirst().orElse(null).getLastName();
		String userNick = socialNetworkBoard.getListOfRegisteredUsers().stream().findFirst().orElse(null).getNick();

		//Then
		Assert.assertEquals(1, usersQuantity);
		Assert.assertEquals(1, userId);
		Assert.assertEquals("John", userFirstName);
		Assert.assertEquals("Doe", userLastName);
		Assert.assertEquals("nn", userNick);

		//CleanUp
		socialNetworkBoard.deleteAllUsers();
		socialNetworkBoard.deleteAllPosts();
	}

	@Test
	public void testAddTwoDifferentUsers() {
		//Given
		User user1 = new User("John", "Doe", "nn");
		User user2 = new User("Joanna", "White", "joan");

		//When
		socialNetworkBoard.addUser(user1);
		socialNetworkBoard.addUser(user2);

		long user1Id = socialNetworkBoard.getListOfRegisteredUsers().stream().filter(u -> u.equals(user1)).findFirst().orElse(null).getId();
		long user2Id = socialNetworkBoard.getListOfRegisteredUsers().stream().filter(u -> u.equals(user2)).findFirst().orElse(null).getId();

		//Then
		Assert.assertEquals(1, user1Id);
		Assert.assertEquals(2, user2Id);

		//CleanUp
		socialNetworkBoard.deleteAllUsers();
		socialNetworkBoard.deleteAllPosts();
	}

	@Test
	public void testAddTwiceTheSameUser() {
		//Given
		User user1 = new User("John", "Doe", "nn");

		//When
		socialNetworkBoard.addUser(user1);
		socialNetworkBoard.addUser(user1);

		int usersQuantity = socialNetworkBoard.getListOfRegisteredUsers().size();

		//Then
		Assert.assertEquals(1, usersQuantity);

		//CleanUp
		socialNetworkBoard.deleteAllUsers();
		socialNetworkBoard.deleteAllPosts();
	}

	@Test
	public void testCreatePost() {
		//Given
		User user = new User("John", "Doe", "nn");
		Post post = new Post("Test post", "Post body", user, LocalDate.now());

		//When
		socialNetworkBoard.createPost(post);

		int usersQuantity = socialNetworkBoard.getListOfRegisteredUsers().size();
		int postQuantity = socialNetworkBoard.getAllPosts().size();

		//Then
		Assert.assertEquals(1, usersQuantity);
		Assert.assertEquals(1, postQuantity);

		//CleanUp
		socialNetworkBoard.deleteAllUsers();
		socialNetworkBoard.deleteAllPosts();
	}

	@Test
	public void testGetAllUserPosts() {
		//Given
		User user1 = new User("John", "Doe", "nn");
		User user2 = new User("Joanna", "White", "joan");
		Post post1 = new Post("Test post1", "Post body1", user1, LocalDate.now());
		Post post2 = new Post("Test post2", "Post body2", user1, LocalDate.now());

		//When
		socialNetworkBoard.createPost(post1);
		socialNetworkBoard.createPost(post2);

		int user1postQuantity = socialNetworkBoard.getListOfUsersPosts(user1.getId()).size();
		int user2postQuantity = socialNetworkBoard.getListOfUsersPosts(user2.getId()).size();

		//Then
		Assert.assertEquals(2, user1postQuantity);
		Assert.assertEquals(0, user2postQuantity);

		//CleanUp
		socialNetworkBoard.deleteAllUsers();
		socialNetworkBoard.deleteAllPosts();
	}

	@Test
	public void testAddFollowingUser() {
		//Given
		User user1 = new User("John", "Doe", "nn");
		User user2 = new User("Joanna", "White", "joan");

		socialNetworkBoard.addUser(user1);
		socialNetworkBoard.addUser(user2);

		socialNetworkBoard.addFollowing(user1.getId(), user2.getId());

		//When
		int user1FollowQuantity = socialNetworkBoard.getListOfRegisteredUsers().stream().filter(u -> u.equals(user1)).findFirst().orElse(null).getFollowing().size();
		int user2FollowQuantity = socialNetworkBoard.getListOfRegisteredUsers().stream().filter(u -> u.equals(user2)).findFirst().orElse(null).getFollowing().size();

		//Then
		Assert.assertEquals(1, user1FollowQuantity);
		Assert.assertEquals(0, user2FollowQuantity);

		//CleanUp
		socialNetworkBoard.deleteAllUsers();
		socialNetworkBoard.deleteAllPosts();
	}

	@Test
	public void testGetPostsOfUserFollowing() {
		//Given
		User user1 = new User("John", "Doe", "nn");
		User user2 = new User("Joanna", "White", "joan");
		Post post1 = new Post("Test post1", "Post body1", user1, LocalDate.now());
		Post post2 = new Post("Test post2", "Post body2", user1, LocalDate.now());
		Post post3 = new Post("Test post3", "Post body3", user1, LocalDate.now());

		//When
		socialNetworkBoard.createPost(post1);
		socialNetworkBoard.createPost(post2);
		socialNetworkBoard.createPost(post3);
		socialNetworkBoard.addUser(user2);

		socialNetworkBoard.addFollowing(user2.getId(), user1.getId());

		int numberOfPostOfUser1Follwing = socialNetworkBoard.getPostsOfUserFollowing(user1.getId()).size();
		int numberOfPostOfUser2Follwing = socialNetworkBoard.getPostsOfUserFollowing(user2.getId()).size();

		//Then
		Assert.assertEquals(0, numberOfPostOfUser1Follwing);
		Assert.assertEquals(3, numberOfPostOfUser2Follwing);

		//CleanUp
		socialNetworkBoard.deleteAllUsers();
		socialNetworkBoard.deleteAllPosts();
	}
}
