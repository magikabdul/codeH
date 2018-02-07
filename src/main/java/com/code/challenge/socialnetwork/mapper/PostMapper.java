package com.code.challenge.socialnetwork.mapper;

import com.code.challenge.socialnetwork.domain.Post;
import com.code.challenge.socialnetwork.domain.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    @Autowired
    private UserMapper userMapper;

    public Post mapToPost(PostDto postDto) {
        return new Post(postDto.getTitle(),
                postDto.getContent(),
                userMapper.mapToUser(postDto.getAuthor()),
                postDto.getCreated());
    }

    public PostDto mapToPostDto(Post post) {
        return new PostDto(post.getTitle(),
                post.getContent(),
                userMapper.mapToUserDto(post.getAuthor()),
                post.getCreated());
    }
}
