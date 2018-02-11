package com.code.challenge.socialnetwork.controller;

import com.code.challenge.socialnetwork.domain.PostDto;
import com.code.challenge.socialnetwork.domain.UserDto;
import com.code.challenge.socialnetwork.mapper.PostMapper;
import com.code.challenge.socialnetwork.mapper.UserMapper;
import com.code.challenge.socialnetwork.service.SocialNetworkBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class SocialNetworkController {
    @Autowired
    SocialNetworkBoard board;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;

    @RequestMapping(method = RequestMethod.POST, value = "users", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        board.addUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "users")
    public Set<UserDto> getAllUsers() {
        return board.getListOfRegisteredUsers().stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toSet());
    }

    @RequestMapping(method = RequestMethod.POST, value = "posts", consumes = APPLICATION_JSON_VALUE)
    public void createPost(@RequestBody PostDto postDto) {
        board.createPost(postMapper.mapToPost(postDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "posts")
    public List<PostDto> getAllPosts() {
        return board.getAllPosts().stream()
                .map(post -> postMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "users/{userId}")
    public List<PostDto> getUserAllPosts(@PathVariable Long userId) {
        return board.getListOfUsersPosts(userId).stream()
                .map(post -> postMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "users/{userId}/followers/{followerId}")
    public void addFollowingUser(@PathVariable Long userId, @PathVariable Long followerId) {
        board.addFollowing(userId, followerId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "users/{userId}/followers")
    public Set<UserDto> getUserFollowing(@PathVariable Long userId) {
        return board.getFollowing(userId).stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toSet());
    }

    @RequestMapping(method = RequestMethod.GET, value = "users/{userId}/followers/posts")
    public List<PostDto> getPostsOfUserFollowing(@PathVariable Long userId) {
        return board.getPostsOfUserFollowing(userId).stream()
                .map(post -> postMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }
}
