package com.code.challenge.socialnetwork.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private UserDto author;
    private LocalDate created;
}
