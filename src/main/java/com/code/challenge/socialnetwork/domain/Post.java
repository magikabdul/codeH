package com.code.challenge.socialnetwork.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Post {
    private String title;
    private String content;
    private User author;
    private LocalDate created;

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", created=" + created +
                '}';
    }
}
