# SOCIAL NETWORK APPLICATION

# Intro
This code uses memory for storing all the values. Thats why I used POST method body to pass full user information. 

## Documentation
This section describes all available functions.

* *__addUser__* - adds single user. Application allows only for one copy of the same user.

Body of POST method must to include:
```
{
  "firstName": "",
  "lastName": "",
  "nick": ""
}
```

* *__getAllUsers__* - show list of all stored users

No additional arguments required

* *__createPost__* - creates new post, if user doesn't exists - new user is created too. Maximum length of post content is limited to 140 characters. `(SCENARIO: POSTING)`

Body of POST method must to include:
```
{
  "title": "",
  "content": "",
  "author": 
  {
    "firstName": "",
    "lastName": "",
    "nick": ""	
  },
  "created": "YYYY-MM-DD"
}
```

* *__getAllPosts__* - shows all the posts stored in memory

No additional arguments required

* *__getUserAllPosts__* - show all the posts of a particular user in reverse chronological order `(SCENARIO: WALL)`

Body of POST method must to include:
```
{
  "firstName": "",
  "lastName": "",
  "nick": ""
}
```

* *__addFollowingUser__* - this function allows to the user to add an another user to be following `(SCENARIO: FOLLOWING)`

Body of POST method must to include:
```
[
  {
    "firstName": "",
    "lastName": "",
    "nick": ""
  },
  {
    "firstName": "",
    "lastName": "",
    "nick": ""
  }
]
```
First part - user, second - user to follow

* *__getUserFollowing__* - shows following users of a particular user

Body of POST method must to include:
```
{
  "firstName": "",
  "lastName": "",
  "nick": ""
}
```

* *__getPostsOfUserFollowing__* - list of all messagess posted by all the people user follow in reverse chronological order `(SCENARIO: TIMELINE)`

Body od POST method must to include:
```
{
  "firstName": "",
  "lastName": "",
  "nick": ""
}
```
