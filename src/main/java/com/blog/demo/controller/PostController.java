package com.blog.demo.controller;

import com.blog.demo.exception.ResourceNotFoundException;
import com.blog.demo.model.Post;
import com.blog.demo.model.Users;
import com.blog.demo.service.PostService;
import com.blog.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<?> getAllCommentsByUserId(@PathVariable(value = "userId") Long userId){
        Optional<Users> u = userService.findById(userId);
        if(!u.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Users user = u.get();
        return new ResponseEntity<>(user.getPosts(), HttpStatus.ACCEPTED);
    }


    @PostMapping("/user/{userId}/post")
    public Post createPost(@PathVariable(value = "userId") Long userId, @RequestBody Post post){
        return userService.findById(userId).map(users -> {post.setUser(users);
            return postService.savePost(post);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));

    }
}

