package com.code.challenge.socialnetwork.service;

import com.code.challenge.socialnetwork.domain.Post;
import com.code.challenge.socialnetwork.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SocialNetworkBoard {
    private final static Logger LOGGER = LoggerFactory.getLogger(SocialNetworkBoard.class);

    private Set<User> listOfRegisteredUsers = new HashSet<>();
    private List<Post> listOfUsersPosts = new ArrayList<>();

    public void addUser(User user) {
        listOfRegisteredUsers.add(user);
    }

    public Set<User> getListOfRegisteredUsers() {
        return listOfRegisteredUsers;
    }

    public void createPost(Post post) {
        User user = post.getAuthor();

        if (!listOfRegisteredUsers.contains(user)) {
            addUser(user);
        }

        listOfUsersPosts.add(post);

        LOGGER.info("Post created: " + post);
    }

    public List<Post> getAllPosts() {
        return listOfUsersPosts;
    }

    public List<Post> getListOfUsersPosts(User user) {
        return listOfUsersPosts.stream()
                .filter(post -> post.getAuthor().equals(user))
                .sorted((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()))
                .collect(Collectors.toList());
    }

    public void addFollowing(User user, User following) {
        if (listOfRegisteredUsers.contains(user) && listOfRegisteredUsers.contains(following)) {
            for (User u : listOfRegisteredUsers) {
                if (u.equals(user)) {
                    u.addFollowing(following);
                }
            }
        }

        LOGGER.info("FOLLOWING added: " + user + " --- follows ---" + following);
    }

    public Set<User> getFollowing(User user) {
        for (User u : listOfRegisteredUsers) {
            if (u.equals(user)) {
                return u.getFollowing();
            }
        }

        return new HashSet<>();
    }

    public List<Post> getPostsOfUserFollowing(User user) {
        Set<User> userSet = getFollowing(user);

        return listOfUsersPosts.stream()
                .filter(post -> userSet.contains(post.getAuthor()))
                .sorted((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()))
                .collect(Collectors.toList());
    }
}
