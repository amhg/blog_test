package com.blog.demo.controller;

import com.blog.demo.exception.ResourceNotFoundException;
import com.blog.demo.model.Post;
import com.blog.demo.model.Users;
import com.blog.demo.service.PostService;
import com.blog.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;
    private PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    //get user
    @GetMapping(value = "/user/{id}")
    private ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<Users> u = userService.findById(id);

        if(!u.isPresent()){
            return new ResponseEntity<>("User not registered" ,HttpStatus.BAD_REQUEST);
        }
        Users user = u.get();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}

/*

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId,
                                                Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

*/