package com.code.challenge.socialnetwork.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String nick;
    private Long id;
}
