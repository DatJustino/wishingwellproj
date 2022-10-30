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

    @PostMapping("/login")
    public String getWishes(WebRequest userpayload, Model model) {
      //if userexists get wishes
      // else if user doesnt exist, return to indexpage with error message user doesnt exist.

      model.addAttribute("email", userpayload.getParameter("email"));
      boolean hasEmail;
      boolean password;
      hasEmail = wishService.getAllWishesFromUser(userpayload, model);
    //  password = userService.create();
      if (hasEmail){
        return "mypage";
      } else return "index";
      //første email er variabelnavn og andet email er email fra html formen
      //den her model skal være der, da det er den, der sætter email ind i hidden form
      //til brug i wishing_lidt. Uden den kommer email ikke ind i wishing_list
    }

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
