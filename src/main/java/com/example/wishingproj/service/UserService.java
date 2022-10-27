package com.example.wishingproj.service;

import com.example.wishingproj.model.User;
import com.example.wishingproj.repository.DatabaseConnectionManager;
import com.example.wishingproj.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import java.sql.Connection;
import java.util.List;

public class UserService {

    UserRepository userRepository = new UserRepository();
    Connection conn = DatabaseConnectionManager.getConnection();

    public List<User> getAllUsers() {
      return userRepository.getAllUsers();
    }

    /*public User getUser(WebRequest emailpayload){
      return userRepository.getUser(emailpayload.getParameter("email"));
    }*/


    public void create(WebRequest userpayload) {
      User user = new User(
          userpayload.getParameter("email"),
          userpayload.getParameter("name")
      );
      userRepository.create(user);
    }
  }






