package com.code.challenge.socialnetwork.domain;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class User {
    private String firstName;
    private String lastName;
    private String nick;
    private Set<User> following = new HashSet<>();
    private Long id;

    public User(String firstName, String lastName, String nick) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addFollowing(User user) {
        following.add(user);
    }

    public Set<User> getFollowing() {
        return following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        return nick.equals(user.nick);
    }

    @Override
    public int hashCode() {
        return nick.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
