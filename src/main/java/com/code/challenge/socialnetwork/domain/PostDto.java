package com.code.challenge.socialnetwork.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PostDto {
    private final static int POST_MAX_LENGTH = 140;

    private String title;
    private String content;
    private UserDto author;
    private LocalDate created;

    public PostDto(String title, String content, UserDto author, LocalDate created) {
        this.title = title;

        if (content.length() <= POST_MAX_LENGTH) {
            this.content = content;
        } else {
            this.content = content.substring(0, POST_MAX_LENGTH - 1);
        }

        this.author = author;
        this.created = created;
    }
}
