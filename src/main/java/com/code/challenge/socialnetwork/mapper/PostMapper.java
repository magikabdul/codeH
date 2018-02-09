package com.code.challenge.socialnetwork.mapper;

import com.code.challenge.socialnetwork.domain.Post;
import com.code.challenge.socialnetwork.domain.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    private final static int POST_MAX_LENGTH = 140;

    @Autowired
    private UserMapper userMapper;

    public Post mapToPost(PostDto postDto) {
        String content = postDto.getContent();

        if (postDto.getContent().length() > POST_MAX_LENGTH) {
            content = content.substring(0, POST_MAX_LENGTH - 1);
        }

        return new Post(postDto.getTitle(),
                content,
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
