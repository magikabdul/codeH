package com.code.challenge.socialnetwork.service;

import com.code.challenge.socialnetwork.domain.Post;
import com.code.challenge.socialnetwork.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SocialNetworkBoard {
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

        System.out.println();
        listOfUsersPosts.forEach(System.out::println);
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

        for (User u : listOfRegisteredUsers) {
            if (u.equals(user)) {
                System.out.println(user + " --- follows ---" + u.getFollowing());
            }
        }
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
        List<Post> posts;

        System.out.println("List of following ------" + userSet);

        listOfUsersPosts.forEach(post -> System.out.println(post.getAuthor()));
        System.out.println("Authors of post ------");

        posts = listOfUsersPosts.stream()
                .filter(post -> userSet.contains(post.getAuthor()))
                .sorted((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()))
                .collect(Collectors.toList());

        System.out.println("List of post of following ------" + posts);

        return posts;
    }
}
