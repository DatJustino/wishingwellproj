package com.example.wishingproj.controller;

import com.example.wishingproj.model.User;
import com.example.wishingproj.model.Wish;
import com.example.wishingproj.repository.UserRepository;
import com.example.wishingproj.repository.WishRepository;
import com.example.wishingproj.service.UserService;
import com.example.wishingproj.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
@Controller
public class HomeController {

    WishService wishService = new WishService();
    UserService userService = new UserService();

    @GetMapping("/")
    public String index() {
      return "index";
    }


    @GetMapping("/login")
    public String Login(){
      return "login";
    }

  /*@GetMapping("/justinoian")
  public String JustinoIan(){
    return "userprofiles/justinoian";
  }*/



  //--skal ikke bruges endnu--
  @GetMapping("/omos")
  public String omos(){
    return "omos";
  }

 /* @GetMapping("/userrepo")
  public List<User> userRepo() {
    return userService.getAllUsers();
  }*/

   /* @GetMapping("/wishrepo")
    public List<Wish> wishRepo() {
      return wishService.getAllWishes();
    }*/
  }
