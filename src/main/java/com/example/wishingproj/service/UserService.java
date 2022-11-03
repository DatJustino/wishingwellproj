package com.example.wishingproj.service;

import com.example.wishingproj.model.User;
import com.example.wishingproj.repository.DatabaseConnectionManager;
import com.example.wishingproj.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import java.sql.Connection;

public class UserService {

  UserRepository userRepository = new UserRepository();
  Connection conn = DatabaseConnectionManager.getConnection();


/*
  public List<User> getAllUsers() {
    return userRepository.getAllUsers();
  }
*/
    /*public User getUser(WebRequest emailpayload){
      return userRepository.getUser(emailpayload.getParameter("email"));
    }*/


  public void create(WebRequest userpayload) {
    boolean hasEmail;
    boolean hasPassword;

    hasEmail = userRepository.emailVerification(userpayload.getParameter("email"));
    hasPassword = userRepository.passwordVerification("email", "password");
    if (hasEmail == false) {
      User user = new User(
          userpayload.getParameter("email"),
          userpayload.getParameter("password"),
          userpayload.getParameter("name")
      );
      userRepository.create(user);
    } else // if has password
      hasPassword = userRepository.passwordVerification("email","password");
      if (hasPassword == false) {
        System.out.println("wrong password");
      } else

      System.out.println("har allerede bruger");
    }

    public void createURL(WebRequest userpayload, Model model){
      String newmail = userpayload.getParameter("email");
      String URLmail = newmail.substring(0, newmail.indexOf("@"))
          .replace(".","")
          .replace("_","")
          .replace("-","")
          .trim();
      URLmail = "http://localhost:8080/" + URLmail;
      model.addAttribute("URLsharing", URLmail);
    }

  }








