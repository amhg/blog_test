package com.blog.demo.service;

import com.blog.demo.model.Post;
import com.blog.demo.model.Users;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.repository.UserRepository;
import com.blog.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

   private UserRepository userRepository;
   private PostRepository postRepository;

   @Autowired
   public UserService(UserRepository userRepository, PostRepository postRepository )
   {
      this.userRepository = userRepository;
      this.postRepository = postRepository;
   }

   public Optional<Users> findByUsername(String username )
   {
      return userRepository.findByUsername( username );
   }

   public Optional<Users> findByEmail(String email )
   {
      return userRepository.findByEmail( email );
   }

   public List<Users> findAllUsers() {
      return (List<Users>) userRepository.findAll();
   }

   public Optional<Users> findById(Long id) {
      return userRepository.findById(id);
   }

   public Users save(Users user )
   {
      return userRepository.save( user );
   }
}