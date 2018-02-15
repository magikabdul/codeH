package com.code.challenge.socialnetwork.service;

import com.code.challenge.socialnetwork.domain.IdGenerator;
import com.code.challenge.socialnetwork.domain.Post;
import com.code.challenge.socialnetwork.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SocialNetworkBoard {
    private final static Logger LOGGER = LoggerFactory.getLogger(SocialNetworkBoard.class);

    @Autowired
    private IdGenerator idGenerator;

    private Set<User> listOfRegisteredUsers = new HashSet<>();
    private List<Post> listOfUsersPosts = new ArrayList<>();

    public void addUser(User user) {
        if (!listOfRegisteredUsers.contains(user)) {
            user.setId(idGenerator.getIdByEntity("User"));
        }

        listOfRegisteredUsers.add(user);
    }

    public Set<User> getListOfRegisteredUsers() {
        return listOfRegisteredUsers;
    }

    public void createPost(Post post) {
        User user = post.getAuthor();
        Long userId = null;

        if (!listOfRegisteredUsers.contains(user)) {
            addUser(user);
        } else {
            for (User u : listOfRegisteredUsers) {
                if (u.equals(user)) {
                    userId = u.getId();
                }
            }

            post.getAuthor().setId(userId);
        }

        listOfUsersPosts.add(post);

        LOGGER.info("Post created: " + post);
    }

    public List<Post> getAllPosts() {
        return listOfUsersPosts;
    }

    public List<Post> getListOfUsersPosts(Long userId) {
        return listOfUsersPosts.stream()
                .filter(post -> post.getAuthor().getId().equals(userId))
                .sorted((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()))
                .collect(Collectors.toList());
    }

    public void addFollowing(Long userId, Long followingId) {
        User user = findUser(userId);
        User followingUser = findUser(followingId);

        if (user != null && followingUser != null) {
            user.addFollowing(followingUser);
        }

        LOGGER.info("FOLLOWING added: " + user + " --- follows ---" + followingUser);
    }

    public Set<User> getFollowing(Long userId) {
        return listOfRegisteredUsers.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .map(User::getFollowing)
                .orElseGet(HashSet::new);
    }

    private User findUser(Long userId) {
        return listOfRegisteredUsers.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<Post> getPostsOfUserFollowing(Long userId) {
        Set<User> userSet = getFollowing(userId);

        return listOfUsersPosts.stream()
                .filter(post -> userSet.contains(post.getAuthor()))
                .sorted((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()))
                .collect(Collectors.toList());
    }

    public void deleteAllUsers() {
        idGenerator.resetGenerator("User");
        listOfRegisteredUsers.clear();
    }

    public void deleteAllPosts() {
        listOfUsersPosts.clear();
    }
}
