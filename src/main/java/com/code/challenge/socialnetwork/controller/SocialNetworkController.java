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
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class SocialNetworkController {
    @Autowired
    SocialNetworkBoard board;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        board.addUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllUsers")
    public Set<UserDto> getAllUsers() {
        return board.getListOfRegisteredUsers().stream().map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toSet());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createPost", consumes = APPLICATION_JSON_VALUE)
    public void createPost(@RequestBody PostDto postDto) {
        board.createPost(postMapper.mapToPost(postDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllPosts")
    public List<PostDto> getAllPosts() {
        return board.getAllPosts().stream().map(post -> postMapper.mapToPostDto(post)).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "getUserAllPosts", consumes = APPLICATION_JSON_VALUE)
    public List<PostDto> getUserAllPosts(@RequestBody UserDto userDto) {
        return board.getListOfUsersPosts(userMapper.mapToUser(userDto)).stream().map(post -> postMapper.mapToPostDto(post)).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addFollowingUser", consumes = APPLICATION_JSON_VALUE)
    public void addFollowingUser(@RequestBody UserDto[] userDto) {
        board.addFollowing(userMapper.mapToUser(userDto[0]), userMapper.mapToUser(userDto[1]));
    }

    @RequestMapping(method = RequestMethod.POST, value = "getUserFollowing")
    public Set<UserDto> getUserFollowing(@RequestBody UserDto userDto) {
        return board.getFollowing(userMapper.mapToUser(userDto)).stream().map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toSet());
    }

    @RequestMapping(method = RequestMethod.POST, value = "getPostsOfUserFollowing")
    public List<PostDto> getPostsOfUserFollowing(@RequestBody UserDto userDto) {
        return board.getPostsOfUserFollowing(userMapper.mapToUser(userDto)).stream().map(post -> postMapper.mapToPostDto(post)).collect(Collectors.toList());
    }
}
