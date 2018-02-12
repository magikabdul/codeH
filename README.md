# SOCIAL NETWORK APPLICATION

This repository contains two different versions of the code. First version was commited on Friday 9th 2018 and API description is provided at the bottom of this document.
Last Sunday 12th 2018, I wrote controller code in differnt way. First I provided new class `IdGenerator` which generates id's for users and than I was able to use id's in exchange of full user data (`firstName`, `lastName` and `nick`).

# Intro (version 12th February)
This code uses memory for storing all the values. Using `IdGenerator` class I limited using of POST method. 

Full documentation for API is available thru Swagger - http://localhost:8080/swagger-ui.html

![swagerr screenshot](https://github.com/magikabdul/codeH/blob/master/postman_tests/swagger.PNG)



----------------------------------------------------------------------------------------------------------------------------------------

# Intro (version 9th February)
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
