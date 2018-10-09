package com.blog.demo.service.Impl;

import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import com.blog.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

   private UserRepository userRepository;

   @Autowired
   public UserServiceImp( UserRepository userRepository )
   {
      this.userRepository = userRepository;
   }

   @Override
   public User save( User user )
   {
      return userRepository.save( user );
   }
}
